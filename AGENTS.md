# AGENTS.md

This file provides guidance to AI coding agents when working with code in this repository.

## Project Overview

This is adk-clojure, a Clojure wrapper for Google's Agent Development Kit (ADK) Java library. It provides idiomatic Clojure abstractions for building AI agents with Google's Generative AI models. The project consists of two main libraries (core and dev), an experimental module, plus several example agents.

**Requirements:** Java 17+, Clojure 1.12.3

**Related source:** ADK Java library source is at `/home/keytiong/Projects/adk-java` — refer there for Java API signatures, constructors, internal behavior, and debugging.

## Build Commands

### Building Libraries

```bash
# Build core library (includes Java compilation)
cd core
clojure -T:build clean
clojure -T:build jar

# Build dev library (no Java compilation needed)
cd dev
clojure -T:build clean
clojure -T:build jar
```

The core library includes custom Java classes (`ClojureFunctionTool.java` and `ClojureAgent.java`) that must be compiled before building the JAR. The `jar` task automatically runs Java compilation via the `:deps/prep-lib` hook. The dev library has no Java sources.

### Running Examples

Each example has its own deps.edn and can be run independently:

```bash
cd examples/chatbot
clojure -M -m agents.chatbot

cd examples/blog_writer
clojure -M -m agents.blogger
```

### Running Tests

This project uses **Cognitect Labs Test Runner** (`cognitect.test-runner`) for unit tests. Tests are organized as `clojure.test` namespaces and executed via the `-X:test` alias.

> **Important:** Use `clojure -X:test`, **not** `clojure -M:test`. The `-M` flag treats the map args as a file path and throws `FileNotFoundException`. Only `-X` correctly passes the test-runner map arguments.

> **Important:** `-X:test` does **not** recompile changed Java sources — it uses whatever is in `target/classes`. After editing `core/src/main/java/**`, run `clojure -T:build compile-java` before `clojure -X:test`, or tests run against stale classes.

**Run all tests:**

```bash
cd core
clojure -X:test
```

**Run a single test namespace** (e.g. `utils-test`):

```bash
cd core
clojure -X:test :only io.kosong.adk.utils-test
```

**Run a specific test by name** (e.g. `optional-datafy-assoc-nil-test` within a namespace):

```bash
cd core
clojure -X:test :only-ns '[:only io.kosong.adk.utils-test/optional-datafy-assoc-nil-test]
```

**Run tests tagged with `:focus`:**

Add `:focus true` metadata to any `deftest` to narrow the run:

```clojure
(deftest ^:focus some-test
  (is (= 1 1)))
```

Then run with:

```bash
cd core
clojure -X:test :focus true
```

**Common Cognitect test-runner parameters (passed as map keys after `-X:test`):**

| Parameter | Description | Example |
|-----------|-------------|---------|
| `:only` | Run specific namespace(s) | `:only io.kosong.adk.utils-test` |
| `:focus` | Run only tests marked `^:focus` | `:focus true` |
| `:testing-load-hooks` | Add custom hooks loaded before tests | `:testing-load-hooks [my.hooks/hook-fn]` |
| `:dirs` | Directories to scan for tests (default: `["src/test/clojure"]`) | `:dirs ["src/test/clojure"]` |
| `:load-hooks` | Namespace load hooks for test fixtures | `:load-hooks [my.ns/setup]` |
| `:fail-fast` | Stop on first failure | `:fail-fast true` |
| `:summary` | Control summary report | `:summary false` |
| `:report` | Report format (e.g. `progress`, `event`) | `:report progress` |

**Example — run only `utils-test` and `runner-test` with progress reporting:**

```bash
cd core
clojure -X:test :only '[io.kosong.adk.utils-test io.kosong.adk.runner-test]' :report progress
```

### REPL Development

The recommended workflow is REPL-driven from the `dev-resources` directory:

```bash
cd dev-resources
clojure -M:dev
```

Then in the REPL:

```clojure
(require '[io.kosong.adk.web :as adk-web])

;; Start web server with agent namespaces
(adk-web/run {:port 8080
              :agent-namespaces ['agents.chatbot 'agents.blogger]})

;; Stop web server
(adk-web/stop!)
```

Alternatively, run examples with their ADK web alias:

```bash
cd examples/chatbot
clojure -X:adk-web
```

## REPL-Driven Debugging with nREPL

Always prefer a running nREPL server for debugging Clojure code, testing expressions, and verifying namespace reloads after file edits. Use the `clj-nrepl-eval` CLI tool to evaluate code against an nREPL server.

### Before Your Session

Start an nREPL server if one isn't already running. The agent can discover it:

