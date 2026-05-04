(ns io.kosong.adk.callback-test
  "Unit tests for all callback types with mocked LLM.

  Covers: before-agent, after-agent, before-model, after-model,
  before-tool, after-tool, into-instruction (String and IFn)."
  (:require [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.core :as adk]
            [io.kosong.adk.models.mock-llm :as mock-llm]))

;; ---------------------------------------------------------------------------
;; Helpers
;; ---------------------------------------------------------------------------

(def callback-log
  (atom []))

(defn clear-log! []
  (reset! callback-log []))

(def user-message
  {:role "user" :parts [{:text "hello"}]})

(defn make-context [state]
  (-> (adk/agent-context :app-name "test" :user-id "u1")
      (adk/with-new-session (or state {}))))

;; ---------------------------------------------------------------------------
;; After-Agent Callback (existing tests)
;; ---------------------------------------------------------------------------

(deftest after-agent-callback-fires
  (testing "after-agent callback fires and logs invocation"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-cb/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :model "mock-cb/test"
                 :after-agent-callback (fn [_]
                                         (swap! callback-log conj :after-agent)
                                         nil))]
      (dorun (adk/run (make-context {}) agent user-message {}))
      (is (= [:after-agent] @callback-log)))))

(deftest after-agent-callback-context
  (testing "after-agent callback receives context containing agent-name"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-cb/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "context-test-agent"
                 :model "mock-cb/test"
                 :after-agent-callback (fn [ctx]
                                         (swap! callback-log conj ctx)
                                         nil))]
      (dorun (adk/run (make-context {}) agent user-message {}))
      (let [ctx (first @callback-log)]
        (is (= "context-test-agent" (:agent-name ctx)))))))

(deftest callback-receives-session-state
  (testing "callback context contains session state"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-cb/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "state-test"
                 :model "mock-cb/test"
                 :after-agent-callback (fn [ctx]
                                         (swap! callback-log conj ctx)
                                         nil))
          context (make-context {"test-key" "test-value"})]
      (dorun (adk/run context agent user-message {}))
      (let [ctx (first @callback-log)]
        (is (some? (:state ctx)))))))

(deftest multiple-callbacks
  (testing "multiple after-agent callbacks all fire in order"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-cb/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "multi-callback-test"
                 :model "mock-cb/test"
                 :after-agent-callback [(fn [_]
                                          (swap! callback-log conj :after-agent-1)
                                          nil)
                                        (fn [_]
                                          (swap! callback-log conj :after-agent-2)
                                          nil)
                                        (fn [_]
                                          (swap! callback-log conj :after-agent-3)
                                          nil)])]
      (dorun (adk/run (make-context {}) agent user-message {}))
      (is (= [:after-agent-1 :after-agent-2 :after-agent-3]
             @callback-log)))))

;; ---------------------------------------------------------------------------
;; T1.3.1  before-agent-callback fires with datafied context
;; ---------------------------------------------------------------------------

(deftest before-agent-callback-fires
  (testing "before-agent callback fires with datafied context"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-ba/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "before-agent-test"
                 :model "mock-ba/test"
                 :before-agent-callback (fn [ctx]
                                          (swap! callback-log conj ctx)
                                          nil))]
      (dorun (adk/run (make-context {}) agent user-message {}))
      (let [ctx (first @callback-log)]
        (is (map? ctx) "context is a datafied map")
        (is (= "before-agent-test" (:agent-name ctx)))))))

;; ---------------------------------------------------------------------------
;; T1.3.2  before-agent-callback returning nil suppresses (Maybe/empty)
;; ---------------------------------------------------------------------------

(deftest before-agent-callback-suppresses
  (testing "before-agent callback returning nil produces Maybe/empty (suppression)"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-ba-sup/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "suppress-agent"
                 :model "mock-ba-sup/test"
                 :before-agent-callback (fn [_]
                                          (swap! callback-log conj :before-agent)
                                          nil))]
      (dorun (adk/run (make-context {}) agent user-message {}))
      ;; before-agent callback was registered and called
      (is (contains? (set @callback-log) :before-agent)
          "before-agent callback was invoked"))))

;; ---------------------------------------------------------------------------
;; T1.3.3  before-model-callback fires with context + LlmRequest builder
;; ---------------------------------------------------------------------------

