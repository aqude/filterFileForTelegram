(ns analize-start.core)

(require '[clojure.java.io.file :as file])

(if (file/exists? "src/file.txt")
  (println "Файл существует")
  (println "Файл не существует"))
