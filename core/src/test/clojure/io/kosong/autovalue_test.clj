(ns io.kosong.autovalue-test
  (:require
   [io.kosong.adk.generated-types]
   [io.kosong.java]
   [clojure.test :refer [deftest testing is]]))

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
