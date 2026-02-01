(ns io.kosong.adk.protocols)

;;
;; com.google.adk.agents
;;

(defprotocol IntoAgent
  (into-agent [x]))

(defprotocol IntoTool
  (into-tool [x]))

(defprotocol IntoBeforeModelCallback
  (into-before-model-callback [x]))

(defprotocol IntoAfterModelCallback
  (into-after-model-callback [x]))

(defprotocol IntoBeforeAgentCallback
  (into-before-agent-callback [x]))

(defprotocol IntoAfterAgentCallback
  (into-after-agent-callback [x]))

(defprotocol IntoBeforeToolCallback
  (into-before-tool-callback [x]))

(defprotocol IntoAfterToolCallback
  (into-after-tool-callback [x]))

(defprotocol IntoInstruction
  (into-instruction [x]))

#_(defprotocol IntoRunConfig
  (into-run-config [x]))

#_(defprotocol IntoOutputAudioTranscriptionConfig
  (into-output-audio-transcription-config [x]))

#_(defprotocol IntoLiveRequest
  (into-live-request [x]))

;;
;; com.google.adk.events
;;
(defprotocol IntoEvent
  (into-event [x]))

(defprotocol IntoEventActions
  (into-event-actions [x]))


;;
;; com.google.genai.types
;;

;(defprotocol IntoPart
;  (into-part [x]))
;
;(defprotocol IntoVideoMetadata
;  (into-video-metadata [x]))
;
;(defprotocol IntoBlob
;  (into-blob [x]))
;
;(defprotocol IntoCodeExecutionResult
;  (into-code-execution-result [x]))
;
;(defprotocol IntoExecutableCode
;  (into-executable-code [x]))
;
;(defprotocol IntoOutcome
;  (into-outcome [x]))
;
;(defprotocol IntoFileData
;  (into-file-data [x]))
;
;(defprotocol IntoFunctionCall
;  (into-function-call [x]))
;
;(defprotocol IntoFunctionResponse
;  (into-function-response [x]))
;
;(defprotocol IntoContent
;  (into-content [x]))
;
;(defprotocol IntoSchema
;  (into-schema [x]))
;
;(defprotocol IntoGroundingMetadata
;  (into-grounding-metadata [x]))
;
;(defprotocol IntoGroundingChunk
;  (into-grounding-chunk [x]))
;
;(defprotocol IntoGroundingSupport
;  (into-grounding-support [x]))
;
;(defprotocol IntoRetrievalMetadata
;  (into-retrieval-metadata [x]))
;
;(defprotocol IntoSearchEntryPoint
;  (into-search-entry-point [x]))
;
;(defprotocol IntoGroundingChunkRetrievedContext
;  (into-grounding-chunk-retrieval-context [x]))
;
;(defprotocol IntoGroundingChunkWeb
;  (into-grounding-chunk-web [x]))
;
;(defprotocol IntoSegment
;  (into-segment [x]))
;
;(defprotocol IntoFinishReason
;  (into-finish-reason [x]))
;
;(defprotocol IntoHttpOptions
;  (into-http-options [x]))
;
;(defprotocol IntoGenerateContentConfig
;  (into-generate-content-config [x]))
;
;(defprotocol IntoGenerateContentResponseUsageMetadata
;  (into-generate-content-response-usage-metadata [x]))
;
;(defprotocol IntoModalityTokenCount
;  (into-modality-token-count [x]))
;
;(defprotocol IntoGenerationConfigRoutingConfig
;  (into-generation-config-routing-config [x]))
;
;(defprotocol IntoGenerationConfigRoutingConfigAutoRoutingMode
;  (into-generation-config-routing-config-auto-routing-mode [x]))
;
;(defprotocol IntoGenerationConfigRoutingConfigManualRoutingMode
;  (into-generation-config-routing-config-manual-routing-mode [x]))
;
;(defprotocol IntoModelSelectionConfig
;  (into-model-selection-config [x]))
;
;(defprotocol IntoSafetySetting
;  (into-safety-setting [x]))
;
;(defprotocol IntoGenaiTool
;  (into-genai-tool [x]))
;
;(defprotocol IntoToolConfig
;  (into-tool-config [x]))
;
;(defprotocol IntoSpeechConfig
;  (into-speech-config [x]))
;
;(defprotocol IntoVoiceConfig
;  (into-voice-config [x]))
;
;(defprotocol IntoReplicatedVoiceConfig
;  (into-replicated-voice-config [x]))
;
;(defprotocol IntoPrebuiltVoiceConfig
;  (into-prebuilt-voice-config [x]))
;
;(defprotocol IntoMultiSpeakerVoiceConfig
;  (into-multi-speaker-voice-config [x]))
;
;(defprotocol IntoAutomaticFunctionCallingConfig
;  (into-automatic-function-calling-config [x]))
;
;(defprotocol IntoThinkingConfig
;  (into-thinking-config [x]))
;
;(defprotocol IntoImageConfig
;  (into-image-config [x]))

;;
;; com.google.adk.models
;;

;(defprotocol IntoLlmRequest
;  (into-llm-request [x]))
;
;(defprotocol IntoLlmResponse
;  (into-llm-response [x]))

