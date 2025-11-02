(ns io.kosong.adk.tools
  (:require [io.kosong.adk.protocols :refer [IntoTool into-tool]]
            [io.kosong.adk.utils :refer [optional-datafy-assoc]]
            [clojure.core.protocols :refer [Datafiable]])
  (:import (com.google.adk.tools BaseTool AgentTool ToolContext)
           (com.google.adk.agents BaseAgent)
           (clojure.lang Var Symbol)
           (io.kosong.adk.tools ClojureFunctionTool)))

(extend-protocol Datafiable
  ToolContext
  (datafy [^ToolContext x]
    (-> {}
        (optional-datafy-assoc :invocation-id (.invocationId x))
        (optional-datafy-assoc :branch (.branch x))
        (optional-datafy-assoc :agent-name (.agentName x))
        (optional-datafy-assoc :state (.state x))
        (optional-datafy-assoc :user-content (.userContent x))
        (optional-datafy-assoc :event-actions (.eventActions x))
        (optional-datafy-assoc :actions (.eventActions x))
        (optional-datafy-assoc :function-call-id (.functionCallId x)))))

(extend-protocol IntoTool
  Var
  (into-tool [x]
    (ClojureFunctionTool/create x)))

(extend-protocol IntoTool
  Symbol
  (into-tool [x]
    (into-tool (resolve x))))

(extend-protocol IntoTool
  BaseTool
  (into-tool [x]
    x))

(extend-protocol IntoTool
  BaseAgent
  (into-tool [x]
    (AgentTool/create x)))