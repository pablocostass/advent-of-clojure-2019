(ns advent-of-clojure.week1.day2
  (:gen-class)
  (:require [clojure.string :as str]))

(defn replace-value [input pos value]
  (concat (take pos input) (cons value '()) (drop (inc pos) input)))

(defn opcode-function [input offset operator]
  (let [arg1 (nth input (nth input (inc offset)))
        arg2 (nth input (nth input (+ offset 2)))
        output-pos (nth input (+ offset 3))] 
          (replace-value input output-pos (operator arg1 arg2))))

(defn test1 [program offset]
  (let [opcode (nth program offset)]
    (cond
      (= opcode 1) (test1 (opcode-function program offset +) (+ offset 4))
      (= opcode 2) (test1 (opcode-function program offset *) (+ offset 4))
      (= opcode 99) (first program)
      :else (test1 program (+ offset 4)))))

(defn test2 [input]
  (for [noun (range 100) verb (range 100)
        :let [result (test1 (replace-value (replace-value input 1 noun) 2 verb) 0)]
        :when (= 19690720 result)]
    (+ (* 100 noun) verb)))

(defn day2 []
  (let [input (map read-string (str/split (slurp "resources/day2.txt") #","))]
    (println "Day 2:\tTest1: " (test1 input 0) "\tTest2: " (first (test2 input)))))