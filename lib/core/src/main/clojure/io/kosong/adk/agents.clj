(ns io.kosong.adk.agents
  (:require [clojure.core.protocols :refer [Datafiable]]
            [clojure.datafy :refer [datafy]]
            [io.kosong.adk.protocols :as p]
            [io.kosong.adk.utils :refer [optional-datafy-assoc]])
  (:import (clojure.lang IFn IPersistentMap)
           (com.google.adk.agents
             BaseAgent CallbackContext Callbacks$AfterAgentCallback Callbacks$AfterAgentCallbackBase Callbacks$AfterModelCallback Callbacks$AfterModelCallbackBase
             Callbacks$AfterToolCallback Callbacks$AfterToolCallbackBase Callbacks$BeforeAgentCallback Callbacks$BeforeAgentCallbackBase Callbacks$BeforeModelCallback Callbacks$BeforeModelCallbackBase
             Callbacks$BeforeToolCallback Callbacks$BeforeToolCallbackBase Instruction Instruction$Provider Instruction$Static InvocationContext ReadonlyContext
             RunConfig RunConfig$Builder)
           (com.google.adk.models LlmRequest LlmRequest$Builder LlmResponse)
           (com.google.adk.tools BaseTool ToolContext)
           (com.google.genai.types AudioTranscriptionConfig)
           (io.reactivex.rxjava3.core Maybe Single)
           (java.util Map)))


(extend-protocol Datafiable
  InvocationContext
  (datafy [^InvocationContext x]
    (-> {}
        (optional-datafy-assoc :artifact-service (.artifactService x))
        (optional-datafy-assoc :live-request-queue (.liveRequestQueue x))
        (optional-datafy-assoc :invocation-id (.invocationId x))
        (optional-datafy-assoc :branch (.branch x))
        (optional-datafy-assoc :agent (.agent x))
        (optional-datafy-assoc :session (.session x))
        (optional-datafy-assoc :user-content (.userContent x))
        (optional-datafy-assoc :run-config (.runConfig x))
        (optional-datafy-assoc :end-invocation (.endInvocation x))
        (optional-datafy-assoc :app-name (.appName x))
        (optional-datafy-assoc :user-id (.userId x)))))

(extend-protocol Datafiable
  ReadonlyContext
  (datafy [^ReadonlyContext x]
    (-> {}
        (optional-datafy-assoc :invocation-id (.invocationId x))
        (optional-datafy-assoc :branch (.branch x))
        (optional-datafy-assoc :agent-name (.agentName x))
        (optional-datafy-assoc :state (.state x)))))

(extend-protocol Datafiable
  CallbackContext
  (datafy [^CallbackContext x]
    (-> {}
        (optional-datafy-assoc :invocation-id (.invocationId x))
        (optional-datafy-assoc :branch (.branch x))
        (optional-datafy-assoc :agent-name (.agentName x))
        (optional-datafy-assoc :state (.state x))
        (optional-datafy-assoc :user-content (.userContent x))
        (optional-datafy-assoc :event-actions (.eventActions x)))))

(extend-protocol p/IntoBeforeModelCallback
  IFn
  (into-before-model-callback [f]
    (reify Callbacks$BeforeModelCallback
      (^Maybe call [_ ^CallbackContext context ^LlmRequest$Builder llm-request-builder]
        (try
          (let [context-m      (datafy context)
                llm-response-m (f context-m llm-request-builder)]
            (if llm-response-m
              (Maybe/just (p/into-llm-response llm-response-m))
              (Maybe/empty)))
          (catch Throwable t
            (Maybe/error ^Throwable t)))))))

(extend-protocol p/IntoBeforeModelCallback
  Callbacks$BeforeModelCallbackBase
  (into-before-model-callback [x]
    x))

(extend-protocol p/IntoAfterModelCallback
  IFn
  (into-after-model-callback [f]
    (reify Callbacks$AfterModelCallback
      (^Maybe call [_ ^CallbackContext context ^LlmResponse llm-response]
        (try
          (let [llm-response-m0 (datafy llm-response)
                context-m       (datafy context)
                llm-response-m1 (f context-m llm-response-m0)]
            (if llm-response-m1
              (Maybe/just (p/into-llm-response llm-response-m1))
              (Maybe/empty)))
          (catch Throwable t
            (Maybe/error ^Throwable t)))))))

(extend-protocol p/IntoAfterModelCallback
  Callbacks$AfterModelCallbackBase
  (into-after-model-callback [x]
    x))

