(ns io.kosong.adk.integration.setup
  "Registers a catch-all LLM factory so integration tests run against a real
  OpenAI-compatible server (llama.cpp by default). Any model name a test asks
  for (\"test-model\", \"ollama/qwen3:8b\", ...) is served by the single model
  configured via TEST_LLM_MODEL."
  (:require [io.kosong.adk.models :as models])
  (:import (com.google.adk.models.langchain4j LangChain4j)
           (dev.langchain4j.model.openai OpenAiChatModel OpenAiStreamingChatModel)
           (java.time Duration)))

(def ^:private base-url
  (or (System/getenv "LLAMA_CPP_BASE_URL") "http://spark-aa1d:9931/v1"))

(def ^:private real-model
  (or (System/getenv "TEST_LLM_MODEL")
      "unsloth/Qwen3.8-Flash-Next-GGUF:Q4_K_XL"))

(def ^:private timeout (Duration/ofSeconds 180))

(defn register!
  "Register the catch-all factory. Safe to call more than once."
  []
  (models/register-llm-factory!
    ".*"
    (fn [_requested-model-name]
      (.build
        (doto (LangChain4j/builder)
          (.chatModel
            (.build (doto (OpenAiChatModel/builder)
                      (.baseUrl base-url)
                      (.apiKey "none")
                      (.modelName real-model)
                      (.timeout timeout))))
          (.streamingChatModel
            (.build (doto (OpenAiStreamingChatModel/builder)
                      (.baseUrl base-url)
                      (.apiKey "none")
                      (.modelName real-model)
                      (.timeout timeout))))
          (.modelName real-model))))))

(comment
  (register!))

;; Register on namespace load; integration test namespaces require this ns.
(when-not *compile-files*
  (register!))
