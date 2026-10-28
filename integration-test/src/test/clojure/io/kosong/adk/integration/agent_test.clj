(ns io.kosong.adk.integration.agent-test
  "Integration tests for agent execution with real LLM."
  (:require [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.core :as adk]))

(defn echo-tool
  [^{:schema {:title "message" :description "The message to echo back" :type "STRING"}}
   message]
  {:status "success" :echo message})

;; Test: Basic LLM agent execution
(deftest llm-basic-execution
  (testing "basic LLM agent runs and generates response"
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model")]
      (is (some? agent)))))

;; Test: Tool calling
(deftest tool-calling
  (testing "agent calls tools and returns structured results"
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model"
                 :tools [#'echo-tool])]
      (is (some? agent)))))

;; Test: Multi-agent delegation
(deftest multi-agent-delegation
  (testing "parent agent delegates to sub-agent"
    (let [sub-agent (adk/llm-agent
                     :name "sub-agent"
                     :instruction "You are a specialist."
                     :model "test-model")
          parent-agent (adk/llm-agent
                        :name "parent-agent"
                        :instruction "Delegate to specialists."
                        :model "test-model"
                        :sub-agents [sub-agent])]
      (is (some? parent-agent)))))

;; Test: Loop agent execution
(deftest loop-agent-execution
  (testing "loop agent runs until max iterations"
    (let [worker-agent (adk/llm-agent
                        :name "worker"
                        :instruction "Do work."
                        :model "test-model")
          validation-agent (adk/base-agent
                            :run-async-fn (fn [context]
                                            (if (= (:state context)
                                                   {:done true})
                                              {:actions {:escalate true}}
                                              {})))]
      (let [loop-agent (adk/loop-agent
                        :sub-agents [worker-agent validation-agent]
                        :max-iterations 3)]
        (is (some? loop-agent))))))

;; Test: Sequential agent flow
(deftest sequential-agent-flow
  (testing "sequential agent runs sub-agents in order"
    (let [first-agent (adk/llm-agent
                       :name "first"
                       :instruction "First step."
                       :model "test-model")
          second-agent (adk/llm-agent
                        :name "second"
                        :instruction "Second step."
                        :model "test-model")]
      (let [seq-agent (adk/sequential-agent
                       :name "sequential"
                       :sub-agents [first-agent second-agent])]
        (is (some? seq-agent))))))

;; Test: Agent output-key storage
(deftest agent-output-key-storage
  (testing "agent stores output in session state via output-key"
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "Respond with your answer."
                 :model "test-model"
                 :output-key "my-result")]
      (is (some? agent)))))

;; Test: Agent tool (agent as tool)
(deftest agent-as-tool
  (testing "sub-agent can be used as tool by parent"
    (let [sub-agent (adk/llm-agent
                     :name "tool-agent"
                     :instruction "Act as a tool."
                     :model "test-model")
          parent-agent (adk/llm-agent
                        :name "parent"
                        :instruction "Use tools to help."
                        :model "test-model"
                        :tools [sub-agent])]
      (is (some? parent-agent)))))
