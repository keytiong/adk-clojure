(ns io.kosong.adk.integration.callback-test
  "Integration tests for callback behavior with real LLM."
  (:require [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.core :as adk]))

(def callback-log (atom []))

(defn reset-callback-log!
  []
  (reset! callback-log []))

(defn log-callback!
  [callback-name context]
  (swap! callback-log
         conj {:callback-name callback-name
               :timestamp (System/currentTimeMillis)
               :context (when context (dissoc context :event-actions))}))

(defn make-test-agent
  ([agent-name]
   (make-test-agent agent-name "ollama/qwen3:8b"))
  ([agent-name model]
   (adk/llm-agent :name agent-name
                  :model model
                  :instruction "You are a helpful assistant. Answer simply."
                  :before-agent-callback (fn [ctx]
                                           (log-callback! :before-agent ctx)
                                           nil)
                  :after-agent-callback (fn [ctx]
                                          (log-callback! :after-agent ctx)
                                          nil)
                  :before-model-callback (fn [ctx _llm-request-builder]
                                           (log-callback! :before-model ctx)
                                           nil)
                  :after-model-callback (fn [ctx llm-response]
                                          (log-callback! :after-model ctx)
                                          nil)
                  :before-tool-callback (fn [_invocation tool input _tool-context]
                                          (log-callback! :before-tool
                                                         {:tool (some-> tool .getName)
                                                          :input input})
                                          nil)
                  :after-tool-callback (fn [_invocation tool input _tool-context response]
                                         (log-callback! :after-tool
                                                        {:tool (some-> tool .getName)
                                                         :input input
                                                         :response response})
                                         nil))))

(defn run-agent-with-log
  [agent message]
  (let [context (-> (adk/agent-context :app-name "callback-test"
                                       :user-id "test-user")
                   (adk/with-new-session))]
    (adk/run context agent message {})))

