# Phase 1: Ship It - Research

**Researched:** 2026-03-22
**Domain:** Clojure build tooling (tools.build), cognitect test-runner, clojure.test, deps.edn alias configuration
**Confidence:** HIGH

---

<phase_requirements>
## Phase Requirements

| ID | Description | Research Support |
|----|-------------|------------------|
| GEN-01 | `generated_types.clj` committed to repository | File exists at `core/src/main/clojure/io/kosong/adk/generated_types.clj` (16,235 lines, untracked); requires `git add` and commit |
| GEN-02 | `generate-types` docstring explains when to re-run | Current docstring exists but omits the version-change trigger; update the `build.clj` docstring |
| GEN-03 | Generator produces deterministic (idempotent) output | `generate-source-file` sorts classes by name; verify by diffing two runs |
| TEST-01 | `autovalue_test.clj` uses `deftest` wrappers | Currently uses bare `testing`/`is` at top level — not discoverable; wrap in `deftest` |
| TEST-02 | `:test` alias includes a test runner (`clojure -X:test`) | Current `:test` alias has only `:extra-paths`; needs `cognitect test-runner` dep + `:exec-fn` |
| TEST-03 | Round-trip tests pass | Tests already cover `datafy` and `make-object` for `GenerateContentConfig`; require passing |
| BUILD-01 | `jar` task packages `generated_types.clj` from source without silent regeneration | `jar` calls `b/copy-dir` on `src/main/clojure` which already includes the file; no regeneration inside `jar` |
| BUILD-02 | End-to-end build verified: `generate-types` then `jar` succeeds | Verify after all code changes |
</phase_requirements>

---

## Summary

Phase 1 is almost entirely "wire up existing work." The generator (`io.kosong.autovalue/generate-source-file`), the round-trip logic, and the tests are all written. The work is four distinct actions:

1. Wrap the two top-level `testing` blocks in `autovalue_test.clj` inside `deftest` forms so the cognitect test-runner can discover them.
2. Add the cognitect test-runner dependency and `:exec-fn` to the existing `:test` alias in `core/deps.edn`, keeping the `:extra-paths` that is already there.
3. Update the `generate-types` docstring in `build.clj` to state that the task must be re-run when the `google-genai` dependency version changes.
4. Track `generated_types.clj` in git (`git add` then commit).

No new functionality needs to be built. The `jar` task already copies `src/main/clojure` into the JAR; `generated_types.clj` will be included automatically once committed. No silent regeneration exists inside `jar`.

**Primary recommendation:** Make the four targeted edits, run the test suite, verify idempotency with two `generate-types` runs, confirm the JAR build, then commit.

---

## Current State Audit

### What Already Works (do not touch)

| Item | Location | State |
|------|----------|-------|
| `generate-source-file` function | `core/src/main/clojure/io/kosong/autovalue.clj` | Complete; sorts classes by `.getName` for determinism |
| `generate-types` build task | `core/build.clj` | Wired; runs `compile-java` then spawns subprocess with `:generate` basis |
| `generated_types.clj` content | `core/src/main/clojure/io/kosong/adk/generated_types.clj` | 16,235-line file exists on disk; just not tracked in git |
| `:generate` alias | `core/deps.edn` | Present in working tree; adds `org.reflections/reflections 0.10.2` |
| Round-trip test data | `autovalue_test.clj` | Complete test data for `GenerateContentConfig`; re-uses `io.kosong.adk.generated-types` |
| `jar` task copy-dir step | `core/build.clj` | Copies `src/main/clojure` verbatim; no regeneration |

### What Is Broken / Missing

| Item | Current State | Gap |
|------|--------------|-----|
| `autovalue_test.clj` — test wrapper | Bare `(testing ...)` at top level | Must be wrapped in `(deftest ...)` for runner discovery |
| `:test` alias | Only `{:extra-paths ["src/test/clojure"]}` | Missing test-runner dep and `:exec-fn` |
| `generate-types` docstring | "Generate static Clojure source... Run with: clojure -T:build generate-types" | Missing: "Re-run when google-genai dependency version changes" |
| `generated_types.clj` git status | Untracked | Must `git add` and commit |

