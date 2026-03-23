---
created: 2026-03-23T16:13:26.843Z
title: Fix critical bugs before 0.1.0 release
area: general
files:
  - dev/src/main/clojure/io/kosong/adk/web/handlers.clj:244
  - core/src/main/clojure/io/kosong/adk/core.clj:157
---

## Problem

Two known bugs identified in CONCERNS.md that should be fixed before shipping 0.1.0:

**Bug 1 (Critical) — WebSocket user_id parameter:**
`dev/src/main/clojure/io/kosong/adk/web/handlers.clj:244`
Handler reads `"user"` query parameter instead of `"user_id"`. This breaks WebSocket user isolation — all live connections share the same user context regardless of the `user_id` query param passed by the client.

**Bug 2 (High) — Copy-paste bug in callback parameter detection:**
`core/src/main/clojure/io/kosong/adk/core.clj:157`
Callback parameter detection uses wrong variable name. May cause certain callbacks to not receive correct context.

## Solution

- Fix Bug 1: Change `"user"` → `"user_id"` in the WebSocket query param lookup at handlers.clj:244
- Fix Bug 2: Identify and correct the wrong variable name in core.clj:157
- Add regression tests for both fixes
- Verify with live-chatbot example after Bug 1 fix
