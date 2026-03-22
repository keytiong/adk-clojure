(ns build
  (:require [clojure.tools.build.api :as b]
            [clojure.java.io :as io]))

(def lib 'io.kosong.adk/adk-clojure)
(def version (format "0.1.0-SNAPSHOT"))
(def class-dir "target/classes")
(def jar-file (format "target/%s-%s.jar" (name lib) version))

;; delay to defer side effects (artifact downloads)
(def basis (delay (b/create-basis {:project "deps.edn"})))

(defn clean [_]
  (b/delete {:path "target"}))

(defn compile-java [_]
  (b/javac {:src-dirs   ["src/main/java"]
            :class-dir  class-dir
            :basis      @basis
            :javac-opts ["--release" "17" "-proc:none"]}))

(defn jar [_]
  (compile-java nil)
  (b/write-pom {:class-dir class-dir
                :lib       lib
                :version   version
                :basis     @basis
                :src-dirs  ["src/main/clojure"]
                :pom-data  [[:url "https://github.com/keytiong/adk-clojure"]
                            [:scm
                             [:url "https://github.com/keytiong/adk-clojure"]]
                            [:licenses
                             [:license
                              [:name "The MIT License"]
                              [:url "https://opensource.org/license/MIT"]]]]})
  (b/copy-dir {:src-dirs   ["src/main/clojure" "resources"]
               :target-dir class-dir})
  (b/jar {:class-dir class-dir
          :jar-file  jar-file}))

(defn generate-types
  "Generate static Clojure source for AutoValue types in com.google.genai.types.
  The generated file (generated_types.clj) is committed to the repository.
  Re-run this task when the google-genai dependency version changes in deps.edn.
  Run with: clojure -T:build generate-types"
  [_]
  (compile-java nil)
  (let [generate-basis (b/create-basis {:project "deps.edn" :aliases [:generate]})
        cmd            (b/java-command
                        {:basis      generate-basis
                         :main       'clojure.main
                         :main-args  ["-e"
                                      (pr-str
                                       '(do
                                          (require 'io.kosong.autovalue)
                                          (io.kosong.autovalue/generate-source-file
                                           "com.google.genai.types"
                                           'io.kosong.adk.generated-types
                                           "src/main/clojure/io/kosong/adk/generated_types.clj")))]})]
    (b/process cmd)))