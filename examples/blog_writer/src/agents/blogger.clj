(ns agents.blogger
  (:require [clojure.java.io :as io]
            [io.kosong.adk.core :as adk]
            [io.kosong.adk.protocols :as p])
  (:import (com.google.adk.agents LoopAgent)
           (com.google.adk.tools GoogleSearchTool)
           (java.util List)))

(defn template
  [name]
  (slurp (io/resource name)))

(defn analyze-codebase
  "Analyses the codebase in the given directory"
  [^{:schema {:type "STRING"}} directory]
  (let [dir (io/file directory)
        ^StringBuffer sb (StringBuffer.)]
    (doseq [f (file-seq dir)]
      (if (.isFile f)
        (.append sb (str "- **" (.name f) "**:"))
        (.append sb (slurp f))))
    {:codebase_content (.toString sb)}))

(defn save-blog-post-to-file
  "Saves the blog post to a file"
  [^{:schema {:type "STRING"}} blog-post
   ^{:schema {:type "STRING"}} file]
  (try
    (spit file blog-post)
    (catch Exception e
      {:status "error"
       :error-message (.getMessage e)}))
  {:status "success"})

(defn suppress-output-callback
  [callback-context]
  {:role  "model"
   :parts []}
  )

(def blog-post-validation-checker
  (adk/base-agent
    :name "blog_post_validation_checker"
    :description ""
    :run-async-fn (fn [context]
                    (let [invocation-id (:invocation-id context)
                          author        (-> context :agent (.name))
                          id            (str (random-uuid))]
                      (if (some? (get-in context [:session :state "blog_post"]))
                        {:invocation-id invocation-id
                         :author        author
                         :id            id
                         :actions       {:escalate true}}
                        {:invocation-id invocation-id
                         :author        author
                         :id            id})))))

(def outline-validation-checker
  (adk/base-agent
    :name "outline_validation_checker"
    :description ""
    :run-async-fn (fn [context]
                    (let [invocation-id (:invocation-id context)
                          author        (-> context :agent (.name))
                          id            (str (random-uuid))]
                      (if (some? (get-in context [:session :state "blog_outline"]))
                        {:invocation-id invocation-id
                         :author        author
                         :id            id
                         :actions       {:escalate true}}
                        {:invocation-id invocation-id
                         :author        author
                         :id            id})))))

(def blog-writer
  (adk/llm-agent
    :name "blog_writer"
    :model "gemini-2.5-pro"
    :description "Writes a technical blog post"
    :instruction " You are an expert technical writer, crafting articles for a sophisticated audience similar to
    that of 'Towards Data Science' and 'freeCodeCamp'.

    Your task is to write a high-quality, in-depth technical blog post based on the provided outline and codebase summary.
    The article must be well-written, authoritative, and engaging for a technical audience.
    - Assume your readers are familiar with programming concepts and software development.
    - Dive deep into the technical details. Explain the 'how' and 'why' behind the code.
    - Use code snippets extensively to illustrate your points.
    - Use Google Search to find relevant information and examples to support your writing.
    - The codebase context will be available in the `codebase_context` state key.
    The final output must be a complete blog post in Markdown format. Do not wrap the output in a code block."
    :tools [(GoogleSearchTool.)]
    :output-key "blog_post"
    :after-agent-callback suppress-output-callback))

(def robust-blog-writer
  (adk/loop-agent
    :name "robust_blog_writer"
    :description "A robust blog writer that retries if it fails."
    :sub-agents [blog-writer blog-post-validation-checker]
    :max-iterations 3))

(def blog-planner
  (adk/llm-agent
    :name "blog_planner"
    :model "gemini-2.5-flash"
    :description "Generates a blog post outline."
    :instruction "
        You are a technical content strategist. Your job is to create a blog post outline.
        The outline should be well-structured and easy to follow.
        It should include a title, an introduction, a main body with several sections, and a conclusion.
        If a codebase is provided, the outline should include sections for code snippets and technical deep dives.
        The codebase context will be available in the `codebase_context` state key.
        Use the information in the `codebase_context` to generate a specific and accurate outline.
        Use Google Search to find relevant information and examples to support your writing.
        Your final output should be a blog post outline in Markdown format.
    "
    :tools [(GoogleSearchTool.)]
    :output-key "blog_outline"
    :after-agent-callback suppress-output-callback
    ))

(def robust-blog-planner
  (adk/loop-agent
    :name "robust_blog_planner"
    :sub-agents [blog-planner outline-validation-checker]
    :max-iterations 3
    :after-agent-callback suppress-output-callback))

(def blog-editor
  (adk/llm-agent
    :name "blog_editor"
    :model "gemini-2.5-pro"
    :description "Edits a technical blog post based on user feedback."
    :instruction "
    You are a professional technical editor. You will be given a blog post and user feedback.
    Your task is to edit the blog post based on the provided feedback.
    The final output should be a revised blog post in Markdown format.
    "
    :output-key "blog_post"
    :after-agent-callback suppress-output-callback))

(def social-media-writer
  (adk/llm-agent
    :name "social_media_writer"
    :model "gemini-2.5-pro"
    :description "Writes social media posts to promote the blog post"
    :instruction "
    You are a social media marketing expert. You will be given a blog post, and your task is to write social media posts for the following platforms:
    - Twitter: A short, engaging tweet that summarizes the blog post and includes relevant hashtags.
    - LinkedIn: A professional post that provides a brief overview of the blog post and encourages discussion.

    The final output should be a markdown-formatted string with the following sections:

    ### Twitter Post

    ```
    <twitter_post_content>
    ```

    ### LinkedIn Post

    ```
    <linkedin_post_content>
    ```
    "
    :output-key "social_media_posts"))

(def interactive-blogger
  (adk/llm-agent
    :name "interactive_blogger_agent"
    :model "gemini-2.5-flash"
    :description "The primary technical blogging assistant. It collaborates with the user to create a blog post."
    :instruction (template "templates/interactive_blogger.md")
    :sub-agents [robust-blog-writer robust-blog-planner blog-editor social-media-writer]
    :tools [#'save-blog-post-to-file #'analyze-codebase]
    :output-key "blog_outline"))