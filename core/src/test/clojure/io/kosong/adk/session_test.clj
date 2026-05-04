(ns io.kosong.adk.session-test
  "Unit tests for session state management.

  Tests that session state is properly handled as ConcurrentHashMap,
  session CRUD operations work, datafy behaves correctly, and edge cases
  (nil state, explicit IDs, missing sessions) are covered."
  (:require [clojure.datafy :refer [datafy]]
            [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.sessions :as sessions])
  (:import [java.util Map]))

;; Helper to get session state
(defn session-state [session]
  (.state session))

;; -- State type tests --

(deftest session-state-is-map
  (testing "session state is a java.util.Map (wrapped by ADK State)"
    (let [service (sessions/in-memory-session-service)
          initial-state {:key "value" :number 42}
          session (sessions/create-session service "app" "user" initial-state)]
      (let [state (session-state session)]
        (is (instance? Map state))
        (is (= "value" (get state "key")))
        (is (= 42 (get state "number")))))))

;; -- CRUD tests --

(deftest session-create-get-delete
  (testing "session CRUD operations work correctly"
    (let [service (sessions/in-memory-session-service)]

      ;; 1. Create session
      (let [session (sessions/create-session service "test-app" "test-user"
                                             {:key "value"})]
        (is (some? session))
        (is (= "test-app" (.appName session)))
        (is (= "test-user" (.userId session)))
        (is (some? (.id session)))
        (is (= "value" (get-in (session-state session) ["key"]))))

      ;; 2. Get session
      (let [session (sessions/create-session service "test-app" "test-user"
                                             {:key "value2"})
            session-id (.id session)]
        (let [retrieved (sessions/get-session service "test-app" "test-user" session-id)]
          (is (some? retrieved))
          (is (= session-id (.id retrieved)))
          (is (= "value2" (get-in (session-state retrieved) ["key"])))))

      ;; 3. Delete session
      (let [session (sessions/create-session service "test-app" "test-user"
                                             {:key "value3"})
            session-id (.id session)]
        (sessions/delete-session service "test-app" "test-user" session-id)
        (is (nil? (sessions/get-session service "test-app" "test-user" session-id)))))))

;; -- Edge case tests --

(deftest create-session-nil-state
  (testing "create-session with nil state defaults to empty map"
    (let [service (sessions/in-memory-session-service)
          session (sessions/create-session service "app" "user" nil)]
      (is (some? session))
      (is (empty? (session-state session))))))

(deftest create-session-with-id
  (testing "create-session accepts explicit session-id"
    (let [service (sessions/in-memory-session-service)
          session (sessions/create-session service "app" "user" {} "my-custom-id")]
      (is (= "my-custom-id" (.id session))))))

(deftest create-session-stringifies-keys
  (testing "create-session stringify-keys on state map"
    (let [service (sessions/in-memory-session-service)
          session (sessions/create-session service "app" "user" {:nested {:inner "val"}})]
      (let [state (session-state session)]
        ;; stringify-keys recurses into nested maps
        (is (= {"inner" "val"} (get state "nested")))))))

(deftest get-session-missing-session
  (testing "get-session returns nil for non-existent session"
    (let [service (sessions/in-memory-session-service)]
      (is (nil? (sessions/get-session service "app" "user" "does-not-exist"))))))

(deftest delete-session-missing-session
  (testing "delete-session does not throw for non-existent session"
    (let [service (sessions/in-memory-session-service)]
      ;; Should not throw
      (sessions/delete-session service "app" "user" "does-not-exist")
      (is true))))

;; -- list-sessions tests --

(deftest list-sessions-test
  (testing "list-sessions returns all sessions for a user"
    (let [service (sessions/in-memory-session-service)]
      (sessions/create-session service "app" "user" {:a 1} "id-1")
      (sessions/create-session service "app" "user" {:b 2} "id-2")
      (let [sessions (sessions/list-sessions service "app" "user")]
        (is (= 2 (count sessions)))))

    (testing "list-sessions returns empty for user with no sessions"
      (let [service (sessions/in-memory-session-service)
            sessions (sessions/list-sessions service "app" "nobody")]
        (is (= 0 (count sessions)))))))

;; -- datafy tests --

(deftest session-datafy-test
  (testing "datafy returns expected structure"
    (let [service (sessions/in-memory-session-service)
          session (sessions/create-session service "app" "user" {:key "value"} "test-id")
          data    (datafy session)]
      (is (= "app" (:app-name data)))
      (is (= "user" (:user-id data)))
      (is (= "test-id" (:id data)))
      (is (some? (:state data))))))
