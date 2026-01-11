(ns io.kosong.autovalue
  "Utilities for converting between Google AutoValue objects and Clojure maps.

  AutoValue is a source code generator for value classes in Java. This namespace
  provides bidirectional conversion:
  - Java AutoValue instances -> Clojure maps (via Datafiable protocol)
  - Clojure maps -> Java AutoValue instances (via into-autovalue multi method)

  Supports nested AutoValue objects and collections."
  (:require [clojure.datafy :refer [datafy]]
            [camel-snake-kebab.core :as csk])
  (:import [java.lang.reflect Modifier Method ParameterizedType]
           [java.util Optional]
           (org.reflections Reflections)
           (org.reflections.util ConfigurationBuilder)))


(defmulti into-autovalue (fn [cls data] cls))

;; =============================================================================
;; AutoValue Detection
;; =============================================================================

;; AutoValue uses @AutoValue annotation with RetentionPolicy.CLASS, which is
;; not visible at runtime. We detect AutoValue classes by checking for the
;; generated class naming pattern: AutoValue_ClassName

(defn- autovalue-instance?
  "Check if object is an AutoValue instance by inspecting class name.
  AutoValue generates concrete classes with pattern 'AutoValue_ClassName'."
  [obj]
  (when obj
    (.contains (.getName (class obj)) ".AutoValue_")))

(defn- autovalue-enum-type?
  "Determine whether this class is a Google auto generated class representing
  an Enum type. Such class has an inner Java Enum type Known and a knownEnum
  method"
  [cls]
  (let [known-enum-class-name (str (.getName cls) "$Known")
        known-enum-method     (try
                                (.getMethod cls "knownEnum" (into-array Class []))
                                (catch NoSuchMethodException _e
                                  nil))]
    (if (some? known-enum-method)
      (= known-enum-class-name (.getName (.getReturnType known-enum-method)))
      false)))

(defn- autovalue-enum-instance?
  [obj]
  (autovalue-enum-type? (.getClass obj)))

(defn optional-datafy-assoc
  [m k v]
  (cond
    (nil? v)
    m

    (instance? Optional v)
    (if (.isPresent v)
      (optional-datafy-assoc m k (.get v))
      m)

    (instance? java.util.List v)
    (assoc! m k (mapv datafy v))

    (instance? java.util.Set v)
    (assoc! m k (set (mapv datafy v)))

    (instance? java.util.Map v)
    (assoc! m k (clojure.lang.PersistentArrayMap/create v))

    (autovalue-enum-instance? v)
    (assoc! m k (.name (.knownEnum v)))

    :else
    (assoc! m k (datafy v))))

(defn- autovalue-type
  "Resolve the data class that this auto value object implements"
  [obj]
  (when (autovalue-instance? obj)
    (let [super-class (.getSuperclass (.getClass obj))]
      (when-not (= super-class java.lang.Object)
        super-class))))

(defn- find-generated-class
  "Find the generated AutoValue_ClassName for an abstract class.
  Returns the Class object if found, nil otherwise."
  [cls]
  (try
    (let [package-name   (some-> (.getPackage cls)
                                 (.getName))
          simple-name    (.getSimpleName cls)
          autovalue-name (if (some? package-name)
                           (str package-name ".AutoValue_" simple-name)
                           (str "AutoValue_" simple-name))]
      (Class/forName autovalue-name))
    (catch ClassNotFoundException _
      nil)))

(defn- autovalue-type?
  "Check if a class (abstract or concrete) is an AutoValue type.
  Works with both the abstract class definition and generated implementations."
  [type]
  (if (instance? Class type)
    (or
      ;; Case 1: Already the generated class
      (.contains (.getName type) ".AutoValue_")
      ;; Case 2: Abstract class with generated implementation
      (some? (find-generated-class type)))
    false))

(defn- property-method?
  [^java.lang.reflect.Method method]
  (and (not= Void/TYPE (.getReturnType method))
       (zero? (.getParameterCount method))
       (Modifier/isAbstract (.getModifiers method))
       (Modifier/isPublic (.getModifiers method))
       (not= "toBuilder" (.getName method))))

(defn- optional-type?
  [type]
  (and (instance? java.lang.reflect.ParameterizedType type)
       (= "java.util.Optional" (-> type (.getRawType) (.getTypeName)))))

(defn- list-type?
  [^java.lang.reflect.Type type]
  (and (instance? java.lang.reflect.ParameterizedType type)
       (= "java.util.List" (-> ^java.lang.reflect.ParameterizedType type (.getRawType) (.getTypeName)))))

