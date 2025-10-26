(ns io.kosong.adk.web.routes
  (:require [io.kosong.adk.web.handlers :as h]
            [reitit.ring]
            [ring.util.response]))

(defn ->routes
  [app-context]
  [
   [""
    (ring.util.response/redirect "/")]

   ["/"
    (constantly (ring.util.response/redirect "/index.html"))]

   ["/index.html"
    h/index-html]

   ["/list-apps"
    (partial h/list-apps app-context)]

   ["/apps/:app-name/users/:user-id/sessions"
    {:post {:handler    (partial h/create-session app-context)
            :parameters {:path {:app-name [:string]
                                :user-id  [:string]}
                         :body [:maybe [:map-of :string :any]]}}
     :get  {:parameters {:path {:app-name [:string]
                                :user-id  [:string]}}
            :handler    (partial h/list-sessions app-context)}}]

   ["/apps/:app-name/users/:user-id/sessions/:session-id"
    {:get    {:handler (partial h/get-session app-context)}
     :delete {:handler (partial h/delete-session app-context)}}]

   ["/run_sse"
    {:post {:interceptors [(h/run-sse-interceptor app-context)]}}]

   ["/debug/trace/:event-id"
    {:get {:handler (partial h/get-trace-by-event app-context)}}]

   ["/debug/trace/session/:session-id"
    {:get {:handler (partial h/get-trace-by-session app-context)}}]

   ["/apps/:app-name/users/:user-id/sessions/:session-id/events/:event-id/graph"
    {:get {:handler (partial h/get-graph app-context)}}]

   ["/apps/:app-name/eval_sets"
    {:get {:handler (partial h/list-eval-sets app-context)}}
    ]

   ["/apps/:app-name/eval_results"
    {:get {:handler (partial h/list-eval-results app-context)}}]
   ])