(ns analize-start.core)
; Импортируем модуль для удаления файлов
(use '[clojure.java.io :as io])

(defn clean-file []
  (->> "/home/aqude/Desktop/tests/nonFilter.txt"
       (io/reader)
       (line-seq)
       (map #(clojure.string/replace % ",," ","))
       (map #(clojure.string/replace % ".,", ","))
       (map #(clojure.string/replace % "!,", ","))
       (remove #(clojure.string/blank? %))
       (map #(clojure.string/trim %))
       (filter #(clojure.string/includes? % ", "))
       (map #(let [[text num-str] (clojure.string/split % #",")]
               {:text (clojure.string/trim text)
                :num (Integer/parseInt (clojure.string/trim num-str))}))
       (remove #(or (nil? (:num %)) (not (number? (:num %)))))
       (sort-by :num)
       (map #(str (:text %) ", " (:num %)))
       (clojure.string/join "\n")
       (io/writer "/home/aqude/Desktop/tests/sorted_file.txt")
       (.close)))

;(clean-file)
;(io/delete-file "/home/aqude/Desktop/tests/sorted_file.txt")