(extend-protocol p/IntoBeforeAgentCallback
  IFn
  (into-before-agent-callback [f]
    (reify Callbacks$BeforeAgentCallback
      (^Maybe call [_ ^CallbackContext context]
        (try
          (if-let [r (f (datafy context))]
            (Maybe/just (p/into-content r))
            (Maybe/empty))
          (catch Throwable t
            (Maybe/error ^Throwable t)))))))

(extend-protocol p/IntoBeforeAgentCallback
  Callbacks$BeforeAgentCallbackBase
  (into-before-agent-callback [x]
    x))

(extend-protocol p/IntoAfterAgentCallback
  IFn
  (into-after-agent-callback [f]
    (reify Callbacks$AfterAgentCallback
      (^Maybe call [_ ^CallbackContext context]
        (try
          (if-let [r (f (datafy context))]
            (Maybe/just (p/into-content r))
            (Maybe/empty))
          (catch Throwable t
            (Maybe/error ^Throwable t)))))))

(extend-protocol p/IntoAfterAgentCallback
  Callbacks$AfterAgentCallbackBase
  (into-after-agent-callback [x]
    x))

(extend-protocol p/IntoBeforeToolCallback
  IFn
  (into-before-tool-callback [f]
    (reify Callbacks$BeforeToolCallback
      (^Maybe call [_ ^InvocationContext invocation-context
                    ^BaseTool tool
                    ^Map input
                    ^ToolContext tool-context]
        (try
          (let [invocation-context-m (datafy invocation-context)
                tool-context-m       (datafy tool-context)
                r                    (f invocation-context-m tool input tool-context-m)]
            (if r
              (Maybe/just (clojure.walk/stringify-keys r))
              (Maybe/empty)))
          (catch Throwable t
            (Maybe/error ^Throwable t)))))))

(extend-protocol p/IntoBeforeToolCallback
  Callbacks$BeforeToolCallbackBase
  (into-before-tool-callback [x]
    x))

(extend-protocol p/IntoAfterToolCallback
  IFn
  (into-after-tool-callback [f]
    (reify Callbacks$AfterToolCallback
      (^Maybe call [_ ^InvocationContext invocation-context
                    ^BaseTool tool
                    ^Map input
                    ^ToolContext tool-context
                    ^Object response]
        (try
          (let [invocation-context-m (datafy invocation-context)
                tool-context-m       (datafy tool-context)
                r                    (f invocation-context-m tool input tool-context-m response)]
            (if r
              (Maybe/just (clojure.walk/stringify-keys r))
              (Maybe/empty)))
          (catch Throwable t
            (Maybe/error ^Throwable t)))))))

(extend-protocol p/IntoAfterToolCallback
  Callbacks$AfterToolCallbackBase
  (into-after-tool-callback [x]
    x))

(extend-protocol p/IntoRunConfig
  IPersistentMap
  (into-run-config [x]
    (let [{:keys [streaming-mode max-llm-calls response-modalities
                  save-input-blobs-as-artifact output-audio-transcription]} x
          ^RunConfig$Builder b (RunConfig/builder)]
      (when (some? streaming-mode)
        (.setStreamingMode b streaming-mode))
      (when (some? max-llm-calls)
        (.setMaxLlmCalls b max-llm-calls))
      (when (some? response-modalities)
        (.setResponseModalities response-modalities))
      (when (some? save-input-blobs-as-artifact)
        (.setSaveInputBlobsAsArtifacts save-input-blobs-as-artifact))
      (when (some? output-audio-transcription)
        (.setOutputAudioTranscription (p/into-output-audio-transcription-config output-audio-transcription)))
      (.build b))))

(extend-protocol p/IntoRunConfig
  RunConfig
  (into-run-config [x]
    x))

(extend-protocol p/IntoOutputAudioTranscriptionConfig
  IPersistentMap
  (into-output-audio-transcription-config [x]
    (let [b (AudioTranscriptionConfig/builder)]
      (.build b))))

(extend-protocol p/IntoInstruction
  String
  (into-instruction [x]
    (Instruction$Static. x)))

(extend-protocol p/IntoInstruction
  IFn
  (into-instruction [f]
    (let [get-instruction-fn (reify java.util.function.Function
                               (apply [_  context]
                                 (Single/just (f (datafy context)))))]
      (Instruction$Provider. get-instruction-fn))))

(extend-protocol p/IntoInstruction
  Instruction
  (into-instruction [x]
    x))

(extend-protocol p/IntoAgent
  BaseAgent
  (into-agent [x]
    x))