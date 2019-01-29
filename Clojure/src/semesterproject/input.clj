;;Author: Jeffrey Ross
;;This file contains the functions for getting user input

(comment
"Function:
  readchoice

Description:
  Prints out the main menu prompt and reads in the value as chosen
  by the user. If the value is not numeric, throws an exception.

Arguments:
  none

Returns:
  The choice the reader has chosen

Author:
  Jeff Ross

Todos/Known Bugs:
  none
")
(defn readchoice []
  (print (str "1. Get value\n"
              "2. Moving average\n"
              "3. Minimum value\n"
              "4. Maximum value\n"
              "5. Range of values over time\n"
              "6. Exit\n"
              "Choice: "))
  (flush)           
    ;; this reads in and attempts to convert to an integer
    (try (Integer. (re-find  #"\d+" (read-line)))
    (catch Exception e (def choice -1))))

(comment
"Function:
  readstock

Description:
  Provides a prompt to read in a stock name, returns what it recieves.

Arguments:
  none

Returns:
  The stock it has read in

Author:
  Jeff Ross

Todos/Known Bugs:
  none
")
(defn readstock []
  (print "Please enter a stock: ")
  (flush)
  (read-line))

(comment
"Function:
  readdays

Description:
  Offers a prompt to read in the number of days the user would like, if 
  the value is not numeric, throws an exception.

Arguments:
  none

Returns:
  The value entered by the user

Author:
  Jeff Ross

Todos/Known Bugs:
  none
")
(defn readdays []
  (print "Please enter number of days: ")
  (flush)
  (try (Integer. (re-find  #"\d+" (read-line)))
    ;; -1 throws an exception back out
    (catch Exception e (-1))))

(comment
"Function:
  readtype

Description:
  Displays the type menu for the user to select a type, and then reads 
  the input. If the value is not numeric, throws an exception.

Arguments:
  none

Returns:
  value chosen

Author:
  Jeff Ross

Todos/Known Bugs:
  none
")
(defn readtype []
  (print (str "Choose a type:\n"
              "1. Opening Price\n"
              "2. Closing Price\n"
              "3. High Price\n"
              "4. Low Price\n"
              "5. Volume\n"
              "Choice: "))
  (flush)
  (try (Integer. (re-find  #"\d+" (read-line)))
  ;; -1 throws an exception back out
  (catch Exception e (-1))))
