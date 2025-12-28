(ns io.kosong.adk.types
  (:require [clojure.core.protocols :refer [Datafiable]]
            [io.kosong.adk.protocols :as p]
            [io.kosong.adk.utils :refer [optional-datafy-assoc]])
  (:import (clojure.lang IPersistentMap)
           (com.google.genai.types AutomaticFunctionCallingConfig AutomaticFunctionCallingConfig$Builder Content FunctionCall FunctionResponse GenerateContentConfig GenerateContentConfig$Builder GenerateContentResponseUsageMetadata GenerateContentResponseUsageMetadata$Builder GenerationConfigRoutingConfig GenerationConfigRoutingConfig$Builder GenerationConfigRoutingConfigAutoRoutingMode GenerationConfigRoutingConfigAutoRoutingMode$Builder GenerationConfigRoutingConfigManualRoutingMode GenerationConfigRoutingConfigManualRoutingMode$Builder GroundingChunk GroundingChunkRetrievedContext
                                   GroundingChunkWeb GroundingMetadata GroundingSupport HttpOptions ImageConfig ImageConfig$Builder ModalityTokenCount ModalityTokenCount$Builder ModelSelectionConfig ModelSelectionConfig$Builder MultiSpeakerVoiceConfig Part
                                   Blob PrebuiltVoiceConfig PrebuiltVoiceConfig$Builder ReplicatedVoiceConfig ReplicatedVoiceConfig$Builder RetrievalMetadata SafetySetting SafetySetting$Builder Schema SearchEntryPoint Segment SpeakerVoiceConfig$Builder SpeechConfig SpeechConfig$Builder ThinkingConfig ThinkingConfig$Builder ToolConfig VoiceConfig VoiceConfig$Builder)
           (com.google.adk.agents LiveRequest LiveRequestQueue)
           (java.util Base64 List)))


(extend-protocol Datafiable
  Content
  (datafy [^Content x]
    (-> {}
        (optional-datafy-assoc :parts (.parts x))
        (optional-datafy-assoc :role (.role x)))))

(extend-protocol Datafiable
  Part
  (datafy [^Part x]
    (-> {}
        (optional-datafy-assoc :video-metadata (.videoMetadata x))
        (optional-datafy-assoc :thought (.thought x))
        (optional-datafy-assoc :inline-data (.inlineData x))
        (optional-datafy-assoc :code-execution-result (.codeExecutionResult x))
        (optional-datafy-assoc :executable-code (.executableCode x))
        (optional-datafy-assoc :file-data (.fileData x))
        (optional-datafy-assoc :function-call (.functionCall x))
        (optional-datafy-assoc :function-response (.functionResponse x))
        (optional-datafy-assoc :text (.text x)))))

(extend-protocol Datafiable
  Blob
  (datafy [^Blob x]
    (-> {}
        (optional-datafy-assoc :display-name (.displayName x))
        (optional-datafy-assoc :mime-type (.mimeType x))
        (optional-datafy-assoc :data (when (.isPresent (.data x))
                                       (.encodeToString (Base64/getEncoder) (.get (.data x))))))))

(extend-protocol Datafiable
  FunctionCall
  (datafy [^FunctionCall x]
    (-> {}
        (optional-datafy-assoc :id (.id x))
        (optional-datafy-assoc :args (.args x))
        (optional-datafy-assoc :name (.name x)))))

(extend-protocol Datafiable
  FunctionResponse
  (datafy [^FunctionResponse x]
    (-> {}
        (optional-datafy-assoc :will-continue (.willContinue x))
        (optional-datafy-assoc :scheduling (.scheduling x))
        (optional-datafy-assoc :id (.id x))
        (optional-datafy-assoc :name (.name x))
        (optional-datafy-assoc :response (.response x)))))

(extend-protocol Datafiable
  GroundingMetadata
  (datafy [^GroundingMetadata x]
    (-> {}
        (optional-datafy-assoc :grounding-chunks (.groundingChunks x))
        (optional-datafy-assoc :grounding-support (.groundingSupports x))
        (optional-datafy-assoc :retrieval-metadata (.retrievalMetadata x))
        (optional-datafy-assoc :retrieval-queries (.retrievalQueries x))
        (optional-datafy-assoc :search-entry-point (.searchEntryPoint x))
        (optional-datafy-assoc :web-search-queries (.webSearchQueries x)))))

