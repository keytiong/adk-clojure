# CONCERNS.md — Technical Debt and Issues

## Critical Bugs

### Type Mapping Bug in `core/src/main/clojure/io/kosong/adk/core.clj`
- Lines 103, 106, 157: Wrong callback protocol mappers used
- Could cause silent failures or incorrect callback routing

### WebSocket Parameter Parsing Bug in `dev/src/main/clojure/io/kosong/adk/web/handlers.clj`
- Line 244: Reading `:user` instead of `:user_id` from query params
- Causes session lookup failures for live WebSocket connections

### Header Typo in handlers
- Lines 163, 168: Using `:header` instead of `:headers` in response maps
- Could cause malformed HTTP responses

### Silent Error Suppression in `ClojureFunctionTool.java`
- Line 121: Catches and hides exceptions from Clojure tool functions
- Errors silently swallowed, making debugging very difficult

## Memory Leaks

### WebSocket Connection Tracking Not Cleaned Up
- Connection counts (per-user, per-app) incremented on open but not reliably decremented on close
- Under reconnection scenarios, limits could be hit prematurely

## Maintainability

### 16,000+ Line Generated File
- `core/src/main/clojure/io/kosong/adk/generated_types.clj` (auto-generated)
- Extremely large file, difficult to review or maintain
- Should not be checked into version control or should be clearly marked

### Error Handling Inconsistency
- Errors mixed with events in streaming response channels
- Consumers must know to check event type to distinguish errors from normal events

## Security Concerns

### No Authentication
- HTTP endpoints (`/api/run/sse`, `/api/run_live`, session endpoints) have no auth
- Any caller can create sessions or run agents

### No Input Validation
- Agent names, session IDs, and message content not validated at HTTP boundary
- Potential for injection or unexpected behavior

### Session State Race Conditions
- Session state uses `ConcurrentHashMap` but multi-step read-modify-write operations are not atomic
- Concurrent agents sharing state may produce inconsistent results

## Performance Concerns

### Large Generated File at Startup
- Loading `generated_types.clj` at startup adds significant initialization overhead

### Repeated `postwalk` Conversions
- Type conversion in `types.clj` uses recursive tree walks (postwalk)
- For large payloads or high-frequency calls, this could be a bottleneck

## Testing Gaps

### Minimal Test Coverage
- Only 1 test file: `core/src/test/clojure/io/kosong/autovalue_test.clj`
- No integration tests, no HTTP endpoint tests, no agent behavior tests
- No error scenario tests or edge case coverage

### No CI Configuration Found
- No `.github/workflows/` or equivalent CI pipeline detected
- No automated test runs on commit/PR

## Scaling Limitations

### In-Memory Only Storage
- Session service and artifact service are in-memory by default
- State lost on restart; not suitable for multi-instance deployments without custom backends

### Hardcoded Connection Limits
- WebSocket: 10 connections per user, 100 per app — hardcoded, not configurable

### Unbounded Telemetry
- OpenTelemetry spans stored in memory with no eviction policy mentioned
- Long-running services may accumulate unbounded span data

## Areas of Fragility

### Protocol Extension Order Sensitivity
- `types.clj` extends protocols for many Java types; extension order matters
- Adding new protocol extensions could silently shadow existing ones

### Dependency on Internal ADK APIs
- Direct use of internal ADK Java classes (not public API) may break on ADK upgrades
- No version pinning strategy documented beyond deps.edn
