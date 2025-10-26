(ns agents.multi-tool
  (:require [clojure.string :as str]
            [io.kosong.adk.core :as adk])
  (:import (java.text Normalizer Normalizer$Form)
           (java.time ZonedDateTime ZoneId)
           (java.time.format DateTimeFormatter)))

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
    :name "multi_tool"
    :model "gemini-2.5-flash"
    :tools [#'get-current-time #'get-weather]
    :description "Agent to answer questions about the time and weather in a city."
    :instruction "You are a helpful agent who can answer user questions about the time and weather in a city"))
