(ns pocket.test.base
  (:require [clojure.test :refer :all]
            [pocket.base :refer :all]))

(deftest uniform-test
  (let [big (take 10000 (uniform -1 1))]
    (testing "uniform limits"
      (is (<= -1 (apply min big)))
      (is (< (apply max big) 1)))))

