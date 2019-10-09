(defproject {{name}} "0.1.0-SNAPSHOT"
  :min-lein-version "2.5.3"
  :plugins [[lein-shadow "0.1.6"]]

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"
                  :exclusions [com.google.javascript/closure-compiler-unshaded
                               org.clojure/google-closure-library]]
                 [thheller/shadow-cljs "2.8.60"]
                 [reagent "0.8.1"]
                 [re-frame "0.10.9"]]
  :npm-deps [[create-react-class "15.6.3"]
             [expo "^35.0.0"]
             [react "16.8.6"]
             [react-dom "^16.8.6"]
             [react-native "https://github.com/expo/react-native/archive/sdk-35.0.0.tar.gz"]
             [react-native-web "^0.11.4"]]
  :npm-dev-deps [[babel-preset-expo "^5.2.0"]
                 [shadow-cljs "^2.8.60"]]

  :shadow-cljs {:nrepl {:port 9000}
                :builds {:app
                         {:target :react-native
                          :init-fn {{name}}.app/init
                          :output-dir "App"
                          :compiler-options {:infer-externs :auto}
                          :devtools {:autoload true}}}}

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["node_modules" "App" "target" "shadow-cljs.edn" "package.json" "package-lock.json" ".expo" ".shadow-cljs" "index.js"]

  :profiles
  {:dev {:dependencies [[binaryage/devtools "0.9.10"]]}
   :prod {}})
