(ns robot.core
  (:require
    [clojure.java.io :as io]
    [clojure.string :refer [lower-case split trim]]
    [nomad :refer [defconfig]]
    [robot.robot :as robot])
  (:gen-class))

(defn- parse-position
  "Parses the position from the supplied string and returns it as a map."
  [^String position]
  (let [args   (split position #" *, *")
        x      (Integer/parseInt (args 0))
        y      (Integer/parseInt (args 1))
        facing (keyword (lower-case (args 2)))]
    {:x x :y y :facing facing}))

(defn- parse-command
  "Returns a zero-arg function that when called executes the specified command on the robot."
  [command current table]
  (let [halved      (split (trim command) #" +" 2)
        instruction (symbol (lower-case (first halved)))]
    (if-let [fun (ns-resolve 'robot.robot instruction)]
      (if (= @fun robot/place)
        (partial fun (parse-position (second halved)) table)
        (partial fun current table))
      (constantly nil))))

(defn- run-command
  "Runs the specified command against the table and return resulting position of the robot."
  [command current table]
  (try
    (let [new ((parse-command command current table))]
      (if-not (nil? new) new current))
    (catch Exception e current)))

(defn start-robot
  "Starts the robot by reading and processing a line at a time from standard input."
  [table]
  (loop [current nil command (read-line)]
    (when-not (nil? command)
      (recur (run-command command current table) (read-line)))))

(defn -main
  "Application entry point."
  [& args]
  (defconfig conf (io/resource "config.edn"))
  (start-robot (:table (conf)))
  (System/exit 0))
