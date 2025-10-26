(ns build
  (:require [clojure.tools.build.api :as b]))

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
                    :src-dirs  ["src/main/clojure"]})
      (b/copy-dir {:src-dirs   ["src/main/clojure" "resources"]
                   :target-dir class-dir})
      (b/jar {:class-dir class-dir
              :jar-file  jar-file}))