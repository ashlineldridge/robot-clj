(ns robot.robot-test
  (:require [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [robot.robot :as robot]))

(defn gen-within-bounds
  [table]
  "Returns a generator for generating positions within the bounds of the specified table."
  (gen/hash-map
    :x (gen/fmap #(mod % (:width table)) gen/pos-int)
    :y (gen/fmap #(mod % (:height table)) gen/pos-int)
    :facing (gen/elements robot/cardinal-directions)))

(defn gen-out-of-bounds
  [table]
  "Returns a generator for generating positions oustide the bounds of the specified table."
  (gen/hash-map
    :x (gen/fmap #(+ (:width table) %) gen/pos-int)
    :y (gen/fmap #(+ (:height table) %) gen/pos-int)
    :facing (gen/elements robot/cardinal-directions)))

(defspec place-command-accepts-within-bounds-coords
         100
         (let [table {:width 5 :height 5}]
           (prop/for-all [p (gen-within-bounds table)]
                         (= p (robot/place p table)))))

(defspec place-command-ignores-out-of-bounds-coords
         100
         (let [table {:width 5 :height 5}]
           (prop/for-all [p (gen-out-of-bounds table)]
                         (= nil (robot/place p table)))))