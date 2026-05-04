(ns io.kosong.adk.memory-test
  "Smoke tests for `io.kosong.adk.memory`."
  (:require
   [clojure.test :refer [deftest is testing]]
   [io.kosong.adk.memory :as memory])
  (:import
   (com.google.adk.memory InMemoryMemoryService)))

(deftest in-memory-memory-service-instance
  (testing "`in-memory-memory-service` returns a non-nil `InMemoryMemoryService`"
    (let [svc (memory/in-memory-memory-service)]
      (is (some? svc) "service is not nil")
      (is (instance? InMemoryMemoryService svc)
          "service is an InMemoryMemoryService"))))
