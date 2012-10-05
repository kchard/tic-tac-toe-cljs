(defproject tic-tac-toe-cljs "0.1.0-SNAPSHOT"
    :description "Tic-Tac-Toe implemented on the client with ClojureScript"
    :dependencies [[org.clojure/clojure "1.4.0"]
                   [noir "1.2.1" :exclusions [org.clojure/clojure]]]
    :plugins [[lein-cljsbuild "0.2.7"]]
    :cljsbuild
    {
        :source-path "src-cljs"
        :compiler
        {
            :output-to "resources/public/js/cljs.js"
            :optimizations :simple
            :pretty-print true
        }
    }
    :main tic-tac-toe-cljs.server)

