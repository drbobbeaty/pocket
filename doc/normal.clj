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
(ns grieving-lake
  (:require [gorilla-plot.core :as plot]
            [pocket.base :as pb]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def spoo (take 10000 (pb/normal 5 1)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;grieving-lake/spoo</span>","value":"#'grieving-lake/spoo"}
;; <=

;; @@
(plot/histogram spoo)
;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"deba0cd7-8448-4ee7-a852-43ebba557cf5","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"deba0cd7-8448-4ee7-a852-43ebba557cf5","field":"data.y"}}],"marks":[{"type":"line","from":{"data":"deba0cd7-8448-4ee7-a852-43ebba557cf5"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"data":[{"name":"deba0cd7-8448-4ee7-a852-43ebba557cf5","values":[{"x":2.5434259410621385,"y":0},{"x":2.863738463804393,"y":6.0},{"x":3.184050986546647,"y":22.0},{"x":3.5043635092889014,"y":80.0},{"x":3.8246760320311557,"y":289.0},{"x":4.14498855477341,"y":577.0},{"x":4.465301077515664,"y":1070.0},{"x":4.785613600257919,"y":1694.0},{"x":5.105926123000173,"y":1943.0},{"x":5.426238645742427,"y":1750.0},{"x":5.7465511684846815,"y":1301.0},{"x":6.066863691226936,"y":765.0},{"x":6.38717621396919,"y":346.0},{"x":6.707488736711444,"y":107.0},{"x":7.027801259453699,"y":42.0},{"x":7.348113782195953,"y":7.0},{"x":7.668426304938207,"y":1.0},{"x":7.988738827680462,"y":0}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"deba0cd7-8448-4ee7-a852-43ebba557cf5\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"deba0cd7-8448-4ee7-a852-43ebba557cf5\", :field \"data.y\"}}], :marks [{:type \"line\", :from {:data \"deba0cd7-8448-4ee7-a852-43ebba557cf5\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :data [{:name \"deba0cd7-8448-4ee7-a852-43ebba557cf5\", :values ({:x 2.5434259410621385, :y 0} {:x 2.863738463804393, :y 6.0} {:x 3.184050986546647, :y 22.0} {:x 3.5043635092889014, :y 80.0} {:x 3.8246760320311557, :y 289.0} {:x 4.14498855477341, :y 577.0} {:x 4.465301077515664, :y 1070.0} {:x 4.785613600257919, :y 1694.0} {:x 5.105926123000173, :y 1943.0} {:x 5.426238645742427, :y 1750.0} {:x 5.7465511684846815, :y 1301.0} {:x 6.066863691226936, :y 765.0} {:x 6.38717621396919, :y 346.0} {:x 6.707488736711444, :y 107.0} {:x 7.027801259453699, :y 42.0} {:x 7.348113782195953, :y 7.0} {:x 7.668426304938207, :y 1.0} {:x 7.988738827680462, :y 0})}], :width 400, :height 247.2188, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@

;; @@
