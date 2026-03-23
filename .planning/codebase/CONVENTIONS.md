# CONVENTIONS.md — Code Conventions

## Language & Style

- **Clojure 1.12.3** — idiomatic functional style
- **Thread-first** (`->`) used throughout for builder chains and data transformations
- **Keyword arguments** for all public builder functions (`llm-agent`, `loop-agent`, etc.)
- **`defn-`** for private functions; `defn` for public API
- **Docstrings** on all public functions
- **Type hints** throughout for Java interop (`^String`, `^Builder`, `^LlmAgent`, etc.)

## Naming

| Thing | Convention | Example |
|-------|-----------|---------|
| Namespaces | `io.kosong.adk.*` reverse-domain | `io.kosong.adk.core` |
| Files | `snake_case.clj` | `agent_registry.clj` |
| Functions | `kebab-case` | `run-async`, `into-content` |
| Protocols | `PascalCase` with semantic prefix | `IntoContent`, `IntoAgent` |
| Java interop protocols | `Into*` prefix | `IntoContent`, `IntoPart` |
| Java compat protocols | `Datafiable` | (clojure.core.protocols) |
| Constants/atoms | `kebab-case` with `!` suffix for mutating fns | `load-agent-registry!` |
| Test vars | `deftest` with descriptive name | |

## Protocol Pattern (Core Convention)

All type conversions follow the same bidirectional protocol pattern:

```clojure
;; Direction 1: Java → Clojure (extend Datafiable)
(extend-protocol clojure.core.protocols/Datafiable
  SomeJavaClass
  (datafy [^SomeJavaClass x]
    {:field1 (.field1 x)
     :field2 (.field2 x)}))

;; Direction 2: Clojure → Java (extend Into* protocol)
(extend-protocol IntoSomething
  clojure.lang.IPersistentMap
  (into-something [m]
    (-> (SomeJavaClass/builder)
        (.field1 (:field1 m))
        (.build)))

  String  ; Convenience extension for simple cases
  (into-something [s]
    (into-something {:field1 s})))
```

## Java Optional / Collection Handling

Use `optional-datafy-assoc` from `utils.clj` for Java Optional, List, Set, Map fields:

```clojure
(-> {}
    (optional-datafy-assoc :field (.getField obj))  ; handles Optional
    (optional-datafy-assoc :items (.getItems obj)))  ; handles List
```

## Agent Builder Pattern

Public builder functions accept keyword args and return Java objects:

```clojure
(llm-agent
  :name "my-agent"
  :model "gemini-2.0-flash"
  :instruction "You are helpful"
  :tools [my-tool-fn]
  :sub-agents [sub-agent])
```

Internally, builders use Java builder chains:

```clojure
(-> (LlmAgent/builder)
    (.name name)
    (.model model)
    (.build))
```

## Tool Definition Convention

```clojure
(defn my-tool
  "Docstring becomes tool description"
  [^{:schema {:type "STRING"}} query  ; type metadata → JSON schema
   tool-context]                       ; auto-injected execution context
  {:result "..."})  ; map return → structured output
```

Key rules:
1. Docstring required (becomes tool description)
2. `^{:schema {:type "..."}}` on parameters for schema generation
3. `tool-context` parameter name triggers auto-injection
4. Return map for structured output; any other value wrapped as `{"result": value}`
5. Return nil → empty response

## Callback Convention

Callbacks receive a context map and return either nil (no effect) or a modified value:

```clojure
;; Before-model callback: return nil to proceed, or {:content ...} to override
(defn my-before-model [callback-context]
  nil)  ; proceed normally

;; After-agent callback: suppress output
(defn suppress-output [callback-context]
  {:role "model" :parts []})  ; override with empty
```

## Error Handling

- **`ex-info`** with context map for library errors
- Tool execution errors: logged via `clojure.tools.logging`, not propagated to caller (silent)
- WebSocket/SSE errors: logged; connection closed on unrecoverable errors
- No global try/catch; errors bubble to component boundaries

## Logging

```clojure
(require '[clojure.tools.logging :as log])

(log/info "message")
(log/error e "message with exception")
(log/debug "debug info")
```

## State Management

- **Session state**: `ConcurrentHashMap` (mutable); use `:output-key` on agents to write
- **Agent registry**: `atom` containing `{name → agent}` map
- **Component state**: Integrant refs (start/stop lifecycle)
- Avoid `def` for mutable state; prefer atoms or Integrant components

## Imports / Requires

```clojure
(ns io.kosong.adk.core
  (:require
    [io.kosong.adk.protocols :as protocols]
    [clojure.core.protocols :refer [Datafiable datafy]])
  (:import
    [com.google.adk.agents LlmAgent]))
```

- Group `:require` alphabetically by namespace
- Group `:import` by Java package
- Prefer aliased requires over `:refer :all`
- Use `:reload` when requiring in REPL for development