(extend-protocol Datafiable
  GroundingChunk
  (datafy [^GroundingChunk x]
    (-> {}
        (optional-datafy-assoc :retrieved-context (.retrievedContext x))
        (optional-datafy-assoc :web (.web x)))))

(extend-protocol Datafiable
  GroundingChunkRetrievedContext
  (datafy [^GroundingChunkRetrievedContext x]
    (-> {}
        (optional-datafy-assoc :text (.text x))
        (optional-datafy-assoc :title (.title x))
        (optional-datafy-assoc :uri (.uri x)))))

(extend-protocol Datafiable
  GroundingChunkWeb
  (datafy [^GroundingChunkWeb x]
    (-> {}
        (optional-datafy-assoc :domain (.domain x))
        (optional-datafy-assoc :title (.title x))
        (optional-datafy-assoc :uri (.uri x)))))

(extend-protocol Datafiable
  GroundingSupport
  (datafy [^GroundingSupport x]
    (-> {}
        (optional-datafy-assoc :confidence-scores (.confidenceScores x))
        (optional-datafy-assoc :grounding-chunk-indices (.groundingChunkIndices x))
        (optional-datafy-assoc :segment (.segment x)))))

(extend-protocol Datafiable
  RetrievalMetadata
  (datafy [^RetrievalMetadata x]
    (-> {}
        (optional-datafy-assoc :google-search-dynamic-retrieval-score (.googleSearchDynamicRetrievalScore x)))))

(extend-protocol Datafiable
  Segment
  (datafy [^Segment x]
    (-> {}
        (optional-datafy-assoc :end-index (.endIndex x))
        (optional-datafy-assoc :part-index (.partIndex x))
        (optional-datafy-assoc :start-index (.startIndex x))
        (optional-datafy-assoc :text (.text x)))))

(extend-protocol Datafiable
  SearchEntryPoint
  (datafy [^SearchEntryPoint x]
    (-> {}
        (optional-datafy-assoc :rendered-context (.renderedContent x))
        (optional-datafy-assoc :sdk-blob (.sdkBlob x)))))

(extend-protocol Datafiable
  GenerateContentConfig
  (datafy [^GenerateContentConfig x]
    (-> {}
        (optional-datafy-assoc :http-options (.httpOptions x))
        (optional-datafy-assoc :system-instruction (.systemInstruction x))
        (optional-datafy-assoc :temperature (.temperature x))
        (optional-datafy-assoc :top-p (.topP x))
        (optional-datafy-assoc :top-k (.topK x))
        (optional-datafy-assoc :candidate-count (.candidateCount x))
        (optional-datafy-assoc :max-output-token (.maxOutputTokens x))
        (optional-datafy-assoc :stop-sequences (.stopSequences x))
        (optional-datafy-assoc :response-logprobs (.responseLogprobs x))
        (optional-datafy-assoc :presence-penalty (.presencePenalty x))
        (optional-datafy-assoc :frequency-penalty (.frequencyPenalty x))
        (optional-datafy-assoc :seed (.seed x))
        (optional-datafy-assoc :response-mime-type (.responseMimeType x))
        (optional-datafy-assoc :response-schema (.responseSchema x))
        (optional-datafy-assoc :routing-config (.routingConfig x)) ;; todo
        (optional-datafy-assoc :model-selection-config (.modelSelectionConfig x)) ;; todo
        (optional-datafy-assoc :safety-settings (.safetySettings x)) ;; todo
        (optional-datafy-assoc :tools (.tools x))           ;; todo
        (optional-datafy-assoc :tool-config (.toolConfig x)) ;; todo
        (optional-datafy-assoc :labels (.labels x))
        (optional-datafy-assoc :cached-content (.cachedContent x))
        (optional-datafy-assoc :response-modalities (.responseModalities x))
        (optional-datafy-assoc :media-resolution (.mediaResolution x)) ;;todo
        (optional-datafy-assoc :speech-config (.speechConfig x)) ;;todo
        (optional-datafy-assoc :audio-timestamp (.audioTimestamp x))
        (optional-datafy-assoc :automatic-function-calling (.automaticFunctionCalling x)) ;; todo
        (optional-datafy-assoc :thinking-config (.thinkingConfig x)) ;; todo
        )))