---

## Standard Stack

### Core
| Library | Version | Purpose | Why Standard |
|---------|---------|---------|--------------|
| io.github.cognitect-labs/test-runner | v0.5.1 (git/tag) | Discovers and runs `deftest` forms via `clojure -X:test` | Project requirement (ROADMAP.md, TEST-02); community standard for deps.edn projects |
| org.clojure/clojure | 1.12.3 | Already in deps | No change |
| io.github.clojure/tools.build | 0.10.10 | Build task execution | Already in `:build` alias |

### Supporting
| Library | Version | Purpose | When to Use |
|---------|---------|---------|-------------|
| org.reflections/reflections | 0.10.2 | Scans `com.google.genai.types` for `@AutoValue` annotated types | Only needed during `generate-types` via `:generate` alias; already present |

**Installation — only the test-runner is new:**
```bash
# No install needed; added to deps.edn :test alias directly:
io.github.cognitect-labs/test-runner {:git/tag "v0.5.1" :git/sha "dfb30dd"}
```

---

## Architecture Patterns

### Pattern 1: cognitect test-runner `:test` alias

**What:** The runner is a git-dep added to the `:test` alias alongside the existing `:extra-paths`. The `:exec-fn` key enables `clojure -X:test` invocation.

**When to use:** Any deps.edn project using `clojure.test`; standard choice named in ROADMAP.md.

**Example (verified against official README):**
```clojure
;; In core/deps.edn :aliases map — merge with existing :test entry
:test
{:extra-paths ["src/test/clojure"]
 :extra-deps  {io.github.cognitect-labs/test-runner
               {:git/tag "v0.5.1" :git/sha "dfb30dd"}}
 :exec-fn     cognitect.test-runner.api/test
 :exec-args   {:dirs ["src/test/clojure"]}}
```

The runner defaults to discovering namespaces matching `.*-test$` inside the configured dirs. No additional exec-args are required because `io.kosong.autovalue-test` ends in `-test`.

### Pattern 2: `deftest` wrapper for top-level test expressions

**What:** Standard `clojure.test` form that names a group of assertions as a single discoverable test.

**When to use:** Any time assertions appear at top level; required for runner discovery (TEST-01).

**Example:**
```clojure
;; Before (not discoverable):
(testing "autovalue to map"
  (is (= ...)))

;; After (discoverable):
(deftest autovalue-to-map-test
  (testing "autovalue to map"
    (is (= ...))))
```

### Pattern 3: Docstring that captures operational context

**What:** The `generate-types` function docstring must include the re-run trigger (GEN-02).

**Example (updated docstring):**
```clojure
(defn generate-types
  "Generate static Clojure source for AutoValue types in com.google.genai.types.
  The generated file (generated_types.clj) is committed to the repository.
  Re-run this task when the google-genai dependency version changes in deps.edn.
  Run with: clojure -T:build generate-types"
  [_]
  ...)
```

### Recommended Project Structure

No structural changes — all paths already exist:
```
core/
├── src/main/clojure/io/kosong/adk/generated_types.clj  # track in git
├── src/test/clojure/io/kosong/autovalue_test.clj       # add deftest wrappers
├── deps.edn                                             # extend :test alias
└── build.clj                                           # update docstring
```

### Anti-Patterns to Avoid

- **Adding `:main-opts` to the `:test` alias:** `clojure -X:test` uses `:exec-fn`; `:main-opts ["-m" "cognitect.test-runner"]` is the `-M` invocation style and conflicts with `-X`. Do not use both.
- **Moving `org.reflections` to main `:deps`:** It is intentionally scoped to `:generate` so it is not a runtime dependency. Leave it there.
- **Silent regeneration inside `jar`:** BUILD-01 explicitly forbids it. The `jar` function must not call `generate-types`. Currently it does not; leave it that way.
- **Removing the `:extra-paths` when adding the runner:** The `:test` alias already has `{:extra-paths ["src/test/clojure"]}`. Merge the new keys in; do not replace the map.

---

## Don't Hand-Roll

