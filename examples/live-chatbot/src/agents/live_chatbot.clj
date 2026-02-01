(ns agents.live-chatbot
  (:require [io.kosong.adk.core :as adk]))

(def live-chatbot
  (adk/llm-agent
    :name "live-chatbot"
    :description "A conversational chatbot with live streaming support for text and audio"
    :model "gemini-live-2.5-flash-native-audio"
    :instruction "You are a helpful assistant that can engage in real-time conversations.
You can handle both text messages and audio input.
Be concise and conversational in your responses."
    :generate-content-config {:temperature 0.7
                              :top-p       0.95}))
