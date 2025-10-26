(ns io.kosong.adk.web.handlers
  (:require [io.kosong.adk.web.telemetry]
            [io.kosong.adk.web.agent-graph :as agent-graph]
            [camel-snake-kebab.core :as csk]
            [clojure.java.io :as io]
            [clojure.core.async :as async]
            [io.kosong.adk.core :as adk]
            [io.pedestal.interceptor]
            [io.pedestal.http.sse]
            [clojure.walk]
            [reitit.ring]
            [charred.api :refer [write-json-str]])
  (:import (com.google.adk.agents RunConfig$StreamingMode)))

(defn index-html
  [_]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (slurp (io/resource "public/index.html"))})

(def default-handler
  (reitit.ring/routes
    (reitit.ring/create-resource-handler {:path "/"})
    (reitit.ring/create-default-handler)))

(defn list-apps
  [app-context req]
  (let [registry @(:agent-registry app-context)
        apps     (vec (keys registry))]
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    (charred.api/write-json-str apps)}))

(defn create-session
  [app-context req]
  (let [app-name (-> req :parameters :path :app-name)
        user-id  (-> req :parameters :path :user-id)
        state    (-> req :parameters :body)
        session  (adk/create-session app-context :app-name app-name :user-id user-id :state state)]
    (if session
      {:status 200
       :body   session}
      (throw (ex-info (format "Error creating session for user %s" user-id) {})))))

(defn list-sessions
  [app-context req]
  (let [app-name (-> req :parameters :path :app-name)
        user-id  (-> req :parameters :path :user-id)
        sessions (adk/list-sessions app-context :app-name app-name :user-id user-id)]
    {:status 200
     :body   sessions}))

(defn get-session
  [app-context req]
  (let [app-name   (-> req :path-params :app-name)
        user-id    (-> req :path-params :user-id)
        session-id (-> req :path-params :session-id)
        session    (adk/get-session app-context app-name user-id session-id)]
    {:status 200
     :body   session}))

(defn delete-session
  [app-context req]
  (let [app-name   (-> req :path-params :app-name)
        user-id    (-> req :path-params :user-id)
        session-id (-> req :path-params :session-id)]
    (adk/delete-session app-context app-name user-id session-id)
    {:status 204}))

(defn ->camel-case-string-key [m]
  (let [f (fn [[k v]] [(csk/->camelCaseString k) v])]
    (clojure.walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))

(defn to-sse-event
  [adk-event]
  {:name "adk-event"
   :data (write-json-str (->camel-case-string-key adk-event))
   :id   (:id adk-event)})

(defn run-sse
  [app-context event-chan context]
  (let [b               (-> context :request :body-params)
        app-name        (:app-name b)
        agent           (get (-> app-context :agent-registry deref) app-name)
        session-id      (:session-id b)
        streaming       (:streaming b)
        run-config      (if streaming
                          {:streaming-mode RunConfig$StreamingMode/SSE}
                          {:streaming-mode RunConfig$StreamingMode/NONE})
        user-id         (:user-id b)
        new-message     (:new-message b)
        session-context (-> (adk/agent-context
                              :app-name app-name
                              :user-id user-id
                              :session-service (:session-service app-context)
                              :artifact-service (:artifact-service app-context))
                            (adk/with-session session-id))
        events          (adk/run-async session-context agent new-message run-config)
        xf              (map to-sse-event)]
    (clojure.core.async/pipeline 1 event-chan xf events)))

(defn run-sse-interceptor
  [app-context]
  {:name  ::run-sse
   :enter (fn [context]
            (io.pedestal.http.sse/start-stream (partial run-sse app-context) context))})

(defn get-trace-by-event
  [app-context req]
  (let [event-id  (-> req :path-params :event-id)
        telemetry (:telemetry app-context)
        spans     (io.kosong.adk.web.telemetry/find-trace-by-event telemetry event-id)]
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    (write-json-str spans)}))


(defn get-trace-by-session
  [app-context req]
  (let [session-id (-> req :path-params :session-id)
        telemetry  (:telemetry app-context)
        spans      (io.kosong.adk.web.telemetry/find-trace-by-session telemetry session-id)]
    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    (write-json-str spans)}))

(defn get-graph
  [app-context req]
  (let [app-name         (-> req :path-params :app-name)
        user-id          (-> req :path-params :user-id)
        session-id       (-> req :path-params :session-id)
        event-id         (-> req :path-params :event-id)
        agent-registry   (:agent-registry app-context)
        agent            (get @agent-registry app-name)

        session          (adk/get-session app-context app-name user-id session-id)
        event            (->> (:events session)
                              (filter (fn [evt] (= (:id evt) event-id)))
                              (first))
        author           (:author event)
        author->fn-pairs (->> (get-in event [:content :parts])
                              (filter #(some? (:function-call %)))
                              (map :function-call)
                              (map :name)
                              (filter some?)
                              (map #(vector author %)))
        fn->author-pairs (->> (get-in event [:content :parts])
                              (filter #(some? (:function-response %)))
                              (map :function-response)
                              (map :name)
                              (filter some?)
                              (map #(vector % author)))
        highlight-pairs  (concat author->fn-pairs fn->author-pairs)
        highlight-pairs  (if (empty? highlight-pairs)
                           [[author author]]
                           highlight-pairs)]
    (cond
      (nil? agent)
      {:status 404
       :header {"Content-Type" "application/json"}
       :body   (write-json-str {"dotSrc" (str "Agent app not found: " app-name)})}

      (nil? event)
      {:status 200
       :header {"Content-Type" "application/json"}
       :body   (write-json-str {"dotSrc" nil})}

      :else
      (if-let [dot-source (agent-graph/get-agent-graph-dot-source agent highlight-pairs)]
        {:status  200
         :headers {"Content-Type" "application/json"}
         :body    (write-json-str {"dotSrc" dot-source})}
        {:status  200
         :headers {"Content-Type" "application/json"}
         :body    (write-json-str {"dotSrc" "Could not generate graph for this event."})})
      )))

(defn list-eval-sets
  [app-context req]
  {:status 200
   :body   []})

(defn list-eval-results
  [app-context req]
  {:status 200
   :body   []})

