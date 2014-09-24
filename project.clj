(defproject robot "0.1.0-SNAPSHOT"

  :description "FIXME: write description"

  :url "http://example.com/FIXME"

  :main ^:skip-aot robot.core

  :target-path "target/%s"

  :profiles {:uberjar {:aot :all}
             :dev     {:source-paths ["dev"]
                       :resource-paths ["test_resources"]
                       :dependencies [[org.clojure/tools.namespace "0.2.6"]]}
             :test    {:source-paths ["test_acceptance"]
                       :resource-paths ["test_resources"]}}

  :test-paths ["test" "test_acceptance"]

  :test-selectors {:default #(not (:acceptance %))
                   :acceptance :acceptance
                   :all #(do true)}

  :plugins [[lein-ancient "0.5.5"]]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [jarohen/nomad "0.7.0"]])