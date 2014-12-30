(ns pocket.gens
  "Generators for the Pocket project. These are based on the underlying random
  number generators for the different distributions, and then the business logic
  is placed on top of this so that we have business data generators, and the
  distribution is built-into these generators."
  (:require [clojure.tools.cli :refer [cli]]
            [clojure.tools.logging :refer [error info infof]]
            [clojure.math.numeric-tower :as math]
            [pocket.base :as pb]))

(defn pull-names
  "Function to randomly generate 'n' names from the two CSV files of first
  names and last names. This is for a person generator where we will be
  taking a series of people from this function and then randomly shuffling
  them and making an infinite sequence on that data."
  [n]
  (let [lnames (->> (slurp "resources/last_names.csv") (.split #"\r"))
        lcnt (count lnames)
        fnames (->> (slurp "resources/first_names.csv") (.split #"\r"))
        fcnt (count fnames)]
    (for [i (range n)]
      (str (nth fnames (rand-int fcnt)) " " (nth lnames (rand-int lcnt))))))

(defn people-shoe
  "Function to take a random set of `n` people from the `pull-names` function
  and then make an infinite equence by shuffling them up over and over so that
  each name will be seen in the sequence *before* any repeats. This is like
  a card show in the casino, where the deck is delt and then a new deck is
  placed in the shoe and delt."
  [n]
  (let [folks (pull-names (or n 10))]
    (flatten (repeatedly (partial shuffle folks)))))
