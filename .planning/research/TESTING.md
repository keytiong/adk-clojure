# Clojure Test Structure: clojure.test Best Practices

**Context:** Fixing `autovalue_test.clj` — bare `testing` forms not discoverable by test runners.

## 1. Correct deftest / testing Structure

`deftest` defines a named, discoverable test function. `testing` adds a descriptive string to failure
messages but does NOT define a runnable test on its own — it must nest inside `deftest`.

```clojure
(ns io.kosong.autovalue-test
  (:require [clojure.test :refer [deftest testing is]]))

(deftest autovalue-roundtrip
  (testing "autovalue to map"
    (is (= (clojure.datafy/datafy some-obj) expected-map)))

  (testing "map to autovalue"
    (is (= (make-object SomeClass expected-map) some-obj))))
```

Bare `testing` at the top level runs at namespace load time and silently swallows failures (they
print but no test runner sees them). Always wrap in `deftest`.

## 2. Running Tests

The `:test` alias in `core/deps.edn` only adds `src/test/clojure` to the path — it has no runner.
Two options:

**Option A — cognitect test-runner (recommended):**
```clojure
;; Add to deps.edn :aliases :test
:extra-deps {io.github.cognitect-labs/test-runner {:git/tag "v0.5.1" :git/sha "dfb30dd"}}
:exec-fn cognitect.test-runner.api/test
:exec-args {:dirs ["src/test/clojure"]}
```
Run: `clojure -X:test`

**Option B — inline via clojure.test/run-tests:**
```clojure
clojure -M:test -e "(require 'io.kosong.autovalue-test 'clojure.test) (clojure.test/run-tests 'io.kosong.autovalue-test)"
```

## 3. Equality Checks for AutoValue / Google GenAI Types

AutoValue generates `equals()` and `hashCode()` based on all fields — structural equality is
guaranteed by the library. Clojure's `=` delegates to `.equals`, so `(= obj1 obj2)` works
correctly for AutoValue objects as long as all nested fields also implement `equals` properly.

**Gotchas specific to this codebase:**
- Java `Optional` fields: two objects with `Optional.empty()` vs `null` may not be `=`. Verify
  the builder default behavior matches what `datafy` returns before asserting equality.
- `float` vs `double` precision: `(float 0.8)` and `(double 0.8)` are not `=` in Clojure.
  The test already uses `(float 0.8)` on both sides, which is correct.
- `List` fields: AutoValue stores them as `ImmutableList`. Clojure `=` compares sequences by
  value across implementations, so `["User:" "Human:"] = ImmutableList.of(...)` returns `true`.
- `Map` fields: Same — `{"a" "1"} = ImmutableMap.of(...)` works via `.equals`.

**Confidence:** HIGH — AutoValue contract, Clojure `=` semantics, and `clojure.test` structure
are well-documented stable behaviors.