| Problem | Don't Build | Use Instead | Why |
|---------|-------------|-------------|-----|
| Test discovery | Custom namespace scanner | cognitect test-runner | Handles classpath scanning, `deftest` discovery, reporting, exit codes — all edge cases handled |
| Test namespace pattern matching | Custom regex | cognitect test-runner `:patterns` option | Already handles `.*-test$` by default |

**Key insight:** The only missing piece for a working test suite is two configuration lines in `deps.edn` and wrapping two `testing` forms in `deftest`. Nothing new needs to be written.

---

## Common Pitfalls

### Pitfall 1: Top-level `testing` not discovered by runner

**What goes wrong:** `clojure -X:test` reports "0 tests, 0 assertions" even though tests exist.
**Why it happens:** `testing` is not a test; it is a context wrapper. Test runners discover `deftest` vars by reflecting on loaded namespaces. Bare `testing` calls are executed at namespace load time (side effects) but not registered as tests.
**How to avoid:** Always wrap assertions in `deftest`. The `testing` macro can remain inside `deftest` for sub-context labels.
**Warning signs:** Runner output says "Ran 0 tests, 0 assertions, 0 failures, 0 errors."

### Pitfall 2: `:exec-fn` missing from `:test` alias

**What goes wrong:** `clojure -X:test` throws "No function found for :exec-fn" or "No exec-fn specified."
**Why it happens:** `-X` requires `:exec-fn` in the alias (or on the command line). `:extra-paths` alone is not enough.
**How to avoid:** Always pair `extra-deps` (test-runner) with `:exec-fn cognitect.test-runner.api/test`.
**Warning signs:** Error at startup before any test code runs.

### Pitfall 3: `generated_types.clj` namespace declaration uses hyphen vs underscore

**What goes wrong:** The file is at `io/kosong/adk/generated_types.clj` but the `ns` form says `io.kosong.adk.generated-types` (hyphen). Clojure maps hyphens to underscores in filesystem paths. This is correct convention but easy to get confused.
**How to avoid:** The current file already has `(ns io.kosong.adk.generated-types ...)` with a hyphen. Leave it. The `require` in `autovalue_test.clj` uses `[io.kosong.adk.generated-types]` which is correct.
**Warning signs:** `FileNotFoundException: Could not locate io/kosong/adk/generated_types__init.class or io/kosong/adk/generated_types.clj on classpath.`

### Pitfall 4: Determinism verification requires a clean target

**What goes wrong:** Running `generate-types` twice without cleaning `target/` produces the same output file trivially (no classes recompiled). This can mask a real non-determinism bug.
**Why it happens:** `compile-java` writes to `target/classes`; if classes already exist, behavior may differ.
**How to avoid:** For the idempotency check, run `clojure -T:build generate-types` twice in succession from the same `core/` directory; compare the file with `diff`. A full `clean` is not required between runs to verify idempotency of the source generator.
**Warning signs:** Files differ between runs (would indicate non-determinism in type discovery or code emission).

### Pitfall 5: `jar` build requires `target/classes` from `compile-java`

**What goes wrong:** `clojure -T:build jar` fails or produces an incomplete JAR if `compile-java` has not been run.
**Why it happens:** The `jar` function calls `compile-java nil` at the start, so this is self-healing. But if `target/classes` is absent at the start of `b/copy-dir`, the copy may fail.
**How to avoid:** The existing `jar` implementation calls `compile-java` first. No change needed. For BUILD-02 verification, run `generate-types` first, then `jar`.
**Warning signs:** ClassNotFoundException at runtime for `ClojureFunctionTool` or `ClojureAgent`.

---

## Code Examples

### Minimal working `:test` alias (verified against cognitect test-runner README)
```clojure
;; Source: https://github.com/cognitect-labs/test-runner
:test
{:extra-paths ["src/test/clojure"]
 :extra-deps  {io.github.cognitect-labs/test-runner
               {:git/tag "v0.5.1" :git/sha "dfb30dd"}}
 :exec-fn     cognitect.test-runner.api/test
 :exec-args   {:dirs ["src/test/clojure"]}}
```

