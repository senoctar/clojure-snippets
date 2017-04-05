(ns birthday-split-regex
  (:require [clojure.string :as s]))

(defn parse-int-safe [s]
  (if (re-matches #"\d+" (str s)) (Integer/parseInt s) 0))

(defn parse-duration [formatted]
  (let [tokens (s/split formatted #"[ ,]")
        elem-before-match (fn [coll f] (last (take-while (complement f) coll)))]
    (map #(parse-int-safe (elem-before-match tokens (fn [s] (s/starts-with? s %))))
         ["year" "month" "day"])))


(println (parse-duration "3 years,      2 days"))