(ns advent-of-clojure.core
  (:gen-class)
  (:require [advent-of-clojure.week1.day1 :refer [day1]]
            [advent-of-clojure.week1.day2 :refer [day2]]))

(defn -main [& args]
  (day1) (day2))
