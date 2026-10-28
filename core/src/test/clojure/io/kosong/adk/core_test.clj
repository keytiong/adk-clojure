(ns io.kosong.adk.core-test
  "Unit tests for core module: agent builders (loop-agent, sequential-agent,
  base-agent) and session retrieval helpers (with-session)."
  (:require [clojure.datafy :refer [datafy]]
            [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.core :as adk])
  (:import [com.google.adk.agents LoopAgent SequentialAgent]
           [io.kosong.adk.agents ClojureAgent]))

;; ---------------------------------------------------------------------------
;; T1.1.1  loop-agent sets name, description, sub-agents, max-iterations
;; ---------------------------------------------------------------------------

(deftest loop-agent-properties
  (testing "loop-agent sets name"
    (let [agent (adk/loop-agent :name "loop-test")]
      (is (= "loop-test" (.name agent)))))
  (testing "loop-agent sets description"
    (let [agent (adk/loop-agent :name "l2" :description "a loop agent")]
      (is (= "a loop agent" (.description agent)))))
  (testing "loop-agent sets sub-agents"
    (let [sub   (adk/llm-agent :name "sub" :model "mock/sub")
          agent (adk/loop-agent :name "l3" :sub-agents [sub])]
      (is (= 1 (count (.subAgents agent))))
      (is (= "sub" (.name (first (.subAgents agent)))))))
  (testing "loop-agent accepts a single sub-agent as a non-collection"
    (let [sub   (adk/llm-agent :name "sub2" :model "mock/sub2")
          agent (adk/loop-agent :name "l4" :sub-agents sub)]
      (is (= 1 (count (.subAgents agent))))
      (is (= "sub2" (.name (first (.subAgents agent)))))))
  (testing "loop-agent sets max-iterations (verified via reflection)"
    (let [agent (adk/loop-agent :name "l5" :max-iterations 42)
          field (.getDeclaredField (class agent) "maxIterations")]
      (.setAccessible field true)
      (let [^Integer max-iterations (.get field agent)]
        (is (= 42 max-iterations) "maxIterations should be 42")))))

;; ---------------------------------------------------------------------------
;; T1.1.2  sequential-agent sets name, description, sub-agents
;; ---------------------------------------------------------------------------

