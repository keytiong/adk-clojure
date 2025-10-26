# Simple Chatbot

## Overview

This example showcase a simple chatbot. It uses a local LLM served from Docker Desktop via
`google-adk-langchain4j` and `langchain4j-openai` 

## Run

Ensure a local LLM is setup
- Docker Desktop is running
- Docker Model Runner is enabled with host-side TCP support listening on port 12434
- Install model `ai/qwen3` from Docker Hub

```bash
cd examples/chatbot

clojure -A:adk-web
```
Access ADK web on http://localhost:8080