(ns advent-of-clojure.week1.day1
  (:gen-class)
  (:require [clojure.string :refer [split-lines]]))

(defn calculate-fuel [input]
  (def fun #(- (int (Math/floor (/ % 3))) 2))
  (def result (reduce + (map fun input))) result)

(defn day1-test1 [input]
  "Day 1 test 1"
  (def result (calculate-fuel input)) result)

(defn calculate-fuel-recursive [input acc]
  (def fun #(- (int (Math/floor (/ % 3))) 2))
  (def result (fun input))
  (cond 
  (<= result 0) acc
  :else (calculate-fuel-recursive result (+ result acc))))

(defn day1-test2 [input]
  "Day 1 test 2"
  (def fun #(calculate-fuel-recursive % 0))
  (def result (reduce + (map fun input))) result)

(defn day1 []
  "https://adventofcode.com/2019/day/1"
  (let [input (map read-string (split-lines (slurp "resources/day1.txt")))]
    (println (str "Day 1:\tTest1: " (day1-test1 input) "\tTest2: " (day1-test2 input)))))