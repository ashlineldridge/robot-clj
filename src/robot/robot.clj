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
  "Returns the specified position rotated in the specified direction."
  [position table direction]
  (when (valid? position table)
    (let [delta   (get {:left -1 :right 1} direction 0)
          total   (count cardinal-directions)
          cur-ind (.indexOf cardinal-directions (:facing position))
          rot-ind (mod (+ cur-ind delta total) total)
          facing  (nth cardinal-directions rot-ind)]
      (assoc position :facing facing))))

(defn place
  "Instructs the robot to be placed at the specified position on the table.
   Returns the resulting position of the robot."
  [position table]
  (when (valid? position table) position))

(defn move
  "Instructs the robot to move one space forward based on the direction it is facing.
   Returns the resulting position of the robot."
  [position table]
  (when (valid? position table)
    (let [facing (:facing position)
          delta  ({:north [0 1] :east [1 0] :south [0 -1] :west [-1 0]} facing)
          result (map + [(:x position) (:y position)] delta)
          new    {:x (first result) :y (second result) :facing facing}]
      (if (valid? new table) new position))))

(defn left
  "Instructs the robot to turn left, altering the direction it is facing.
   Returns the resulting position of the robot."
  [position table]
  (rotate position table :left))

(defn right
  "Instructs the robot to turn report, altering the direction it is facing.
   Returns the resulting position of the robot."
  [position table]
  (rotate position table :right))

(defn report
  "Instructs the robot to turn report its current position to standard output."
  [{:keys [x y facing] :as position} table]
  (when (valid? position table)
    (println (str x "," y "," (upper-case (name facing))))))