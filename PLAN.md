# adk-clojure Upgrade Plan: v0.5.0 → v1.0.0 → v1.2.0

## Source Reference
- adk-java 0.5.0: `/home/keytiong/Projects/adk-java-0.5.0`
- adk-java 1.0.0: `/home/keytiong/Projects/adk-java-1.0.0`
- adk-java 1.2.0: `/home/keytiong/Projects/adk-java-1.2.0`

---

## Phase A: Upgrade to v1.0.0 (Primary Target)

v1.0.0 is the first stable release with significant architectural changes. Upgrade to this first.

### A.1 Dependency & Compilation (Estimate: 2 days)

#### A.1.1 Update `core/deps.edn`
- [x] Change `com.google.adk/google-adk` from `0.5.0` to `1.0.0`
- [x] Verify no other transitive dependency conflicts

#### A.1.2 Fix `State` constructor change
- **Change**: `State(ConcurrentMap<String,Object>)` → `State(Map<String,Object>)` (internally converts)
- [x] `sessions.clj:33` — Remove `ConcurrentHashMap.` wrapper; pass plain `Map` directly
- [x] Verify no behavioral regression (State still implements ConcurrentMap internally)

#### A.1.3 Fix `LlmAgent` callback type changes
- **Change**: Callback fields from `Optional<List<...>>` to `ImmutableList<...>` (non-optional, defaults to empty)
- [x] `core.clj` — `llm-agent` builder: verify callback builder methods accept lists directly (not wrapped in Optional)
- [x] `agents.clj` — `IntoBeforeModelCallback` etc.: verify reified callbacks work with the new `List` setter methods

#### A.1.4 Remove `exampleProvider` from LlmAgent
- **Change**: `exampleProvider` field/methods removed entirely
- [x] `core.clj` — Remove `:example-provider` parameter from `llm-agent`
- [x] `core.clj` — Remove `(.exampleProvider b example-provider)` call
- [x] Remove from any examples that use it

#### A.1.5 Add new error callbacks to LlmAgent
- **New**: `OnModelErrorCallback`, `OnModelErrorCallbackSync`, `OnModelErrorCallbackBase`
- **New**: `OnToolErrorCallback`, `OnToolErrorCallbackSync`, `OnToolErrorCallbackBase`
- [x] `protocols.clj` — Add `IntoOnModelErrorCallback` and `IntoOnToolErrorCallback` protocols
- [x] `agents.clj` — Implement protocol extensions for `IFn` → reify the new callback interfaces
- [x] `core.clj` — Add `:on-model-error-callback` and `:on-tool-error-callback` params to `llm-agent`
- [x] `core.clj` — Wire `.onModelErrorCallback()` and `.onToolErrorCallback()` builder calls

#### A.1.6 Fix `tools()` return type change
- **Change**: `tools()` returns `Single<List<BaseTool>>` instead of `List<BaseTool>`
- [x] Search for any calls to `(.tools agent)` and handle the Single return type
- [x] `run-async` / `run-live` should be unaffected (they use internal paths)

#### A.1.7 Fix `canonicalTools()` parameter change
- **Change**: `canonicalTools(Optional<ReadonlyContext>)` → `canonicalTools(@Nullable ReadonlyContext)`
- [x] Verify no Clojure code calls `canonicalTools` directly (internal method)

#### A.1.8 Fix `codeExecutor()` return type change
- **Change**: `@Nullable BaseCodeExecutor` → `Optional<BaseCodeExecutor>`
- [x] Verify no Clojure code calls `.codeExecutor()` directly

### A.2 `InvocationContext` Overhaul (Estimate: 2 days)

#### A.2.1 Remove `ResumabilityConfig`
- **Change**: `ResumabilityConfig` removed entirely from InvocationContext
- [x] Search for any usage of `ResumabilityConfig` in Clojure code
- [x] Remove references to `.resumabilityConfig()`, `isResumable()`, `shouldPauseInvocation()`