### `deftest` wrapping pattern
```clojure
;; Source: clojure.test standard library documentation
(ns io.kosong.autovalue-test
  (:require [io.kosong.adk.generated-types]
            [io.kosong.java]
            [clojure.test :refer [deftest testing is]]))

(deftest autovalue-to-map-test
  (testing "autovalue to map"
    (is (= (clojure.datafy/datafy generate-content-config-obj)
           generate-content-config-data))))

(deftest map-to-autovalue-test
  (testing "map to autovalue"
    (let [obj (io.kosong.java/make-object com.google.genai.types.GenerateContentConfig
                                          generate-content-config-data)]
      (is (= obj generate-content-config-obj)))))
```

### Idempotency verification commands
```bash
cd core
clojure -T:build generate-types
cp src/main/clojure/io/kosong/adk/generated_types.clj /tmp/gen1.clj
clojure -T:build generate-types
diff /tmp/gen1.clj src/main/clojure/io/kosong/adk/generated_types.clj
# Expected: no output (empty diff)
```

### git tracking command
```bash
cd /path/to/adk-clojure
git add core/src/main/clojure/io/kosong/adk/generated_types.clj
git commit -m "feat: commit generated_types.clj (GEN-01)"
```

---

## State of the Art

| Old Approach | Current Approach | When Changed | Impact |
|--------------|------------------|--------------|--------|
| `:main-opts ["-m" "cognitect.test-runner"]` | `:exec-fn cognitect.test-runner.api/test` | cognitect test-runner v0.5.x | `-X` is the modern invocation; `-M` still works but `:exec-fn` is idiomatic |
| `org.reflections` in main `:deps` | `org.reflections` in `:generate` alias only | Current working tree (already changed) | Keeps reflection off the runtime classpath |

---

## Open Questions

1. **Should `:exec-args {:dirs [...]}` be explicit or omitted?**
   - What we know: The runner defaults to discovering dirs from `:extra-paths`. With `extra-paths ["src/test/clojure"]`, it should find tests automatically without explicit `:exec-args`.
   - What's unclear: The cognitect runner documentation says defaults are `["test"]` — not the `:extra-paths` value. This may mean tests are not found without explicit `:exec-args {:dirs ["src/test/clojure"]}`.
   - Recommendation: Include `:exec-args {:dirs ["src/test/clojure"]}` explicitly to avoid ambiguity. Verify by running `clojure -X:test` after the change.

2. **`deftest` naming convention for the two tests**
   - What we know: The two test blocks are "autovalue to map" and "map to autovalue".
   - What's unclear: Whether to use `autovalue-to-map-test` / `map-to-autovalue-test` or `round-trip-test` wrapping both.
   - Recommendation: Two separate `deftest` forms (`autovalue-to-map-test` and `map-to-autovalue-test`) — clearer failure messages and matches TEST-03 "round-trip" framing.

---

## Sources

### Primary (HIGH confidence)
- Official cognitect test-runner README — https://github.com/cognitect-labs/test-runner/blob/master/readme.md — alias structure, `:exec-fn` key, version v0.5.1
- `core/build.clj` (working tree) — direct inspection of `generate-types` and `jar` tasks
- `core/deps.edn` (working tree and HEAD) — direct inspection of `:test` and `:generate` aliases
- `core/src/test/clojure/io/kosong/autovalue_test.clj` (working tree) — direct inspection of test structure
- `core/src/main/clojure/io/kosong/autovalue.clj` — direct inspection of `generate-source-file` determinism (sort-by)
- `core/src/main/clojure/io/kosong/adk/generated_types.clj` — confirmed present on disk, untracked by git

### Secondary (MEDIUM confidence)
- WebSearch result cross-referencing cognitect test-runner configuration against official README

---

## Metadata

**Confidence breakdown:**
- Standard stack: HIGH — versions verified from official README; existing deps verified from working-tree files
- Architecture: HIGH — based on direct code inspection; no speculative claims
- Pitfalls: HIGH — pitfalls 1-3 from direct code inspection; pitfalls 4-5 from build.clj logic analysis

**Research date:** 2026-03-22
**Valid until:** 2026-06-22 (cognitect test-runner is stable; clojure.test API does not change)
