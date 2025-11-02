(ns io.kosong.adk.types
  (:require [clojure.core.protocols :refer [Datafiable]]
            [io.kosong.adk.protocols :as p]
            [io.kosong.adk.utils :refer [optional-datafy-assoc]])
  (:import (clojure.lang IPersistentMap)
           (com.google.genai.types Content FunctionCall FunctionResponse GenerateContentConfig GroundingChunk GroundingChunkRetrievedContext
                                   GroundingChunkWeb GroundingMetadata GroundingSupport HttpOptions Part
                                   Blob RetrievalMetadata SearchEntryPoint Segment)
           (java.util Base64)))


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