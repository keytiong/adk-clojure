# ARCHITECTURE.md — System Architecture

## Pattern

**Protocol-Based Java Interop Library** with a layered web framework on top.

The core pattern: bidirectional conversion between Clojure data and Google ADK Java objects via Clojure protocols. Everything flows through ~30 conversion protocols for consistency.

## Layers

```
┌─────────────────────────────────────────────┐
│              Example Agents                  │  examples/chatbot, examples/blog_writer, etc.
├─────────────────────────────────────────────┤
│           Web Framework (dev)                │  HTTP/WS endpoints, SSE streaming
│   Integrant system + Pedestal + Reitit       │
├─────────────────────────────────────────────┤
│           Public API (core.clj)              │  llm-agent, run-async, run-live, agent-context
├─────────────────────────────────────────────┤
│        Agent Builders (agents.clj)           │  LlmAgent, LoopAgent, SequentialAgent, BaseAgent
│        Tool Builders (tools.clj)             │  Var→Tool, Agent→Tool conversions
│        Model Layer (models.clj)              │  LlmRegistry, request/response conversions
├─────────────────────────────────────────────┤
│      Type Conversion Layer (types.clj)       │  Datafiable (Java→Clojure), Into* (Clojure→Java)
│      Protocol Definitions (protocols.clj)    │  ~30 conversion protocols
├─────────────────────────────────────────────┤
│        Custom Java Bridge Classes            │  ClojureFunctionTool, ClojureAgent
├─────────────────────────────────────────────┤
│         Google ADK Java Library              │  LlmAgent, SessionService, ArtifactService, etc.
└─────────────────────────────────────────────┘
```

## Core Abstractions

### 1. Protocol Conversion (`protocols.clj`, `types.clj`)

The central abstraction. ~30 protocols in two directions:

- **Java→Clojure**: `Datafiable` extensions on ADK Java objects → `(datafy obj)` returns Clojure maps
- **Clojure→Java**: `Into*` protocols (IntoAgent, IntoContent, IntoPart, IntoSchema, etc.)

This enables: pass a Clojure map where a Java object is expected, get a Clojure map back from any Java object.

```clojure
;; Java → Clojure
(datafy some-adk-content) ;=> {:role "user" :parts [{:text "Hello"}]}

;; Clojure → Java
(into-content {:role "user" :parts [{:text "Hello"}]}) ;=> Content Java object
(into-content "Hello") ;=> Content Java object (String extension)
```

### 2. Agent Hierarchy

Agents compose hierarchically. Four types:

| Type | Builder | Java Class | Purpose |
|------|---------|-----------|---------|
| LLM Agent | `llm-agent` | `LlmAgent` | Direct LLM interaction with tools/sub-agents |
| Loop Agent | `loop-agent` | `LoopAgent` | Retry logic with max-iterations |
| Sequential Agent | `sequential-agent` | `SequentialAgent` | Run sub-agents in order |
| Base Agent | `base-agent` | `ClojureAgent` | Custom Clojure function behavior |

Agents reference sub-agents (delegation via transfer) and tools (function invocation).

### 3. Java Bridge Classes

**ClojureFunctionTool** (`core/src/main/java/...`):
- Converts Clojure vars → ADK `BaseTool`
- Reads var metadata: name → tool name, docstring → description, arglists → parameter schema
- `^{:schema {:type "STRING"}}` parameter metadata → JSON schema
- Parameters named `tool-context` receive execution context automatically

**ClojureAgent** (`core/src/main/java/...`):
- Converts Clojure functions → ADK `BaseAgent`
- Accepts `runAsyncFn` and `runLiveFn` as Clojure functions
- Bridges Java `InvocationContext` ↔ Clojure maps via `datafy`

### 4. Session & Context

```
agent-context (app-name, user-id)
  └─ with-new-session / with-session
       └─ session (state: ConcurrentHashMap, history: events)
            └─ run-async / run-live → core.async channel of Events
```

Context propagates through execution; state mutations via `:output-key` on agents.

### 5. Event Streaming

```
run-async → core.async channel → Event objects
run-live → {:event-ch channel, :request-ch channel}
```

- `run-async`: Unidirectional; sliding buffer; SSE or NONE streaming mode
- `run-live`: Bidirectional; event-ch (sliding 16) + request-ch (blocking 10); WebSocket

### 6. Web Framework (Integrant System)

```
system/session-service   → session storage
system/artifact-service  → artifact storage
system/agent-registry    → atom: {agent-name → agent}
system/telemetry         → OpenTelemetry SDK init
system/http-server       → Pedestal server
```

Agent registry: scans namespaces for root `LlmAgent` instances (no parent), registers by `.name`.

## Data Flow

### SSE Request
```
HTTP POST /api/run/sse
  → handler (handlers.clj)
  → lookup agent in registry
  → run-async context agent message run-config
  → stream Events via SSE
```

### WebSocket Live
```
WS GET /api/run_live?...
  → handler upgrades to WebSocket
  → run-live context agent initial-content run-config
  → bidirectional: client→request-ch, event-ch→client
```

### Tool Execution
```
LlmAgent receives function call
  → ClojureFunctionTool.execute()
  → calls Clojure var with args
  → auto-injects tool-context if parameter present
  → return value converted to tool response
```

## Entry Points

- **REPL**: `dev-resources/` with `io.kosong.adk.web/run` and `stop!`
- **Examples**: `clojure -M -m agents.chatbot` etc.
- **Web alias**: `clojure -X:adk-web` in example dirs
- **Library**: `(require '[io.kosong.adk.core :as adk])` + `(adk/llm-agent ...)`
