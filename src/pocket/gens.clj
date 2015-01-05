(ns pocket.gens
  "Generators for the Pocket project. These are based on the underlying random
  number generators for the different distributions, and then the business logic
  is placed on top of this so that we have business data generators, and the
  distribution is built-into these generators."
  (:require [clojure.string :as cs]
            [clojure.tools.cli :refer [cli]]
            [clojure.tools.logging :refer [error info infof]]
            [clojure.math.numeric-tower :as math]
            [pocket.base :as pb]
            [pocket.util :refer [to-2dp to-4dp]]))

(defn pull-names
  "Function to randomly generate 'n' names from the two CSV files of first
  names and last names. This is for a person generator where we will be
  taking a series of people from this function and then randomly shuffling
  them and making an infinite sequence on that data."
  [n]
  (let [lnames (->> (slurp "resources/last_names.csv") (.split #"\r") (map cs/trim))
        lcnt (count lnames)
        fnames (->> (slurp "resources/first_names.csv") (.split #"\r") (map cs/trim))
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

(defn companies
  "Function to generate an infinite sequence of company names from the
  list in the resources directory. This list will be a uniformly random
  selection where there will be no guarantees about the non-duplication
  of the names in the sequence. This is because for the business data,
  this data can - and should - repeat."
  []
  (let [cos (->> (slurp "resources/companies.csv") (.split #"\n") (map cs/trim))
        cnt (count cos)]
    (repeatedly #(nth cos (rand-int cnt)))))

(defn mk-pipeline
  "Function to take the sales pipeline configuration of the form:

    [{ :name \"First Contact\"
       :success { :distribution \"pass-fail\"
                  :pass 0.75 }
       :duration { :distribution \"normal\"
                   :mean 4
                   :stdev 2 } }
     { :name \"Reach Decision Maker\"
       :success { :distribution \"pass-fail\"
                  :pass 0.50 },
       :duration { :distribution \"normal\"
                   :mean 5
                   :stdev 2 } }
     { :name \"Send Contract\"
       :success { :distribution \"pass-fail\"
                  :pass 0.40 }
       :duration { :distribution \"normal\"
                   :mean 7
                   :stdev 2 } }
     { :name \"Contract Signed\"
       :success { :distribution \"pass-fail\"
                  :pass 0.40 }
       :duration { :distribution \"normal\"
                   :mean 15
                   :stdev 5 } }]

  and return an infinite sequence of deal 'lives' where each step that
  it passes is tagged appropriately, and with the appropriate time it
  took on that step, and the last one will either be the `:pass` on the
  last step, or a `:fail` on any one step - indicating that it was a
  failed deal:

    [{ :name \"First Contact\"
       :disposition :pass
       :duration 3.2 }
     { :name \"Reach Decision Maker\"
       :disposition :pass
       :duration 4.2 }
     { :name \"Send Contract\"
       :disposition :pass
       :duration 2.2 }
     { :name \"Contract Signed\"
       :disposition :pass
       :duration 6.2 }]

  for a complete 'won' deal, and:

    [{ :name \"First Contact\"
       :disposition :pass
       :duration 3.2 }
     { :name \"Reach Decision Maker\"
       :disposition :fail }]

  for a deal that was 'lost'."
  [p-cfg]
  (if (coll? p-cfg)
    (let [stps (map :name p-cfg)
          c-stps (count stps)
          gen (apply map vector
                (map #(map vector (repeat (:name %))
                                  (pb/mk-var (:success %))
                                  (pb/mk-var (:duration %))) p-cfg))]
      (for [life gen]
        (let [ol (vec (for [[n s d] life
                            :while (= :pass s)]
                        { :name n, :disposition s, :duration (to-4dp d) }))
              c-ol (count ol)]
          (if (< c-ol c-stps)
            (conj ol { :name (nth stps c-ol) :disposition :fail })
            ol))))))

(defn deals
  "Function to produce an infinite series of deals of the form:

    { :sales-rep \"Amy Irving\"
      :company \"AAA Travel\"
      :value 502.34
      :pipeline [{ :name \"First Contact\"
                   :disposition :pass
                   :duration 3.2 }
                 { :name \"Reach Decision Maker\"
                   :disposition :pass
                   :duration 4.2 }
                 { :name \"Send Contract\"
                   :disposition :pass
                   :duration 2.2 }
                 { :name \"Contract Signed\"
                   :disposition :pass
                   :duration 6.2 }]
      :duration 15.8
      :disposition :pass }

  for a successful deal (where all stages are passed), and for an
  unsuccessful one:

    { :sales-rep \"Donald Duck\"
      :company \"Jack Daniels\"
      :value 5022.34
      :pipeline [{ :name \"First Contact\"
                   :disposition :pass
                   :duration 3.2 }
                 { :name \"Reach Decision Maker\"
                   :disposition :fail }]
      :duration 3.2
      :disposition :fail }
  "
  [cfg]
  (let [reps (people-shoe (or (:sales_rep_count cfg) 10))
        value (pb/mk-var (:deal_value cfg))
        pipe (mk-pipeline (:pipeline cfg))]
    (for [[r c v p] (map vector reps (companies) value pipe)]
      { :sales-rep r
        :company c
        :value (to-2dp v)
        :pipeline p
        :duration (to-2dp (apply + (filter identity (map :duration p))))
        :disposition (:disposition (last p)) })))

(defn simulate-sales
  "Function to simluate the sales for a mythical tenant based on the config
  profile provided and the number of days to simulate. The tenant config has
  the configuration of what distribution to use for determining the number
  of deals to start on any one day, and then for that day, a full series of
  deals will be run, and the results determined.

  This will continue for every day in the simulation. At the end, the values
  and durations of each 'successful' deal will be tallied, and placed in a
  timeline of 'revenue' like:

  { :sales (sequence of deals by day)
    :revenue ([32.9 139.85] [33.9 139.85] [34.9 139.85] [35.9 139.85]
              [39.9 139.85] [40.9 139.85] [41.9 139.85]) }

  where each tuple is the days since the start of the simulation and the
  value of that revenue. So these can then be added up, plotted, etc. and
  you can see the simulated revenue."
  [cfg & [days]]
  (if (and (map? cfg) (pos? days))
    (let [dpd (pb/mk-var (:deals_per_day cfg))
          dg (partition 10 (deals cfg))
          hist (for [[d n dl] (map vector (range days) dpd dg)
                     :let [sd (take n dl)
                           rev (for [w (filter #(= :pass (:disposition %)) sd)]
                                 [(+ d (:duration w)) (:value w)])]]
                 { :day d
                   :deals sd
                   :revenue rev })]
      { :sales hist
        :revenue (mapcat :revenue hist) })))