```bash
clj-nrepl-eval --discover-ports
```

### After Editing a Clojure File

1. **Reload the namespace** to pick up changes (always use `:reload`):

```bash
clj-nrepl-eval -p <PORT> "(require '[io.kosong.adk.core :as adk] :reload)"
```

2. **Evaluate expressions to verify behavior** (prefer heredoc for multiline code):

```bash
clj-nrepl-eval -p <PORT> <<'EOF'
(require '[io.kosong.adk.core :as adk] :reload)
(adk/run (adk/agent-context) my-agent "test message" {})
EOF
```

3. **Reset the session** to clear dynamic vars without losing loaded namespaces:

```bash
clj-nrepl-eval -p <PORT> --reset-session
```

### Guidelines

- **Always use `:reload`** when requiring namespaces — this picks up the latest file changes
- **Prefer heredoc (`<<'EOF'`)** for multiline code to avoid shell escaping issues
- **Run REPL checks before committing** code changes to catch errors early
- **Session state persists** across evaluations — vars, namespaces, and loaded libraries survive
- **Fix delimiter errors with `clj-paren-repair <file>`** instead of manually editing parentheses

## Architecture

### Core Abstraction Pattern: Protocol-Based Java Interop

The architecture uses Clojure protocols extensively to provide seamless bidirectional conversion between Clojure data structures and Google ADK Java objects. This pattern is central to the entire codebase:

**Protocols** (`core/src/main/clojure/io/kosong/adk/protocols.clj`):
- Define 11 conversion protocols organized by domain
- Agent-related: `IntoAgent`, `IntoTool`, `IntoInstruction`, callback protocols (`IntoBeforeModelCallback`, `IntoAfterModelCallback`, `IntoBeforeAgentCallback`, `IntoAfterAgentCallback`, `IntoBeforeToolCallback`, `IntoAfterToolCallback`)
- Event-related: `IntoEvent`, `IntoEventActions`
- All conversions flow through these protocols for consistency

**Type Implementations** (`core/src/main/clojure/io/kosong/adk/types.clj`):
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

**ClojureFunctionTool** (`core/src/main/java/io/kosong/adk/tools/ClojureFunctionTool.java`):
- Automatically converts Clojure functions (vars) into ADK tools
- Extracts function metadata (name, docstring, arglists) to generate tool schemas
- Uses var metadata `^{:schema {...}}` on parameters for type specifications
- Special handling: parameters named `tool-context` receive execution context automatically

**ClojureAgent** (`core/src/main/java/io/kosong/adk/agents/ClojureAgent.java`):
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

The web framework (`dev`) uses:

**Integrant** for component lifecycle management:
- `:system/session-service` - Session storage
- `:system/artifact-service` - Artifact storage
- `:system/agent-registry` - Agent registration (atom-based)
- `:system/telemetry` - OpenTelemetry integration
- `:system/app-context` - Context holding service refs (session-service, artifact-service, agent-registry, telemetry)
- `:system/http-server` - Pedestal server with Reitit routing

**Agent Registry Pattern**:
- Registry is an atom containing `{agent-name -> agent}` map
- `load-agent-registry!` scans a namespace for root agents (LlmAgent instances without parent)
- Only root agents are registered; sub-agents are discovered via agent hierarchy
- Agents are identified by their `.name` property

**HTTP Endpoints**:
- `POST /apps/:app-name/users/:user-id/sessions` - Create session
- `GET /apps/:app-name/users/:user-id/sessions` - List sessions
- `GET /apps/:app-name/users/:user-id/sessions/:session-id` - Get session
- `DELETE /apps/:app-name/users/:user-id/sessions/:session-id` - Delete session
- `POST /run_sse` - Run agent with Server-Sent Events streaming (body: `{app-name, user-id, session-id, new-message, streaming}`)
- `GET /run_live` - Run agent with WebSocket bidirectional live streaming (query params: app_name, user_id, session_id)
- `GET /apps/:app-name/users/:user-id/sessions/:session-id/events/:event-id/graph` - Agent graph visualization
- `GET /debug/trace/:event-id` - Get trace spans by event ID
- `GET /debug/trace/session/:session-id` - Get trace spans by session ID
- `GET /list-apps` - List registered apps
- `GET /index.html` - Frontend UI
- `GET /apps/:app-name/eval_sets` - List eval sets (stub)
- `GET /apps/:app-name/eval_results` - List eval results (stub)

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
- `RunConfig$StreamingMode/BIDI` - Bidirectional streaming for live connections

### Bidirectional Live Streaming with run-live

