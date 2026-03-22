(ns
 io.kosong.adk.generated-types
 (:require
  [io.kosong.autovalue :refer [optional-datafy-assoc]]
  [io.kosong.java]))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ActivityEnd
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ActivityEnd data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ActivityEnd/builder)]
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ActivityEnd
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ActivityStart
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ActivityStart data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ActivityStart/builder)]
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ActivityStart
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ApiAuth
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ApiAuth data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ApiAuth/builder)]
   (clojure.core/when-some
    [v (:api-key-config data)]
    (.
     b
     apiKeyConfig
     (io.kosong.java/make-object
      com.google.genai.types.ApiAuthApiKeyConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ApiAuth
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :api-key-config
    (. x apiKeyConfig))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ApiAuthApiKeyConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ApiAuthApiKeyConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ApiAuthApiKeyConfig/builder)]
   (clojure.core/when-some
    [v (:api-key-secret-version data)]
    (. b apiKeySecretVersion v))
   (clojure.core/when-some
    [v (:api-key-string data)]
    (. b apiKeyString v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ApiAuthApiKeyConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :api-key-secret-version
    (. x apiKeySecretVersion))
   (io.kosong.autovalue/optional-datafy-assoc
    :api-key-string
    (. x apiKeyString))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ApiKeyConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ApiKeyConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ApiKeyConfig/builder)]
   (clojure.core/when-some
    [v (:api-key-secret data)]
    (. b apiKeySecret v))
   (clojure.core/when-some
    [v (:api-key-string data)]
    (. b apiKeyString v))
   (clojure.core/when-some
    [v (:http-element-location data)]
    (. b httpElementLocation v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ApiKeyConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :api-key-secret
    (. x apiKeySecret))
   (io.kosong.autovalue/optional-datafy-assoc
    :api-key-string
    (. x apiKeyString))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-element-location
    (. x httpElementLocation))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.AudioTranscriptionConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.AudioTranscriptionConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.AudioTranscriptionConfig/builder)]
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.AudioTranscriptionConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.AuthConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.AuthConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.AuthConfig/builder)]
   (clojure.core/when-some
    [v (:api-key-config data)]
    (.
     b
     apiKeyConfig
     (io.kosong.java/make-object
      com.google.genai.types.ApiKeyConfig
      v)))
   (clojure.core/when-some [v (:auth-type data)] (. b authType v))
   (clojure.core/when-some
    [v (:google-service-account-config data)]
    (.
     b
     googleServiceAccountConfig
     (io.kosong.java/make-object
      com.google.genai.types.AuthConfigGoogleServiceAccountConfig
      v)))
   (clojure.core/when-some
    [v (:http-basic-auth-config data)]
    (.
     b
     httpBasicAuthConfig
     (io.kosong.java/make-object
      com.google.genai.types.AuthConfigHttpBasicAuthConfig
      v)))
   (clojure.core/when-some
    [v (:oauth-config data)]
    (.
     b
     oauthConfig
     (io.kosong.java/make-object
      com.google.genai.types.AuthConfigOauthConfig
      v)))
   (clojure.core/when-some
    [v (:oidc-config data)]
    (.
     b
     oidcConfig
     (io.kosong.java/make-object
      com.google.genai.types.AuthConfigOidcConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.AuthConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :api-key-config
    (. x apiKeyConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :auth-type
    (. x authType))
   (io.kosong.autovalue/optional-datafy-assoc
    :google-service-account-config
    (. x googleServiceAccountConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-basic-auth-config
    (. x httpBasicAuthConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :oauth-config
    (. x oauthConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :oidc-config
    (. x oidcConfig))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.AuthConfigGoogleServiceAccountConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.AuthConfigGoogleServiceAccountConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.AuthConfigGoogleServiceAccountConfig/builder)]
   (clojure.core/when-some
    [v (:service-account data)]
    (. b serviceAccount v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.AuthConfigGoogleServiceAccountConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :service-account
    (. x serviceAccount))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.AuthConfigHttpBasicAuthConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.AuthConfigHttpBasicAuthConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.AuthConfigHttpBasicAuthConfig/builder)]
   (clojure.core/when-some
    [v (:credential-secret data)]
    (. b credentialSecret v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.AuthConfigHttpBasicAuthConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :credential-secret
    (. x credentialSecret))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.AuthConfigOauthConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.AuthConfigOauthConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.AuthConfigOauthConfig/builder)]
   (clojure.core/when-some
    [v (:access-token data)]
    (. b accessToken v))
   (clojure.core/when-some
    [v (:service-account data)]
    (. b serviceAccount v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.AuthConfigOauthConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :access-token
    (. x accessToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :service-account
    (. x serviceAccount))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.AuthConfigOidcConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.AuthConfigOidcConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.AuthConfigOidcConfig/builder)]
   (clojure.core/when-some [v (:id-token data)] (. b idToken v))
   (clojure.core/when-some
    [v (:service-account data)]
    (. b serviceAccount v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.AuthConfigOidcConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :id-token (. x idToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :service-account
    (. x serviceAccount))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.AuthToken
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.AuthToken data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.AuthToken/builder)]
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.AuthToken
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.AutomaticActivityDetection
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.AutomaticActivityDetection
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.AutomaticActivityDetection/builder)]
   (clojure.core/when-some [v (:disabled data)] (. b disabled v))
   (clojure.core/when-some
    [v (:end-of-speech-sensitivity data)]
    (. b endOfSpeechSensitivity v))
   (clojure.core/when-some
    [v (:prefix-padding-ms data)]
    (. b prefixPaddingMs v))
   (clojure.core/when-some
    [v (:silence-duration-ms data)]
    (. b silenceDurationMs v))
   (clojure.core/when-some
    [v (:start-of-speech-sensitivity data)]
    (. b startOfSpeechSensitivity v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.AutomaticActivityDetection
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :disabled (. x disabled))
   (io.kosong.autovalue/optional-datafy-assoc
    :end-of-speech-sensitivity
    (. x endOfSpeechSensitivity))
   (io.kosong.autovalue/optional-datafy-assoc
    :prefix-padding-ms
    (. x prefixPaddingMs))
   (io.kosong.autovalue/optional-datafy-assoc
    :silence-duration-ms
    (. x silenceDurationMs))
   (io.kosong.autovalue/optional-datafy-assoc
    :start-of-speech-sensitivity
    (. x startOfSpeechSensitivity))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.AutomaticFunctionCallingConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.AutomaticFunctionCallingConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.AutomaticFunctionCallingConfig/builder)]
   (clojure.core/when-some [v (:disable data)] (. b disable v))
   (clojure.core/when-some
    [v (:ignore-call-history data)]
    (. b ignoreCallHistory v))
   (clojure.core/when-some
    [v (:maximum-remote-calls data)]
    (. b maximumRemoteCalls v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.AutomaticFunctionCallingConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :disable (. x disable))
   (io.kosong.autovalue/optional-datafy-assoc
    :ignore-call-history
    (. x ignoreCallHistory))
   (io.kosong.autovalue/optional-datafy-assoc
    :maximum-remote-calls
    (. x maximumRemoteCalls))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.AutoraterConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.AutoraterConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.AutoraterConfig/builder)]
   (clojure.core/when-some
    [v (:autorater-model data)]
    (. b autoraterModel v))
   (clojure.core/when-some
    [v (:generation-config data)]
    (.
     b
     generationConfig
     (io.kosong.java/make-object
      com.google.genai.types.GenerationConfig
      v)))
   (clojure.core/when-some
    [v (:sampling-count data)]
    (. b samplingCount v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.AutoraterConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :autorater-model
    (. x autoraterModel))
   (io.kosong.autovalue/optional-datafy-assoc
    :generation-config
    (. x generationConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :sampling-count
    (. x samplingCount))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.BatchJob
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.BatchJob data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.BatchJob/builder)]
   (clojure.core/when-some
    [v (:completion-stats data)]
    (.
     b
     completionStats
     (io.kosong.java/make-object
      com.google.genai.types.CompletionStats
      v)))
   (clojure.core/when-some [v (:create-time data)] (. b createTime v))
   (clojure.core/when-some
    [v (:dest data)]
    (.
     b
     dest
     (io.kosong.java/make-object
      com.google.genai.types.BatchJobDestination
      v)))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some [v (:end-time data)] (. b endTime v))
   (clojure.core/when-some
    [v (:error data)]
    (.
     b
     error
     (io.kosong.java/make-object com.google.genai.types.JobError v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:src data)]
    (.
     b
     src
     (io.kosong.java/make-object
      com.google.genai.types.BatchJobSource
      v)))
   (clojure.core/when-some [v (:start-time data)] (. b startTime v))
   (clojure.core/when-some [v (:state data)] (. b state v))
   (clojure.core/when-some [v (:update-time data)] (. b updateTime v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.BatchJob
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :completion-stats
    (. x completionStats))
   (io.kosong.autovalue/optional-datafy-assoc
    :create-time
    (. x createTime))
   (io.kosong.autovalue/optional-datafy-assoc :dest (. x dest))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc :end-time (. x endTime))
   (io.kosong.autovalue/optional-datafy-assoc :error (. x error))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc :src (. x src))
   (io.kosong.autovalue/optional-datafy-assoc
    :start-time
    (. x startTime))
   (io.kosong.autovalue/optional-datafy-assoc :state (. x state))
   (io.kosong.autovalue/optional-datafy-assoc
    :update-time
    (. x updateTime))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.BatchJobDestination
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.BatchJobDestination
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.BatchJobDestination/builder)]
   (clojure.core/when-some
    [v (:bigquery-uri data)]
    (. b bigqueryUri v))
   (clojure.core/when-some [v (:file-name data)] (. b fileName v))
   (clojure.core/when-some [v (:format data)] (. b format v))
   (clojure.core/when-some [v (:gcs-uri data)] (. b gcsUri v))
   (clojure.core/when-some
    [v (:inlined-embed-content-responses data)]
    (.
     b
     inlinedEmbedContentResponses
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.InlinedEmbedContentResponse
        x))
      v)))
   (clojure.core/when-some
    [v (:inlined-responses data)]
    (.
     b
     inlinedResponses
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.InlinedResponse
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.BatchJobDestination
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :bigquery-uri
    (. x bigqueryUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :file-name
    (. x fileName))
   (io.kosong.autovalue/optional-datafy-assoc :format (. x format))
   (io.kosong.autovalue/optional-datafy-assoc :gcs-uri (. x gcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :inlined-embed-content-responses
    (. x inlinedEmbedContentResponses))
   (io.kosong.autovalue/optional-datafy-assoc
    :inlined-responses
    (. x inlinedResponses))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.BatchJobSource
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.BatchJobSource data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.BatchJobSource/builder)]
   (clojure.core/when-some
    [v (:bigquery-uri data)]
    (. b bigqueryUri v))
   (clojure.core/when-some [v (:file-name data)] (. b fileName v))
   (clojure.core/when-some [v (:format data)] (. b format v))
   (clojure.core/when-some [v (:gcs-uri data)] (. b gcsUri v))
   (clojure.core/when-some
    [v (:inlined-requests data)]
    (.
     b
     inlinedRequests
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.InlinedRequest
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.BatchJobSource
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :bigquery-uri
    (. x bigqueryUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :file-name
    (. x fileName))
   (io.kosong.autovalue/optional-datafy-assoc :format (. x format))
   (io.kosong.autovalue/optional-datafy-assoc :gcs-uri (. x gcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :inlined-requests
    (. x inlinedRequests))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Blob
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Blob data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Blob/builder)]
   (clojure.core/when-some [v (:data data)] (. b data v))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Blob
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :data (. x data))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CachedContent
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.CachedContent data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CachedContent/builder)]
   (clojure.core/when-some [v (:create-time data)] (. b createTime v))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some [v (:expire-time data)] (. b expireTime v))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some [v (:update-time data)] (. b updateTime v))
   (clojure.core/when-some
    [v (:usage-metadata data)]
    (.
     b
     usageMetadata
     (io.kosong.java/make-object
      com.google.genai.types.CachedContentUsageMetadata
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CachedContent
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :create-time
    (. x createTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :expire-time
    (. x expireTime))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :update-time
    (. x updateTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :usage-metadata
    (. x usageMetadata))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CachedContentUsageMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CachedContentUsageMetadata
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CachedContentUsageMetadata/builder)]
   (clojure.core/when-some
    [v (:audio-duration-seconds data)]
    (. b audioDurationSeconds v))
   (clojure.core/when-some [v (:image-count data)] (. b imageCount v))
   (clojure.core/when-some [v (:text-count data)] (. b textCount v))
   (clojure.core/when-some
    [v (:total-token-count data)]
    (. b totalTokenCount v))
   (clojure.core/when-some
    [v (:video-duration-seconds data)]
    (. b videoDurationSeconds v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CachedContentUsageMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :audio-duration-seconds
    (. x audioDurationSeconds))
   (io.kosong.autovalue/optional-datafy-assoc
    :image-count
    (. x imageCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :text-count
    (. x textCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :total-token-count
    (. x totalTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :video-duration-seconds
    (. x videoDurationSeconds))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CancelBatchJobConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CancelBatchJobConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CancelBatchJobConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CancelBatchJobConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CancelBatchJobParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CancelBatchJobParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CancelBatchJobParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CancelBatchJobConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CancelBatchJobParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CancelTuningJobConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CancelTuningJobConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CancelTuningJobConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CancelTuningJobConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CancelTuningJobParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CancelTuningJobParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CancelTuningJobParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CancelTuningJobConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CancelTuningJobParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CancelTuningJobResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CancelTuningJobResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CancelTuningJobResponse/builder)]
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CancelTuningJobResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Candidate
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Candidate data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Candidate/builder)]
   (clojure.core/when-some
    [v (:avg-logprobs data)]
    (. b avgLogprobs v))
   (clojure.core/when-some
    [v (:citation-metadata data)]
    (.
     b
     citationMetadata
     (io.kosong.java/make-object
      com.google.genai.types.CitationMetadata
      v)))
   (clojure.core/when-some
    [v (:content data)]
    (.
     b
     content
     (io.kosong.java/make-object com.google.genai.types.Content v)))
   (clojure.core/when-some
    [v (:finish-message data)]
    (. b finishMessage v))
   (clojure.core/when-some
    [v (:finish-reason data)]
    (. b finishReason v))
   (clojure.core/when-some
    [v (:grounding-metadata data)]
    (.
     b
     groundingMetadata
     (io.kosong.java/make-object
      com.google.genai.types.GroundingMetadata
      v)))
   (clojure.core/when-some [v (:index data)] (. b index v))
   (clojure.core/when-some
    [v (:logprobs-result data)]
    (.
     b
     logprobsResult
     (io.kosong.java/make-object
      com.google.genai.types.LogprobsResult
      v)))
   (clojure.core/when-some
    [v (:safety-ratings data)]
    (.
     b
     safetyRatings
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.SafetyRating
        x))
      v)))
   (clojure.core/when-some [v (:token-count data)] (. b tokenCount v))
   (clojure.core/when-some
    [v (:url-context-metadata data)]
    (.
     b
     urlContextMetadata
     (io.kosong.java/make-object
      com.google.genai.types.UrlContextMetadata
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Candidate
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :avg-logprobs
    (. x avgLogprobs))
   (io.kosong.autovalue/optional-datafy-assoc
    :citation-metadata
    (. x citationMetadata))
   (io.kosong.autovalue/optional-datafy-assoc :content (. x content))
   (io.kosong.autovalue/optional-datafy-assoc
    :finish-message
    (. x finishMessage))
   (io.kosong.autovalue/optional-datafy-assoc
    :finish-reason
    (. x finishReason))
   (io.kosong.autovalue/optional-datafy-assoc
    :grounding-metadata
    (. x groundingMetadata))
   (io.kosong.autovalue/optional-datafy-assoc :index (. x index))
   (io.kosong.autovalue/optional-datafy-assoc
    :logprobs-result
    (. x logprobsResult))
   (io.kosong.autovalue/optional-datafy-assoc
    :safety-ratings
    (. x safetyRatings))
   (io.kosong.autovalue/optional-datafy-assoc
    :token-count
    (. x tokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :url-context-metadata
    (. x urlContextMetadata))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Checkpoint
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Checkpoint data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Checkpoint/builder)]
   (clojure.core/when-some
    [v (:checkpoint-id data)]
    (. b checkpointId v))
   (clojure.core/when-some [v (:epoch data)] (. b epoch v))
   (clojure.core/when-some [v (:step data)] (. b step v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Checkpoint
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :checkpoint-id
    (. x checkpointId))
   (io.kosong.autovalue/optional-datafy-assoc :epoch (. x epoch))
   (io.kosong.autovalue/optional-datafy-assoc :step (. x step))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ChunkingConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ChunkingConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ChunkingConfig/builder)]
   (clojure.core/when-some
    [v (:white-space-config data)]
    (.
     b
     whiteSpaceConfig
     (io.kosong.java/make-object
      com.google.genai.types.WhiteSpaceConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ChunkingConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :white-space-config
    (. x whiteSpaceConfig))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Citation
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Citation data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Citation/builder)]
   (clojure.core/when-some [v (:end-index data)] (. b endIndex v))
   (clojure.core/when-some [v (:license data)] (. b license v))
   (clojure.core/when-some
    [v (:publication-date data)]
    (.
     b
     publicationDate
     (io.kosong.java/make-object
      com.google.genai.types.GoogleTypeDate
      v)))
   (clojure.core/when-some [v (:start-index data)] (. b startIndex v))
   (clojure.core/when-some [v (:title data)] (. b title v))
   (clojure.core/when-some [v (:uri data)] (. b uri v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Citation
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :end-index
    (. x endIndex))
   (io.kosong.autovalue/optional-datafy-assoc :license (. x license))
   (io.kosong.autovalue/optional-datafy-assoc
    :publication-date
    (. x publicationDate))
   (io.kosong.autovalue/optional-datafy-assoc
    :start-index
    (. x startIndex))
   (io.kosong.autovalue/optional-datafy-assoc :title (. x title))
   (io.kosong.autovalue/optional-datafy-assoc :uri (. x uri))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CitationMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.CitationMetadata data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CitationMetadata/builder)]
   (clojure.core/when-some
    [v (:citations data)]
    (.
     b
     citations
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Citation x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CitationMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :citations
    (. x citations))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ClientOptions
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ClientOptions data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ClientOptions/builder)]
   (clojure.core/when-some
    [v (:max-connections data)]
    (. b maxConnections v))
   (clojure.core/when-some
    [v (:max-connections-per-host data)]
    (. b maxConnectionsPerHost v))
   (clojure.core/when-some
    [v (:proxy-options data)]
    (.
     b
     proxyOptions
     (io.kosong.java/make-object
      com.google.genai.types.ProxyOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ClientOptions
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :max-connections
    (. x maxConnections))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-connections-per-host
    (. x maxConnectionsPerHost))
   (io.kosong.autovalue/optional-datafy-assoc
    :proxy-options
    (. x proxyOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CodeExecutionResult
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CodeExecutionResult
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CodeExecutionResult/builder)]
   (clojure.core/when-some [v (:outcome data)] (. b outcome v))
   (clojure.core/when-some [v (:output data)] (. b output v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CodeExecutionResult
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :outcome (. x outcome))
   (io.kosong.autovalue/optional-datafy-assoc :output (. x output))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CompletionStats
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.CompletionStats data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CompletionStats/builder)]
   (clojure.core/when-some
    [v (:failed-count data)]
    (. b failedCount v))
   (clojure.core/when-some
    [v (:incomplete-count data)]
    (. b incompleteCount v))
   (clojure.core/when-some
    [v (:successful-count data)]
    (. b successfulCount v))
   (clojure.core/when-some
    [v (:successful-forecast-point-count data)]
    (. b successfulForecastPointCount v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CompletionStats
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :failed-count
    (. x failedCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :incomplete-count
    (. x incompleteCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :successful-count
    (. x successfulCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :successful-forecast-point-count
    (. x successfulForecastPointCount))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ComputeTokensConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ComputeTokensConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ComputeTokensConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ComputeTokensConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ComputeTokensParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ComputeTokensParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ComputeTokensParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.ComputeTokensConfig
      v)))
   (clojure.core/when-some
    [v (:contents data)]
    (.
     b
     contents
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ComputeTokensParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :contents (. x contents))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ComputeTokensResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ComputeTokensResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ComputeTokensResponse/builder)]
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (clojure.core/when-some
    [v (:tokens-info data)]
    (.
     b
     tokensInfo
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.TokensInfo
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ComputeTokensResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (io.kosong.autovalue/optional-datafy-assoc
    :tokens-info
    (. x tokensInfo))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ComputeTokensResult
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ComputeTokensResult
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ComputeTokensResult/builder)]
   (clojure.core/when-some
    [v (:tokens-info data)]
    (.
     b
     tokensInfo
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.TokensInfo
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ComputeTokensResult
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :tokens-info
    (. x tokensInfo))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ComputerUse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ComputerUse data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ComputerUse/builder)]
   (clojure.core/when-some [v (:environment data)] (. b environment v))
   (clojure.core/when-some
    [v (:excluded-predefined-functions data)]
    (. b excludedPredefinedFunctions v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ComputerUse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :environment
    (. x environment))
   (io.kosong.autovalue/optional-datafy-assoc
    :excluded-predefined-functions
    (. x excludedPredefinedFunctions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Content
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Content data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Content/builder)]
   (clojure.core/when-some
    [v (:parts data)]
    (.
     b
     parts
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Part x))
      v)))
   (clojure.core/when-some [v (:role data)] (. b role v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Content
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :parts (. x parts))
   (io.kosong.autovalue/optional-datafy-assoc :role (. x role))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ContentEmbedding
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ContentEmbedding data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ContentEmbedding/builder)]
   (clojure.core/when-some
    [v (:statistics data)]
    (.
     b
     statistics
     (io.kosong.java/make-object
      com.google.genai.types.ContentEmbeddingStatistics
      v)))
   (clojure.core/when-some [v (:values data)] (. b values v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ContentEmbedding
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :statistics
    (. x statistics))
   (io.kosong.autovalue/optional-datafy-assoc :values (. x values))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ContentEmbeddingStatistics
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ContentEmbeddingStatistics
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ContentEmbeddingStatistics/builder)]
   (clojure.core/when-some [v (:token-count data)] (. b tokenCount v))
   (clojure.core/when-some [v (:truncated data)] (. b truncated v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ContentEmbeddingStatistics
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :token-count
    (. x tokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :truncated
    (. x truncated))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ContentReferenceImage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ContentReferenceImage
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ContentReferenceImage/builder)]
   (clojure.core/when-some
    [v (:reference-id data)]
    (. b referenceId v))
   (clojure.core/when-some
    [v (:reference-image data)]
    (.
     b
     referenceImage
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:reference-type data)]
    (. b referenceType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ContentReferenceImage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-id
    (. x referenceId))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-image
    (. x referenceImage))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-type
    (. x referenceType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ContextWindowCompressionConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ContextWindowCompressionConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ContextWindowCompressionConfig/builder)]
   (clojure.core/when-some
    [v (:sliding-window data)]
    (.
     b
     slidingWindow
     (io.kosong.java/make-object
      com.google.genai.types.SlidingWindow
      v)))
   (clojure.core/when-some
    [v (:trigger-tokens data)]
    (. b triggerTokens v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ContextWindowCompressionConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :sliding-window
    (. x slidingWindow))
   (io.kosong.autovalue/optional-datafy-assoc
    :trigger-tokens
    (. x triggerTokens))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ControlReferenceConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ControlReferenceConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ControlReferenceConfig/builder)]
   (clojure.core/when-some
    [v (:control-type data)]
    (. b controlType v))
   (clojure.core/when-some
    [v (:enable-control-image-computation data)]
    (. b enableControlImageComputation v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ControlReferenceConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :control-type
    (. x controlType))
   (io.kosong.autovalue/optional-datafy-assoc
    :enable-control-image-computation
    (. x enableControlImageComputation))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ControlReferenceImage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ControlReferenceImage
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ControlReferenceImage/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.ControlReferenceConfig
      v)))
   (clojure.core/when-some
    [v (:reference-id data)]
    (. b referenceId v))
   (clojure.core/when-some
    [v (:reference-image data)]
    (.
     b
     referenceImage
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:reference-type data)]
    (. b referenceType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ControlReferenceImage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-id
    (. x referenceId))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-image
    (. x referenceImage))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-type
    (. x referenceType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CountTokensConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CountTokensConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CountTokensConfig/builder)]
   (clojure.core/when-some
    [v (:generation-config data)]
    (.
     b
     generationConfig
     (io.kosong.java/make-object
      com.google.genai.types.GenerationConfig
      v)))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some
    [v (:system-instruction data)]
    (.
     b
     systemInstruction
     (io.kosong.java/make-object com.google.genai.types.Content v)))
   (clojure.core/when-some
    [v (:tools data)]
    (.
     b
     tools
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Tool x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CountTokensConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :generation-config
    (. x generationConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :system-instruction
    (. x systemInstruction))
   (io.kosong.autovalue/optional-datafy-assoc :tools (. x tools))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CountTokensParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CountTokensParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CountTokensParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CountTokensConfig
      v)))
   (clojure.core/when-some
    [v (:contents data)]
    (.
     b
     contents
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CountTokensParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :contents (. x contents))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CountTokensResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CountTokensResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CountTokensResponse/builder)]
   (clojure.core/when-some
    [v (:cached-content-token-count data)]
    (. b cachedContentTokenCount v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (clojure.core/when-some
    [v (:total-tokens data)]
    (. b totalTokens v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CountTokensResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :cached-content-token-count
    (. x cachedContentTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (io.kosong.autovalue/optional-datafy-assoc
    :total-tokens
    (. x totalTokens))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CountTokensResult
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CountTokensResult
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CountTokensResult/builder)]
   (clojure.core/when-some
    [v (:total-tokens data)]
    (. b totalTokens v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CountTokensResult
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :total-tokens
    (. x totalTokens))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateAuthTokenConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateAuthTokenConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateAuthTokenConfig/builder)]
   (clojure.core/when-some [v (:expire-time data)] (. b expireTime v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some
    [v (:live-connect-constraints data)]
    (.
     b
     liveConnectConstraints
     (io.kosong.java/make-object
      com.google.genai.types.LiveConnectConstraints
      v)))
   (clojure.core/when-some
    [v (:lock-additional-fields data)]
    (. b lockAdditionalFields v))
   (clojure.core/when-some
    [v (:new-session-expire-time data)]
    (. b newSessionExpireTime v))
   (clojure.core/when-some [v (:uses data)] (. b uses v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateAuthTokenConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :expire-time
    (. x expireTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :live-connect-constraints
    (. x liveConnectConstraints))
   (io.kosong.autovalue/optional-datafy-assoc
    :lock-additional-fields
    (. x lockAdditionalFields))
   (io.kosong.autovalue/optional-datafy-assoc
    :new-session-expire-time
    (. x newSessionExpireTime))
   (io.kosong.autovalue/optional-datafy-assoc :uses (. x uses))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateAuthTokenParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateAuthTokenParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateAuthTokenParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CreateAuthTokenConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateAuthTokenParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateBatchJobConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateBatchJobConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateBatchJobConfig/builder)]
   (clojure.core/when-some
    [v (:dest data)]
    (.
     b
     dest
     (io.kosong.java/make-object
      com.google.genai.types.BatchJobDestination
      v)))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateBatchJobConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :dest (. x dest))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateBatchJobParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateBatchJobParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateBatchJobParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CreateBatchJobConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some
    [v (:src data)]
    (.
     b
     src
     (io.kosong.java/make-object
      com.google.genai.types.BatchJobSource
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateBatchJobParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc :src (. x src))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateCachedContentConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateCachedContentConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateCachedContentConfig/builder)]
   (clojure.core/when-some
    [v (:contents data)]
    (.
     b
     contents
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some [v (:expire-time data)] (. b expireTime v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:kms-key-name data)] (. b kmsKeyName v))
   (clojure.core/when-some
    [v (:system-instruction data)]
    (.
     b
     systemInstruction
     (io.kosong.java/make-object com.google.genai.types.Content v)))
   (clojure.core/when-some
    [v (:tool-config data)]
    (.
     b
     toolConfig
     (io.kosong.java/make-object com.google.genai.types.ToolConfig v)))
   (clojure.core/when-some
    [v (:tools data)]
    (.
     b
     tools
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Tool x))
      v)))
   (clojure.core/when-some [v (:ttl data)] (. b ttl v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateCachedContentConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :contents (. x contents))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :expire-time
    (. x expireTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :kms-key-name
    (. x kmsKeyName))
   (io.kosong.autovalue/optional-datafy-assoc
    :system-instruction
    (. x systemInstruction))
   (io.kosong.autovalue/optional-datafy-assoc
    :tool-config
    (. x toolConfig))
   (io.kosong.autovalue/optional-datafy-assoc :tools (. x tools))
   (io.kosong.autovalue/optional-datafy-assoc :ttl (. x ttl))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateCachedContentParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateCachedContentParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateCachedContentParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CreateCachedContentConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateCachedContentParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateEmbeddingsBatchJobConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateEmbeddingsBatchJobConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateEmbeddingsBatchJobConfig/builder)]
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateEmbeddingsBatchJobConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateEmbeddingsBatchJobParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateEmbeddingsBatchJobParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.CreateEmbeddingsBatchJobParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CreateEmbeddingsBatchJobConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some
    [v (:src data)]
    (.
     b
     src
     (io.kosong.java/make-object
      com.google.genai.types.EmbeddingsBatchJobSource
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateEmbeddingsBatchJobParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc :src (. x src))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateFileConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.CreateFileConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateFileConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some
    [v (:should-return-http-response data)]
    (. b shouldReturnHttpResponse v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateFileConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :should-return-http-response
    (. x shouldReturnHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateFileParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateFileParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateFileParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CreateFileConfig
      v)))
   (clojure.core/when-some
    [v (:file data)]
    (.
     b
     file
     (io.kosong.java/make-object com.google.genai.types.File v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateFileParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :file (. x file))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateFileResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateFileResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateFileResponse/builder)]
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateFileResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateFileSearchStoreConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateFileSearchStoreConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateFileSearchStoreConfig/builder)]
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateFileSearchStoreConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateFileSearchStoreParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateFileSearchStoreParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateFileSearchStoreParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CreateFileSearchStoreConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateFileSearchStoreParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateTuningJobConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateTuningJobConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateTuningJobConfig/builder)]
   (clojure.core/when-some
    [v (:adapter-size data)]
    (. b adapterSize v))
   (clojure.core/when-some [v (:batch-size data)] (. b batchSize v))
   (clojure.core/when-some [v (:beta data)] (. b beta v))
   (clojure.core/when-some [v (:description data)] (. b description v))
   (clojure.core/when-some [v (:epoch-count data)] (. b epochCount v))
   (clojure.core/when-some
    [v (:evaluation-config data)]
    (.
     b
     evaluationConfig
     (io.kosong.java/make-object
      com.google.genai.types.EvaluationConfig
      v)))
   (clojure.core/when-some
    [v (:export-last-checkpoint-only data)]
    (. b exportLastCheckpointOnly v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:labels data)] (. b labels v))
   (clojure.core/when-some
    [v (:learning-rate data)]
    (. b learningRate v))
   (clojure.core/when-some
    [v (:learning-rate-multiplier data)]
    (. b learningRateMultiplier v))
   (clojure.core/when-some [v (:method data)] (. b method v))
   (clojure.core/when-some
    [v (:pre-tuned-model-checkpoint-id data)]
    (. b preTunedModelCheckpointId v))
   (clojure.core/when-some
    [v (:tuned-model-display-name data)]
    (. b tunedModelDisplayName v))
   (clojure.core/when-some
    [v (:validation-dataset data)]
    (.
     b
     validationDataset
     (io.kosong.java/make-object
      com.google.genai.types.TuningValidationDataset
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateTuningJobConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :adapter-size
    (. x adapterSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :batch-size
    (. x batchSize))
   (io.kosong.autovalue/optional-datafy-assoc :beta (. x beta))
   (io.kosong.autovalue/optional-datafy-assoc
    :description
    (. x description))
   (io.kosong.autovalue/optional-datafy-assoc
    :epoch-count
    (. x epochCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :evaluation-config
    (. x evaluationConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :export-last-checkpoint-only
    (. x exportLastCheckpointOnly))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc
    :learning-rate
    (. x learningRate))
   (io.kosong.autovalue/optional-datafy-assoc
    :learning-rate-multiplier
    (. x learningRateMultiplier))
   (io.kosong.autovalue/optional-datafy-assoc :method (. x method))
   (io.kosong.autovalue/optional-datafy-assoc
    :pre-tuned-model-checkpoint-id
    (. x preTunedModelCheckpointId))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuned-model-display-name
    (. x tunedModelDisplayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :validation-dataset
    (. x validationDataset))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateTuningJobParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateTuningJobParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CreateTuningJobParameters/builder)]
   (clojure.core/when-some [v (:base-model data)] (. b baseModel v))
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CreateTuningJobConfig
      v)))
   (clojure.core/when-some
    [v (:training-dataset data)]
    (.
     b
     trainingDataset
     (io.kosong.java/make-object
      com.google.genai.types.TuningDataset
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateTuningJobParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :base-model
    (. x baseModel))
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc
    :training-dataset
    (. x trainingDataset))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CreateTuningJobParametersPrivate
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.CreateTuningJobParametersPrivate
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.CreateTuningJobParametersPrivate/builder)]
   (clojure.core/when-some [v (:base-model data)] (. b baseModel v))
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.CreateTuningJobConfig
      v)))
   (clojure.core/when-some
    [v (:pre-tuned-model data)]
    (.
     b
     preTunedModel
     (io.kosong.java/make-object
      com.google.genai.types.PreTunedModel
      v)))
   (clojure.core/when-some
    [v (:training-dataset data)]
    (.
     b
     trainingDataset
     (io.kosong.java/make-object
      com.google.genai.types.TuningDataset
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CreateTuningJobParametersPrivate
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :base-model
    (. x baseModel))
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc
    :pre-tuned-model
    (. x preTunedModel))
   (io.kosong.autovalue/optional-datafy-assoc
    :training-dataset
    (. x trainingDataset))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.CustomMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.CustomMetadata data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.CustomMetadata/builder)]
   (clojure.core/when-some [v (:key data)] (. b key v))
   (clojure.core/when-some
    [v (:numeric-value data)]
    (. b numericValue v))
   (clojure.core/when-some
    [v (:string-list-value data)]
    (.
     b
     stringListValue
     (io.kosong.java/make-object com.google.genai.types.StringList v)))
   (clojure.core/when-some
    [v (:string-value data)]
    (. b stringValue v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.CustomMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :key (. x key))
   (io.kosong.autovalue/optional-datafy-assoc
    :numeric-value
    (. x numericValue))
   (io.kosong.autovalue/optional-datafy-assoc
    :string-list-value
    (. x stringListValue))
   (io.kosong.autovalue/optional-datafy-assoc
    :string-value
    (. x stringValue))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DatasetDistribution
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DatasetDistribution
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DatasetDistribution/builder)]
   (clojure.core/when-some
    [v (:buckets data)]
    (.
     b
     buckets
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.DatasetDistributionDistributionBucket
        x))
      v)))
   (clojure.core/when-some [v (:max data)] (. b max v))
   (clojure.core/when-some [v (:mean data)] (. b mean v))
   (clojure.core/when-some [v (:median data)] (. b median v))
   (clojure.core/when-some [v (:min data)] (. b min v))
   (clojure.core/when-some [v (:p-5 data)] (. b p5 v))
   (clojure.core/when-some [v (:p-95 data)] (. b p95 v))
   (clojure.core/when-some [v (:sum data)] (. b sum v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DatasetDistribution
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :buckets (. x buckets))
   (io.kosong.autovalue/optional-datafy-assoc :max (. x max))
   (io.kosong.autovalue/optional-datafy-assoc :mean (. x mean))
   (io.kosong.autovalue/optional-datafy-assoc :median (. x median))
   (io.kosong.autovalue/optional-datafy-assoc :min (. x min))
   (io.kosong.autovalue/optional-datafy-assoc :p-5 (. x p5))
   (io.kosong.autovalue/optional-datafy-assoc :p-95 (. x p95))
   (io.kosong.autovalue/optional-datafy-assoc :sum (. x sum))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DatasetDistributionDistributionBucket
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DatasetDistributionDistributionBucket
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.DatasetDistributionDistributionBucket/builder)]
   (clojure.core/when-some [v (:count data)] (. b count v))
   (clojure.core/when-some [v (:left data)] (. b left v))
   (clojure.core/when-some [v (:right data)] (. b right v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DatasetDistributionDistributionBucket
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :count (. x count))
   (io.kosong.autovalue/optional-datafy-assoc :left (. x left))
   (io.kosong.autovalue/optional-datafy-assoc :right (. x right))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DatasetStats
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.DatasetStats data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DatasetStats/builder)]
   (clojure.core/when-some
    [v (:total-billable-character-count data)]
    (. b totalBillableCharacterCount v))
   (clojure.core/when-some
    [v (:total-tuning-character-count data)]
    (. b totalTuningCharacterCount v))
   (clojure.core/when-some
    [v (:tuning-dataset-example-count data)]
    (. b tuningDatasetExampleCount v))
   (clojure.core/when-some
    [v (:tuning-step-count data)]
    (. b tuningStepCount v))
   (clojure.core/when-some
    [v (:user-dataset-examples data)]
    (.
     b
     userDatasetExamples
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (clojure.core/when-some
    [v (:user-input-token-distribution data)]
    (.
     b
     userInputTokenDistribution
     (io.kosong.java/make-object
      com.google.genai.types.DatasetDistribution
      v)))
   (clojure.core/when-some
    [v (:user-message-per-example-distribution data)]
    (.
     b
     userMessagePerExampleDistribution
     (io.kosong.java/make-object
      com.google.genai.types.DatasetDistribution
      v)))
   (clojure.core/when-some
    [v (:user-output-token-distribution data)]
    (.
     b
     userOutputTokenDistribution
     (io.kosong.java/make-object
      com.google.genai.types.DatasetDistribution
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DatasetStats
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :total-billable-character-count
    (. x totalBillableCharacterCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :total-tuning-character-count
    (. x totalTuningCharacterCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuning-dataset-example-count
    (. x tuningDatasetExampleCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuning-step-count
    (. x tuningStepCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-dataset-examples
    (. x userDatasetExamples))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-input-token-distribution
    (. x userInputTokenDistribution))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-message-per-example-distribution
    (. x userMessagePerExampleDistribution))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-output-token-distribution
    (. x userOutputTokenDistribution))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteBatchJobConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteBatchJobConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteBatchJobConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteBatchJobConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteBatchJobParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteBatchJobParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteBatchJobParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.DeleteBatchJobConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteBatchJobParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteCachedContentConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteCachedContentConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteCachedContentConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteCachedContentConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteCachedContentParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteCachedContentParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteCachedContentParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.DeleteCachedContentConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteCachedContentParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteCachedContentResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteCachedContentResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteCachedContentResponse/builder)]
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteCachedContentResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteDocumentConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteDocumentConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteDocumentConfig/builder)]
   (clojure.core/when-some [v (:force data)] (. b force v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteDocumentConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :force (. x force))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteDocumentParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteDocumentParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteDocumentParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.DeleteDocumentConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteDocumentParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteFileConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.DeleteFileConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteFileConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteFileConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteFileParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteFileParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteFileParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.DeleteFileConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteFileParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteFileResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteFileResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteFileResponse/builder)]
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteFileResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteFileSearchStoreConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteFileSearchStoreConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteFileSearchStoreConfig/builder)]
   (clojure.core/when-some [v (:force data)] (. b force v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteFileSearchStoreConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :force (. x force))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteFileSearchStoreParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteFileSearchStoreParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteFileSearchStoreParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.DeleteFileSearchStoreConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteFileSearchStoreParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteModelConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteModelConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteModelConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteModelConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteModelParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteModelParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteModelParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.DeleteModelConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteModelParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteModelResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteModelResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteModelResponse/builder)]
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteModelResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DeleteResourceJob
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DeleteResourceJob
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DeleteResourceJob/builder)]
   (clojure.core/when-some [v (:done data)] (. b done v))
   (clojure.core/when-some
    [v (:error data)]
    (.
     b
     error
     (io.kosong.java/make-object com.google.genai.types.JobError v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DeleteResourceJob
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :done (. x done))
   (io.kosong.autovalue/optional-datafy-assoc :error (. x error))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DistillationDataStats
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DistillationDataStats
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DistillationDataStats/builder)]
   (clojure.core/when-some
    [v (:training-dataset-stats data)]
    (.
     b
     trainingDatasetStats
     (io.kosong.java/make-object
      com.google.genai.types.DatasetStats
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DistillationDataStats
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :training-dataset-stats
    (. x trainingDatasetStats))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Document
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Document data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Document/builder)]
   (clojure.core/when-some [v (:create-time data)] (. b createTime v))
   (clojure.core/when-some
    [v (:custom-metadata data)]
    (.
     b
     customMetadata
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.CustomMetadata
        x))
      v)))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some [v (:size-bytes data)] (. b sizeBytes v))
   (clojure.core/when-some [v (:state data)] (. b state v))
   (clojure.core/when-some [v (:update-time data)] (. b updateTime v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Document
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :create-time
    (. x createTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :custom-metadata
    (. x customMetadata))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :size-bytes
    (. x sizeBytes))
   (io.kosong.autovalue/optional-datafy-assoc :state (. x state))
   (io.kosong.autovalue/optional-datafy-assoc
    :update-time
    (. x updateTime))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DownloadFileConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DownloadFileConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DownloadFileConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DownloadFileConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.DynamicRetrievalConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.DynamicRetrievalConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.DynamicRetrievalConfig/builder)]
   (clojure.core/when-some
    [v (:dynamic-threshold data)]
    (. b dynamicThreshold v))
   (clojure.core/when-some [v (:mode data)] (. b mode v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.DynamicRetrievalConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :dynamic-threshold
    (. x dynamicThreshold))
   (io.kosong.autovalue/optional-datafy-assoc :mode (. x mode))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EditImageConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.EditImageConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EditImageConfig/builder)]
   (clojure.core/when-some
    [v (:add-watermark data)]
    (. b addWatermark v))
   (clojure.core/when-some
    [v (:aspect-ratio data)]
    (. b aspectRatio v))
   (clojure.core/when-some [v (:base-steps data)] (. b baseSteps v))
   (clojure.core/when-some [v (:edit-mode data)] (. b editMode v))
   (clojure.core/when-some
    [v (:guidance-scale data)]
    (. b guidanceScale v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some
    [v (:include-rai-reason data)]
    (. b includeRaiReason v))
   (clojure.core/when-some
    [v (:include-safety-attributes data)]
    (. b includeSafetyAttributes v))
   (clojure.core/when-some [v (:labels data)] (. b labels v))
   (clojure.core/when-some [v (:language data)] (. b language v))
   (clojure.core/when-some
    [v (:negative-prompt data)]
    (. b negativePrompt v))
   (clojure.core/when-some
    [v (:number-of-images data)]
    (. b numberOfImages v))
   (clojure.core/when-some
    [v (:output-compression-quality data)]
    (. b outputCompressionQuality v))
   (clojure.core/when-some
    [v (:output-gcs-uri data)]
    (. b outputGcsUri v))
   (clojure.core/when-some
    [v (:output-mime-type data)]
    (. b outputMimeType v))
   (clojure.core/when-some
    [v (:person-generation data)]
    (. b personGeneration v))
   (clojure.core/when-some
    [v (:safety-filter-level data)]
    (. b safetyFilterLevel v))
   (clojure.core/when-some [v (:seed data)] (. b seed v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EditImageConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :add-watermark
    (. x addWatermark))
   (io.kosong.autovalue/optional-datafy-assoc
    :aspect-ratio
    (. x aspectRatio))
   (io.kosong.autovalue/optional-datafy-assoc
    :base-steps
    (. x baseSteps))
   (io.kosong.autovalue/optional-datafy-assoc
    :edit-mode
    (. x editMode))
   (io.kosong.autovalue/optional-datafy-assoc
    :guidance-scale
    (. x guidanceScale))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :include-rai-reason
    (. x includeRaiReason))
   (io.kosong.autovalue/optional-datafy-assoc
    :include-safety-attributes
    (. x includeSafetyAttributes))
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc :language (. x language))
   (io.kosong.autovalue/optional-datafy-assoc
    :negative-prompt
    (. x negativePrompt))
   (io.kosong.autovalue/optional-datafy-assoc
    :number-of-images
    (. x numberOfImages))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-compression-quality
    (. x outputCompressionQuality))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-gcs-uri
    (. x outputGcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-mime-type
    (. x outputMimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :person-generation
    (. x personGeneration))
   (io.kosong.autovalue/optional-datafy-assoc
    :safety-filter-level
    (. x safetyFilterLevel))
   (io.kosong.autovalue/optional-datafy-assoc :seed (. x seed))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EditImageParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.EditImageParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EditImageParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.EditImageConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some [v (:prompt data)] (. b prompt v))
   (clojure.core/when-some
    [v (:reference-images data)]
    (.
     b
     referenceImages
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ReferenceImageAPI
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EditImageParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc :prompt (. x prompt))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-images
    (. x referenceImages))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EditImageResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.EditImageResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EditImageResponse/builder)]
   (clojure.core/when-some
    [v (:generated-images data)]
    (.
     b
     generatedImages
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GeneratedImage
        x))
      v)))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EditImageResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :generated-images
    (. x generatedImages))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EmbedContentBatch
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.EmbedContentBatch
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EmbedContentBatch/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.EmbedContentConfig
      v)))
   (clojure.core/when-some
    [v (:contents data)]
    (.
     b
     contents
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EmbedContentBatch
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :contents (. x contents))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EmbedContentConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.EmbedContentConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EmbedContentConfig/builder)]
   (clojure.core/when-some
    [v (:auto-truncate data)]
    (. b autoTruncate v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (clojure.core/when-some
    [v (:output-dimensionality data)]
    (. b outputDimensionality v))
   (clojure.core/when-some [v (:task-type data)] (. b taskType v))
   (clojure.core/when-some [v (:title data)] (. b title v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EmbedContentConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :auto-truncate
    (. x autoTruncate))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-dimensionality
    (. x outputDimensionality))
   (io.kosong.autovalue/optional-datafy-assoc
    :task-type
    (. x taskType))
   (io.kosong.autovalue/optional-datafy-assoc :title (. x title))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EmbedContentMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.EmbedContentMetadata
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EmbedContentMetadata/builder)]
   (clojure.core/when-some
    [v (:billable-character-count data)]
    (. b billableCharacterCount v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EmbedContentMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :billable-character-count
    (. x billableCharacterCount))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EmbedContentParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.EmbedContentParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EmbedContentParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.EmbedContentConfig
      v)))
   (clojure.core/when-some
    [v (:contents data)]
    (.
     b
     contents
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EmbedContentParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :contents (. x contents))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EmbedContentResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.EmbedContentResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EmbedContentResponse/builder)]
   (clojure.core/when-some
    [v (:embeddings data)]
    (.
     b
     embeddings
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ContentEmbedding
        x))
      v)))
   (clojure.core/when-some
    [v (:metadata data)]
    (.
     b
     metadata
     (io.kosong.java/make-object
      com.google.genai.types.EmbedContentMetadata
      v)))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EmbedContentResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :embeddings
    (. x embeddings))
   (io.kosong.autovalue/optional-datafy-assoc :metadata (. x metadata))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EmbeddingsBatchJobSource
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.EmbeddingsBatchJobSource
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EmbeddingsBatchJobSource/builder)]
   (clojure.core/when-some [v (:file-name data)] (. b fileName v))
   (clojure.core/when-some
    [v (:inlined-requests data)]
    (.
     b
     inlinedRequests
     (io.kosong.java/make-object
      com.google.genai.types.EmbedContentBatch
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EmbeddingsBatchJobSource
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :file-name
    (. x fileName))
   (io.kosong.autovalue/optional-datafy-assoc
    :inlined-requests
    (. x inlinedRequests))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EncryptionSpec
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.EncryptionSpec data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EncryptionSpec/builder)]
   (clojure.core/when-some [v (:kms-key-name data)] (. b kmsKeyName v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EncryptionSpec
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :kms-key-name
    (. x kmsKeyName))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Endpoint
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Endpoint data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Endpoint/builder)]
   (clojure.core/when-some
    [v (:deployed-model-id data)]
    (. b deployedModelId v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Endpoint
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :deployed-model-id
    (. x deployedModelId))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EnterpriseWebSearch
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.EnterpriseWebSearch
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EnterpriseWebSearch/builder)]
   (clojure.core/when-some
    [v (:blocking-confidence data)]
    (. b blockingConfidence v))
   (clojure.core/when-some
    [v (:exclude-domains data)]
    (. b excludeDomains v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EnterpriseWebSearch
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :blocking-confidence
    (. x blockingConfidence))
   (io.kosong.autovalue/optional-datafy-assoc
    :exclude-domains
    (. x excludeDomains))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EntityLabel
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.EntityLabel data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EntityLabel/builder)]
   (clojure.core/when-some [v (:label data)] (. b label v))
   (clojure.core/when-some [v (:score data)] (. b score v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EntityLabel
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :label (. x label))
   (io.kosong.autovalue/optional-datafy-assoc :score (. x score))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.EvaluationConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.EvaluationConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.EvaluationConfig/builder)]
   (clojure.core/when-some
    [v (:autorater-config data)]
    (.
     b
     autoraterConfig
     (io.kosong.java/make-object
      com.google.genai.types.AutoraterConfig
      v)))
   (clojure.core/when-some
    [v (:metrics data)]
    (.
     b
     metrics
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Metric x))
      v)))
   (clojure.core/when-some
    [v (:output-config data)]
    (.
     b
     outputConfig
     (io.kosong.java/make-object
      com.google.genai.types.OutputConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.EvaluationConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :autorater-config
    (. x autoraterConfig))
   (io.kosong.autovalue/optional-datafy-assoc :metrics (. x metrics))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-config
    (. x outputConfig))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ExecutableCode
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ExecutableCode data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ExecutableCode/builder)]
   (clojure.core/when-some [v (:code data)] (. b code v))
   (clojure.core/when-some [v (:language data)] (. b language v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ExecutableCode
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :code (. x code))
   (io.kosong.autovalue/optional-datafy-assoc :language (. x language))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ExternalApi
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ExternalApi data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ExternalApi/builder)]
   (clojure.core/when-some
    [v (:api-auth data)]
    (.
     b
     apiAuth
     (io.kosong.java/make-object com.google.genai.types.ApiAuth v)))
   (clojure.core/when-some [v (:api-spec data)] (. b apiSpec v))
   (clojure.core/when-some
    [v (:auth-config data)]
    (.
     b
     authConfig
     (io.kosong.java/make-object com.google.genai.types.AuthConfig v)))
   (clojure.core/when-some
    [v (:elastic-search-params data)]
    (.
     b
     elasticSearchParams
     (io.kosong.java/make-object
      com.google.genai.types.ExternalApiElasticSearchParams
      v)))
   (clojure.core/when-some [v (:endpoint data)] (. b endpoint v))
   (clojure.core/when-some
    [v (:simple-search-params data)]
    (.
     b
     simpleSearchParams
     (io.kosong.java/make-object
      com.google.genai.types.ExternalApiSimpleSearchParams
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ExternalApi
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :api-auth (. x apiAuth))
   (io.kosong.autovalue/optional-datafy-assoc :api-spec (. x apiSpec))
   (io.kosong.autovalue/optional-datafy-assoc
    :auth-config
    (. x authConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :elastic-search-params
    (. x elasticSearchParams))
   (io.kosong.autovalue/optional-datafy-assoc :endpoint (. x endpoint))
   (io.kosong.autovalue/optional-datafy-assoc
    :simple-search-params
    (. x simpleSearchParams))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ExternalApiElasticSearchParams
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ExternalApiElasticSearchParams
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ExternalApiElasticSearchParams/builder)]
   (clojure.core/when-some [v (:index data)] (. b index v))
   (clojure.core/when-some [v (:num-hits data)] (. b numHits v))
   (clojure.core/when-some
    [v (:search-template data)]
    (. b searchTemplate v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ExternalApiElasticSearchParams
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :index (. x index))
   (io.kosong.autovalue/optional-datafy-assoc :num-hits (. x numHits))
   (io.kosong.autovalue/optional-datafy-assoc
    :search-template
    (. x searchTemplate))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ExternalApiSimpleSearchParams
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ExternalApiSimpleSearchParams
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ExternalApiSimpleSearchParams/builder)]
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ExternalApiSimpleSearchParams
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FetchPredictOperationConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.FetchPredictOperationConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FetchPredictOperationConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FetchPredictOperationConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FetchPredictOperationParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.FetchPredictOperationParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FetchPredictOperationParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.FetchPredictOperationConfig
      v)))
   (clojure.core/when-some
    [v (:operation-name data)]
    (. b operationName v))
   (clojure.core/when-some
    [v (:resource-name data)]
    (. b resourceName v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FetchPredictOperationParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc
    :operation-name
    (. x operationName))
   (io.kosong.autovalue/optional-datafy-assoc
    :resource-name
    (. x resourceName))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.File
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.File data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.File/builder)]
   (clojure.core/when-some [v (:create-time data)] (. b createTime v))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some
    [v (:download-uri data)]
    (. b downloadUri v))
   (clojure.core/when-some
    [v (:error data)]
    (.
     b
     error
     (io.kosong.java/make-object com.google.genai.types.FileStatus v)))
   (clojure.core/when-some
    [v (:expiration-time data)]
    (. b expirationTime v))
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some [v (:sha-256-hash data)] (. b sha256Hash v))
   (clojure.core/when-some [v (:size-bytes data)] (. b sizeBytes v))
   (clojure.core/when-some [v (:source data)] (. b source v))
   (clojure.core/when-some [v (:state data)] (. b state v))
   (clojure.core/when-some [v (:update-time data)] (. b updateTime v))
   (clojure.core/when-some [v (:uri data)] (. b uri v))
   (clojure.core/when-some
    [v (:video-metadata data)]
    (. b videoMetadata v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.File
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :create-time
    (. x createTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :download-uri
    (. x downloadUri))
   (io.kosong.autovalue/optional-datafy-assoc :error (. x error))
   (io.kosong.autovalue/optional-datafy-assoc
    :expiration-time
    (. x expirationTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :sha-256-hash
    (. x sha256Hash))
   (io.kosong.autovalue/optional-datafy-assoc
    :size-bytes
    (. x sizeBytes))
   (io.kosong.autovalue/optional-datafy-assoc :source (. x source))
   (io.kosong.autovalue/optional-datafy-assoc :state (. x state))
   (io.kosong.autovalue/optional-datafy-assoc
    :update-time
    (. x updateTime))
   (io.kosong.autovalue/optional-datafy-assoc :uri (. x uri))
   (io.kosong.autovalue/optional-datafy-assoc
    :video-metadata
    (. x videoMetadata))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FileData
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.FileData data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FileData/builder)]
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some [v (:file-uri data)] (. b fileUri v))
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FileData
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc :file-uri (. x fileUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FileSearch
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.FileSearch data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FileSearch/builder)]
   (clojure.core/when-some
    [v (:file-search-store-names data)]
    (. b fileSearchStoreNames v))
   (clojure.core/when-some
    [v (:metadata-filter data)]
    (. b metadataFilter v))
   (clojure.core/when-some [v (:top-k data)] (. b topK v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FileSearch
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :file-search-store-names
    (. x fileSearchStoreNames))
   (io.kosong.autovalue/optional-datafy-assoc
    :metadata-filter
    (. x metadataFilter))
   (io.kosong.autovalue/optional-datafy-assoc :top-k (. x topK))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FileSearchStore
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.FileSearchStore data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FileSearchStore/builder)]
   (clojure.core/when-some
    [v (:active-documents-count data)]
    (. b activeDocumentsCount v))
   (clojure.core/when-some [v (:create-time data)] (. b createTime v))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some
    [v (:failed-documents-count data)]
    (. b failedDocumentsCount v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:pending-documents-count data)]
    (. b pendingDocumentsCount v))
   (clojure.core/when-some [v (:size-bytes data)] (. b sizeBytes v))
   (clojure.core/when-some [v (:update-time data)] (. b updateTime v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FileSearchStore
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :active-documents-count
    (. x activeDocumentsCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :create-time
    (. x createTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :failed-documents-count
    (. x failedDocumentsCount))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :pending-documents-count
    (. x pendingDocumentsCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :size-bytes
    (. x sizeBytes))
   (io.kosong.autovalue/optional-datafy-assoc
    :update-time
    (. x updateTime))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FileStatus
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.FileStatus data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FileStatus/builder)]
   (clojure.core/when-some [v (:code data)] (. b code v))
   (clojure.core/when-some [v (:details data)] (. b details v))
   (clojure.core/when-some [v (:message data)] (. b message v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FileStatus
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :code (. x code))
   (io.kosong.autovalue/optional-datafy-assoc :details (. x details))
   (io.kosong.autovalue/optional-datafy-assoc :message (. x message))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FunctionCall
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.FunctionCall data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FunctionCall/builder)]
   (clojure.core/when-some [v (:args data)] (. b args v))
   (clojure.core/when-some [v (:id data)] (. b id v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:partial-args data)]
    (.
     b
     partialArgs
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.PartialArg
        x))
      v)))
   (clojure.core/when-some
    [v (:will-continue data)]
    (. b willContinue v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FunctionCall
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :args (. x args))
   (io.kosong.autovalue/optional-datafy-assoc :id (. x id))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :partial-args
    (. x partialArgs))
   (io.kosong.autovalue/optional-datafy-assoc
    :will-continue
    (. x willContinue))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FunctionCallingConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.FunctionCallingConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FunctionCallingConfig/builder)]
   (clojure.core/when-some
    [v (:allowed-function-names data)]
    (. b allowedFunctionNames v))
   (clojure.core/when-some [v (:mode data)] (. b mode v))
   (clojure.core/when-some
    [v (:stream-function-call-arguments data)]
    (. b streamFunctionCallArguments v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FunctionCallingConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :allowed-function-names
    (. x allowedFunctionNames))
   (io.kosong.autovalue/optional-datafy-assoc :mode (. x mode))
   (io.kosong.autovalue/optional-datafy-assoc
    :stream-function-call-arguments
    (. x streamFunctionCallArguments))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FunctionDeclaration
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.FunctionDeclaration
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FunctionDeclaration/builder)]
   (clojure.core/when-some [v (:behavior data)] (. b behavior v))
   (clojure.core/when-some [v (:description data)] (. b description v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:parameters data)]
    (.
     b
     parameters
     (io.kosong.java/make-object com.google.genai.types.Schema v)))
   (clojure.core/when-some
    [v (:parameters-json-schema data)]
    (. b parametersJsonSchema v))
   (clojure.core/when-some
    [v (:response data)]
    (.
     b
     response
     (io.kosong.java/make-object com.google.genai.types.Schema v)))
   (clojure.core/when-some
    [v (:response-json-schema data)]
    (. b responseJsonSchema v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FunctionDeclaration
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :behavior (. x behavior))
   (io.kosong.autovalue/optional-datafy-assoc
    :description
    (. x description))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :parameters
    (. x parameters))
   (io.kosong.autovalue/optional-datafy-assoc
    :parameters-json-schema
    (. x parametersJsonSchema))
   (io.kosong.autovalue/optional-datafy-assoc :response (. x response))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-json-schema
    (. x responseJsonSchema))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FunctionResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.FunctionResponse data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FunctionResponse/builder)]
   (clojure.core/when-some [v (:id data)] (. b id v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:parts data)]
    (.
     b
     parts
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.FunctionResponsePart
        x))
      v)))
   (clojure.core/when-some [v (:response data)] (. b response v))
   (clojure.core/when-some [v (:scheduling data)] (. b scheduling v))
   (clojure.core/when-some
    [v (:will-continue data)]
    (. b willContinue v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FunctionResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :id (. x id))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc :parts (. x parts))
   (io.kosong.autovalue/optional-datafy-assoc :response (. x response))
   (io.kosong.autovalue/optional-datafy-assoc
    :scheduling
    (. x scheduling))
   (io.kosong.autovalue/optional-datafy-assoc
    :will-continue
    (. x willContinue))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FunctionResponseBlob
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.FunctionResponseBlob
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FunctionResponseBlob/builder)]
   (clojure.core/when-some [v (:data data)] (. b data v))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FunctionResponseBlob
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :data (. x data))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FunctionResponseFileData
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.FunctionResponseFileData
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FunctionResponseFileData/builder)]
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some [v (:file-uri data)] (. b fileUri v))
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FunctionResponseFileData
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc :file-uri (. x fileUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.FunctionResponsePart
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.FunctionResponsePart
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.FunctionResponsePart/builder)]
   (clojure.core/when-some
    [v (:file-data data)]
    (.
     b
     fileData
     (io.kosong.java/make-object
      com.google.genai.types.FunctionResponseFileData
      v)))
   (clojure.core/when-some
    [v (:inline-data data)]
    (.
     b
     inlineData
     (io.kosong.java/make-object
      com.google.genai.types.FunctionResponseBlob
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.FunctionResponsePart
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :file-data
    (. x fileData))
   (io.kosong.autovalue/optional-datafy-assoc
    :inline-data
    (. x inlineData))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GcsDestination
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GcsDestination data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GcsDestination/builder)]
   (clojure.core/when-some
    [v (:output-uri-prefix data)]
    (. b outputUriPrefix v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GcsDestination
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :output-uri-prefix
    (. x outputUriPrefix))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GeminiPreferenceExample
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GeminiPreferenceExample
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GeminiPreferenceExample/builder)]
   (clojure.core/when-some
    [v (:completions data)]
    (.
     b
     completions
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GeminiPreferenceExampleCompletion
        x))
      v)))
   (clojure.core/when-some
    [v (:contents data)]
    (.
     b
     contents
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GeminiPreferenceExample
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :completions
    (. x completions))
   (io.kosong.autovalue/optional-datafy-assoc :contents (. x contents))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GeminiPreferenceExampleCompletion
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GeminiPreferenceExampleCompletion
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.GeminiPreferenceExampleCompletion/builder)]
   (clojure.core/when-some
    [v (:completion data)]
    (.
     b
     completion
     (io.kosong.java/make-object com.google.genai.types.Content v)))
   (clojure.core/when-some [v (:score data)] (. b score v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GeminiPreferenceExampleCompletion
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :completion
    (. x completion))
   (io.kosong.autovalue/optional-datafy-assoc :score (. x score))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateContentConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateContentConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateContentConfig/builder)]
   (clojure.core/when-some
    [v (:audio-timestamp data)]
    (. b audioTimestamp v))
   (clojure.core/when-some
    [v (:automatic-function-calling data)]
    (.
     b
     automaticFunctionCalling
     (io.kosong.java/make-object
      com.google.genai.types.AutomaticFunctionCallingConfig
      v)))
   (clojure.core/when-some
    [v (:cached-content data)]
    (. b cachedContent v))
   (clojure.core/when-some
    [v (:candidate-count data)]
    (. b candidateCount v))
   (clojure.core/when-some
    [v (:enable-enhanced-civic-answers data)]
    (. b enableEnhancedCivicAnswers v))
   (clojure.core/when-some
    [v (:frequency-penalty data)]
    (. b frequencyPenalty v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some
    [v (:image-config data)]
    (.
     b
     imageConfig
     (io.kosong.java/make-object
      com.google.genai.types.ImageConfig
      v)))
   (clojure.core/when-some [v (:labels data)] (. b labels v))
   (clojure.core/when-some [v (:logprobs data)] (. b logprobs v))
   (clojure.core/when-some
    [v (:max-output-tokens data)]
    (. b maxOutputTokens v))
   (clojure.core/when-some
    [v (:media-resolution data)]
    (. b mediaResolution v))
   (clojure.core/when-some
    [v (:model-selection-config data)]
    (.
     b
     modelSelectionConfig
     (io.kosong.java/make-object
      com.google.genai.types.ModelSelectionConfig
      v)))
   (clojure.core/when-some
    [v (:presence-penalty data)]
    (. b presencePenalty v))
   (clojure.core/when-some
    [v (:response-json-schema data)]
    (. b responseJsonSchema v))
   (clojure.core/when-some
    [v (:response-logprobs data)]
    (. b responseLogprobs v))
   (clojure.core/when-some
    [v (:response-mime-type data)]
    (. b responseMimeType v))
   (clojure.core/when-some
    [v (:response-modalities data)]
    (. b responseModalities v))
   (clojure.core/when-some
    [v (:response-schema data)]
    (.
     b
     responseSchema
     (io.kosong.java/make-object com.google.genai.types.Schema v)))
   (clojure.core/when-some
    [v (:routing-config data)]
    (.
     b
     routingConfig
     (io.kosong.java/make-object
      com.google.genai.types.GenerationConfigRoutingConfig
      v)))
   (clojure.core/when-some
    [v (:safety-settings data)]
    (.
     b
     safetySettings
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.SafetySetting
        x))
      v)))
   (clojure.core/when-some [v (:seed data)] (. b seed v))
   (clojure.core/when-some
    [v (:should-return-http-response data)]
    (. b shouldReturnHttpResponse v))
   (clojure.core/when-some
    [v (:speech-config data)]
    (.
     b
     speechConfig
     (io.kosong.java/make-object
      com.google.genai.types.SpeechConfig
      v)))
   (clojure.core/when-some
    [v (:stop-sequences data)]
    (. b stopSequences v))
   (clojure.core/when-some
    [v (:system-instruction data)]
    (.
     b
     systemInstruction
     (io.kosong.java/make-object com.google.genai.types.Content v)))
   (clojure.core/when-some [v (:temperature data)] (. b temperature v))
   (clojure.core/when-some
    [v (:thinking-config data)]
    (.
     b
     thinkingConfig
     (io.kosong.java/make-object
      com.google.genai.types.ThinkingConfig
      v)))
   (clojure.core/when-some
    [v (:tool-config data)]
    (.
     b
     toolConfig
     (io.kosong.java/make-object com.google.genai.types.ToolConfig v)))
   (clojure.core/when-some
    [v (:tools data)]
    (.
     b
     tools
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Tool x))
      v)))
   (clojure.core/when-some [v (:top-k data)] (. b topK v))
   (clojure.core/when-some [v (:top-p data)] (. b topP v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateContentConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :audio-timestamp
    (. x audioTimestamp))
   (io.kosong.autovalue/optional-datafy-assoc
    :automatic-function-calling
    (. x automaticFunctionCalling))
   (io.kosong.autovalue/optional-datafy-assoc
    :cached-content
    (. x cachedContent))
   (io.kosong.autovalue/optional-datafy-assoc
    :candidate-count
    (. x candidateCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :enable-enhanced-civic-answers
    (. x enableEnhancedCivicAnswers))
   (io.kosong.autovalue/optional-datafy-assoc
    :frequency-penalty
    (. x frequencyPenalty))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :image-config
    (. x imageConfig))
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc :logprobs (. x logprobs))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-output-tokens
    (. x maxOutputTokens))
   (io.kosong.autovalue/optional-datafy-assoc
    :media-resolution
    (. x mediaResolution))
   (io.kosong.autovalue/optional-datafy-assoc
    :model-selection-config
    (. x modelSelectionConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :presence-penalty
    (. x presencePenalty))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-json-schema
    (. x responseJsonSchema))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-logprobs
    (. x responseLogprobs))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-mime-type
    (. x responseMimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-modalities
    (. x responseModalities))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-schema
    (. x responseSchema))
   (io.kosong.autovalue/optional-datafy-assoc
    :routing-config
    (. x routingConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :safety-settings
    (. x safetySettings))
   (io.kosong.autovalue/optional-datafy-assoc :seed (. x seed))
   (io.kosong.autovalue/optional-datafy-assoc
    :should-return-http-response
    (. x shouldReturnHttpResponse))
   (io.kosong.autovalue/optional-datafy-assoc
    :speech-config
    (. x speechConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :stop-sequences
    (. x stopSequences))
   (io.kosong.autovalue/optional-datafy-assoc
    :system-instruction
    (. x systemInstruction))
   (io.kosong.autovalue/optional-datafy-assoc
    :temperature
    (. x temperature))
   (io.kosong.autovalue/optional-datafy-assoc
    :thinking-config
    (. x thinkingConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :tool-config
    (. x toolConfig))
   (io.kosong.autovalue/optional-datafy-assoc :tools (. x tools))
   (io.kosong.autovalue/optional-datafy-assoc :top-k (. x topK))
   (io.kosong.autovalue/optional-datafy-assoc :top-p (. x topP))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateContentParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateContentParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateContentParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GenerateContentConfig
      v)))
   (clojure.core/when-some
    [v (:contents data)]
    (.
     b
     contents
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateContentParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :contents (. x contents))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateContentResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateContentResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateContentResponse/builder)]
   (clojure.core/when-some
    [v (:automatic-function-calling-history data)]
    (.
     b
     automaticFunctionCallingHistory
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (clojure.core/when-some
    [v (:candidates data)]
    (.
     b
     candidates
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Candidate x))
      v)))
   (clojure.core/when-some [v (:create-time data)] (. b createTime v))
   (clojure.core/when-some
    [v (:model-version data)]
    (. b modelVersion v))
   (clojure.core/when-some
    [v (:prompt-feedback data)]
    (.
     b
     promptFeedback
     (io.kosong.java/make-object
      com.google.genai.types.GenerateContentResponsePromptFeedback
      v)))
   (clojure.core/when-some [v (:response-id data)] (. b responseId v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (clojure.core/when-some
    [v (:usage-metadata data)]
    (.
     b
     usageMetadata
     (io.kosong.java/make-object
      com.google.genai.types.GenerateContentResponseUsageMetadata
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateContentResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :automatic-function-calling-history
    (. x automaticFunctionCallingHistory))
   (io.kosong.autovalue/optional-datafy-assoc
    :candidates
    (. x candidates))
   (io.kosong.autovalue/optional-datafy-assoc
    :create-time
    (. x createTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :model-version
    (. x modelVersion))
   (io.kosong.autovalue/optional-datafy-assoc
    :prompt-feedback
    (. x promptFeedback))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-id
    (. x responseId))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (io.kosong.autovalue/optional-datafy-assoc
    :usage-metadata
    (. x usageMetadata))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateContentResponsePromptFeedback
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateContentResponsePromptFeedback
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.GenerateContentResponsePromptFeedback/builder)]
   (clojure.core/when-some
    [v (:block-reason data)]
    (. b blockReason v))
   (clojure.core/when-some
    [v (:block-reason-message data)]
    (. b blockReasonMessage v))
   (clojure.core/when-some
    [v (:safety-ratings data)]
    (.
     b
     safetyRatings
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.SafetyRating
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateContentResponsePromptFeedback
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :block-reason
    (. x blockReason))
   (io.kosong.autovalue/optional-datafy-assoc
    :block-reason-message
    (. x blockReasonMessage))
   (io.kosong.autovalue/optional-datafy-assoc
    :safety-ratings
    (. x safetyRatings))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateContentResponseUsageMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateContentResponseUsageMetadata
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.GenerateContentResponseUsageMetadata/builder)]
   (clojure.core/when-some
    [v (:cache-tokens-details data)]
    (.
     b
     cacheTokensDetails
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ModalityTokenCount
        x))
      v)))
   (clojure.core/when-some
    [v (:cached-content-token-count data)]
    (. b cachedContentTokenCount v))
   (clojure.core/when-some
    [v (:candidates-token-count data)]
    (. b candidatesTokenCount v))
   (clojure.core/when-some
    [v (:candidates-tokens-details data)]
    (.
     b
     candidatesTokensDetails
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ModalityTokenCount
        x))
      v)))
   (clojure.core/when-some
    [v (:prompt-token-count data)]
    (. b promptTokenCount v))
   (clojure.core/when-some
    [v (:prompt-tokens-details data)]
    (.
     b
     promptTokensDetails
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ModalityTokenCount
        x))
      v)))
   (clojure.core/when-some
    [v (:thoughts-token-count data)]
    (. b thoughtsTokenCount v))
   (clojure.core/when-some
    [v (:tool-use-prompt-token-count data)]
    (. b toolUsePromptTokenCount v))
   (clojure.core/when-some
    [v (:tool-use-prompt-tokens-details data)]
    (.
     b
     toolUsePromptTokensDetails
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ModalityTokenCount
        x))
      v)))
   (clojure.core/when-some
    [v (:total-token-count data)]
    (. b totalTokenCount v))
   (clojure.core/when-some
    [v (:traffic-type data)]
    (. b trafficType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateContentResponseUsageMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :cache-tokens-details
    (. x cacheTokensDetails))
   (io.kosong.autovalue/optional-datafy-assoc
    :cached-content-token-count
    (. x cachedContentTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :candidates-token-count
    (. x candidatesTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :candidates-tokens-details
    (. x candidatesTokensDetails))
   (io.kosong.autovalue/optional-datafy-assoc
    :prompt-token-count
    (. x promptTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :prompt-tokens-details
    (. x promptTokensDetails))
   (io.kosong.autovalue/optional-datafy-assoc
    :thoughts-token-count
    (. x thoughtsTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :tool-use-prompt-token-count
    (. x toolUsePromptTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :tool-use-prompt-tokens-details
    (. x toolUsePromptTokensDetails))
   (io.kosong.autovalue/optional-datafy-assoc
    :total-token-count
    (. x totalTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :traffic-type
    (. x trafficType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateImagesConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateImagesConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateImagesConfig/builder)]
   (clojure.core/when-some
    [v (:add-watermark data)]
    (. b addWatermark v))
   (clojure.core/when-some
    [v (:aspect-ratio data)]
    (. b aspectRatio v))
   (clojure.core/when-some
    [v (:enhance-prompt data)]
    (. b enhancePrompt v))
   (clojure.core/when-some
    [v (:guidance-scale data)]
    (. b guidanceScale v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:image-size data)] (. b imageSize v))
   (clojure.core/when-some
    [v (:include-rai-reason data)]
    (. b includeRaiReason v))
   (clojure.core/when-some
    [v (:include-safety-attributes data)]
    (. b includeSafetyAttributes v))
   (clojure.core/when-some [v (:labels data)] (. b labels v))
   (clojure.core/when-some [v (:language data)] (. b language v))
   (clojure.core/when-some
    [v (:negative-prompt data)]
    (. b negativePrompt v))
   (clojure.core/when-some
    [v (:number-of-images data)]
    (. b numberOfImages v))
   (clojure.core/when-some
    [v (:output-compression-quality data)]
    (. b outputCompressionQuality v))
   (clojure.core/when-some
    [v (:output-gcs-uri data)]
    (. b outputGcsUri v))
   (clojure.core/when-some
    [v (:output-mime-type data)]
    (. b outputMimeType v))
   (clojure.core/when-some
    [v (:person-generation data)]
    (. b personGeneration v))
   (clojure.core/when-some
    [v (:safety-filter-level data)]
    (. b safetyFilterLevel v))
   (clojure.core/when-some [v (:seed data)] (. b seed v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateImagesConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :add-watermark
    (. x addWatermark))
   (io.kosong.autovalue/optional-datafy-assoc
    :aspect-ratio
    (. x aspectRatio))
   (io.kosong.autovalue/optional-datafy-assoc
    :enhance-prompt
    (. x enhancePrompt))
   (io.kosong.autovalue/optional-datafy-assoc
    :guidance-scale
    (. x guidanceScale))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :image-size
    (. x imageSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :include-rai-reason
    (. x includeRaiReason))
   (io.kosong.autovalue/optional-datafy-assoc
    :include-safety-attributes
    (. x includeSafetyAttributes))
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc :language (. x language))
   (io.kosong.autovalue/optional-datafy-assoc
    :negative-prompt
    (. x negativePrompt))
   (io.kosong.autovalue/optional-datafy-assoc
    :number-of-images
    (. x numberOfImages))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-compression-quality
    (. x outputCompressionQuality))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-gcs-uri
    (. x outputGcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-mime-type
    (. x outputMimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :person-generation
    (. x personGeneration))
   (io.kosong.autovalue/optional-datafy-assoc
    :safety-filter-level
    (. x safetyFilterLevel))
   (io.kosong.autovalue/optional-datafy-assoc :seed (. x seed))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateImagesParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateImagesParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateImagesParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GenerateImagesConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some [v (:prompt data)] (. b prompt v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateImagesParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc :prompt (. x prompt))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateImagesResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateImagesResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateImagesResponse/builder)]
   (clojure.core/when-some
    [v (:generated-images data)]
    (.
     b
     generatedImages
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GeneratedImage
        x))
      v)))
   (clojure.core/when-some
    [v (:positive-prompt-safety-attributes data)]
    (.
     b
     positivePromptSafetyAttributes
     (io.kosong.java/make-object
      com.google.genai.types.SafetyAttributes
      v)))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateImagesResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :generated-images
    (. x generatedImages))
   (io.kosong.autovalue/optional-datafy-assoc
    :positive-prompt-safety-attributes
    (. x positivePromptSafetyAttributes))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateVideosConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateVideosConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateVideosConfig/builder)]
   (clojure.core/when-some
    [v (:aspect-ratio data)]
    (. b aspectRatio v))
   (clojure.core/when-some
    [v (:compression-quality data)]
    (. b compressionQuality v))
   (clojure.core/when-some
    [v (:duration-seconds data)]
    (. b durationSeconds v))
   (clojure.core/when-some
    [v (:enhance-prompt data)]
    (. b enhancePrompt v))
   (clojure.core/when-some [v (:fps data)] (. b fps v))
   (clojure.core/when-some
    [v (:generate-audio data)]
    (. b generateAudio v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some
    [v (:last-frame data)]
    (.
     b
     lastFrame
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:mask data)]
    (.
     b
     mask
     (io.kosong.java/make-object
      com.google.genai.types.VideoGenerationMask
      v)))
   (clojure.core/when-some
    [v (:negative-prompt data)]
    (. b negativePrompt v))
   (clojure.core/when-some
    [v (:number-of-videos data)]
    (. b numberOfVideos v))
   (clojure.core/when-some
    [v (:output-gcs-uri data)]
    (. b outputGcsUri v))
   (clojure.core/when-some
    [v (:person-generation data)]
    (. b personGeneration v))
   (clojure.core/when-some
    [v (:pubsub-topic data)]
    (. b pubsubTopic v))
   (clojure.core/when-some
    [v (:reference-images data)]
    (.
     b
     referenceImages
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.VideoGenerationReferenceImage
        x))
      v)))
   (clojure.core/when-some [v (:resolution data)] (. b resolution v))
   (clojure.core/when-some [v (:seed data)] (. b seed v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateVideosConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :aspect-ratio
    (. x aspectRatio))
   (io.kosong.autovalue/optional-datafy-assoc
    :compression-quality
    (. x compressionQuality))
   (io.kosong.autovalue/optional-datafy-assoc
    :duration-seconds
    (. x durationSeconds))
   (io.kosong.autovalue/optional-datafy-assoc
    :enhance-prompt
    (. x enhancePrompt))
   (io.kosong.autovalue/optional-datafy-assoc :fps (. x fps))
   (io.kosong.autovalue/optional-datafy-assoc
    :generate-audio
    (. x generateAudio))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :last-frame
    (. x lastFrame))
   (io.kosong.autovalue/optional-datafy-assoc :mask (. x mask))
   (io.kosong.autovalue/optional-datafy-assoc
    :negative-prompt
    (. x negativePrompt))
   (io.kosong.autovalue/optional-datafy-assoc
    :number-of-videos
    (. x numberOfVideos))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-gcs-uri
    (. x outputGcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :person-generation
    (. x personGeneration))
   (io.kosong.autovalue/optional-datafy-assoc
    :pubsub-topic
    (. x pubsubTopic))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-images
    (. x referenceImages))
   (io.kosong.autovalue/optional-datafy-assoc
    :resolution
    (. x resolution))
   (io.kosong.autovalue/optional-datafy-assoc :seed (. x seed))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateVideosOperation
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateVideosOperation
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateVideosOperation/builder)]
   (clojure.core/when-some [v (:done data)] (. b done v))
   (clojure.core/when-some [v (:error data)] (. b error v))
   (clojure.core/when-some [v (:metadata data)] (. b metadata v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:response data)]
    (.
     b
     response
     (io.kosong.java/make-object
      com.google.genai.types.GenerateVideosResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateVideosOperation
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :done (. x done))
   (io.kosong.autovalue/optional-datafy-assoc :error (. x error))
   (io.kosong.autovalue/optional-datafy-assoc :metadata (. x metadata))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc :response (. x response))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateVideosParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateVideosParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateVideosParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GenerateVideosConfig
      v)))
   (clojure.core/when-some
    [v (:image data)]
    (.
     b
     image
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some [v (:prompt data)] (. b prompt v))
   (clojure.core/when-some
    [v (:source data)]
    (.
     b
     source
     (io.kosong.java/make-object
      com.google.genai.types.GenerateVideosSource
      v)))
   (clojure.core/when-some
    [v (:video data)]
    (.
     b
     video
     (io.kosong.java/make-object com.google.genai.types.Video v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateVideosParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :image (. x image))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc :prompt (. x prompt))
   (io.kosong.autovalue/optional-datafy-assoc :source (. x source))
   (io.kosong.autovalue/optional-datafy-assoc :video (. x video))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateVideosResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateVideosResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateVideosResponse/builder)]
   (clojure.core/when-some
    [v (:generated-videos data)]
    (.
     b
     generatedVideos
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GeneratedVideo
        x))
      v)))
   (clojure.core/when-some
    [v (:rai-media-filtered-count data)]
    (. b raiMediaFilteredCount v))
   (clojure.core/when-some
    [v (:rai-media-filtered-reasons data)]
    (. b raiMediaFilteredReasons v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateVideosResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :generated-videos
    (. x generatedVideos))
   (io.kosong.autovalue/optional-datafy-assoc
    :rai-media-filtered-count
    (. x raiMediaFilteredCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :rai-media-filtered-reasons
    (. x raiMediaFilteredReasons))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerateVideosSource
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerateVideosSource
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerateVideosSource/builder)]
   (clojure.core/when-some
    [v (:image data)]
    (.
     b
     image
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some [v (:prompt data)] (. b prompt v))
   (clojure.core/when-some
    [v (:video data)]
    (.
     b
     video
     (io.kosong.java/make-object com.google.genai.types.Video v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerateVideosSource
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :image (. x image))
   (io.kosong.autovalue/optional-datafy-assoc :prompt (. x prompt))
   (io.kosong.autovalue/optional-datafy-assoc :video (. x video))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GeneratedImage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GeneratedImage data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GeneratedImage/builder)]
   (clojure.core/when-some
    [v (:enhanced-prompt data)]
    (. b enhancedPrompt v))
   (clojure.core/when-some
    [v (:image data)]
    (.
     b
     image
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:rai-filtered-reason data)]
    (. b raiFilteredReason v))
   (clojure.core/when-some
    [v (:safety-attributes data)]
    (.
     b
     safetyAttributes
     (io.kosong.java/make-object
      com.google.genai.types.SafetyAttributes
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GeneratedImage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :enhanced-prompt
    (. x enhancedPrompt))
   (io.kosong.autovalue/optional-datafy-assoc :image (. x image))
   (io.kosong.autovalue/optional-datafy-assoc
    :rai-filtered-reason
    (. x raiFilteredReason))
   (io.kosong.autovalue/optional-datafy-assoc
    :safety-attributes
    (. x safetyAttributes))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GeneratedImageMask
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GeneratedImageMask
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GeneratedImageMask/builder)]
   (clojure.core/when-some
    [v (:labels data)]
    (.
     b
     labels
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.EntityLabel
        x))
      v)))
   (clojure.core/when-some
    [v (:mask data)]
    (.
     b
     mask
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GeneratedImageMask
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc :mask (. x mask))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GeneratedVideo
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GeneratedVideo data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GeneratedVideo/builder)]
   (clojure.core/when-some
    [v (:video data)]
    (.
     b
     video
     (io.kosong.java/make-object com.google.genai.types.Video v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GeneratedVideo
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :video (. x video))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerationConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GenerationConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerationConfig/builder)]
   (clojure.core/when-some
    [v (:audio-timestamp data)]
    (. b audioTimestamp v))
   (clojure.core/when-some
    [v (:candidate-count data)]
    (. b candidateCount v))
   (clojure.core/when-some
    [v (:enable-affective-dialog data)]
    (. b enableAffectiveDialog v))
   (clojure.core/when-some
    [v (:enable-enhanced-civic-answers data)]
    (. b enableEnhancedCivicAnswers v))
   (clojure.core/when-some
    [v (:frequency-penalty data)]
    (. b frequencyPenalty v))
   (clojure.core/when-some [v (:logprobs data)] (. b logprobs v))
   (clojure.core/when-some
    [v (:max-output-tokens data)]
    (. b maxOutputTokens v))
   (clojure.core/when-some
    [v (:media-resolution data)]
    (. b mediaResolution v))
   (clojure.core/when-some
    [v (:model-selection-config data)]
    (.
     b
     modelSelectionConfig
     (io.kosong.java/make-object
      com.google.genai.types.ModelSelectionConfig
      v)))
   (clojure.core/when-some
    [v (:presence-penalty data)]
    (. b presencePenalty v))
   (clojure.core/when-some
    [v (:response-json-schema data)]
    (. b responseJsonSchema v))
   (clojure.core/when-some
    [v (:response-logprobs data)]
    (. b responseLogprobs v))
   (clojure.core/when-some
    [v (:response-mime-type data)]
    (. b responseMimeType v))
   (clojure.core/when-some
    [v (:response-modalities data)]
    (. b responseModalities v))
   (clojure.core/when-some
    [v (:response-schema data)]
    (.
     b
     responseSchema
     (io.kosong.java/make-object com.google.genai.types.Schema v)))
   (clojure.core/when-some
    [v (:routing-config data)]
    (.
     b
     routingConfig
     (io.kosong.java/make-object
      com.google.genai.types.GenerationConfigRoutingConfig
      v)))
   (clojure.core/when-some [v (:seed data)] (. b seed v))
   (clojure.core/when-some
    [v (:speech-config data)]
    (.
     b
     speechConfig
     (io.kosong.java/make-object
      com.google.genai.types.SpeechConfig
      v)))
   (clojure.core/when-some
    [v (:stop-sequences data)]
    (. b stopSequences v))
   (clojure.core/when-some [v (:temperature data)] (. b temperature v))
   (clojure.core/when-some
    [v (:thinking-config data)]
    (.
     b
     thinkingConfig
     (io.kosong.java/make-object
      com.google.genai.types.ThinkingConfig
      v)))
   (clojure.core/when-some [v (:top-k data)] (. b topK v))
   (clojure.core/when-some [v (:top-p data)] (. b topP v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerationConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :audio-timestamp
    (. x audioTimestamp))
   (io.kosong.autovalue/optional-datafy-assoc
    :candidate-count
    (. x candidateCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :enable-affective-dialog
    (. x enableAffectiveDialog))
   (io.kosong.autovalue/optional-datafy-assoc
    :enable-enhanced-civic-answers
    (. x enableEnhancedCivicAnswers))
   (io.kosong.autovalue/optional-datafy-assoc
    :frequency-penalty
    (. x frequencyPenalty))
   (io.kosong.autovalue/optional-datafy-assoc :logprobs (. x logprobs))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-output-tokens
    (. x maxOutputTokens))
   (io.kosong.autovalue/optional-datafy-assoc
    :media-resolution
    (. x mediaResolution))
   (io.kosong.autovalue/optional-datafy-assoc
    :model-selection-config
    (. x modelSelectionConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :presence-penalty
    (. x presencePenalty))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-json-schema
    (. x responseJsonSchema))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-logprobs
    (. x responseLogprobs))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-mime-type
    (. x responseMimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-modalities
    (. x responseModalities))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-schema
    (. x responseSchema))
   (io.kosong.autovalue/optional-datafy-assoc
    :routing-config
    (. x routingConfig))
   (io.kosong.autovalue/optional-datafy-assoc :seed (. x seed))
   (io.kosong.autovalue/optional-datafy-assoc
    :speech-config
    (. x speechConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :stop-sequences
    (. x stopSequences))
   (io.kosong.autovalue/optional-datafy-assoc
    :temperature
    (. x temperature))
   (io.kosong.autovalue/optional-datafy-assoc
    :thinking-config
    (. x thinkingConfig))
   (io.kosong.autovalue/optional-datafy-assoc :top-k (. x topK))
   (io.kosong.autovalue/optional-datafy-assoc :top-p (. x topP))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerationConfigRoutingConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerationConfigRoutingConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GenerationConfigRoutingConfig/builder)]
   (clojure.core/when-some
    [v (:auto-mode data)]
    (.
     b
     autoMode
     (io.kosong.java/make-object
      com.google.genai.types.GenerationConfigRoutingConfigAutoRoutingMode
      v)))
   (clojure.core/when-some
    [v (:manual-mode data)]
    (.
     b
     manualMode
     (io.kosong.java/make-object
      com.google.genai.types.GenerationConfigRoutingConfigManualRoutingMode
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerationConfigRoutingConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :auto-mode
    (. x autoMode))
   (io.kosong.autovalue/optional-datafy-assoc
    :manual-mode
    (. x manualMode))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerationConfigRoutingConfigAutoRoutingMode
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerationConfigRoutingConfigAutoRoutingMode
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.GenerationConfigRoutingConfigAutoRoutingMode/builder)]
   (clojure.core/when-some
    [v (:model-routing-preference data)]
    (. b modelRoutingPreference v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerationConfigRoutingConfigAutoRoutingMode
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :model-routing-preference
    (. x modelRoutingPreference))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GenerationConfigRoutingConfigManualRoutingMode
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GenerationConfigRoutingConfigManualRoutingMode
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.GenerationConfigRoutingConfigManualRoutingMode/builder)]
   (clojure.core/when-some [v (:model-name data)] (. b modelName v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GenerationConfigRoutingConfigManualRoutingMode
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :model-name
    (. x modelName))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetBatchJobConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetBatchJobConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetBatchJobConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetBatchJobConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetBatchJobParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetBatchJobParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetBatchJobParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GetBatchJobConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetBatchJobParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetCachedContentConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetCachedContentConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetCachedContentConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetCachedContentConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetCachedContentParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetCachedContentParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetCachedContentParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GetCachedContentConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetCachedContentParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetDocumentConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetDocumentConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetDocumentConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetDocumentConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetDocumentParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetDocumentParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetDocumentParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GetDocumentConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetDocumentParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetFileConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GetFileConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetFileConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetFileConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetFileParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetFileParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetFileParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GetFileConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetFileParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetFileSearchStoreConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetFileSearchStoreConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetFileSearchStoreConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetFileSearchStoreConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetFileSearchStoreParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetFileSearchStoreParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetFileSearchStoreParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GetFileSearchStoreConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetFileSearchStoreParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetModelConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GetModelConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetModelConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetModelConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetModelParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetModelParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetModelParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GetModelConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetModelParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetOperationConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetOperationConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetOperationConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetOperationConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetOperationParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetOperationParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetOperationParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GetOperationConfig
      v)))
   (clojure.core/when-some
    [v (:operation-name data)]
    (. b operationName v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetOperationParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc
    :operation-name
    (. x operationName))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetTuningJobConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetTuningJobConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetTuningJobConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetTuningJobConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GetTuningJobParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GetTuningJobParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GetTuningJobParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GetTuningJobConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GetTuningJobParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GoogleMaps
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GoogleMaps data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GoogleMaps/builder)]
   (clojure.core/when-some
    [v (:auth-config data)]
    (.
     b
     authConfig
     (io.kosong.java/make-object com.google.genai.types.AuthConfig v)))
   (clojure.core/when-some
    [v (:enable-widget data)]
    (. b enableWidget v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GoogleMaps
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :auth-config
    (. x authConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :enable-widget
    (. x enableWidget))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GoogleRpcStatus
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GoogleRpcStatus data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GoogleRpcStatus/builder)]
   (clojure.core/when-some [v (:code data)] (. b code v))
   (clojure.core/when-some [v (:details data)] (. b details v))
   (clojure.core/when-some [v (:message data)] (. b message v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GoogleRpcStatus
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :code (. x code))
   (io.kosong.autovalue/optional-datafy-assoc :details (. x details))
   (io.kosong.autovalue/optional-datafy-assoc :message (. x message))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GoogleSearch
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GoogleSearch data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GoogleSearch/builder)]
   (clojure.core/when-some
    [v (:blocking-confidence data)]
    (. b blockingConfidence v))
   (clojure.core/when-some
    [v (:exclude-domains data)]
    (. b excludeDomains v))
   (clojure.core/when-some
    [v (:time-range-filter data)]
    (.
     b
     timeRangeFilter
     (io.kosong.java/make-object com.google.genai.types.Interval v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GoogleSearch
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :blocking-confidence
    (. x blockingConfidence))
   (io.kosong.autovalue/optional-datafy-assoc
    :exclude-domains
    (. x excludeDomains))
   (io.kosong.autovalue/optional-datafy-assoc
    :time-range-filter
    (. x timeRangeFilter))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GoogleSearchRetrieval
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GoogleSearchRetrieval
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GoogleSearchRetrieval/builder)]
   (clojure.core/when-some
    [v (:dynamic-retrieval-config data)]
    (.
     b
     dynamicRetrievalConfig
     (io.kosong.java/make-object
      com.google.genai.types.DynamicRetrievalConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GoogleSearchRetrieval
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :dynamic-retrieval-config
    (. x dynamicRetrievalConfig))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GoogleTypeDate
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GoogleTypeDate data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GoogleTypeDate/builder)]
   (clojure.core/when-some [v (:day data)] (. b day v))
   (clojure.core/when-some [v (:month data)] (. b month v))
   (clojure.core/when-some [v (:year data)] (. b year v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GoogleTypeDate
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :day (. x day))
   (io.kosong.autovalue/optional-datafy-assoc :month (. x month))
   (io.kosong.autovalue/optional-datafy-assoc :year (. x year))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GroundingChunk
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GroundingChunk data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GroundingChunk/builder)]
   (clojure.core/when-some
    [v (:maps data)]
    (.
     b
     maps
     (io.kosong.java/make-object
      com.google.genai.types.GroundingChunkMaps
      v)))
   (clojure.core/when-some
    [v (:retrieved-context data)]
    (.
     b
     retrievedContext
     (io.kosong.java/make-object
      com.google.genai.types.GroundingChunkRetrievedContext
      v)))
   (clojure.core/when-some
    [v (:web data)]
    (.
     b
     web
     (io.kosong.java/make-object
      com.google.genai.types.GroundingChunkWeb
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GroundingChunk
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :maps (. x maps))
   (io.kosong.autovalue/optional-datafy-assoc
    :retrieved-context
    (. x retrievedContext))
   (io.kosong.autovalue/optional-datafy-assoc :web (. x web))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GroundingChunkMaps
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GroundingChunkMaps
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GroundingChunkMaps/builder)]
   (clojure.core/when-some
    [v (:place-answer-sources data)]
    (.
     b
     placeAnswerSources
     (io.kosong.java/make-object
      com.google.genai.types.GroundingChunkMapsPlaceAnswerSources
      v)))
   (clojure.core/when-some [v (:place-id data)] (. b placeId v))
   (clojure.core/when-some [v (:text data)] (. b text v))
   (clojure.core/when-some [v (:title data)] (. b title v))
   (clojure.core/when-some [v (:uri data)] (. b uri v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GroundingChunkMaps
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :place-answer-sources
    (. x placeAnswerSources))
   (io.kosong.autovalue/optional-datafy-assoc :place-id (. x placeId))
   (io.kosong.autovalue/optional-datafy-assoc :text (. x text))
   (io.kosong.autovalue/optional-datafy-assoc :title (. x title))
   (io.kosong.autovalue/optional-datafy-assoc :uri (. x uri))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GroundingChunkMapsPlaceAnswerSources
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GroundingChunkMapsPlaceAnswerSources
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.GroundingChunkMapsPlaceAnswerSources/builder)]
   (clojure.core/when-some
    [v (:flag-content-uri data)]
    (. b flagContentUri v))
   (clojure.core/when-some
    [v (:review-snippets data)]
    (.
     b
     reviewSnippets
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GroundingChunkMapsPlaceAnswerSourcesReviewSnippet
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GroundingChunkMapsPlaceAnswerSources
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :flag-content-uri
    (. x flagContentUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :review-snippets
    (. x reviewSnippets))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution/builder)]
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some [v (:photo-uri data)] (. b photoUri v))
   (clojure.core/when-some [v (:uri data)] (. b uri v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :photo-uri
    (. x photoUri))
   (io.kosong.autovalue/optional-datafy-assoc :uri (. x uri))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GroundingChunkMapsPlaceAnswerSourcesReviewSnippet
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GroundingChunkMapsPlaceAnswerSourcesReviewSnippet
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.GroundingChunkMapsPlaceAnswerSourcesReviewSnippet/builder)]
   (clojure.core/when-some
    [v (:author-attribution data)]
    (.
     b
     authorAttribution
     (io.kosong.java/make-object
      com.google.genai.types.GroundingChunkMapsPlaceAnswerSourcesAuthorAttribution
      v)))
   (clojure.core/when-some
    [v (:flag-content-uri data)]
    (. b flagContentUri v))
   (clojure.core/when-some
    [v (:google-maps-uri data)]
    (. b googleMapsUri v))
   (clojure.core/when-some
    [v (:relative-publish-time-description data)]
    (. b relativePublishTimeDescription v))
   (clojure.core/when-some [v (:review data)] (. b review v))
   (clojure.core/when-some [v (:review-id data)] (. b reviewId v))
   (clojure.core/when-some [v (:title data)] (. b title v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GroundingChunkMapsPlaceAnswerSourcesReviewSnippet
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :author-attribution
    (. x authorAttribution))
   (io.kosong.autovalue/optional-datafy-assoc
    :flag-content-uri
    (. x flagContentUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :google-maps-uri
    (. x googleMapsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :relative-publish-time-description
    (. x relativePublishTimeDescription))
   (io.kosong.autovalue/optional-datafy-assoc :review (. x review))
   (io.kosong.autovalue/optional-datafy-assoc
    :review-id
    (. x reviewId))
   (io.kosong.autovalue/optional-datafy-assoc :title (. x title))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GroundingChunkRetrievedContext
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GroundingChunkRetrievedContext
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GroundingChunkRetrievedContext/builder)]
   (clojure.core/when-some
    [v (:document-name data)]
    (. b documentName v))
   (clojure.core/when-some
    [v (:rag-chunk data)]
    (.
     b
     ragChunk
     (io.kosong.java/make-object com.google.genai.types.RagChunk v)))
   (clojure.core/when-some [v (:text data)] (. b text v))
   (clojure.core/when-some [v (:title data)] (. b title v))
   (clojure.core/when-some [v (:uri data)] (. b uri v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GroundingChunkRetrievedContext
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :document-name
    (. x documentName))
   (io.kosong.autovalue/optional-datafy-assoc
    :rag-chunk
    (. x ragChunk))
   (io.kosong.autovalue/optional-datafy-assoc :text (. x text))
   (io.kosong.autovalue/optional-datafy-assoc :title (. x title))
   (io.kosong.autovalue/optional-datafy-assoc :uri (. x uri))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GroundingChunkWeb
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GroundingChunkWeb
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GroundingChunkWeb/builder)]
   (clojure.core/when-some [v (:domain data)] (. b domain v))
   (clojure.core/when-some [v (:title data)] (. b title v))
   (clojure.core/when-some [v (:uri data)] (. b uri v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GroundingChunkWeb
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :domain (. x domain))
   (io.kosong.autovalue/optional-datafy-assoc :title (. x title))
   (io.kosong.autovalue/optional-datafy-assoc :uri (. x uri))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GroundingMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GroundingMetadata
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GroundingMetadata/builder)]
   (clojure.core/when-some
    [v (:google-maps-widget-context-token data)]
    (. b googleMapsWidgetContextToken v))
   (clojure.core/when-some
    [v (:grounding-chunks data)]
    (.
     b
     groundingChunks
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GroundingChunk
        x))
      v)))
   (clojure.core/when-some
    [v (:grounding-supports data)]
    (.
     b
     groundingSupports
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GroundingSupport
        x))
      v)))
   (clojure.core/when-some
    [v (:retrieval-metadata data)]
    (.
     b
     retrievalMetadata
     (io.kosong.java/make-object
      com.google.genai.types.RetrievalMetadata
      v)))
   (clojure.core/when-some
    [v (:retrieval-queries data)]
    (. b retrievalQueries v))
   (clojure.core/when-some
    [v (:search-entry-point data)]
    (.
     b
     searchEntryPoint
     (io.kosong.java/make-object
      com.google.genai.types.SearchEntryPoint
      v)))
   (clojure.core/when-some
    [v (:source-flagging-uris data)]
    (.
     b
     sourceFlaggingUris
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GroundingMetadataSourceFlaggingUri
        x))
      v)))
   (clojure.core/when-some
    [v (:web-search-queries data)]
    (. b webSearchQueries v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GroundingMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :google-maps-widget-context-token
    (. x googleMapsWidgetContextToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :grounding-chunks
    (. x groundingChunks))
   (io.kosong.autovalue/optional-datafy-assoc
    :grounding-supports
    (. x groundingSupports))
   (io.kosong.autovalue/optional-datafy-assoc
    :retrieval-metadata
    (. x retrievalMetadata))
   (io.kosong.autovalue/optional-datafy-assoc
    :retrieval-queries
    (. x retrievalQueries))
   (io.kosong.autovalue/optional-datafy-assoc
    :search-entry-point
    (. x searchEntryPoint))
   (io.kosong.autovalue/optional-datafy-assoc
    :source-flagging-uris
    (. x sourceFlaggingUris))
   (io.kosong.autovalue/optional-datafy-assoc
    :web-search-queries
    (. x webSearchQueries))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GroundingMetadataSourceFlaggingUri
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.GroundingMetadataSourceFlaggingUri
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.GroundingMetadataSourceFlaggingUri/builder)]
   (clojure.core/when-some
    [v (:flag-content-uri data)]
    (. b flagContentUri v))
   (clojure.core/when-some [v (:source-id data)] (. b sourceId v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GroundingMetadataSourceFlaggingUri
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :flag-content-uri
    (. x flagContentUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :source-id
    (. x sourceId))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.GroundingSupport
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.GroundingSupport data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.GroundingSupport/builder)]
   (clojure.core/when-some
    [v (:confidence-scores data)]
    (. b confidenceScores v))
   (clojure.core/when-some
    [v (:grounding-chunk-indices data)]
    (. b groundingChunkIndices v))
   (clojure.core/when-some
    [v (:segment data)]
    (.
     b
     segment
     (io.kosong.java/make-object com.google.genai.types.Segment v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.GroundingSupport
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :confidence-scores
    (. x confidenceScores))
   (io.kosong.autovalue/optional-datafy-assoc
    :grounding-chunk-indices
    (. x groundingChunkIndices))
   (io.kosong.autovalue/optional-datafy-assoc :segment (. x segment))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.HttpOptions
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.HttpOptions data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.HttpOptions/builder)]
   (clojure.core/when-some [v (:api-version data)] (. b apiVersion v))
   (clojure.core/when-some [v (:base-url data)] (. b baseUrl v))
   (clojure.core/when-some [v (:extra-body data)] (. b extraBody v))
   (clojure.core/when-some [v (:headers data)] (. b headers v))
   (clojure.core/when-some
    [v (:retry-options data)]
    (.
     b
     retryOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpRetryOptions
      v)))
   (clojure.core/when-some [v (:timeout data)] (. b timeout v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.HttpOptions
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :api-version
    (. x apiVersion))
   (io.kosong.autovalue/optional-datafy-assoc :base-url (. x baseUrl))
   (io.kosong.autovalue/optional-datafy-assoc
    :extra-body
    (. x extraBody))
   (io.kosong.autovalue/optional-datafy-assoc :headers (. x headers))
   (io.kosong.autovalue/optional-datafy-assoc
    :retry-options
    (. x retryOptions))
   (io.kosong.autovalue/optional-datafy-assoc :timeout (. x timeout))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.HttpResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.HttpResponse data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.HttpResponse/builder)]
   (clojure.core/when-some [v (:body data)] (. b body v))
   (clojure.core/when-some [v (:headers data)] (. b headers v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.HttpResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :body (. x body))
   (io.kosong.autovalue/optional-datafy-assoc :headers (. x headers))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.HttpRetryOptions
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.HttpRetryOptions data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.HttpRetryOptions/builder)]
   (clojure.core/when-some [v (:attempts data)] (. b attempts v))
   (clojure.core/when-some [v (:exp-base data)] (. b expBase v))
   (clojure.core/when-some
    [v (:http-status-codes data)]
    (. b httpStatusCodes v))
   (clojure.core/when-some
    [v (:initial-delay data)]
    (. b initialDelay v))
   (clojure.core/when-some [v (:jitter data)] (. b jitter v))
   (clojure.core/when-some [v (:max-delay data)] (. b maxDelay v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.HttpRetryOptions
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :attempts (. x attempts))
   (io.kosong.autovalue/optional-datafy-assoc :exp-base (. x expBase))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-status-codes
    (. x httpStatusCodes))
   (io.kosong.autovalue/optional-datafy-assoc
    :initial-delay
    (. x initialDelay))
   (io.kosong.autovalue/optional-datafy-assoc :jitter (. x jitter))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-delay
    (. x maxDelay))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Image
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Image data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Image/builder)]
   (clojure.core/when-some [v (:gcs-uri data)] (. b gcsUri v))
   (clojure.core/when-some [v (:image-bytes data)] (. b imageBytes v))
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Image
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :gcs-uri (. x gcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :image-bytes
    (. x imageBytes))
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ImageConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ImageConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ImageConfig/builder)]
   (clojure.core/when-some
    [v (:aspect-ratio data)]
    (. b aspectRatio v))
   (clojure.core/when-some [v (:image-size data)] (. b imageSize v))
   (clojure.core/when-some
    [v (:output-compression-quality data)]
    (. b outputCompressionQuality v))
   (clojure.core/when-some
    [v (:output-mime-type data)]
    (. b outputMimeType v))
   (clojure.core/when-some
    [v (:person-generation data)]
    (. b personGeneration v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ImageConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :aspect-ratio
    (. x aspectRatio))
   (io.kosong.autovalue/optional-datafy-assoc
    :image-size
    (. x imageSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-compression-quality
    (. x outputCompressionQuality))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-mime-type
    (. x outputMimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :person-generation
    (. x personGeneration))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ImportFileConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ImportFileConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ImportFileConfig/builder)]
   (clojure.core/when-some
    [v (:chunking-config data)]
    (.
     b
     chunkingConfig
     (io.kosong.java/make-object
      com.google.genai.types.ChunkingConfig
      v)))
   (clojure.core/when-some
    [v (:custom-metadata data)]
    (.
     b
     customMetadata
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.CustomMetadata
        x))
      v)))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ImportFileConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :chunking-config
    (. x chunkingConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :custom-metadata
    (. x customMetadata))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ImportFileOperation
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ImportFileOperation
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ImportFileOperation/builder)]
   (clojure.core/when-some [v (:done data)] (. b done v))
   (clojure.core/when-some [v (:error data)] (. b error v))
   (clojure.core/when-some [v (:metadata data)] (. b metadata v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:response data)]
    (.
     b
     response
     (io.kosong.java/make-object
      com.google.genai.types.ImportFileResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ImportFileOperation
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :done (. x done))
   (io.kosong.autovalue/optional-datafy-assoc :error (. x error))
   (io.kosong.autovalue/optional-datafy-assoc :metadata (. x metadata))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc :response (. x response))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ImportFileParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ImportFileParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ImportFileParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.ImportFileConfig
      v)))
   (clojure.core/when-some [v (:file-name data)] (. b fileName v))
   (clojure.core/when-some
    [v (:file-search-store-name data)]
    (. b fileSearchStoreName v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ImportFileParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc
    :file-name
    (. x fileName))
   (io.kosong.autovalue/optional-datafy-assoc
    :file-search-store-name
    (. x fileSearchStoreName))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ImportFileResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ImportFileResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ImportFileResponse/builder)]
   (clojure.core/when-some
    [v (:document-name data)]
    (. b documentName v))
   (clojure.core/when-some [v (:parent data)] (. b parent v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ImportFileResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :document-name
    (. x documentName))
   (io.kosong.autovalue/optional-datafy-assoc :parent (. x parent))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.InlinedEmbedContentResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.InlinedEmbedContentResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.InlinedEmbedContentResponse/builder)]
   (clojure.core/when-some
    [v (:error data)]
    (.
     b
     error
     (io.kosong.java/make-object com.google.genai.types.JobError v)))
   (clojure.core/when-some
    [v (:response data)]
    (.
     b
     response
     (io.kosong.java/make-object
      com.google.genai.types.SingleEmbedContentResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.InlinedEmbedContentResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :error (. x error))
   (io.kosong.autovalue/optional-datafy-assoc :response (. x response))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.InlinedRequest
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.InlinedRequest data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.InlinedRequest/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.GenerateContentConfig
      v)))
   (clojure.core/when-some
    [v (:contents data)]
    (.
     b
     contents
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (clojure.core/when-some [v (:metadata data)] (. b metadata v))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.InlinedRequest
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :contents (. x contents))
   (io.kosong.autovalue/optional-datafy-assoc :metadata (. x metadata))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.InlinedResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.InlinedResponse data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.InlinedResponse/builder)]
   (clojure.core/when-some
    [v (:error data)]
    (.
     b
     error
     (io.kosong.java/make-object com.google.genai.types.JobError v)))
   (clojure.core/when-some
    [v (:response data)]
    (.
     b
     response
     (io.kosong.java/make-object
      com.google.genai.types.GenerateContentResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.InlinedResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :error (. x error))
   (io.kosong.autovalue/optional-datafy-assoc :response (. x response))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Interval
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Interval data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Interval/builder)]
   (clojure.core/when-some [v (:end-time data)] (. b endTime v))
   (clojure.core/when-some [v (:start-time data)] (. b startTime v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Interval
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :end-time (. x endTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :start-time
    (. x startTime))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.JobError
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.JobError data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.JobError/builder)]
   (clojure.core/when-some [v (:code data)] (. b code v))
   (clojure.core/when-some [v (:details data)] (. b details v))
   (clojure.core/when-some [v (:message data)] (. b message v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.JobError
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :code (. x code))
   (io.kosong.autovalue/optional-datafy-assoc :details (. x details))
   (io.kosong.autovalue/optional-datafy-assoc :message (. x message))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LatLng
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.LatLng data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LatLng/builder)]
   (clojure.core/when-some [v (:latitude data)] (. b latitude v))
   (clojure.core/when-some [v (:longitude data)] (. b longitude v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LatLng
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :latitude (. x latitude))
   (io.kosong.autovalue/optional-datafy-assoc
    :longitude
    (. x longitude))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListBatchJobsConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListBatchJobsConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListBatchJobsConfig/builder)]
   (clojure.core/when-some [v (:filter data)] (. b filter v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:page-size data)] (. b pageSize v))
   (clojure.core/when-some [v (:page-token data)] (. b pageToken v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListBatchJobsConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :filter (. x filter))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-size
    (. x pageSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-token
    (. x pageToken))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListBatchJobsParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListBatchJobsParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListBatchJobsParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.ListBatchJobsConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListBatchJobsParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListBatchJobsResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListBatchJobsResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListBatchJobsResponse/builder)]
   (clojure.core/when-some
    [v (:batch-jobs data)]
    (.
     b
     batchJobs
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.BatchJob x))
      v)))
   (clojure.core/when-some
    [v (:next-page-token data)]
    (. b nextPageToken v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListBatchJobsResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :batch-jobs
    (. x batchJobs))
   (io.kosong.autovalue/optional-datafy-assoc
    :next-page-token
    (. x nextPageToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListCachedContentsConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListCachedContentsConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListCachedContentsConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:page-size data)] (. b pageSize v))
   (clojure.core/when-some [v (:page-token data)] (. b pageToken v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListCachedContentsConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-size
    (. x pageSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-token
    (. x pageToken))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListCachedContentsParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListCachedContentsParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListCachedContentsParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.ListCachedContentsConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListCachedContentsParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListCachedContentsResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListCachedContentsResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListCachedContentsResponse/builder)]
   (clojure.core/when-some
    [v (:cached-contents data)]
    (.
     b
     cachedContents
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.CachedContent
        x))
      v)))
   (clojure.core/when-some
    [v (:next-page-token data)]
    (. b nextPageToken v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListCachedContentsResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :cached-contents
    (. x cachedContents))
   (io.kosong.autovalue/optional-datafy-assoc
    :next-page-token
    (. x nextPageToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListDocumentsConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListDocumentsConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListDocumentsConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:page-size data)] (. b pageSize v))
   (clojure.core/when-some [v (:page-token data)] (. b pageToken v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListDocumentsConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-size
    (. x pageSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-token
    (. x pageToken))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListDocumentsParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListDocumentsParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListDocumentsParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.ListDocumentsConfig
      v)))
   (clojure.core/when-some [v (:parent data)] (. b parent v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListDocumentsParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :parent (. x parent))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListDocumentsResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListDocumentsResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListDocumentsResponse/builder)]
   (clojure.core/when-some
    [v (:documents data)]
    (.
     b
     documents
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Document x))
      v)))
   (clojure.core/when-some
    [v (:next-page-token data)]
    (. b nextPageToken v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListDocumentsResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :documents
    (. x documents))
   (io.kosong.autovalue/optional-datafy-assoc
    :next-page-token
    (. x nextPageToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListFileSearchStoresConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListFileSearchStoresConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListFileSearchStoresConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:page-size data)] (. b pageSize v))
   (clojure.core/when-some [v (:page-token data)] (. b pageToken v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListFileSearchStoresConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-size
    (. x pageSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-token
    (. x pageToken))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListFileSearchStoresParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListFileSearchStoresParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListFileSearchStoresParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.ListFileSearchStoresConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListFileSearchStoresParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListFileSearchStoresResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListFileSearchStoresResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListFileSearchStoresResponse/builder)]
   (clojure.core/when-some
    [v (:file-search-stores data)]
    (.
     b
     fileSearchStores
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.FileSearchStore
        x))
      v)))
   (clojure.core/when-some
    [v (:next-page-token data)]
    (. b nextPageToken v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListFileSearchStoresResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :file-search-stores
    (. x fileSearchStores))
   (io.kosong.autovalue/optional-datafy-assoc
    :next-page-token
    (. x nextPageToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListFilesConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ListFilesConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListFilesConfig/builder)]
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:page-size data)] (. b pageSize v))
   (clojure.core/when-some [v (:page-token data)] (. b pageToken v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListFilesConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-size
    (. x pageSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-token
    (. x pageToken))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListFilesParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListFilesParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListFilesParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.ListFilesConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListFilesParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListFilesResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListFilesResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListFilesResponse/builder)]
   (clojure.core/when-some
    [v (:files data)]
    (.
     b
     files
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.File x))
      v)))
   (clojure.core/when-some
    [v (:next-page-token data)]
    (. b nextPageToken v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListFilesResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :files (. x files))
   (io.kosong.autovalue/optional-datafy-assoc
    :next-page-token
    (. x nextPageToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListModelsConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ListModelsConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListModelsConfig/builder)]
   (clojure.core/when-some [v (:filter data)] (. b filter v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:page-size data)] (. b pageSize v))
   (clojure.core/when-some [v (:page-token data)] (. b pageToken v))
   (clojure.core/when-some [v (:query-base data)] (. b queryBase v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListModelsConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :filter (. x filter))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-size
    (. x pageSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-token
    (. x pageToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :query-base
    (. x queryBase))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListModelsParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListModelsParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListModelsParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.ListModelsConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListModelsParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListModelsResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListModelsResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListModelsResponse/builder)]
   (clojure.core/when-some
    [v (:models data)]
    (.
     b
     models
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Model x))
      v)))
   (clojure.core/when-some
    [v (:next-page-token data)]
    (. b nextPageToken v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListModelsResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :models (. x models))
   (io.kosong.autovalue/optional-datafy-assoc
    :next-page-token
    (. x nextPageToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListTuningJobsConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListTuningJobsConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListTuningJobsConfig/builder)]
   (clojure.core/when-some [v (:filter data)] (. b filter v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:page-size data)] (. b pageSize v))
   (clojure.core/when-some [v (:page-token data)] (. b pageToken v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListTuningJobsConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :filter (. x filter))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-size
    (. x pageSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :page-token
    (. x pageToken))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListTuningJobsParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListTuningJobsParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListTuningJobsParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.ListTuningJobsConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListTuningJobsParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ListTuningJobsResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ListTuningJobsResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ListTuningJobsResponse/builder)]
   (clojure.core/when-some
    [v (:next-page-token data)]
    (. b nextPageToken v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (clojure.core/when-some
    [v (:tuning-jobs data)]
    (.
     b
     tuningJobs
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.TuningJob x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ListTuningJobsResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :next-page-token
    (. x nextPageToken))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuning-jobs
    (. x tuningJobs))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveClientContent
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveClientContent
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveClientContent/builder)]
   (clojure.core/when-some
    [v (:turn-complete data)]
    (. b turnComplete v))
   (clojure.core/when-some
    [v (:turns data)]
    (.
     b
     turns
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveClientContent
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :turn-complete
    (. x turnComplete))
   (io.kosong.autovalue/optional-datafy-assoc :turns (. x turns))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveClientMessage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveClientMessage
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveClientMessage/builder)]
   (clojure.core/when-some
    [v (:client-content data)]
    (.
     b
     clientContent
     (io.kosong.java/make-object
      com.google.genai.types.LiveClientContent
      v)))
   (clojure.core/when-some
    [v (:realtime-input data)]
    (.
     b
     realtimeInput
     (io.kosong.java/make-object
      com.google.genai.types.LiveClientRealtimeInput
      v)))
   (clojure.core/when-some
    [v (:realtime-input-parameters data)]
    (.
     b
     realtimeInputParameters
     (io.kosong.java/make-object
      com.google.genai.types.LiveSendRealtimeInputParameters
      v)))
   (clojure.core/when-some
    [v (:setup data)]
    (.
     b
     setup
     (io.kosong.java/make-object
      com.google.genai.types.LiveClientSetup
      v)))
   (clojure.core/when-some
    [v (:tool-response data)]
    (.
     b
     toolResponse
     (io.kosong.java/make-object
      com.google.genai.types.LiveClientToolResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveClientMessage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :client-content
    (. x clientContent))
   (io.kosong.autovalue/optional-datafy-assoc
    :realtime-input
    (. x realtimeInput))
   (io.kosong.autovalue/optional-datafy-assoc
    :realtime-input-parameters
    (. x realtimeInputParameters))
   (io.kosong.autovalue/optional-datafy-assoc :setup (. x setup))
   (io.kosong.autovalue/optional-datafy-assoc
    :tool-response
    (. x toolResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveClientRealtimeInput
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveClientRealtimeInput
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveClientRealtimeInput/builder)]
   (clojure.core/when-some
    [v (:activity-end data)]
    (.
     b
     activityEnd
     (io.kosong.java/make-object
      com.google.genai.types.ActivityEnd
      v)))
   (clojure.core/when-some
    [v (:activity-start data)]
    (.
     b
     activityStart
     (io.kosong.java/make-object
      com.google.genai.types.ActivityStart
      v)))
   (clojure.core/when-some
    [v (:audio data)]
    (.
     b
     audio
     (io.kosong.java/make-object com.google.genai.types.Blob v)))
   (clojure.core/when-some
    [v (:audio-stream-end data)]
    (. b audioStreamEnd v))
   (clojure.core/when-some
    [v (:media-chunks data)]
    (.
     b
     mediaChunks
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Blob x))
      v)))
   (clojure.core/when-some [v (:text data)] (. b text v))
   (clojure.core/when-some
    [v (:video data)]
    (.
     b
     video
     (io.kosong.java/make-object com.google.genai.types.Blob v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveClientRealtimeInput
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :activity-end
    (. x activityEnd))
   (io.kosong.autovalue/optional-datafy-assoc
    :activity-start
    (. x activityStart))
   (io.kosong.autovalue/optional-datafy-assoc :audio (. x audio))
   (io.kosong.autovalue/optional-datafy-assoc
    :audio-stream-end
    (. x audioStreamEnd))
   (io.kosong.autovalue/optional-datafy-assoc
    :media-chunks
    (. x mediaChunks))
   (io.kosong.autovalue/optional-datafy-assoc :text (. x text))
   (io.kosong.autovalue/optional-datafy-assoc :video (. x video))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveClientSetup
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.LiveClientSetup data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveClientSetup/builder)]
   (clojure.core/when-some
    [v (:context-window-compression data)]
    (.
     b
     contextWindowCompression
     (io.kosong.java/make-object
      com.google.genai.types.ContextWindowCompressionConfig
      v)))
   (clojure.core/when-some
    [v (:explicit-vad-signal data)]
    (. b explicitVadSignal v))
   (clojure.core/when-some
    [v (:generation-config data)]
    (.
     b
     generationConfig
     (io.kosong.java/make-object
      com.google.genai.types.GenerationConfig
      v)))
   (clojure.core/when-some
    [v (:input-audio-transcription data)]
    (.
     b
     inputAudioTranscription
     (io.kosong.java/make-object
      com.google.genai.types.AudioTranscriptionConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some
    [v (:output-audio-transcription data)]
    (.
     b
     outputAudioTranscription
     (io.kosong.java/make-object
      com.google.genai.types.AudioTranscriptionConfig
      v)))
   (clojure.core/when-some
    [v (:proactivity data)]
    (.
     b
     proactivity
     (io.kosong.java/make-object
      com.google.genai.types.ProactivityConfig
      v)))
   (clojure.core/when-some
    [v (:realtime-input-config data)]
    (.
     b
     realtimeInputConfig
     (io.kosong.java/make-object
      com.google.genai.types.RealtimeInputConfig
      v)))
   (clojure.core/when-some
    [v (:session-resumption data)]
    (.
     b
     sessionResumption
     (io.kosong.java/make-object
      com.google.genai.types.SessionResumptionConfig
      v)))
   (clojure.core/when-some
    [v (:system-instruction data)]
    (.
     b
     systemInstruction
     (io.kosong.java/make-object com.google.genai.types.Content v)))
   (clojure.core/when-some
    [v (:tools data)]
    (.
     b
     tools
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Tool x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveClientSetup
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :context-window-compression
    (. x contextWindowCompression))
   (io.kosong.autovalue/optional-datafy-assoc
    :explicit-vad-signal
    (. x explicitVadSignal))
   (io.kosong.autovalue/optional-datafy-assoc
    :generation-config
    (. x generationConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :input-audio-transcription
    (. x inputAudioTranscription))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-audio-transcription
    (. x outputAudioTranscription))
   (io.kosong.autovalue/optional-datafy-assoc
    :proactivity
    (. x proactivity))
   (io.kosong.autovalue/optional-datafy-assoc
    :realtime-input-config
    (. x realtimeInputConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :session-resumption
    (. x sessionResumption))
   (io.kosong.autovalue/optional-datafy-assoc
    :system-instruction
    (. x systemInstruction))
   (io.kosong.autovalue/optional-datafy-assoc :tools (. x tools))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveClientToolResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveClientToolResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveClientToolResponse/builder)]
   (clojure.core/when-some
    [v (:function-responses data)]
    (.
     b
     functionResponses
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.FunctionResponse
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveClientToolResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :function-responses
    (. x functionResponses))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveConnectConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveConnectConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveConnectConfig/builder)]
   (clojure.core/when-some
    [v (:context-window-compression data)]
    (.
     b
     contextWindowCompression
     (io.kosong.java/make-object
      com.google.genai.types.ContextWindowCompressionConfig
      v)))
   (clojure.core/when-some
    [v (:enable-affective-dialog data)]
    (. b enableAffectiveDialog v))
   (clojure.core/when-some
    [v (:explicit-vad-signal data)]
    (. b explicitVadSignal v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some
    [v (:input-audio-transcription data)]
    (.
     b
     inputAudioTranscription
     (io.kosong.java/make-object
      com.google.genai.types.AudioTranscriptionConfig
      v)))
   (clojure.core/when-some
    [v (:max-output-tokens data)]
    (. b maxOutputTokens v))
   (clojure.core/when-some
    [v (:media-resolution data)]
    (. b mediaResolution v))
   (clojure.core/when-some
    [v (:output-audio-transcription data)]
    (.
     b
     outputAudioTranscription
     (io.kosong.java/make-object
      com.google.genai.types.AudioTranscriptionConfig
      v)))
   (clojure.core/when-some
    [v (:proactivity data)]
    (.
     b
     proactivity
     (io.kosong.java/make-object
      com.google.genai.types.ProactivityConfig
      v)))
   (clojure.core/when-some
    [v (:realtime-input-config data)]
    (.
     b
     realtimeInputConfig
     (io.kosong.java/make-object
      com.google.genai.types.RealtimeInputConfig
      v)))
   (clojure.core/when-some
    [v (:response-modalities data)]
    (. b responseModalities v))
   (clojure.core/when-some [v (:seed data)] (. b seed v))
   (clojure.core/when-some
    [v (:session-resumption data)]
    (.
     b
     sessionResumption
     (io.kosong.java/make-object
      com.google.genai.types.SessionResumptionConfig
      v)))
   (clojure.core/when-some
    [v (:speech-config data)]
    (.
     b
     speechConfig
     (io.kosong.java/make-object
      com.google.genai.types.SpeechConfig
      v)))
   (clojure.core/when-some
    [v (:system-instruction data)]
    (.
     b
     systemInstruction
     (io.kosong.java/make-object com.google.genai.types.Content v)))
   (clojure.core/when-some [v (:temperature data)] (. b temperature v))
   (clojure.core/when-some
    [v (:thinking-config data)]
    (.
     b
     thinkingConfig
     (io.kosong.java/make-object
      com.google.genai.types.ThinkingConfig
      v)))
   (clojure.core/when-some
    [v (:tools data)]
    (.
     b
     tools
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Tool x))
      v)))
   (clojure.core/when-some [v (:top-k data)] (. b topK v))
   (clojure.core/when-some [v (:top-p data)] (. b topP v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveConnectConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :context-window-compression
    (. x contextWindowCompression))
   (io.kosong.autovalue/optional-datafy-assoc
    :enable-affective-dialog
    (. x enableAffectiveDialog))
   (io.kosong.autovalue/optional-datafy-assoc
    :explicit-vad-signal
    (. x explicitVadSignal))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :input-audio-transcription
    (. x inputAudioTranscription))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-output-tokens
    (. x maxOutputTokens))
   (io.kosong.autovalue/optional-datafy-assoc
    :media-resolution
    (. x mediaResolution))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-audio-transcription
    (. x outputAudioTranscription))
   (io.kosong.autovalue/optional-datafy-assoc
    :proactivity
    (. x proactivity))
   (io.kosong.autovalue/optional-datafy-assoc
    :realtime-input-config
    (. x realtimeInputConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-modalities
    (. x responseModalities))
   (io.kosong.autovalue/optional-datafy-assoc :seed (. x seed))
   (io.kosong.autovalue/optional-datafy-assoc
    :session-resumption
    (. x sessionResumption))
   (io.kosong.autovalue/optional-datafy-assoc
    :speech-config
    (. x speechConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :system-instruction
    (. x systemInstruction))
   (io.kosong.autovalue/optional-datafy-assoc
    :temperature
    (. x temperature))
   (io.kosong.autovalue/optional-datafy-assoc
    :thinking-config
    (. x thinkingConfig))
   (io.kosong.autovalue/optional-datafy-assoc :tools (. x tools))
   (io.kosong.autovalue/optional-datafy-assoc :top-k (. x topK))
   (io.kosong.autovalue/optional-datafy-assoc :top-p (. x topP))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveConnectConstraints
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveConnectConstraints
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveConnectConstraints/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.LiveConnectConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveConnectConstraints
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveConnectParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveConnectParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveConnectParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.LiveConnectConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveConnectParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveSendClientContentParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveSendClientContentParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveSendClientContentParameters/builder)]
   (clojure.core/when-some
    [v (:turn-complete data)]
    (. b turnComplete v))
   (clojure.core/when-some
    [v (:turns data)]
    (.
     b
     turns
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveSendClientContentParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :turn-complete
    (. x turnComplete))
   (io.kosong.autovalue/optional-datafy-assoc :turns (. x turns))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveSendRealtimeInputParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveSendRealtimeInputParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveSendRealtimeInputParameters/builder)]
   (clojure.core/when-some
    [v (:activity-end data)]
    (.
     b
     activityEnd
     (io.kosong.java/make-object
      com.google.genai.types.ActivityEnd
      v)))
   (clojure.core/when-some
    [v (:activity-start data)]
    (.
     b
     activityStart
     (io.kosong.java/make-object
      com.google.genai.types.ActivityStart
      v)))
   (clojure.core/when-some
    [v (:audio data)]
    (.
     b
     audio
     (io.kosong.java/make-object com.google.genai.types.Blob v)))
   (clojure.core/when-some
    [v (:audio-stream-end data)]
    (. b audioStreamEnd v))
   (clojure.core/when-some
    [v (:media data)]
    (.
     b
     media
     (io.kosong.java/make-object com.google.genai.types.Blob v)))
   (clojure.core/when-some [v (:text data)] (. b text v))
   (clojure.core/when-some
    [v (:video data)]
    (.
     b
     video
     (io.kosong.java/make-object com.google.genai.types.Blob v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveSendRealtimeInputParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :activity-end
    (. x activityEnd))
   (io.kosong.autovalue/optional-datafy-assoc
    :activity-start
    (. x activityStart))
   (io.kosong.autovalue/optional-datafy-assoc :audio (. x audio))
   (io.kosong.autovalue/optional-datafy-assoc
    :audio-stream-end
    (. x audioStreamEnd))
   (io.kosong.autovalue/optional-datafy-assoc :media (. x media))
   (io.kosong.autovalue/optional-datafy-assoc :text (. x text))
   (io.kosong.autovalue/optional-datafy-assoc :video (. x video))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveSendToolResponseParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveSendToolResponseParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveSendToolResponseParameters/builder)]
   (clojure.core/when-some
    [v (:function-responses data)]
    (.
     b
     functionResponses
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.FunctionResponse
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveSendToolResponseParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :function-responses
    (. x functionResponses))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveServerContent
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveServerContent
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveServerContent/builder)]
   (clojure.core/when-some
    [v (:generation-complete data)]
    (. b generationComplete v))
   (clojure.core/when-some
    [v (:grounding-metadata data)]
    (.
     b
     groundingMetadata
     (io.kosong.java/make-object
      com.google.genai.types.GroundingMetadata
      v)))
   (clojure.core/when-some
    [v (:input-transcription data)]
    (.
     b
     inputTranscription
     (io.kosong.java/make-object
      com.google.genai.types.Transcription
      v)))
   (clojure.core/when-some [v (:interrupted data)] (. b interrupted v))
   (clojure.core/when-some
    [v (:model-turn data)]
    (.
     b
     modelTurn
     (io.kosong.java/make-object com.google.genai.types.Content v)))
   (clojure.core/when-some
    [v (:output-transcription data)]
    (.
     b
     outputTranscription
     (io.kosong.java/make-object
      com.google.genai.types.Transcription
      v)))
   (clojure.core/when-some
    [v (:turn-complete data)]
    (. b turnComplete v))
   (clojure.core/when-some
    [v (:turn-complete-reason data)]
    (. b turnCompleteReason v))
   (clojure.core/when-some
    [v (:url-context-metadata data)]
    (.
     b
     urlContextMetadata
     (io.kosong.java/make-object
      com.google.genai.types.UrlContextMetadata
      v)))
   (clojure.core/when-some
    [v (:waiting-for-input data)]
    (. b waitingForInput v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveServerContent
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :generation-complete
    (. x generationComplete))
   (io.kosong.autovalue/optional-datafy-assoc
    :grounding-metadata
    (. x groundingMetadata))
   (io.kosong.autovalue/optional-datafy-assoc
    :input-transcription
    (. x inputTranscription))
   (io.kosong.autovalue/optional-datafy-assoc
    :interrupted
    (. x interrupted))
   (io.kosong.autovalue/optional-datafy-assoc
    :model-turn
    (. x modelTurn))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-transcription
    (. x outputTranscription))
   (io.kosong.autovalue/optional-datafy-assoc
    :turn-complete
    (. x turnComplete))
   (io.kosong.autovalue/optional-datafy-assoc
    :turn-complete-reason
    (. x turnCompleteReason))
   (io.kosong.autovalue/optional-datafy-assoc
    :url-context-metadata
    (. x urlContextMetadata))
   (io.kosong.autovalue/optional-datafy-assoc
    :waiting-for-input
    (. x waitingForInput))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveServerGoAway
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.LiveServerGoAway data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveServerGoAway/builder)]
   (clojure.core/when-some [v (:time-left data)] (. b timeLeft v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveServerGoAway
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :time-left
    (. x timeLeft))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveServerMessage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveServerMessage
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveServerMessage/builder)]
   (clojure.core/when-some
    [v (:go-away data)]
    (.
     b
     goAway
     (io.kosong.java/make-object
      com.google.genai.types.LiveServerGoAway
      v)))
   (clojure.core/when-some
    [v (:server-content data)]
    (.
     b
     serverContent
     (io.kosong.java/make-object
      com.google.genai.types.LiveServerContent
      v)))
   (clojure.core/when-some
    [v (:session-resumption-update data)]
    (.
     b
     sessionResumptionUpdate
     (io.kosong.java/make-object
      com.google.genai.types.LiveServerSessionResumptionUpdate
      v)))
   (clojure.core/when-some
    [v (:setup-complete data)]
    (.
     b
     setupComplete
     (io.kosong.java/make-object
      com.google.genai.types.LiveServerSetupComplete
      v)))
   (clojure.core/when-some
    [v (:tool-call data)]
    (.
     b
     toolCall
     (io.kosong.java/make-object
      com.google.genai.types.LiveServerToolCall
      v)))
   (clojure.core/when-some
    [v (:tool-call-cancellation data)]
    (.
     b
     toolCallCancellation
     (io.kosong.java/make-object
      com.google.genai.types.LiveServerToolCallCancellation
      v)))
   (clojure.core/when-some
    [v (:usage-metadata data)]
    (.
     b
     usageMetadata
     (io.kosong.java/make-object
      com.google.genai.types.UsageMetadata
      v)))
   (clojure.core/when-some
    [v (:voice-activity-detection-signal data)]
    (.
     b
     voiceActivityDetectionSignal
     (io.kosong.java/make-object
      com.google.genai.types.VoiceActivityDetectionSignal
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveServerMessage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :go-away (. x goAway))
   (io.kosong.autovalue/optional-datafy-assoc
    :server-content
    (. x serverContent))
   (io.kosong.autovalue/optional-datafy-assoc
    :session-resumption-update
    (. x sessionResumptionUpdate))
   (io.kosong.autovalue/optional-datafy-assoc
    :setup-complete
    (. x setupComplete))
   (io.kosong.autovalue/optional-datafy-assoc
    :tool-call
    (. x toolCall))
   (io.kosong.autovalue/optional-datafy-assoc
    :tool-call-cancellation
    (. x toolCallCancellation))
   (io.kosong.autovalue/optional-datafy-assoc
    :usage-metadata
    (. x usageMetadata))
   (io.kosong.autovalue/optional-datafy-assoc
    :voice-activity-detection-signal
    (. x voiceActivityDetectionSignal))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveServerSessionResumptionUpdate
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveServerSessionResumptionUpdate
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.LiveServerSessionResumptionUpdate/builder)]
   (clojure.core/when-some
    [v (:last-consumed-client-message-index data)]
    (. b lastConsumedClientMessageIndex v))
   (clojure.core/when-some [v (:new-handle data)] (. b newHandle v))
   (clojure.core/when-some [v (:resumable data)] (. b resumable v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveServerSessionResumptionUpdate
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :last-consumed-client-message-index
    (. x lastConsumedClientMessageIndex))
   (io.kosong.autovalue/optional-datafy-assoc
    :new-handle
    (. x newHandle))
   (io.kosong.autovalue/optional-datafy-assoc
    :resumable
    (. x resumable))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveServerSetupComplete
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveServerSetupComplete
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveServerSetupComplete/builder)]
   (clojure.core/when-some [v (:session-id data)] (. b sessionId v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveServerSetupComplete
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :session-id
    (. x sessionId))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveServerToolCall
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveServerToolCall
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveServerToolCall/builder)]
   (clojure.core/when-some
    [v (:function-calls data)]
    (.
     b
     functionCalls
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.FunctionCall
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveServerToolCall
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :function-calls
    (. x functionCalls))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LiveServerToolCallCancellation
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LiveServerToolCallCancellation
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LiveServerToolCallCancellation/builder)]
   (clojure.core/when-some [v (:ids data)] (. b ids v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LiveServerToolCallCancellation
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :ids (. x ids))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LogprobsResult
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.LogprobsResult data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LogprobsResult/builder)]
   (clojure.core/when-some
    [v (:chosen-candidates data)]
    (.
     b
     chosenCandidates
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.LogprobsResultCandidate
        x))
      v)))
   (clojure.core/when-some
    [v (:top-candidates data)]
    (.
     b
     topCandidates
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.LogprobsResultTopCandidates
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LogprobsResult
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :chosen-candidates
    (. x chosenCandidates))
   (io.kosong.autovalue/optional-datafy-assoc
    :top-candidates
    (. x topCandidates))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LogprobsResultCandidate
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LogprobsResultCandidate
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LogprobsResultCandidate/builder)]
   (clojure.core/when-some
    [v (:log-probability data)]
    (. b logProbability v))
   (clojure.core/when-some [v (:token data)] (. b token v))
   (clojure.core/when-some [v (:token-id data)] (. b tokenId v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LogprobsResultCandidate
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :log-probability
    (. x logProbability))
   (io.kosong.autovalue/optional-datafy-assoc :token (. x token))
   (io.kosong.autovalue/optional-datafy-assoc :token-id (. x tokenId))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.LogprobsResultTopCandidates
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.LogprobsResultTopCandidates
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.LogprobsResultTopCandidates/builder)]
   (clojure.core/when-some
    [v (:candidates data)]
    (.
     b
     candidates
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.LogprobsResultCandidate
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.LogprobsResultTopCandidates
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :candidates
    (. x candidates))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.MaskReferenceConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.MaskReferenceConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.MaskReferenceConfig/builder)]
   (clojure.core/when-some
    [v (:mask-dilation data)]
    (. b maskDilation v))
   (clojure.core/when-some [v (:mask-mode data)] (. b maskMode v))
   (clojure.core/when-some
    [v (:segmentation-classes data)]
    (. b segmentationClasses v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.MaskReferenceConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :mask-dilation
    (. x maskDilation))
   (io.kosong.autovalue/optional-datafy-assoc
    :mask-mode
    (. x maskMode))
   (io.kosong.autovalue/optional-datafy-assoc
    :segmentation-classes
    (. x segmentationClasses))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.MaskReferenceImage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.MaskReferenceImage
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.MaskReferenceImage/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.MaskReferenceConfig
      v)))
   (clojure.core/when-some
    [v (:reference-id data)]
    (. b referenceId v))
   (clojure.core/when-some
    [v (:reference-image data)]
    (.
     b
     referenceImage
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:reference-type data)]
    (. b referenceType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.MaskReferenceImage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-id
    (. x referenceId))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-image
    (. x referenceImage))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-type
    (. x referenceType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Metric
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Metric data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Metric/builder)]
   (clojure.core/when-some
    [v (:judge-model-system-instruction data)]
    (. b judgeModelSystemInstruction v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:prompt-template data)]
    (. b promptTemplate v))
   (clojure.core/when-some
    [v (:return-raw-output data)]
    (. b returnRawOutput v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Metric
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :judge-model-system-instruction
    (. x judgeModelSystemInstruction))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :prompt-template
    (. x promptTemplate))
   (io.kosong.autovalue/optional-datafy-assoc
    :return-raw-output
    (. x returnRawOutput))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ModalityTokenCount
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ModalityTokenCount
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ModalityTokenCount/builder)]
   (clojure.core/when-some [v (:modality data)] (. b modality v))
   (clojure.core/when-some [v (:token-count data)] (. b tokenCount v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ModalityTokenCount
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :modality (. x modality))
   (io.kosong.autovalue/optional-datafy-assoc
    :token-count
    (. x tokenCount))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Model
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Model data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Model/builder)]
   (clojure.core/when-some
    [v (:checkpoints data)]
    (.
     b
     checkpoints
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.Checkpoint
        x))
      v)))
   (clojure.core/when-some
    [v (:default-checkpoint-id data)]
    (. b defaultCheckpointId v))
   (clojure.core/when-some [v (:description data)] (. b description v))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some
    [v (:endpoints data)]
    (.
     b
     endpoints
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Endpoint x))
      v)))
   (clojure.core/when-some
    [v (:input-token-limit data)]
    (. b inputTokenLimit v))
   (clojure.core/when-some [v (:labels data)] (. b labels v))
   (clojure.core/when-some
    [v (:max-temperature data)]
    (. b maxTemperature v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:output-token-limit data)]
    (. b outputTokenLimit v))
   (clojure.core/when-some
    [v (:supported-actions data)]
    (. b supportedActions v))
   (clojure.core/when-some [v (:temperature data)] (. b temperature v))
   (clojure.core/when-some [v (:thinking data)] (. b thinking v))
   (clojure.core/when-some [v (:top-k data)] (. b topK v))
   (clojure.core/when-some [v (:top-p data)] (. b topP v))
   (clojure.core/when-some
    [v (:tuned-model-info data)]
    (.
     b
     tunedModelInfo
     (io.kosong.java/make-object
      com.google.genai.types.TunedModelInfo
      v)))
   (clojure.core/when-some [v (:version data)] (. b version v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Model
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :checkpoints
    (. x checkpoints))
   (io.kosong.autovalue/optional-datafy-assoc
    :default-checkpoint-id
    (. x defaultCheckpointId))
   (io.kosong.autovalue/optional-datafy-assoc
    :description
    (. x description))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :endpoints
    (. x endpoints))
   (io.kosong.autovalue/optional-datafy-assoc
    :input-token-limit
    (. x inputTokenLimit))
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-temperature
    (. x maxTemperature))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-token-limit
    (. x outputTokenLimit))
   (io.kosong.autovalue/optional-datafy-assoc
    :supported-actions
    (. x supportedActions))
   (io.kosong.autovalue/optional-datafy-assoc
    :temperature
    (. x temperature))
   (io.kosong.autovalue/optional-datafy-assoc :thinking (. x thinking))
   (io.kosong.autovalue/optional-datafy-assoc :top-k (. x topK))
   (io.kosong.autovalue/optional-datafy-assoc :top-p (. x topP))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuned-model-info
    (. x tunedModelInfo))
   (io.kosong.autovalue/optional-datafy-assoc :version (. x version))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ModelSelectionConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ModelSelectionConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ModelSelectionConfig/builder)]
   (clojure.core/when-some
    [v (:feature-selection-preference data)]
    (. b featureSelectionPreference v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ModelSelectionConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :feature-selection-preference
    (. x featureSelectionPreference))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.MultiSpeakerVoiceConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.MultiSpeakerVoiceConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.MultiSpeakerVoiceConfig/builder)]
   (clojure.core/when-some
    [v (:speaker-voice-configs data)]
    (.
     b
     speakerVoiceConfigs
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.SpeakerVoiceConfig
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.MultiSpeakerVoiceConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :speaker-voice-configs
    (. x speakerVoiceConfigs))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.OutputConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.OutputConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.OutputConfig/builder)]
   (clojure.core/when-some
    [v (:gcs-destination data)]
    (.
     b
     gcsDestination
     (io.kosong.java/make-object
      com.google.genai.types.GcsDestination
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.OutputConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :gcs-destination
    (. x gcsDestination))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Part
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Part data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Part/builder)]
   (clojure.core/when-some
    [v (:code-execution-result data)]
    (.
     b
     codeExecutionResult
     (io.kosong.java/make-object
      com.google.genai.types.CodeExecutionResult
      v)))
   (clojure.core/when-some
    [v (:executable-code data)]
    (.
     b
     executableCode
     (io.kosong.java/make-object
      com.google.genai.types.ExecutableCode
      v)))
   (clojure.core/when-some
    [v (:file-data data)]
    (.
     b
     fileData
     (io.kosong.java/make-object com.google.genai.types.FileData v)))
   (clojure.core/when-some
    [v (:function-call data)]
    (.
     b
     functionCall
     (io.kosong.java/make-object
      com.google.genai.types.FunctionCall
      v)))
   (clojure.core/when-some
    [v (:function-response data)]
    (.
     b
     functionResponse
     (io.kosong.java/make-object
      com.google.genai.types.FunctionResponse
      v)))
   (clojure.core/when-some
    [v (:inline-data data)]
    (.
     b
     inlineData
     (io.kosong.java/make-object com.google.genai.types.Blob v)))
   (clojure.core/when-some
    [v (:media-resolution data)]
    (.
     b
     mediaResolution
     (io.kosong.java/make-object
      com.google.genai.types.PartMediaResolution
      v)))
   (clojure.core/when-some [v (:text data)] (. b text v))
   (clojure.core/when-some [v (:thought data)] (. b thought v))
   (clojure.core/when-some
    [v (:thought-signature data)]
    (. b thoughtSignature v))
   (clojure.core/when-some
    [v (:video-metadata data)]
    (.
     b
     videoMetadata
     (io.kosong.java/make-object
      com.google.genai.types.VideoMetadata
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Part
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :code-execution-result
    (. x codeExecutionResult))
   (io.kosong.autovalue/optional-datafy-assoc
    :executable-code
    (. x executableCode))
   (io.kosong.autovalue/optional-datafy-assoc
    :file-data
    (. x fileData))
   (io.kosong.autovalue/optional-datafy-assoc
    :function-call
    (. x functionCall))
   (io.kosong.autovalue/optional-datafy-assoc
    :function-response
    (. x functionResponse))
   (io.kosong.autovalue/optional-datafy-assoc
    :inline-data
    (. x inlineData))
   (io.kosong.autovalue/optional-datafy-assoc
    :media-resolution
    (. x mediaResolution))
   (io.kosong.autovalue/optional-datafy-assoc :text (. x text))
   (io.kosong.autovalue/optional-datafy-assoc :thought (. x thought))
   (io.kosong.autovalue/optional-datafy-assoc
    :thought-signature
    (. x thoughtSignature))
   (io.kosong.autovalue/optional-datafy-assoc
    :video-metadata
    (. x videoMetadata))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.PartMediaResolution
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.PartMediaResolution
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.PartMediaResolution/builder)]
   (clojure.core/when-some [v (:level data)] (. b level v))
   (clojure.core/when-some [v (:num-tokens data)] (. b numTokens v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.PartMediaResolution
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :level (. x level))
   (io.kosong.autovalue/optional-datafy-assoc
    :num-tokens
    (. x numTokens))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.PartialArg
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.PartialArg data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.PartialArg/builder)]
   (clojure.core/when-some [v (:bool-value data)] (. b boolValue v))
   (clojure.core/when-some [v (:json-path data)] (. b jsonPath v))
   (clojure.core/when-some [v (:null-value data)] (. b nullValue v))
   (clojure.core/when-some
    [v (:number-value data)]
    (. b numberValue v))
   (clojure.core/when-some
    [v (:string-value data)]
    (. b stringValue v))
   (clojure.core/when-some
    [v (:will-continue data)]
    (. b willContinue v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.PartialArg
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :bool-value
    (. x boolValue))
   (io.kosong.autovalue/optional-datafy-assoc
    :json-path
    (. x jsonPath))
   (io.kosong.autovalue/optional-datafy-assoc
    :null-value
    (. x nullValue))
   (io.kosong.autovalue/optional-datafy-assoc
    :number-value
    (. x numberValue))
   (io.kosong.autovalue/optional-datafy-assoc
    :string-value
    (. x stringValue))
   (io.kosong.autovalue/optional-datafy-assoc
    :will-continue
    (. x willContinue))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.PartnerModelTuningSpec
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.PartnerModelTuningSpec
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.PartnerModelTuningSpec/builder)]
   (clojure.core/when-some
    [v (:hyper-parameters data)]
    (. b hyperParameters v))
   (clojure.core/when-some
    [v (:training-dataset-uri data)]
    (. b trainingDatasetUri v))
   (clojure.core/when-some
    [v (:validation-dataset-uri data)]
    (. b validationDatasetUri v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.PartnerModelTuningSpec
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :hyper-parameters
    (. x hyperParameters))
   (io.kosong.autovalue/optional-datafy-assoc
    :training-dataset-uri
    (. x trainingDatasetUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :validation-dataset-uri
    (. x validationDatasetUri))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.PreTunedModel
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.PreTunedModel data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.PreTunedModel/builder)]
   (clojure.core/when-some [v (:base-model data)] (. b baseModel v))
   (clojure.core/when-some
    [v (:checkpoint-id data)]
    (. b checkpointId v))
   (clojure.core/when-some
    [v (:tuned-model-name data)]
    (. b tunedModelName v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.PreTunedModel
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :base-model
    (. x baseModel))
   (io.kosong.autovalue/optional-datafy-assoc
    :checkpoint-id
    (. x checkpointId))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuned-model-name
    (. x tunedModelName))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.PrebuiltVoiceConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.PrebuiltVoiceConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.PrebuiltVoiceConfig/builder)]
   (clojure.core/when-some [v (:voice-name data)] (. b voiceName v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.PrebuiltVoiceConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :voice-name
    (. x voiceName))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.PreferenceOptimizationDataStats
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.PreferenceOptimizationDataStats
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.PreferenceOptimizationDataStats/builder)]
   (clojure.core/when-some
    [v (:score-variance-per-example-distribution data)]
    (.
     b
     scoreVariancePerExampleDistribution
     (io.kosong.java/make-object
      com.google.genai.types.DatasetDistribution
      v)))
   (clojure.core/when-some
    [v (:scores-distribution data)]
    (.
     b
     scoresDistribution
     (io.kosong.java/make-object
      com.google.genai.types.DatasetDistribution
      v)))
   (clojure.core/when-some
    [v (:total-billable-token-count data)]
    (. b totalBillableTokenCount v))
   (clojure.core/when-some
    [v (:tuning-dataset-example-count data)]
    (. b tuningDatasetExampleCount v))
   (clojure.core/when-some
    [v (:tuning-step-count data)]
    (. b tuningStepCount v))
   (clojure.core/when-some
    [v (:user-dataset-examples data)]
    (.
     b
     userDatasetExamples
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GeminiPreferenceExample
        x))
      v)))
   (clojure.core/when-some
    [v (:user-input-token-distribution data)]
    (.
     b
     userInputTokenDistribution
     (io.kosong.java/make-object
      com.google.genai.types.DatasetDistribution
      v)))
   (clojure.core/when-some
    [v (:user-output-token-distribution data)]
    (.
     b
     userOutputTokenDistribution
     (io.kosong.java/make-object
      com.google.genai.types.DatasetDistribution
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.PreferenceOptimizationDataStats
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :score-variance-per-example-distribution
    (. x scoreVariancePerExampleDistribution))
   (io.kosong.autovalue/optional-datafy-assoc
    :scores-distribution
    (. x scoresDistribution))
   (io.kosong.autovalue/optional-datafy-assoc
    :total-billable-token-count
    (. x totalBillableTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuning-dataset-example-count
    (. x tuningDatasetExampleCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuning-step-count
    (. x tuningStepCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-dataset-examples
    (. x userDatasetExamples))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-input-token-distribution
    (. x userInputTokenDistribution))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-output-token-distribution
    (. x userOutputTokenDistribution))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.PreferenceOptimizationHyperParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.PreferenceOptimizationHyperParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.PreferenceOptimizationHyperParameters/builder)]
   (clojure.core/when-some
    [v (:adapter-size data)]
    (. b adapterSize v))
   (clojure.core/when-some [v (:beta data)] (. b beta v))
   (clojure.core/when-some [v (:epoch-count data)] (. b epochCount v))
   (clojure.core/when-some
    [v (:learning-rate-multiplier data)]
    (. b learningRateMultiplier v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.PreferenceOptimizationHyperParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :adapter-size
    (. x adapterSize))
   (io.kosong.autovalue/optional-datafy-assoc :beta (. x beta))
   (io.kosong.autovalue/optional-datafy-assoc
    :epoch-count
    (. x epochCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :learning-rate-multiplier
    (. x learningRateMultiplier))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.PreferenceOptimizationSpec
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.PreferenceOptimizationSpec
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.PreferenceOptimizationSpec/builder)]
   (clojure.core/when-some
    [v (:export-last-checkpoint-only data)]
    (. b exportLastCheckpointOnly v))
   (clojure.core/when-some
    [v (:hyper-parameters data)]
    (.
     b
     hyperParameters
     (io.kosong.java/make-object
      com.google.genai.types.PreferenceOptimizationHyperParameters
      v)))
   (clojure.core/when-some
    [v (:training-dataset-uri data)]
    (. b trainingDatasetUri v))
   (clojure.core/when-some
    [v (:validation-dataset-uri data)]
    (. b validationDatasetUri v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.PreferenceOptimizationSpec
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :export-last-checkpoint-only
    (. x exportLastCheckpointOnly))
   (io.kosong.autovalue/optional-datafy-assoc
    :hyper-parameters
    (. x hyperParameters))
   (io.kosong.autovalue/optional-datafy-assoc
    :training-dataset-uri
    (. x trainingDatasetUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :validation-dataset-uri
    (. x validationDatasetUri))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ProactivityConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ProactivityConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ProactivityConfig/builder)]
   (clojure.core/when-some
    [v (:proactive-audio data)]
    (. b proactiveAudio v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ProactivityConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :proactive-audio
    (. x proactiveAudio))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ProductImage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ProductImage data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ProductImage/builder)]
   (clojure.core/when-some
    [v (:product-image data)]
    (.
     b
     productImage
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ProductImage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :product-image
    (. x productImage))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ProxyOptions
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ProxyOptions data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ProxyOptions/builder)]
   (clojure.core/when-some [v (:host data)] (. b host v))
   (clojure.core/when-some [v (:password data)] (. b password v))
   (clojure.core/when-some [v (:port data)] (. b port v))
   (clojure.core/when-some [v (:type data)] (. b type v))
   (clojure.core/when-some [v (:username data)] (. b username v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ProxyOptions
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :host (. x host))
   (io.kosong.autovalue/optional-datafy-assoc :password (. x password))
   (io.kosong.autovalue/optional-datafy-assoc :port (. x port))
   (io.kosong.autovalue/optional-datafy-assoc :type (. x type))
   (io.kosong.autovalue/optional-datafy-assoc :username (. x username))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RagChunk
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.RagChunk data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RagChunk/builder)]
   (clojure.core/when-some
    [v (:page-span data)]
    (.
     b
     pageSpan
     (io.kosong.java/make-object
      com.google.genai.types.RagChunkPageSpan
      v)))
   (clojure.core/when-some [v (:text data)] (. b text v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RagChunk
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :page-span
    (. x pageSpan))
   (io.kosong.autovalue/optional-datafy-assoc :text (. x text))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RagChunkPageSpan
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.RagChunkPageSpan data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RagChunkPageSpan/builder)]
   (clojure.core/when-some [v (:first-page data)] (. b firstPage v))
   (clojure.core/when-some [v (:last-page data)] (. b lastPage v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RagChunkPageSpan
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :first-page
    (. x firstPage))
   (io.kosong.autovalue/optional-datafy-assoc
    :last-page
    (. x lastPage))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RagRetrievalConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RagRetrievalConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RagRetrievalConfig/builder)]
   (clojure.core/when-some
    [v (:filter data)]
    (.
     b
     filter
     (io.kosong.java/make-object
      com.google.genai.types.RagRetrievalConfigFilter
      v)))
   (clojure.core/when-some
    [v (:hybrid-search data)]
    (.
     b
     hybridSearch
     (io.kosong.java/make-object
      com.google.genai.types.RagRetrievalConfigHybridSearch
      v)))
   (clojure.core/when-some
    [v (:ranking data)]
    (.
     b
     ranking
     (io.kosong.java/make-object
      com.google.genai.types.RagRetrievalConfigRanking
      v)))
   (clojure.core/when-some [v (:top-k data)] (. b topK v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RagRetrievalConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :filter (. x filter))
   (io.kosong.autovalue/optional-datafy-assoc
    :hybrid-search
    (. x hybridSearch))
   (io.kosong.autovalue/optional-datafy-assoc :ranking (. x ranking))
   (io.kosong.autovalue/optional-datafy-assoc :top-k (. x topK))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RagRetrievalConfigFilter
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RagRetrievalConfigFilter
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RagRetrievalConfigFilter/builder)]
   (clojure.core/when-some
    [v (:metadata-filter data)]
    (. b metadataFilter v))
   (clojure.core/when-some
    [v (:vector-distance-threshold data)]
    (. b vectorDistanceThreshold v))
   (clojure.core/when-some
    [v (:vector-similarity-threshold data)]
    (. b vectorSimilarityThreshold v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RagRetrievalConfigFilter
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :metadata-filter
    (. x metadataFilter))
   (io.kosong.autovalue/optional-datafy-assoc
    :vector-distance-threshold
    (. x vectorDistanceThreshold))
   (io.kosong.autovalue/optional-datafy-assoc
    :vector-similarity-threshold
    (. x vectorSimilarityThreshold))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RagRetrievalConfigHybridSearch
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RagRetrievalConfigHybridSearch
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RagRetrievalConfigHybridSearch/builder)]
   (clojure.core/when-some [v (:alpha data)] (. b alpha v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RagRetrievalConfigHybridSearch
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :alpha (. x alpha))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RagRetrievalConfigRanking
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RagRetrievalConfigRanking
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RagRetrievalConfigRanking/builder)]
   (clojure.core/when-some
    [v (:llm-ranker data)]
    (.
     b
     llmRanker
     (io.kosong.java/make-object
      com.google.genai.types.RagRetrievalConfigRankingLlmRanker
      v)))
   (clojure.core/when-some
    [v (:rank-service data)]
    (.
     b
     rankService
     (io.kosong.java/make-object
      com.google.genai.types.RagRetrievalConfigRankingRankService
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RagRetrievalConfigRanking
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :llm-ranker
    (. x llmRanker))
   (io.kosong.autovalue/optional-datafy-assoc
    :rank-service
    (. x rankService))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RagRetrievalConfigRankingLlmRanker
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RagRetrievalConfigRankingLlmRanker
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.RagRetrievalConfigRankingLlmRanker/builder)]
   (clojure.core/when-some [v (:model-name data)] (. b modelName v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RagRetrievalConfigRankingLlmRanker
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :model-name
    (. x modelName))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RagRetrievalConfigRankingRankService
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RagRetrievalConfigRankingRankService
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.RagRetrievalConfigRankingRankService/builder)]
   (clojure.core/when-some [v (:model-name data)] (. b modelName v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RagRetrievalConfigRankingRankService
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :model-name
    (. x modelName))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RawReferenceImage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RawReferenceImage
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RawReferenceImage/builder)]
   (clojure.core/when-some
    [v (:reference-id data)]
    (. b referenceId v))
   (clojure.core/when-some
    [v (:reference-image data)]
    (.
     b
     referenceImage
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:reference-type data)]
    (. b referenceType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RawReferenceImage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-id
    (. x referenceId))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-image
    (. x referenceImage))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-type
    (. x referenceType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RealtimeInputConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RealtimeInputConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RealtimeInputConfig/builder)]
   (clojure.core/when-some
    [v (:activity-handling data)]
    (. b activityHandling v))
   (clojure.core/when-some
    [v (:automatic-activity-detection data)]
    (.
     b
     automaticActivityDetection
     (io.kosong.java/make-object
      com.google.genai.types.AutomaticActivityDetection
      v)))
   (clojure.core/when-some
    [v (:turn-coverage data)]
    (. b turnCoverage v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RealtimeInputConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :activity-handling
    (. x activityHandling))
   (io.kosong.autovalue/optional-datafy-assoc
    :automatic-activity-detection
    (. x automaticActivityDetection))
   (io.kosong.autovalue/optional-datafy-assoc
    :turn-coverage
    (. x turnCoverage))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RecontextImageConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RecontextImageConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RecontextImageConfig/builder)]
   (clojure.core/when-some
    [v (:add-watermark data)]
    (. b addWatermark v))
   (clojure.core/when-some [v (:base-steps data)] (. b baseSteps v))
   (clojure.core/when-some
    [v (:enhance-prompt data)]
    (. b enhancePrompt v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:labels data)] (. b labels v))
   (clojure.core/when-some
    [v (:number-of-images data)]
    (. b numberOfImages v))
   (clojure.core/when-some
    [v (:output-compression-quality data)]
    (. b outputCompressionQuality v))
   (clojure.core/when-some
    [v (:output-gcs-uri data)]
    (. b outputGcsUri v))
   (clojure.core/when-some
    [v (:output-mime-type data)]
    (. b outputMimeType v))
   (clojure.core/when-some
    [v (:person-generation data)]
    (. b personGeneration v))
   (clojure.core/when-some
    [v (:safety-filter-level data)]
    (. b safetyFilterLevel v))
   (clojure.core/when-some [v (:seed data)] (. b seed v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RecontextImageConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :add-watermark
    (. x addWatermark))
   (io.kosong.autovalue/optional-datafy-assoc
    :base-steps
    (. x baseSteps))
   (io.kosong.autovalue/optional-datafy-assoc
    :enhance-prompt
    (. x enhancePrompt))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc
    :number-of-images
    (. x numberOfImages))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-compression-quality
    (. x outputCompressionQuality))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-gcs-uri
    (. x outputGcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-mime-type
    (. x outputMimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :person-generation
    (. x personGeneration))
   (io.kosong.autovalue/optional-datafy-assoc
    :safety-filter-level
    (. x safetyFilterLevel))
   (io.kosong.autovalue/optional-datafy-assoc :seed (. x seed))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RecontextImageParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RecontextImageParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RecontextImageParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.RecontextImageConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some
    [v (:source data)]
    (.
     b
     source
     (io.kosong.java/make-object
      com.google.genai.types.RecontextImageSource
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RecontextImageParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc :source (. x source))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RecontextImageResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RecontextImageResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RecontextImageResponse/builder)]
   (clojure.core/when-some
    [v (:generated-images data)]
    (.
     b
     generatedImages
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GeneratedImage
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RecontextImageResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :generated-images
    (. x generatedImages))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RecontextImageSource
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RecontextImageSource
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RecontextImageSource/builder)]
   (clojure.core/when-some
    [v (:person-image data)]
    (.
     b
     personImage
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:product-images data)]
    (.
     b
     productImages
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ProductImage
        x))
      v)))
   (clojure.core/when-some [v (:prompt data)] (. b prompt v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RecontextImageSource
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :person-image
    (. x personImage))
   (io.kosong.autovalue/optional-datafy-assoc
    :product-images
    (. x productImages))
   (io.kosong.autovalue/optional-datafy-assoc :prompt (. x prompt))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ReferenceImageAPI
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ReferenceImageAPI
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ReferenceImageAPI/builder)]
   (clojure.core/when-some
    [v (:control-image-config data)]
    (.
     b
     controlImageConfig
     (io.kosong.java/make-object
      com.google.genai.types.ControlReferenceConfig
      v)))
   (clojure.core/when-some
    [v (:mask-image-config data)]
    (.
     b
     maskImageConfig
     (io.kosong.java/make-object
      com.google.genai.types.MaskReferenceConfig
      v)))
   (clojure.core/when-some
    [v (:reference-id data)]
    (. b referenceId v))
   (clojure.core/when-some
    [v (:reference-image data)]
    (.
     b
     referenceImage
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:reference-type data)]
    (. b referenceType v))
   (clojure.core/when-some
    [v (:style-image-config data)]
    (.
     b
     styleImageConfig
     (io.kosong.java/make-object
      com.google.genai.types.StyleReferenceConfig
      v)))
   (clojure.core/when-some
    [v (:subject-image-config data)]
    (.
     b
     subjectImageConfig
     (io.kosong.java/make-object
      com.google.genai.types.SubjectReferenceConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ReferenceImageAPI
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :control-image-config
    (. x controlImageConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :mask-image-config
    (. x maskImageConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-id
    (. x referenceId))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-image
    (. x referenceImage))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-type
    (. x referenceType))
   (io.kosong.autovalue/optional-datafy-assoc
    :style-image-config
    (. x styleImageConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :subject-image-config
    (. x subjectImageConfig))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ReplayFile
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ReplayFile data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ReplayFile/builder)]
   (clojure.core/when-some
    [v (:interactions data)]
    (.
     b
     interactions
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ReplayInteraction
        x))
      v)))
   (clojure.core/when-some [v (:replay-id data)] (. b replayId v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ReplayFile
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :interactions
    (. x interactions))
   (io.kosong.autovalue/optional-datafy-assoc
    :replay-id
    (. x replayId))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ReplayInteraction
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ReplayInteraction
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ReplayInteraction/builder)]
   (clojure.core/when-some
    [v (:request data)]
    (.
     b
     request
     (io.kosong.java/make-object
      com.google.genai.types.ReplayRequest
      v)))
   (clojure.core/when-some
    [v (:response data)]
    (.
     b
     response
     (io.kosong.java/make-object
      com.google.genai.types.ReplayResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ReplayInteraction
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :request (. x request))
   (io.kosong.autovalue/optional-datafy-assoc :response (. x response))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ReplayRequest
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ReplayRequest data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ReplayRequest/builder)]
   (clojure.core/when-some
    [v (:body-segments data)]
    (. b bodySegments v))
   (clojure.core/when-some [v (:headers data)] (. b headers v))
   (clojure.core/when-some [v (:method data)] (. b method v))
   (clojure.core/when-some [v (:url data)] (. b url v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ReplayRequest
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :body-segments
    (. x bodySegments))
   (io.kosong.autovalue/optional-datafy-assoc :headers (. x headers))
   (io.kosong.autovalue/optional-datafy-assoc :method (. x method))
   (io.kosong.autovalue/optional-datafy-assoc :url (. x url))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ReplayResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ReplayResponse data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ReplayResponse/builder)]
   (clojure.core/when-some
    [v (:body-segments data)]
    (. b bodySegments v))
   (clojure.core/when-some [v (:headers data)] (. b headers v))
   (clojure.core/when-some
    [v (:sdk-response-segments data)]
    (. b sdkResponseSegments v))
   (clojure.core/when-some [v (:status-code data)] (. b statusCode v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ReplayResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :body-segments
    (. x bodySegments))
   (io.kosong.autovalue/optional-datafy-assoc :headers (. x headers))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-response-segments
    (. x sdkResponseSegments))
   (io.kosong.autovalue/optional-datafy-assoc
    :status-code
    (. x statusCode))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ReplicatedVoiceConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ReplicatedVoiceConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ReplicatedVoiceConfig/builder)]
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (clojure.core/when-some
    [v (:voice-sample-audio data)]
    (. b voiceSampleAudio v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ReplicatedVoiceConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :voice-sample-audio
    (. x voiceSampleAudio))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Retrieval
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Retrieval data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Retrieval/builder)]
   (clojure.core/when-some
    [v (:disable-attribution data)]
    (. b disableAttribution v))
   (clojure.core/when-some
    [v (:external-api data)]
    (.
     b
     externalApi
     (io.kosong.java/make-object
      com.google.genai.types.ExternalApi
      v)))
   (clojure.core/when-some
    [v (:vertex-ai-search data)]
    (.
     b
     vertexAiSearch
     (io.kosong.java/make-object
      com.google.genai.types.VertexAISearch
      v)))
   (clojure.core/when-some
    [v (:vertex-rag-store data)]
    (.
     b
     vertexRagStore
     (io.kosong.java/make-object
      com.google.genai.types.VertexRagStore
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Retrieval
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :disable-attribution
    (. x disableAttribution))
   (io.kosong.autovalue/optional-datafy-assoc
    :external-api
    (. x externalApi))
   (io.kosong.autovalue/optional-datafy-assoc
    :vertex-ai-search
    (. x vertexAiSearch))
   (io.kosong.autovalue/optional-datafy-assoc
    :vertex-rag-store
    (. x vertexRagStore))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RetrievalConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.RetrievalConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RetrievalConfig/builder)]
   (clojure.core/when-some
    [v (:language-code data)]
    (. b languageCode v))
   (clojure.core/when-some
    [v (:lat-lng data)]
    (.
     b
     latLng
     (io.kosong.java/make-object com.google.genai.types.LatLng v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RetrievalConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :language-code
    (. x languageCode))
   (io.kosong.autovalue/optional-datafy-assoc :lat-lng (. x latLng))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.RetrievalMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.RetrievalMetadata
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.RetrievalMetadata/builder)]
   (clojure.core/when-some
    [v (:google-search-dynamic-retrieval-score data)]
    (. b googleSearchDynamicRetrievalScore v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.RetrievalMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :google-search-dynamic-retrieval-score
    (. x googleSearchDynamicRetrievalScore))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SafetyAttributes
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.SafetyAttributes data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SafetyAttributes/builder)]
   (clojure.core/when-some [v (:categories data)] (. b categories v))
   (clojure.core/when-some
    [v (:content-type data)]
    (. b contentType v))
   (clojure.core/when-some [v (:scores data)] (. b scores v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SafetyAttributes
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :categories
    (. x categories))
   (io.kosong.autovalue/optional-datafy-assoc
    :content-type
    (. x contentType))
   (io.kosong.autovalue/optional-datafy-assoc :scores (. x scores))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SafetyRating
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.SafetyRating data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SafetyRating/builder)]
   (clojure.core/when-some [v (:blocked data)] (. b blocked v))
   (clojure.core/when-some [v (:category data)] (. b category v))
   (clojure.core/when-some
    [v (:overwritten-threshold data)]
    (. b overwrittenThreshold v))
   (clojure.core/when-some [v (:probability data)] (. b probability v))
   (clojure.core/when-some
    [v (:probability-score data)]
    (. b probabilityScore v))
   (clojure.core/when-some [v (:severity data)] (. b severity v))
   (clojure.core/when-some
    [v (:severity-score data)]
    (. b severityScore v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SafetyRating
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :blocked (. x blocked))
   (io.kosong.autovalue/optional-datafy-assoc :category (. x category))
   (io.kosong.autovalue/optional-datafy-assoc
    :overwritten-threshold
    (. x overwrittenThreshold))
   (io.kosong.autovalue/optional-datafy-assoc
    :probability
    (. x probability))
   (io.kosong.autovalue/optional-datafy-assoc
    :probability-score
    (. x probabilityScore))
   (io.kosong.autovalue/optional-datafy-assoc :severity (. x severity))
   (io.kosong.autovalue/optional-datafy-assoc
    :severity-score
    (. x severityScore))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SafetySetting
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.SafetySetting data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SafetySetting/builder)]
   (clojure.core/when-some [v (:category data)] (. b category v))
   (clojure.core/when-some [v (:method data)] (. b method v))
   (clojure.core/when-some [v (:threshold data)] (. b threshold v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SafetySetting
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :category (. x category))
   (io.kosong.autovalue/optional-datafy-assoc :method (. x method))
   (io.kosong.autovalue/optional-datafy-assoc
    :threshold
    (. x threshold))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Schema
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Schema data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Schema/builder)]
   (clojure.core/when-some
    [v (:any-of data)]
    (.
     b
     anyOf
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Schema x))
      v)))
   (clojure.core/when-some [v (:default data)] (. b default_ v))
   (clojure.core/when-some [v (:description data)] (. b description v))
   (clojure.core/when-some [v (:enum data)] (. b enum_ v))
   (clojure.core/when-some [v (:example data)] (. b example v))
   (clojure.core/when-some [v (:format data)] (. b format v))
   (clojure.core/when-some
    [v (:items data)]
    (.
     b
     items
     (io.kosong.java/make-object com.google.genai.types.Schema v)))
   (clojure.core/when-some [v (:max-items data)] (. b maxItems v))
   (clojure.core/when-some [v (:max-length data)] (. b maxLength v))
   (clojure.core/when-some
    [v (:max-properties data)]
    (. b maxProperties v))
   (clojure.core/when-some [v (:maximum data)] (. b maximum v))
   (clojure.core/when-some [v (:min-items data)] (. b minItems v))
   (clojure.core/when-some [v (:min-length data)] (. b minLength v))
   (clojure.core/when-some
    [v (:min-properties data)]
    (. b minProperties v))
   (clojure.core/when-some [v (:minimum data)] (. b minimum v))
   (clojure.core/when-some [v (:nullable data)] (. b nullable v))
   (clojure.core/when-some [v (:pattern data)] (. b pattern v))
   (clojure.core/when-some [v (:properties data)] (. b properties v))
   (clojure.core/when-some
    [v (:property-ordering data)]
    (. b propertyOrdering v))
   (clojure.core/when-some [v (:required data)] (. b required v))
   (clojure.core/when-some [v (:title data)] (. b title v))
   (clojure.core/when-some [v (:type data)] (. b type v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Schema
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :any-of (. x anyOf))
   (io.kosong.autovalue/optional-datafy-assoc :default (. x default_))
   (io.kosong.autovalue/optional-datafy-assoc
    :description
    (. x description))
   (io.kosong.autovalue/optional-datafy-assoc :enum (. x enum_))
   (io.kosong.autovalue/optional-datafy-assoc :example (. x example))
   (io.kosong.autovalue/optional-datafy-assoc :format (. x format))
   (io.kosong.autovalue/optional-datafy-assoc :items (. x items))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-items
    (. x maxItems))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-length
    (. x maxLength))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-properties
    (. x maxProperties))
   (io.kosong.autovalue/optional-datafy-assoc :maximum (. x maximum))
   (io.kosong.autovalue/optional-datafy-assoc
    :min-items
    (. x minItems))
   (io.kosong.autovalue/optional-datafy-assoc
    :min-length
    (. x minLength))
   (io.kosong.autovalue/optional-datafy-assoc
    :min-properties
    (. x minProperties))
   (io.kosong.autovalue/optional-datafy-assoc :minimum (. x minimum))
   (io.kosong.autovalue/optional-datafy-assoc :nullable (. x nullable))
   (io.kosong.autovalue/optional-datafy-assoc :pattern (. x pattern))
   (io.kosong.autovalue/optional-datafy-assoc
    :properties
    (. x properties))
   (io.kosong.autovalue/optional-datafy-assoc
    :property-ordering
    (. x propertyOrdering))
   (io.kosong.autovalue/optional-datafy-assoc :required (. x required))
   (io.kosong.autovalue/optional-datafy-assoc :title (. x title))
   (io.kosong.autovalue/optional-datafy-assoc :type (. x type))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ScribbleImage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ScribbleImage data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ScribbleImage/builder)]
   (clojure.core/when-some
    [v (:image data)]
    (.
     b
     image
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ScribbleImage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :image (. x image))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SearchEntryPoint
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.SearchEntryPoint data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SearchEntryPoint/builder)]
   (clojure.core/when-some
    [v (:rendered-content data)]
    (. b renderedContent v))
   (clojure.core/when-some [v (:sdk-blob data)] (. b sdkBlob v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SearchEntryPoint
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :rendered-content
    (. x renderedContent))
   (io.kosong.autovalue/optional-datafy-assoc :sdk-blob (. x sdkBlob))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Segment
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Segment data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Segment/builder)]
   (clojure.core/when-some [v (:end-index data)] (. b endIndex v))
   (clojure.core/when-some [v (:part-index data)] (. b partIndex v))
   (clojure.core/when-some [v (:start-index data)] (. b startIndex v))
   (clojure.core/when-some [v (:text data)] (. b text v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Segment
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :end-index
    (. x endIndex))
   (io.kosong.autovalue/optional-datafy-assoc
    :part-index
    (. x partIndex))
   (io.kosong.autovalue/optional-datafy-assoc
    :start-index
    (. x startIndex))
   (io.kosong.autovalue/optional-datafy-assoc :text (. x text))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SegmentImageConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SegmentImageConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SegmentImageConfig/builder)]
   (clojure.core/when-some
    [v (:binary-color-threshold data)]
    (. b binaryColorThreshold v))
   (clojure.core/when-some
    [v (:confidence-threshold data)]
    (. b confidenceThreshold v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:labels data)] (. b labels v))
   (clojure.core/when-some
    [v (:mask-dilation data)]
    (. b maskDilation v))
   (clojure.core/when-some
    [v (:max-predictions data)]
    (. b maxPredictions v))
   (clojure.core/when-some [v (:mode data)] (. b mode v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SegmentImageConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :binary-color-threshold
    (. x binaryColorThreshold))
   (io.kosong.autovalue/optional-datafy-assoc
    :confidence-threshold
    (. x confidenceThreshold))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc
    :mask-dilation
    (. x maskDilation))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-predictions
    (. x maxPredictions))
   (io.kosong.autovalue/optional-datafy-assoc :mode (. x mode))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SegmentImageParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SegmentImageParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SegmentImageParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.SegmentImageConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some
    [v (:source data)]
    (.
     b
     source
     (io.kosong.java/make-object
      com.google.genai.types.SegmentImageSource
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SegmentImageParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc :source (. x source))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SegmentImageResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SegmentImageResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SegmentImageResponse/builder)]
   (clojure.core/when-some
    [v (:generated-masks data)]
    (.
     b
     generatedMasks
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GeneratedImageMask
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SegmentImageResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :generated-masks
    (. x generatedMasks))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SegmentImageSource
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SegmentImageSource
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SegmentImageSource/builder)]
   (clojure.core/when-some
    [v (:image data)]
    (.
     b
     image
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some [v (:prompt data)] (. b prompt v))
   (clojure.core/when-some
    [v (:scribble-image data)]
    (.
     b
     scribbleImage
     (io.kosong.java/make-object
      com.google.genai.types.ScribbleImage
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SegmentImageSource
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :image (. x image))
   (io.kosong.autovalue/optional-datafy-assoc :prompt (. x prompt))
   (io.kosong.autovalue/optional-datafy-assoc
    :scribble-image
    (. x scribbleImage))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SessionResumptionConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SessionResumptionConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SessionResumptionConfig/builder)]
   (clojure.core/when-some [v (:handle data)] (. b handle v))
   (clojure.core/when-some [v (:transparent data)] (. b transparent v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SessionResumptionConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :handle (. x handle))
   (io.kosong.autovalue/optional-datafy-assoc
    :transparent
    (. x transparent))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SingleEmbedContentResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SingleEmbedContentResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SingleEmbedContentResponse/builder)]
   (clojure.core/when-some
    [v (:embedding data)]
    (.
     b
     embedding
     (io.kosong.java/make-object
      com.google.genai.types.ContentEmbedding
      v)))
   (clojure.core/when-some [v (:token-count data)] (. b tokenCount v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SingleEmbedContentResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :embedding
    (. x embedding))
   (io.kosong.autovalue/optional-datafy-assoc
    :token-count
    (. x tokenCount))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SlidingWindow
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.SlidingWindow data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SlidingWindow/builder)]
   (clojure.core/when-some
    [v (:target-tokens data)]
    (. b targetTokens v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SlidingWindow
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :target-tokens
    (. x targetTokens))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SpeakerVoiceConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SpeakerVoiceConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SpeakerVoiceConfig/builder)]
   (clojure.core/when-some [v (:speaker data)] (. b speaker v))
   (clojure.core/when-some
    [v (:voice-config data)]
    (.
     b
     voiceConfig
     (io.kosong.java/make-object
      com.google.genai.types.VoiceConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SpeakerVoiceConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :speaker (. x speaker))
   (io.kosong.autovalue/optional-datafy-assoc
    :voice-config
    (. x voiceConfig))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SpeechConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.SpeechConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SpeechConfig/builder)]
   (clojure.core/when-some
    [v (:language-code data)]
    (. b languageCode v))
   (clojure.core/when-some
    [v (:multi-speaker-voice-config data)]
    (.
     b
     multiSpeakerVoiceConfig
     (io.kosong.java/make-object
      com.google.genai.types.MultiSpeakerVoiceConfig
      v)))
   (clojure.core/when-some
    [v (:voice-config data)]
    (.
     b
     voiceConfig
     (io.kosong.java/make-object
      com.google.genai.types.VoiceConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SpeechConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :language-code
    (. x languageCode))
   (io.kosong.autovalue/optional-datafy-assoc
    :multi-speaker-voice-config
    (. x multiSpeakerVoiceConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :voice-config
    (. x voiceConfig))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.StringList
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.StringList data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.StringList/builder)]
   (clojure.core/when-some [v (:values data)] (. b values v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.StringList
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :values (. x values))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.StyleReferenceConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.StyleReferenceConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.StyleReferenceConfig/builder)]
   (clojure.core/when-some
    [v (:style-description data)]
    (. b styleDescription v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.StyleReferenceConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :style-description
    (. x styleDescription))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.StyleReferenceImage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.StyleReferenceImage
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.StyleReferenceImage/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.StyleReferenceConfig
      v)))
   (clojure.core/when-some
    [v (:reference-id data)]
    (. b referenceId v))
   (clojure.core/when-some
    [v (:reference-image data)]
    (.
     b
     referenceImage
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:reference-type data)]
    (. b referenceType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.StyleReferenceImage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-id
    (. x referenceId))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-image
    (. x referenceImage))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-type
    (. x referenceType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SubjectReferenceConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SubjectReferenceConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SubjectReferenceConfig/builder)]
   (clojure.core/when-some
    [v (:subject-description data)]
    (. b subjectDescription v))
   (clojure.core/when-some
    [v (:subject-type data)]
    (. b subjectType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SubjectReferenceConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :subject-description
    (. x subjectDescription))
   (io.kosong.autovalue/optional-datafy-assoc
    :subject-type
    (. x subjectType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SubjectReferenceImage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SubjectReferenceImage
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SubjectReferenceImage/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.SubjectReferenceConfig
      v)))
   (clojure.core/when-some
    [v (:reference-id data)]
    (. b referenceId v))
   (clojure.core/when-some
    [v (:reference-image data)]
    (.
     b
     referenceImage
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:reference-type data)]
    (. b referenceType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SubjectReferenceImage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-id
    (. x referenceId))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-image
    (. x referenceImage))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-type
    (. x referenceType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SupervisedHyperParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SupervisedHyperParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SupervisedHyperParameters/builder)]
   (clojure.core/when-some
    [v (:adapter-size data)]
    (. b adapterSize v))
   (clojure.core/when-some [v (:batch-size data)] (. b batchSize v))
   (clojure.core/when-some [v (:epoch-count data)] (. b epochCount v))
   (clojure.core/when-some
    [v (:learning-rate data)]
    (. b learningRate v))
   (clojure.core/when-some
    [v (:learning-rate-multiplier data)]
    (. b learningRateMultiplier v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SupervisedHyperParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :adapter-size
    (. x adapterSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :batch-size
    (. x batchSize))
   (io.kosong.autovalue/optional-datafy-assoc
    :epoch-count
    (. x epochCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :learning-rate
    (. x learningRate))
   (io.kosong.autovalue/optional-datafy-assoc
    :learning-rate-multiplier
    (. x learningRateMultiplier))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SupervisedTuningDataStats
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SupervisedTuningDataStats
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SupervisedTuningDataStats/builder)]
   (clojure.core/when-some
    [v (:dropped-example-reasons data)]
    (. b droppedExampleReasons v))
   (clojure.core/when-some
    [v (:total-billable-character-count data)]
    (. b totalBillableCharacterCount v))
   (clojure.core/when-some
    [v (:total-billable-token-count data)]
    (. b totalBillableTokenCount v))
   (clojure.core/when-some
    [v (:total-truncated-example-count data)]
    (. b totalTruncatedExampleCount v))
   (clojure.core/when-some
    [v (:total-tuning-character-count data)]
    (. b totalTuningCharacterCount v))
   (clojure.core/when-some
    [v (:truncated-example-indices data)]
    (. b truncatedExampleIndices v))
   (clojure.core/when-some
    [v (:tuning-dataset-example-count data)]
    (. b tuningDatasetExampleCount v))
   (clojure.core/when-some
    [v (:tuning-step-count data)]
    (. b tuningStepCount v))
   (clojure.core/when-some
    [v (:user-dataset-examples data)]
    (.
     b
     userDatasetExamples
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object com.google.genai.types.Content x))
      v)))
   (clojure.core/when-some
    [v (:user-input-token-distribution data)]
    (.
     b
     userInputTokenDistribution
     (io.kosong.java/make-object
      com.google.genai.types.SupervisedTuningDatasetDistribution
      v)))
   (clojure.core/when-some
    [v (:user-message-per-example-distribution data)]
    (.
     b
     userMessagePerExampleDistribution
     (io.kosong.java/make-object
      com.google.genai.types.SupervisedTuningDatasetDistribution
      v)))
   (clojure.core/when-some
    [v (:user-output-token-distribution data)]
    (.
     b
     userOutputTokenDistribution
     (io.kosong.java/make-object
      com.google.genai.types.SupervisedTuningDatasetDistribution
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SupervisedTuningDataStats
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :dropped-example-reasons
    (. x droppedExampleReasons))
   (io.kosong.autovalue/optional-datafy-assoc
    :total-billable-character-count
    (. x totalBillableCharacterCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :total-billable-token-count
    (. x totalBillableTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :total-truncated-example-count
    (. x totalTruncatedExampleCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :total-tuning-character-count
    (. x totalTuningCharacterCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :truncated-example-indices
    (. x truncatedExampleIndices))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuning-dataset-example-count
    (. x tuningDatasetExampleCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuning-step-count
    (. x tuningStepCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-dataset-examples
    (. x userDatasetExamples))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-input-token-distribution
    (. x userInputTokenDistribution))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-message-per-example-distribution
    (. x userMessagePerExampleDistribution))
   (io.kosong.autovalue/optional-datafy-assoc
    :user-output-token-distribution
    (. x userOutputTokenDistribution))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SupervisedTuningDatasetDistribution
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SupervisedTuningDatasetDistribution
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.SupervisedTuningDatasetDistribution/builder)]
   (clojure.core/when-some
    [v (:billable-sum data)]
    (. b billableSum v))
   (clojure.core/when-some
    [v (:buckets data)]
    (.
     b
     buckets
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.SupervisedTuningDatasetDistributionDatasetBucket
        x))
      v)))
   (clojure.core/when-some [v (:max data)] (. b max v))
   (clojure.core/when-some [v (:mean data)] (. b mean v))
   (clojure.core/when-some [v (:median data)] (. b median v))
   (clojure.core/when-some [v (:min data)] (. b min v))
   (clojure.core/when-some [v (:p-5 data)] (. b p5 v))
   (clojure.core/when-some [v (:p-95 data)] (. b p95 v))
   (clojure.core/when-some [v (:sum data)] (. b sum v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SupervisedTuningDatasetDistribution
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :billable-sum
    (. x billableSum))
   (io.kosong.autovalue/optional-datafy-assoc :buckets (. x buckets))
   (io.kosong.autovalue/optional-datafy-assoc :max (. x max))
   (io.kosong.autovalue/optional-datafy-assoc :mean (. x mean))
   (io.kosong.autovalue/optional-datafy-assoc :median (. x median))
   (io.kosong.autovalue/optional-datafy-assoc :min (. x min))
   (io.kosong.autovalue/optional-datafy-assoc :p-5 (. x p5))
   (io.kosong.autovalue/optional-datafy-assoc :p-95 (. x p95))
   (io.kosong.autovalue/optional-datafy-assoc :sum (. x sum))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SupervisedTuningDatasetDistributionDatasetBucket
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SupervisedTuningDatasetDistributionDatasetBucket
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.SupervisedTuningDatasetDistributionDatasetBucket/builder)]
   (clojure.core/when-some [v (:count data)] (. b count v))
   (clojure.core/when-some [v (:left data)] (. b left v))
   (clojure.core/when-some [v (:right data)] (. b right v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SupervisedTuningDatasetDistributionDatasetBucket
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :count (. x count))
   (io.kosong.autovalue/optional-datafy-assoc :left (. x left))
   (io.kosong.autovalue/optional-datafy-assoc :right (. x right))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.SupervisedTuningSpec
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.SupervisedTuningSpec
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.SupervisedTuningSpec/builder)]
   (clojure.core/when-some
    [v (:export-last-checkpoint-only data)]
    (. b exportLastCheckpointOnly v))
   (clojure.core/when-some
    [v (:hyper-parameters data)]
    (.
     b
     hyperParameters
     (io.kosong.java/make-object
      com.google.genai.types.SupervisedHyperParameters
      v)))
   (clojure.core/when-some
    [v (:training-dataset-uri data)]
    (. b trainingDatasetUri v))
   (clojure.core/when-some [v (:tuning-mode data)] (. b tuningMode v))
   (clojure.core/when-some
    [v (:validation-dataset-uri data)]
    (. b validationDatasetUri v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.SupervisedTuningSpec
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :export-last-checkpoint-only
    (. x exportLastCheckpointOnly))
   (io.kosong.autovalue/optional-datafy-assoc
    :hyper-parameters
    (. x hyperParameters))
   (io.kosong.autovalue/optional-datafy-assoc
    :training-dataset-uri
    (. x trainingDatasetUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuning-mode
    (. x tuningMode))
   (io.kosong.autovalue/optional-datafy-assoc
    :validation-dataset-uri
    (. x validationDatasetUri))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TestTableFile
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.TestTableFile data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TestTableFile/builder)]
   (clojure.core/when-some [v (:comment data)] (. b comment v))
   (clojure.core/when-some
    [v (:parameter-names data)]
    (. b parameterNames v))
   (clojure.core/when-some [v (:test-method data)] (. b testMethod v))
   (clojure.core/when-some
    [v (:test-table data)]
    (.
     b
     testTable
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.TestTableItem
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TestTableFile
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :comment (. x comment))
   (io.kosong.autovalue/optional-datafy-assoc
    :parameter-names
    (. x parameterNames))
   (io.kosong.autovalue/optional-datafy-assoc
    :test-method
    (. x testMethod))
   (io.kosong.autovalue/optional-datafy-assoc
    :test-table
    (. x testTable))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TestTableItem
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.TestTableItem data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TestTableItem/builder)]
   (clojure.core/when-some
    [v (:exception-if-mldev data)]
    (. b exceptionIfMldev v))
   (clojure.core/when-some
    [v (:exception-if-vertex data)]
    (. b exceptionIfVertex v))
   (clojure.core/when-some [v (:has-union data)] (. b hasUnion v))
   (clojure.core/when-some [v (:ignore-keys data)] (. b ignoreKeys v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:override-replay-id data)]
    (. b overrideReplayId v))
   (clojure.core/when-some [v (:parameters data)] (. b parameters v))
   (clojure.core/when-some
    [v (:skip-in-api-mode data)]
    (. b skipInApiMode v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TestTableItem
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :exception-if-mldev
    (. x exceptionIfMldev))
   (io.kosong.autovalue/optional-datafy-assoc
    :exception-if-vertex
    (. x exceptionIfVertex))
   (io.kosong.autovalue/optional-datafy-assoc
    :has-union
    (. x hasUnion))
   (io.kosong.autovalue/optional-datafy-assoc
    :ignore-keys
    (. x ignoreKeys))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :override-replay-id
    (. x overrideReplayId))
   (io.kosong.autovalue/optional-datafy-assoc
    :parameters
    (. x parameters))
   (io.kosong.autovalue/optional-datafy-assoc
    :skip-in-api-mode
    (. x skipInApiMode))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ThinkingConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ThinkingConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ThinkingConfig/builder)]
   (clojure.core/when-some
    [v (:include-thoughts data)]
    (. b includeThoughts v))
   (clojure.core/when-some
    [v (:thinking-budget data)]
    (. b thinkingBudget v))
   (clojure.core/when-some
    [v (:thinking-level data)]
    (. b thinkingLevel v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ThinkingConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :include-thoughts
    (. x includeThoughts))
   (io.kosong.autovalue/optional-datafy-assoc
    :thinking-budget
    (. x thinkingBudget))
   (io.kosong.autovalue/optional-datafy-assoc
    :thinking-level
    (. x thinkingLevel))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TokensInfo
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.TokensInfo data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TokensInfo/builder)]
   (clojure.core/when-some [v (:role data)] (. b role v))
   (clojure.core/when-some [v (:token-ids data)] (. b tokenIds v))
   (clojure.core/when-some [v (:tokens data)] (. b tokens v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TokensInfo
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :role (. x role))
   (io.kosong.autovalue/optional-datafy-assoc
    :token-ids
    (. x tokenIds))
   (io.kosong.autovalue/optional-datafy-assoc :tokens (. x tokens))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Tool
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Tool data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Tool/builder)]
   (clojure.core/when-some
    [v (:code-execution data)]
    (.
     b
     codeExecution
     (io.kosong.java/make-object
      com.google.genai.types.ToolCodeExecution
      v)))
   (clojure.core/when-some
    [v (:computer-use data)]
    (.
     b
     computerUse
     (io.kosong.java/make-object
      com.google.genai.types.ComputerUse
      v)))
   (clojure.core/when-some
    [v (:enterprise-web-search data)]
    (.
     b
     enterpriseWebSearch
     (io.kosong.java/make-object
      com.google.genai.types.EnterpriseWebSearch
      v)))
   (clojure.core/when-some
    [v (:file-search data)]
    (.
     b
     fileSearch
     (io.kosong.java/make-object com.google.genai.types.FileSearch v)))
   (clojure.core/when-some
    [v (:function-declarations data)]
    (.
     b
     functionDeclarations
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.FunctionDeclaration
        x))
      v)))
   (clojure.core/when-some [v (:functions data)] (. b functions v))
   (clojure.core/when-some
    [v (:google-maps data)]
    (.
     b
     googleMaps
     (io.kosong.java/make-object com.google.genai.types.GoogleMaps v)))
   (clojure.core/when-some
    [v (:google-search data)]
    (.
     b
     googleSearch
     (io.kosong.java/make-object
      com.google.genai.types.GoogleSearch
      v)))
   (clojure.core/when-some
    [v (:google-search-retrieval data)]
    (.
     b
     googleSearchRetrieval
     (io.kosong.java/make-object
      com.google.genai.types.GoogleSearchRetrieval
      v)))
   (clojure.core/when-some
    [v (:retrieval data)]
    (.
     b
     retrieval
     (io.kosong.java/make-object com.google.genai.types.Retrieval v)))
   (clojure.core/when-some
    [v (:url-context data)]
    (.
     b
     urlContext
     (io.kosong.java/make-object com.google.genai.types.UrlContext v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Tool
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :code-execution
    (. x codeExecution))
   (io.kosong.autovalue/optional-datafy-assoc
    :computer-use
    (. x computerUse))
   (io.kosong.autovalue/optional-datafy-assoc
    :enterprise-web-search
    (. x enterpriseWebSearch))
   (io.kosong.autovalue/optional-datafy-assoc
    :file-search
    (. x fileSearch))
   (io.kosong.autovalue/optional-datafy-assoc
    :function-declarations
    (. x functionDeclarations))
   (io.kosong.autovalue/optional-datafy-assoc
    :functions
    (. x functions))
   (io.kosong.autovalue/optional-datafy-assoc
    :google-maps
    (. x googleMaps))
   (io.kosong.autovalue/optional-datafy-assoc
    :google-search
    (. x googleSearch))
   (io.kosong.autovalue/optional-datafy-assoc
    :google-search-retrieval
    (. x googleSearchRetrieval))
   (io.kosong.autovalue/optional-datafy-assoc
    :retrieval
    (. x retrieval))
   (io.kosong.autovalue/optional-datafy-assoc
    :url-context
    (. x urlContext))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ToolCodeExecution
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.ToolCodeExecution
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ToolCodeExecution/builder)]
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ToolCodeExecution
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.ToolConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.ToolConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.ToolConfig/builder)]
   (clojure.core/when-some
    [v (:function-calling-config data)]
    (.
     b
     functionCallingConfig
     (io.kosong.java/make-object
      com.google.genai.types.FunctionCallingConfig
      v)))
   (clojure.core/when-some
    [v (:retrieval-config data)]
    (.
     b
     retrievalConfig
     (io.kosong.java/make-object
      com.google.genai.types.RetrievalConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.ToolConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :function-calling-config
    (. x functionCallingConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :retrieval-config
    (. x retrievalConfig))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Transcription
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Transcription data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Transcription/builder)]
   (clojure.core/when-some [v (:finished data)] (. b finished v))
   (clojure.core/when-some [v (:text data)] (. b text v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Transcription
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :finished (. x finished))
   (io.kosong.autovalue/optional-datafy-assoc :text (. x text))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TunedModel
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.TunedModel data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TunedModel/builder)]
   (clojure.core/when-some
    [v (:checkpoints data)]
    (.
     b
     checkpoints
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.TunedModelCheckpoint
        x))
      v)))
   (clojure.core/when-some [v (:endpoint data)] (. b endpoint v))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TunedModel
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :checkpoints
    (. x checkpoints))
   (io.kosong.autovalue/optional-datafy-assoc :endpoint (. x endpoint))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TunedModelCheckpoint
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.TunedModelCheckpoint
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TunedModelCheckpoint/builder)]
   (clojure.core/when-some
    [v (:checkpoint-id data)]
    (. b checkpointId v))
   (clojure.core/when-some [v (:endpoint data)] (. b endpoint v))
   (clojure.core/when-some [v (:epoch data)] (. b epoch v))
   (clojure.core/when-some [v (:step data)] (. b step v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TunedModelCheckpoint
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :checkpoint-id
    (. x checkpointId))
   (io.kosong.autovalue/optional-datafy-assoc :endpoint (. x endpoint))
   (io.kosong.autovalue/optional-datafy-assoc :epoch (. x epoch))
   (io.kosong.autovalue/optional-datafy-assoc :step (. x step))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TunedModelInfo
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.TunedModelInfo data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TunedModelInfo/builder)]
   (clojure.core/when-some [v (:base-model data)] (. b baseModel v))
   (clojure.core/when-some [v (:create-time data)] (. b createTime v))
   (clojure.core/when-some [v (:update-time data)] (. b updateTime v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TunedModelInfo
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :base-model
    (. x baseModel))
   (io.kosong.autovalue/optional-datafy-assoc
    :create-time
    (. x createTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :update-time
    (. x updateTime))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TuningDataStats
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.TuningDataStats data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TuningDataStats/builder)]
   (clojure.core/when-some
    [v (:distillation-data-stats data)]
    (.
     b
     distillationDataStats
     (io.kosong.java/make-object
      com.google.genai.types.DistillationDataStats
      v)))
   (clojure.core/when-some
    [v (:preference-optimization-data-stats data)]
    (.
     b
     preferenceOptimizationDataStats
     (io.kosong.java/make-object
      com.google.genai.types.PreferenceOptimizationDataStats
      v)))
   (clojure.core/when-some
    [v (:supervised-tuning-data-stats data)]
    (.
     b
     supervisedTuningDataStats
     (io.kosong.java/make-object
      com.google.genai.types.SupervisedTuningDataStats
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TuningDataStats
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :distillation-data-stats
    (. x distillationDataStats))
   (io.kosong.autovalue/optional-datafy-assoc
    :preference-optimization-data-stats
    (. x preferenceOptimizationDataStats))
   (io.kosong.autovalue/optional-datafy-assoc
    :supervised-tuning-data-stats
    (. x supervisedTuningDataStats))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TuningDataset
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.TuningDataset data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TuningDataset/builder)]
   (clojure.core/when-some
    [v (:examples data)]
    (.
     b
     examples
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.TuningExample
        x))
      v)))
   (clojure.core/when-some [v (:gcs-uri data)] (. b gcsUri v))
   (clojure.core/when-some
    [v (:vertex-dataset-resource data)]
    (. b vertexDatasetResource v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TuningDataset
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :examples (. x examples))
   (io.kosong.autovalue/optional-datafy-assoc :gcs-uri (. x gcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :vertex-dataset-resource
    (. x vertexDatasetResource))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TuningExample
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.TuningExample data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TuningExample/builder)]
   (clojure.core/when-some [v (:output data)] (. b output v))
   (clojure.core/when-some [v (:text-input data)] (. b textInput v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TuningExample
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :output (. x output))
   (io.kosong.autovalue/optional-datafy-assoc
    :text-input
    (. x textInput))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TuningJob
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.TuningJob data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TuningJob/builder)]
   (clojure.core/when-some [v (:base-model data)] (. b baseModel v))
   (clojure.core/when-some [v (:create-time data)] (. b createTime v))
   (clojure.core/when-some
    [v (:custom-base-model data)]
    (. b customBaseModel v))
   (clojure.core/when-some [v (:description data)] (. b description v))
   (clojure.core/when-some
    [v (:encryption-spec data)]
    (.
     b
     encryptionSpec
     (io.kosong.java/make-object
      com.google.genai.types.EncryptionSpec
      v)))
   (clojure.core/when-some [v (:end-time data)] (. b endTime v))
   (clojure.core/when-some
    [v (:error data)]
    (.
     b
     error
     (io.kosong.java/make-object
      com.google.genai.types.GoogleRpcStatus
      v)))
   (clojure.core/when-some
    [v (:evaluation-config data)]
    (.
     b
     evaluationConfig
     (io.kosong.java/make-object
      com.google.genai.types.EvaluationConfig
      v)))
   (clojure.core/when-some [v (:experiment data)] (. b experiment v))
   (clojure.core/when-some [v (:labels data)] (. b labels v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some [v (:output-uri data)] (. b outputUri v))
   (clojure.core/when-some
    [v (:partner-model-tuning-spec data)]
    (.
     b
     partnerModelTuningSpec
     (io.kosong.java/make-object
      com.google.genai.types.PartnerModelTuningSpec
      v)))
   (clojure.core/when-some
    [v (:pipeline-job data)]
    (. b pipelineJob v))
   (clojure.core/when-some
    [v (:pre-tuned-model data)]
    (.
     b
     preTunedModel
     (io.kosong.java/make-object
      com.google.genai.types.PreTunedModel
      v)))
   (clojure.core/when-some
    [v (:preference-optimization-spec data)]
    (.
     b
     preferenceOptimizationSpec
     (io.kosong.java/make-object
      com.google.genai.types.PreferenceOptimizationSpec
      v)))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (clojure.core/when-some
    [v (:service-account data)]
    (. b serviceAccount v))
   (clojure.core/when-some [v (:start-time data)] (. b startTime v))
   (clojure.core/when-some [v (:state data)] (. b state v))
   (clojure.core/when-some
    [v (:supervised-tuning-spec data)]
    (.
     b
     supervisedTuningSpec
     (io.kosong.java/make-object
      com.google.genai.types.SupervisedTuningSpec
      v)))
   (clojure.core/when-some
    [v (:tuned-model data)]
    (.
     b
     tunedModel
     (io.kosong.java/make-object com.google.genai.types.TunedModel v)))
   (clojure.core/when-some
    [v (:tuned-model-display-name data)]
    (. b tunedModelDisplayName v))
   (clojure.core/when-some
    [v (:tuning-data-stats data)]
    (.
     b
     tuningDataStats
     (io.kosong.java/make-object
      com.google.genai.types.TuningDataStats
      v)))
   (clojure.core/when-some [v (:update-time data)] (. b updateTime v))
   (clojure.core/when-some
    [v (:veo-tuning-spec data)]
    (.
     b
     veoTuningSpec
     (io.kosong.java/make-object
      com.google.genai.types.VeoTuningSpec
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TuningJob
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :base-model
    (. x baseModel))
   (io.kosong.autovalue/optional-datafy-assoc
    :create-time
    (. x createTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :custom-base-model
    (. x customBaseModel))
   (io.kosong.autovalue/optional-datafy-assoc
    :description
    (. x description))
   (io.kosong.autovalue/optional-datafy-assoc
    :encryption-spec
    (. x encryptionSpec))
   (io.kosong.autovalue/optional-datafy-assoc :end-time (. x endTime))
   (io.kosong.autovalue/optional-datafy-assoc :error (. x error))
   (io.kosong.autovalue/optional-datafy-assoc
    :evaluation-config
    (. x evaluationConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :experiment
    (. x experiment))
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-uri
    (. x outputUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :partner-model-tuning-spec
    (. x partnerModelTuningSpec))
   (io.kosong.autovalue/optional-datafy-assoc
    :pipeline-job
    (. x pipelineJob))
   (io.kosong.autovalue/optional-datafy-assoc
    :pre-tuned-model
    (. x preTunedModel))
   (io.kosong.autovalue/optional-datafy-assoc
    :preference-optimization-spec
    (. x preferenceOptimizationSpec))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (io.kosong.autovalue/optional-datafy-assoc
    :service-account
    (. x serviceAccount))
   (io.kosong.autovalue/optional-datafy-assoc
    :start-time
    (. x startTime))
   (io.kosong.autovalue/optional-datafy-assoc :state (. x state))
   (io.kosong.autovalue/optional-datafy-assoc
    :supervised-tuning-spec
    (. x supervisedTuningSpec))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuned-model
    (. x tunedModel))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuned-model-display-name
    (. x tunedModelDisplayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuning-data-stats
    (. x tuningDataStats))
   (io.kosong.autovalue/optional-datafy-assoc
    :update-time
    (. x updateTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :veo-tuning-spec
    (. x veoTuningSpec))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TuningOperation
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.TuningOperation data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TuningOperation/builder)]
   (clojure.core/when-some [v (:done data)] (. b done v))
   (clojure.core/when-some [v (:error data)] (. b error v))
   (clojure.core/when-some [v (:metadata data)] (. b metadata v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TuningOperation
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :done (. x done))
   (io.kosong.autovalue/optional-datafy-assoc :error (. x error))
   (io.kosong.autovalue/optional-datafy-assoc :metadata (. x metadata))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.TuningValidationDataset
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.TuningValidationDataset
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.TuningValidationDataset/builder)]
   (clojure.core/when-some [v (:gcs-uri data)] (. b gcsUri v))
   (clojure.core/when-some
    [v (:vertex-dataset-resource data)]
    (. b vertexDatasetResource v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.TuningValidationDataset
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :gcs-uri (. x gcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :vertex-dataset-resource
    (. x vertexDatasetResource))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UpdateCachedContentConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UpdateCachedContentConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UpdateCachedContentConfig/builder)]
   (clojure.core/when-some [v (:expire-time data)] (. b expireTime v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:ttl data)] (. b ttl v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UpdateCachedContentConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :expire-time
    (. x expireTime))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc :ttl (. x ttl))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UpdateCachedContentParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UpdateCachedContentParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UpdateCachedContentParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.UpdateCachedContentConfig
      v)))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UpdateCachedContentParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UpdateModelConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UpdateModelConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UpdateModelConfig/builder)]
   (clojure.core/when-some
    [v (:default-checkpoint-id data)]
    (. b defaultCheckpointId v))
   (clojure.core/when-some [v (:description data)] (. b description v))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UpdateModelConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :default-checkpoint-id
    (. x defaultCheckpointId))
   (io.kosong.autovalue/optional-datafy-assoc
    :description
    (. x description))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UpdateModelParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UpdateModelParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UpdateModelParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.UpdateModelConfig
      v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UpdateModelParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UploadFileConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.UploadFileConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UploadFileConfig/builder)]
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UploadFileConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UploadToFileSearchStoreConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UploadToFileSearchStoreConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UploadToFileSearchStoreConfig/builder)]
   (clojure.core/when-some
    [v (:chunking-config data)]
    (.
     b
     chunkingConfig
     (io.kosong.java/make-object
      com.google.genai.types.ChunkingConfig
      v)))
   (clojure.core/when-some
    [v (:custom-metadata data)]
    (.
     b
     customMetadata
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.CustomMetadata
        x))
      v)))
   (clojure.core/when-some
    [v (:display-name data)]
    (. b displayName v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (clojure.core/when-some
    [v (:should-return-http-response data)]
    (. b shouldReturnHttpResponse v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UploadToFileSearchStoreConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :chunking-config
    (. x chunkingConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :custom-metadata
    (. x customMetadata))
   (io.kosong.autovalue/optional-datafy-assoc
    :display-name
    (. x displayName))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :should-return-http-response
    (. x shouldReturnHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UploadToFileSearchStoreOperation
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UploadToFileSearchStoreOperation
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.UploadToFileSearchStoreOperation/builder)]
   (clojure.core/when-some [v (:done data)] (. b done v))
   (clojure.core/when-some [v (:error data)] (. b error v))
   (clojure.core/when-some [v (:metadata data)] (. b metadata v))
   (clojure.core/when-some [v (:name data)] (. b name v))
   (clojure.core/when-some
    [v (:response data)]
    (.
     b
     response
     (io.kosong.java/make-object
      com.google.genai.types.UploadToFileSearchStoreResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UploadToFileSearchStoreOperation
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :done (. x done))
   (io.kosong.autovalue/optional-datafy-assoc :error (. x error))
   (io.kosong.autovalue/optional-datafy-assoc :metadata (. x metadata))
   (io.kosong.autovalue/optional-datafy-assoc :name (. x name))
   (io.kosong.autovalue/optional-datafy-assoc :response (. x response))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UploadToFileSearchStoreParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UploadToFileSearchStoreParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.UploadToFileSearchStoreParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.UploadToFileSearchStoreConfig
      v)))
   (clojure.core/when-some
    [v (:file-search-store-name data)]
    (. b fileSearchStoreName v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UploadToFileSearchStoreParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc
    :file-search-store-name
    (. x fileSearchStoreName))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UploadToFileSearchStoreResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UploadToFileSearchStoreResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UploadToFileSearchStoreResponse/builder)]
   (clojure.core/when-some
    [v (:document-name data)]
    (. b documentName v))
   (clojure.core/when-some [v (:parent data)] (. b parent v))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UploadToFileSearchStoreResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :document-name
    (. x documentName))
   (io.kosong.autovalue/optional-datafy-assoc :parent (. x parent))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UploadToFileSearchStoreResumableResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UploadToFileSearchStoreResumableResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b
    (com.google.genai.types.UploadToFileSearchStoreResumableResponse/builder)]
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UploadToFileSearchStoreResumableResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UpscaleImageAPIConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UpscaleImageAPIConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UpscaleImageAPIConfig/builder)]
   (clojure.core/when-some
    [v (:enhance-input-image data)]
    (. b enhanceInputImage v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some
    [v (:image-preservation-factor data)]
    (. b imagePreservationFactor v))
   (clojure.core/when-some
    [v (:include-rai-reason data)]
    (. b includeRaiReason v))
   (clojure.core/when-some [v (:labels data)] (. b labels v))
   (clojure.core/when-some [v (:mode data)] (. b mode v))
   (clojure.core/when-some
    [v (:number-of-images data)]
    (. b numberOfImages v))
   (clojure.core/when-some
    [v (:output-compression-quality data)]
    (. b outputCompressionQuality v))
   (clojure.core/when-some
    [v (:output-gcs-uri data)]
    (. b outputGcsUri v))
   (clojure.core/when-some
    [v (:output-mime-type data)]
    (. b outputMimeType v))
   (clojure.core/when-some
    [v (:person-generation data)]
    (. b personGeneration v))
   (clojure.core/when-some
    [v (:safety-filter-level data)]
    (. b safetyFilterLevel v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UpscaleImageAPIConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :enhance-input-image
    (. x enhanceInputImage))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :image-preservation-factor
    (. x imagePreservationFactor))
   (io.kosong.autovalue/optional-datafy-assoc
    :include-rai-reason
    (. x includeRaiReason))
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc :mode (. x mode))
   (io.kosong.autovalue/optional-datafy-assoc
    :number-of-images
    (. x numberOfImages))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-compression-quality
    (. x outputCompressionQuality))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-gcs-uri
    (. x outputGcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-mime-type
    (. x outputMimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :person-generation
    (. x personGeneration))
   (io.kosong.autovalue/optional-datafy-assoc
    :safety-filter-level
    (. x safetyFilterLevel))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UpscaleImageAPIParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UpscaleImageAPIParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UpscaleImageAPIParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.UpscaleImageAPIConfig
      v)))
   (clojure.core/when-some
    [v (:image data)]
    (.
     b
     image
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some
    [v (:upscale-factor data)]
    (. b upscaleFactor v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UpscaleImageAPIParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :image (. x image))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc
    :upscale-factor
    (. x upscaleFactor))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UpscaleImageConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UpscaleImageConfig
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UpscaleImageConfig/builder)]
   (clojure.core/when-some
    [v (:enhance-input-image data)]
    (. b enhanceInputImage v))
   (clojure.core/when-some
    [v (:http-options data)]
    (.
     b
     httpOptions
     (io.kosong.java/make-object
      com.google.genai.types.HttpOptions
      v)))
   (clojure.core/when-some
    [v (:image-preservation-factor data)]
    (. b imagePreservationFactor v))
   (clojure.core/when-some
    [v (:include-rai-reason data)]
    (. b includeRaiReason v))
   (clojure.core/when-some [v (:labels data)] (. b labels v))
   (clojure.core/when-some
    [v (:output-compression-quality data)]
    (. b outputCompressionQuality v))
   (clojure.core/when-some
    [v (:output-gcs-uri data)]
    (. b outputGcsUri v))
   (clojure.core/when-some
    [v (:output-mime-type data)]
    (. b outputMimeType v))
   (clojure.core/when-some
    [v (:person-generation data)]
    (. b personGeneration v))
   (clojure.core/when-some
    [v (:safety-filter-level data)]
    (. b safetyFilterLevel v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UpscaleImageConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :enhance-input-image
    (. x enhanceInputImage))
   (io.kosong.autovalue/optional-datafy-assoc
    :http-options
    (. x httpOptions))
   (io.kosong.autovalue/optional-datafy-assoc
    :image-preservation-factor
    (. x imagePreservationFactor))
   (io.kosong.autovalue/optional-datafy-assoc
    :include-rai-reason
    (. x includeRaiReason))
   (io.kosong.autovalue/optional-datafy-assoc :labels (. x labels))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-compression-quality
    (. x outputCompressionQuality))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-gcs-uri
    (. x outputGcsUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :output-mime-type
    (. x outputMimeType))
   (io.kosong.autovalue/optional-datafy-assoc
    :person-generation
    (. x personGeneration))
   (io.kosong.autovalue/optional-datafy-assoc
    :safety-filter-level
    (. x safetyFilterLevel))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UpscaleImageParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UpscaleImageParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UpscaleImageParameters/builder)]
   (clojure.core/when-some
    [v (:config data)]
    (.
     b
     config
     (io.kosong.java/make-object
      com.google.genai.types.UpscaleImageConfig
      v)))
   (clojure.core/when-some
    [v (:image data)]
    (.
     b
     image
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some [v (:model data)] (. b model v))
   (clojure.core/when-some
    [v (:upscale-factor data)]
    (. b upscaleFactor v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UpscaleImageParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :config (. x config))
   (io.kosong.autovalue/optional-datafy-assoc :image (. x image))
   (io.kosong.autovalue/optional-datafy-assoc :model (. x model))
   (io.kosong.autovalue/optional-datafy-assoc
    :upscale-factor
    (. x upscaleFactor))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UpscaleImageResponse
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UpscaleImageResponse
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UpscaleImageResponse/builder)]
   (clojure.core/when-some
    [v (:generated-images data)]
    (.
     b
     generatedImages
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.GeneratedImage
        x))
      v)))
   (clojure.core/when-some
    [v (:sdk-http-response data)]
    (.
     b
     sdkHttpResponse
     (io.kosong.java/make-object
      com.google.genai.types.HttpResponse
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UpscaleImageResponse
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :generated-images
    (. x generatedImages))
   (io.kosong.autovalue/optional-datafy-assoc
    :sdk-http-response
    (. x sdkHttpResponse))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UrlContext
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.UrlContext data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UrlContext/builder)]
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UrlContext
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UrlContextMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.UrlContextMetadata
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UrlContextMetadata/builder)]
   (clojure.core/when-some
    [v (:url-metadata data)]
    (.
     b
     urlMetadata
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.UrlMetadata
        x))
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UrlContextMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :url-metadata
    (. x urlMetadata))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UrlMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.UrlMetadata data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UrlMetadata/builder)]
   (clojure.core/when-some
    [v (:retrieved-url data)]
    (. b retrievedUrl v))
   (clojure.core/when-some
    [v (:url-retrieval-status data)]
    (. b urlRetrievalStatus v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UrlMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :retrieved-url
    (. x retrievedUrl))
   (io.kosong.autovalue/optional-datafy-assoc
    :url-retrieval-status
    (. x urlRetrievalStatus))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.UsageMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.UsageMetadata data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.UsageMetadata/builder)]
   (clojure.core/when-some
    [v (:cache-tokens-details data)]
    (.
     b
     cacheTokensDetails
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ModalityTokenCount
        x))
      v)))
   (clojure.core/when-some
    [v (:cached-content-token-count data)]
    (. b cachedContentTokenCount v))
   (clojure.core/when-some
    [v (:prompt-token-count data)]
    (. b promptTokenCount v))
   (clojure.core/when-some
    [v (:prompt-tokens-details data)]
    (.
     b
     promptTokensDetails
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ModalityTokenCount
        x))
      v)))
   (clojure.core/when-some
    [v (:response-token-count data)]
    (. b responseTokenCount v))
   (clojure.core/when-some
    [v (:response-tokens-details data)]
    (.
     b
     responseTokensDetails
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ModalityTokenCount
        x))
      v)))
   (clojure.core/when-some
    [v (:thoughts-token-count data)]
    (. b thoughtsTokenCount v))
   (clojure.core/when-some
    [v (:tool-use-prompt-token-count data)]
    (. b toolUsePromptTokenCount v))
   (clojure.core/when-some
    [v (:tool-use-prompt-tokens-details data)]
    (.
     b
     toolUsePromptTokensDetails
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.ModalityTokenCount
        x))
      v)))
   (clojure.core/when-some
    [v (:total-token-count data)]
    (. b totalTokenCount v))
   (clojure.core/when-some
    [v (:traffic-type data)]
    (. b trafficType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.UsageMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :cache-tokens-details
    (. x cacheTokensDetails))
   (io.kosong.autovalue/optional-datafy-assoc
    :cached-content-token-count
    (. x cachedContentTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :prompt-token-count
    (. x promptTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :prompt-tokens-details
    (. x promptTokensDetails))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-token-count
    (. x responseTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :response-tokens-details
    (. x responseTokensDetails))
   (io.kosong.autovalue/optional-datafy-assoc
    :thoughts-token-count
    (. x thoughtsTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :tool-use-prompt-token-count
    (. x toolUsePromptTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :tool-use-prompt-tokens-details
    (. x toolUsePromptTokensDetails))
   (io.kosong.autovalue/optional-datafy-assoc
    :total-token-count
    (. x totalTokenCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :traffic-type
    (. x trafficType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VeoHyperParameters
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.VeoHyperParameters
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VeoHyperParameters/builder)]
   (clojure.core/when-some [v (:epoch-count data)] (. b epochCount v))
   (clojure.core/when-some
    [v (:learning-rate-multiplier data)]
    (. b learningRateMultiplier v))
   (clojure.core/when-some [v (:tuning-task data)] (. b tuningTask v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VeoHyperParameters
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :epoch-count
    (. x epochCount))
   (io.kosong.autovalue/optional-datafy-assoc
    :learning-rate-multiplier
    (. x learningRateMultiplier))
   (io.kosong.autovalue/optional-datafy-assoc
    :tuning-task
    (. x tuningTask))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VeoTuningSpec
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.VeoTuningSpec data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VeoTuningSpec/builder)]
   (clojure.core/when-some
    [v (:hyper-parameters data)]
    (.
     b
     hyperParameters
     (io.kosong.java/make-object
      com.google.genai.types.VeoHyperParameters
      v)))
   (clojure.core/when-some
    [v (:training-dataset-uri data)]
    (. b trainingDatasetUri v))
   (clojure.core/when-some
    [v (:validation-dataset-uri data)]
    (. b validationDatasetUri v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VeoTuningSpec
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :hyper-parameters
    (. x hyperParameters))
   (io.kosong.autovalue/optional-datafy-assoc
    :training-dataset-uri
    (. x trainingDatasetUri))
   (io.kosong.autovalue/optional-datafy-assoc
    :validation-dataset-uri
    (. x validationDatasetUri))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VertexAISearch
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.VertexAISearch data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VertexAISearch/builder)]
   (clojure.core/when-some
    [v (:data-store-specs data)]
    (.
     b
     dataStoreSpecs
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.VertexAISearchDataStoreSpec
        x))
      v)))
   (clojure.core/when-some [v (:datastore data)] (. b datastore v))
   (clojure.core/when-some [v (:engine data)] (. b engine v))
   (clojure.core/when-some [v (:filter data)] (. b filter v))
   (clojure.core/when-some [v (:max-results data)] (. b maxResults v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VertexAISearch
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :data-store-specs
    (. x dataStoreSpecs))
   (io.kosong.autovalue/optional-datafy-assoc
    :datastore
    (. x datastore))
   (io.kosong.autovalue/optional-datafy-assoc :engine (. x engine))
   (io.kosong.autovalue/optional-datafy-assoc :filter (. x filter))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-results
    (. x maxResults))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VertexAISearchDataStoreSpec
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.VertexAISearchDataStoreSpec
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VertexAISearchDataStoreSpec/builder)]
   (clojure.core/when-some [v (:data-store data)] (. b dataStore v))
   (clojure.core/when-some [v (:filter data)] (. b filter v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VertexAISearchDataStoreSpec
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :data-store
    (. x dataStore))
   (io.kosong.autovalue/optional-datafy-assoc :filter (. x filter))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VertexRagStore
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.VertexRagStore data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VertexRagStore/builder)]
   (clojure.core/when-some [v (:rag-corpora data)] (. b ragCorpora v))
   (clojure.core/when-some
    [v (:rag-resources data)]
    (.
     b
     ragResources
     (clojure.core/mapv
      (clojure.core/fn
       [x]
       (io.kosong.java/make-object
        com.google.genai.types.VertexRagStoreRagResource
        x))
      v)))
   (clojure.core/when-some
    [v (:rag-retrieval-config data)]
    (.
     b
     ragRetrievalConfig
     (io.kosong.java/make-object
      com.google.genai.types.RagRetrievalConfig
      v)))
   (clojure.core/when-some
    [v (:similarity-top-k data)]
    (. b similarityTopK v))
   (clojure.core/when-some
    [v (:store-context data)]
    (. b storeContext v))
   (clojure.core/when-some
    [v (:vector-distance-threshold data)]
    (. b vectorDistanceThreshold v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VertexRagStore
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :rag-corpora
    (. x ragCorpora))
   (io.kosong.autovalue/optional-datafy-assoc
    :rag-resources
    (. x ragResources))
   (io.kosong.autovalue/optional-datafy-assoc
    :rag-retrieval-config
    (. x ragRetrievalConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :similarity-top-k
    (. x similarityTopK))
   (io.kosong.autovalue/optional-datafy-assoc
    :store-context
    (. x storeContext))
   (io.kosong.autovalue/optional-datafy-assoc
    :vector-distance-threshold
    (. x vectorDistanceThreshold))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VertexRagStoreRagResource
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.VertexRagStoreRagResource
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VertexRagStoreRagResource/builder)]
   (clojure.core/when-some [v (:rag-corpus data)] (. b ragCorpus v))
   (clojure.core/when-some [v (:rag-file-ids data)] (. b ragFileIds v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VertexRagStoreRagResource
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :rag-corpus
    (. x ragCorpus))
   (io.kosong.autovalue/optional-datafy-assoc
    :rag-file-ids
    (. x ragFileIds))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.Video
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.Video data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.Video/builder)]
   (clojure.core/when-some [v (:mime-type data)] (. b mimeType v))
   (clojure.core/when-some [v (:uri data)] (. b uri v))
   (clojure.core/when-some [v (:video-bytes data)] (. b videoBytes v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.Video
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :mime-type
    (. x mimeType))
   (io.kosong.autovalue/optional-datafy-assoc :uri (. x uri))
   (io.kosong.autovalue/optional-datafy-assoc
    :video-bytes
    (. x videoBytes))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VideoGenerationMask
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.VideoGenerationMask
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VideoGenerationMask/builder)]
   (clojure.core/when-some
    [v (:image data)]
    (.
     b
     image
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some [v (:mask-mode data)] (. b maskMode v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VideoGenerationMask
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :image (. x image))
   (io.kosong.autovalue/optional-datafy-assoc
    :mask-mode
    (. x maskMode))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VideoGenerationReferenceImage
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.VideoGenerationReferenceImage
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VideoGenerationReferenceImage/builder)]
   (clojure.core/when-some
    [v (:image data)]
    (.
     b
     image
     (io.kosong.java/make-object com.google.genai.types.Image v)))
   (clojure.core/when-some
    [v (:reference-type data)]
    (. b referenceType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VideoGenerationReferenceImage
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc :image (. x image))
   (io.kosong.autovalue/optional-datafy-assoc
    :reference-type
    (. x referenceType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VideoMetadata
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.VideoMetadata data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VideoMetadata/builder)]
   (clojure.core/when-some [v (:end-offset data)] (. b endOffset v))
   (clojure.core/when-some [v (:fps data)] (. b fps v))
   (clojure.core/when-some
    [v (:start-offset data)]
    (. b startOffset v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VideoMetadata
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :end-offset
    (. x endOffset))
   (io.kosong.autovalue/optional-datafy-assoc :fps (. x fps))
   (io.kosong.autovalue/optional-datafy-assoc
    :start-offset
    (. x startOffset))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VoiceActivityDetectionSignal
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance?
   com.google.genai.types.VoiceActivityDetectionSignal
   data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VoiceActivityDetectionSignal/builder)]
   (clojure.core/when-some
    [v (:vad-signal-type data)]
    (. b vadSignalType v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VoiceActivityDetectionSignal
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :vad-signal-type
    (. x vadSignalType))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.VoiceConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.VoiceConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.VoiceConfig/builder)]
   (clojure.core/when-some
    [v (:prebuilt-voice-config data)]
    (.
     b
     prebuiltVoiceConfig
     (io.kosong.java/make-object
      com.google.genai.types.PrebuiltVoiceConfig
      v)))
   (clojure.core/when-some
    [v (:replicated-voice-config data)]
    (.
     b
     replicatedVoiceConfig
     (io.kosong.java/make-object
      com.google.genai.types.ReplicatedVoiceConfig
      v)))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.VoiceConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :prebuilt-voice-config
    (. x prebuiltVoiceConfig))
   (io.kosong.autovalue/optional-datafy-assoc
    :replicated-voice-config
    (. x replicatedVoiceConfig))
   (clojure.core/persistent!))))

(clojure.core/defmethod
 io.kosong.java/make-object
 com.google.genai.types.WhiteSpaceConfig
 [_cls data]
 (clojure.core/cond
  (clojure.core/instance? com.google.genai.types.WhiteSpaceConfig data)
  data
  (clojure.core/instance? clojure.lang.IPersistentMap data)
  (clojure.core/let
   [b (com.google.genai.types.WhiteSpaceConfig/builder)]
   (clojure.core/when-some
    [v (:max-overlap-tokens data)]
    (. b maxOverlapTokens v))
   (clojure.core/when-some
    [v (:max-tokens-per-chunk data)]
    (. b maxTokensPerChunk v))
   (.build b))))

(clojure.core/extend-type
 com.google.genai.types.WhiteSpaceConfig
 clojure.core.protocols/Datafiable
 (clojure.datafy/datafy
  [x]
  (clojure.core/->
   (clojure.core/transient {})
   (io.kosong.autovalue/optional-datafy-assoc
    :max-overlap-tokens
    (. x maxOverlapTokens))
   (io.kosong.autovalue/optional-datafy-assoc
    :max-tokens-per-chunk
    (. x maxTokensPerChunk))
   (clojure.core/persistent!))))

