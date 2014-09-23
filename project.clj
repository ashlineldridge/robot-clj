(defproject robot "0.1.0-SNAPSHOT"

  :description "FIXME: write description"

  :url "http://example.com/FIXME"

  :main ^:skip-aot robot.core

  :target-path "target/%s"

  :profiles {:uberjar {:aot :all}
             :dev     {:source-paths ["dev"]
                       :dependencies [[org.clojure/tools.namespace "0.2.6"]]}
             :test    {:resource-paths ["test_resources"]}}

  :plugins [[lein-ancient "0.5.5"]]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [jarohen/nomad "0.7.0"]])