The `run-live` function provides real-time bidirectional communication between clients and agents via WebSockets:

```clojure
(let [{:keys [event-ch request-ch]} (adk/run-live context agent initial-content run-config)]
  ;; request-ch: Send LiveRequest maps to agent (blocking buffer 10)
  (async/>!! request-ch {:content "Hello"})
  (async/>!! request-ch {:blob {:mime-type "audio/pcm" :data "..."}})
  (async/>!! request-ch {:close true})

  ;; event-ch: Receive Event objects from agent (sliding buffer 16)
  (async/<!! event-ch))
```

**LiveRequest Format:**
- `{:content ...}` - Send turn-by-turn content (text/parts)
- `{:blob {:mime-type "..." :data "..."}}` - Send realtime audio/video blob
- `{:close true}` - Close the live connection

**Channel Behavior:**
- `event-ch`: Sliding buffer (16) - drops oldest events if consumer is slow
- `request-ch`: Blocking buffer (10) - applies backpressure to prevent overwhelming agent

**WebSocket Endpoint** (`GET /run_live`):
- Query params: `app_name`, `user_id`, `session_id`
- Client → Server: JSON LiveRequest objects
- Server → Client: JSON Event objects
- Connection limits: 10 per user, 100 per app
- Frame size: 10MB max
- Idle timeout: 5 minutes

**Example WebSocket Usage** (JavaScript):
```javascript
const ws = new WebSocket('ws://localhost:8080/run_live?app_name=my-agent&user_id=user123&session_id=session456');

ws.onopen = () => {
  ws.send(JSON.stringify({content: {role: "user", parts: [{text: "Hello!"}]}}));
};

ws.onmessage = (event) => {
  const data = JSON.parse(event.data);
  console.log('Event:', data);
};

ws.send(JSON.stringify({close: true})); // Close connection
```

**Use Cases:**
- Real-time voice conversations (audio streaming)
- Continuous interaction without request/response overhead
- Multi-modal applications (video + audio + text)
- Live transcription and feedback

See `examples/live-chatbot` for a complete working example.

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

