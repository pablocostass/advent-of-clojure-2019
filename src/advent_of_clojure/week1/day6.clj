(ns advent-of-clojure.week1.day6
  (:gen-class)
  (:require [clojure.string :as str]))

(defn map-orbits 
  "Defines an atom called `orbits` that is a map of 
  the relationship of who orbits whom"
  [file] 
  (def orbits
    (atom (->> file
               (slurp)
               (str/split-lines)
               (map #(str/split % #"\)"))
               (map #(map keyword %))
               (reduce #(assoc %1 (second %2) (first %2)) {})
          )
    )
  )
)

(defn path-to-center 
  "Returns the path of orbits between an object and the 
  Center of Mass (COM)"
  [object] 
  (take-while #(not= :COM %) (iterate @orbits object)))

(defn test1 
  "Calculates for each key, aka every object in orbit of other, its 
  path to the center and returns the cumulative sum of every distance"
  []
  (->> @orbits
       (keys)
       (map path-to-center)
       (map count)
       (reduce +)
  )
)

(defn test2 
  "Calculates the path to the center for both you and Santa 
  in order to get the path you share and filter it out, 
  leaving the path to get to Santa from where you are, excluding
  your position and Santa's."
  []
  (- (->> (concat (path-to-center :YOU) (path-to-center :SAN))
       (frequencies)
       (filter #(= 1 (second %)))
       (count)
  ) 2)
)

(defn day6 []
    (map-orbits "resources/day6.txt")
    (println (str "Day 6:\tTest1: " (test1) "\tTest2: " (test2)))
)