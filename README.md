# tic-tac-toe-cljs

A client side game of tic tac toe written in ClojureScript and utilizing the HTML5 Canvas to draw the board. 

## Usage

```bash
lein deps
lein run
```

Play the game at: http://localhost:8080/tic-tac-toe

## Google Closure Compiler

This project used the cljsbuild lein plugin to compile the ClojureScript source to Javascript using the Google Closure Compiler

Clean and Compile to Javascript
```bash
lein cljsbuild clean
lein cljsbuild once
```

Automatic Compilation for Development
```bash
lein cljsbuild auto
```

## License

Copyright (C) 2011 Kevin Chard


