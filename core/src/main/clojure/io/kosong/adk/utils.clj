(ns io.kosong.adk.utils
  (:require [clojure.datafy :refer [datafy]])
  (:import (clojure.lang PersistentArrayMap)
           (java.util List Map Optional Set)))

(defn user-content
  "Coerce a plain string into user Content data for make-object
  (`{:role \"user\" :parts [{:text ...}]}`). Maps and Content instances
  pass through unchanged."
  [x]
  (if (string? x) {:role "user" :parts [{:text x}]} x))

(defn optional-datafy-assoc
  [m k v]
  (cond
    (nil? v)
    m

    (instance? Optional v)
    (if (.isPresent v)
      (optional-datafy-assoc m k (.get v))
      m)

    (instance? List v)
    (assoc m k (mapv datafy v))

    (instance? Set v)
    (assoc m k (set (mapv datafy v)))

    (instance? Map v)
    (assoc m k (PersistentArrayMap/create v))

    (satisfies? clojure.core.protocols/Datafiable v)
    (assoc m k (datafy v))

    :else
    (assoc m k v)))