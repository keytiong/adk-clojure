# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is adk-clojure, a Clojure wrapper for Google's Agent Development Kit (ADK) Java library. It provides idiomatic Clojure abstractions for building AI agents with Google's Generative AI models. The project consists of two main libraries (core and dev) plus several example agents.

**Requirements:** Java 17+, Clojure 1.12.3

## Build Commands

### Building Libraries

```bash
# Build core library (includes Java compilation)
cd lib/core
clojure -T:build clean
clojure -T:build javac
clojure -T:build jar

# Build dev library (no Java compilation needed)
cd lib/dev
clojure -T:build clean
clojure -T:build jar
```

The core library includes custom Java classes (`ClojureFunctionTool.java` and `ClojureAgent.java`) that must be compiled before building the JAR. The dev library has no Java sources.

### Running Examples

Each example has its own deps.edn and can be run independently:

```bash
cd examples/chatbot
clojure -M -m agents.chatbot

cd examples/blog_writer
clojure -M -m agents.blogger
```

### REPL Development

The recommended workflow is REPL-driven from the project root:

```bash
clojure -M:dev
```

Then in the REPL:

```clojure
(require '[io.kosong.adk.web :as adk-web])
(adk-web/main ["config.edn"])

;; Register agents dynamically
(require 'agents.chatbot)
(adk-web/load-agent-registry! 'agents.chatbot)
```

## Architecture

### Core Abstraction Pattern: Protocol-Based Java Interop

The architecture uses Clojure protocols extensively to provide seamless bidirectional conversion between Clojure data structures and Google ADK Java objects. This pattern is central to the entire codebase:

**Protocols** (`lib/core/src/main/clojure/io/kosong/adk/protocols.clj`):
- Define ~30 conversion protocols organized by domain
- Agent-related: `IntoAgent`, `IntoTool`, `IntoInstruction`, `IntoRunConfig`, callback protocols
- Type-related: `IntoPart`, `IntoContent`, `IntoBlob`, `IntoSchema`, `IntoFunctionCall`, etc.
- All conversions flow through these protocols for consistency

**Type Implementations** (`lib/core/src/main/clojure/io/kosong/adk/types.clj`):
- Extend `Datafiable` protocol for Java→Clojure (using `datafy`)
- Extend `Into*` protocols for Clojure→Java (e.g., `into-content`, `into-part`)
- Enable transparent use of Clojure maps where Java objects are expected
- Uses `optional-datafy-assoc` utility from `utils.clj` to handle Java Optional, List, Set, Map types

**Example Pattern**:
```clojure
;; Java → Clojure via Datafiable
(extend-protocol Datafiable
  Content
  (datafy [^Content x]
    {:parts (.parts x)
     :role (.role x)}))

;; Clojure → Java via Into* protocol
(extend-protocol IntoContent
  String
  (into-content [^String x]
    (into-content {:role "user" :parts [{:text x}]})))
```

### Custom Java Bridge Classes

Two critical Java classes bridge Clojure and ADK Java:

**ClojureFunctionTool** (`lib/core/src/main/java/io/kosong/adk/tools/ClojureFunctionTool.java`):
- Automatically converts Clojure functions (vars) into ADK tools
- Extracts function metadata (name, docstring, arglists) to generate tool schemas
- Uses var metadata `^{:schema {...}}` on parameters for type specifications
- Special handling: parameters named `tool-context` receive execution context automatically

**ClojureAgent** (`lib/core/src/main/java/io/kosong/adk/agents/ClojureAgent.java`):
- Enables custom agent implementations in pure Clojure
- Accepts Clojure functions for `runAsyncFn` and `runLiveFn`
- Converts between Java `InvocationContext` and Clojure maps using `datafy`

### Agent Composition Hierarchy

Agents are hierarchical and composable:

