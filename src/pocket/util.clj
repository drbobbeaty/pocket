(ns pocket.util
  "Simple utility functions for all aspects of the project."
  (:require [cheshire.core :as json]
            [clojure.string :as cs]
            [clojure.tools.logging :refer [error info infof]]
            [clojure.math.numeric-tower :as math]))

(defn to-2dp
  "Function to return the value rounded to two decimal places - if it's a
  numeric value. If not, it returns what was passed in - unaltered."
  [x]
  (cond
    (number? x) (/ (int (* 100.0 x)) 100.0)
    :else x))

(defn to-4dp
  "Function to return the value rounded to four decimal places - if it's a
  numeric value. If not, it returns what was passed in - unaltered."
  [x]
  (cond
    (number? x) (/ (int (* 10000.0 x)) 10000.0)
    :else x))

(defn load-json
  "Function to load the JSON file from the `resources` directory in this
  peoject. This is just a simple way to load up the JSON into a clojure
  structure where the keys will be mapped to keywords in clojure. This
  just makes the rest of the code a lot cleaner."
  [fname]
  (json/parse-stream (clojure.java.io/reader (str "resources/" fname)) true))
