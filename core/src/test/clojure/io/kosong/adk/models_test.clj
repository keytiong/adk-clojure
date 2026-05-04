(ns io.kosong.adk.models-test
  "Smoke tests for `io.kosong.adk.models/register-llm-factory!`."
  (:require
   [clojure.test :refer [deftest is testing]]
   [io.kosong.adk.models.mock-llm :as mock-llm])
  (:import
   (com.google.adk.models LlmRegistry LlmRegistry$LlmFactory)))

;; ---------------------------------------------------------------------------
;; Tests
;; ---------------------------------------------------------------------------

(deftest register-llm-factory-raw-fn
  (testing "`register-llm-factory!` accepts a raw fn (reify wrapper path)"
    ;; register-mock-llm! internally calls register-llm-factory! with a raw fn
    ;; If registration succeeded, the mock will intercept requests
    (mock-llm/register-mock-llm! "mock/phase3/fn-raw" "raw-fn-ok")
    (let [agent-name "mock/phase3/fn-raw"]
      ;; The mock-llm helper already exercises this path — just verify
      ;; the factory was registered by checking it doesn't throw
      (is (some? agent-name) "registration did not throw"))))

(deftest register-llm-factory-lmm-factory-instance
  (testing "`register-llm-factory!` accepts an `LlmFactory` directly (passthrough path)"
    (let [factory (reify LlmRegistry$LlmFactory
                    (create [_ model-name]
                      (mock-llm/mock-llm "factory-ok" model-name)))]
      ;; Register the factory directly — should not throw
      (io.kosong.adk.models/register-llm-factory! "mock/phase3/factory-direct" factory)
      (is (some? factory) "registration did not throw"))))
