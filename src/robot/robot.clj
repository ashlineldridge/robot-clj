(ns robot.robot
  (:require
    [clojure.string :refer [upper-case]]))

(def cardinal-directions [:north :east :south :west])

(defn- valid?
  "Returns whether the position is valid for the table."
  [{:keys [x y facing]} {:keys [width height]}]
  (and (>= x 0) (< x width)
       (>= y 0) (< y height)
       (contains? (set cardinal-directions) facing)))

(defn- rotate
  "Returns the current position rotated in the specified direction."
  [current table direction]
  (when (valid? current table)
    (let [delta   (get {:left -1 :right 1} direction 0)
          total   (count cardinal-directions)
          cur-ind (.indexOf cardinal-directions (:facing current))
          rot-ind (mod (+ cur-ind delta total) total)
          facing  (nth cardinal-directions rot-ind)]
      (assoc current :facing facing))))

(defn place
  ""
  [new current table]
  (if (valid? new table) new current))

(defn move
  ""
  [current table]
  (when (valid? current table) current))

(defn left
  ""
  [current table]
  (rotate current table :left))

(defn right
  ""
  [current table]
  (rotate current table :right))

(defn report
  ""
  [{:keys [x y facing] :as current} table]
  (when (valid? current table)
    (println (str x ", " y ", " (upper-case (name facing))))))