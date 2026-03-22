# TESTING.md — Test Structure and Practices

## Framework

- **`clojure.test`** — standard Clojure test framework
- No additional test libraries detected (no Midje, expectations, etc.)
- No test runner configuration beyond standard `clojure -T:test` tasks

## Test Coverage

**Extremely minimal** — only 1 test file found in the entire project:

- `core/src/test/clojure/io/kosong/autovalue_test.clj`

### What is Tested

- AutoValue type conversion: Java → Clojure via `datafy`
- AutoValue type conversion: Clojure → Java via `make-object`
- Basic round-trip correctness for generated AutoValue types

### What is NOT Tested

- Agent creation and composition (`llm-agent`, `loop-agent`, etc.)
- Tool registration and invocation (`ClojureFunctionTool`)
- HTTP endpoints (session creation, run/sse, run_live)
- WebSocket live streaming
- Event streaming and channel behavior
- Callback execution (before/after agent/model/tool)
- Session state management
- Protocol conversions (types.clj, agents.clj, events.clj)
- Error scenarios and edge cases

## Test Structure

```
core/
  src/
    test/
      clojure/
        io/kosong/
          autovalue_test.clj   ← Only test file
```

## Test Patterns

```clojure
(ns io.kosong.autovalue-test
  (:require [clojure.test :refer [deftest is testing]]))

(deftest some-test
  (testing "description"
    (is (= expected actual))))
```

- Direct object creation (no mocking framework)
- No test fixtures or setup/teardown
- No property-based testing

## CI/CD

- **No CI pipeline detected** — no `.github/workflows/`, no `Jenkinsfile`, no CircleCI config
- Tests must be run manually:
  ```bash
  cd core
  clojure -T:build test
  ```

## Mocking

- No mocking framework in use
- Tests work directly with real objects
- No test doubles, stubs, or spies

## Recommendations

Given the critical gaps in test coverage, the following test categories are most urgently needed:
1. Integration tests for agent execution (using real ADK in-memory services)
2. HTTP handler tests using `ring.mock.request`
3. Protocol conversion unit tests for `types.clj`
4. Error scenario tests for streaming channels
5. WebSocket endpoint tests