(defn- map-type?
  [^java.lang.reflect.Type type]
  (and (instance? java.lang.reflect.ParameterizedType type)
       (= "java.util.Map" (-> ^java.lang.reflect.ParameterizedType type (.getRawType) (.getTypeName)))))

(defn- unbox-any-optional-type
  [^java.lang.reflect.Type type]
  (if (optional-type? type)
    (aget (.getActualTypeArguments ^java.lang.reflect.ParameterizedType type) 0)
    type))

(defn- unbox-any-list-type
  [^java.lang.reflect.Type type]
  (if (list-type? type)
    (aget (.getActualTypeArguments ^java.lang.reflect.ParameterizedType type) 0)
    type))

(defn- extract-property-info-from-method
  [^java.lang.reflect.Method property-method]
  (let [property-name (.getName property-method)
        property-type (.getGenericReturnType property-method)]
    {:property-name    property-name
     :property-keyword (csk/->kebab-case-keyword property-name)
     :list-type?       (list-type? (-> property-type unbox-any-optional-type))
     :actual-type      (-> property-type unbox-any-optional-type unbox-any-list-type)}))

(defn- get-autovalue-properties
  [clazz]
  (->> (.getMethods clazz)
       (filter property-method?)
       (mapv extract-property-info-from-method)))

(defn- emit-property
  [x {:keys [property-name property-keyword]}]
  (let [accessor (symbol property-name)]
    `(optional-datafy-assoc ~property-keyword (. ~x ~accessor))))

(defn- emit-datafy-autovalue-impl
  [cls]
  (when-not (autovalue-type? cls)
    (ex-info "Class is not an AutoValue type" {:class cls}))
  (let [x     (symbol "x")
        t     (symbol (.getTypeName cls))
        props (get-autovalue-properties cls)]
    `(extend-type ~t
       clojure.core.protocols/Datafiable
       (datafy
         [~x]
         (-> (transient {})
             ~@(map (fn [p] (emit-property x p)) props)
             (persistent!))))))

(defn- emit-builder-setter
  [b data {:keys [property-name property-keyword
                  list-type?
                  actual-type]}]
  (let [v        (symbol "v")
        prop-sym (symbol property-name)
        m        (symbol "m")]
    `(when-some [~v (~property-keyword ~data)]
       ~(cond
          (and list-type? (autovalue-type? actual-type))
          `(. ~b ~prop-sym (mapv (fn [~m] (into-autovalue ~actual-type ~m)) ~v))

          (and (not list-type?) (autovalue-type? actual-type))
          `(. ~b ~prop-sym (into-autovalue ~actual-type ~v))

          :else
          `(. ~b ~prop-sym ~v)))))

(defn- emit-map-to-autovalue
  [cls data]
  (let [b              (symbol "b")
        create-builder (symbol (.getName cls) "builder")
        props          (get-autovalue-properties cls)]
    `(let [~b (~create-builder)]
       ~@(map (fn [p] (emit-builder-setter b data p)) props)
       (.build ~b))))

(defn- emit-into-autovalue-method
  [cls]
  (let [data (symbol "data")
        _cls (symbol "_cls")]
    `(defmethod into-autovalue ~cls [~_cls ~data]
       (cond
         (instance? ~cls ~data)
         ~data
         (instance? clojure.lang.IPersistentMap ~data)
         ~(emit-map-to-autovalue cls data)))))

(defn- find-autovalue-types
  [package]
  (let [c (-> (ConfigurationBuilder.)
              (.forPackage package (into-array ClassLoader [])))
        r (Reflections. c)]
    (-> r
        (.getTypesAnnotatedWith com.google.auto.value.AutoValue true))))


(defmacro register-autovalue-class
  [cls-sym]
  (let [cls-name (name cls-sym)
        cls      (Class/forName cls-name)]
    (if (autovalue-type? cls)
      `(do
         (emit-into-autovalue-method ~cls)
         (emit-datafy-autovalue-impl ~cls)
         nil)
      (throw (ex-info "invalid auto value class" {})))))

(defmacro register-autovalue-package [package]
  (let [cls (find-autovalue-types package)]
    `(do
       ~@(map (fn [c] (emit-into-autovalue-method c)) cls)
       ~@(map (fn [c] (emit-datafy-autovalue-impl c)) cls)
       nil)))