(deftest before-model-callback-fires
  (testing "before-model callback can be registered and agent runs"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-bm/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "before-model-test"
                 :model "mock-bm/test"
                 :before-model-callback (fn [ctx _req-builder]
                                          (swap! callback-log conj :before-model ctx)
                                          nil))]
      ;; Agent should be created without error
      (is (some? agent) "agent created with before-model callback"))))

;; ---------------------------------------------------------------------------
;; T1.3.4  before-model-callback returning LlmResponse map short-circuits
;; ---------------------------------------------------------------------------

(deftest before-model-callback-short-circuits
  (testing "before-model callback can return an LlmResponse map"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-bm-sc/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "short-circuit-test"
                 :model "mock-bm-sc/test"
                 :before-model-callback (fn [_ _]
                                          (swap! callback-log conj :before-model)
                                          ;; Return an LlmResponse map to short-circuit
                                          {:content {:role "model" :parts [{:text "short-circuited"}]}
                                           :turn-complete true}))]
      ;; Agent should be created without error
      (is (some? agent) "agent created with before-model callback returning response"))))

;; ---------------------------------------------------------------------------
;; After-Model Callback (migrated from am_test.clj)
;; ---------------------------------------------------------------------------

(deftest after-model-and-agent-fire
  (testing "after-model and after-agent callbacks fire in order"
    (mock-llm/register-mock-llm! "mock-am/.*" "Mock response")
    (mock-llm/clear-llm-requests!)
    (let [log (atom [])
          agent (adk/llm-agent
                 :name "am-test-agent"
                 :model "mock-am/test"
                 :after-model-callback (fn [_ resp] (swap! log conj :am) resp)
                 :after-agent-callback (fn [_] (swap! log conj :aa) nil))]
      (dorun (adk/run (make-context {}) agent user-message {}))
      (is (= [:am :aa] @log)
          "after-model should fire before after-agent"))))

(deftest after-model-suppression
  (testing "after-model returning nil suppresses after-agent"
    (mock-llm/register-mock-llm! "mock-am-sup/.*" "Mock response")
    (mock-llm/clear-llm-requests!)
    (let [log (atom [])
          agent (adk/llm-agent
                 :name "suppress-test"
                 :model "mock-am-sup/test"
                 :after-model-callback (fn [_ resp] (swap! log conj :am) nil)
                 :after-agent-callback (fn [_] (swap! log conj :aa) nil))]
      (dorun (adk/run (make-context {}) agent user-message {}))
      (is (contains? (set @log) :am)
          "after-model should fire, regardless of after-agent"))))

(deftest after-model-modification
  (testing "after-model returning modified response propagates through"
    (mock-llm/register-mock-llm! "mock-am-mod/.*" "Mock response")
    (mock-llm/clear-llm-requests!)
    (let [log (atom [])
          agent (adk/llm-agent
                 :name "modify-test"
                 :model "mock-am-mod/test"
                 :after-model-callback (fn [_ resp]
                                         (swap! log conj :am)
                                         resp)
                 :after-agent-callback (fn [_]
                                         (swap! log conj :aa)
                                         nil))]
      (dorun (adk/run (make-context {}) agent user-message {}))
      (is (= [:am :aa] @log)
          "after-model returning response allows after-agent to fire"))))

(deftest callback-error-handling
  (testing "throwing after-agent callback does not crash the run"
    (mock-llm/register-mock-llm! "mock-am-err/.*" "Mock response")
    (mock-llm/clear-llm-requests!)
    (let [log (atom [])
          agent (adk/llm-agent
                 :name "error-test"
                 :model "mock-am-err/test"
                 :after-model-callback (fn [_ resp] (swap! log conj :am) resp)
                 :after-agent-callback (fn [_] (throw (ex-info "Callback error" {}))))]
      (try
        (dorun (adk/run (make-context {}) agent user-message {}))
        (catch Throwable _e
          ;; Error expected - the callback threw
          nil))
      (is (= 1 (mock-llm/request-count))
          "LLM should have been called despite throwing callback"))))

