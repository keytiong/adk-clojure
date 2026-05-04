(ns io.kosong.adk.utils-test
  "Tests for `io.kosong.adk.utils/optional-datafy-assoc`."
  (:require
   [clojure.datafy :refer [datafy]]
   [clojure.test :refer [deftest testing is]]
   [io.kosong.adk.utils :refer [optional-datafy-assoc]])
  (:import
   (java.util ArrayList HashMap HashSet Optional)))

;; ---------------------------------------------------------------------------
;; Helpers
;; ---------------------------------------------------------------------------

(defn- present-opt [v]
  "Wrap `v` in a non-empty Optional."
  (Optional/of v))

(defn- empty-opt []
  "Return an empty Optional."
  ^Optional (Optional/empty))

(def ^:const a-key :a)

;; ---------------------------------------------------------------------------
;; Tests
;; ---------------------------------------------------------------------------

(deftest optional-datafy-assoc-nil-test
  (testing "nil value → key omitted from map"
    (is (= {}
           (optional-datafy-assoc {} a-key nil)))
    (is (= {:existing true}
           (optional-datafy-assoc {:existing true} a-key nil)))))

(deftest optional-datafy-assoc-optional-present-test
  (testing "`Optional` with value → datafied value in map"
    (is (= {a-key "hello"}
           (optional-datafy-assoc {} a-key (present-opt "hello"))))
    (is (= {a-key 42}
           (optional-datafy-assoc {} a-key (present-opt 42))))))

(deftest optional-datafy-assoc-optional-empty-test
  (testing "`Optional` empty → key omitted from map"
    (is (= {}
           (optional-datafy-assoc {} a-key (empty-opt))))
    (is (= {:keep-me true}
           (optional-datafy-assoc {:keep-me true} a-key (empty-opt))))))

(deftest optional-datafy-assoc-list-test
  (testing "`List` → vector of datafied items"
    (let [lst (doto (ArrayList.)
                (.add "one")
                (.add "two")
                (.add "three"))]
      (is (= {a-key ["one" "two" "three"]}
             (optional-datafy-assoc {} a-key lst))))

    (testing "empty list → empty vector"
      (is (= {a-key []}
             (optional-datafy-assoc {} a-key (ArrayList.)))))))

(deftest optional-datafy-assoc-set-test
  (testing "`Set` → set of datafied items"
    (let [s (doto (HashSet.)
              (.add "a")
              (.add "b"))]
      (is (= {a-key #{"a" "b"}}
             (optional-datafy-assoc {} a-key s))))

    (testing "empty set → empty set"
      (is (= {a-key #{}}
             (optional-datafy-assoc {} a-key (HashSet.)))))))

(deftest optional-datafy-assoc-map-test
  (testing "`Map` → PersistentArrayMap"
    (let [m (doto (HashMap.)
              (.put "x" 1)
              (.put "y" 2))]
      (let [result (optional-datafy-assoc {} a-key m)]
        (is (= 1 (count result))
            "result map has 1 key (:a)")
        (is (map? (get result a-key)))
        (is (= 1 (get (get result a-key) "x")))
        (is (= 2 (get (get result a-key) "y")))))))

(deftest optional-datafy-assoc-scalar-test
  (testing "plain scalar → datafied directly"
    (is (= {a-key "text"}
           (optional-datafy-assoc {} a-key "text")))
    (is (= {a-key 123}
           (optional-datafy-assoc {} a-key 123)))
    (is (= {a-key true}
           (optional-datafy-assoc {} a-key true)))
    (is (= {a-key [1 2 3]}
           (optional-datafy-assoc {} a-key [1 2 3])))))
