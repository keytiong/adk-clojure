# Build-Time Code Generation: Findings for adk-clojure

**Researched:** 2026-03-22
**Scope:** clojure.tools.build patterns for committed generated source files

## Current State

`generate-types` is a standalone build task. It calls `compile-java` internally, then
spawns a subprocess running the generator. The output (`generated_types.clj`) is committed
to the repo and consumed at compile/runtime. There is no CI enforcement and no connection
between `generate-types` and `jar`.

---

## 1. Should `generate-types` be called inside `jar`?

**Recommendation: No. Keep it separate.**

The rationale:

- `generate-types` produces a *committed* file. The JAR task should package what is in
  the repo, not silently regenerate it. Regenerating inside `jar` means two different
  runs of `jar` could produce different JARs depending on whether the upstream Java
  library (google-genai) changed — this is non-deterministic and surprises consumers.
- The subprocess cost is real: `generate-types` calls `compile-java` then spawns a new
  JVM. That is acceptable as a deliberate developer action, not as a side effect of
  every build.
- The `deps/prep-lib` hook already handles the prerequisite (`compile-java`) for the
  normal build path. Adding `generate-types` there would make library prep non-
  reproducible for consumers who don't have `org.reflections` on the classpath unless
  they use the `:generate` alias.

**Appropriate pattern:** Document that `generate-types` must be re-run when the
`google-genai` dependency version changes, then commit the result. Treat it like a
lock file or a generated protobuf file.

---

## 2. Keeping the Committed File in Sync

Since there is no `.github/` CI directory in this repo yet, the options are:

**Option A: Diff check in CI (recommended)**

Add a CI step that runs `clojure -T:build generate-types`, then checks whether the
generated file is dirty:

```bash
clojure -T:build generate-types
git diff --exit-code core/src/main/clojure/io/kosong/adk/generated_types.clj
```

A non-zero exit from `git diff` fails the job. This is the same pattern used by
`go generate` + `git diff` checks, Buf protobuf CI, and gofmt enforcement. It is
simple, robust, and requires no special tooling.

**Option B: Checksum sentinel**

Compute a hash of the input (e.g., the resolved coordinates of `google-genai`) and
embed it as a comment at the top of `generated_types.clj`. On each build, recompute
the hash and compare. Fail if mismatched. This is lighter than re-running the full
generator in CI but requires the hash to be maintained deliberately.

Option A is preferred because it validates the actual output, not just the input.

**Practical note:** The diff check only catches staleness when someone updates
`google-genai` version and forgets to regenerate. Tying the check to a deps.edn
change (via path filters in CI) avoids running the expensive generator on every PR.

---

## 3. Testing the Generator's Output

The existing `autovalue_test.clj` tests the *runtime behavior* of the generated code
(round-trip Java<->Clojure for `GenerateContentConfig`). That is the right level.

Two additional patterns are worth adding:

**A. Structural completeness test**

Assert that every class in `com.google.genai.types` that the generator claims to
handle has a registered `make-object` method and a `Datafiable` implementation.
This catches classes that were added to the library but skipped by the generator
due to a reflection edge case.

```clojure
(testing "all genai types have make-object methods"
  (let [registered (set (keys (methods io.kosong.java/make-object)))
        expected   (reflection/scan-autovalue-classes "com.google.genai.types")]
    (is (= expected registered))))
```

**B. Generator idempotency test**

Run the generator twice on the same classpath and assert the output is identical
(byte-for-byte or after normalization). This guards against non-deterministic
reflection ordering producing unstable diffs.

This can be a test in the `:build` namespace itself, not in the main test suite,
since it requires `org.reflections` and the `:generate` alias.

**What not to do:** Do not write tests that parse the generated `.clj` file as text
and assert specific forms. The generated output format may change (e.g., switching
from `extend-type` to `defmethod`-only) without changing correctness. Test behavior,
not syntax.

---

## Summary

| Question | Recommendation |
|----------|---------------|
| `generate-types` inside `jar`? | No — keep separate, treat output like a lock file |
| Sync enforcement | CI diff check after re-running generator, scoped to deps.edn changes |
| Testing output | Round-trip behavior tests (already done) + structural completeness test |
