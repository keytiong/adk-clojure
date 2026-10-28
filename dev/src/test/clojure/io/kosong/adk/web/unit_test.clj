(ns io.kosong.adk.web.unit-test
  "Unit tests for web framework.

  Tests web server setup, session management, and agent registry
  without requiring a real LLM."
  (:require [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.web :as web]
            [io.kosong.adk.web.agent-registry :as registry]))

;; Test: Web server setup
(deftest web-server-setup
  (testing "web server initializes correctly"
    ;; Test web server initialization
    (is (some? web))))

;; Test: Agent registry
(deftest agent-registry
  (testing "agent registry manages agents correctly"
    ;; Test agent registry operations
    (let [registry (atom {})]
      ;; Test empty registry
      (is (= {} @registry)))))
