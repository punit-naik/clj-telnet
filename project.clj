(defproject clj-telnet "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [stylefruits/gniazdo "1.2.0"]]
  :profiles
  {:uberjar {:main allstreet.editor.server.core
             :aot :all}
   :dev {:plugins [[lein-kibit "0.1.8"]
                   [lein-cloverage "1.2.2"]
                   [lein-codox "0.10.7"]]}}
  :repl-options {:init-ns clj-telnet.core})
