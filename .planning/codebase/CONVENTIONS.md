# CONVENTIONS.md — Code Style and Patterns

## Naming Conventions

- **Kebab-case** throughout: functions, variables, local bindings, filenames
- **Namespaces** mirror directory structure: `io.kosong.adk.core` → `core/src/main/clojure/io/kosong/adk/core.clj`
- **Protocol names** use `Into*` prefix for Clojure→Java conversions: `IntoContent`, `IntoAgent`, `IntoTool`
- **Datafiable** protocol used for Java→Clojure conversions (via `clojure.core.protocols/Datafiable`)
- **Private vars** use `defn-` (rare); most functions are public by convention

## Code Style

- No explicit formatting tool configured (no cljfmt, zprint config found)
- Consistent use of `->` and `->>` threading macros for readability
- `let` bindings preferred over deeply nested expressions
- Docstrings present on public API functions; tool functions require them for schema generation

## Protocol-Based Java Interop Pattern

The central pattern in this codebase:

```clojure
;; ~30 protocols defined in protocols.clj
;; Java → Clojure: extend Datafiable
(extend-protocol Datafiable
  SomeJavaClass
  (datafy [^SomeJavaClass x]
    {:field (.getField x)}))

;; Clojure → Java: extend Into* protocol
(extend-protocol IntoSomething
  clojure.lang.IPersistentMap
  (into-something [m]
    (-> (SomeJavaClass/builder)
        (.field (:field m))
        (.build))))
```

## Builder Pattern

Java ADK objects are constructed via builder pattern:
```clojure
(-> (JavaClass/builder)
    (.someField value)
    (.anotherField value)
    (.build))
```

## Type Hints

- Java method calls use type hints to avoid reflection: `^String`, `^Integer`, `^Content`
- Used consistently on protocol method parameters for performance

## Error Handling

- `try/catch` used at boundaries (WebSocket handlers, HTTP handlers)
- RxJava `Maybe` types handled via `.blockingGet` in some synchronous paths
- Errors from async channels propagated as exception objects in event stream
- No global error handler; each component handles its own exceptions

## Logging

- `clojure.tools.logging` used throughout (wraps SLF4J)
- Log levels: `log/info`, `log/warn`, `log/error`, `log/debug`
- Minimal logging — most execution paths are silent

## Async Patterns

- `core.async` channels for event streaming
- `async/go` blocks for non-blocking channel operations
- `async/<!!` for blocking reads in sync contexts
- Channel buffers: sliding (16) for output events, blocking (10) for input requests

## Optional/Null Handling

- `optional-datafy-assoc` utility in `utils.clj` handles Java `Optional`, `List`, `Set`, `Map`
- Returns `nil` (not exception) when Optional is empty
- Clojure `nil` used throughout instead of Java `null`

## Configuration Pattern

- Clojure maps as configuration everywhere
- Keyword keys (`:name`, `:model`, `:instruction`)
- Maps passed to builder functions (e.g., `llm-agent`, `loop-agent`)
- Integrant used in dev/web layer for component lifecycle (`:system/session-service`, etc.)

## Metadata Conventions

- Tool schemas defined via function parameter metadata: `^{:schema {:type "STRING"}}`
- Var metadata used to carry ADK-specific information
- `^:private` used occasionally for implementation details
