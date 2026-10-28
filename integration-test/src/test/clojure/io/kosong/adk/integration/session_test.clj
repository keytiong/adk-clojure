(ns io.kosong.adk.integration.session-test
  "Integration tests for session lifecycle and state management.

  Tests session CRUD operations, state persistence, and multi-user isolation
  using a real LLM backend (Vertex AI primary, Ollama fallback)."
  (:require [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.core :as adk]
            [io.kosong.adk.sessions :as sessions]))

(def test-app-name "adk-integration")
(def test-user-id "integration-test-user")

(defn session-state [session]
  (.state session))

(defn setup-session-service
  []
  (sessions/in-memory-session-service))

(defn create-test-session
  [service app-name user-id state]
  (sessions/create-session service app-name user-id state))

(defn get-test-session
  [service app-name user-id session-id]
  (sessions/get-session service app-name user-id session-id))

(defn delete-test-session
  [service app-name user-id session-id]
  (sessions/delete-session service app-name user-id session-id))

(defn list-test-sessions
  [service app-name user-id]
  (sessions/list-sessions service app-name user-id))

(deftest session-lifecycle
  (testing "full session lifecycle: create, get, delete"
    (let [service (setup-session-service)
          state {:initial-key "initial-value"}
          session (create-test-session service test-app-name test-user-id state)]
      (is (some? session))
      (is (= test-app-name (.appName session)))
      (is (= test-user-id (.userId session)))
      (is (some? (.id session)))
      (let [retrieved (get-test-session service test-app-name test-user-id (.id session))]
        (is (some? retrieved))
        (is (= test-app-name (.appName retrieved)))
        (is (= test-user-id (.userId retrieved)))
        (is (= (.id session) (.id retrieved))))
      (delete-test-session service test-app-name test-user-id (.id session))
      (is (nil? (get-test-session service test-app-name test-user-id (.id session)))))))

(deftest state-persists
  (testing "session state persists across operations"
    (let [service (setup-session-service)
          initial-state {:counter 0 :status "created"}
          session (create-test-session service test-app-name test-user-id initial-state)]
      (is (= "created" (get-in (session-state session) ["status"])))
      (let [retrieved (get-test-session service test-app-name test-user-id (.id session))]
        (is (= "created" (get-in (session-state retrieved) ["status"])))
        (is (= 0 (get-in (session-state retrieved) ["counter"]))))
      (delete-test-session service test-app-name test-user-id (.id session)))))

(deftest multi-user-isolation
  (testing "different users see different sessions"
    (let [service (setup-session-service)
          user1-state {:user-id "user-1"}
          user2-state {:user-id "user-2"}
          session1 (create-test-session service test-app-name "user-1" user1-state)
          session2 (create-test-session service test-app-name "user-2" user2-state)]
      (is (some? session1))
      (is (some? session2))
      (is (not= (.id session1) (.id session2)))
      (let [retrieved1 (get-test-session service test-app-name "user-1" (.id session1))]
        (is (= "user-1" (get-in (session-state retrieved1) ["user-id"]))))
      (let [retrieved2 (get-test-session service test-app-name "user-2" (.id session2))]
        (is (= "user-2" (get-in (session-state retrieved2) ["user-id"])))))))