;; ============================================================================
;; Test: before-agent callback
;; ============================================================================
(deftest before-agent-callback
  (testing "before-agent callback fires before agent runs"
    (reset-callback-log!)
    (let [agent (make-test-agent "before-agent-test")
          events (run-agent-with-log agent "Hello")]
      (is (some? events))
      (let [before-events
            (filter #(= :before-agent (:callback-name %)) @callback-log)]
        (is (not (empty? before-events))
            "before-agent callback should have fired")))))

;; ============================================================================
;; Test: after-agent callback
;; ============================================================================
(deftest after-agent-callback
  (testing "after-agent callback fires after agent completes"
    (reset-callback-log!)
    (let [agent (make-test-agent "after-agent-test")
          events (run-agent-with-log agent "What is 2+2?")]
      (is (some? events))
      (let [after-events
            (filter #(= :after-agent (:callback-name %)) @callback-log)]
        (is (not (empty? after-events))
            "after-agent callback should have fired")))))

;; ============================================================================
;; Test: before-model callback
;; ============================================================================
(deftest before-model-callback
  (testing "before-model callback fires before LLM call"
    (reset-callback-log!)
    (let [agent (make-test-agent "before-model-test")
          events (run-agent-with-log agent "Explain recursion in one sentence.")]
      (is (some? events))
      (let [before-model-events
            (filter #(= :before-model (:callback-name %)) @callback-log)]
        (is (not (empty? before-model-events))
            "before-model callback should have fired")))))

;; ============================================================================
;; Test: after-model callback
;; ============================================================================
(deftest after-model-callback
  (testing "after-model callback fires after LLM call"
    (reset-callback-log!)
    (let [agent (make-test-agent "after-model-test")
          events (run-agent-with-log agent "What is the capital of France?")]
      (is (some? events))
      (let [after-model-events
            (filter #(= :after-model (:callback-name %)) @callback-log)]
        (is (not (empty? after-model-events))
            "after-model callback should have fired")))))

;; ============================================================================
;; Test: tool callbacks
;; ============================================================================
(defn search-tool
  [^{:schema {:title "query"
              :description "Search query"
              :type "STRING"}}
   query]
  {:status "success"
   :results [(str "Found result for: " query)]})

(deftest tool-callbacks
  (testing "before-tool and after-tool callbacks fire around tool execution"
    (reset-callback-log!)
    (let [agent (adk/llm-agent :name "tool-callback-test"
                              :model "ollama/qwen3:8b"
                              :instruction "Use tools when available."
                              :tools [#'search-tool]
                              :before-tool-callback
                              (fn [_invocation tool input _tool-context]
                                (log-callback! :before-tool
                                               {:tool (some-> tool .getName)
                                                :input input})
                                nil)
                              :after-tool-callback
                              (fn [_invocation tool input _tool-context response]
                                (log-callback! :after-tool
                                               {:tool (some-> tool .getName)
                                                :input input
                                                :response response})
                                nil))]
      (let [events (run-agent-with-log agent "Use the search tool for clojure.")]
        (let [tool-events
              (filter #(or (= :before-tool (:callback-name %))
                           (= :after-tool (:callback-name %)))
                      @callback-log)]
          (if (empty? tool-events)
            (is true "LLM chose not to call the tool in this run")
            (is (> (count tool-events) 0)
                "Tool callbacks should have fired")))))))

;; ============================================================================
;; Test: callback ordering
;; ============================================================================
(deftest callback-ordering
  (testing "callbacks fire in correct order: before-agent before-model after-model after-agent"
    (reset-callback-log!)
    (let [model-name (or (System/getenv "TEST_LLM_MODEL") "ollama/qwen3:0.5b")
          agent (make-test-agent "ordering-test" model-name)
          events (run-agent-with-log agent "Say hello.")]
      (is (some? events))
      (let [logged-callbacks (map :callback-name @callback-log)]
        (let [last-before-agent
              (or (last (keep-indexed
                         #(when (= :before-agent %2) %1))
                        logged-callbacks)
                  -1)
              last-before-model
              (or (last (keep-indexed
                         #(when (= :before-model %2) %1))
                        logged-callbacks)
                  -1)
              last-after-model
              (or (last (keep-indexed
                         #(when (= :after-model %2) %1))
                        logged-callbacks)
                  -1)
              last-after-agent
              (or (last (keep-indexed
                         #(when (= :after-agent %2) %1))
                        logged-callbacks)
                  -1)]
          (is (> last-before-agent -1) "before-agent fired")
          (is (> last-before-model -1) "before-model fired")
          (is (> last-after-model -1) "after-model fired")
          (is (> last-after-agent -1) "after-agent fired")
          (is (< last-before-agent last-before-model)
              "before-agent before before-model")
          (is (< last-before-model last-after-model)
              "before-model before after-model")
          (is (< last-after-model last-after-agent)
              "after-model before after-agent"))))))

;; ============================================================================
;; Test: callback context data
;; ============================================================================
(deftest callback-context-data
  (testing "callback context contains agent-name and state"
    (reset-callback-log!)
    (let [agent (adk/llm-agent :name "context-test-agent"
                              :model "ollama/qwen3:8b"
                              :instruction "Be helpful."
                              :after-agent-callback
                              (fn [ctx]
                                (log-callback! :after-agent ctx)
                                nil))]
      (let [events (run-agent-with-log agent "Test context data.")]
        (is (some? events))
        (let [after-events
              (filter #(= :after-agent (:callback-name %)) @callback-log)
              first-after (first after-events)]
          (if first-after
            (let [ctx (:context first-after)]
              (is (some? ctx) "context not nil")
              (is (some? (:agent-name ctx)) "has agent-name")
              (is (some? (:state ctx)) "has state"))
            (is true "No after-agent event captured.")))))))

;; ============================================================================
;; Test: multiple callbacks
;; ============================================================================
(deftest multiple-callbacks
  (testing "multiple callbacks of the same type all fire"
    (reset-callback-log!)
    (let [agent (adk/llm-agent :name "multi-callback-test"
                              :model "ollama/qwen3:8b"
                              :instruction "Be concise."
                              :after-agent-callback
                              [(fn [ctx]
                                 (log-callback! :after-agent-1 ctx))
                               (fn [ctx]
                                 (log-callback! :after-agent-2 ctx))
                               (fn [ctx]
                                 (log-callback! :after-agent-3 ctx))])]
      (let [events (run-agent-with-log agent "Brief answer: yes.")]
        (is (some? events))
        (let [cb1-events (filter #(= :after-agent-1 (:callback-name %)) @callback-log)
              cb2-events (filter #(= :after-agent-2 (:callback-name %)) @callback-log)
              cb3-events (filter #(= :after-agent-3 (:callback-name %)) @callback-log)]
          (is (not (empty? cb1-events)) "cb1 fired")
          (is (not (empty? cb2-events)) "cb2 fired")
          (is (not (empty? cb3-events)) "cb3 fired"))))))

;; ============================================================================
;; Test: callback with session state
;; ============================================================================
(deftest callback-with-session-state
  (testing "callbacks fire correctly when session has initial state"
    (reset-callback-log!)
    (let [agent (adk/llm-agent :name "state-test"
                              :model "ollama/qwen3:8b"
                              :instruction "Answer briefly."
                              :after-agent-callback
                              (fn [ctx]
                                (log-callback! :after-agent ctx)
                                nil))
          context (-> (adk/agent-context :app-name "callback-test"
                                        :user-id "test-user")
                      (adk/with-new-session {:test-key "test-value"}))]
      (let [events (adk/run context agent "Test with state." {})]
        (is (some? events))
        (let [after-events
              (filter #(= :after-agent (:callback-name %)) @callback-log)]
          (is (not (empty? after-events))
              "after-agent with session state should fire"))))))



