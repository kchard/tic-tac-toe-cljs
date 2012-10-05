(ns tic-tac-toe-cljs.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css include-js html5]]))

(defpartial layout [& content]
    (html5
        [:head
            [:title "testproject"]
            (include-css "/css/reset.css")
            (include-css "/css/main.css")
            (include-js "/js/cljs.js")]
        [:body
            [:div#wrapper content]]))

