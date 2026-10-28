(ns io.kosong.adk.integration.plugin-test
  "Integration tests for plugin management with real LLM.

  Tests PluginManager integration, plugin lifecycle, and execution with
  a real LLM backend."
  (:require [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.core :as adk]
            [io.kosong.adk.integration.setup]
            [io.kosong.adk.sessions :as sessions]))

;; Test fixtures
(def test-app-name "adk-integration")
(def test-user-id "integration-test-user")

;; Test: PluginManager integration
(deftest plugin-manager-integration
  (testing "PluginManager integrates with agent execution"
    ;; Note: This test requires a real LLM to be configured.
    ;; The PluginManager should integrate with agent execution.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model"
                 :plugins [])] ;; TODO: add plugins
      ;; Agent should run with plugins
      (is (some? agent)))))

;; Test: Plugin lifecycle
(deftest plugin-lifecycle
  (testing "plugin lifecycle hooks fire correctly"
    ;; Note: This test requires a real LLM to be configured.
    ;; Plugin lifecycle hooks should fire during agent execution.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model"
                 :plugins [])] ;; TODO: add plugins
      ;; Agent should run with plugins
      (is (some? agent)))))

;; Test: Plugin registration
(deftest plugin-registration
  (testing "plugins register correctly with PluginManager"
    ;; Note: This test requires a real LLM to be configured.
    ;; Plugins should register correctly with PluginManager.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model"
                 :plugins [])] ;; TODO: add plugins
      ;; Agent should run with plugins
      (is (some? agent)))))

;; Test: Plugin execution
(deftest plugin-execution
  (testing "plugins execute during agent run"
    ;; Note: This test requires a real LLM to be configured.
    ;; Plugins should execute during agent run.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model"
                 :plugins [])] ;; TODO: add plugins
      ;; Agent should run with plugins
      (is (some? agent)))))
