# STACK.md — Technology Stack

## Languages

- **Clojure** 1.12.3 — primary language
- **Java** 17+ — required for Google ADK Java library; custom bridge classes compiled at build time

## Runtime & Package Management

- **Clojure CLI** (`clojure` / `deps.edn`) — dependency management and task runner
- **tools.build** (`clojure -T:build`) — JAR packaging
- No Leiningen; pure deps.edn workspace

## Core Frameworks & Libraries

### Google ADK (Agent Development Kit)
- `com.google.adk/google-adk 0.5.0` — core Java library for AI agent infrastructure
- `com.google.adk/google-adk-dev 0.5.0` — dev/web extensions
- Provides: `LlmAgent`, `LoopAgent`, `SequentialAgent`, `BaseAgent`, `LlmRegistry`, session services

### LLM / AI
- **LangChain4J** — OpenAI-compatible HTTP client used for custom LLM backend registration (Ollama, vLLM, etc.)
- Model backends registered via `LlmRegistry` with glob pattern matching (`"custom-provider/*"`)

### Web Layer (dev library)
- **Pedestal** 0.8.0 — HTTP server with interceptor chain
- **Reitit** 0.9.1 — data-driven routing for Pedestal
- **Integrant** 1.0.1 — component lifecycle management (init/halt)
- **Muuntaja** — content negotiation / JSON encoding

### Async
- **core.async** — event streaming channels; agent execution returns `core.async` channels

### Observability
- **OpenTelemetry** — tracing; spans tied to sessions and events
- `io.opentelemetry/opentelemetry-sdk`, `opentelemetry-exporter-otlp`

### Data
- **clojure.data.json** — JSON serialization/deserialization
- **clojure.tools.logging** — logging facade (SLF4J backend)

## Configuration

- `deps.edn` per module (core, dev, examples)
- Build configuration in `core/build.clj`, `dev/build.clj`
- Java compilation configured via `:deps/prep-lib` hook in `core/deps.edn`
- Environment variables for API keys (Google credentials, model endpoints)

## Module Structure

| Module | Path | Purpose |
|--------|------|---------|
| core | `core/` | Main Clojure↔Java interop library |
| dev | `dev/` | Web framework + REPL tooling |
| examples | `examples/*/` | Standalone example agents |
| experimental | `experimental/` | In-progress features |
| dev-resources | `dev-resources/` | REPL development workspace |

## Build Outputs

- `core/target/` — compiled JAR with Java classes + Clojure sources
- `dev/target/` — dev library JAR
- Java sources compiled first via `:deps/prep-lib` before JAR packaging

## Platform Requirements

- Java 17+ (Google ADK requirement)
- Clojure 1.12.3+
- Google Cloud credentials for Vertex AI (or alternative LLM backend)