(extend-protocol Datafiable
  HttpOptions
  (datafy [^HttpOptions x]
    (-> {}
        (optional-datafy-assoc :base-url (.baseUrl x))
        (optional-datafy-assoc :api-version (.apiVersion x))
        (optional-datafy-assoc :headers (.headers x))
        (optional-datafy-assoc :timeout (.timeout x)))))

(extend-protocol Datafiable
  GenerateContentResponseUsageMetadata
  (datafy [^GenerateContentResponseUsageMetadata x]
    (-> {}
        (optional-datafy-assoc :cache-tokens-details (.cacheTokensDetails x))
        (optional-datafy-assoc :cached-content-token-count (.cachedContentTokenCount x))
        (optional-datafy-assoc :candidates-token-count (.candidatesTokenCount x))
        (optional-datafy-assoc :candidates-tokens-details (.candidatesTokensDetails x))
        (optional-datafy-assoc :prompt-token-count (.promptTokenCount x))
        (optional-datafy-assoc :prompt-tokens-details (.promptTokensDetails x))
        (optional-datafy-assoc :thoughts-token-count (.thoughtsTokenCount x))
        (optional-datafy-assoc :tool-use-prompt-token-count (.toolUsePromptTokenCount x))
        (optional-datafy-assoc :tool-use-prompt-tokens-details (.toolUsePromptTokensDetails x))
        (optional-datafy-assoc :total-token-count (.totalTokenCount x))
        (optional-datafy-assoc :traffic-type (.trafficType x)))))

(extend-protocol Datafiable
  ModalityTokenCount
  (datafy [^ModalityTokenCount x]
    (-> {}
        (optional-datafy-assoc :modality (.modality x))
        (optional-datafy-assoc :token-count (.tokenCount x)))))

(extend-protocol p/IntoPart
  IPersistentMap
  (into-part [^IPersistentMap x]
    (let [{:keys [video-metadata thought inline-data code-execution-result
                  executable-code file-data function-call function-response
                  text]} x
          b (Part/builder)]
      (when (some? video-metadata)
        (.videoMetadata b (p/into-video-metadata video-metadata)))
      (when (some? thought)
        (.thought b thought))
      (when (some? inline-data)
        (.inlineData b (p/into-blob inline-data)))
      (when (some? code-execution-result)
        (.codeExecutionResult b (p/into-code-execution-result code-execution-result)))
      (when (some? executable-code)
        (.executableCode b (p/into-executable-code executable-code)))
      (when (some? file-data)
        (.fileData (p/into-file-data file-data)))
      (when (some? function-call)
        (.functionCall (p/into-function-call function-call)))
      (when (some? function-response)
        (.functionResponse (p/into-function-response function-response)))
      (when (some? text)
        (.text b text))
      (.build b))))

(extend-protocol p/IntoContent
  IPersistentMap
  (into-content [^IPersistentMap x]
    (let [{:keys [role parts]} x
          b (Content/builder)]
      (when (some? role)
        (.role b role))
      (when (some? parts)
        (.parts b (mapv p/into-part parts)))
      (.build b))))

(extend-protocol p/IntoContent
  String
  (into-content [^String x]
    (p/into-content {:role  "user"
                     :parts [{:text x}]})))

(extend-protocol p/IntoSchema
  IPersistentMap
  (into-schema [^IPersistentMap x]
    (let [{:keys [any-of default description enum example format items
                  max-items max-length max-properties maximum
                  min-items, min-length, min-properties minimum
                  nullable pattern properties property-ordering
                  required title type]} x
          b (com.google.genai.types.Schema/builder)]
      (when (some? any-of)
        (.anyOf b (mapv p/into-schema any-of)))
      (when (some? default)
        (.default b default))
      (when (some? description)
        (.description b description))
      (when (some? enum)
        (.enum b enum))
      (when (some? example)
        (.example b example))
      (when (some? format)
        (.format b format))
      (when (some? items)
        (.items b (p/into-schema items)))
      (when (some? max-items)
        (.maxItems b max-items))
      (when (some? max-length)
        (.maxLength b max-length))
      (when (some? max-properties)
        (.maxProperties b max-properties) b)
      (when (some? maximum)
        (.maximum b maximum))
      (when (some? min-items)
        (.minItems b min-items))
      (when (some? min-length)
        (.minLength b min-length))
      (when (some? min-properties)
        (.minProperties b min-properties))
      (when (some? minimum)
        (.minimum b minimum))
      (when (some? nullable)
        (.nullable b nullable))
      (when (some? pattern)
        (.pattern b pattern))
      (when (some? properties)
        (.properties b (reduce (fn [a [k v]] (assoc a (name k) (p/into-schema v))) {} properties)))
      (when (some? property-ordering)
        (.propertyOrdering b property-ordering))
      (when (some? required)
        (.required b required))
      (when (some? title)
        (.title b title))
      (when (some? type)
        (.type b ^String type))
      (.build b))))

