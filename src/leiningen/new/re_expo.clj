(ns leiningen.new.re-expo
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "re-expo"))

(defn re-expo
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' re-expo project.")
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
