;;PL Semester Project
;;Author: Jeffrey Ross and Sean Oolman
;;Clojure Stock Program
;;CSC461 Programming Languages M001 11/29/18
;;Clojure 1.9.0 with Leiningen
;;gets stock data from Yahoo and does simple math to it


(ns semesterproject.menu)
(load-file "./src/semesterproject/input.clj")
(load-file "./src/semesterproject/stockfunctions.clj")

(comment
"Function:
  typemenu

Description:
  Calls a function to display a menu and read input, then matches the desired
  input to a regex indicator of which value the user would like. If the value
  chosen is not an option, throws an exception

Arguments:
  none

Returns:
  Regex indication of the type of value the user would like

Author:
  Jeff Ross, small changes by Sean Oolman

Todos/Known Bugs:
  none
")
(defn typemenu []
  (def ctype (readtype))
  (try (case ctype
    1 (let [temp #"\"open\""] temp)
    2 (let [temp #"\"close\""] temp)
    3 (let [temp #"\"high\""] temp)
    4 (let [temp #"\"low\""] temp)
    5 (let [temp #"\"volume\""] temp))
  ;; throw exception if not valid
  (catch Exception e (-1))))

(comment
"Function:
  -main

Description:
  The entry function. Will go through and call a function to display a main
  menu and read input, then matches the selected input to the corresponding
  function. If the value is the exit choice, will exit out of the program.
  If the value is not an option or if any errors had occured on any of the 
  functions being called underneath, will output 'invalid option'

Arguments:
  args - commandline arguments

Returns:
  none

Author:
  Jeff Ross, small changes by Sean Oolman

Todos/Known Bugs:
  Add more options to the menu and more functions to push us up to 12 functions
")
(defn -main [& args]
  (def choice 0)
  (while (not= 6 choice) 
    (do     
    (def choice (readchoice))
    (try (case choice
    ;;(case choice
      1 (getvalue (typemenu)(readstock)(readdays))
      2 (movingaverage (typemenu)(readstock)(readdays))
      3 (getmin (typemenu)(readstock)(readdays))
      4 (getmax (typemenu)(readstock)(readdays))
      5 (getrange (typemenu)(readstock)(readdays))
      6 ())
    ;; catches if not a part of the switch statement
    (catch IllegalArgumentException e (println "invalid option\n\n"))))))
    ;;)))