(extend-protocol p/IntoBlob
  IPersistentMap
  (into-blob [x]
    (let [{:keys [display-name data mime-type]} x
          b (Blob/builder)]
      (when (some? display-name)
        (.displayName b display-name))
      (when (some? data)
        (cond (string? data)
              (.data b (.decode (Base64/getDecoder) ^String data))
              (instance? (Class/forName "[B") data)
              (.data b ^bytes data)))
      (when (some? mime-type)
        (.mimeType b mime-type))
      (.build b))))

(extend-protocol p/IntoHttpOptions
  IPersistentMap
  (into-http-options [{:keys [base-url api-version headers timeout]}]
    (let [b (HttpOptions/builder)]
      (when (some? base-url)
        (.baseUrl b base-url))
      (when (some? api-version)
        (.apiVersion b api-version))
      (when (some? headers)
        (.headers b headers))
      (when (some? timeout)
        (.timeout b timeout))
      (.build b))))

(extend-protocol p/IntoHttpOptions
  HttpOptions
  (into-http-options [x]
    x))

;;
;; LiveRequest
;;

(extend-protocol Datafiable
  LiveRequest
  (datafy [^LiveRequest x]
    (-> {}
        (optional-datafy-assoc :content (.content x))
        (optional-datafy-assoc :blob (.blob x))
        (optional-datafy-assoc :close (.close x)))))

(extend-protocol p/IntoLiveRequest
  IPersistentMap
  (into-live-request [x]
    (let [{:keys [content blob close]} x
          b (LiveRequest/builder)]
      (when (some? content)
        (.content b (p/into-content content)))
      (when (some? blob)
        (.blob b (p/into-blob blob)))
      (when (some? close)
        (.close b close))
      (.build b))))

(extend-protocol p/IntoLiveRequest
  LiveRequest
  (into-live-request [x]
    x))

(extend-protocol p/IntoGenerateContentConfig
  GenerateContentConfig
  (into-generate-content-config [x]
    x))

