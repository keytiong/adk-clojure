# External Integrations

**Analysis Date:** 2026-03-22

## APIs & External Services

**LLM Providers:**
- Google Vertex AI / Gemini - Primary LLM integration via Google ADK
  - SDK: com.google.adk/google-adk 0.5.0
  - Auth: Via Google Cloud credentials (GOOGLE_APPLICATION_CREDENTIALS environment variable)
  - Implementation: `com.google.adk.models.LlmRegistry` for model registration

- OpenAI-Compatible APIs - Secondary integration via LangChain4j
  - SDK: dev.langchain4j/langchain4j-open-ai 1.4.0 (chatbot example)
  - Auth: API key via environment or configuration
  - Integration: Registered in `examples/chatbot/src/agents/chatbot.clj` with `register-llm-factory!`
  - Supported: Ollama, llama.cpp, vLLM, or any OpenAI-compatible endpoint
  - Example configs:
    - Ollama: `http://127.0.0.1:11434/v1`
    - llama.cpp: `http://127.0.0.1:12434/engines/llama.cpp/v1`

**Custom LLM Registration Pattern:**
- Uses `io.kosong.adk.models/register-llm-factory!` for custom LLM backends
- Pattern: `register-llm-factory! "model-pattern/*" (fn [model-name] ...)`
- Located in: `core/src/main/clojure/io/kosong/adk/models.clj`
- Example: chatbot example registers `docker/*` and `ollama/*` patterns

## Data Storage

**Sessions:**
- In-Memory Session Service (default) - `com.google.adk.sessions.InMemorySessionService`
  - Implementation: `io.kosong.adk.sessions/in-memory-session-service`
  - Ephemeral storage, resets on application restart

- Vertex AI Session Service - Persistent sessions via Google Cloud
  - Class: `com.google.adk.sessions.VertexAiSessionService`
  - Available as alternative in `core/src/main/clojure/io/kosong/adk/sessions.clj`
  - Auth: Via Google Cloud credentials

- Custom Session Services - Can be plugged in via context
  - Interface: `com.google.adk.sessions.BaseSessionService`
  - Attached via `agent-context :session-service` parameter

**Artifacts:**
- In-Memory Artifact Service (default) - `com.google.adk.artifacts.InMemoryArtifactService`
  - Implementation: `io.kosong.adk.artifacts/in-memory-artifact-service`
  - Ephemeral blob storage for files, images, audio

- Custom Artifact Services - Pluggable interface
  - Interface: `com.google.adk.artifacts.BaseArtifactService`
  - Attached via `agent-context :artifact-service` parameter

**Memory Service:**
- In-Memory Memory Service - `com.google.adk.memory.InMemoryMemoryService`
  - Implementation: `io.kosong.adk.memory/in-memory-memory-service`
  - Used for agent memory state

**Caching:**
- None detected. Session/artifact caching via in-memory or external services as needed.

## Authentication & Identity

**Auth Provider:**
- Google Cloud Authentication - Via Application Default Credentials
  - Used for: Vertex AI models, Vertex AI sessions
  - Env var: `GOOGLE_APPLICATION_CREDENTIALS` (path to service account JSON)
  - Required: Project with ADK API enabled, credentials setup per README

- API Key / Bearer Token - For OpenAI-compatible endpoints
  - Configuration: In `register-llm-factory!` calls
  - Example: chatbot hardcodes empty string for Ollama (local, no auth required)
  - Approach: Passed directly in factory configuration or environment variables

**Session Identity:**
- App-Name + User-ID + Session-ID triple identifies sessions
- Format: User context includes `:app-name`, `:user-id` in agent-context
- Session creation: `create-session service app-name user-id state [session-id]`

## Monitoring & Observability

**Error Tracking:**
- None detected. Errors logged via SLF4J.

