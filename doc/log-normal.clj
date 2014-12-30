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
(ns enigmatic-desert
  (:require [gorilla-plot.core :as plot]
            [pocket.base :as pb]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def moo (take 10000 (pb/log-normal 3 1)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;enigmatic-desert/moo</span>","value":"#'enigmatic-desert/moo"}
;; <=

;; @@
(plot/histogram moo)
;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"99a941a2-2f0c-4b3a-b6d8-d9396b1108dc","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"99a941a2-2f0c-4b3a-b6d8-d9396b1108dc","field":"data.y"}}],"marks":[{"type":"line","from":{"data":"99a941a2-2f0c-4b3a-b6d8-d9396b1108dc"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"data":[{"name":"99a941a2-2f0c-4b3a-b6d8-d9396b1108dc","values":[{"x":1.3171682215520413,"y":0},{"x":19.244753637164173,"y":4814.0},{"x":37.17233905277631,"y":3430.0},{"x":55.09992446838844,"y":1115.0},{"x":73.02750988400057,"y":393.0},{"x":90.9550952996127,"y":135.0},{"x":108.88268071522482,"y":69.0},{"x":126.81026613083695,"y":20.0},{"x":144.7378515464491,"y":14.0},{"x":162.66543696206122,"y":1.0},{"x":180.59302237767335,"y":5.0},{"x":198.52060779328548,"y":1.0},{"x":216.4481932088976,"y":0.0},{"x":234.37577862450973,"y":1.0},{"x":252.30336404012186,"y":1.0},{"x":270.230949455734,"y":1.0},{"x":288.15853487134615,"y":0}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"99a941a2-2f0c-4b3a-b6d8-d9396b1108dc\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"99a941a2-2f0c-4b3a-b6d8-d9396b1108dc\", :field \"data.y\"}}], :marks [{:type \"line\", :from {:data \"99a941a2-2f0c-4b3a-b6d8-d9396b1108dc\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :data [{:name \"99a941a2-2f0c-4b3a-b6d8-d9396b1108dc\", :values ({:x 1.3171682215520413, :y 0} {:x 19.244753637164173, :y 4814.0} {:x 37.17233905277631, :y 3430.0} {:x 55.09992446838844, :y 1115.0} {:x 73.02750988400057, :y 393.0} {:x 90.9550952996127, :y 135.0} {:x 108.88268071522482, :y 69.0} {:x 126.81026613083695, :y 20.0} {:x 144.7378515464491, :y 14.0} {:x 162.66543696206122, :y 1.0} {:x 180.59302237767335, :y 5.0} {:x 198.52060779328548, :y 1.0} {:x 216.4481932088976, :y 0.0} {:x 234.37577862450973, :y 1.0} {:x 252.30336404012186, :y 1.0} {:x 270.230949455734, :y 1.0} {:x 288.15853487134615, :y 0})}], :width 400, :height 247.2188, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@

;; @@
