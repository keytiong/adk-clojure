(ns io.kosong.adk.artifacts
  (:import (com.google.adk.artifacts InMemoryArtifactService)))

(defn in-memory-artifact-service
  []
  (InMemoryArtifactService.))