(ns io.kosong.adk.autovalue-test
  "Tests for autovalue round-trips, edge cases, and the
  `io.kosong.autovalue` namespace utilities."
  (:require
   [clojure.datafy :refer [datafy]]
   [clojure.test :refer [deftest testing is]]
   [io.kosong.adk.generated-types]
   [io.kosong.autovalue :as autovalue]
   [io.kosong.java]))

(def generate-content-config-obj
  (-> (com.google.genai.types.GenerateContentConfig/builder)
      (.topP (float 0.8))
      (.mediaResolution com.google.genai.types.MediaResolution$Known/MEDIA_RESOLUTION_HIGH)
      (.maxOutputTokens (int 4096))
      (.stopSequences ["User:" "Human:"])
      (.systemInstruction (-> (com.google.genai.types.Content/builder)
                              (.role "system")
                              (.parts [(-> (com.google.genai.types.Part/builder)
                                           (.text "Hello")
                                           (.build))
                                       (-> (com.google.genai.types.Part/builder)
                                           (.text "World")
                                           (.build))])))
      (.labels (doto (java.util.HashMap.)
                 (.put "a" "1")
                 (.put "b" "2")))
      (.safetySettings [(-> (com.google.genai.types.SafetySetting/builder)
                            (.category com.google.genai.types.HarmCategory$Known/HARM_CATEGORY_DANGEROUS_CONTENT)
                            (.threshold com.google.genai.types.HarmBlockThreshold$Known/BLOCK_ONLY_HIGH)
                            (.method com.google.genai.types.HarmBlockMethod$Known/PROBABILITY)
                            (.build))])
      (.build)))

(def generate-content-config-data
  {:top-p              (float 0.8)
   :media-resolution   "MEDIA_RESOLUTION_HIGH"
   :max-output-tokens  (int 4096)
   :stop-sequences     ["User:" "Human:"]
   :system-instruction {:role  "system"
                        :parts [{:text "Hello"}
                                {:text "World"}]}
   :labels             {"a" "1"
                        "b" "2"}
   :safety-settings    [{:category  "HARM_CATEGORY_DANGEROUS_CONTENT"
                         :threshold "BLOCK_ONLY_HIGH"
                         :method    "PROBABILITY"}]})

(deftest autovalue-to-map-test
  (testing "autovalue to map"
    (is (= (clojure.datafy/datafy generate-content-config-obj)
           generate-content-config-data))))

(deftest map-to-autovalue-test
  (testing "map to autovalue"
    (let [obj (io.kosong.java/make-object com.google.genai.types.GenerateContentConfig generate-content-config-data)]
      (is (= obj generate-content-config-obj)))))

;; ======================================================================
;; Edge-case tests (Task 2.3)
;; ======================================================================

(deftest register-autovalue-class-non-autovalue-throws
  (testing "`register-autovalue-class` throws for non-AutoValue class"
    (is (thrown? Exception
                 (eval '(autovalue/register-autovalue-class java.lang.String))))))

(deftest nested-autovalue-round-trip
  (testing "Datafy round-trip preserves nested AutoValue fields"
    ;; :system-instruction is an Optional<Content> — the existing data
    ;; already unwraps it via optional-datafy-assoc
    (let [content-data (:system-instruction generate-content-config-data)
          ;; Build Content from the data, datafy it again
          content-obj (io.kosong.java/make-object com.google.genai.types.Content content-data)
          content-data2 (datafy content-obj)]
      (is (= content-data content-data2)
          "nested Content round-trips correctly"))))

(deftest list-optional-round-trip
  (testing "Datafy round-trip preserves List/Set/Optional fields"
    ;; :stop-sequences is an Optional<List<String>>
    (let [stops-data (:stop-sequences generate-content-config-data)
          ;; :labels is a Map<String, String>
          labels-data (-> generate-content-config-obj datafy :labels)]
      (is (= ["User:" "Human:"] stops-data)
          "List field dataifies to vector")
      (is (= {"a" "1" "b" "2"} labels-data)
          "Map field dataifies to map with string keys")
      ;; Rebuild and verify List survives round-trip
      (let [rebuilt (io.kosong.java/make-object com.google.genai.types.GenerateContentConfig
                                                {:stop-sequences ["User:" "Human:"]})
            stops-after (-> rebuilt datafy :stop-sequences)]
        (is (= ["User:" "Human:"] stops-after)
            "List field survives make-object round-trip")))))

(deftest optional-datafy-assoc-enum-value
  (testing "optional-datafy-assoc (autovalue ns) handles Enum values via datafy"
    ;; Plain Java enum instances fall through to the :else branch -> datafy
    (let [enum-val com.google.genai.types.MediaResolution$Known/MEDIA_RESOLUTION_HIGH]
      (let [result (persistent! (autovalue/optional-datafy-assoc (transient {}) :resolution enum-val))]
        (is (some? (:resolution result))
            "enum value is stored")
        (is (instance? com.google.genai.types.MediaResolution$Known (:resolution result))
            (str "enum is stored as Java enum object via datafy, got " (type (:resolution result))))))))

(deftest optional-datafy-assoc-autovalue-enum
  (testing "optional-datafy-assoc (autovalue ns) handles autovalue-enum types via knownEnum"
    ;; MediaResolution is an "autovalue-enum": has inner $Known enum + knownEnum()
    ;; autovalue-enum-instance? dispatches to knownEnum -> .name()
    (let [media-res (com.google.genai.types.MediaResolution. com.google.genai.types.MediaResolution$Known/MEDIA_RESOLUTION_HIGH)]
      (is (= {:resolution "MEDIA_RESOLUTION_HIGH"}
             (persistent! (autovalue/optional-datafy-assoc (transient {}) :resolution media-res)))
          "autovalue-enum instance is converted via knownEnum -> .name()"))
    ;; Also test with a string-based MediaResolution (unknown variant)
    (let [media-res-unknown (com.google.genai.types.MediaResolution. "CUSTOM_VALUE")]
      (is (= {:resolution "MEDIA_RESOLUTION_UNSPECIFIED"}
             (persistent! (autovalue/optional-datafy-assoc (transient {}) :resolution media-res-unknown)))
          "unknown autovalue-enum resolves to UNSPECIFIED via knownEnum"))))
