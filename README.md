# Clojure Agent Development Kit (ADK)

[![Clojars Project](https://img.shields.io/clojars/v/io.kosong.adk/adk-clojure.svg?include_prereleases)](https://clojars.org/io.kosong.adk/adk-clojure)

A Clojure wrapper for Google's Java [Agent Development Kit (ADK)](https://github.com/google/adk-java), providing idiomatic Clojure interfaces for building AI agents.
## Overview

ADK Clojure provides idiomatic Clojure APIs for creating AI agents with ADK:

- **Domain Object Construction**: Construct ADK domain objects with maps instead of Java builders.
- **Function Tool**: Converts Clojure functions into tools for agents.
- **Datafied Domain Objects**: ADK domain objects are `Datafiable` as maps.
- **ADK Web for Clojure Agents**: ADK web implemented in Clojure for Clojure based agents.
- **Async Event Support**: Converts ADK `RxJava` observables into `core.async` channels.

## Prerequisites

Before using ADK Clojure, ensure you have:

- **Java 17 or higher**: Required for running the Google ADK Java library
- **Clojure CLI tools**: Install from [clojure.org](https://clojure.org/guides/install_clojure)
- **Google API Credentials**: Follow these [instructions](https://google.github.io/adk-docs/agents/models/#using-google-gemini-models) to setup credentials to use Google Gemini models and Vertex AI.

## Installation

### As a Library Dependency

Add to your `deps.edn`:

```clojure
{:deps {io.kosong.adk/adk-clojure {:local/root "path/to/adk-clojure/core"}}}
```

### Run ADK Web

If you need the ADK Web for development:

```clojure
{:deps {io.kosong.adk/adk-clojure-dev {:local/root "path/to/adk-clojure/dev"}}}
```

## Build

```bash
# Build adk-clojure library
cd core
clojure -T:build jar

# Build adk-clojure-dev library
cd dev
clojure -T:build jar
```

## Quick Start

### Simple Agent

```clojure
(require '[io.kosong.adk.core :as adk])

;; Define a simple chatbot agent
(def chatbot
  (adk/llm-agent
    :name "my-chatbot"
    :model "gemini-2.5-flash"
    :description "A helpful AI assistant"
    :instruction "You are a helpful assistant who can answer user questions"))

;; Create a context and run the agent
(adk/run (adk/agent-context) chatbot "What is the capital of France?")
```
Result: A sequence of map representation of `com.google.adk.events.Event`

```clojure
({:id "2ee78340-d5fb-4cc5-b24c-83f8deb89e4e",
  :invocation-id "e-d23ddd9b-d225-40fe-903d-485f09f74013",
  :author "my-chatbot",
  :content {:parts [{:text "The capital of France is **Paris**."}], :role "model"},
  :actions {:state-delta {}, :artifact-delta {}, :request-auth-configs {}},
  :timestamp 1761483753983})
```

### Agent with Tools

```clojure
(require '[io.kosong.adk.core :as adk])

; Define a function with hard coded result for demo
(defn get-weather
  "Gets the weather for a given location"
  [^{:schema {:type "STRING"}} location]
  {:temperature 72
   :condition "Sunny"
   :location location})

; Define a weather agent that uses the function as a tool
(def weather-agent
  (adk/llm-agent
    :name "weather-assistant"
    :model "gemini-2.5-flash"
    :description "Provides weather information"
    :instruction "You are a weather assistant. Use the get-weather tool to provide accurate weather information."
    :tools [#'get-weather]))

; Run the agent
(adk/run (adk/agent-context) weather-agent "What is the weather in New York?")
```
Result: A sequence of events showing function call, function response, and LLM response
```clojure
({:id "784b9267-6017-40c4-a3bc-bc82c14ff910",
  :invocation-id "e-62a5ccaf-2e5e-480d-bcd6-6a09f3a1461d",
  :author "weather-assistant",
  :content {:parts [{:function-call {:id "adk-1d244f27-2fb7-407c-8263-cd8483d163b9",
                                     :args {"location" "new york"},
                                     :name "get-weather"}}],
            :role "model"},
  :actions {:state-delta {}, :artifact-delta {}, :request-auth-configs {}},
  :timestamp 1761484201785}
 {:id "8bd76536-23a5-45f5-8e34-9b896082f517",
  :invocation-id "e-62a5ccaf-2e5e-480d-bcd6-6a09f3a1461d",
  :author "weather-assistant",
  :content {:parts [{:function-response {:id "adk-1d244f27-2fb7-407c-8263-cd8483d163b9",
                                         :name "get-weather",
                                         :response {"temperature" 72, "condition" "Sunny", "location" "new york"}}}],
            :role "user"},
  :actions {:state-delta {}, :artifact-delta {}, :request-auth-configs {}},
  :timestamp 1761484202677}
 {:id "b9103ea8-ddc0-4d79-b39b-10473e7c4c7e",
  :invocation-id "e-62a5ccaf-2e5e-480d-bcd6-6a09f3a1461d",
  :author "weather-assistant",
  :content {:parts [{:text "The weather in New York is Sunny with a temperature of 72 degrees."}], :role "model"},
  :actions {:state-delta {}, :artifact-delta {}, :request-auth-configs {}},
  :timestamp 1761484202682})
```

### Run ADK Web

Start the server:
```clojure
(require '[io.kosong.adk.web :as adk-web])

;; Starts adk web on port 8080 with the agents defined in namespaces
(adk-web/run {:port 8080
              :agent-namespaces ['my-namespace.my-agent]})

;; Stop adk web
(adk-web/stop!)
```
## License

This project is licensed under the MIT License - see the LICENSE file for details.
