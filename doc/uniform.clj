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
(ns snowy-volcano
  (:require [gorilla-plot.core :as plot]
            [pocket.base :as pb]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def goop (take 5000 (pb/uniform -1 1)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;snowy-volcano/goop</span>","value":"#'snowy-volcano/goop"}
;; <=

;; @@
(apply min goop)
(apply max goop)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-double'>0.9984002145316362</span>","value":"0.9984002145316362"}
;; <=

;; @@
(plot/histogram goop)
;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"d96936ef-d3f1-411d-a0c4-a97d9c0edcf3","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"d96936ef-d3f1-411d-a0c4-a97d9c0edcf3","field":"data.y"}}],"marks":[{"type":"line","from":{"data":"d96936ef-d3f1-411d-a0c4-a97d9c0edcf3"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"data":[{"name":"d96936ef-d3f1-411d-a0c4-a97d9c0edcf3","values":[{"x":-0.9996065648030606,"y":0},{"x":-0.8568917948505823,"y":352.0},{"x":-0.714177024898104,"y":379.0},{"x":-0.5714622549456256,"y":346.0},{"x":-0.4287474849931473,"y":374.0},{"x":-0.28603271504066896,"y":357.0},{"x":-0.1433179450881906,"y":378.0},{"x":-6.031751357122306E-4,"y":336.0},{"x":0.14211159481676613,"y":347.0},{"x":0.2848263647692445,"y":357.0},{"x":0.42754113472172284,"y":350.0},{"x":0.5702559046742012,"y":380.0},{"x":0.7129706746266795,"y":365.0},{"x":0.8556854445791579,"y":336.0},{"x":0.9984002145316362,"y":343.0},{"x":1.1411149844841146,"y":0}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"d96936ef-d3f1-411d-a0c4-a97d9c0edcf3\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"d96936ef-d3f1-411d-a0c4-a97d9c0edcf3\", :field \"data.y\"}}], :marks [{:type \"line\", :from {:data \"d96936ef-d3f1-411d-a0c4-a97d9c0edcf3\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :data [{:name \"d96936ef-d3f1-411d-a0c4-a97d9c0edcf3\", :values ({:x -0.9996065648030606, :y 0} {:x -0.8568917948505823, :y 352.0} {:x -0.714177024898104, :y 379.0} {:x -0.5714622549456256, :y 346.0} {:x -0.4287474849931473, :y 374.0} {:x -0.28603271504066896, :y 357.0} {:x -0.1433179450881906, :y 378.0} {:x -6.031751357122306E-4, :y 336.0} {:x 0.14211159481676613, :y 347.0} {:x 0.2848263647692445, :y 357.0} {:x 0.42754113472172284, :y 350.0} {:x 0.5702559046742012, :y 380.0} {:x 0.7129706746266795, :y 365.0} {:x 0.8556854445791579, :y 336.0} {:x 0.9984002145316362, :y 343.0} {:x 1.1411149844841146, :y 0})}], :width 400, :height 247.2188, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@

;; @@
