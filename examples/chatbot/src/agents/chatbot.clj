(ns agents.chatbot
  (:require [io.kosong.adk.core :as adk]
            [io.kosong.adk.models]
            [clojure.string :as str])
  (:import (com.google.adk.models.langchain4j LangChain4j)
           (dev.langchain4j.model.openai OpenAiChatModel OpenAiStreamingChatModel)
           (java.text Normalizer Normalizer$Form)
           (java.time ZonedDateTime ZoneId)
           (java.time.format DateTimeFormatter)))

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

(io.kosong.adk.models/register-llm-factory!
  "ollama/.*"
  (open-ai-compatible-llm-factory
    :base-url "http://127.0.0.1:11434/v1"
    :api-key ""
    :model-name-fn (strip-prefix "ollama/")))

(defn get-current-time
  "Returns the current time in a specified city

  Returns:
    map: status and result or error msg"
  ([city]
   (get-current-time nil city))
  ([tool-context
    ^{:schema {:title       "city"
               :description "The name of the city for which to retrieve the current time."
               :type        "STRING"}}
    city]
   (let [normalized-city (-> (Normalizer/normalize city Normalizer$Form/NFD)
                             (.trim)
                             (.toLowerCase)
                             (.replaceAll "(\\p{IsM}+|\\p{IsP}+)", "")
                             (.replaceAll "\\s+", "_"))
         zone-id         (->> (ZoneId/getAvailableZoneIds)
                              (filter #(str/ends-with? (.toLowerCase %) (str "/" normalized-city)))
                              (first))]
     (if zone-id
       {:status "success"
        :result (str "The current time in " city " is " (-> (ZoneId/of zone-id)
                                                            (ZonedDateTime/now)
                                                            (.format (DateTimeFormatter/ofPattern "HH:mm"))))}
       {:status "error"
        :result (str "Sorry , I don't have timezone information for " city ".")}
       ))))

(defn get-weather
  "Retrieves the current weather report for a specified city.

  Returns:
    map : A map containing the weather information.
          Indicates a 'status' key ('success or 'error').
          If 'success', includes a 'result' key with weather details.
          If 'error', includes an 'error_message' key."
  ([^{:schema {:title       "city"
               :description "The name of the city (e.g. 'New York', 'London', 'Tokyo')"
               :type        "STRING"}}
    city]
   (if (= "new york" (.toLowerCase city))
     {:status "success"
      :result "The weather in New York is sunny with a temperature of 25 degree Celsius (77 degrees Fahrenheit"}
     {:status "error"
      :result (str "Weather information for " city " is not available")})))

(def root-agent
  (adk/llm-agent
    :name "chatbot"
    :model "docker/ai/qwen3"
    ;;:model "ollama/qwen3.5:9b"
    :tools [#'get-current-time #'get-weather]
    :description "Simple AI assistant to answer user questions."
    :instruction "You are a helpful assistant who can answer user questions"))

