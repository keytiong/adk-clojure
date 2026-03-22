# Architecture

**Analysis Date:** 2026-03-22

## Pattern Overview

**Overall:** Protocol-based bidirectional interop with hierarchical agent composition

The adk-clojure architecture uses Clojure protocols to seamlessly bridge between Clojure data structures and Google ADK Java objects, enabling idiomatic Clojure abstractions while maintaining compatibility with the underlying Java Agent Development Kit. The system is organized in three layers:

1. **Core Interop Layer** - Java/Clojure type conversions via protocols
2. **Agent Orchestration Layer** - Agent builders and execution flow
3. **Web/Integration Layer** - HTTP/WebSocket exposure via Integrant component system

**Key Characteristics:**
- Protocol-driven polymorphic conversions for type safety and extensibility
- Custom Java bridge classes (`ClojureFunctionTool`, `ClojureAgent`) for deep Clojure integration
- Hierarchical agent composition with sub-agents and tools
- Core.async channels for event streaming and bidirectional live communication
- Datafiable for Java→Clojure transformation, Into* protocols for Clojure→Java
- Integrant-based component lifecycle for web services

## Layers

**Protocol Conversion Layer:**
- Purpose: Enable transparent bidirectional conversion between Java objects and Clojure maps
- Location: `core/src/main/clojure/io/kosong/adk/protocols.clj`, `core/src/main/clojure/io/kosong/adk/types.clj`
- Contains: ~30 protocols for agents, tools, callbacks, events, and schemas
- Depends on: Google ADK Java APIs, Clojure core protocols (Datafiable)
- Used by: All other layers

**Java Interop Module:**
- Purpose: Handle AutoValue object conversion and generic Java object construction
- Location: `core/src/main/clojure/io/kosong/autovalue.clj`, `core/src/main/clojure/io/kosong/java.clj`
- Contains: AutoValue detection and bidirectional conversion logic, multimethod dispatch
- Depends on: Java reflection API, Clojure protocols
- Used by: Type conversion layer for nested object serialization

**Agent Composition Layer:**
- Purpose: Define agent builders and lifecycle callback handling
- Location: `core/src/main/clojure/io/kosong/adk/agents.clj`
- Contains:
  - Protocol extensions for agent callbacks (before/after model, agent, tool)
  - Datafiable extensions for `InvocationContext`, `CallbackContext`, `ReadonlyContext`
  - Callback function wrappers converting Clojure functions to Java callbacks
- Depends on: Protocol layer, RxJava (Maybe/Single types for callback results)
- Used by: Agent builders and runtime

**Agent Building Layer:**
- Purpose: Provide idiomatic API for constructing agents
- Location: `core/src/main/clojure/io/kosong/adk/core.clj`
- Contains:
  - `agent-context` - creates execution context with services
  - `with-new-session`, `with-session` - session management
  - `base-agent`, `llm-agent`, `loop-agent`, `sequential-agent` - agent factories
  - `run-async`, `run` - execution entry points
- Depends on: Agent composition layer, session/artifact services
- Used by: Examples and web layer

**Tool Bridge Layer:**
- Purpose: Convert Clojure vars/functions and agents into ADK tools
- Location: `core/src/main/clojure/io/kosong/adk/tools.clj`
- Contains: Protocol extensions for Var, Symbol, BaseTool, BaseAgent types
- Depends on: `ClojureFunctionTool` (Java bridge), agent protocol layer
- Used by: Agent builders, tool registration

**Model Registry Layer:**
- Purpose: Support custom LLM backends via pattern-matched factory functions
- Location: `core/src/main/clojure/io/kosong/adk/models.clj`
- Contains: `register-llm-factory!` for registering model name patterns to factories
- Depends on: Google ADK LlmRegistry
- Used by: Examples for OpenAI-compatible endpoints (Ollama, vLLM, etc.)

**Event Streaming Layer:**
- Purpose: Convert Java Event objects to Clojure maps and vice versa
- Location: `core/src/main/clojure/io/kosong/adk/events.clj`
- Contains: Datafiable extensions for Event, EventActions; IntoEvent protocol
- Depends on: Protocol layer, Google ADK event types
- Used by: Runtime execution, handler responses

**Live Request Queue Layer:**
- Purpose: Bridge core.async channels to Java LiveRequestQueue for bidirectional streaming
- Location: `core/src/main/clojure/io/kosong/adk/runner.clj`
- Contains: `live-request-queue` factory that creates async channel ↔ Java queue bridge
- Depends on: Core.async, Google ADK LiveRequestQueue
- Used by: WebSocket handlers for real-time agent communication

**Service Abstraction Layer:**
- Purpose: Provide factory functions for session, artifact, and memory services
- Location: `core/src/main/clojure/io/kosong/adk/sessions.clj`, `core/src/main/clojure/io/kosong/adk/artifacts.clj`, `core/src/main/clojure/io/kosong/adk/memory.clj`
- Contains: Constructors for InMemory and Vertex AI implementations; Datafiable for Session
- Depends on: Google ADK service interfaces
- Used by: Agent context, component initialization

