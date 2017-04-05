(ns birthday-all-regex)

(defn parse-duration [formatted]
  (let [get-number #(if (nil? %) 0 (Integer/parseInt (nth % 1)))]
    (map #(get-number (re-find (re-pattern (str "(\\d+)\\s*" %)) formatted))
         ["year" "month" "day"])))


(println (parse-duration "3 years,      2 days"))