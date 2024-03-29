(ns tic-tac-toe-cljs.game)

(defn matrix [size value]
  (vec (repeat size (vec (repeat size value)))))

(defn ^:export mget [matrix x-index y-index]
  (get (get matrix x-index) y-index))
  
(defn massoc [matrix x-index y-index value]
  (let [y-vec (get matrix x-index)] 
    (assoc matrix x-index (assoc y-vec y-index value))))

(defn trans-diag [matrix] 
  (vec (apply map vector matrix)))

(def open-spot :_)
  
(defn create-board [size]
  (matrix size open-spot))

(defn can-play? [board x-index y-index]
  (= open-spot (mget board x-index y-index)))

(defn winner-by-column? [board player]
  (reduce #(or %1 %2) (for [col board] (every? #(= player %) col ))))
  
(defn winner-by-row? [board player]
  (winner-by-column? (trans-diag board) player))
  
(defn winner-by-left-diag? [board player]
  (every? #(= player %) (for [i (range (count board))] (mget board i i))))
  
(defn winner-by-right-diag? [board player]
  (winner-by-left-diag? (vec (reverse board)) player))
  
(defn winner? [board player]
  (or (winner-by-column? board player) 
      (winner-by-row? board player) 
	  (winner-by-left-diag? board player)
	  (winner-by-right-diag? board player)))
      
(defn full? [board]
  (not-any? #(= % :_)(flatten board)))
  
(defn ^:export create-game [size player1 player2]
  {:board (create-board size) :turn (take (* size size) (cycle [player1 player2]))})
  
(defn take-turn [game x-index y-index]
  {:board (massoc (game :board) x-index y-index (first (game :turn))) :turn (rest (game :turn))})
  
(defn try-turn [game x-index y-index]
	(if (can-play? (game :board) x-index y-index) 
	  (take-turn game x-index y-index) 
      game))

(defn ^:export play [game x-index y-index]
  (let [player (first (game :turn)) game (try-turn game x-index y-index)]
	(if (winner? (game :board) player) 
      (assoc game :winner player) 
	  (if (full? (game :board))
       (assoc game :draw true)
        game))))