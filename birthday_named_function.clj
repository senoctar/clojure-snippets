(ns birthday-named-function
  (:require [clojure.string :as s]))

(defn str-split [s sep-chars]
  (reverse (reduce
             #(if (s/includes? sep-chars (str %2))
                (cons "" %1)
                (cons (str (first %1) %2) (rest %1)))
             [] (char-array s))))

(defn elem-before-match [coll f]
  (second (drop-while (complement f) (reverse coll))))

(defn parse-int-safe [s]
  (try (Integer/parseInt s)
       (catch NumberFormatException e 0)))

(defn parse-duration [formatted]
  (let [tokens (str-split formatted " ,")
        prefix-fn #(fn [s] (s/starts-with? s %))
        number-before-match #(parse-int-safe (elem-before-match %1 %2))]
    (map #(number-before-match tokens (prefix-fn %)) ["year" "month" "day"])))


(println (parse-duration "3 years,      2 days"))