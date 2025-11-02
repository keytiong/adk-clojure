(ns io.kosong.adk.memory
  (:import (com.google.adk.memory InMemoryMemoryService)))

(defn in-memory-memory-service
  []
  (InMemoryMemoryService.))
