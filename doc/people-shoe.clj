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
(ns purring-dew
  (:require [gorilla-plot.core :as plot]
            [pocket.base :as pb]
            [pocket.gens :as pg]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def loo (take 5000 (pg/people-shoe 5)))
(count loo)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>5000</span>","value":"5000"}
;; <=

;; @@
(def freq (frequencies loo))
(plot/bar-chart (keys freq) (vals freq))
;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"ordinal","range":"width","domain":{"data":"dec02d30-24cb-4739-8861-0905fa4420ea","field":"data.x"}},{"name":"y","range":"height","nice":true,"domain":{"data":"dec02d30-24cb-4739-8861-0905fa4420ea","field":"data.y"}}],"marks":[{"type":"rect","from":{"data":"dec02d30-24cb-4739-8861-0905fa4420ea"},"properties":{"enter":{"y":{"scale":"y","field":"data.y"},"width":{"offset":-1,"scale":"x","band":true},"x":{"scale":"x","field":"data.x"},"y2":{"scale":"y","value":0}},"update":{"fill":{"value":"steelblue"},"opacity":{"value":1}},"hover":{"fill":{"value":"#FF29D2"}}}}],"data":[{"name":"dec02d30-24cb-4739-8861-0905fa4420ea","values":[{"x":"Keesha Hoxsie","y":1000},{"x":"Audrea Ambert","y":1000},{"x":"Lakeesha Madziar","y":1000},{"x":"Isela Skrip","y":1000},{"x":"Lavada Cisnero","y":1000}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"ordinal\", :range \"width\", :domain {:data \"dec02d30-24cb-4739-8861-0905fa4420ea\", :field \"data.x\"}} {:name \"y\", :range \"height\", :nice true, :domain {:data \"dec02d30-24cb-4739-8861-0905fa4420ea\", :field \"data.y\"}}], :marks [{:type \"rect\", :from {:data \"dec02d30-24cb-4739-8861-0905fa4420ea\"}, :properties {:enter {:y {:scale \"y\", :field \"data.y\"}, :width {:offset -1, :scale \"x\", :band true}, :x {:scale \"x\", :field \"data.x\"}, :y2 {:scale \"y\", :value 0}}, :update {:fill {:value \"steelblue\"}, :opacity {:value 1}}, :hover {:fill {:value \"#FF29D2\"}}}}], :data [{:name \"dec02d30-24cb-4739-8861-0905fa4420ea\", :values ({:x \"Keesha Hoxsie\", :y 1000} {:x \"Audrea Ambert\", :y 1000} {:x \"Lakeesha Madziar\", :y 1000} {:x \"Isela Skrip\", :y 1000} {:x \"Lavada Cisnero\", :y 1000})}], :width 400, :height 247.2188, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@

;; @@
