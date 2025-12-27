(ns io.kosong.adk.experimental
  (:require [io.kosong.adk.core :as adk]
            [clojure.string :as str]))

(defn generative-function
  [& {:as opts}]
  (let [agent (adk/llm-agent opts)
        run-config {}]
    (fn [user-content & {:as state}]
      (let [context (adk/with-new-session (adk/agent-context) state)
            user-content (or user-content "")
            events (adk/run context agent user-content run-config)]
        (->> events
             (map (fn [elem]
                    (if (instance? Exception elem)
                      (throw (ex-info (.getMessage elem) {} elem))
                      (get-in elem [:content :parts 0 :text]))))
             (str/join " "))))))
