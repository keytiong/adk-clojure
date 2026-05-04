(ns io.kosong.adk.artifacts-test
  "Smoke tests for `io.kosong.adk.artifacts`."
  (:require
   [clojure.test :refer [deftest is testing]]
   [io.kosong.adk.artifacts :as artifacts])
  (:import
   (com.google.adk.artifacts InMemoryArtifactService)))

(deftest in-memory-artifact-service-instance
  (testing "`in-memory-artifact-service` returns a non-nil `InMemoryArtifactService`"
    (let [svc (artifacts/in-memory-artifact-service)]
      (is (some? svc) "service is not nil")
      (is (instance? InMemoryArtifactService svc)
          "service is an InMemoryArtifactService"))))