#### A.2.2 New fields in InvocationContext
- **New**: `eventsCompactionConfig` (EventsCompactionConfig), `contextCacheConfig` (ContextCacheConfig), `callbackContextData` (Map<String, Object>)
- [x] `agents.clj` — Update `InvocationContext` datafy to include new fields
- [ ] Verify `toBuilder()` method works for creating modified copies

#### A.2.3 `PluginManager` → `Plugin` type change
- **Change**: `.pluginManager()` returns `Plugin` interface (not `PluginManager` class)
- [x] `agents.clj` — Verify `PluginManager` still works (it now extends BasePlugin which implements Plugin)
- [x] No Clojure code should need changes here (Java-side compatibility preserved)

#### A.2.4 `@Nullable` instead of `Optional` for many fields
- **Change**: `liveRequestQueue`, `userContent`, `branch` now `@Nullable` (return Optional from getters still)
- [x] Verify autovalue/datafy handles `@Nullable` fields correctly
- [x] The getters still return `Optional`, so Clojure-side code should be unaffected

#### A.2.5 Builder validation
- **Change**: Builder now validates required fields (invocationId, agent, session, sessionService)
- [x] Ensure all InvocationContext construction in Clojure provides required fields

### A.3 Plugin System Changes (Estimate: 1 day)

#### A.3.1 `BasePlugin` → `Plugin` interface
- **Change**: `BasePlugin` is now an abstract class implementing the `Plugin` interface
- [x] `agents.clj` — `PluginManager` constructor: `List<Plugin>` (was `List<BasePlugin>`)
- [x] `core.clj` — `.plugins(plugins)` should work (plugins are now Plugin interface instances)

#### A.3.2 New `close()` on Plugin/Runner
- **New**: Both `Plugin` interface and `Runner` have `close()` method
- [x] Consider adding `close-runner` helper in runner.clj
- [ ] Update examples to close runner when done

#### A.3.3 `PluginManager` extends `BasePlugin`
- **Change**: PluginManager itself is now a Plugin (implements all callback methods via override)
- [x] No Clojure-side changes needed, but be aware of the structural change

### A.4 `Runner` Changes (Estimate: 2 days)

#### A.4.1 New `App` class
- **New**: `com.google.adk.apps.App` — can be passed to `Runner.Builder.app(app)`
- [ ] `core.clj` — Optionally add support for `:app` parameter in runner creation

