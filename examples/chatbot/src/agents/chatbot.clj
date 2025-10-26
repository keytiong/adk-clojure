(ns agents.chatbot
  (:require [io.kosong.adk.core :as adk]
            [io.kosong.adk.models]
            [clojure.string :as str])
  (:import (com.google.adk.models.langchain4j LangChain4j)
           (dev.langchain4j.model.openai OpenAiChatModel OpenAiStreamingChatModel)))

(defn open-ai-compatible-llm-factory
  [& {:keys [base-url api-key model-name-fn]}]
  (let [model-name-fn (or model-name-fn identity)]
    (fn [model-name]
      (let [^String model-name   (model-name-fn model-name)
            chat-model           (-> (OpenAiChatModel/builder)
                                     (.baseUrl base-url)
                                     (.apiKey api-key)
                                     (.modelName model-name)
                                     (.build))
            streaming-chat-model (-> (OpenAiStreamingChatModel/builder)
                                     (.baseUrl base-url)
                                     (.apiKey api-key)
                                     (.modelName model-name)
                                     (.build))]
        (LangChain4j. chat-model streaming-chat-model model-name)))))

(defn strip-prefix [prefix]
  (fn [s]
    (if (str/starts-with? s prefix)
      (subs s (.length prefix))
      s)))

(io.kosong.adk.models/register-llm-factory!
  "docker/.*"
  (open-ai-compatible-llm-factory
    :base-url "http://127.0.0.1:12434/engines/llama.cpp/v1"
    :api-key ""
    :model-name-fn (strip-prefix "docker/")))

(def root-agent
  (adk/llm-agent
    :name "chatbot"
    :model "docker/ai/qwen3"
    :tools []
    :description "Simple AI assistant to answer user questions."
    :instruction "You are a helpful assistant who can answer user questions"))