1. **LLM Agent** (`llm-agent`) - Direct LLM interaction with tools, sub-agents, and callbacks
   - Key parameters: `:name`, `:description`, `:model`, `:instruction`, `:global-instruction`
   - `:sub-agents` - nested agents for delegation
   - `:tools` - function/agent tools
   - `:generate-content-config` - LLM parameters (temperature, top-k, top-p, max-output-tokens, etc.)
   - `:before/after-agent/model/tool-callback` - lifecycle hooks
   - `:input-schema` / `:output-schema` - type specifications
   - `:output-key` - store result in session state
   - `:executor` - custom executor

2. **Loop Agent** (`loop-agent`) - Wraps sub-agents with retry logic
   - Parameters: `:sub-agents`, `:max-iterations`, callbacks

3. **Sequential Agent** (`sequential-agent`) - Runs sub-agents in sequence
   - Parameters: `:name`, `:description`, `:sub-agents`, callbacks

4. **Base Agent** (`base-agent`) - Custom behavior via Clojure functions
   - Parameters: `:name`, `:run-async-fn`, `:run-live-fn`, `:sub-agents`, callbacks

Agents can have:
- **Sub-agents**: Nested agents that can be delegated to (transfer control)
- **Tools**: Functions or other agents converted to tools (via `AgentTool`)
- **Callbacks**: Before/after hooks at agent, model, and tool levels
- **State management**: Via `:output-key` to store results in session state

### Session and State Management

**Session Flow**:
1. Session service (in-memory or custom) stores conversation history and state
2. `agent-context` creates execution context with session/artifact services
3. `with-session` or `with-new-session` attaches session to context
4. Agent execution automatically persists events and state changes
5. State is a `ConcurrentHashMap` (mutable) for performance with concurrent agents

**Context Pattern**:
```clojure
(def context
  (-> (adk/agent-context :app-name "my-app" :user-id "user-123")
      (adk/with-new-session {"initial-key" "value"})))

;; Session state can be modified via :output-key
(adk/llm-agent :output-key "result") ; Stores result in session state
```

### Web Framework Architecture

The web framework (`lib/dev`) uses:

**Integrant** for component lifecycle management:
- `:system/session-service` - Session storage
- `:system/artifact-service` - Artifact storage
- `:system/agent-registry` - Agent registration (atom-based)
- `:system/telemetry` - OpenTelemetry integration
- `:system/http-server` - Pedestal server with Reitit routing

**Agent Registry Pattern**:
- Registry is an atom containing `{agent-name -> agent}` map
- `load-agent-registry!` scans a namespace for root agents (LlmAgent instances without parent)
- Only root agents are registered; sub-agents are discovered via agent hierarchy
- Agents are identified by their `.name` property

**HTTP Endpoints**:
- `POST /api/sessions/{app-name}/{user-id}` - Create session
- `GET /api/sessions/{app-name}/{user-id}` - List sessions
- `POST /api/run/sse` - Run agent with Server-Sent Events streaming
- `GET /api/graph/{app-name}/{user-id}/{session-id}/{event-id}` - Agent graph visualization

### Event Streaming with core.async

Agent execution returns core.async channels:

```clojure
(let [event-ch (adk/run-async context agent message run-config)]
  ;; event-ch emits Event objects as they occur
  (async/<!! event-ch)) ; Blocking read

;; Or convert to lazy sequence
(adk/run context agent message run-config) ; Returns lazy seq of events
```

Streaming modes:
- `RunConfig$StreamingMode/SSE` - Server-sent events (partial responses)
- `RunConfig$StreamingMode/NONE` - Wait for complete responses

### Tool Definition Conventions

When creating Clojure function tools:

1. **Metadata is critical**: Function name, docstring become tool name/description
2. **Parameter schemas**: Use `^{:schema {:type "STRING"}}` metadata on parameters
3. **Type tags**: Support Java type hints (`^String`, `^Integer`, etc.)
4. **Context injection**: Name a parameter `tool-context` to receive execution context
5. **Return values**:
   - Return a map for structured output (keys become response fields)
   - Return any other value → wrapped as `{"result": value}`
   - Return nil → empty response

