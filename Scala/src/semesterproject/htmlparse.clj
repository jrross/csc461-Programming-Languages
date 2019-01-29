;;Author: Jeffrey Ross
;;This file contains the function for pulling html stock data

(require '[clojure.string :as str])
(comment
"Function: 
  pullvalue

Description: 
  Pulls a specified value from yahoo.com

Arguments:
  stock - the stock symbol to find the value for
  id - regex indicator of what value to pull, closing, opening etc.
  day - which day to get the value for in reference to how many days ago,
        the most recent stock day would be 1

Returns:
  the value

Author:
  Jeff Ross

Todos/Known Bugs:
  Make the html variable be static, and add a new static variable to keep track
  of what the stock symbol being called is, if the last call and the current call
  have the same symbol, use the statically saved html variable to avoid having to
  pull the HTML, saving time, since pulling is expensive
")
(defn pullvalue [stock id day]
  ;; pull the HTML for a given stock
  (def html (slurp (str "https://finance.yahoo.com/quote/"stock"/history?p="stock)))
  ;; find the information for the desired day and split at desired data
  (def days (str/split (nth (str/split html #"HistoricalPriceStore") 1) id))
  ;; scrape out the desired data
  (nth (str/split (nth (str/split (nth days day) #":") 1) #",")0))
