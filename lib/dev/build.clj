(ns build
  (:require [clojure.tools.build.api :as b]))

(def lib 'io.kosong.adk/adk-clojure-dev)
(def version (format "0.1.0-SNAPSHOT"))
(def class-dir "target/classes")
(def jar-file (format "target/%s-%s.jar" (name lib) version))

;; delay to defer side effects (artifact downloads)
(def basis (delay (b/create-basis {:project "deps.edn"})))

(defn clean [_]
      (b/delete {:path "target"}))

(defn jar [_]
      (b/write-pom {:class-dir class-dir
                    :lib       lib
                    :version   version
                    :basis     @basis
                    :src-dirs  ["src/main/clojure"]})
      (b/copy-dir {:src-dirs   ["src/main/clojure" "src/main/resources"]
                   :target-dir class-dir})
      (b/jar {:class-dir class-dir
              :jar-file  jar-file}))
