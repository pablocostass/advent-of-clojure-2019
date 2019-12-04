(ns advent-of-clojure.week1.day4
  (:gen-class))

(defn is-valid-password? [password]
  (let [pairs-of-digits (partition 2 1 password)]
    (and 
      (some #(= (first %) (last %)) pairs-of-digits)
      (every? #(<= (int (first %)) (int (last %))) pairs-of-digits))))

(defn test1 [input]
  (count (filter is-valid-password? input)))

(defn is-valid-password2? [password]
  (let [pairs-of-digits (partition 2 1 password)]
    (and 
      (some #(and (= (first %) (last %)) (= 2 (get (frequencies password) (first %)))) pairs-of-digits)
      (every? #(<= (int (first %)) (int (last %))) pairs-of-digits))))

(defn test2 [input]
  (count (filter is-valid-password2? input)))

(defn day4 []
  (let [input (map str (range 136818 (inc 685979)))]
    (println (str "Day 4:\tTest1: " (test1 input) "\tTest2: " (test2 input)))))