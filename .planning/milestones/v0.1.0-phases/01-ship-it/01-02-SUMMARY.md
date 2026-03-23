---
phase: 01-ship-it
plan: 02
subsystem: core/code-generation
tags: [build, code-generation, idempotency, jar]
dependency_graph:
  requires: ["01-01"]
  provides: ["generated_types.clj committed to repo", "idempotent generation verified"]
  affects: ["core/build pipeline", "runtime type conversions"]
tech_stack:
  added: []
  patterns: ["sort properties by method name for deterministic output"]
key_files:
  created:
    - core/src/main/clojure/io/kosong/adk/generated_types.clj
  modified:
    - core/src/main/clojure/io/kosong/autovalue.clj
decisions:
  - "Sort AutoValue class properties by method name to ensure deterministic code generation"
  - "generated_types.clj committed to git so no runtime reflection dependency needed"
metrics:
  duration: "2min 30sec"
  completed: "2026-03-22"
  tasks_completed: 1
  files_changed: 2
---

# Phase 01 Plan 02: Track Generated Types and Verify Build Pipeline Summary

**One-liner:** Committed generated_types.clj (351 AutoValue types) after fixing non-deterministic property ordering in autovalue.clj, with idempotency verified and end-to-end jar build confirmed.

## Tasks Completed

| Task | Name | Commit | Files |
|------|------|--------|-------|
| 1 | Verify idempotency and track generated_types.clj in git | afdf9e5 | core/src/main/clojure/io/kosong/adk/generated_types.clj, core/src/main/clojure/io/kosong/autovalue.clj |

## Verification Results

- Two consecutive `generate-types` runs produce identical output (GEN-03: PASS)
- `git status` shows `generated_types.clj` as committed new file (GEN-01: PASS)
- `jar` function in build.clj contains only `compile-java`, `write-pom`, `copy-dir`, `jar` — no `generate-types` call (BUILD-01: PASS)
- `clojure -T:build jar` produces `core/target/adk-clojure-0.1.0-SNAPSHOT.jar` without errors (BUILD-02: PASS)
- `clojure -X:test` passes: 2 tests, 2 assertions, 0 failures (regression: PASS)
- First line of generated_types.clj: `(ns io.kosong.adk.generated-types ...` (PASS)

## Deviations from Plan

### Auto-fixed Issues

**1. [Rule 1 - Bug] Fixed non-deterministic property ordering in get-autovalue-properties**
- **Found during:** Task 1, step 2 (idempotency verification)
- **Issue:** `get-autovalue-properties` called `.getMethods` on a Java class, which returns methods in JVM-internal order (non-deterministic across runs). This caused different property ordering between generate-types runs, producing different file content.
- **Fix:** Added `(sort-by #(.getName ^java.lang.reflect.Method %))` after the `filter property-method?` step in `get-autovalue-properties`.
- **Files modified:** `core/src/main/clojure/io/kosong/autovalue.clj`
- **Commit:** afdf9e5

## Decisions Made

1. Sort AutoValue class properties by Java method name alphabetically — ensures deterministic output independent of JVM method enumeration order.
2. Commit `generated_types.clj` with 351 AutoValue types from `com.google.genai.types` package.

## Known Stubs

None — generated_types.clj contains fully functional conversion code for all 351 AutoValue types.

## Self-Check: PASSED
