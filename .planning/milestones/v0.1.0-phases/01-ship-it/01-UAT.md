---
status: testing
phase: 01-ship-it
source: [01-01-SUMMARY.md, 01-02-SUMMARY.md]
started: 2026-03-22T14:10:09Z
updated: 2026-03-22T14:10:09Z
---

## Current Test

number: 1
name: Test runner discovers and runs deftest forms
expected: |
  Run `cd core && clojure -X:test`. The output should show:
  "Ran 2 tests containing 2 assertions. 0 failures, 0 errors."
awaiting: user response

## Tests

### 1. Test runner discovers and runs deftest forms
expected: Run `cd core && clojure -X:test`. The output should show "Ran 2 tests containing 2 assertions. 0 failures, 0 errors."
result: [pending]

### 2. generate-types docstring explains when to re-run
expected: Open `core/build.clj` and find the `generate-types` function. Its docstring should state that `generated_types.clj` is committed to the repository and must be re-run when the `google-genai` dependency version changes.
result: [pending]

### 3. generated_types.clj is tracked by git
expected: Run `git status` from the project root. `core/src/main/clojure/io/kosong/adk/generated_types.clj` should NOT appear as untracked or modified — it should be a clean committed file.
result: [pending]

### 4. generate-types is idempotent
expected: Run `cd core && clojure -T:build generate-types` twice in a row. Running `git diff core/src/main/clojure/io/kosong/adk/generated_types.clj` after the second run should show no changes (empty diff).
result: [pending]

### 5. jar build completes cleanly
expected: Run `cd core && clojure -T:build jar`. It should complete without errors and produce `core/target/adk-clojure-0.1.0-SNAPSHOT.jar`. It should NOT re-run `generate-types` automatically.
result: [pending]

## Summary

total: 5
passed: 0
issues: 0
pending: 5
skipped: 0
blocked: 0

## Gaps

