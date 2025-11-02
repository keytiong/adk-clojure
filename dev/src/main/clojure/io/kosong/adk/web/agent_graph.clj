(ns io.kosong.adk.web.agent-graph
  "Utility namespace to generate Graphviz DOT representations of Agent structures."
  (:require [clojure.tools.logging :as log])
  (:import (guru.nidi.graphviz.attribute Arrow Arrow$DirType Attributes Color Label Rank Rank$RankDir Shape Style)
           (guru.nidi.graphviz.engine Format Graphviz)
           (guru.nidi.graphviz.model Factory Link MutableGraph)
           (com.google.adk.agents BaseAgent LlmAgent)
           (com.google.adk.tools BaseTool FunctionTool AgentTool)
           (com.google.adk.tools.retrieval BaseRetrievalTool)))

;; Color definitions
(def ^:private ^Color dark-green (Color/rgb "#0F5223"))
(def ^:private ^Color light-green (Color/rgb "#69CB87"))
(def ^:private ^Color light-gray (Color/rgb "#CCCCCC"))
(def ^:private ^Color bg-color (Color/rgb "#333537"))

;; Node name extraction
(defn- get-node-name
  "Extracts the name from an agent or tool."
  [tool-or-agent]
  (cond
    (instance? BaseAgent tool-or-agent)
    (.name ^BaseAgent tool-or-agent)

    (instance? BaseTool tool-or-agent)
    (.name ^BaseTool tool-or-agent)

    :else
    (do
      (log/warn "Unsupported type for get-node-name:" (.getName (class tool-or-agent)))
      (str "unknown_" (.hashCode tool-or-agent)))))

;; Node caption with emoji
(defn- get-node-caption
  "Returns the caption for a node with appropriate emoji."
  [tool-or-agent]
  (let [name (get-node-name tool-or-agent)]
    (cond
      (instance? BaseAgent tool-or-agent)
      (str "🤖 " name)

      (instance? BaseRetrievalTool tool-or-agent)
      (str "🔎 " name)

      (instance? FunctionTool tool-or-agent)
      (str "🔧 " name)

      (instance? AgentTool tool-or-agent)
      (str "🤖 " name)

      (instance? BaseTool tool-or-agent)
      (str "🔧 " name)

      :else
      (do
        (log/warn "Unsupported type for get-node-caption:" (.getName (class tool-or-agent)))
        (str "❓ " name)))))

;; Node shape determination
(defn- get-node-shape
  "Returns the appropriate shape for a node."
  ^Shape [tool-or-agent]
  (cond
    (instance? BaseAgent tool-or-agent)
    Shape/ELLIPSE

    (instance? BaseRetrievalTool tool-or-agent)
    Shape/CYLINDER

    (instance? FunctionTool tool-or-agent)
    Shape/BOX

    (instance? BaseTool tool-or-agent)
    Shape/BOX

    :else
    (do
      (log/warn "Unsupported type for get-node-shape:" (.getName (class tool-or-agent)))
      Shape/EGG)))