(deftest sequential-agent-properties
  (testing "sequential-agent sets name"
    (let [agent (adk/sequential-agent :name "seq-test")]
      (is (= "seq-test" (.name agent)))))
  (testing "sequential-agent sets description"
    (let [agent (adk/sequential-agent :name "s2" :description "a sequential agent")]
      (is (= "a sequential agent" (.description agent)))))
  (testing "sequential-agent sets sub-agents"
    (let [sub1  (adk/llm-agent :name "child-a" :model "mock/child-a")
          sub2  (adk/llm-agent :name "child-b" :model "mock/child-b")
          agent (adk/sequential-agent :name "s3" :sub-agents [sub1 sub2])]
      (is (= 2 (count (.subAgents agent))))
      (is (= ["child-a" "child-b"]
             (mapv #(.name %) (.subAgents agent))))))
  (testing "sequential-agent accepts a single sub-agent as a non-collection"
    (let [sub   (adk/llm-agent :name "child-c" :model "mock/child-c")
          agent (adk/sequential-agent :name "s4" :sub-agents sub)]
      (is (= 1 (count (.subAgents agent))))
      (is (= "child-c" (.name (first (.subAgents agent))))))))

;; ---------------------------------------------------------------------------
;; T1.1.3  base-agent sets name, description, sub-agents, callbacks
;; ---------------------------------------------------------------------------

(deftest base-agent-properties
  (testing "base-agent sets name"
    (let [agent (adk/base-agent :name "base-test")]
      (is (= "base-test" (.name agent)))))
  (testing "base-agent sets description"
    (let [agent (adk/base-agent :name "b2" :description "a base agent")]
      (is (= "a base agent" (.description agent)))))
  (testing "base-agent sets sub-agents"
    (let [sub   (adk/llm-agent :name "child-d" :model "mock/child-d")
          agent (adk/base-agent :name "b3" :sub-agents [sub])]
      (is (= 1 (count (.subAgents agent))))
      (is (= "child-d" (.name (first (.subAgents agent)))))))
  (testing "base-agent returns a ClojureAgent instance"
    (let [agent (adk/base-agent :name "b4")]
      (is (instance? ClojureAgent agent))))
  (testing "base-agent with after-agent-callback stores callbacks"
    (let [agent (adk/base-agent :name "b5"
                                :after-agent-callback (fn [_] nil))]
      (is (some? (.afterAgentCallback agent))))))

;; ---------------------------------------------------------------------------
;; T1.1.4  with-session retrieves existing session by ID
;; ---------------------------------------------------------------------------

(deftest with-session-retrieves-existing
  (testing "with-session retrieves an existing session by ID"
    (let [session-service (io.kosong.adk.sessions/in-memory-session-service)
          ctx             (adk/agent-context :app-name "test" :user-id "u1"
                                             :session-service session-service)]
      ;; Create a session first
      (let [ctx-with-session (adk/with-new-session ctx {"key" "val"} "existing-id")]
        (is (= "existing-id" (:id (datafy (:session ctx-with-session)))))
        ;; Now retrieve it via with-session
        (let [ctx-retrieved (adk/with-session ctx "existing-id")]
          (is (some? (:session ctx-retrieved)))
          (is (= "existing-id" (:id (datafy (:session ctx-retrieved)))))
          (is (= "val" (get (.state ^com.google.adk.sessions.Session (:session ctx-retrieved)) "key"))))))))

;; ---------------------------------------------------------------------------
;; T1.1.5  with-session throws when session not found
;; ---------------------------------------------------------------------------

(deftest with-session-throws-when-not-found
  (testing "with-session throws when session ID does not exist"
    (let [session-service (io.kosong.adk.sessions/in-memory-session-service)
          ctx             (adk/agent-context :app-name "test" :user-id "u1"
                                             :session-service session-service)]
      (is (thrown? Exception
                   (adk/with-session ctx "does-not-exist"))))))

;; ---------------------------------------------------------------------------
;; RunConfig make-object: non-deprecated builder methods + auto-create-session
;; ---------------------------------------------------------------------------

(deftest run-config-make-object-properties
  (testing "RunConfig maps convert to a RunConfig with the given properties"
    (let [^com.google.adk.agents.RunConfig rc
          (io.kosong.java/make-object
            com.google.adk.agents.RunConfig
            {:streaming-mode                "SSE"
             :max-llm-calls                 7
             :save-input-blobs-as-artifact  true
             :auto-create-session           true})]
      (is (= "SSE" (.. rc streamingMode name)))
      (is (= 7 (.maxLlmCalls rc)))
      (is (true? (.saveInputBlobsAsArtifacts rc)))
      (is (true? (.autoCreateSession rc)))))
  (testing "auto-create-session defaults to false when unset"
    (let [^com.google.adk.agents.RunConfig rc
          (io.kosong.java/make-object com.google.adk.agents.RunConfig {})]
      (is (false? (.autoCreateSession rc))))))

;; ---------------------------------------------------------------------------
;; InvocationContext datafy: 1.0.0 fields
;; ---------------------------------------------------------------------------

(deftest invocation-context-datafy-includes-1-0-0-fields
  (testing "datafied InvocationContext exposes callback-context-data"
    (let [captured (atom nil)
          agent    (adk/base-agent :name "ctx-capture"
                                   :run-async-fn (fn [ctx] (reset! captured ctx) nil))
          ctx      (-> (adk/agent-context :app-name "core-test" :user-id "u1")
                       (adk/with-new-session {}))]
      (adk/run ctx agent {:role "user" :parts [{:text "hi"}]} {})
      (is (map? @captured) "run-async-fn received the datafied context")
      (is (contains? @captured :callback-context-data)
          "datafied context includes :callback-context-data")
      (is (= "ctx-capture" (.name ^com.google.adk.agents.BaseAgent (:agent @captured)))))))