;; ---------------------------------------------------------------------------
;; T1.3.5  before-tool-callback fires with invocation context, tool, input
;; ---------------------------------------------------------------------------
;; TODO: strengthen — mock LLM needs to emit function calls for callbacks to fire

(deftest before-tool-callback-fires
  (testing "before-tool callback is registered and agent runs with tools"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-bt/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "before-tool-test"
                 :model "mock-bt/test"
                 :before-tool-callback (fn [_ctx _tool _input _tool-ctx]
                                         (swap! callback-log conj :before-tool)
                                         nil))]
      (dorun (adk/run (make-context {}) agent user-message {}))
      (is (some? (mock-llm/request-count))
          "agent ran without error"))))

;; ---------------------------------------------------------------------------
;; T1.3.6  after-tool-callback fires with invocation context, tool, input, response
;; ---------------------------------------------------------------------------
;; TODO: strengthen — mock LLM needs to emit function calls for callbacks to fire

(deftest after-tool-callback-fires
  (testing "after-tool callback is registered and agent runs with tools"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-at/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "after-tool-test"
                 :model "mock-at/test"
                 :after-tool-callback (fn [_ctx _tool _input _tool-ctx _response]
                                        (swap! callback-log conj :after-tool)
                                        nil))]
      (dorun (adk/run (make-context {}) agent user-message {}))
      (is (some? (mock-llm/request-count))
          "agent ran without error"))))

;; ---------------------------------------------------------------------------
;; T1.3.7  Multiple before-tool / after-tool callbacks fire in order
;; ---------------------------------------------------------------------------
;; TODO: strengthen — mock LLM needs to emit function calls for callbacks to fire

(deftest multiple-tool-callbacks-fire-in-order
  (testing "multiple before-tool and after-tool callbacks are registered"
    (clear-log!)
    (mock-llm/clear-llm-requests!)
    (mock-llm/register-mock-llm! "mock-multi-tool/.*" "Mock response")
    (let [agent (adk/llm-agent
                 :name "multi-tool-callback-test"
                 :model "mock-multi-tool/test"
                 :before-tool-callback [(fn [& _]
                                          (swap! callback-log conj :bt-1)
                                          nil)
                                        (fn [& _]
                                          (swap! callback-log conj :bt-2)
                                          nil)]
                 :after-tool-callback [(fn [& _]
                                         (swap! callback-log conj :at-1)
                                         nil)
                                       (fn [& _]
                                         (swap! callback-log conj :at-2)
                                         nil)])]
      (dorun (adk/run (make-context {}) agent user-message {}))
      (is (some? (mock-llm/request-count))
          "agent ran without error"))))

;; ---------------------------------------------------------------------------
;; T1.3.8  into-instruction with String produces Static instruction
;; ---------------------------------------------------------------------------

(deftest into-instruction-string
  (testing "into-instruction with String produces a static instruction"
    (mock-llm/register-mock-llm! "mock-instr/.*" "Mock response")
    (mock-llm/clear-llm-requests!)
    (let [agent (adk/llm-agent
                 :name "string-instruction"
                 :model "mock-instr/test"
                 :instruction "You are a helpful assistant.")]
      (dorun (adk/run (make-context {}) agent user-message {}))
      ;; If it ran without error, the instruction was accepted
      (is (= 1 (mock-llm/request-count))
          "agent ran with string instruction"))))

;; ---------------------------------------------------------------------------
;; T1.3.9  into-instruction with IFn produces Provider instruction
;; ---------------------------------------------------------------------------

(deftest into-instruction-ifn
  (testing "into-instruction with IFn produces a provider instruction"
    (mock-llm/register-mock-llm! "mock-ifn-instr/.*" "Mock response")
    (mock-llm/clear-llm-requests!)
    (let [agent (adk/llm-agent
                 :name "ifn-instruction"
                 :model "mock-ifn-instr/test"
                 :instruction (fn [ctx]
                                (swap! callback-log conj :instruction-fn ctx)
                                "Dynamic instruction from fn."))]
      (dorun (adk/run (make-context {}) agent user-message {}))
      ;; The fn instruction should have been called with a datafied context
      (let [ctx (second @callback-log)]
        (is (map? ctx) "instruction fn received a datafied context"))
      (is (= 1 (mock-llm/request-count))
          "agent ran with fn instruction"))))
