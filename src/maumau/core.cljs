(ns maumau.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [sablono.core :as html :refer-macros [html]]
            [maumau.cards :as cards]
            [maumau.game :as game]))

(enable-console-print!)

(defonce app-state
  (atom {:game-state (game/init-game-state 2)}))

(defui DrawingCardsPile
  Object
  (render [this]
          (html
           [:div (str "Drawing pile cards: "
                      (count (om/props this)))])))

(def drawing-cards-pile (om/factory DrawingCardsPile))

(defui PlayedCardsPile
  Object
  (render [this]
          (html
           [:div (str "Played cards: "
                      (count (om/props this)))])))

(def played-cards-pile (om/factory PlayedCardsPile))

(defn humanize-card [card]
  (let [rank (-> card :rank name clojure.string/capitalize)
        suit (-> card :suit name clojure.string/capitalize)]
    (str rank " of " suit)))

(defui Card
  Object
  (render [this]
          (html
           [:li (humanize-card (om/props this))])))

(def card (om/factory Card {:keyfn (fn [card]
                                     (str (:rank card) (:suit card)))}))

(defui Player
  Object
  (render [this]
          (let [player-number (:player-number (om/props this))
                hand (:hand (om/props this))]
            (html
             [:div
              [:h1 (str "Player " player-number)]
              [:ul (map card hand)]]))))

(def player (om/factory Player {:keyfn :player-number}))

(defui App
  Object
  (render [this]
          (let [game-state (:game-state (om/props this))
                {:keys [drawing-pile playing-pile players]} game-state]
            (html
             [:div
              (drawing-cards-pile drawing-pile)
              (played-cards-pile playing-pile)
              (map player players)]))))

(def app (om/factory App))

(def reconciler
  (om/reconciler {:state app-state}))

(om/add-root! reconciler
              App
              (gdom/getElement "app"))
