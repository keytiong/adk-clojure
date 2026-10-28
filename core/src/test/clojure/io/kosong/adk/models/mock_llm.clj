(ns io.kosong.adk.models.mock-llm
  "Mock LLM implementation for testing callbacks and agent behavior without a real LLM.

  Provides a BaseLlm proxy subclass that returns configurable LlmResponse objects.
  Register with `register-mock-llm!` so the ADK Runner can resolve it by model name.

  ## Usage

  ```clojure
  ;; Simple: register a mock that returns a fixed string
  (require '[io.kosong.adk.models.mock-llm :as mock-llm])
  (mock-llm/register-mock-llm! \"mock/.*\")

  ;; Custom response function (receives no args, returns string or map)
  (mock-llm/register-mock-llm! \"mock/.*\" (fn [] \"Hello from mock\"))

  ;; Return a full LlmResponse map for fine-grained control
  (mock-llm/register-mock-llm! \"mock/.*\"
    (fn []
      {:content {:role \"model\" :parts [{:text \"Custom response\"}]}
       :turn-complete true}))

  ;; Inspect requests sent to the mock
  (mock-llm/clear-llm-requests!)
  ;; ... run agent ...
  (mapv :contents @mock-llm/*llm-requests*)
  ```"
  (:require [clojure.datafy :refer [datafy]]
            [io.kosong.adk.models :as models]
            [io.kosong.java])
  (:import [com.google.adk.models BaseLlm LlmRequest LlmResponse]
           [com.google.genai.types Content Part FunctionCall]
           [io.reactivex.rxjava3.core Flowable]))

;; =============================================================================
;; Response construction
;; =============================================================================

(defn- ->Part [p]
  "Convert a Clojure map or existing Part into a Part object."
  (cond
    (instance? Part p)
    p

    (map? p)
    (cond
      (:function-call p)
      (let [fc       (:function-call p)
            name     (if (instance? FunctionCall fc)
                       (.getName ^FunctionCall fc)
                       (name fc))
            args-map (or (:args p) {})
            args-j   (reduce (fn [m [k v]] (.put m (name k) (str v)) m)
                             (java.util.HashMap.) args-map)]
        (Part/fromFunctionCall name args-j))

      (:text p)
      (Part/fromText (:text p))

      :else
      (Part/fromText ""))

    :else
    (Part/fromText (str p))))

(defn- ->Content [data]
  "Convert a Clojure map or string into a Content object."
  (cond
    (string? data)
    (-> (Content/builder)
        (.role "model")
        (.parts (into-array Part [(Part/fromText data)]))
        (.build))

    (map? data)
    (let [parts (->> (or (:parts data) [])
                     (mapv ->Part))]
      (-> (Content/builder)
          (.role (or (:role data) "model"))
          (.parts (into-array Part parts))
          (.build)))

    (instance? Content data)
    data

    :else
    (-> (Content/builder)
        (.role "model")
        (.parts (into-array Part [(Part/fromText (str data))]))
        (.build))))

(defn- ->LlmResponse [data]
  "Convert a Clojure map or string into an LlmResponse object."
  (cond
    (string? data)
    (-> (LlmResponse/builder)
        (.content (->Content data))
        (.turnComplete true)
        (.build))

    (map? data)
    (if (:content data)
      (io.kosong.java/make-object LlmResponse
                                  (cond-> data
                                          (not (:turn-complete data))
                                          (assoc :turn-complete true)))
      (-> (LlmResponse/builder)
          (.content (->Content data))
          (.turnComplete true)
          (.build)))

    (instance? LlmResponse data)
    data

    :else
    (-> (LlmResponse/builder)
        (.content (->Content data))
        (.turnComplete true)
        (.build))))

;; =============================================================================
;; Mock LLM factory
;; =============================================================================

(def ^:dynamic *llm-requests*
  "Thread-local atom that captures every LlmRequest sent to the mock LLM."
  (atom []))

(defn mock-llm-response-fn [] "Mock response")

(defn mock-llm
  "Create a BaseLlm proxy that returns responses from `response-fn`."
  ([response-fn] (mock-llm response-fn "mock-model"))
  ([response-fn model-name]
   (let [response-fn (if (fn? response-fn) response-fn (fn [] response-fn))]
     (proxy [BaseLlm] [model-name]
       (generateContent [^LlmRequest request ^Boolean _streaming]
         (swap! *llm-requests* conj (datafy request))
         (let [raw-response (response-fn)
               response     (->LlmResponse raw-response)]
           (Flowable/just response)))
       (connect [_request]
         (throw (java.lang.UnsupportedOperationException. "Live connections not supported by mock LLM")))))))

