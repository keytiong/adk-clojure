(ns io.kosong.adk.models
  (:require [clojure.core.protocols :refer [Datafiable]]
            [io.kosong.adk.protocols :as p]
            [io.kosong.adk.utils :refer [optional-datafy-assoc]])
  (:import (clojure.lang IPersistentMap)
           (com.google.adk.models LlmRegistry LlmRegistry$LlmFactory LlmRequest LlmRequest$Builder LlmResponse)
           (com.google.genai.types EditImageParameters$Builder FinishReason GenerateContentConfig HttpOptions)))


(defn register-llm-factory!
  [model-name-pattern llm-factory]
  (let [factory (if (instance? LlmRegistry$LlmFactory llm-factory)
                  llm-factory
                  (reify LlmRegistry$LlmFactory
                    (create [_ model-name]
                      (llm-factory model-name))))]
    (LlmRegistry/registerLlm model-name-pattern factory)))


(extend-protocol Datafiable
  LlmRequest
  (datafy [^LlmRequest x]
    (-> {}
        (optional-datafy-assoc :model (.model x))
        (optional-datafy-assoc :contents (.contents x))
        (optional-datafy-assoc :config (.config x))
        (optional-datafy-assoc :live-connect-config (.liveConnectConfig x))
        (optional-datafy-assoc :tools (.tools x))
        (optional-datafy-assoc :first-system-instruction (.getFirstSystemInstruction x))
        (optional-datafy-assoc :system-instructions (.getSystemInstructions x)))))

(extend-protocol Datafiable
  LlmResponse
  (datafy [^LlmResponse x]
    (-> {}
        (optional-datafy-assoc :content (.content x))
        (optional-datafy-assoc :grounding-metadata (.groundingMetadata x))
        (optional-datafy-assoc :partial (.partial x))
        (optional-datafy-assoc :turn-complete (.turnComplete x))
        (optional-datafy-assoc :error-code (.errorCode x))
        (optional-datafy-assoc :error-message (.errorMessage x))
        (optional-datafy-assoc :interrupted (.interrupted x)))))

(extend-protocol p/IntoLlmResponse
  IPersistentMap
  (into-llm-response [x]
    (let [{:keys [content grounding-metadata partial
                  turn-complete error-code error-message
                  interrupted]} x
          b (LlmResponse/builder)]
      (when (some? content)
        (.content b (p/into-content content)))
      (when (some? grounding-metadata)
        (.groundingMetadata (p/into-grounding-metadata grounding-metadata)))
      (when (some? partial)
        (.partial b ^Boolean partial))
      (when (some? turn-complete)
        (.turnComplete b ^Boolean turn-complete))
      (when (some? error-code)
        (.errorCode b ^FinishReason  (p/into-finish-reason error-code)))
      (when (some? error-message)
        (.errorMessage b ^String error-message))
      (when (some? interrupted)
        (.interrupted b ^Boolean interrupted))
      (.build b))))