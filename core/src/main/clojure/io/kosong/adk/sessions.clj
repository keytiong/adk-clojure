(ns io.kosong.adk.sessions
  (:require [clojure.core.protocols :refer [Datafiable]]
            [io.kosong.adk.utils :refer [optional-datafy-assoc]]
            [io.kosong.java])
  (:import (com.google.adk.sessions BaseSessionService GetSessionConfig InMemorySessionService ListSessionsResponse Session VertexAiSessionService)
           (com.google.genai.types HttpOptions)
           (java.util Map Optional)
           (java.util.concurrent ConcurrentHashMap)))

(defn in-memory-session-service
  []
  (InMemorySessionService.))

(defn vertex-ai-session-service
  [& {:keys [project location google-credentials http-options]}]
  (let [http-options       (if http-options
                             (Optional/of (io.kosong.java/make-object HttpOptions http-options))
                             (Optional/empty))
        google-credentials (if google-credentials
                             (Optional/of google-credentials)
                             (Optional/empty))]
    (VertexAiSessionService. project location google-credentials http-options)))

(defn create-session
  ([^BaseSessionService session-service app-name user-id]
   (create-session session-service app-name user-id nil))
  ([^BaseSessionService session-service app-name user-id state]
   (create-session session-service app-name user-id state nil))
  ([^BaseSessionService session-service app-name user-id state session-id]
   (let [state          (or state {})
         state          (clojure.walk/stringify-keys state)
         concurrent-map (if (some? state)
                          (ConcurrentHashMap. ^Map state)
                          (ConcurrentHashMap.))
         session-id     (or session-id (str (random-uuid)))]
     (-> session-service
         (.createSession app-name user-id concurrent-map session-id)
         (.blockingGet)))))

(defn get-session
  ([^BaseSessionService session-service app-name user-id session-id]
   (get-session session-service app-name user-id session-id (Optional/empty)))
  ([^BaseSessionService session-service app-name user-id session-id get-session-config]
   (-> session-service
       (.getSession app-name, user-id, session-id get-session-config)
       (.blockingGet))))

(defn delete-session
  ([^BaseSessionService session-service app-name user-id session-id]
   (-> session-service
       (.deleteSession app-name, user-id, session-id)
       (.blockingAwait))))

(defn list-sessions
  ([^BaseSessionService session-service app-name user-id]
   (let [^ListSessionsResponse result (-> session-service
                                          (.listSessions app-name user-id)
                                          (.blockingGet))]
     (-> result (.sessions)))))


(extend-protocol Datafiable
  Session
  (datafy [^Session x]
    (-> {}
        (optional-datafy-assoc :user-id (.userId x))
        (optional-datafy-assoc :id (.id x))
        (optional-datafy-assoc :app-name (.appName x))
        (optional-datafy-assoc :state (.state x))
        (optional-datafy-assoc :events (.events x))
        (optional-datafy-assoc :last-update-time (.getLastUpdateTimeAsDouble x)))))

(extend-protocol Datafiable
  GetSessionConfig
  (datafy [^GetSessionConfig x]
    (-> {}
        (optional-datafy-assoc :num-recent-events (.numRecentEvents x))
        (optional-datafy-assoc :after-timestamp (.afterTimestamp x)))))