(defn make-mock-llm
  "Create a BaseLlm proxy where generate-content-fn receives the datafied LlmRequest map.\n\n  Use this when you need to inspect the request before responding (e.g. echo tests).\n  For simple fixed responses, use `mock-llm` instead."
  ([generate-content-fn] (make-mock-llm generate-content-fn "mock-model"))
  ([generate-content-fn model-name]
   (proxy [BaseLlm] [model-name]
     (generateContent [^LlmRequest request ^Boolean _streaming]
       (swap! *llm-requests* conj (datafy request))
       (let [request-data  (datafy request)
             response-data (generate-content-fn request-data)
             response      (if response-data
                             (Flowable/just (io.kosong.java/make-object LlmResponse response-data))
                             (Flowable/empty))]
         response))
     (connect [_request]
       (throw (java.lang.UnsupportedOperationException. "Live connections not supported by mock LLM"))))))


(defn echo-message [llm-request]
  "Echo back the text from the request as an LlmResponse map."
  (let [content          (last (get-in llm-request [:contents]))
        parts            (get-in content [:parts])
        request-message  (clojure.string/join " " (map :text parts))
        response-message (str "Echo: " request-message)]
    {:content       {:role "model" :parts [{:text response-message}]}
     :turn-complete true}))

;; =============================================================================
;; Registration helpers
;; =============================================================================

(defn register-mock-llm!
  "Register a mock LLM factory with the ADK LlmRegistry."
  ([pattern] (register-mock-llm! pattern mock-llm-response-fn))
  ([pattern response-fn]
   (models/register-llm-factory!
     pattern
     (fn [model-name]
       (mock-llm response-fn model-name)))))

(defn- function-call-response
  "Build an LlmResponse with a single function call."
  ([tool-name] (function-call-response tool-name {}))
  ([tool-name args]
   (let [args-j  (reduce (fn [m [k v]] (.put m (name k) (str v)) m)
                         (java.util.HashMap.) args)
         part    (Part/fromFunctionCall tool-name args-j)
         content (-> (Content/builder)
                     (.role "model")
                     (.parts (into-array Part [part]))
                     (.build))]
     (-> (LlmResponse/builder)
         (.content content)
         (.turnComplete false)
         (.build)))))

(defn- text-response
  "Build an LlmResponse with a single text part."
  [text]
  (-> (LlmResponse/builder)
      (.content (-> (Content/builder)
                    (.role "model")
                    (.parts (into-array Part [(Part/fromText text)]))
                    (.build)))
      (.turnComplete true)
      (.build)))

(defn register-mock-llm-with-call!
  "Register a mock LLM factory that returns a function-call response on the
  first call, then a plain-text response on all subsequent calls.  Triggers
  tool execution so that before-tool / after-tool callbacks actually fire,
  without creating an infinite loop.

  pattern:     model name pattern string (e.g. \"mock-bt/.*\")
  tool-name:   the Clojure tool name to invoke (e.g. \"echo-tool\")
  args:        map of args to pass to the tool (default empty)

  NOTE: Because the LlmRegistry cannot be unregistered, each test must use
  a unique model-name pattern."
  ([pattern tool-name] (register-mock-llm-with-call! pattern tool-name {}))
  ([pattern tool-name args]
   (models/register-llm-factory!
     pattern
     (let [^java.util.concurrent.atomic.AtomicBoolean called?
           (java.util.concurrent.atomic.AtomicBoolean. false)]
       (fn [model-name]
         (proxy [BaseLlm] [model-name]
           (generateContent [^LlmRequest request ^Boolean _streaming]
             (swap! *llm-requests* conj (datafy request))
             (if (.getAndSet called? true)
               (Flowable/just (text-response "tool-ok"))
               (Flowable/just (function-call-response tool-name args))))
           (connect [_request]
             (throw (java.lang.UnsupportedOperationException.
                      "Live connections not supported")))))))))

;; =============================================================================
;; Inspection helpers
;; =============================================================================

(defn clear-llm-requests!
  "Reset the `*llm-requests*` atom to an empty vector."
  []
  (reset! *llm-requests* []))

(defn last-request
  "Return the most recent datafied LlmRequest, or nil if none recorded."
  []
  (last @*llm-requests*))

(defn request-count
  "Return the number of requests captured in `*llm-requests*`."
  []
  (count @*llm-requests*))



