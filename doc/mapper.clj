;; gorilla-repl.fileformat = 1

;; **
;;; # Gorilla REPL
;;; 
;;; Welcome to gorilla :-)
;;; 
;;; Shift + enter evaluates code. Hit ctrl+g twice in quick succession or click the menu icon (upper-right corner) for more commands ...
;;; 
;;; It's a good habit to run each worksheet in its own namespace: feel free to use the declaration we've provided below if you'd like.
;; **

;; @@
(ns serene-thorns
  (:require [gorilla-plot.core :as plot]
            [pocket.base :as pb]
            [pocket.gens :as pg]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
;; define a uniform random variable from 0 to 100
(def und (pb/uniform 0 100))

;; define the mapping of the output
(def cov {:heads [0 55], :tails [55 100]})

;; now define the mapped random sequence
(def bad-penny (pb/mapped und cov))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;serene-thorns/bad-penny</span>","value":"#'serene-thorns/bad-penny"}
;; <=

;; @@
(take 5 bad-penny)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:tails</span>","value":":tails"},{"type":"html","content":"<span class='clj-keyword'>:heads</span>","value":":heads"},{"type":"html","content":"<span class='clj-keyword'>:tails</span>","value":":tails"},{"type":"html","content":"<span class='clj-keyword'>:heads</span>","value":":heads"},{"type":"html","content":"<span class='clj-keyword'>:heads</span>","value":":heads"}],"value":"(:tails :heads :tails :heads :heads)"}
;; <=

;; @@
(def fr (frequencies (take 10000 bad-penny)))
fr
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:tails</span>","value":":tails"},{"type":"html","content":"<span class='clj-long'>4583</span>","value":"4583"}],"value":"[:tails 4583]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:heads</span>","value":":heads"},{"type":"html","content":"<span class='clj-long'>5417</span>","value":"5417"}],"value":"[:heads 5417]"}],"value":"{:tails 4583, :heads 5417}"}
;; <=

;; @@
(plot/bar-chart (keys fr) (vals fr))
;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"ordinal","range":"width","domain":{"data":"acfc4e39-146d-47ce-b03c-7236bcfd5d6e","field":"data.x"}},{"name":"y","range":"height","nice":true,"domain":{"data":"acfc4e39-146d-47ce-b03c-7236bcfd5d6e","field":"data.y"}}],"marks":[{"type":"rect","from":{"data":"acfc4e39-146d-47ce-b03c-7236bcfd5d6e"},"properties":{"enter":{"y":{"scale":"y","field":"data.y"},"width":{"offset":-1,"scale":"x","band":true},"x":{"scale":"x","field":"data.x"},"y2":{"scale":"y","value":0}},"update":{"fill":{"value":"steelblue"},"opacity":{"value":1}},"hover":{"fill":{"value":"#FF29D2"}}}}],"data":[{"name":"acfc4e39-146d-47ce-b03c-7236bcfd5d6e","values":[{"x":"tails","y":4583},{"x":"heads","y":5417}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"ordinal\", :range \"width\", :domain {:data \"acfc4e39-146d-47ce-b03c-7236bcfd5d6e\", :field \"data.x\"}} {:name \"y\", :range \"height\", :nice true, :domain {:data \"acfc4e39-146d-47ce-b03c-7236bcfd5d6e\", :field \"data.y\"}}], :marks [{:type \"rect\", :from {:data \"acfc4e39-146d-47ce-b03c-7236bcfd5d6e\"}, :properties {:enter {:y {:scale \"y\", :field \"data.y\"}, :width {:offset -1, :scale \"x\", :band true}, :x {:scale \"x\", :field \"data.x\"}, :y2 {:scale \"y\", :value 0}}, :update {:fill {:value \"steelblue\"}, :opacity {:value 1}}, :hover {:fill {:value \"#FF29D2\"}}}}], :data [{:name \"acfc4e39-146d-47ce-b03c-7236bcfd5d6e\", :values ({:x :tails, :y 4583} {:x :heads, :y 5417})}], :width 400, :height 247.2188, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@

;; @@