**Web Component Layer:**
- Purpose: Integrant-based component lifecycle and composition
- Location: `dev/src/main/clojure/io/kosong/adk/web.clj`
- Contains: Init/halt methods for `:system/agent-registry`, `:system/session-service`, `:system/artifact-service`, `:system/telemetry`, `:system/http-server`
- Depends on: Integrant, Google ADK services, HTTP server
- Used by: `run` function for starting web services

**HTTP Handler Layer:**
- Purpose: Implement request/response handling for all endpoints
- Location: `dev/src/main/clojure/io/kosong/adk/web/handlers.clj`
- Contains: Handlers for sessions, agent execution (SSE and WebSocket), tracing, and graph visualization
- Depends on: Pedestal interceptors, core.async, ADK core, Reitit
- Used by: Routes

**Routing Layer:**
- Purpose: Define HTTP and WebSocket routes with parameter validation
- Location: `dev/src/main/clojure/io/kosong/adk/web/routes.clj`
- Contains: Reitit route definitions with Malli schema validation
- Depends on: Reitit, handlers
- Used by: HTTP server

**HTTP Server Layer:**
- Purpose: Configure Pedestal HTTP server with interceptors and serialization
- Location: `dev/src/main/clojure/io/kosong/adk/web/http_server.clj`
- Contains:
  - Pedestal interceptor chain (tracing, logging, secure headers)
  - Muuntaja JSON serialization with camelCase conversion
  - Reitit router configuration with Malli coercion
  - Connector initialization
- Depends on: Pedestal, Reitit, Muuntaja, http-kit
- Used by: Component system

**Agent Registry Layer:**
- Purpose: Discover and register agents from namespaces
- Location: `dev/src/main/clojure/io/kosong/adk/web/agent_registry.clj`
- Contains: `find-root-agents` (scans namespace for LlmAgent instances), registry atom
- Depends on: Clojure reflection
- Used by: Component system and web handlers

**Telemetry Layer:**
- Purpose: OpenTelemetry integration for distributed tracing
- Location: `dev/src/main/clojure/io/kosong/adk/web/telemetry.clj`
- Contains: OpenTelemetry SDK initialization and configuration
- Depends on: OpenTelemetry libraries
- Used by: Component system and request context

## Data Flow

**Agent Execution Flow:**

1. Create context with `agent-context` (specifies app-name, user-id, services)
2. Attach session with `with-new-session` or `with-session`
3. Call `run-async` or `run` with context, agent, user input, and run config
4. Runtime executes agent:
   - Converts Clojure maps to Java Content objects via `IntoContent` protocol
   - Agent processes with LLM and tools
   - Events emitted as Java Event objects
5. Events converted back to Clojure maps via Datafiable protocol
6. Core.async channel delivers events to caller

**WebSocket Live Streaming Flow:**

1. Client connects to `/run_live` WebSocket with query params (app_name, user_id, session_id)
2. Handler creates `live-request-queue` with async channels:
   - `request-ch`: Accepts client messages (text, audio blobs, close signal)
   - `event-ch`: Emits agent responses
3. Core.async bridge connects JavaScript to Java LiveRequestQueue
4. Agent runs with bidirectional streaming:
   - Client sends LiveRequest via channel
   - Agent processes in real-time
   - Events streamed back to client
   - Connection closes when client sends `{:close true}`

**HTTP SSE Flow:**

1. Client POSTs to `/run_sse` with agent name, message, and streaming config
2. Handler sets up core.async pipeline
3. Agent runs with `StreamingMode/SSE`
4. Events streamed as Server-Sent Events
5. Connection persists until agent completes

**State Management:**

- Session state stored in `ConcurrentHashMap` (mutable for concurrent safety)
- Agents modify state via `:output-key` parameter (stores result in session state)
- State mutations visible across agent invocations within same session
- EventActions can include `:state-delta` for explicit state updates

## Key Abstractions

**Protocol-Based Conversion:**
- Purpose: Unify Java↔Clojure conversion across all ADK types
- Examples: `IntoContent`, `IntoPart`, `IntoAgent`, `IntoTool`
- Pattern: Extend protocol on Clojure type (e.g., String, IPersistentMap) to convert to Java type
- Benefit: Clojure code can use native data structures; automatic conversion at ADK boundary

**Custom Java Bridge Classes:**
- Purpose: Enable Clojure functions and agents as native ADK tools/agents
- `ClojureFunctionTool` (Java): Converts Clojure vars to ADK tools via reflection on metadata
- `ClojureAgent` (Java): Accepts Clojure functions for `runAsyncFn` and `runLiveFn`
- Pattern: Inspect var metadata for function name, docstring, parameter schemas