(extend-protocol p/IntoGenerateContentConfig
  IPersistentMap
  (into-generate-content-config [x]
    (let [{:keys [http-options
                  should-return-http-response
                  system-instruction
                  temperature
                  top-p
                  top-k
                  candidate-count
                  max-output-tokens
                  stop-sequences
                  response-logprobs
                  logprobs
                  presence-penalty
                  frequency-penalty
                  seed
                  response-mime-type
                  response-schema
                  response-json-schema
                  routing-config
                  model-selection-config
                  safety-settings
                  tools
                  tool-config
                  labels
                  cached-content
                  response-modalities
                  media-resolution
                  speech-config
                  audio-timestamp
                  automatic-function-calling
                  thinking-config
                  image-config
                  enable-enhanced-civic-answers]} x
          ^GenerateContentConfig$Builder b (GenerateContentConfig/builder)]
      (when (some? http-options)
        (.httpOptions b ^HttpOptions (p/into-http-options http-options)))
      (when (some? should-return-http-response)
        (.shouldReturnHttpResponse b should-return-http-response))
      (when (some? system-instruction)
        (.systemInstruction b ^Content (p/into-content system-instruction)))
      (when (some? temperature)
        (.temperature b (float temperature)))
      (when (some? top-p)
        (.topP b (float top-p)))
      (when (some? top-k)
        (.topK b (float top-k)))
      (when (some? candidate-count)
        (.candidateCount b (int candidate-count)))
      (when (some? max-output-tokens)
        (.maxOutputTokens b (int candidate-count)))
      (when (some? stop-sequences)
        (.stopSequences b ^List stop-sequences))
      (when (some? response-logprobs)
        (.responseLogprobs b response-logprobs))
      (when (some? logprobs)
        (.logprobs b (int logprobs)))
      (when (some? presence-penalty)
        (.presencePenalty b (float presence-penalty)))
      (when (some? frequency-penalty)
        (.frequencyPenalty b (float frequency-penalty)))
      (when (some? seed)
        (.seed b (int seed)))
      (when (some? response-mime-type)
        (.responseMimeType b response-mime-type))
      (when (some? response-schema)
        (.responseSchema b ^Schema (p/into-schema response-schema)))
      (when (some? response-json-schema)
        ;; TODO clojure to json schema object conversion
        (.responseJsonSchema b response-json-schema))
      (when (some? routing-config)
        (.routingConfig b ^GenerationConfigRoutingConfig (p/into-generation-config-routing-config routing-config)))
      (when (some? model-selection-config)
        (.modelSelectionConfig b ^ModelSelectionConfig (p/into-model-selection-config model-selection-config)))
      (when (some? safety-settings)
        (.safetySettings b ^List (mapv p/into-safety-setting safety-settings)))
      (when (some? tools)
        ;; TODO
        (.tools b ^List (mapv p/into-genai-tool tools)))
      (when (some? tool-config)
        ;; TODO
        (.toolConfig b ^ToolConfig (p/into-tool-config tool-config)))
      (when (some? labels)
        (.labels b labels))
      (when (some? cached-content)
        (.cachedContent b cached-content))
      (when (some? response-modalities)
        (.responseModalities b ^List response-modalities))
      (when (some? media-resolution)
        (.mediaResolution b ^String media-resolution))
      (when (some? speech-config)
        (.speechConfig b ^SpeechConfig (p/into-speech-config speech-config)))
      (when (some? audio-timestamp)
        (.audioTimestamp b audio-timestamp))
      (when (some? automatic-function-calling)
        (.automaticFunctionCalling b ^AutomaticFunctionCallingConfig (p/into-automatic-function-calling-config automatic-function-calling)))
      (when (some? thinking-config)
        (.thinkingConfig b ^ThinkingConfig (p/into-thinking-config thinking-config)))
      (when (some? image-config)
        (.imageConfig b ^ImageConfig (p/into-image-config image-config)))
      (when (some? enable-enhanced-civic-answers)
        (.enableEnhancedCivicAnswers b enable-enhanced-civic-answers))
      (.build b))))

(extend-protocol p/IntoGenerateContentResponseUsageMetadata
  GenerateContentResponseUsageMetadata
  (into-generate-content-response-usage-metadata [x]
    x))

(extend-protocol p/IntoGenerateContentResponseUsageMetadata
  IPersistentMap
  (into-generate-content-response-usage-metadata [x]
    (let [{:keys [cache-tokens-details
                  cached-content-token-count
                  candidates-token-count
                  candidates-tokens-details
                  prompt-token-count
                  prompt-tokens-details
                  thoughts-token-count
                  tool-use-prompt-token-count
                  tool-use-prompt-tokens-details
                  total-token-count
                  traffic-type
                  ]} x
          ^GenerateContentResponseUsageMetadata$Builder b (GenerateContentResponseUsageMetadata/builder)]
      (when (some? cache-tokens-details)
        (.cacheTokensDetails b ^List (mapv p/into-modality-token-count cache-tokens-details)))
      (when (some? cached-content-token-count)
        (.cachedContentTokenCount b cached-content-token-count))
      (when (some? candidates-token-count)
        (.candidatesTokenCount b candidates-token-count))
      (when (some? candidates-tokens-details)
        (.candidatesTokensDetails b ^List (mapv p/into-modality-token-count candidates-tokens-details)))
      (when (some? prompt-token-count)
        (.promptTokenCount b prompt-token-count))
      (when (some? prompt-tokens-details)
        (.promptTokensDetails b ^List (mapv p/into-modality-token-count prompt-tokens-details)))
      (when (some? thoughts-token-count)
        (.thoughtsTokenCount b thoughts-token-count))
      (when (some? tool-use-prompt-token-count)
        (.toolUsePromptTokenCount b tool-use-prompt-token-count))
      (when (some? tool-use-prompt-tokens-details)
        (.toolUsePromptTokensDetails b ^List (mapv p/into-modality-token-count tool-use-prompt-tokens-details)))
      (when (some? total-token-count)
        (.totalTokenCount b total-token-count))
      (when (some? traffic-type)
        (.trafficType b ^String traffic-type))
      (.build b))))

