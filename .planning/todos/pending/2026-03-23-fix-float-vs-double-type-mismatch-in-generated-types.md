---
created: 2026-03-23T16:44:30.671Z
title: Fix Float vs Double type mismatch in generated-types
area: general
files:
  - core/src/main/clojure/io/kosong/adk/generated_types.clj:5454
---

## Problem

In `generated_types.clj`, some Java builder methods accept `java.lang.Float` parameters (e.g., `temperature`, `frequencyPenalty`, `presencePenalty`, `topP`, `topK`), but the generated code passes Clojure numeric values directly — which default to `java.lang.Double` on the JVM.

Example at line 5454:
```clojure
(clojure.core/when-some [v (:temperature data)] (. b temperature v))
```

If `b.temperature()` expects `Float`, passing a `Double` `v` will throw a `ClassCastException` at runtime.

Affected fields likely include: `:temperature`, `:frequency-penalty`, `:presence-penalty`, `:top-p`, `:top-k`, and any other Float-typed builder methods in the GenAI types.

## Solution

The code generator needs to emit explicit casts to `Float` where the Java method signature requires it:

```clojure
;; Instead of:
(clojure.core/when-some [v (:temperature data)] (. b temperature v))

;; Should emit:
(clojure.core/when-some [v (:temperature data)] (. b temperature (float v)))
```

Fix approach:
1. Identify all Float-typed builder methods in the affected Java classes (via reflection in the generator)
2. Update the code generator (`generate-types` task in `build.clj`) to emit `(float v)` casts
3. Regenerate `generated_types.clj`
4. Verify with a test that passes `:temperature 0.7` and checks no ClassCastException
