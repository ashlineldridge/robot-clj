(ns robot.acceptance-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [robot.core :as core]))

(defn- run-robot
       "Runs the robot against the specified input string.
        Returns the output of the robot as a string."
       [input]
       (with-out-str (with-in-str input (core/start-robot {:width 5 :height 5}))))

(deftest ^:acceptance acceptance-test
  (testing "Robot full acceptance test"
    (let [input    (slurp (io/resource "input.txt"))
          expected (slurp (io/resource "output.txt"))
          actual   (run-robot input)]
      (is (= actual expected)))))