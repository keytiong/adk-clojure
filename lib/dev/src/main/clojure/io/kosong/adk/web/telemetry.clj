(ns io.kosong.adk.web.telemetry
  (:require [clojure.string])
  (:import (io.opentelemetry.sdk OpenTelemetrySdk)
           (io.opentelemetry.sdk.resources Resource)
           (io.opentelemetry.sdk.trace SdkTracerProvider)
           (io.opentelemetry.sdk.trace.export SimpleSpanProcessor SpanExporter)
           (io.opentelemetry.sdk.trace.data SpanData)
           (io.opentelemetry.api.common AttributeKey Attributes)
           (io.opentelemetry.sdk.common CompletableResultCode)
           (java.util Collection)))

(defn- as-attribute-map
  [^Attributes attributes]
  (->> (.asMap attributes)
       (reduce (fn [m [^AttributeKey k v]] (assoc m (.getKey k) v)) {})))

(defn- as-span-map
  [^SpanData span]
  (let [trace-id       (-> span (.getSpanContext) (.getTraceId))
        span-id        (-> span (.getSpanContext) (.getSpanId))
        parent-span-id (-> span (.getParentSpanId))
        span-name      (-> span (.getName))
        start-time     (-> span (.getStartEpochNanos))
        end-time       (-> span (.getEndEpochNanos))
        attributes     (-> span (.getAttributes) (as-attribute-map))]
    {"trace_id"       trace-id
     "span_id"        span-id
     "parent_span_id" parent-span-id
     "name"           span-name
     "start_time"     start-time
     "end_time"       end-time
     "attributes"     attributes}))

(defn store-span
  [trace-store ^SpanData span]
  (let [span-map   (as-span-map span)
        span-id    (get span-map "span_id")
        trace-id   (get span-map "trace_id")
        event-id   (get-in span-map ["attributes" "gcp.vertex.agent.event_id"])
        session-id (get-in span-map ["attributes" "gcp.vertex.agent.session_id"])
        f          (fn [store span]
                     (-> store
                         (update :spans assoc span-id span)
                         (update-in [:trace->span-index trace-id] (fn [span-ids] (if span-ids (conj span-ids span-id) [span-id])))
                         (update-in [:session->trace-index session-id] (fn [trace-ids] (if trace-ids (conj trace-ids trace-id) [trace-id])))
                         (update-in [:event->span-index] assoc event-id span-id)))]
    (swap! trace-store f span-map)))

(defn ->api-server-span-exporter
  [trace-store]
  (reify SpanExporter
    (^CompletableResultCode export
      [_ ^Collection spans]
      (doseq [^SpanData span spans]
        (let [span-name (.getName span)]
          (when (or (= span-name "call_llm")
                    (= span-name "send_data")
                    (clojure.string/starts-with? span-name "tool_response"))
            (store-span trace-store span))))
      (CompletableResultCode/ofSuccess))

    (^CompletableResultCode flush
      [_]
      (CompletableResultCode/ofSuccess))

    (^CompletableResultCode shutdown
      [_]
      (CompletableResultCode/ofSuccess))))

(defn init-otel-sdk
  [trace-store]
  (let [span-exporter  (io.kosong.adk.web.telemetry/->api-server-span-exporter trace-store)
        resource       (-> (Resource/getDefault)
                           (.merge (Resource/create (Attributes/of (AttributeKey/stringKey "service.name")
                                                                   "adk-web-server"))))
        trace-provider (-> (SdkTracerProvider/builder)
                           (.addSpanProcessor (SimpleSpanProcessor/create span-exporter))
                           (.setResource resource)
                           (.build))
        otel-sdk       (-> (OpenTelemetrySdk/builder)
                           (.setTracerProvider trace-provider)
                           (.build))]
    otel-sdk))

(defn find-trace-by-event
  [telemetry event-id]
  (let [trace-store @(:trace-store telemetry)
        span-id     (get-in trace-store [:event->span-index event-id])
        span        (get-in trace-store [:spans span-id])]
    (-> (merge span (get span "attributes"))
        (dissoc "attributes"))))

(defn find-trace-by-session
  [telemetry session-id]
  (let [trace-store @(:trace-store telemetry)]
    (->> (get-in trace-store [:session->trace-index session-id])
         (map (fn [trace-id] (get-in trace-store [:trace->span-index trace-id])))
         (flatten)
         (map (fn [span-id] (get-in trace-store [:spans span-id])))
         (filter (complement nil?))
         (into []))))

(defn ->telemetry
  [opts]
  (let [trace-store (atom {:spans                {}
                           :trace->span-index    {}
                           :session->trace-index {}
                           :event->span-index    {}})
        otel-sdk    (init-otel-sdk trace-store)]
    {:otel-sdk    otel-sdk
     :trace-store trace-store}))