(ns io.kosong.adk.web
  (:require [integrant.core :as ig]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [io.pedestal.connector]
            [io.pedestal.http.tracing]
            [io.pedestal.http.cors]
            [io.pedestal.http.ring-middlewares]
            [io.pedestal.service.interceptors]
            [io.pedestal.http.body-params]
            [io.pedestal.http.secure-headers]
            [io.pedestal.http.route]
            [io.pedestal.http.http-kit]
            [io.pedestal.interceptor]
            [io.pedestal.interceptor.chain]

            [reitit.ring]
            [reitit.ring.malli]
            [reitit.dev.pretty]
            [reitit.coercion.malli]
            [malli.util]
            [reitit.http]
            [reitit.http.interceptors.muuntaja]
            [reitit.http.interceptors.multipart]
            [reitit.http.coercion]
            [reitit.http.interceptors.parameters]

            [reitit.pedestal]
            [reitit.interceptor]
            [muuntaja.core]

            [io.kosong.adk.web.http-server]
            [io.kosong.adk.web.telemetry]
            [io.kosong.adk.web.agent-registry]
            )
  (:import (com.google.adk.artifacts InMemoryArtifactService)
           (com.google.adk.sessions InMemorySessionService)
           (io.opentelemetry.api GlobalOpenTelemetry OpenTelemetry)
           (java.io File)))

(defn system-config
  [port]
  {:system/agent-registry   {}
   :system/session-service  {}
   :system/artifact-service {}
   :system/telemetry        {}
   :system/app-context      {:session-service  (ig/ref :system/session-service)
                             :artifact-service (ig/ref :system/artifact-service)
                             :agent-registry   (ig/ref :system/agent-registry)
                             :telemetry        (ig/ref :system/telemetry)}
   :system/http-server      {:port        port
                             :app-context (ig/ref :system/app-context)}}
  )

(defonce system nil)

(defmethod ig/init-key :system/agent-registry
  [_ opt]
  (io.kosong.adk.web.agent-registry/->agent-registry))

(defmethod ig/init-key :system/session-service
  [_ opts]
  (InMemorySessionService.))

(defmethod ig/init-key :system/artifact-service
  [_ opts]
  (InMemoryArtifactService.))

(defmethod ig/init-key :system/telemetry
  [_ opts]
  (let [telemetry (io.kosong.adk.web.telemetry/->telemetry opts)]
    (GlobalOpenTelemetry/resetForTest)
    (GlobalOpenTelemetry/set ^OpenTelemetry (:otel-sdk telemetry))
    telemetry))

(defmethod ig/init-key :system/app-context
  [_ opts]
  opts)

(defmethod ig/init-key :system/http-server
  [_ opts]
  (let [connector (io.kosong.adk.web.http-server/->connector opts)]
    (io.pedestal.connector/start! connector)
    connector))

(defmethod ig/halt-key! :system/http-server
  [_ connector]
  (io.pedestal.connector/stop! connector)
  nil)

(defn clear-agent-registry!
  []
  (when-let [agent-registry (system :system/agent-registry)]
    (io.kosong.adk.web.agent-registry/clear-agent-registry! agent-registry)))

(defn load-agent-registry!
  [ns]
  (when-let [agent-registry (system :system/agent-registry)]
    (io.kosong.adk.web.agent-registry/load-agent-registry! agent-registry ns)))


(defn load-config
  [config-path]
  (let [s (slurp config-path)]
    (ig/read-string s)))

(defn stop!
  []
  (when system
    (ig/halt! system)
    (alter-var-root #'system (constantly nil))))

(defn start! [config]
  (when system
    (stop!))
  (let [system-map (ig/init config)]
    (alter-var-root #'system (constantly system-map))))

(defn run
  [{:keys [config-path port agent-namespaces]}]
  (let [port (or port 8080)
        config (if (some? config-path)
                 (load-config config-path)
                 (system-config port))]
    (start! config)
    (when agent-namespaces
      (doseq [ns agent-namespaces]
        (require ns)
        (load-agent-registry! ns)))))

(defn main
  [args]
  (let [args        (apply hash-map args)
        config-path (or (first args)
                        "config.edn")
        port        (or (:port args) 8080)
        config      (load-config config-path)]
    (start! config)))
