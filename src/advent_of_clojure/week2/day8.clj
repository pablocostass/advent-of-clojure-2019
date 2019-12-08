(ns advent-of-clojure.week2.day8
  (:gen-class)
  (:require [clojure.string :refer [split-lines]]))

(defn check-layer [layer]
  (assoc {} (->> layer
       (filter #(= \0 %))
       (count)) layer))

(defn get-fewest-0-layer [layers]
  (let [mapped-layers (map check-layer layers)]
    (let [fewest-0-key (->> mapped-layers
                            (map keys)
                            (reduce into [])
                            (sort)
                            (first))]
      (let [fewest-0-layer 
            (->> mapped-layers
                 (map #(get % fewest-0-key))
                 (filter #(not= nil %))
                 (first))]
        fewest-0-layer))))

(defn test1 [layers]
  (let [layer (get-fewest-0-layer layers)]
    (* 
      (count (filter #(= \1 %) layer))
      (count (filter #(= \2 %) layer)))))

(defn day8 []
  (let [input (->> "resources/day8.txt"
                   (slurp)
                   (split-lines)
                   (first)
                   (partition 150))]
    ;(println (str "Day 1:\tTest1: " (test1 input) "\tTest2: " (test2 input)))
    (println (str "Day 8:\tTest1: " (test1 input)))))