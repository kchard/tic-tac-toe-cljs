(ns tic-tac-toe-cljs.views.welcome
  (:require [tic-tac-toe-cljs.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(def canvas-size 750)
        
(defpage "/tic-tac-toe" []
    (common/layout
        [:canvas#board {:width canvas-size :height canvas-size :onclick "tic_tac_toe_cljs.app.clicked(event);"}]
        [:script "tic_tac_toe_cljs.app.paint();"]))