**core/src/main/clojure/io/kosong/adk/**:
- `core.clj` - Main API (agent builders, run functions, context management)
- `protocols.clj` - Conversion protocols between Clojure and Java types (11 protocols)
- `types.clj` - Type conversion implementations
- `agents.clj` - Agent callback implementations and RunConfig builders
- `tools.clj` - Tool protocol implementations (Var→Tool, Agent→Tool)
- `models.clj` - LLM registry and request/response conversions
- `events.clj` - Event and EventActions conversions (datafy + IntoEvent)
- `sessions.clj` - Session service wrappers (in-memory + Vertex AI)
- `artifacts.clj` - Artifact service wrapper (in-memory)
- `runner.clj` - Live request queue bridge for run-live streaming
- `utils.clj` - Helper functions (`optional-datafy-assoc` for handling Java Optional/List/Set/Map)
- `core/src/generated/clojure/io/kosong/genai/types.clj` - Auto-generated Clojure source (ns `io.kosong.genai.types`) for Google GenAI AutoValue types (tracked in git; regenerate with `clojure -T:build generate-types`, never hand-edit)

**core/src/main/clojure/io/kosong/**:
- `java.clj` - `make-object` multi-method for creating Java instances from Clojure maps

### make-object: Creating Google GenAI Objects from Clojure Maps

`make-object` is a `defmulti` dispatched on the target Java class. It takes a Java class and a Clojure map, uses the class's AutoValue builder to construct an instance, and recursively handles nested objects and collections. This is the primary way to create Google GenAI types (`com.google.genai.types.*`) from Clojure data:

```clojure
(require '[io.kosong.java])

;; Create a GenerateContentConfig from a map
(io.kosong.java/make-object
  com.google.genai.types.GenerateContentConfig
  {:top-p              0.8
   :max-output-tokens  4096
   :stop-sequences     ["User:" "Human:"]
   :system-instruction {:role "system"
                        :parts [{:text "You are a helpful assistant"}]}
   :labels             {"env" "test"}
   :safety-settings    [{:category  "HARM_CATEGORY_DANGEROUS_CONTENT"
                         :threshold "BLOCK_ONLY_HIGH"}]})
```

**Key behaviors:**
- **Pass-through:** If the data is already an instance of the target class, it's returned as-is
- **Recursive:** Nested maps and collections are automatically converted via recursive `make-object` calls (e.g., `:parts` in `Content` maps to `Part` objects)
- **Enum support:** String enum values (e.g., `"BLOCK_ONLY_HIGH"`) are resolved to Java enum constants
- **Optional fields:** Missing keys are simply omitted (builder defaults apply)

Implemented methods are auto-generated in `core/src/generated/clojure/io/kosong/genai/types.clj` (ns `io.kosong.genai.types`) for all `com.google.genai.types.*` AutoValue classes (separate source root; regenerate with `clojure -T:build generate-types`). Check availability with `(io.kosong.java/make-object-able? SomeClass)`.

**Bidirectional pattern:** `make-object` (map → Java) pairs with `datafy` (Java → map), so you can round-trip GenAI objects freely. Together with the generated `io.kosong.genai.types` namespace, this covers all `com.google.genai.types.*` types.

**dev/src/main/clojure/io/kosong/adk/web/**:
- `web.clj` - Integrant system configuration and lifecycle
- `handlers.clj` - HTTP request handlers (SSE, WebSocket, session CRUD)
- `routes.clj` - Reitit route definitions
- `agent_registry.clj` - Agent discovery and registration (root-agent scanning)
- `agent_graph.clj` - Graphviz DOT graph generation for agent visualization
- `http_server.clj` - Pedestal server setup with Reitit routing
- `telemetry.clj` - OpenTelemetry configuration and trace storage

**Other modules**:
- `experimental/` - Experimental features and utilities
- `dev-resources/` - Development resources with sample `system.clj` for LLM factory registration

## Important Patterns and Conventions

### Callback Suppression Pattern

In examples like blog-writer, callbacks can suppress output to prevent intermediate results from being shown to the user:

```clojure
(defn suppress-output-callback [callback-context]
  {:role "model" :parts []})

(adk/llm-agent
  :after-agent-callback suppress-output-callback)
```

The callback receives a map with keys like `:agent-name`, `:state`, `:invocation-id`, `:branch`, `:user-content`. Returning `{:role "model" :parts []}` suppresses the output event.

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

- **State**: Mutable session data (`ConcurrentHashMap`), for agent-to-agent communication and persistent key/value storage within a session
- **Artifacts**: Immutable blobs (files, images, audio), for storing/retrieving media. Accessible via `artifact-service` in the runner context

### Instruction Sources

Instructions can be static strings or dynamic providers:

```clojure
;; Static
:instruction "You are a helpful assistant"

;; Dynamic (function receives context)
:instruction (fn [context]
               (str "You are assisting " (:user-id context)))
```

Dynamic instructions return a `Single<String>` via the `Instruction$Provider` pattern.

### Adding Telemetry

Telemetry is automatically enabled when using the web framework. Spans are associated with:
- Sessions (via session-id)
- Events (via event-id)
- Accessible via `/debug/trace/:event-id` and `/debug/trace/session/:session-id`

Traces store spans for `call_llm`, `send_data`, and `tool_response` events.

## Interactive Development via nREPL

Always use a running nREPL server for debugging, testing, and verifying code changes. The `clj-nrepl-eval` CLI tool (from `clojure-mcp-light`) lets you evaluate Clojure code against an nREPL server from the command line.

### Workflow

1. **Start an nREPL server** (if not already running):

```bash
# From dev-resources or any module with an :nrepl alias
cd dev-resources && clojure -M:dev:cljs:deps/nrepl --headless &
```

2. **Discover the nREPL port**:

```bash
clj-nrepl-eval --discover-ports
```

3. **Reload a namespace after editing** (always use `:reload` to pick up file changes):

```bash
clj-nrepl-eval -p <PORT> <<'EOF'
(require '[io.kosong.adk.core :as adk] :reload)
EOF
```

4. **Evaluate expressions to debug** (prefer heredoc for multiline code to avoid shell escaping):

```bash
clj-nrepl-eval -p <PORT> <<'EOF'
(require '[io.kosong.adk.core :as adk] :reload)
(adk/run (adk/agent-context) my-agent "test input" {})
EOF
```

5. **Reset the REPL session** when needed (clears dynamic vars, keeps loaded namespaces):

```bash
clj-nrepl-eval -p <PORT> --reset-session
```

### Key Guidelines

- **Always reload namespaces with `:reload`** after any file modification to pick up changes
- **Use heredoc (`<<'EOF'`) for multiline code** to avoid shell escaping issues with quotes and backslashes
- **Test behavior before committing** — evaluate expressions, check return values, verify no errors
- **If `clj-nrepl-eval --discover-ports` finds no server**, start one first before evaluating code
- **Session state persists** across invocations — vars, namespaces, and loaded libraries survive until the server restarts
- **Fix delimiter errors with `clj-paren-repair <file>`** if you encounter mismatched parentheses; do not try to fix them manually

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