(extend-protocol p/IntoGenerationConfigRoutingConfig
  GenerationConfigRoutingConfig
  (into-generation-config-routing-config [x]
    x))

(extend-protocol p/IntoGenerationConfigRoutingConfig
  IPersistentMap
  (into-generation-config-routing-config [x]
    (let [{:keys [auto-mode manual-mode]} x
          ^GenerationConfigRoutingConfig$Builder b (GenerationConfigRoutingConfig/builder)]
      (when (some? auto-mode)
        (.autoMode b ^GenerationConfigRoutingConfigAutoRoutingMode  (p/into-generation-config-routing-config-auto-routing-mode auto-mode)))
      (when (some? manual-mode)
        (.manualMode b ^GenerationConfigRoutingConfigManualRoutingMode  (p/into-generation-config-routing-config-manual-routing-mode manual-mode)))
      (.build b))))

(extend-protocol p/IntoGenerationConfigRoutingConfigAutoRoutingMode
  GenerationConfigRoutingConfigAutoRoutingMode
  (into-generation-config-routing-config-auto-routing-mode [x]
    x))

(extend-protocol p/IntoGenerationConfigRoutingConfigAutoRoutingMode
  IPersistentMap
  (into-generation-config-routing-config-auto-routing-mode [x]
    (let [{:keys [model-routing-preference]} x
          ^GenerationConfigRoutingConfigAutoRoutingMode$Builder b (GenerationConfigRoutingConfigAutoRoutingMode/builder)]
      (when (some? model-routing-preference)
        (.modelRoutingPreference b ^String model-routing-preference))
      (.build b))))

(extend-protocol p/IntoGenerationConfigRoutingConfigManualRoutingMode
  GenerationConfigRoutingConfigManualRoutingMode
  (into-generation-config-routing-config-manual-routing-mode [x]
    x))

(extend-protocol p/IntoGenerationConfigRoutingConfigManualRoutingMode
  IPersistentMap
  (into-generation-config-routing-config-manual-routing-mode [x]
    (let [{:keys [model-name]} x
          ^GenerationConfigRoutingConfigManualRoutingMode$Builder b (GenerationConfigRoutingConfigManualRoutingMode/builder)]
      (when (some? model-name)
        (.modelName b model-name))
      (.build b))))

(extend-protocol p/IntoModelSelectionConfig
  ModelSelectionConfig
  (into-model-selection-config [x]
    x))

(extend-protocol p/IntoModelSelectionConfig
  IPersistentMap
  (into-model-selection-config [x]
    (let [{:keys [feature-selection-preference]} x
          ^ModelSelectionConfig$Builder b (ModelSelectionConfig/builder)]
      (when (some? feature-selection-preference)
        (.featureSelectionPreference b ^String feature-selection-preference))
      (.build b))))

(extend-protocol p/IntoSafetySetting
  SafetySetting
  (into-safety-setting [x]
    x))

(extend-protocol p/IntoSafetySetting

  IPersistentMap
  (into-safety-setting [x]
    (let [{:keys [category method threshold]} x
          ^SafetySetting$Builder b (SafetySetting/builder)]
      (when (some? category)
        (.category b ^String category))
      (when (some? method)
        (.method b ^String method))
      (when (some? threshold)
        (.threshold b ^String threshold))
      (.build b))))

(extend-protocol p/IntoSpeechConfig
  SpeechConfig
  (into-speech-config [x]
    x))

(extend-protocol p/IntoSpeechConfig
  IPersistentMap
  (into-speech-config [x]
    (let [{:keys [voice-config language-code multi-speaker-voice-config]} x
          ^SpeechConfig$Builder b (SpeechConfig/builder)]
      (when (some? voice-config)
        (.voiceConfig b ^VoiceConfig (p/into-voice-config voice-config)))
      (when (some? language-code)
        (.languageCode b language-code))
      (when (some? multi-speaker-voice-config)
        (.multiSpeakerVoiceConfig b ^MultiSpeakerVoiceConfig (p/into-multi-speaker-voice-config multi-speaker-voice-config)))
      (.build b))))

