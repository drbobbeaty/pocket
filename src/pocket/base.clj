(ns pocket.base
  "Base random variable generators for the project. The point is that these
  functions - like `range` can produce a sequence of random variables of the
  described probability density function, which can then be used in any number
  of ways to generate ever more complex random sequences for source material."
  (:require [clojure.tools.cli :refer [cli]]
            [clojure.tools.logging :refer [error info infof]]
            [clojure.math.numeric-tower :as math]))

(defn uniform
  "Simple uniform distribution between the low value (inclusive) and the high
  value (exclusive). If no values are supplied, then the default values are 0
  and 1. These are double precision numbers in the sequence."
  [& [lo hi]]
  (let [os (or lo 0)
        r (- (or hi 1) os)
        go (fn [x] (+ (rand r) os))]
    (iterate go (go 0))))

(defn normal
  "Simple normal distribution with the provided mean and standard deviation.
  If no values are supplied, then the default values are 0 and 1. These are
  double precision numbers in the sequence."
  [& [mean stdev]]
  (let [xb (or mean 0)
        ss (or stdev 1)]
    (flatten (for [[u v] (partition 2 (uniform -1 1))
                   :let [w (+ (* u u) (* v v))]
                   :when (< w 1)
                   :let [a (Math/sqrt (/ (* -2 (Math/log10 w)) w))]]
               [(+ (* u a ss) xb) (+ (* v a ss) xb)]))))
