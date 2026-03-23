# TESTING.md — Testing Practices

## Current State

**Minimal test coverage.** The codebase has one test file:
- `core/src/test/clojure/io/kosong/autovalue_test.clj`

This tests internal type conversion utilities; there are no integration tests, no agent behavior tests, no web handler tests.

## Test Framework

- **`clojure.test`** — standard library
- Macros: `deftest`, `testing`, `is`
- No advanced assertion libraries (e.g., Midje, Expectations)

## Running Tests

```bash
cd core
clojure -T:test exec
```

Configured via `cognitect-labs/test-runner` in `core/deps.edn`:

```clojure
{:aliases
 {:test {:deps {io.github.cognitect-labs/test-runner {...}}
         :exec-fn cognitect.test-runner.api/test
         :exec-args {:dirs ["src/test/clojure"]}}}}
```

## Test File Structure

```clojure
(ns io.kosong.autovalue-test
  (:require [clojure.test :refer [deftest testing is]]))

(deftest my-test
  (testing "description"
    (is (= expected actual))))
```

Files follow `*_test.clj` naming convention, mirroring source namespace structure.

## Coverage Gaps

### No Tests For:
- `core.clj` public API (agent builders, run functions)
- `types.clj` type conversions (Datafiable, Into* protocols)
- `protocols.clj` protocol dispatch
- `agents.clj` callback implementations
- `tools.clj` Var→Tool conversion
- `models.clj` LLM request/response handling
- `events.clj` event conversions
- `sessions.clj` session wrappers

### Web Layer (no tests):
- HTTP handlers (`handlers.clj`)
- WebSocket lifecycle
- SSE streaming
- Agent registry (`agent_registry.clj`)
- Route definitions

### Missing Test Categories:
- Unit tests for protocol conversions
- Integration tests hitting real (or mock) ADK sessions
- WebSocket lifecycle tests
- Tool error path tests
- Async backpressure tests
- Agent composition / sub-agent delegation tests

## Mocking

No mocking framework in use. Testing against in-memory implementations:
- `InMemorySessionService` for session state
- `InMemoryArtifactService` for artifacts

For LLM calls in tests: no mock LLM; tests would require real API credentials or a local model.

## Recommended Test Approach

Given the architecture, tests should focus on:

1. **Protocol conversion round-trips**: `(= original (datafy (into-x original)))`
2. **Tool metadata extraction**: verify schema generation from var metadata
3. **Agent context threading**: verify state propagation
4. **Web handler behavior**: use Pedestal test utilities (`io.pedestal.test`)

Example pattern for protocol tests:
```clojure
(deftest content-round-trip
  (testing "string converts to Content and back"
    (let [original "Hello"
          java-obj (into-content original)
          clojure-map (datafy java-obj)]
      (is (= "user" (:role clojure-map)))
      (is (= "Hello" (get-in clojure-map [:parts 0 :text]))))))
```
