(set-env!
 :source-paths   #{"src"}
 :dependencies '[[org.clojure/clojure "1.8.0"     :scope "provided"]
                 [org.clojure/tools.nrepl "0.2.12"]])

(def +version+ "0.0.1-SNAPSHOT")

(task-options!
 pom  {:project     'yenda/clj-gtfs
       :version     +version+
       :description "A library to work with gtfs format in Clojure"
       :url         "https://github.com/yenda/clj-gtfs"
       :scm         {:url "https://github.com/yenda" }
       :license     {"MIT" "https://opensource.org/licenses/MIT"}}
 aot {:all true}
 jar {:main 'clj-gtfs.core :file "clj-gtfs.jar"})

(deftask uberjar
  []
  (comp
   (aot)
   (pom)
   (uber)
   (jar)
   (target)))
