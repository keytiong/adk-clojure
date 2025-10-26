(ns agents.academic-research
  (:require [io.kosong.adk.core :as adk]
            [clojure.java.io :as io])
  (:import (com.google.adk.tools GoogleSearchTool)))

(defn template
  [name]
  (slurp (io/resource name)))


(def academic-websearch-agent
  (adk/llm-agent
    :name "academic_websearch_agent"
    :model "gemini-2.5-pro"
    :description "AI assistant specialized in factual retrieval to identify and list academic papers that cite the seminal paper"
    :instruction (template "templates/academic_websearch.md")
    :output-key "recent_citing_papers"
    :tools [(GoogleSearchTool.)]))

(def academic-newsearch-agent
  (adk/llm-agent
    :name "academic_newsearch_agent"
    :model "gemini-2.5-pro"
    :description "AI Research Foresight Agent"
    :instruction (template "templates/academic_newsearch.md")))


(def academic-coordinator
  (adk/llm-agent
    :name "academic_coordinator"
    :model "gemini-2.5-pro"
    :description "
    analyzing seminal papers provided by the users,
    providing research advice, locating current papers
    relevant to the seminal paper, generating suggestions
    for new research directions, and accessing web resources
    to acquire knowledge
    "
    :instruction (template "templates/academic_coordinator.md")
    :output-key "seminal_paper"
    :tools [academic-websearch-agent academic-newsearch-agent]))

