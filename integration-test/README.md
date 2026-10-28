# Integration Tests

Integration tests for adk-clojure that verify end-to-end agent behavior using a
real LLM served through an **OpenAI-compatible API** (llama.cpp by default, via
the `google-adk-langchain4j` adapter).

## LLM Setup

`io.kosong.adk.integration.setup` registers a catch-all LLM factory (pattern
`.*`): whatever model name a test requests (`"test-model"`, `"ollama/qwen3:8b"`,
...) is served by a single real model on an OpenAI-compatible server. The
namespace registers itself on load; every integration test namespace requires it.

| Variable | Default | Description |
|----------|---------|-------------|
| `LLAMA_CPP_BASE_URL` | `http://spark-aa1d:9931/v1` | OpenAI-compatible base URL (must include `/v1`) |
| `TEST_LLM_MODEL` | `unsloth/Qwen3.8-Flash-Next-GGUF:Q4_K_XL` | model id served at that URL |

No API key is required (llama.cpp ignores it; a dummy `"none"` is sent). Any
OpenAI-compatible server works, e.g. a local llama.cpp:

```bash
llama-server -m model.gguf --jinja --port 8080
export LLAMA_CPP_BASE_URL=http://localhost:8080/v1
export TEST_LLM_MODEL=whatever-id-the-server-serves
```

`--jinja` (or an equivalent chat template with tool-support) is needed for the
tool-calling paths to emit `tool_calls`.

## Running Tests

```bash
cd integration-test
clojure -X:test

# Progress reporting
clojure -X:test :report progress

# Skip / include specific test files
clojure -X:test :exclude '[streaming_test]'
clojure -X:test :only io.kosong.adk.integration.agent-test
```

## Test Categories

| Test File | Description |
|-----------|-------------|
| `setup.clj` | Registers the catch-all LLM factory on load (not a test) |
| `session_test.clj` | Session CRUD - lifecycle, state persistence, isolation |
| `agent_test.clj` | Agent construction, tool calling, multi-agent delegation, loop agents |
| `callback_test.clj` | All callback hooks with real LLM - ordering, error handling |
| `streaming_test.clj` | SSE and bidirectional streaming construction paths |
| `plugin_test.clj` | PluginManager integration - registration, lifecycle |

## Adding Tests

1. Create a new test file in `src/test/clojure/io/kosong/adk/integration/`
2. Require `[io.kosong.adk.integration.setup]` so the LLM factory is registered
3. Use `adk/run` with any `:model` string - the catch-all factory serves the
   configured real model

> **Note:** `adk/run` consumes a core.async channel; force the sequence with
> `doall`/`dorun` before asserting on side effects (e.g. callback logs).
