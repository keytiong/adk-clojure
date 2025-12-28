(ns io.kosong.adk.runner
  (:require [clojure.core.async :as async]
            [clojure.tools.logging :as log]
            [io.kosong.adk.protocols :as p])
  (:import (com.google.adk.agents LiveRequestQueue)))

(defn live-request-queue
  "Creates a LiveRequestQueue with a core.async channel bridge.
   Returns a map with:
   - :queue - the Java LiveRequestQueue instance
   - :channel - core.async channel for putting LiveRequest maps

   LiveRequest maps should have one of:
   - {:content ...} - for turn-by-turn content
   - {:blob ...} - for realtime audio/video blobs
   - {:close true} - to close the connection

   The channel has a blocking buffer of size 10 to apply backpressure to clients."
  []
  (let [^LiveRequestQueue java-queue (LiveRequestQueue.)
        request-ch (async/chan 10)] ; blocking buffer for backpressure
    ;; Bridge: core.async channel -> Java LiveRequestQueue
    (async/go-loop []
      (if-some [live-request-map (async/<! request-ch)]
        (do
          (try
            (.send java-queue (p/into-live-request live-request-map))
            (catch Exception e
              ;; Log error but continue processing
              (log/error "Error offering LiveRequest to queue:" (.getMessage e))))
          (recur))
        ;; Channel closed, close the queue
        (.close java-queue)))
    {:queue java-queue :channel request-ch}))
