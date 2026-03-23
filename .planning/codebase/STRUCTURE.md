# STRUCTURE.md — Directory Structure

## Top-Level Layout

```
adk-clojure/
├── core/                    # Core Clojure↔Java interop library
├── dev/                     # Web framework + dev tooling library
├── examples/                # Standalone example agents
│   ├── chatbot/
│   ├── blog_writer/
│   ├── live-chatbot/
│   └── ...
├── experimental/            # In-progress / experimental features
├── dev-resources/           # REPL workspace (not a library)
├── .planning/               # GSD planning artifacts
└── CLAUDE.md
```

## core/ — Main Library

```
core/
├── deps.edn                 # Dependencies + build aliases
├── build.clj                # tools.build config (jar, clean, compile-java)
└── src/
    ├── main/
    │   ├── clojure/io/kosong/adk/
    │   │   ├── core.clj           # PUBLIC API: agent builders, run functions, context
    │   │   ├── protocols.clj      # ~30 conversion protocols (Into*, Datafiable)
    │   │   ├── types.clj          # Protocol implementations for ADK Java types
    │   │   ├── agents.clj         # Agent callback implementations, RunConfig builders
    │   │   ├── tools.clj          # Tool protocol implementations (Var→Tool, Agent→Tool)
    │   │   ├── models.clj         # LlmRegistry, request/response conversions
    │   │   ├── events.clj         # Event and EventActions conversions
    │   │   ├── sessions.clj       # Session service wrappers
    │   │   ├── utils.clj          # optional-datafy-assoc, Java Optional/List/Set/Map helpers
    │   │   └── generated_types.clj # Auto-generated type extensions (tracked in git)
    │   └── java/io/kosong/adk/
    │       ├── agents/
    │       │   └── ClojureAgent.java      # Custom agent bridge
    │       └── tools/
    │           └── ClojureFunctionTool.java # Function-to-tool bridge
    └── test/
        └── clojure/io/kosong/
            └── autovalue_test.clj         # Minimal test coverage
```

## dev/ — Web Framework Library

```
dev/
├── deps.edn
├── build.clj
└── src/main/clojure/io/kosong/adk/web/
    ├── web.clj              # Integrant system config + lifecycle (run, stop!)
    ├── system.clj           # Component definitions + LLM backend registration
    ├── handlers.clj         # HTTP request handlers (SSE, WebSocket, sessions)
    ├── routes.clj           # Reitit route definitions
    ├── agent_registry.clj   # Agent discovery and atom-based registry
    ├── http_server.clj      # Pedestal server setup
    └── telemetry.clj        # OpenTelemetry SDK configuration
```

## examples/ — Example Agents

Each example is a standalone project:

```
examples/{name}/
├── deps.edn                 # Own dependencies (includes core + dev as local deps)
└── src/
    └── agents/
        └── {name}.clj       # Agent definitions + entry point
```

| Example | Description |
|---------|-------------|
| `chatbot/` | Simple conversational agent |
| `blog_writer/` | Multi-agent blog writing pipeline |
| `live-chatbot/` | WebSocket bidirectional live streaming |

## dev-resources/ — REPL Workspace

```
dev-resources/
├── deps.edn                 # Includes core + dev + all examples
└── src/
    └── ...                  # REPL scratch files
```

Start with: `cd dev-resources && clojure -M:dev`

## Key File Locations

| What | Where |
|------|-------|
| Public API | `core/src/main/clojure/io/kosong/adk/core.clj` |
| Protocols | `core/src/main/clojure/io/kosong/adk/protocols.clj` |
| Type conversions | `core/src/main/clojure/io/kosong/adk/types.clj` |
| Java bridge | `core/src/main/java/io/kosong/adk/{agents,tools}/` |
| HTTP handlers | `dev/src/main/clojure/io/kosong/adk/web/handlers.clj` |
| Route definitions | `dev/src/main/clojure/io/kosong/adk/web/routes.clj` |
| System config | `dev/src/main/clojure/io/kosong/adk/web/system.clj` |
| Generated types | `core/src/main/clojure/io/kosong/adk/generated_types.clj` |

## Naming Conventions

- **Namespaces**: `io.kosong.adk.*` — reverse domain, dot-separated
- **Files**: `snake_case.clj` matching namespace segment (e.g., `agent_registry.clj`)
- **Functions**: `kebab-case` (Clojure standard)
- **Private functions**: `defn-` prefix pattern
- **Protocols**: PascalCase with `Into` prefix for Clojure→Java (e.g., `IntoContent`)
- **Java classes**: PascalCase in `io.kosong.adk.{agents,tools}` packages
- **Test files**: `*_test.clj` suffix

## Where to Add New Code

| Adding | Where |
|--------|-------|
| New protocol | `protocols.clj` |
| New type conversion | `types.clj` |
| New agent builder | `core.clj` (public), `agents.clj` (implementation) |
| New HTTP endpoint | `handlers.clj` + `routes.clj` |
| New Integrant component | `system.clj` |
| New example agent | `examples/{name}/` (new standalone project) |
