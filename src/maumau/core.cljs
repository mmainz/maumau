(ns maumau.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [maumau.cards :as cards]
            [maumau.game :as game]))

(enable-console-print!)

(defonce app-state
  (atom {:game-state (game/init-game-state 2)}))

(om/root
 (fn [data owner]
   (reify om/IRender
     (render [_]
       (dom/h1 nil (:text data)))))
 app-state
 {:target (. js/document (getElementById "app"))})
