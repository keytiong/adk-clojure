# Technology Stack

**Analysis Date:** 2026-03-22

## Languages

**Primary:**
- Clojure 1.12.3 - Core library (adk-clojure) and all examples
- Java 17+ - Java compilation target via `--release 17` in build.clj

**Secondary:**
- Java (source) - Custom bridge classes (`ClojureFunctionTool.java`, `ClojureAgent.java`)

## Runtime

**Environment:**
- Java 17+ (required)
- Clojure CLI tools (via deps.edn)

**Package Manager:**
- Clojure deps.edn dependency management
- Maven Central for transitive dependencies (via clojure tools.build)
- Lockfile: `deps.lock` (not visible in repository, deps.edn primary)

## Frameworks

**Core Agent Framework:**
- Google ADK (Agent Development Kit) 0.5.0 - Main Java library for agent framework
  - Provides: LlmAgent, LoopAgent, SequentialAgent, BaseAgent, Tool, Session, RunConfig

**Web Framework (dev module):**
- Pedestal 0.8.0 - HTTP server foundation
- Pedestal HTTP Kit 0.8.0 - WebSocket and HTTP support
- Reitit 0.9.1 - Routing and HTTP handler configuration
  - Includes coercion (malli), interceptors, parameter handling
- Integrant 1.0.1 - Component lifecycle management
- http-kit (via Pedestal) - Async HTTP/WebSocket server

**Testing:**
- clojure/tools.build 0.10.10 - Build and test coordination

**Build/Dev:**
- clojure/tools.build 0.10.10 - JAR building, Java compilation, pom.xml generation
- slipset/deps-deploy 0.2.2 - Maven deployment (publish to Clojars)
- org.reflections 0.10.2 - Reflection-based code generation (in `:generate` alias)

## Key Dependencies

**Critical (Core):**
- org.clojure/core.async 1.9.808-alpha1 - Async channels for event streaming (core.async integration)
- org.clojure/tools.logging 1.3.0 - Logging abstraction (used by ClojureFunctionTool, core agents)
- com.google.adk/google-adk 0.5.0 - Java Agent Development Kit
- com.cnuernber/charred 1.037 - Fast JSON parsing (handlers.clj uses write-json-str, read-json)
- camel-snake-kebab/camel-snake-kebab 0.4.3 - Kebab/camelCase conversion (web layer JSON serialization)

**LLM Integration (Examples):**
- com.google.adk/google-adk-langchain4j 0.5.0 - LangChain4j integration for ADK (chatbot example)
- dev.langchain4j/langchain4j-open-ai 1.4.0 - OpenAI-compatible LLM provider via LangChain4j

**Template Engine (Blog Writer Example):**
- selmer/selmer 1.12.65 - Template rendering (blog-writer example uses for Markdown/text generation)

**HTTP/Web (dev module):**
- io.pedestal/pedestal.service 0.8.0 - HTTP service utilities
- metosin/reitit-core 0.9.1 - Routing core
- metosin/reitit-dev 0.9.1 - Development tools
- metosin/reitit-malli 0.9.1 - Malli data validation coercion
- metosin/reitit-http 0.9.1 - HTTP routing
- metosin/reitit-interceptors 0.9.1 - Pedestal interceptor support
- metosin/reitit-pedestal 0.9.1 - Pedestal integration
- guru.nidi/graphviz-java 0.18.1 - Graph visualization (agent hierarchy graphs)
- muuntaja (via reitit) - Format negotiation and encoding
- malli (via reitit-malli) - Data schema validation

**Telemetry:**
- io.opentelemetry.sdk:* (versions via google-adk dependency tree) - OpenTelemetry tracing
  - Used in `io.kosong.adk.web.telemetry` for span export and trace storage

**Internal Utilities:**
- clojure.datafy - Built-in Clojure protocol for converting Java objects to Clojure maps
- clojure.walk - Tree walking utilities (stringify-keys conversion)

## Configuration

**Build Configuration:**
- `core/build.clj` - Java compilation (`--release 17`), JAR building, pom.xml generation
- `dev/build.clj` - JAR building for dev module (no Java compilation)
- Both use deps.edn for dependency resolution

**Dependency Configuration:**
- `core/deps.edn` - Core library with Google ADK 0.5.0, core.async, logging
- `dev/deps.edn` - Web framework with Pedestal, Reitit, Integrant, graphviz-java
- `examples/*/deps.edn` - Example-specific dependencies (e.g., chatbot adds langchain4j)

**Preparation Hooks:**
- `:deps/prep-lib` hook in core/deps.edn - Automatically runs Java compilation before JAR build
  - Enables seamless `clojure -T:build jar` from core/

**Java Compilation:**
- Javac target: `--release 17`
- No annotation processors: `-proc:none` flag in build.clj
- Classes compiled to `target/classes/`

## Platform Requirements

**Development:**
- Java 17 or higher
- Clojure CLI tools installed
- Bash shell (for build scripts)
- Git (for version control)

**Runtime:**
- Java 17+ (for running agents and web server)
- Network connectivity (for external LLM APIs - Google Vertex AI, OpenAI-compatible endpoints)
- Optional: Local LLM servers (Ollama, llama.cpp) for offline development

**Deployment:**
- JVM-based server environment supporting Java 17+
- Network access to configured LLM providers
- Environment variables for API credentials (required but not committed)

---

*Stack analysis: 2026-03-22*
