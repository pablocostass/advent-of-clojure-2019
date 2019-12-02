(ns advent-of-clojure.week1.day1
  (:gen-class)
  (:require [clojure.string :refer [split-lines]]))

(defn calculate-fuel [input]
  (reduce + (map #(- (int (Math/floor (/ % 3))) 2) input)))

(defn test1 [input]
  (calculate-fuel input))

(defn calculate-fuel-recursive [input acc]
  (def result (#(- (int (Math/floor (quot % 3))) 2) input))
  (cond 
    (<= result 0) acc
    :else (recur result (+ result acc))))

(defn test2 [input]
  (reduce + (map #(calculate-fuel-recursive % 0) input)))

(defn day1 []
  (let [input (map read-string (split-lines (slurp "resources/day1.txt")))]
    (println (str "Day 1:\tTest1: " (test1 input) "\tTest2: " (test2 input)))))