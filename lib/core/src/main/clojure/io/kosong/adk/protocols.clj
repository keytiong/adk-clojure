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

(defprotocol IntoRunConfig
  (into-run-config [x]))

(defprotocol IntoOutputAudioTranscriptionConfig
  (into-output-audio-transcription-config [x]))


;;
;; com.google.genai.types
;;

(defprotocol IntoPart
  (into-part [x]))

(defprotocol IntoVideoMetadata
  (into-video-metadata [x]))

(defprotocol IntoBlob
  (into-blob [x]))

(defprotocol IntoCodeExecutionResult
  (into-code-execution-result [x]))

(defprotocol IntoExecutableCode
  (into-executable-code [x]))

(defprotocol IntoOutcome
  (into-outcome [x]))

(defprotocol IntoFileData
  (into-file-data [x]))

(defprotocol IntoFunctionCall
  (into-function-call [x]))

(defprotocol IntoFunctionResponse
  (into-function-response [x]))

(defprotocol IntoContent
  (into-content [x]))

(defprotocol IntoSchema
  (into-schema [x]))

(defprotocol IntoGroundingMetadata
  (into-grounding-metadata [x]))

(defprotocol IntoGroundingChunk
  (into-grounding-chunk [x]))

(defprotocol IntoGroundingSupport
  (into-grounding-support [x]))

(defprotocol IntoRetrievalMetadata
  (into-retrieval-metadata [x]))

(defprotocol IntoSearchEntryPoint
  (into-search-entry-point [x]))

(defprotocol IntoGroundingChunkRetrievedContext
  (into-grounding-chunk-retrieval-context [x]))

(defprotocol IntoGroundingChunkWeb
  (into-grounding-chunk-web [x]))

(defprotocol IntoSegment
  (into-segment [x]))

(defprotocol IntoFinishReason
  (into-finish-reason [x]))

(defprotocol IntoHttpOptions
  (into-http-options [x]))

;;
;; com.google.adk.models
;;

(defprotocol IntoLlmRequest
  (into-llm-request [x]))

(defprotocol IntoLlmResponse
  (into-llm-response [x]))


;;
;; com.google.adk.events
;;
(defprotocol IntoEvent
  (into-event [x]))

(defprotocol IntoEventActions
  (into-event-actions [x]))