#### A.4.2 `Telemetry` → `Tracing`
- **Change**: Internal telemetry package renamed (import change only)
- [x] No Clojure-side impact (Clojure doesn't reference Telemetry directly)

#### A.4.3 `autoCreateSession()` in RunConfig
- **New**: `RunConfig.autoCreateSession()` field (defaults to false)
- [x] `agents.clj` — Add `:auto-create-session` to RunConfig `make-object` method
- [x] `core.clj` — Document in run-async/run-live docs

#### A.4.4 `SessionKey` support
- **New**: `Runner.runAsync(SessionKey, ...)` and `Runner.runLive(SessionKey, ...)` overloads
- [ ] Optionally add `:session-key` support to Clojure runner functions

#### A.4.5 `copySessionStates` change
- **Change**: `target.state().putAll(source.state())` instead of iterating
- [x] No Clojure-side impact

#### A.4.6 `findSubAgent()` return type change
- **Change**: `@Nullable BaseAgent` → `Optional<BaseAgent>`
- [x] `core.clj` — If any code calls `findSubAgent`, handle Optional

#### A.4.7 `Runner` close()
- **New**: `Runner.close()` returns `Completable`
- [x] Add `close-runner` helper in runner.clj

### A.5 `RunConfig` Builder Method Renaming (Estimate: 0.5 days)

- **Change**: `setX` methods deprecated, new `x` methods added (both exist via delegation)
- [x] `agents.clj` — Update `make-object` for RunConfig to use new method names (`streamingMode` instead of `setStreamingMode`, etc.)
- [x] The deprecated `setX` methods still work, so this is just cleanup

### A.6 `Session` Changes (Estimate: 0.5 days)

#### A.6.1 `SessionKey` support
- **New**: `Session.builder(SessionKey)` and `Builder.sessionKey(SessionKey)`
- [ ] `sessions.clj` — Optionally add helper for building from SessionKey

#### A.6.2 `Session.state()` returns `Map` (not ConcurrentMap)
- **Change**: State internally still ConcurrentMap, but public API is Map
- [x] Verify datafy works correctly

#### A.6.3 `events` is `Collections.synchronizedList`
- [x] No Clojure-side change needed

### A.7 Autovalue/Java Interop (Estimate: 1 day)

#### A.7.1 `optional-type?` handling
- **Change**: Many `Optional` fields replaced with `@Nullable`
- [x] `autovalue.clj` — Verify `optional-type?` doesn't break for classes that switched from Optional to @Nullable
- [x] The `optional-datafy-assoc` function handles nil correctly, so @Nullable fields should work
- [ ] Key classes to check: `RunConfig`, `InvocationContext`, `LlmAgent`, `Event`, `EventActions`

#### A.7.2 `make-object` for new classes
- [ ] Register autovalue types for new classes: `EventsCompactionConfig`, `ContextCacheConfig`, `SessionKey`
- [ ] Add `defmethod` handlers for any new AutoValue types needed

#### A.7.3 `Event` class changes
- **Change**: Event uses `@Nullable` fields instead of Optional
- [x] Verify event datafy and make-object still work

### A.8 `ClojureAgent.java` Update (Estimate: 0.5 days)

#### A.8.1 `BaseAgent` constructor changes
- [x] Verify ClojureAgent constructor still matches BaseAgent's constructor signature
- [ ] In 1.0.0, BaseAgent constructor params changed slightly (subAgents is `@Nullable List`)
- [x] Verify `beforeAgentCallback`/`afterAgentCallback` types (now `ImmutableList`)

#### A.8.2 `findSubAgent` return type
- [x] If ClojureAgent overrides `findSubAgent`, update return type to `Optional<BaseAgent>`

#### A.8.3 `close()` method
- [ ] Optionally add `close()` override if ClojureAgent has resources to clean up

### A.9 Regenerate `generated_types.clj` (Estimate: 1 day)

- [ ] Run `cd core && clojure -T:build generate-types`
- [ ] Review generated file for any breaking changes in datafy/make-object
- [ ] Fix any `@Nullable` handling issues

### A.10 Polish & Testing (Estimate: 3 days)

#### A.10.1 Update examples
- [ ] Fix `chatbot`, `blog_writer`, `live-chatbot`, `multi_tool` examples
- [ ] Remove any `exampleProvider` usage
- [ ] Verify runner creation works

#### A.10.2 Update web module (dev/)
- [ ] Fix `handlers.clj` for new API
- [ ] Fix `routes.clj` if needed
- [ ] Verify SSE and WebSocket endpoints

#### A.10.3 Unit tests
- [x] Update `session_test.clj` for State as Map
- [x] Add tests for new callback types
- [x] Update `runner_test.clj` for Runner changes
- [x] Add tests for RunConfig `:auto-create-session`

#### A.10.4 Integration tests
- [ ] Run full integration test suite
- [ ] Verify session lifecycle
- [ ] Verify agent execution with tools
- [ ] Verify all callback types fire correctly
- [ ] Verify streaming (SSE + WebSocket)

#### A.10.5 Documentation
- [x] Update README.md
- [x] Document new callback types
- [x] Document new features (autoCreateSession, event compaction)
- [x] Document removed features (exampleProvider)

---

## Phase B: Upgrade to v1.2.0 (After v1.0.0 is stable)

v1.2.0 adds ChatCompletion support and more analytics. The delta from 1.0.0 is smaller.

### B.1 New ChatCompletion Support (Estimate: 2 days)

- **New**: `ChatCompletionsRequest`, `ChatCompletionsResponse` in `com.google.genai.types`
- **New**: Conversion from ChatCompletionsResponse → LlmResponse
- [ ] Evaluate if Clojure wrapper needs ChatCompletion abstraction
- [ ] Register autovalue types for new ChatCompletion classes

### B.2 New Agent Analytics Plugin Enhancements (Estimate: 1 day)

- **New**: `BigQueryUtils`, `EventData`, `PluginState`, `TraceManager` in agentanalytics
- **New**: BigQuery auto-schema upgrade and view creation
- **New**: Plugin state is now per-invocation
- [ ] No Clojure-side changes needed unless using BigQuery plugin directly

### B.3 Bug fixes
- **Fix**: Race condition in Runner for sequential tool execution
- **Fix**: Forward state delta from all events (not just last)
- **Fix**: BuiltInCodeExecutor for Gemini 3 models
- [ ] These are internal fixes, no Clojure changes needed

### B.4 New features (optional)
- [ ] Support plugins in AgentTool (like Python)
- [ ] Customer content formatting
- [ ] Telemetry headers
- [ ] Gemma models in LlmRegistry

---

## Summary of Breaking Changes (0.5.0 → 1.0.0)

| Area | Change | Clojure Impact | Effort |
|------|--------|---------------|--------|
| `State` constructor | `ConcurrentMap` → `Map` | `sessions.clj` — remove ConcurrentHashMap wrapper | Low |
| `LlmAgent.exampleProvider` | **Removed** | `core.clj` — remove param and builder call | Low |
| `LlmAgent` callbacks | `Optional<List>` → `ImmutableList` | `agents.clj` — verify callback wiring works | Low |
| `LmmAgent` error callbacks | **New** | `protocols.clj` + `agents.clj` + `core.clj` — add 2 new protocols | Medium |
| `LlmAgent.tools()` | `List` → `Single<List>` | If called directly, handle Single | Low |
| `LlmAgent.codeExecutor()` | `@Nullable` → `Optional` | If called directly, handle Optional | Low |
| `LlmAgent.close()` | **New** | Add close helper | Low |
| `InvocationContext.ResumabilityConfig` | **Removed** | Remove any references | Low |
| `InvocationContext` new fields | `eventsCompactionConfig`, `contextCacheConfig`, `callbackContextData` | Update datafy | Low |
| `InvocationContext.toBuilder()` | **New** | May be useful for context modification | Low |
| `InvocationContext.copyOf()` | **Removed** | Use `toBuilder()` instead if needed | Low |
| `InvocationContext` @Nullable | Many fields changed from Optional to @Nullable | Autovalue/datafy should handle | Low |
| `BasePlugin` → `Plugin` | Class → Interface | `agents.clj` — PluginManager type change | Low |
| `PluginManager` constructor | `List<BasePlugin>` → `List<? extends Plugin>` | `core.clj` — plugins param type | Low |
| `Plugin.close()` | **New** | Add close helper | Low |
| `Runner` new `App` | `Builder.app(App)` | Optional: add :app param | Low |
| `Runner.close()` | **New** | Add close helper | Low |
| `RunConfig` | `autoCreateSession` added | Add to make-object | Low |
| `RunConfig` builder | `setX` → `x` (deprecated) | Update method names in make-object | Low |
| `Session` | `SessionKey` support | Optional: add builder helper | Low |
| `BaseAgent.findSubAgent()` | `@Nullable` → `Optional` | If called directly, handle Optional | Low |
| `@Nullable` everywhere | Many fields changed | Autovalue/datafy should handle | Medium |

### Estimated Total for v1.0.0: **~3 weeks** for a single developer

---

## Summary of Additions (1.0.0 → 1.2.0)

| Area | Change | Clojure Impact |
|------|--------|---------------|
| ChatCompletion | New types + conversion | Optional: register autovalue types |
| Agent Analytics | BigQuery enhancements | No change (internal) |
| Gemma models | Added to LlmRegistry | No change |
| Race condition fixes | Runner fixes | No change |
| Plugins in AgentTool | Like Python | Optional |
| Content formatting | Customer content | Optional |

### Estimated Total for v1.2.0: **~1 week** for a single developer

---

## File-by-File Change Summary (v1.0.0)

### `core/deps.edn`
- [x] Version: `0.5.0` → `1.0.0`

### `core/src/main/clojure/io/kosong/adk/core.clj`
- [x] `llm-agent`: Remove `:example-provider` param
- [x] `llm-agent`: Add `:on-model-error-callback` and `:on-tool-error-callback` params
- [x] `agent-context`: No change needed
- [x] `run-async`: No structural change (Runner API compatible)
- [x] `run-live`: No structural change

### `core/src/main/clojure/io/kosong/adk/sessions.clj`
- [x] `create-session`: Remove `ConcurrentHashMap.` wrapper, pass plain `Map`

### `core/src/main/clojure/io/kosong/adk/agents.clj`
- [x] Add `IntoOnModelErrorCallback` protocol implementation for `IFn`
- [x] Add `IntoOnToolErrorCallback` protocol implementation for `IFn`
- [x] Update `RunConfig` make-object: use new builder method names (`streamingMode` instead of `setStreamingMode`)
- [x] Add `:auto-create-session` to RunConfig make-object
- [x] Update `InvocationContext` datafy: add `events-compaction-config`, `context-cache-config`, `callback-context-data`
- [x] Verify callback protocol implementations work with new `ImmutableList` types

### `core/src/main/clojure/io/kosong/adk/runner.clj`
- [x] Optionally add `close-runner` helper

### `core/src/main/clojure/io/kosong/adk/protocols.clj`
- [x] Add `IntoOnModelErrorCallback` protocol
- [x] Add `IntoOnToolErrorCallback` protocol

### `core/src/main/clojure/io/kosong/autovalue.clj`
- [x] Verify `optional-type?` works for classes with @Nullable fields
- [ ] Register new autovalue types: `EventsCompactionConfig`, `ContextCacheConfig`, `SessionKey`

### `core/src/main/clojure/io/kosong/adk/generated_types.clj`
- [ ] Regenerate via `clojure -T:build generate-types`

### `core/src/main/java/io/kosong/adk/agents/ClojureAgent.java`
- [x] Verify constructor compatibility with BaseAgent
- [ ] Optionally add `close()` method

### `core/src/main/java/io/kosong/adk/tools/ClojureFunctionTool.java`
- [x] Verify `runAsync` signature compatibility (should be fine)

### `examples/`
- [x] Remove `exampleProvider` usage from all examples
- [ ] Verify runner creation

### `dev/src/main/clojure/io/kosong/adk/web/`
- [x] Update handlers for new API
- [ ] Verify routes and SSE/WebSocket endpoints

---

## Risk Areas

1. **`@Nullable` vs `Optional`**: The autovalue system's `optional-type?` check detects `java.util.Optional` specifically. Fields that switched to `@Nullable` will simply be `nil` in Clojure — the `optional-datafy-assoc` function already handles nil correctly.

2. **`State` constructor**: The Java side now accepts `Map` and converts internally. Clojure just needs to pass a plain map instead of wrapping in ConcurrentHashMap.

3. **Callback types**: The builder methods for callbacks now expect `List` directly (not `Optional<List>`). The Clojure code already passes lists, so this should be a non-change.

4. **`exampleProvider` removal**: This is the most visible user-facing breaking change. Any code using `:example-provider` in `llm-agent` will break.

5. **`ResumabilityConfig` removal**: If any code references this, it will break. Check for any usage.

6. **`Runner` deprecated methods**: The old constructor-based creation is deprecated. Builder-based creation should be used.

### Recommended Approach

1. **Git tag** current working code: `git tag v0.5.0-based`
2. **Phase 1**: Update deps.edn only, commit as WIP
3. **Phase 2**: Fix compile errors (State, LlmAgent, InvocationContext)
4. **Phase 3**: Add new callback protocols
5. **Phase 4**: Regenerate generated_types.clj
6. **Phase 5**: Update examples and tests
7. **Phase 6**: Integration testing
8. **Tag** after successful v1.0.0 upgrade: `git tag v1.0.0-based`
9. **Then** proceed to v1.2.0 changes
