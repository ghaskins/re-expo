(ns leiningen.new.re-expo
  (:require [leiningen.new.templates :refer [renderer raw-resourcer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "re-expo"))
(def cp (raw-resourcer "re-expo"))

(defn re-expo
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' re-expo project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["app.json" (render "app.json" data)]
             ["README.md" (render "README.md" data)]
             ["assets/icon.png" (cp "assets/icon.png")]
             ["assets/shadow-cljs.png" (cp "assets/shadow-cljs.png")]
             ["assets/splash.png" (cp "assets/splash.png")]
             ["src/{{sanitized}}/app.cljs" (render "app.cljs" data)]
             ["src/{{sanitized}}/db.cljs" (render "db.cljs" data)]
             ["src/{{sanitized}}/events.cljs" (render "events.cljs" data)]
             ["src/{{sanitized}}/subs.cljs" (render "subs.cljs" data)]
             ["src/reagent/dom.cljs" (render "dom.cljs" data)])))
