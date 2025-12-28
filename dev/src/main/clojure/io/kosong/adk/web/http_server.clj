(ns io.kosong.adk.web.http-server
  (:require [camel-snake-kebab.core :as csk]
            [io.pedestal.http.tracing]
            [io.pedestal.service.interceptors]
            [io.pedestal.http.secure-headers]
            [io.pedestal.http.http-kit]
            [io.pedestal.connector]

            [reitit.dev.pretty]
            [reitit.coercion.malli]

            [reitit.pedestal]

            [reitit.http]
            [reitit.http.coercion]
            [reitit.http.interceptors.parameters]
            [reitit.http.interceptors.multipart]
            [reitit.http.interceptors.muuntaja]

            [io.kosong.adk.web.routes]

            [io.kosong.adk.web.handlers]
            [muuntaja.core]
            [malli.util]))


(def default-interceptors
  [(io.pedestal.http.tracing/request-tracing-interceptor)
   io.pedestal.service.interceptors/log-request
   #_(when allowed-origins
       (io.pedestal.http.cors/allow-origin allowed-origins))
   io.pedestal.service.interceptors/not-found
   #_(when session-options
       (io.pedestal.http.ring-middlewares/session session-options))
   #_(io.pedestal.http.ring-middlewares/content-type {:mime-types extra-mime-types})
   #_io.pedestal.http.route/query-params
   #_(io.pedestal.http.body-params/body-params)
   (io.pedestal.http.secure-headers/secure-headers {:content-security-policy-settings
                                                    {:default-src "http: https: ws: wss: data:"
                                                     :style-src   "'self' 'unsafe-inline'"
                                                     :script-src  "'self' 'unsafe-inline' 'unsafe-eval'"
                                                     :connect-src  "'self' 'unsafe-inline'"}})])

(def muuntaja-options
  (-> muuntaja.core/default-options
      (assoc-in [:formats "application/json" :encoder-opts] {:encode-key-fn csk/->camelCaseString})
      (assoc-in [:formats "application/json" :decoder-opts] {:decode-key-fn csk/->kebab-case-keyword})))

(def reitit-router-config
  {:exception reitit.dev.pretty/exception
   :data      {:coercion     (reitit.coercion.malli/create
                               {:error-keys       #{:coercion
                                                    :in
                                                    :schema
                                                    :value
                                                    :errors
                                                    :humanized}
                                :compile          malli.util/open-schema
                                :strip-extra-keys true
                                :default-values   true
                                :options          nil})
               :muuntaja     (muuntaja.core/create muuntaja-options)
               :interceptors [(reitit.http.interceptors.parameters/parameters-interceptor)
                              (reitit.http.interceptors.muuntaja/format-negotiate-interceptor)
                              (reitit.http.interceptors.muuntaja/format-response-interceptor)
                              (reitit.http.interceptors.muuntaja/format-request-interceptor)
                              (reitit.http.coercion/coerce-response-interceptor)
                              (reitit.http.coercion/coerce-request-interceptor)
                              (reitit.http.interceptors.multipart/multipart-interceptor)]}})

(defn reitit-http-router
  [app-context]
  (let [routes (io.kosong.adk.web.routes/->routes app-context)
        router (reitit.http/router
                 routes
                 reitit-router-config)]
    (reitit.pedestal/routing-interceptor
      router
      io.kosong.adk.web.handlers/default-handler)))


(defn ->connector
  [{:keys [host port app-context]
    :or   {host "localhost"
           port 8080}}]
  (let [routing-interceptor (reitit-http-router app-context)]
    (-> (io.pedestal.connector/default-connector-map host port)
        (io.pedestal.connector/with-interceptors default-interceptors)
        (io.pedestal.connector/with-interceptor routing-interceptor)
        (io.pedestal.http.http-kit/create-connector {:server-header      "adk-web/http-kit"
                                                     :allow-virtual?     true
                                                     :max-ws             10485760   ; 10MB WebSocket frame size
                                                     :ws-max-idle-time   300000}))) ; 5min idle timeout
  )
