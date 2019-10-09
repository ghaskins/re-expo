# Re-Frame on React Native

This is an example project using: [shadow-cljs](https://github.com/thheller/shadow-cljs), [React Native](https://facebook.github.io/react-native/), [Expo](https://expo.io/), [Reagent](https://reagent-project.github.io/), and [re-frame](https://github.com/Day8/re-frame).

Here follows instructions for getting started:

## Usage
```sh
$ npm install -g expo-cli
$ lein shadow watch app
# wait for first compile to finish or expo gets confused 
# on another terminal tab/window:
$ expo start
```
This will run Expo DevTools at http://localhost:19002/

To run the app in browser using expo-web (react-native-web), press `w` in the same terminal after expo devtools is started.
This should open the app automatically on your browser after the web version is built. If it does not open automatically, open http://localhost:19006/ manually on your browser.

### Using clojurescript REPL
Once the app is deployed and opened in phone/simulator/emulator/browser, connect to the nrepl on port 9000 and run the following:
```clojure
(shadow/nrepl-select :app)
```

```clojure
(js/alert "Hello from Repl")
```

## Production builds

A production build involves first asking shadow-cljs to build a release, then to ask Expo to work in Production Mode.

1. Kill the watch and expo tasks.
1. Execute `lein shadow release app`
1. Start the expo task (as per above)
   1. Enable Production mode.
   1. Start the app.

If you get complaints about [Module HMRClient is not a registered callable module](https://github.com/expo/expo/issues/916)*, you probably have **Hot reloading** enabled. Disable it and try again.

## Some notes from Thomas Heller

(This project is built from this example of his: https://github.com/thheller/reagent-expo)

The `:app` build will create an `App/index.js`. In `release` mode that is the only file needed. In dev mode the `App` directory will contain many more `.js` files.

`:init-fn` is called after all files are loaded and in the case of `expo` must render something synchronously as it will otherwise complain about a missing root component. The `shadow.expo/render-root` takes care of registration and setup.

You should disable the `expo` live reload stuff and let `shadow-cljs` handle that instead as they will otherwise interfere with each other.

Source maps don't seem to work properly. `metro` propably doesn't read input source maps when converting sources as things are correctly mapped to the source .js files but not their sources.

Initial load in dev is quite slow since `metro` processes the generated `.js` files.

`reagent.core` loads `reagent.dom` which will load `react-dom` which we don't have or need. Including the `src/main/reagent/dom.cljs` to create an empty shell. Copied from [re-natal](https://github.com/drapanjanas/re-natal/blob/master/resources/cljs-reagent6/reagent_dom.cljs).
