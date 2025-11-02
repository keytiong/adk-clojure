(ns io.kosong.adk.web.agent-registry)

(defn ->agent-registry
  []
  (atom {}))

(defn find-root-agents
  [ns]
  (->> (ns-map ns)
       (vals)
       (filter var?)
       (map var-get)
       (filter #(instance? com.google.adk.agents.LlmAgent %))
       (filter #(not (.parentAgent %)))))

(defn clear-agent-registry!
  [registry]
  (reset! registry {}))

(defn load-agent-registry!
  [registry ns]
  (let [agent-map (->> (find-root-agents ns)
                       (reduce (fn [m a] (assoc m (.name a) a)) {}))]
    (swap! registry merge agent-map)))
