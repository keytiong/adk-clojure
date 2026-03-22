# Requirements: adk-clojure Build-Time GenAI Type Code Generation

**Defined:** 2026-03-22
**Core Value:** Conversions between Clojure maps and `com.google.genai.types` Java objects must work correctly and without runtime reflection — generated at build time, committed to the repository.

## v1 Requirements

### Code Generation

- [ ] **GEN-01**: `generated_types.clj` is committed to the repository
- [x] **GEN-02**: `generate-types` build task is documented — explicitly states it must be re-run when the `google-genai` dependency version changes
- [ ] **GEN-03**: Generator produces deterministic output (idempotent — running twice produces identical file)

### Testing

- [x] **TEST-01**: `autovalue_test.clj` uses `deftest` wrappers so tests are discoverable by standard test runners
- [x] **TEST-02**: `:test` alias in `deps.edn` includes a test runner (cognitect/test-runner or inline `run-tests`)
- [x] **TEST-03**: Round-trip tests pass — `datafy` (Java→map) and `make-object` (map→Java) verified for representative types

### Build

- [ ] **BUILD-01**: `jar` task packages `generated_types.clj` from source (no silent regeneration inside `jar`)
- [ ] **BUILD-02**: End-to-end build verified: `generate-types` → `jar` succeeds without errors

## v2 Requirements

### CI Enforcement

- **CI-01**: CI runs `clojure -T:build generate-types && git diff --exit-code` to detect generated file drift
- **CI-02**: CI check scoped to trigger only when `deps.edn` or `com.google.genai.types` classes change

### Coverage

- **COV-01**: Structural completeness test — asserts every `com.google.genai.types` AutoValue class has both `make-object` and `Datafiable` implementations

## Out of Scope

| Feature | Reason |
|---------|--------|
| Build-time generation for `com.google.adk.*` types | Runtime macro (`register-autovalue-class`) is acceptable for these |
| Code generation for non-AutoValue Java types | Not needed in this codebase |
| Silent regeneration inside `jar` | Makes builds non-deterministic; committed file is authoritative |

## Traceability

| Requirement | Phase | Status |
|-------------|-------|--------|
| GEN-01 | Phase 1 | Pending |
| GEN-02 | Phase 1 | Complete |
| GEN-03 | Phase 1 | Pending |
| TEST-01 | Phase 1 | Complete |
| TEST-02 | Phase 1 | Complete |
| TEST-03 | Phase 1 | Complete |
| BUILD-01 | Phase 1 | Pending |
| BUILD-02 | Phase 1 | Pending |

**Coverage:**
- v1 requirements: 8 total
- Mapped to phases: 8
- Unmapped: 0 ✓

---
*Requirements defined: 2026-03-22*
*Last updated: 2026-03-22 after initial definition*