;; Highlighting helpers
(defn- is-node-highlighted?
  "Checks if a node should be highlighted based on highlight pairs."
  [node-name highlight-pairs]
  (when (and highlight-pairs node-name)
    (some (fn [pair]
            (when pair
              (some #(= node-name %) pair)))
          highlight-pairs)))

(defn- edge-highlight-type
  "Checks if an edge should be highlighted.
  Returns nil if not highlighted, :forward if forward direction, :backward if backward."
  [from-name to-name highlight-pairs]
  (let [pair-set (apply hash-set highlight-pairs)]
    (cond
      (pair-set [from-name to-name])
      :forward

      (pair-set [to-name from-name])
      :backward

      :else
      :none
      )))

;; Graph drawing functions
(defn- draw-node!
  "Draws a node for an agent or tool, applying highlighting if applicable."
  [graph tool-or-agent highlight-pairs visited-nodes]
  (let [name (get-node-name tool-or-agent)]
    (when (and (seq name) (not (@visited-nodes name)))
      (let [shape        (get-node-shape tool-or-agent)
            caption      (get-node-caption tool-or-agent)
            highlighted? (is-node-highlighted? name highlight-pairs)
            node         (-> (Factory/mutNode ^String name)
                             (.add ^Label (Label/of ^String caption))
                             (.add shape)
                             (.add (.font light-gray)))]

        (if highlighted?
          (-> node
              (.add Style/FILLED)
              (.add dark-green))
          (-> node
              (.add Style/ROUNDED)
              (.add light-gray)))

        (.add graph node)
        (swap! visited-nodes conj name)

        (log/tracef "Added node: name=%s, caption=%s, shape=%s, highlighted=%s"
                    name caption shape highlighted?)))))

(defn- draw-edge!
  "Draws an edge between two nodes, applying highlighting if applicable."
  [graph from-name to-name highlight-pairs]
  (when (and (seq from-name) (seq to-name))
    (let [edge-type (edge-highlight-type from-name to-name highlight-pairs)
          to-node           (Factory/mutNode ^String to-name)
          ^Link link        (Factory/to to-node)
          ^Link styled-link  (cond
                               (= edge-type :forward)
                               (-> link
                                   (.with light-green)
                                   (.with Arrow/NORMAL))

                               (= edge-type :backward)
                               (-> link
                                   (.with light-green)
                                   (.with (.dir Arrow/NORMAL Arrow$DirType/BACK)))

                               :else
                               (-> link
                                   (.with light-gray)
                                   (.with Arrow/NONE)))]

      (.add graph (.addLink (Factory/mutNode ^String from-name) styled-link))

      (log/tracef "Added edge: from=%s, to=%s, highlighted=%s"
                  from-name to-name (some? edge-type)))))

(defn- build-graph-recursive!
  "Recursively builds the graph structure."
  [graph agent highlight-pairs visited-nodes]
  (when (and agent (not (@visited-nodes (get-node-name agent))))
    ;; Draw the agent node
    (draw-node! graph agent highlight-pairs visited-nodes)

    ;; Process sub-agents
    (when-let [sub-agents (.subAgents ^BaseAgent agent)]
      (doseq [sub-agent sub-agents]
        (when sub-agent
          (draw-edge! graph (get-node-name agent) (get-node-name sub-agent) highlight-pairs)
          (build-graph-recursive! graph sub-agent highlight-pairs visited-nodes))))

    ;; Process tools (if this is an LlmAgent)
    (when (instance? LlmAgent agent)
      (when-let [tools (.tools ^LlmAgent agent)]
        (doseq [tool tools]
          (when tool
            (draw-node! graph tool highlight-pairs visited-nodes)
            (draw-edge! graph (get-node-name agent) (get-node-name tool) highlight-pairs)))))))

(defn get-agent-graph-dot-source
  "Generates the DOT source string for the agent graph.

  Args:
    root-agent: The root agent of the structure
    highlight-pairs: A sequence of 2-element vectors [from-node to-node]
                     representing edges to highlight. Order matters for direction.

  Returns:
    The DOT source string, or nil if graph generation fails."
  [root-agent highlight-pairs]
  (log/debugf "Building agent graph with root: %s, highlights: %s"
              (.name ^BaseAgent root-agent) highlight-pairs)
  (try
    (let [graph         (-> (Factory/mutGraph "AgentGraph")
                            (.setDirected true)
                            (.graphAttrs)
                            (.add (Rank/dir Rank$RankDir/LEFT_TO_RIGHT) (.background bg-color)))
          visited-nodes (atom #{})]

      (build-graph-recursive! graph root-agent highlight-pairs visited-nodes)

      (let [dot-source (-> (Graphviz/fromGraph ^MutableGraph graph)
                           (.render Format/DOT)
                           (.toString))]
        (log/debug "Generated DOT source successfully.")
        dot-source))
    (catch Exception e
      (log/error e "Error generating agent graph DOT source")
      nil)))