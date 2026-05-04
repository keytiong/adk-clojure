(ns io.kosong.adk.runner-test
  "Unit tests for `io.kosong.adk.runner/live-request-queue`."
  (:require
   [clojure.core.async :as async]
   [clojure.test :refer [deftest testing is]]
   [io.kosong.adk.runner :as runner])
  (:import
   (com.google.adk.agents LiveRequest LiveRequestQueue)))

;; ---------------------------------------------------------------------------
;; Helpers
;; ---------------------------------------------------------------------------

(defn- await-flowable-item
  "Block until the Flowable emits one item and return it, or nil after timeout.
  Uses `.observeOn(io.reactivex.rxjava3.core.schedulers.Schedulers.trampoline())`
  and `.test()` to synchronously drain emitted items."
  [^io.reactivex.rxjava3.core.Flowable flowable timeout-ms]
  (let [subscriber (.test flowable)]
    (Thread/sleep timeout-ms)
    (.cancel subscriber)
    (let [items (.values subscriber)]
      (if (> (.size items) 0)
        (.get items 0)
        nil))))

;; ---------------------------------------------------------------------------
;; Tests
;; ---------------------------------------------------------------------------

(deftest live-request-queue-returns-map-with-keys
  (testing "`live-request-queue` returns a map with `:queue` and `:channel`"
    (let [{:keys [queue channel]} (runner/live-request-queue)]
      (is (some? queue) "has :queue")
      (is (some? channel) "has :channel")
      ;; Clean up
      (async/close! channel))))

(deftest live-request-queue-channel-is-core-async-channel
  (testing "`:channel` is a core.async channel"
    (let [pair (runner/live-request-queue)
          ch (:channel pair)]
      (is (some? ch) "channel is not nil")
      (is (re-find #"Channel" (str (class ch)))
          (str "channel class name contains 'Channel', got " (class ch)))
      (async/close! ch))))

(deftest live-request-queue-queue-is-live-request-queue-instance
  (testing "`:queue` is a `LiveRequestQueue` instance"
    (let [{:keys [queue]} (runner/live-request-queue)]
      (is (instance? LiveRequestQueue queue)
          "queue is a LiveRequestQueue")
      ;; Clean up
      (.close queue))))

(deftest live-request-queue-channel-sends-live-request
  (testing "putting `{:content ...}` onto channel sends a LiveRequest"
    (let [{:keys [queue channel]} (runner/live-request-queue)]
      (is (instance? LiveRequestQueue queue))
      ;; Put a content map on the channel — the go-loop will
      ;; convert it to a LiveRequest and call (.send queue ...)
      (async/>!! channel {:content {:role "user" :parts [{:text "hello"}]}})
      ;; Give the go-loop a moment to process
      (Thread/sleep 200)
      ;; Subscribe to the queue's Flowable and check that a LiveRequest arrived
      (let [^io.reactivex.rxjava3.core.Flowable flowable (.get queue)
            item (await-flowable-item flowable 500)]
        (is (instance? LiveRequest item)
            "a LiveRequest was sent through the queue"))
      ;; Clean up
      (async/close! channel))))
