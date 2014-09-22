(ns robot.core
  (:require
    [clojure.java.io :as io]
    [clojure.string :refer [lower-case split trim]]
    [nomad :refer [defconfig]]
    [robot.robot :as robot])
  (:gen-class))

(defconfig conf (io/resource "config.edn"))

(defn- parse-position
  ""
  [position-string]
  (let [args   (split position-string #" *, *")
        x      (Integer/parseInt (args 0))
        y      (Integer/parseInt (args 1))
        facing (keyword (lower-case (args 2)))]
    {:x x :y y :facing facing}))

(defn- parse-command
  ""
  [command]
  (let [halved      (split (trim command) #" +" 2)
        instruction (symbol (lower-case (first halved)))]
    (when-let [fun (ns-resolve 'robot.robot instruction)]
      (if (= @fun robot/place)
        (partial fun (parse-position (second halved)))
        fun))))

(defn- run-command
  ""
  [command current table]
  (when-let [fun (parse-command command)]
    (fun current table)))

(defn- start-robot
  ""
  [table]
  (loop [command (read-line) current nil]
    (when-not (nil? command)
      (recur (read-line) (run-command command current table)))))

(defn -main
  "Entry point."
  [& args]
  (start-robot (:table (conf))))
