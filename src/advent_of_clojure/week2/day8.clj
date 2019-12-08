(ns advent-of-clojure.week2.day8
  (:gen-class)
  (:require [clojure.string :refer [split-lines join replace]]))

(defn check-layer
  "Checks if a layer has the character `\0`
  and if so, counts how many and returns
  a map of {count, layer}"
  [layer]
  (assoc {} (->> layer
       (filter #(= \0 %))
       (count)) layer))

(defn get-fewest-0-layer
  "Gets from a map of {num-of-0s, layer}
  the lowest number of 0s and returns its layer."
  [layers]
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

(defn test1
  [layers]
  (let [layer (get-fewest-0-layer layers)]
    (* 
      (count (filter #(= \1 %) layer))
      (count (filter #(= \2 %) layer)))))

(defn stack-layers
  "Stacks the layers of the image and removes
  every transparent pixel (those with the 
  character `\2`) and returns a joined string."
  [layers]
  (let [stacked-layers (partition 100 (apply interleave layers))]
    (->> stacked-layers
         (map (fn [pixel] (first (drop-while #(= % \2) pixel))))
         (partition 25)
         (map #(apply str %))
         (join "\n"))))

(defn test2
  "Replaces the black pixels (the \0s) with spaces
  and the white pixels (the \1s) with hashtags 
  for ease of readibility."
  [layers]
  (let [stacked-layers (stack-layers layers)]
    (replace
      (replace stacked-layers #"1" "#") #"0" " ")))

(defn day8 []
  (let [input (->> "resources/day8.txt"
                   (slurp)
                   (split-lines)
                   (first)
                   (partition (* 25 6)))]
    (println (str "Day 8:\tTest1: " (test1 input) "\tTest2: (down below)\n\n" (test2 input) "\n"))))