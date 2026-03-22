# STRUCTURE.md — Directory Layout and Organization

## Top-Level Layout

```
adk-clojure/
├── core/               # Core library: Clojure ADK wrapper + Java bridge classes
├── dev/                # Dev/web library: HTTP server, agent registry, telemetry
├── dev-resources/      # REPL development environment and system config
├── examples/           # Standalone example agents
│   ├── chatbot/
│   ├── blog_writer/
│   ├── academic_research/
│   ├── live-chatbot/
│   └── multi_tool/
├── experimental/       # Experimental/WIP features
├── .planning/          # GSD planning documents (not shipped)
├── CLAUDE.md           # Claude Code project instructions
└── README.md
```

## Core Library (`core/`)

```
core/
├── build.clj                          # tools.build build script (jar, clean, compile-java)
├── deps.edn                           # Dependencies: google-adk, core.async, cheshire, etc.
└── src/
    ├── main/
    │   ├── clojure/io/kosong/
    │   │   ├── adk/
    │   │   │   ├── core.clj           # Main public API: agent builders, run functions, context
    │   │   │   ├── protocols.clj      # ~30 Into*/Datafiable conversion protocols
    │   │   │   ├── types.clj          # Datafiable + Into* implementations for GenAI types
    │   │   │   ├── agents.clj         # Agent callback impls, RunConfig builders
    │   │   │   ├── tools.clj          # Var→Tool, Agent→Tool protocol impls
    │   │   │   ├── models.clj         # LLM registry, request/response conversions
    │   │   │   ├── events.clj         # Event and EventActions conversions
    │   │   │   ├── sessions.clj       # Session service wrappers
    │   │   │   ├── artifacts.clj      # Artifact service wrappers
    │   │   │   ├── memory.clj         # Memory service wrappers
    │   │   │   ├── runner.clj         # Agent execution runner
    │   │   │   ├── utils.clj          # optional-datafy-assoc, Java Optional handling
    │   │   │   └── generated_types.clj  # Auto-generated datafy/make-object multimethods (16k+ lines)
    │   │   ├── autovalue.clj          # AutoValue reflection utilities
    │   │   └── java.clj               # Java interop helpers
    │   └── java/io/kosong/adk/
    │       ├── agents/
    │       │   └── ClojureAgent.java  # Java bridge: Clojure functions as ADK agents
    │       └── tools/
    │           └── ClojureFunctionTool.java  # Java bridge: Clojure vars as ADK tools
    └── test/
        └── clojure/io/kosong/
            └── autovalue_test.clj     # Only test file: AutoValue round-trip tests
```

## Dev Library (`dev/`)

```
dev/
├── build.clj                          # tools.build script (no Java compilation)
├── deps.edn                           # Dependencies: pedestal, reitit, integrant, otel
└── src/main/clojure/io/kosong/adk/
    ├── web.clj                        # Integrant system config and lifecycle entry point
    └── web/
        ├── handlers.clj               # HTTP request handlers (SSE, WebSocket, sessions)
        ├── routes.clj                 # Reitit route definitions
        ├── agent_registry.clj         # Agent discovery and atom-based registration
        ├── agent_graph.clj            # Agent graph visualization
        ├── http_server.clj            # Pedestal server setup
        ├── telemetry.clj              # OpenTelemetry configuration
        └── main.clj                   # CLI entry point for standalone server
```

Note: `dev/src/main/clojure/io/pedestal/http.clj` is a monkey-patch shim for Pedestal WebSocket support.

## Dev Resources (`dev-resources/`)

```
dev-resources/
├── deps.edn            # REPL dev dependencies (includes core + dev + examples)
├── config.edn          # Integrant system configuration
├── secrets.edn         # API keys and secrets (gitignored)
├── system.clj          # REPL system management (start/stop/restart)
├── user.clj            # REPL user namespace (convenience functions)
└── samples.clj         # Sample agent invocations for REPL testing
```

## Examples (`examples/`)

Each example is a self-contained Clojure project:

```
examples/
├── chatbot/
│   ├── deps.edn        # Depends on core + dev
│   └── src/agents/chatbot.clj         # Simple LLM chatbot agent
├── blog_writer/
│   ├── deps.edn
│   ├── src/agents/blogger.clj         # Multi-agent blog writing pipeline
│   └── src/templates/interactive_blogger.md
├── academic_research/
│   ├── deps.edn
│   ├── src/agents/academic_research.clj  # Research orchestration agent
│   └── src/templates/                    # Prompt templates
├── live-chatbot/
│   ├── deps.edn
│   └── src/agents/live_chatbot.clj    # WebSocket live streaming chatbot
└── multi_tool/
    ├── deps.edn
    └── src/agents/multi_tool.clj      # Agent with multiple tool functions
```

## Experimental (`experimental/`)

```
experimental/
├── build.clj
├── deps.edn
└── src/main/clojure/io/kosong/adk/experimental.clj
```

Work-in-progress features not yet merged to core.

## Naming Conventions

| Entity | Convention | Example |
|--------|-----------|---------|
| Namespaces | dot-separated, mirrors path | `io.kosong.adk.core` |
| Files | kebab-case | `agent_registry.clj` |
| Functions | kebab-case | `run-async`, `llm-agent` |
| Protocols | PascalCase with prefix | `IntoContent`, `IntoAgent` |
| Java classes | PascalCase | `ClojureAgent`, `ClojureFunctionTool` |
| Config keys | kebab-case keywords | `:app-name`, `:user-id` |
| Integrant keys | namespaced keywords | `:system/session-service` |

## Key File Locations Quick Reference

| Purpose | File |
|---------|------|
| Main public API | `core/src/main/clojure/io/kosong/adk/core.clj` |
| Conversion protocols | `core/src/main/clojure/io/kosong/adk/protocols.clj` |
| HTTP handlers | `dev/src/main/clojure/io/kosong/adk/web/handlers.clj` |
| Route definitions | `dev/src/main/clojure/io/kosong/adk/web/routes.clj` |
| System config | `dev/src/main/clojure/io/kosong/adk/web.clj` |
| REPL entry point | `dev-resources/user.clj` |
| Core build | `core/build.clj` |
| Core dependencies | `core/deps.edn` |
