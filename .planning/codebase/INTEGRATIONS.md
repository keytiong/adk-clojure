# INTEGRATIONS.md — External Integrations

## LLM Providers

### Google Vertex AI / Gemini (Primary)
- **Integration point**: Google ADK `LlmRegistry` + Google credentials
- **Configuration**: Environment credentials (Application Default Credentials or service account)
- **Models**: Gemini family (gemini-2.0-flash, etc.)
- **Registration**: Built into ADK; no explicit registration needed

### OpenAI-Compatible Backends (Custom)
- **Integration point**: `io.kosong.adk.models/register-llm-factory!`
- **Pattern**: `"provider/*"` glob matching model name strings
- **Supported**: Ollama, vLLM, any OpenAI-compatible HTTP endpoint
- **Location**: `dev/src/main/clojure/io/kosong/adk/web/system.clj`

```clojure
(models/register-llm-factory!
  "ollama/*"
  (fn [model-name] ...))
```

## Session Storage

### In-Memory (Default)
- `InMemorySessionService` — built-in ADK implementation
- State stored in `ConcurrentHashMap`
- Not persistent across restarts

### Vertex AI Session Service
- Cloud-backed session persistence
- Configured via ADK `VertexAiSessionService`
- Requires Google Cloud project credentials

## Artifact Storage

### In-Memory (Default)
- `InMemoryArtifactService` — built-in ADK implementation
- Stores blobs (images, audio, files) in memory
- Used for agent-produced media artifacts

### Vertex AI Artifact Service
- Cloud-backed artifact storage
- For production deployments

## HTTP / API Layer

### Pedestal HTTP Server
- **Port**: 8080 (configurable)
- **Endpoints exposed**:
  - `POST /api/sessions/{app-name}/{user-id}` — create session
  - `GET /api/sessions/{app-name}/{user-id}` — list sessions
  - `POST /api/run/sse` — SSE streaming agent execution
  - `GET /api/run_live` — WebSocket bidirectional live streaming
  - `GET /api/graph/{app-name}/{user-id}/{session-id}/{event-id}` — agent graph
  - `GET /api/telemetry/trace/by-event/{event-id}` — telemetry traces

### WebSocket (run_live)
- **Protocol**: WebSocket upgrade from HTTP GET
- **Query params**: `app_name`, `user_id`, `session_id`
- **Frame size limit**: 10MB
- **Idle timeout**: 5 minutes
- **Connection limits**: 10 per user, 100 per app
- **Message format**: JSON `LiveRequest` / `Event` objects

### Server-Sent Events (run SSE)
- **Protocol**: SSE over HTTP POST
- **Content-type**: `text/event-stream`
- **Streaming mode**: `RunConfig$StreamingMode/SSE`

## Observability

### OpenTelemetry
- **SDK**: `io.opentelemetry/opentelemetry-sdk`
- **Exporter**: OTLP (configurable endpoint)
- **Span context**: Session ID and Event ID attached to spans
- **Location**: `dev/src/main/clojure/io/kosong/adk/web/telemetry.clj`
- **Note**: Global OpenTelemetry instance reset on every startup (see CONCERNS.md)

## Authentication

- No built-in auth middleware in the web layer
- Google credentials: Application Default Credentials or `GOOGLE_APPLICATION_CREDENTIALS` env var
- Agent-level auth delegated to LLM provider SDKs

## External Tool Integrations (Agent-Defined)

Tools are defined as Clojure functions and can call any external service:
- HTTP clients (via standard Java HTTP or clj-http)
- Databases (via JDBC or client libraries)
- Any external API accessible from JVM

No built-in connectors — tool functions are user-defined.
