(ns io.kosong.adk.events-test
  "Unit tests for IntoEvent / IntoEventActions / Datafy.

  Verifies that Clojure maps round-trip through `into-event` / `into-event-actions`
  and `datafy`, and that the resulting Java objects carry the expected field values."
  (:require [clojure.datafy :refer [datafy]]
            [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.events :as events]
            [io.kosong.adk.protocols :as p])
  (:import [com.google.adk.events Event EventActions]))

;; ---------------------------------------------------------------------------
;; Helpers
;; ---------------------------------------------------------------------------

(def sample-content
  "A minimal Content map suitable for make-object."
  {:role "model"
   :parts [{:text "hello from event"}]})

(def sample-event-map
  "A full event map exercising all IntoEvent fields (minus the heavy metadata types)."
  {:id                "evt-1"
   :invocation-id     "inv-1"
   :author            "test-author"
   :content           sample-content
   :partial           true
   :turn-complete     false
   :error-message     "something went wrong"
   :interrupted       false
   :branch            "branch-a"
   :timestamp         1234567890
   :model-version     "test-model-v1"
   :avg-logprobs      0.95
   :long-running-tool-ids #{"tool-1" "tool-2"}})

(def sample-actions-map
  "A full EventActions map exercising all IntoEventActions fields."
  {:skip-summarization true
   :state-delta        {"counter" 42}
   :artifact-delta     {"note.txt" "some artifact data"}
   :transfer-to-agent  "other-agent"
   :escalate           true
   :end-invocation     true})

;; ---------------------------------------------------------------------------
;; T1.2.1  IntoEvent: Clojure map with all fields → Event → datafy round-trip
;; ---------------------------------------------------------------------------

(deftest into-event-full-map
  (testing "IntoEvent converts a full Clojure map into an Event Java object"
    (let [event (p/into-event sample-event-map)]
      (is (instance? Event event) "result is a com.google.adk.events.Event"))))

(deftest into-event-datafy-round-trip
  (testing "IntoEvent + datafy round-trip preserves key fields"
    (let [event  (p/into-event sample-event-map)
          result (datafy event)]
      (is (= "evt-1"       (:id result)))
      (is (= "inv-1"       (:invocation-id result)))
      (is (= "test-author" (:author result)))
      (is (some?           (:content result))     "content is present (datafied)")
      (is (= true          (:partial result)))
      (is (= false         (:turn-complete result)))
      (is (= "something went wrong" (:error-message result)))
      (is (= false         (:interrupted result)))
      (is (= "branch-a"    (:branch result)))
      (is (= 1234567890    (:timestamp result)))
      (is (= "test-model-v1" (:model-version result)))
      (is (= 0.95          (:avg-logprobs result)))
      (is (= #{"tool-1" "tool-2"} (:long-running-tool-ids result))))))

;; ---------------------------------------------------------------------------
;; T1.2.2  IntoEvent: minimal map (only :content) → Event
;; ---------------------------------------------------------------------------

(deftest into-event-minimal-map
  (testing "IntoEvent with only :content produces a valid Event"
    (let [event (p/into-event {:content sample-content})]
      (is (instance? Event event))
      (is (.isPresent (.content event)) "content is present"))))

(deftest into-event-minimal-datafy
  (testing "minimal Event datafy still carries content"
    (let [event  (p/into-event {:content sample-content})
          result (datafy event)]
      (is (some? (:content result)) "datafied content is present"))))

(deftest into-event-with-actions
  (testing "IntoEvent with :actions produces an Event containing EventActions"
    (let [event (p/into-event {:content sample-content
                               :actions  sample-actions-map})]
      (is (instance? Event event))
      (is (some? (.actions event)) "EventActions is attached")))

  (testing "IntoEvent + datafy with actions round-trip"
    (let [event  (p/into-event {:content sample-content
                                :actions sample-actions-map})
          result (datafy event)]
      (is (some? (:actions result)) "datafied actions are present")
      ;; Spot-check a field inside the datafied actions
      (is (= true (:skip-summarization (:actions result)))))))

;; ---------------------------------------------------------------------------
;; T1.2.3  IntoEventActions: Clojure map → EventActions → datafy round-trip
;; ---------------------------------------------------------------------------

(deftest into-event-actions-full-map
  (testing "IntoEventActions converts a full Clojure map into an EventActions Java object"
    (let [actions (p/into-event-actions sample-actions-map)]
      (is (instance? EventActions actions) "result is a com.google.adk.events.EventActions"))))

(deftest into-event-actions-datafy-round-trip
  (testing "IntoEventActions + datafy round-trip preserves key fields"
    (let [actions (p/into-event-actions sample-actions-map)
          result  (datafy actions)]
      (is (= true (:skip-summarization result)))
      (is (some? (:state-delta result))  "state-delta is present")
      (is (some? (:artifact-delta result)) "artifact-delta is present")
      (is (= "other-agent" (:transfer-to-agent result)))
      (is (= true (:escalate result)))
      (is (= true (:end-invocation result))))))

(deftest into-event-actions-minimal
  (testing "IntoEventActions with a single field works"
    (let [actions (p/into-event-actions {:skip-summarization true})
          result  (datafy actions)]
      (is (= true (:skip-summarization result))))))

;; ---------------------------------------------------------------------------
;; T1.2.4  Datafy Event produces expected Clojure map with correct keys
;; ---------------------------------------------------------------------------

(deftest datafy-event-expected-keys
  (testing "datafy Event produces a map with expected keys"
    (let [event  (p/into-event sample-event-map)
          result (datafy event)]
      (is (map? result) "result is a Clojure map")
      (let [expected-keys #{:id :invocation-id :author :content :actions
                            :partial :turn-complete :error-message
                            :interrupted :branch :timestamp :model-version
                            :avg-logprobs :long-running-tool-ids}]
        (doseq [k expected-keys]
          (is (contains? result k)
              (str "result contains key " k)))))))

(deftest datafy-event-optional-fields-absent
  (testing "datafy Event omits keys for Optional.empty fields"
    (let [event  (p/into-event {:content sample-content})
          result (datafy event)]
      (is (not (contains? result :author))         "author absent")
      (is (not (contains? result :error-message))  "error-message absent")
      (is (not (contains? result :model-version))  "model-version absent"))))

;; ---------------------------------------------------------------------------
;; T1.2.5  Datafy EventActions produces expected Clojure map with correct keys
;; ---------------------------------------------------------------------------

(deftest datafy-event-actions-expected-keys
  (testing "datafy EventActions produces a map with expected keys"
    (let [actions (p/into-event-actions sample-actions-map)
          result  (datafy actions)]
      (is (map? result) "result is a Clojure map")
      (let [expected-keys #{:skip-summarization :state-delta :artifact-delta
                            :transfer-to-agent :escalate :end-invocation}]
        (doseq [k expected-keys]
          (is (contains? result k)
              (str "result contains key " k)))))))

(deftest datafy-event-actions-optional-fields-absent
  (testing "datafy EventActions omits keys for absent optional fields"
    (let [actions (p/into-event-actions {:skip-summarization true})
          result  (datafy actions)]
      (is (not (contains? result :transfer-to-agent)) "transfer-to-agent absent")
      (is (not (contains? result :escalate))          "escalate absent"))))