Example:
```clojure
(defn search-database
  "Searches the database for records"
  [^{:schema {:type "STRING"}} query
   tool-context]  ; Auto-injected
  (let [state (:state tool-context)]
    {:results [...]}))
```

### Model Registration

Custom LLM backends are registered via `LlmRegistry`:

```clojure
(require '[io.kosong.adk.models :as models])

(models/register-llm-factory!
  "custom-provider/*"  ; Pattern matching model names
  (fn [model-name]     ; Factory function
    ;; Return LLM implementation
    ))
```

Common pattern in `dev/system.clj`: Register OpenAI-compatible endpoints (Ollama, vLLM, etc.)

## Key Files and Their Purposes

**lib/core/src/main/clojure/io/kosong/adk/**:
- `core.clj` - Main API (agent builders, run functions, context management)
- `protocols.clj` - Conversion protocols between Clojure and Java types
- `types.clj` - Datafiable implementations and type conversions for Google GenAI types
- `agents.clj` - Agent callback implementations and RunConfig builders
- `tools.clj` - Tool protocol implementations (Var→Tool, Agent→Tool)
- `models.clj` - LLM registry and request/response conversions
- `events.clj` - Event and EventActions conversions
- `sessions.clj` - Session service wrappers
- `utils.clj` - Helper functions (`optional-datafy-assoc` for handling Java Optional/List/Set/Map)

**lib/dev/src/main/clojure/io/kosong/adk/web/**:
- `web.clj` - Integrant system configuration and lifecycle
- `handlers.clj` - HTTP request handlers
- `routes.clj` - Reitit route definitions
- `agent_registry.clj` - Agent discovery and registration
- `http_server.clj` - Pedestal server setup
- `telemetry.clj` - OpenTelemetry configuration

## Important Patterns and Conventions

### Callback Suppression Pattern

In examples like blog-writer, callbacks can suppress output to prevent intermediate results from being shown to the user:

```clojure
(defn suppress-output-callback [callback-context]
  {:role "model" :parts []})

(adk/llm-agent
  :after-agent-callback suppress-output-callback)
```

### Validation Checker Pattern

Use base-agents as validators in loop-agents:

```clojure
(def validation-checker
  (adk/base-agent
    :run-async-fn (fn [context]
                    (if (some? (get-in context [:session :state "key"]))
                      {:actions {:escalate true}}  ; Success, exit loop
                      {}))))                       ; Continue loop

(def robust-agent
  (adk/loop-agent
    :sub-agents [worker-agent validation-checker]
    :max-iterations 3))
```

The `:escalate` action triggers loop exit; otherwise it retries.

### State vs Artifacts

- **State**: Mutable session data (ConcurrentHashMap), for agent-to-agent communication
- **Artifacts**: Immutable blobs (files, images, audio), for storing/retrieving media

### Instruction Sources

Instructions can be static strings or dynamic providers:

```clojure
;; Static
:instruction "You are a helpful assistant"

;; Dynamic (function receives context)
:instruction (fn [context]
               (str "You are assisting " (:user-id context)))
```

## Common Development Patterns

### Testing Agent Behavior

```clojure
(require '[io.kosong.adk.core :as adk])

(def test-agent (adk/llm-agent ...))
(def context (adk/agent-context))

;; Get all events
(def events (adk/run context test-agent "test message" {}))

;; Extract final response
(->> events
     (filter #(= (:author %) (.name test-agent)))
     (last)
     (get-in [:content :parts 0 :text]))
```

### Multi-Agent Orchestration

```clojure
;; Parent agent delegates to sub-agents
(def orchestrator
  (adk/llm-agent
    :name "orchestrator"
    :sub-agents [specialist-1 specialist-2]
    :instruction "Delegate tasks to specialists"))

;; Agent explicitly transfers control
;; The LLM will generate function calls to sub-agents
```

### Adding Telemetry

Telemetry is automatically enabled when using the web framework. Spans are associated with:
- Sessions (via session-id)
- Events (via event-id)
- Accessible via `/api/telemetry/trace/by-event/{event-id}`
