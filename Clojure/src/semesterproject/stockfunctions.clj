;;Author: Jeffrey Ross and Sean Oolman
;;This file contains functions for processing the stock data

(load-file "./src/semesterproject/htmlparse.clj")

(comment
"Function:
  movingaverage

Description:
  Gets the average of a specified number of values for a specified stock
  and then prints it to the terminal.

Arguments:
  id - regex indicator of what value to use, closing, opening etc.
  stock - stock market indicator for the desired stock to average.
  days - the number of days to take an average of.

Returns:
  nothing

Author:
  Jeff Ross

Todos/Known Bugs:
  Need to format the printing for when getting the moving average for 
  volume. currently prints in the format of x.xxE7, should just be 
  printing as a regular number.
")
(defn movingaverage [id stock days]
  (def tot 0)
  (loop [i 1]
  (when (<= i days)
    (def tot (+ tot (Float/parseFloat (pullvalue stock id i))))
    (recur (inc i))))
  (println (/ tot days) "\n\n"))
  
(comment
"Function:
  getvalue

Description:
  Gets the value of a stock for a specified number of days and prints
  them all to the terminal.

Arguments:
  id - regex indicator of what value to use, closing, opening etc.
  stock - stock market inidcator for the desired stock to find the
          value for.
  days - the number of days to find the value for.

Returns:
  nothing

Author:
  Jeff Ross

Todos/Known Bugs:
  none
")
(defn getvalue [id stock days]
  (loop [i 1]
  (when (<= i days)
    (println (pullvalue stock id i))
    (recur (inc i))))
  (println "\n\n"))

(comment
"Function:
  getmin

Description:
  Gets the minimum value of a stock for a specified number of days and prints
  it to the terminal.

Arguments:
  id - regex indicator of what value to use, closing, opening etc.
  stock - stock market inidcator for the desired stock to check.
  days - the number of days to check.

Returns:
  nothing

Author:
  Sean Oolman

Todos/Known Bugs:
  none
")
(defn getmin [id stock days]
  (def stocks [])
  (loop [i 1]
  (when (<= i days)
    (def newstock (read-string (pullvalue stock id i)))
    (def stocks (conj stocks newstock))
    (recur (inc i))))
  (println (apply min stocks))
  (println "\n\n"))

(comment
"Function:
  getmax

Description:
  Gets the maximum value of a stock for a specified number of days and prints
  it to the terminal.

Arguments:
  id - regex indicator of what value to use, closing, opening etc.
  stock - stock market inidcator for the desired stock to check.
  days - the number of days to check.

Returns:
  nothing

Author:
  Sean Oolman

Todos/Known Bugs:
  none
")
(defn getmax [id stock days]
  (def stocks [])
  (loop [i 1]
  (when (<= i days)
    (def newstock (read-string (pullvalue stock id i)))
    (def stocks (conj stocks newstock))
    (recur (inc i))))
  (println (apply max stocks))
  (println "\n\n"))

(comment
"Function:
  getrange

Description:
  Gets the range of values of a stock for a specified number of days and prints
  it to the terminal.

Arguments:
  id - regex indicator of what value to use, closing, opening etc.
  stock - stock market inidcator for the desired stock to check.
  days - the number of days to check.

Returns:
  nothing

Author:
  Sean Oolman

Todos/Known Bugs:
  none
")
(defn getrange [id stock days]
  (def stocks [])
  (loop [i 1]
  (when (<= i days)
    (def newstock (read-string (pullvalue stock id i)))
    (def stocks (conj stocks newstock))
    (recur (inc i))))
  (def minval (apply min stocks))
  (def maxval (apply max stocks))
  (def range (- maxval minval))
  (println range)
  (println "\n\n"))