**Logs:**
- SLF4J abstraction via org.clojure/tools.logging 1.3.0
- Implementation: Bound to backend of choice (Logback, Log4j, etc.)
- Logged by:
  - ClojureFunctionTool (Java bridge): `LoggerFactory.getLogger(ClojureFunctionTool.class)`
  - Agent execution via clojure.tools.logging

**OpenTelemetry Tracing:**
- Implementation: `io.kosong.adk.web.telemetry` module
- SDK: io.opentelemetry.sdk (version via google-adk dependency)
- Span exporter: Custom implementation in `telemetry.clj` that:
  - Captures spans named `call_llm`, `send_data`, `tool_response_*`
  - Stores traces in-memory (atom-based)
  - Maps traces to session and event IDs
- Access: `/debug/trace/:event-id` and `/debug/trace/session/:session-id` endpoints
- Attributes tracked:
  - `gcp.vertex.agent.event_id` - Event identifier
  - `gcp.vertex.agent.session_id` - Session identifier
  - Standard OpenTelemetry span context

## CI/CD & Deployment

**Hosting:**
- JVM-based hosting (any platform supporting Java 17+)
- Designed for containerization (single JAR, standard Java runtime)

**CI Pipeline:**
- Not detected in repository. No GitHub Actions, CircleCI, or GitLab CI config present.

**Package Registry:**
- Clojars - Maven repository for published artifacts
  - Published via slipset/deps-deploy 0.2.2
  - `:deploy` alias - Remote deployment to Clojars
  - `:install` alias - Local Maven installation
  - Artifact: `io.kosong.adk/adk-clojure` (core), `io.kosong.adk/adk-clojure-dev` (web)

## Environment Configuration

**Required Environment Variables:**
- `GOOGLE_APPLICATION_CREDENTIALS` - Path to Google Cloud service account JSON (for Google Vertex AI)
- LLM API Keys - Specific to registered LLM providers (e.g., OpenAI key if using OpenAI backend)
- Custom LLM endpoints - Base URLs (e.g., Ollama URL) configured in code via `register-llm-factory!`

**Secrets Location:**
- Not in repository (see CLAUDE.md for credentials setup requirements)
- Typically: `~/.config/gcloud/application_default_credentials.json` (Google Cloud SDK)
- Or: `$HOME/.gcp/` directory structure
- Must be provided at runtime, not committed to version control

**Configuration Approaches Observed:**
1. **Example chatbot**: Hardcoded LLM endpoints, env-based API key expectation
2. **Web server**: Configurable via `:port`, `:agent-namespaces` in exec-args
3. **Custom backends**: Register via `register-llm-factory!` at startup time

## Webhooks & Callbacks

**Incoming:**
- WebSocket endpoint `/run_live` - Bidirectional streaming (query params: `app_name`, `user-id`, `session_id`)
- Server-Sent Events `/run_sse` - Unidirectional streaming for agent responses
- HTTP endpoints: Create sessions, list sessions, get graphs

**Outgoing:**
- None detected. Agents are request/response based via HTTP/WebSocket.

**Agent Callbacks:**
- Internal callback mechanism (not external webhooks)
- Types: `before-agent-callback`, `after-agent-callback`, `before-model-callback`, `after-model-callback`, `before-tool-callback`, `after-tool-callback`
- Used for: Lifecycle hooks within agent execution
- Not external webhooks - all internal to agent framework

**Live Streaming Channels:**
- `event-ch` - Receives Event objects from agent (sliding buffer 16, drops oldest if slow consumer)
- `request-ch` - Sends LiveRequest maps to agent (blocking buffer 10, applies backpressure)
- LiveRequest format:
  - `{:content {...}}` - Turn-by-turn content
  - `{:blob {:mime-type "..." :data "..."}}` - Realtime audio/video
  - `{:close true}` - Close connection

**WebSocket Limits:**
- 10 connections max per user
- 100 connections max per app
- 10MB max frame size
- 5 minute idle timeout

---

*Integration audit: 2026-03-22*
