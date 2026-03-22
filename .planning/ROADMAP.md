# Roadmap: adk-clojure Build-Time GenAI Type Code Generation

**Milestone:** v1.0 — Ship tested, committed code generation for `com.google.genai.types`
**Requirements:** 8 total | 8 mapped | 0 unmapped

## Phase 1: Ship It

**Goal:** The build pipeline is clean, tests pass via a standard runner, and the generated file is committed — everything a contributor needs to verify and regenerate type conversions.

**Covers:** GEN-01, GEN-02, GEN-03, TEST-01, TEST-02, TEST-03, BUILD-01, BUILD-02

**Deliverables:**
- `autovalue_test.clj` tests wrapped in `deftest` so any standard test runner discovers them
- `:test` alias in `core/deps.edn` includes cognitect test-runner; `clojure -X:test` runs the suite
- `generated_types.clj` committed to the repository
- `generate-types` docstring states it must be re-run when `google-genai` dependency version changes
- End-to-end verification: `generate-types` then `jar` both succeed without errors

**UAT:**
- [ ] `clojure -X:test` (from `core/`) runs and reports all tests passing with no failures
- [ ] `git status` shows `generated_types.clj` as a tracked, unmodified file
- [ ] Running `clojure -T:build generate-types` twice produces an identical file (diff is empty)
- [ ] `clojure -T:build jar` completes without errors after `generate-types` has been run
- [ ] `clojure -T:build generate-types --help` (or reading `build.clj`) shows the docstring explains when to re-run

---
*Roadmap created: 2026-03-22*