**Agent Hierarchy:**
- Purpose: Support composition and delegation patterns
- Root agents: Top-level LlmAgent instances (registered in web layer)
- Sub-agents: Nested agents that parent agents delegate to
- Tools: Sub-agents converted to tools via `AgentTool` for LLM function calls
- Benefit: Complex behaviors via agent composition without explicit orchestration

**Context-Based Execution:**
- Purpose: Thread execution metadata through agent execution
- InvocationContext: Full context with services, session, config, user content
- CallbackContext: Lightweight context available to callbacks
- ReadonlyContext: Immutable context for tools
- Pattern: Callbacks datafy contexts to Clojure maps for inspection/modification

**Callback Lifecycle Hooks:**
- Purpose: Allow inspection and modification at key execution points
- Before-model: Modify LLM request before sending
- After-model: Process LLM response before agent uses it
- Before-agent: Initialize agent state
- After-agent: Post-process agent results
- Before/after-tool: Intercept tool calls
- Return value: Optional modified Java object or empty to skip

**EventActions for Control Flow:**
- Purpose: Signal control flow changes from callbacks
- `:escalate` - Exit loop agent early
- `:transfer-to-agent` - Delegate to another agent
- `:state-delta` - Update session state
- `:end-invocation` - Terminate execution

## Entry Points

**Core Library Entry Points:**

**`agent-context`** (`core/src/main/clojure/io/kosong/adk/core.clj`)
- Triggers: Program initialization
- Responsibilities: Create execution context with services (session, artifact, memory), set app-name and user-id
- Returns: Map containing service references and configuration

**`agent builders`** (`core/src/main/clojure/io/kosong/adk/core.clj`):
- `base-agent` - Triggers: Custom behavior via Clojure functions
- `llm-agent` - Triggers: LLM with tools/sub-agents
- `loop-agent` - Triggers: Retry logic with validation
- `sequential-agent` - Triggers: Sequential sub-agent execution
- Responsibilities: Build and return agent instances configured for specific patterns

**`run-async`** / **`run`** (`core/src/main/clojure/io/kosong/adk/core.clj`)
- Triggers: Agent execution request
- Responsibilities: Execute agent with context/input, return core.async channel or lazy sequence of events
- Returns: Channel of Event objects or lazy sequence

**Web Layer Entry Points:**

**`run` function** (`dev/src/main/clojure/io/kosong/adk/web.clj`)
- Triggers: Development server startup via REPL or example invocation
- Responsibilities: Initialize Integrant system with all components
- Returns: System map with running HTTP server

**`POST /apps/:app-name/users/:user-id/sessions`** (handlers.clj)
- Triggers: Client session creation
- Responsibilities: Create session with optional initial state
- Returns: Session object with id and state

**`POST /run_sse`** (handlers.clj)
- Triggers: Agent execution request with streaming
- Responsibilities: Execute agent and stream events as Server-Sent Events
- Returns: SSE stream of events

**`GET /run_live`** (handlers.clj)
- Triggers: WebSocket connection upgrade
- Responsibilities: Establish bidirectional agent communication via WebSocket
- Returns: WebSocket connection with event/request channels

**`GET /api/graph/:app-name/:user-id/:session-id/:event-id`** (handlers.clj)
- Triggers: Agent execution visualization request
- Responsibilities: Render agent hierarchy and event flow graph
- Returns: HTML/JSON graph representation

## Error Handling

**Strategy:** RxJava Maybe monad for callback results, exception propagation in event streams

**Patterns:**

- **Callback Errors:** Wrapped in `Maybe/error` which triggers invocation error in ADK
- **Tool Errors:** Tools return error responses which agent processes; may retry via loop-agent
- **Agent Errors:** Captured in Event with `:error-code` and `:error-message` fields
- **Validation Errors:** HTTP handlers return 400/422 with Malli error details
- **Service Errors:** Session/artifact service exceptions bubble up to HTTP handlers

## Cross-Cutting Concerns

**Logging:**
- `clojure.tools.logging` for core library
- Pedestal service.interceptors/log-request for HTTP
- Location: Import clojure.tools.logging and call `(log/info "message")`

**Validation:**
- Malli schemas for HTTP request/response validation in routes
- Parameter schemas in tool definitions via `^{:schema {...}}`
- Function arglists for tool discovery in ClojureFunctionTool

**Authentication:**
- Custom implementation via context (user-id in agent-context)
- WebSocket connection params: app-name, user-id, session-id
- No built-in auth; enforce via handlers or HTTP middleware

**State Management:**
- Session state: ConcurrentHashMap modified via `:output-key` or EventActions
- Agent local state: Via `:run-async-fn` in base-agent
- Artifact state: Via artifact service (immutable blobs)
- Memory service: For agent memory/context windows (Vertex AI)

---

*Architecture analysis: 2026-03-22*
