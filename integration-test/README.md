# Integration Tests

Integration tests for adk-clojure that verify end-to-end agent behavior using a real LLM.

## LLM Setup

### Primary: Google Vertex AI
Integration tests prefer Google Vertex AI as the primary LLM. Set up credentials:

```bash
export GOOGLE_CLOUD_PROJECT="your-project-id"
export GOOGLE_CLOUD_LOCATION="us-central1"
export GOOGLE_APPLICATION_CREDENTIALS="/path/to/service-account.json"
export VERTEX_AI_MODEL="gemini-1.5-pro"  # or another Vertex AI model
```

### Fallback: Ollama
Tests fall back to Ollama if Vertex AI credentials are not available.

```bash
# Start Ollama with a model
ollama pull qwen3:8b

# Default URL: http://localhost:11434/v1
```

## Running Tests

```bash
cd integration-test
clojure -X:test

# Run with verbose output
clojure -X:test :verbose true

# Run specific test file
clojure -A:test :exclude '[agent_test callback_test]'
```

## Test Categories

| Test File | Description |
|-----------|-------------|
| `session_test.clj` | Session CRUD with real LLM - lifecycle, state persistence, isolation |
| `agent_test.clj` | Agent execution, tool calling, multi-agent delegation, loop agents |
| `callback_test.clj` | All callback hooks with real LLM - ordering, error handling |
| `streaming_test.clj` | SSE and WebSocket streaming - partial responses, channel hygiene |
| `plugin_test.clj` | PluginManager integration - registration, lifecycle, execution |

## Test Configuration

Tests are configured via `test-runner` config:

```bash
# Run all tests
clojure -X:test

# Skip specific test files
clojure -X:test :exclude '[session_test]'

# Include only specific test files
clojure -X:test :include '[agent_test]'
```

## Environment Variables

| Variable | Required | Description |
|----------|----------|-------------|
| `GOOGLE_CLOUD_PROJECT` | Optional | Vertex AI project ID |
| `GOOGLE_CLOUD_LOCATION` | Optional | Vertex AI location (default: us-central1) |
| `GOOGLE_APPLICATION_CREDENTIALS` | Optional | Path to service account JSON |
| `VERTEX_AI_MODEL` | Optional | Vertex AI model name (default: gemini-1.5-pro) |
| `OLLAMA_URL` | Optional | Ollama API URL (default: http://localhost:11434/v1) |
| `OLLAMA_MODEL` | Optional | Ollama model name (default: qwen3:8b) |
| `LLM_BACKEND` | Optional | Force "vertex" or "ollama" backend |

## Adding Tests

1. Create a new test file in `src/test/clojure/io/kosong/adk/integration/`
2. Use the `cognitect.test-runner.api` API for test definitions
3. Tests are discovered automatically based on the `:dirs` config

Example test:

```clojure
(ns io.kosong.adk.integration.new-test
  (:require [clojure.test :refer [deftest is testing]]
            [io.kosong.adk.core :as adk]))

(deftest my-integration-test
  (testing "test description"
    (is (= expected actual))))
```
