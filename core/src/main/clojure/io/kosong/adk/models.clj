(ns io.kosong.adk.models
  (:require [io.kosong.java]
            [io.kosong.autovalue])
  (:import (com.google.adk.models LlmRegistry LlmRegistry$LlmFactory)))

(io.kosong.autovalue/register-autovalue-class com.google.adk.models.LlmRequest)
(io.kosong.autovalue/register-autovalue-class com.google.adk.models.LlmResponse)

(defn register-llm-factory!
  [model-name-pattern llm-factory]
  (let [factory (if (instance? LlmRegistry$LlmFactory llm-factory)
                  llm-factory
                  (reify LlmRegistry$LlmFactory
                    (create [_ model-name]
                      (llm-factory model-name))))]
    (LlmRegistry/registerLlm model-name-pattern factory)))
