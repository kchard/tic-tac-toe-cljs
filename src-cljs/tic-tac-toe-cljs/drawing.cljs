(ns tic-tac-toe-cljs.drawing
    (:require [tic-tac-toe-cljs.game :as game]
              [goog.dom :as dom]))

(defn get-canvas []
    (dom/getElement "board"))
    
(defn get-context[]
    (. (get-canvas) getContext "2d"))
    
(defn clear [canvas-size]
    (let [context (get-context)]
        (. context clearRect 0 0 canvas-size canvas-size)))

(defn draw-line [{x1 :x1 y1 :y1} {x2 :x2 y2 :y2}]
    (let [context (get-context)]
        (do 
            (. context beginPath)
            (. context moveTo x1 y1)
            (. context lineTo x2 y2)
            (. context stroke))))

(defn draw-grid [size canvas-size] 
    (dorun 
        (map #(draw-line {:x1 0 :y1 (* (/ canvas-size size)(inc %))} {:x2 canvas-size :y2 (* (/ canvas-size size)(inc %))}) (range size)))
    (dorun 
        (map #(draw-line {:x1 (* (/ canvas-size size)(inc %)) :y1 0} {:x2 (* (/ canvas-size size)(inc %)) :y2 canvas-size}) (range size))))
    
(defn draw-x [x y size canvas-size]
    (let [spot-size (/ canvas-size size)] 
        (draw-line {:x1 (* x spot-size) :y1 (* y spot-size)} {:x2 (+ (* x spot-size) spot-size) :y2 (+ (* y spot-size) spot-size)})
        (draw-line {:x1 (+ (* x spot-size) spot-size) :y1 (* y spot-size)} {:x2 (* x spot-size) :y2 (+ (* y spot-size) spot-size)})))
        
(defn draw-o [x y size canvas-size]
    (let [context (get-context) spot-size (/ canvas-size size) radius (/ spot-size 2) center-x (+ (* x spot-size) radius) center-y (+ (* y spot-size) radius)] 
        (do
            (. context beginPath)
            (. context arc center-x center-y radius 0 (* 2 3.14) false)
            (. context stroke))))
            
(defn draw-symbol [x y size canvas-size s p1 p2]
    (if (= s p1 )
        (draw-x x y size canvas-size)
        (if (= s p2)
            (draw-o x y size canvas-size)
            nil)))

(defn draw-symbols [board p1 p2 size canvas-size]
    (dorun 
        (for [x (range size) y (range size)]
            (draw-symbol x y size canvas-size (game/mget board x y) p1 p2))))
    
(defn ^:export draw [board p1 p2 size canvas-size]
    (do
        (clear canvas-size)
        (draw-grid size canvas-size)
        (draw-symbols board p1 p2 size canvas-size)))