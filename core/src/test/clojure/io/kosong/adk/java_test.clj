(ns io.kosong.adk.java-test
  "Unit tests for `io.kosong.java/make-object` multimethod and
  `io.kosong.java/make-object-able?`."
  (:require
   [clojure.test :refer [deftest testing is]]
   [io.kosong.adk.generated-types] ;; registers all defmethods for GenAI types
   [io.kosong.java :as java]))

;; ---------------------------------------------------------------------------
;; Tests
;; ---------------------------------------------------------------------------

(deftest make-object-unregistered-throws
  (testing "`make-object` with no defmethod throws `IllegalArgumentException`"
    (is (thrown? IllegalArgumentException
                 (java/make-object String "hello")))))

(deftest make-object-able-registered-class
  (testing "`make-object-able?` returns true for a registered class"
    (is (java/make-object-able? com.google.genai.types.GenerateContentConfig)
        "GenerateContentConfig has a defmethod")))

(deftest make-object-able-unregistered-class
  (testing "`make-object-able?` returns false for an unregistered class"
    (is (not (java/make-object-able? String))
        "String has no defmethod")
    (is (not (java/make-object-able? Integer))
        "Integer has no defmethod")))
