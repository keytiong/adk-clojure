# adk-clojure: Build-Time GenAI Type Code Generation

## What This Is

A build-time code generator for the adk-clojure library that produces static Clojure source code for bidirectional conversion between `com.google.genai.types` Java objects and Clojure maps. The generator runs as a build task and emits `generated_types.clj` — a file containing `datafy`/`make-object` multi-method implementations for every AutoValue type in the full GenAI type hierarchy, requiring no reflection at load time.

## Core Value

Conversions between Clojure maps and `com.google.genai.types` Java objects must work correctly and without runtime reflection — generated at build time, committed to the repository.

## Requirements

### Validated

- ✓ `autovalue.clj` code generator implemented — emits `datafy` and `make-object` forms for any AutoValue class
- ✓ `build.clj` `generate-types` task implemented — runs generator via `clojure -T:build generate-types`
- ✓ `:generate` alias in `deps.edn` — provides `org.reflections/reflections` for class scanning
- ✓ `generated_types.clj` file generated — covers full `com.google.genai.types` AutoValue hierarchy
- ✓ `types.clj` updated — simplified to require `io.kosong.adk.generated-types`
- ✓ Runtime macro (`register-autovalue-class`) retained for non-genai types (e.g. `LiveRequest`)
- ✓ Tests in `autovalue_test.clj` structured as `deftest` forms, pass via `clojure -X:test` (Validated in Phase 1: Ship It)
- ✓ `generated_types.clj` committed to repository (351 AutoValue types, deterministic output) (Validated in Phase 1: Ship It)
- ✓ Build pipeline verified end-to-end — `generate-types` idempotent, `jar` clean (Validated in Phase 1: Ship It)

### Active

_(none — all requirements validated in Phase 1)_

### Out of Scope

- `com.google.adk.*` types via build-time generation — runtime macro is acceptable for these
- Code generation for non-AutoValue Java types — not needed

## Context

This is an in-progress task in the `develop` branch of adk-clojure. The codebase uses Google's ADK Java library with AutoValue for value types. The `autovalue.clj` namespace uses the Reflections library to scan for `@AutoValue`-annotated classes at generation time, then emits static Clojure forms — so the generated file has no reflection dependency at runtime. The `:generate` alias is only needed when running `generate-types`, not during normal builds or at runtime.

## Constraints

- **Tech stack**: Clojure 1.12.3, Java 17+, `clojure.tools.build`
- **Scope**: Core library only (`core/` directory)
- **Generated file**: Committed to source control — regenerate with `clojure -T:build generate-types` when the GenAI type hierarchy changes

## Key Decisions

| Decision | Rationale | Outcome |
|----------|-----------|---------|
| Generate `com.google.genai.types` only | Full type hierarchy; `com.google.adk.*` types are fewer and runtime macro is acceptable | Confirmed in Phase 1 |
| Commit `generated_types.clj` to repo | Avoids Reflections dependency at runtime or during normal builds | Confirmed in Phase 1 |
| `generate-types` as explicit build task | Regeneration is intentional, not automatic — protects against accidental overwrites | Confirmed in Phase 1 |
| Sort AutoValue properties by method name | Ensures deterministic, idempotent code generation | Added in Phase 1 |

## Evolution

This document evolves at phase transitions and milestone boundaries.

**After each phase transition** (via `/gsd:transition`):
1. Requirements invalidated? → Move to Out of Scope with reason
2. Requirements validated? → Move to Validated with phase reference
3. New requirements emerged? → Add to Active
4. Decisions to log? → Add to Key Decisions
5. "What This Is" still accurate? → Update if drifted

**After each milestone** (via `/gsd:complete-milestone`):
1. Full review of all sections
2. Core Value check — still the right priority?
3. Audit Out of Scope — reasons still valid?
4. Update Context with current state

---
*Last updated: 2026-03-22 after Phase 1 (Ship It) completion*
