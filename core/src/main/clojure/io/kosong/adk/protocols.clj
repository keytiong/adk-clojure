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

;;
;; com.google.adk.events
;;
(defprotocol IntoEvent
  (into-event [x]))

(defprotocol IntoEventActions
  (into-event-actions [x]))

