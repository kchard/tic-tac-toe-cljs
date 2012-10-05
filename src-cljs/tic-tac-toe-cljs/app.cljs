(ns tic-tac-toe-cljs.app
    (:require [tic-tac-toe-cljs.game :as game]
              [tic-tac-toe-cljs.drawing :as draw]
              [goog.dom :as dom]))

(def size 3)
(def player1 :X)
(def player2 :O)
(def canvas-size 750)
(def spot-size (/ canvas-size size))

(def game (atom (game/create-game size player1 player2)))
    
(defn make-move [{x :x y :y}]
    (reset! game (game/play @game x y)))
    
(defn new-game []
    (reset! game (game/create-game size player1 player2)))

(defn get-quadrant [x y]
    {:x (quot x spot-size) :y (quot y spot-size)})

(defn ^:export paint []
    (draw/draw (@game :board) player1 player2 size canvas-size))

(defn ^:export clicked [event]
    (let [element (dom/getElement "wrapper")]
        (do
            (make-move (get-quadrant (- (.-pageX event) (.-offsetLeft element)) (- (.-pageY event) (.-offsetTop element))))
            (paint)
            (if (@game :winner)
                (do
                    (js/alert (str (@game :winner) " Wins!!!"))
                    (new-game)
                    (paint))
                (if (@game :draw)
                    (do
                        (js/alert "Draw!!!")
                        (new-game)
                        (paint))
                    nil)))))
    
