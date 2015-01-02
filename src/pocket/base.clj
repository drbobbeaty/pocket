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

(defn log-normal
  "Simple log normal distribution with the provided mean and standard deviation.
  If no values are supplied, then the default values are 0 and 1. These are
  double precision numbers in the sequence."
  [& [mean stdev]]
  (let [xb (or mean 10)
        ss (or stdev 1)]
    (for [x (normal)]
      (Math/exp (+ xb (* ss x))))))

(defn exponential
  "Simple exponential distribution with the provided lambda (rate parameter).
  In this case, there *must* be a value for the parameter - and it *must* be
  positive. These are double precision numbers in the sequence."
  [lambda]
  (if (pos? lambda)
    (for [x (uniform)]
      (/ (Math/log (- 1 x)) (* -1 lambda)))))

(defn mapped
  "Function to map a given random variable generator into another random
  variable based on the `coverage` map provided. Each of the values coming
  out of the `dist` random process function has to undergo the mapping where
  the `coverage` is defined as:

    { :foo [0 75]
      :bar [75 100] }

  where the keys are the result values of the mapping, and the values are
  the ranges that the random variable must fall into for the key to be the
  resultant."
  [dist cov]
  (if (and dist cov)
    (let [mush (fn [x] (some identity (for [[k [l h]] cov] (if (and (<= l x) (< x h)) k))))]
      (map mush dist))))

(defn pass-fail
  "Function to return either `:pass` or `:fail` based on the uniform
  probability function where the argument is the fraction (0 - 1) that
  should be assigned to getting `:pass`. This can be used to get the
  pass/fail on the stage transitions, and is easily tuned to get just
  the probability desired."
  [pass]
  (let [bp (math/round (* pass 100.0))
        cov { :pass [0 bp], :fail [bp 100]}
        und (uniform 0 100)]
    (mapped und cov)))

(defn mk-var
  "Function to take a configuration map and return the appropriate sequence
  for the configuration passed in. The configuration is dependent on the
  type of distribution, but they all have a `:distribution` tag of the name
  of the distribution, and then based on that, there might be other parameters
  that the generator needs."
  [cfg]
  (case (:distribution cfg)
    "uniform"     (uniform (or (:min cfg) 0) (or (:max cfg) 1))
    "normal"      (normal (or (:mean cfg) 0) (or (:stdev cfg) 1))
    "log-normal"  (log-normal (or (:mean cfg) 0) (or (:stdev cfg) 1))
    "exponential" (exponential (or (:lambda cfg) 1))
    "mapped"      (mapped (mk-var (:underlying cfg)) (:coverage cfg))
    "pass-fail"   (pass-fail (or (:pass cfg) 0.50))
    nil))
