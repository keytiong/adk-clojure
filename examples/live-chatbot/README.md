# Live Chatbot Example

This example demonstrates the use of ADK Clojure's `run-live` API with WebSocket support for bidirectional live streaming between clients and agents.

## Features

- Real-time bidirectional communication via WebSockets
- Support for both text messages and audio blobs
- Connection management with limits (10 per user, 100 per app)
- Automatic session management

## Requirements

- Java 17+
- Clojure 1.12.3
- Google API Key with Gemini API access

## Setup

1. Set your Google API key:
```bash
export GOOGLE_API_KEY=your-api-key-here
```

2. Run the web server:
```bash
clojure -X:adk-web
```

The server will start on http://localhost:8080

## WebSocket API

### Connection

Connect to the WebSocket endpoint:
```
ws://localhost:8080/run_live?app_name=live-chatbot&user_id=user123&session_id=session456
```

**Query Parameters:**
- `app_name`: The agent name (must be "live-chatbot" for this example)
- `user_id`: Your user identifier (any string)
- `session_id`: Session identifier (any string, used to maintain conversation history)

### Message Protocol

#### Client → Server (JSON)

**Send text message:**
```json
{"content": {"role": "user", "parts": [{"text": "Hello, how are you?"}]}}
```

**Send audio blob:**
```json
{
  "blob": {
    "mimeType": "audio/pcm",
    "data": "base64-encoded-audio-data..."
  }
}
```

**Close connection:**
```json
{"close": true}
```

#### Server → Client (JSON)

The server sends ADK Event objects as JSON:
```json
{
  "id": "event-id",
  "author": "live-chatbot",
  "content": {
    "role": "model",
    "parts": [
      {"text": "I'm doing well, thanks for asking!"}
    ]
  }
}
```

## Testing with JavaScript

You can test the WebSocket connection using browser JavaScript:

```javascript
// Connect to WebSocket
const ws = new WebSocket('ws://localhost:8080/run_live?app_name=live-chatbot&user_id=user123&session_id=session456');

// Handle connection open
ws.onopen = () => {
  console.log('WebSocket connected');

  // Send a message
  ws.send(JSON.stringify({
    content: {
      role: "user",
      parts: [{text: "Hello!"}]
    }
  }));
};

// Handle incoming messages
ws.onmessage = (event) => {
  const data = JSON.parse(event.data);
  console.log('Received event:', data);

  // Extract response text
  if (data.content && data.content.parts) {
    const textPart = data.content.parts.find(p => p.text);
    if (textPart) {
      console.log('Agent says:', textPart.text);
    }
  }
};

// Handle errors
ws.onerror = (error) => {
  console.error('WebSocket error:', error);
};

// Handle connection close
ws.onclose = (event) => {
  console.log('WebSocket closed:', event.code, event.reason);
};

// Close connection
// ws.send(JSON.stringify({close: true}));
```

## Testing with Clojure

You can also test using Clojure with the core.async channels directly:

```clojure
(require '[io.kosong.adk.core :as adk])
(require '[clojure.core.async :as async])

;; Create context
(def context (adk/agent-context
               :app-name "live-chatbot"
               :user-id "user123"))

;; Load agent
(require 'agents.live-chatbot)
(def agent agents.live-chatbot/live-chatbot)

;; Start live session
(def {:keys [event-ch request-ch]}
  (adk/run-live context agent))

;; Send a message
(async/>!! request-ch {:content "Hello!"})

;; Read response
(def event (async/<!! event-ch))
(println "Response:" (get-in event [:content :parts 0 :text]))

;; Send another message
(async/>!! request-ch {:content "Tell me a joke"})

;; Close connection
(async/>!! request-ch {:close true})
```

## Connection Limits

The server enforces the following limits:
- Maximum 10 concurrent connections per user
- Maximum 100 concurrent connections per app

When limits are exceeded, the connection is rejected with status code 429.

## Error Handling

The WebSocket handler provides detailed error responses:
- **400 Bad Request**: Missing required query parameters
- **404 Not Found**: Agent not found
- **429 Too Many Requests**: Connection limits exceeded
- **JSON Errors**: Invalid JSON is reported via error event, connection stays open

## Audio Blob Format

Audio blobs should be base64-encoded with the appropriate MIME type:

```json
{
  "blob": {
    "mimeType": "audio/pcm",
    "data": "AAAAAAAAAAAAAAAA..."
  }
}
```

Common audio MIME types:
- `audio/pcm` - PCM audio (raw)
- `audio/wav` - WAV format
- `audio/mpeg` - MP3 format

## Architecture

The implementation follows these key patterns:

1. **Core Library (`adk-clojure`)**:
   - `run-live` function returns dual channels (event-ch, request-ch)
   - LiveRequestQueue bridges core.async → Java RxJava

2. **Dev Library (`adk-clojure-dev`)**:
   - http-kit WebSocket handler at `/run_live`
   - Bidirectional forwarding between WebSocket and channels
   - Connection tracking and limits

3. **Event Flow**:
   ```
   Client WebSocket ↔ http-kit ↔ request-ch → run-live → LLM
                                 event-ch ← run-live ← LLM
   ```

## Next Steps

- Add audio file upload/download examples
- Implement voice-to-text transcription
- Add authentication middleware
- Implement OpenTelemetry tracing
- Add load testing examples
