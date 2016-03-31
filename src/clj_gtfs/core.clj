(ns clj-gtfs.core
  (:gen-class))


(defn get-attributes
  [input]
  (->> input
       (#(clojure.string/split % #","))
       (map symbol)))


(defn get-record [record input]
  (let [attributes (get-attributes input)]
    (eval `(defrecord ~(symbol record) ~(vec (map symbol attributes))))
    (resolve (symbol (str "->" record)))))


(defn make-record
  [record input]
  (apply record (clojure.string/split input #"," -1)))


(defn make-records
  [record filepath]
  (let [ input (clojure.string/split-lines (slurp filepath))]
    (->> input
         (drop 1)
         (map #(make-record (get-record record (first input)) %)))))


(defn -main
  [& args]
  (make-records "Agency" "resources/agency.txt")
  (make-records "Dates" "resources/calendar_dates.txt")
  (make-records "Calendar" "resources/calendar.txt")
  (make-records "Feed-info" "resources/feed_info.txt")
  (make-records "Routes" "resources/routes.txt")
  (make-records "Shapes" "resources/shapes.txt")
  (make-records "Stops" "resources/stops.txt")
  (make-records "Stop-time" "resources/stop_times.txt")
  (make-records "Trips" "resources/trips.txt"))






