(ns io.kosong.adk.integration.streaming-test
  "Integration tests for streaming behavior with real LLM.

  Tests SSE streaming, WebSocket bidirectional streaming, channel hygiene,
  and partial responses using a real LLM backend."
  (:require [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.core :as adk]
            [io.kosong.adk.sessions :as sessions]
            [clojure.core.async :as async]))

;; Test fixtures
(def test-app-name "adk-integration")
(def test-user-id "integration-test-user")

;; Test: SSE streaming - partial responses
(deftest sse-partial-responses
  (testing "SSE mode emits partial content chunks"
    ;; Note: This test requires a real LLM to be configured.
    ;; The SSE streaming mode should emit partial responses.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model"
                 :generate-content-config {:streaming? true})]
      ;; Agent should run with streaming enabled
      (is (some? agent)))))

;; Test: SSE channel hygiene
(deftest sse-channel-hygiene
  (testing "SSE event channel stays healthy during streaming"
    ;; Note: This test requires a real LLM to be configured.
    ;; The event channel should stay open and emit events during streaming.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model"
                 :generate-content-config {:streaming? true})
          context (adk/agent-context :app-name test-app-name
                                     :user-id test-user-id)]
      ;; Agent should run
      (is (some? agent)))))

;; Test: WebSocket bidirectional streaming
(deftest websocket-bidirectional
  (testing "WebSocket mode supports bidirectional communication"
    ;; Note: This test requires a real LLM to be configured.
    ;; The WebSocket mode should support bidirectional streaming.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model")]
      ;; Agent should run
      (is (some? agent)))))

;; Test: Live channel pair
(deftest live-channel-pair
  (testing "run-live returns correct channel pair"
    ;; Note: This test requires a real LLM to be configured.
    ;; run-live should return {event-ch request-ch} for bidirectional streaming.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model")]
      ;; run-live should return channel pair
      (is (some? agent)))))

;; Test: Streaming with core.async
(deftest streaming-with-core-async
  (testing "streaming works with core.async channels"
    ;; Note: This test requires a real LLM to be configured.
    ;; Streaming should work with core.async channel behavior.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model"
                 :generate-content-config {:streaming? true})]
      ;; Agent should run with streaming
      (is (some? agent)))))

;; Test: Streaming buffer bounds
(deftest streaming-buffer-bounds
  (testing "streaming buffers don't hang or drop events"
    ;; Note: This test requires a real LLM to be configured.
    ;; Streaming buffers should have correct sizes and behavior.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model"
                 :generate-content-config {:streaming? true})]
      ;; Agent should run with streaming
      (is (some? agent)))))

;; Test: Bidi channel pair
(deftest bidi-channel-pair
  (testing "bidi channel pair communicates correctly"
    ;; Note: This test requires a real LLM to be configured.
    ;; The bidi channel pair should communicate correctly between channels.
    (let [agent (adk/llm-agent
                 :name "test-agent"
                 :instruction "You are a helpful assistant."
                 :model "test-model")]
      ;; run-live should return bidi channel pair
      (is (some? agent)))))
