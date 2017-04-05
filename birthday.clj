(ns birthday
  (:require [clojure.string :as s]))

(defn str-split [s sep-chars]
  (reverse (reduce
             #(if (s/includes? sep-chars (str %2))
                (cons "" %1)
                (cons (str (first %1) %2) (rest %1)))
             [] (char-array s))))

(defn parse-int-safe [s]
  (try (Integer/parseInt s)
       (catch NumberFormatException e 0)))

(defn parse-duration [formatted]
  (let [tokens (str-split formatted " ,")
        elem-before-match (fn [coll f] (last (take-while (complement f) coll)))]
    (map #(parse-int-safe (elem-before-match tokens (fn [s] (s/starts-with? s %))))
         ["year" "month" "day"])))


(println (parse-duration "3 years,      2 days"))