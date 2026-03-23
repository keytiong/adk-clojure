# Retrospective: adk-clojure

## Milestone: v0.1.0 — Build-Time GenAI Type Generation

**Shipped:** 2026-03-22
**Phases:** 1 | **Plans:** 2

### What Was Built

- cognitect test-runner wired to autovalue round-trip tests via deftest wrappers and :test alias; generate-types docstring documents re-run trigger
- Committed `generated_types.clj` (351 AutoValue types) after fixing non-deterministic property ordering; idempotency verified, end-to-end jar build confirmed

### What Worked

- Two-plan structure for the phase was well-scoped: plan 1 handled test infra, plan 2 handled code generation commit — clean separation, no rework
- Fixing the idempotency bug (sort-by method name) was discovered and fixed within the same plan execution rather than becoming a separate issue
- Verification was able to confirm all 8 requirements statically (6/7 truths automated; 1 flagged for human runtime check)

### What Was Inefficient

- The milestone was named "v1.0" in the ROADMAP/STATE from the start, but the correct version should have been v0.1.0 (aligning with underlying Google ADK 0.x release cycle) — caught at milestone completion

### Patterns Established

- Generated files committed to source control: no runtime reflection, no build-time surprises
- `sort-by` on Java method name for deterministic code generation output
- cognitect test-runner wired via `:exec-fn` (not `:main-opts`) in a dedicated `:test` alias

### Key Lessons

- Version numbering should track the underlying library's maturity (ADK is 0.x → adk-clojure is 0.x)
- Static code analysis (verification) can confirm 85%+ of requirements without running the code — saves time
- Float vs Double type mismatch in generated types is a known gap to address in v0.2.0

### Cost Observations

- Sessions: 1 focused session
- Model: Claude Sonnet 4.6 throughout
- Notable: Phase completed in ~5 minutes of execution time (2 plans, minimal rework)

---

## Cross-Milestone Trends

| Milestone | Phases | Plans | Rework | On-time |
|-----------|--------|-------|--------|---------|
| v0.1.0    | 1      | 2     | Low    | Yes     |
