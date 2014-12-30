(ns pocket.base
  "Base random variable generators for the project. The point is that these
  functions - like `range` can produce a sequence of random variables of the
  described probability density function, which can then be used in any number
  of ways to generate ever more complex random sequences for source material."
  (:require [clojure.tools.cli :refer [cli]]
            [clojure.tools.logging :refer [error info infof]]
))

(defn uniform
  "Simple uniform distribution between the low value (inclusive) and the high
  value (exclusive). If no values are supplied, then the default values are 0
  and 1. These are double precision numbers in the sequence."
  [& [lo hi]]
  (let [os (or lo 0)
        r (- (or hi 1) os)
        go (fn [x] (+ (rand r) os))]
    (iterate go (go 0))))

