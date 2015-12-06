(ns maumau.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [maumau.cards :as cards]
            [maumau.game :as game]))

(enable-console-print!)

(defonce app-state
  (atom {:game-state (game/init-game-state 2)}))

(defui App
  Object
  (render [this]
          (let [game-state (:game-state (om/props this))
                {:keys [drawing-pile playing-pile players]} game-state]
            (dom/div nil
                     (dom/div nil (str "Drawing pile cards: "
                                       (count drawing-pile)))
                     (dom/div nil (str "Played cards: "
                                       (count playing-pile)))
                     (dom/div nil (str "Players: "
                                       (count players)))))))

(def app (om/factory App))

(def reconciler
  (om/reconciler {:state app-state}))

(om/add-root! reconciler
              App
              (gdom/getElement "app"))
