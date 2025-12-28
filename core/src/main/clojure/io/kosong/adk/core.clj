(ns io.kosong.adk.core
  (:require [clojure.core.async :as async]
            [clojure.datafy :as d :refer [datafy]]
            [io.kosong.adk.protocols :as p]
            [clojure.tools.logging :as log])
  (:import (com.google.adk.agents Instruction LlmAgent LoopAgent RunConfig RunConfig$StreamingMode SequentialAgent)
           (com.google.adk.runner Runner)
           (com.google.adk.sessions Session)
           (com.google.genai.types Content)
           (io.kosong.adk.agents ClojureAgent)
           (java.util List Map Optional)))

(require '[io.kosong.adk.agents])
(require '[io.kosong.adk.artifacts])
(require '[io.kosong.adk.events])
(require '[io.kosong.adk.models])
(require '[io.kosong.adk.runner])
(require '[io.kosong.adk.sessions])
(require '[io.kosong.adk.tools])
(require '[io.kosong.adk.utils])
(require '[io.kosong.adk.types])

(defn- resolve-app-name
  [context]
  (or
    (:app-name context)

    (when-let [a (:agent context)]
      (.name a))

    "default"))

(defn- resolve-user-id
  [context]
  (or (:user-id context) "tmp-user"))

(defn- get-or-create-session
  ([context]
   (get-or-create-session context {}))
  ([context state]
   (if-let [session (:session context)]
     session
     (let [session-service (:session-service context)
           app-name        (resolve-app-name context)
           user-id         (resolve-user-id context)]
       (io.kosong.adk.sessions/create-session session-service app-name user-id state)))))

(defn agent-context
  [& {:keys [app-name user-id agent session-service artifact-service memory-service session
             plugins]}]
  (let [session-service  (or session-service (io.kosong.adk.sessions/in-memory-session-service))
        artifact-service (or artifact-service (io.kosong.adk.artifacts/in-memory-artifact-service))]
    (cond-> {}
            (some? session-service) (assoc :session-service session-service)
            (some? artifact-service) (assoc :artifact-service artifact-service)
            (some? memory-service) (assoc :memory-service memory-service)
            (some? plugins) (assoc :plugins plugins)
            (some? session) (assoc :session session)
            (some? app-name) (assoc :app-name app-name)
            (some? user-id) (assoc :user-id user-id)
            (some? agent) (assoc :agent agent))))

(defn with-new-session
  ([context]
   (with-new-session context nil nil))
  ([context state]
   (with-new-session context state nil))
  ([context state session-id]
   (let [session-service (:session-service context)
         session-id      (or session-id (str (random-uuid)))
         state           (or state {})
         app-name        (resolve-app-name context)
         user-id         (resolve-user-id context)
         session         (io.kosong.adk.sessions/create-session session-service app-name user-id state session-id)]
     (assoc context :session session))))

(defn with-session
  ([context session-id]
   (let [session-service (:session-service context)
         app-name        (resolve-app-name context)
         user-id         (resolve-user-id context)
         session         (io.kosong.adk.sessions/get-session session-service app-name user-id session-id)]
     (if (some? session)
       (assoc context :session session)
       (throw (ex-info "Session not found", {:session-id session-id
                                             :app-name   app-name
                                             :user-id    user-id}))))))

(defn base-agent
  [& {:keys [name description sub-agents
             before-agent-callback after-agent-callback
             run-async-fn run-live-fn]}]
  (let [b (ClojureAgent/builder)]
    (when (some? name)
      (.name b name))
    (when (some? description)
      (.description b description))
    (when (some? sub-agents)
      (.subAgents b sub-agents))
    (when (some? before-agent-callback)
      (let [before-agent-callback (if (coll? before-agent-callback) before-agent-callback [before-agent-callback])]
        (.beforeAgentCallback b (mapv p/into-before-model-callback before-agent-callback))))
    (when (some? after-agent-callback)
      (let [after-agent-callback (if (coll? after-agent-callback) after-agent-callback [after-agent-callback])]
        (.afterAgentCallback b (mapv p/into-after-tool-callback after-agent-callback))))
    (when (some? run-async-fn)
      (.runAsyncFn b run-async-fn))
    (when (some? run-live-fn)
      (.runLiveFn b run-live-fn))
    (.build b)))

(defn llm-agent
  [& {:keys [name description model
             instruction global-instruction
             sub-agents tools generate-content-config
             example-provider include-contents
             planning disallow-transfer-to-parent disallow-transfer-to-peers
             before-agent-callback after-agent-callback
             before-model-callback after-model-callback
             before-tool-callback after-tool-callback
             input-schema output-schema
             executor output-key]}]
  (let [b (-> (LlmAgent/builder)
              (.name name)
              (.description description))]
    (when (some? model)
      (.model b model))
    (when (some? instruction)
      (.instruction b ^Instruction (p/into-instruction instruction)))
    (when (some? global-instruction)
      (.globalInstruction b ^Instruction (p/into-instruction global-instruction)))
    (when (some? sub-agents)
      (let [sub-agents (if (coll? sub-agents) sub-agents [sub-agents])]
        (.subAgents b ^List (mapv p/into-agent sub-agents))))
    (when (some? tools)
      (.tools b ^List (mapv p/into-tool tools)))
    (when (some? generate-content-config)
      (.generateContentConfig b (p/into-generate-content-config generate-content-config)))
    (when (some? example-provider)
      (.exampleProvider b example-provider))
    (when (some? include-contents)
      (.includeContents b include-contents))
    (when (some? planning)
      (.planning b planning))
    (when (some? disallow-transfer-to-parent)
      (.disallowTransferToParent b disallow-transfer-to-parent))
    (when (some? disallow-transfer-to-peers)
      (.disallowTransferToPeers b disallow-transfer-to-peers))
    (when (some? before-agent-callback)
      (let [before-agent-callback (if (coll? before-agent-callback) before-agent-callback [before-agent-callback])]
        (.beforeAgentCallback b ^List (mapv p/into-before-agent-callback before-agent-callback))))
    (when (some? after-agent-callback)
      (let [after-agent-callback (if (coll? after-agent-callback) after-agent-callback [after-agent-callback])]
        (.afterAgentCallback b ^List (mapv p/into-after-agent-callback after-agent-callback))))
    (when (some? before-model-callback)
      (let [before-model-callback (if (coll? before-agent-callback) before-model-callback [before-model-callback])]
        (.beforeModelCallback b ^List (mapv p/into-before-model-callback before-model-callback))))
    (when (some? after-model-callback)
      (let [after-model-callback (if (coll? after-model-callback) after-model-callback [after-model-callback])]
        (.afterModelCallback b ^List (mapv p/into-after-model-callback after-model-callback))))
    (when (some? before-tool-callback)
      (let [before-tool-callback (if (coll? before-tool-callback) before-tool-callback [before-tool-callback])]
        (.beforeToolCallback b ^List (mapv p/into-before-tool-callback before-tool-callback))))
    (when (some? after-tool-callback)
      (let [after-tool-callback (if (coll? after-tool-callback) after-tool-callback [after-tool-callback])]
        (.afterToolCallback b ^List (mapv p/into-after-tool-callback after-tool-callback))))
    (when (some? input-schema)
      (.inputSchema b (p/into-schema input-schema)))
    (when (some? output-schema)
      (.outputSchema b (p/into-schema output-schema)))
    (when (some? executor)
      (.executor b executor))
    (when (some? output-key)
      (.outputKey b output-key))
    (.build b)))

(defn loop-agent
  [& {:keys [name description sub-agents max-iterations
             before-agent-callback after-agent-callback]}]
  (let [b (LoopAgent/builder)]
    (when (some? name)
      (.name b name))
    (when (some? description)
      (.description b description))
    (when (some? sub-agents)
      (let [sub-agents (if (coll? sub-agents) sub-agents [sub-agents])]
        (.subAgents b ^List (mapv p/into-agent sub-agents))))
    (when (some? max-iterations)
      (.maxIterations b ^Integer max-iterations))
    (when (some? before-agent-callback)
      (let [before-agent-callback (if (coll? before-agent-callback) before-agent-callback [before-agent-callback])]
        (.beforeAgentCallback b ^List (mapv p/into-before-agent-callback before-agent-callback))))
    (when (some? after-agent-callback)
      (let [after-agent-callback (if (coll? after-agent-callback) after-agent-callback [after-agent-callback])]
        (.afterAgentCallback b ^List (mapv p/into-after-agent-callback after-agent-callback))))
    (.build b)))

(defn sequential-agent
  [& {:keys [name description sub-agents
             before-agent-callback after-agent-callback]}]
  (let [b (SequentialAgent/builder)]
    (when (some? name)
      (.name b name))
    (when (some? description)
      (.description b description))
    (when (some? sub-agents)
      (let [sub-agents (if (coll? sub-agents) sub-agents [sub-agents])]
        (.subAgents b ^List (mapv p/into-agent sub-agents))))
    (when (some? before-agent-callback)
      (let [before-agent-callback (if (coll? before-agent-callback) before-agent-callback [before-agent-callback])]
        (.beforeAgentCallback b ^List (mapv p/into-before-agent-callback before-agent-callback))))
    (when (some? after-agent-callback)
      (let [after-agent-callback (if (coll? after-agent-callback) after-agent-callback [after-agent-callback])]
        (.afterAgentCallback b ^List (mapv p/into-after-agent-callback after-agent-callback))))
    (.build b)))

(defn create-session
  [context & {:keys [^Map state session-id app-name user-id]}]
  (let [app-name        (or app-name (resolve-app-name context))
        user-id         (or user-id (resolve-user-id context))
        session-id      (or session-id (str (random-uuid)))
        session-service (:session-service context)
        state           (or state {})]
    (-> (io.kosong.adk.sessions/create-session session-service app-name user-id state session-id)
        (datafy))))

(defn ->run-config
  []
  (let [b (RunConfig/builder)]
    (.build b)))

(defn chan->seq [ch]
  (if-let [v (async/<!! ch)]
    (lazy-seq (cons v (chan->seq ch)))
    nil))

(defn list-sessions
  [context & {:keys [app-name user-id]}]
  (let [app-name        (or app-name (resolve-app-name context))
        user-id         (or user-id (resolve-user-id context))
        session-service (:session-service context)
        sessions        (io.kosong.adk.sessions/list-sessions session-service
                                                              app-name
                                                              user-id)]
    (mapv datafy sessions)))

(defn get-session
  [context app-name user-id session-id]
  (let [session-service    (:session-service context)
        app-name           (or app-name (resolve-app-name context))
        user-id            (or user-id (resolve-user-id context))
        get-session-config (Optional/empty)]
    (-> (io.kosong.adk.sessions/get-session session-service app-name user-id session-id get-session-config)
        (datafy))))

(defn delete-session
  [context app-name user-id session-id]
  (let [app-name        (or app-name (resolve-app-name context))
        user-id         (or user-id (resolve-user-id context))
        session-service (:session-service context)]
    (io.kosong.adk.sessions/delete-session session-service app-name user-id session-id)))

(defn run-async
  ([context agent user-content]
   (run-async context agent user-content nil))
  ([context agent user-content run-config]
   (let [session-service       (:session-service context)
         artifact-service      (:artifact-service context)
         memory-service        (:memory-service context)
         plugins               (:plugins context)
         app-name              (resolve-app-name context)
         ^Session session      (get-or-create-session context)
         runner                (-> (Runner/builder)
                                   (.agent agent)
                                   (.appName app-name)
                                   (.artifactService artifact-service)
                                   (.sessionService session-service)
                                   (.memoryService memory-service)
                                   (.plugins plugins)
                                   (.build))
         ^Content content      (p/into-content user-content)
         ^RunConfig run-config (if (some? run-config)
                                 (p/into-run-config run-config)
                                 (p/into-run-config {}))
         event-ch              (async/chan 16)
         on-next               (reify io.reactivex.rxjava3.functions.Consumer
                                 (accept [_ event]
                                   (async/>!! event-ch (d/datafy event))))
         on-error              (reify io.reactivex.rxjava3.functions.Consumer
                                 (accept [_ error]
                                   (log/error error "Error in run-async")
                                   (async/>!! event-ch error)
                                   (async/close! event-ch)))
         on-complete           (reify io.reactivex.rxjava3.functions.Action
                                 (run [_]
                                   (async/close! event-ch)))
         event-flow            (.runAsync runner session content run-config)
         _disposable           (.subscribe event-flow on-next on-error on-complete)]
     event-ch)))

(defn run
  ([context agent new-message]
   (run context agent new-message nil))
  ([context agent new-message run-config]
   (let [event-ch (run-async context agent new-message run-config)]
     (chan->seq event-ch))))

(defn run-live
  "Executes an agent with bidirectional live streaming support.

   Returns a map with two channels:
   - :event-ch - core.async channel for Events from agent (sliding buffer 16)
   - :request-ch - core.async channel for LiveRequests to agent (blocking buffer 10)

   LiveRequest format:
   - {:content ...} - send turn-by-turn content to model
   - {:blob ...} - send realtime audio/video blob to model
   - {:close true} - close the live connection

   Parameters:
   - context: agent context (app-name, user-id, session-service, etc.)
   - agent: the agent to execute
   - initial-content: optional initial content to start the conversation (can be nil)
   - run-config: optional RunConfig map (should include :streaming-mode BIDI for live mode)

   Example:
   (let [{:keys [event-ch request-ch]} (run-live context agent nil {:streaming-mode BIDI})]
     ;; Send requests
     (async/>!! request-ch {:content \"Hello\"})
     ;; Read events
     (async/<!! event-ch)
     ;; Close connection
     (async/>!! request-ch {:close true}))"
  ([context agent]
   (run-live context agent nil))
  ([context agent run-config]
   (let [session-service       (:session-service context)
         artifact-service      (:artifact-service context)
         memory-service        (:memory-service context)
         plugins               (:plugins context)
         app-name              (resolve-app-name context)
         ^Session session      (get-or-create-session context)
         runner                (-> (Runner/builder)
                                   (.agent agent)
                                   (.appName app-name)
                                   (.artifactService artifact-service)
                                   (.sessionService session-service)
                                   (.memoryService memory-service)
                                   (.plugins plugins)
                                   (.build))
         ;; Create LiveRequestQueue with channel bridge
         {:keys [queue channel]} (io.kosong.adk.runner/live-request-queue)

         ;; Build RunConfig with BIDI streaming mode
         ^RunConfig run-config (if (some? run-config)
                                 (p/into-run-config run-config)
                                 (p/into-run-config {:streaming-mode RunConfig$StreamingMode/BIDI}))

         ;; Create event channel with sliding buffer
         event-ch              (async/chan (async/sliding-buffer 16))
         on-next               (reify io.reactivex.rxjava3.functions.Consumer
                                 (accept [_ event]
                                   (log/info event)
                                   (async/>!! event-ch (d/datafy event))))
         on-error              (reify io.reactivex.rxjava3.functions.Consumer
                                 (accept [_ error]
                                   (log/error error "Error in run-live")
                                   (async/>!! event-ch error)
                                   (async/close! event-ch)
                                   (async/close! channel)))
         on-complete           (reify io.reactivex.rxjava3.functions.Action
                                 (run [_]
                                   (async/close! event-ch)
                                   (async/close! channel)))

         ;; Call runLive with LiveRequestQueue
         event-flow            (.runLive runner session queue run-config)
         _disposable           (.subscribe event-flow on-next on-error on-complete)]

     {:event-ch           event-ch
      :request-ch         channel
      :live-request-queue queue})))
