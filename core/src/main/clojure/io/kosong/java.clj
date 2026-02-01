(ns io.kosong.java)

(defmulti
  make-object
  "Returns an instance of a Java class with the data"
  (fn [class data] class))

(defn make-object-able?
  [cls]
  (some? (.getMethod make-object cls)))
