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
(ns voiceless-lake
  (:require [gorilla-plot.core :as plot]
            [pocket.base :as pb]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def hop (take 10000 (pb/exponential 5)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;voiceless-lake/hop</span>","value":"#'voiceless-lake/hop"}
;; <=

;; @@
(plot/histogram hop)
;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"5ae9da86-88c3-476c-8ff2-bc3916b3cecc","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"5ae9da86-88c3-476c-8ff2-bc3916b3cecc","field":"data.y"}}],"marks":[{"type":"line","from":{"data":"5ae9da86-88c3-476c-8ff2-bc3916b3cecc"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"data":[{"name":"5ae9da86-88c3-476c-8ff2-bc3916b3cecc","values":[{"x":1.5083955451522524E-5,"y":0},{"x":0.20758594327753457,"y":6504.0},{"x":0.4151568025996176,"y":2241.0},{"x":0.6227276619217007,"y":791.0},{"x":0.8302985212437838,"y":304.0},{"x":1.0378693805658668,"y":111.0},{"x":1.2454402398879498,"y":29.0},{"x":1.453011099210033,"y":14.0},{"x":1.660581958532116,"y":2.0},{"x":1.868152817854199,"y":0.0},{"x":2.075723677176282,"y":3.0},{"x":2.283294536498365,"y":0.0},{"x":2.490865395820448,"y":0.0},{"x":2.6984362551425307,"y":0.0},{"x":2.9060071144646136,"y":0.0},{"x":3.1135779737866964,"y":0.0},{"x":3.3211488331087793,"y":1.0},{"x":3.528719692430862,"y":0}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"5ae9da86-88c3-476c-8ff2-bc3916b3cecc\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"5ae9da86-88c3-476c-8ff2-bc3916b3cecc\", :field \"data.y\"}}], :marks [{:type \"line\", :from {:data \"5ae9da86-88c3-476c-8ff2-bc3916b3cecc\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :data [{:name \"5ae9da86-88c3-476c-8ff2-bc3916b3cecc\", :values ({:x 1.5083955451522524E-5, :y 0} {:x 0.20758594327753457, :y 6504.0} {:x 0.4151568025996176, :y 2241.0} {:x 0.6227276619217007, :y 791.0} {:x 0.8302985212437838, :y 304.0} {:x 1.0378693805658668, :y 111.0} {:x 1.2454402398879498, :y 29.0} {:x 1.453011099210033, :y 14.0} {:x 1.660581958532116, :y 2.0} {:x 1.868152817854199, :y 0.0} {:x 2.075723677176282, :y 3.0} {:x 2.283294536498365, :y 0.0} {:x 2.490865395820448, :y 0.0} {:x 2.6984362551425307, :y 0.0} {:x 2.9060071144646136, :y 0.0} {:x 3.1135779737866964, :y 0.0} {:x 3.3211488331087793, :y 1.0} {:x 3.528719692430862, :y 0})}], :width 400, :height 247.2188, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@

;; @@
