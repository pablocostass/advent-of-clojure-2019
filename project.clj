(defproject advent_of_clojure "0.1.0-SNAPSHOT"
  :description "Advenf of Code (2019) done in Clojure"
  :url "https://adventofcode.com/2019"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :main ^:skip-aot advent-of-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-kibit "0.1.8"]
            [jonase/eastwood "0.3.6"]
            [lein-bikeshed "0.5.2"]
            [lein-autoreload "0.1.1"]])
