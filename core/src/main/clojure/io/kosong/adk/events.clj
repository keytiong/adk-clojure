(ns io.kosong.adk.events
  (:require [io.kosong.adk.utils :refer [optional-datafy-assoc]]
            [io.kosong.adk.protocols :as p]
            [clojure.core.protocols :refer [Datafiable]]
            [io.kosong.java])
  (:import (clojure.lang IPersistentMap)
           (com.google.adk.events Event Event$Builder EventActions EventActions$Builder)
           (com.google.genai.types Content FinishReason GenerateContentResponseUsageMetadata GroundingMetadata)
           (java.util Map Set)
           (java.util.concurrent ConcurrentHashMap)))

(extend-protocol Datafiable
  Event
  (datafy [^Event x]
    (-> {}
        (optional-datafy-assoc :id (.id x))
        (optional-datafy-assoc :invocation-id (.invocationId x))
        (optional-datafy-assoc :author (.author x))
        (optional-datafy-assoc :content (.content x))
        (optional-datafy-assoc :actions (.actions x))
        (optional-datafy-assoc :long-running-tool-ids (.longRunningToolIds x))
        (optional-datafy-assoc :partial (.partial x))
        (optional-datafy-assoc :turn-complete (.turnComplete x))
        (optional-datafy-assoc :error-code (.errorCode x))
        (optional-datafy-assoc :error-message (.errorMessage x))
        (optional-datafy-assoc :finish-reason (.finishReason x))
        (optional-datafy-assoc :usage-metadata (.usageMetadata x))
        (optional-datafy-assoc :interrupted (.interrupted x))
        (optional-datafy-assoc :branch (.branch x))
        (optional-datafy-assoc :grounding-metadata (.groundingMetadata x))
        (optional-datafy-assoc :timestamp (.timestamp x))
        (optional-datafy-assoc :model-version (.modelVersion x))
        (optional-datafy-assoc :usage-metadata (.usageMetadata x))
        (optional-datafy-assoc :avg-logprobs (.avgLogprobs x))
  )))

(extend-protocol Datafiable
  EventActions
  (datafy [^EventActions x]
    (-> {}
        (optional-datafy-assoc :skip-summarization (.skipSummarization x))
        (optional-datafy-assoc :state-delta (.stateDelta x))
        (optional-datafy-assoc :artifact-delta (.artifactDelta x))
        (optional-datafy-assoc :transfer-to-agent (.transferToAgent x))
        (optional-datafy-assoc :escalate (.escalate x))
        (optional-datafy-assoc :request-auth-configs (.requestedAuthConfigs x))
        (optional-datafy-assoc :end-invocation (.endInvocation x)))))

(extend-protocol p/IntoEvent
  IPersistentMap
  (into-event [^IPersistentMap x]
    (let [{:keys [id invocation-id author content actions long-running-tool-ids
                  partial turn-complete error-code error-message interrupted
                  usage-metadata
                  branch grounding-metadata timestamp
                  model-version usage-metadata avg-logprobs finish-reason]} x
          ^Event$Builder b (Event/builder)]
      (when (some? id)
        (.id b id))
      (when (some? invocation-id)
        (.invocationId b invocation-id))
      (when (some? author)
        (.author b author))
      (when (some? content)
        (.content b ^Content (io.kosong.java/make-object Content content)))
      (when (some? actions)
        (.actions b (p/into-event-actions actions)))
      (when (some? long-running-tool-ids)
        (.longRunningToolIds b ^Set (set long-running-tool-ids)))
      (when (some? partial)
        (.partial b ^Boolean partial))
      (when (some? turn-complete)
        (.turnComplete b ^Boolean turn-complete))
      (when (some? error-code)
        (.errorCode b ^FinishReason error-code))
      (when (some? error-message)
        (.errorMessage b ^String error-message))
      (when (some? usage-metadata)
        (.usageMetadata b ^GenerateContentResponseUsageMetadata (io.kosong.java/make-object GenerateContentResponseUsageMetadata  x)))
      (when (some? interrupted)
        (.interrupted b ^Boolean interrupted))
      (when (some? branch)
        (.branch b ^String branch))
      (when (some? grounding-metadata)
        (.groundingMetadata b ^GroundingMetadata (io.kosong.java/make-object GroundingMetadata grounding-metadata)))
      (when (some? timestamp)
        (.timestamp b ^Long timestamp))
      (when (some? model-version)
        (.modelVersion b ^String model-version))
      (when (some? usage-metadata)
        (.usageMetadata b usage-metadata))
      (when (some? avg-logprobs)
        (.avgLogprobs b avg-logprobs))
      (when (some? finish-reason)
        (.finishReason b ^FinishReason finish-reason))
      (.build b))))

(extend-protocol p/IntoEventActions
  IPersistentMap
  (into-event-actions [^IPersistentMap x]
    (let [{:keys [skip-summarization state-delta artifact-delta transfer-to-agent
                  escalate request-auth-configs end-invocation]} x
          b (EventActions/builder)]
      (when (some? skip-summarization)
        (.skipSummarization b skip-summarization))
      (when (some? state-delta)
        (.stateDelta b (ConcurrentHashMap. ^Map state-delta)))
      (when (some? artifact-delta)
        (.artifactDelta b (ConcurrentHashMap. ^Map artifact-delta)))
      (when (some? transfer-to-agent)
        (.transferToAgent b transfer-to-agent))
      (when (some? escalate)
        (.escalate b escalate))
      (when (some? request-auth-configs)
        (.requestedAuthConfigs b (ConcurrentHashMap. ^Map request-auth-configs)))
      (when (some? end-invocation)
        (.endInvocation b end-invocation))
      (.build b))))
