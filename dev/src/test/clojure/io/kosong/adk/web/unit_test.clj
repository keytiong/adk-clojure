(ns io.kosong.adk.web.unit-test
  "Unit tests for the web agent registry: root-agent scanning, registration,
  and clearing. No real LLM required (mock models are never called)."
  (:require [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.core :as adk]
            [io.kosong.adk.web.agent-registry :as registry]))

(defn- fixture-ns!
  "Creates a fresh namespace containing two root LlmAgents, one attached
  sub-agent, and a non-agent var."
  []
  (let [ns    (create-ns (symbol (str "unit-test-fixture-" (System/nanoTime))))
        child (adk/llm-agent :name "fixture-child" :model "mock/child")
        root  (adk/llm-agent :name "fixture-root" :model "mock/root"
                             :sub-agents [child])
        other (adk/llm-agent :name "fixture-other" :model "mock/other")]
    (intern ns 'root root)
    (intern ns 'child child)
    (intern ns 'other other)
    (intern ns 'not-an-agent 42)
    ns))

(deftest load-agent-registry-registers-only-root-agents
  (testing "load-agent-registry! indexes root LlmAgents by name, skipping sub-agents and non-agents"
    (let [reg   (registry/->agent-registry)
          ns    (fixture-ns!)
          root  (ns-resolve ns 'root)
          other (ns-resolve ns 'other)]
      (registry/load-agent-registry! reg ns)
      (is (= #{"fixture-root" "fixture-other"} (set (keys @reg)))
          "only root agents are registered")
      (is (identical? @root (get @reg "fixture-root")))
      (is (identical? @other (get @reg "fixture-other"))))))

(deftest clear-agent-registry-empties-registry
  (testing "clear-agent-registry! removes all registered agents"
    (let [reg (registry/->agent-registry)]
      (registry/load-agent-registry! reg (fixture-ns!))
      (is (seq @reg) "registry populated before clear")
      (registry/clear-agent-registry! reg)
      (is (empty? @reg) "registry empty after clear"))))
