# CONCERNS.md — Technical Debt & Concerns

## Critical Bugs

### 1. Copy-paste bug in callback parameter detection
**File**: `core/src/main/clojure/io/kosong/adk/core.clj:157`
**Issue**: Callback parameter detection uses wrong variable name
**Impact**: Certain callbacks may not receive correct context
**Priority**: High

### 2. WebSocket user_id parameter bug
**File**: `dev/src/main/clojure/io/kosong/adk/web/handlers.clj:244`
**Issue**: Handler reads `"user"` query parameter instead of `"user_id"`
**Impact**: WebSocket user isolation broken; all live connections share same user context
**Priority**: Critical

## Technical Debt

### Generated Types File Size
- `core/src/main/clojure/io/kosong/adk/generated_types.clj` is ~16,235 lines
- Single massive file for all auto-generated type extensions
- Makes navigation and diff reviews difficult
- **Recommendation**: Split into domain-grouped files (content types, agent types, event types, etc.)

### RxJava Subscriptions Not Exposed
- ADK uses RxJava internally for async operations
- Subscriptions created during `run-async` / `run-live` are not returned to callers
- **Impact**: No mechanism to cancel in-flight agent execution; potential memory leaks on long-running operations
- **Risk**: High for production deployments with many concurrent sessions

### Implicit Module Requires
- Some namespaces use implicit requires rather than explicit `ns` declarations
- Complicates dependency graph analysis and tooling support
- **Recommendation**: Audit and add explicit requires throughout

### Missing Resource Cleanup in WebSocket Handlers
- WebSocket close handlers don't clean up all resources (core.async channels, subscriptions)
- `request-ch` may not be closed on abrupt disconnects
- **Risk**: Channel leaks accumulate over time in long-running server

## Security Concerns

### Global OpenTelemetry Reset on Startup
**File**: `dev/src/main/clojure/io/kosong/adk/web/telemetry.clj`
**Issue**: Every server restart resets the global OpenTelemetry SDK instance
**Impact**: Affects any other libraries using the global instance; not safe in multi-tenant JVM deployments

### JSON Deserialization Without Size Limits
- WebSocket frame limit is 10MB, but JSON deserialization has no additional bounds checking
- Deeply nested or adversarially crafted JSON could cause issues
- **Risk**: Medium (requires authenticated access)

### Session Validation Creates New Sessions Silently
- Invalid session ID in requests silently creates a new session instead of returning 404/401
- **Impact**: Session fixation / state confusion; hides programming errors in clients
- **Risk**: Medium — depends on whether auth is added upstream

### Potential Information Disclosure in Error Responses
- Exception messages may be included in HTTP error responses or SSE error events
- Stack traces / internal paths could leak implementation details
- **Recommendation**: Scrub error messages before sending to clients

### No Authentication/Authorization
- The web layer has no built-in auth middleware
- All `/api/*` endpoints are publicly accessible
- **Assumption**: Expected to run behind a reverse proxy or within a trusted network
- **Risk**: High if deployed directly to internet

## Performance Concerns

### ConcurrentHashMap for Session State
- Session state uses mutable `ConcurrentHashMap` passed between agents
- Not suitable for distributed deployments (single-node only)
- In-memory sessions lost on restart

### Sliding Buffer May Drop Events
- `event-ch` uses sliding buffer (size 16) in `run-live`
- If consumer is slow, events are silently dropped
- **Risk**: Lost model responses in high-throughput live sessions

### No Connection Pooling for LLM Requests
- Each LLM request creates new HTTP connections (LangChain4J default behavior)
- May be suboptimal at scale; depends on LangChain4J internals

## Fragile Areas

### WebSocket Lifecycle Management
- Minimal cleanup logic on WebSocket close/error
- Idle timeout (5 min) is the primary cleanup mechanism
- Connection limit checks (10/user, 100/app) may have race conditions

### Tool Error Handling
- Tool execution errors are logged but return empty responses to the LLM
- LLM receives no indication of tool failure
- Agent may loop trying the same failing tool
- **Recommendation**: Return error information in tool response for LLM awareness

### Null Agent Handling
- Agent registry lookup returns nil for unknown agents
- Handlers may not validate before use; NPE risk

### `optional-datafy-assoc` Reliance
- Heavy use of `optional-datafy-assoc` for Java Optional/Collection handling
- Any ADK API changes to return types would silently break conversion
- No type-checking at conversion boundaries

## Test Coverage Gaps

(See TESTING.md for full detail)

- Core library has 1 test file covering minimal functionality
- No tests for protocol conversions, agent builders, web handlers, or streaming
- No CI/CD configuration found in repository root

## Dependency Concerns

- **ADK version 0.5.0** — pre-1.0, API may change without backward compatibility
- **Pedestal 0.8.0** — mature but less maintained than alternatives (Ring/http-kit)
- Java 17+ requirement limits deployment environments

## Areas Needing Documentation

- `generated_types.clj` — no explanation of what generated it or how to regenerate
- Custom LLM backend registration pattern (only in CLAUDE.md, not in source)
- Session state key conventions for multi-agent coordination
- Error recovery patterns for loop agents