(extend-protocol p/IntoVoiceConfig
  VoiceConfig
  (into-voice-config [x]
    x))

(extend-protocol p/IntoVoiceConfig
  IPersistentMap
  (into-voice-config [x]
    (let [{:keys [replicated-voice-config prebuilt-voice-config]} x
          ^VoiceConfig$Builder b (VoiceConfig/builder)]
      (when (some? replicated-voice-config)
        (.replicatedVoiceConfig b ^ReplicatedVoiceConfig  (p/into-replicated-voice-config replicated-voice-config)))
      (when (some? prebuilt-voice-config)
        (.prebuiltVoiceConfig b ^PrebuiltVoiceConfig (p/into-prebuilt-voice-config prebuilt-voice-config)))
      (.build b))))

(extend-protocol p/IntoReplicatedVoiceConfig
  ReplicatedVoiceConfig
  (into-replicated-voice-config [x]
    x))

(extend-protocol p/IntoReplicatedVoiceConfig
  IPersistentMap
  (into-replicated-voice-config [x]
    (let [{:keys [mime-type voice-sample-audio]} x
          ^ReplicatedVoiceConfig$Builder b (ReplicatedVoiceConfig/builder)]
      (when (some? mime-type)
        (.mimeType b mime-type))
      (when (some? voice-sample-audio)
        ;; TODO byte array conversion?
        (.voiceSampleAudio b voice-sample-audio))
      (.build b))))

(extend-protocol p/IntoPrebuiltVoiceConfig
  PrebuiltVoiceConfig
  (into-prebuilt-voice-config [x]
    x))

(extend-protocol p/IntoPrebuiltVoiceConfig
  IPersistentMap
  (into-prebuilt-voice-config [x]
    (let [{:keys [voice-name]} x
          ^PrebuiltVoiceConfig$Builder b (PrebuiltVoiceConfig/builder)]
      (when (some? voice-name)
        (.voiceName b voice-name))
      (.build b))))

(extend-protocol p/IntoAutomaticFunctionCallingConfig
  AutomaticFunctionCallingConfig
  (into-automatic-function-calling-config [x]
    x))

(extend-protocol p/IntoAutomaticFunctionCallingConfig
  IPersistentMap
  (into-automatic-function-calling-config [x]
    (let [{:keys [disable maximum-remote-calls ignore-call-hgistory]} x
          ^AutomaticFunctionCallingConfig$Builder b (AutomaticFunctionCallingConfig/builder)]
      (when (some? disable)
        (.disable b disable))
      (when (some? maximum-remote-calls)
        (.maximumRemoteCalls b (int maximum-remote-calls)))
      (when (some? ignore-call-hgistory)
        (.ignoreCallHistory b ignore-call-hgistory))
      (.build b))))

(extend-protocol p/IntoThinkingConfig
  ThinkingConfig
  (into-thinking-config [x]
    x))

(extend-protocol p/IntoThinkingConfig
  IPersistentMap
  (into-thinking-config [x]
    (let [{:keys [include-thoughts thinking-budget thinking-level]} x
          ^ThinkingConfig$Builder b (ThinkingConfig/builder)]
      (when (some? include-thoughts)
        (.includeThoughts b include-thoughts))
      (when (some? thinking-budget)
        (.thinkingBudget b (int thinking-budget)))
      (when (some? thinking-level)
        (.thinkingLevel b ^String thinking-level))
      (.build b))))

(extend-protocol p/IntoImageConfig
  ImageConfig
  (into-image-config [x]
    x))

(extend-protocol p/IntoImageConfig
  IPersistentMap
  (into-image-config [x]
    (let [{:keys [aspect-ratio image-size person-generation output-mime-type output-compression-quality] } x
          ^ImageConfig$Builder b (ImageConfig/builder)]
      (when (some? aspect-ratio)
        (.aspectRatio b aspect-ratio))
      (when (some? image-size)
        (.imageSize b image-size))
      (when (some? person-generation)
        (.personGeneration b person-generation))
      (when (some? output-mime-type)
        (.outputMimeType b output-mime-type))
      (when (some? output-compression-quality)
        (.outputCompressionQuality b output-compression-quality))
      (.build b))))
