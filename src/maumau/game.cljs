(ns maumau.game
  (:require [maumau.cards :as cards]))

(defn init-game-state [number-of-players]
  {:drawing-pile (cards/generate-shuffled-deck)
   :playing-pile '()
   :players (vec (map (fn [number] {:hand [] :player-number number})
                      (range number-of-players)))})

(defn draw-hand-for-player [game-state player-number]
  (let [[hand new-pile] (cards/draw 5 (:drawing-pile game-state))]
    (-> game-state
        (update-in [:players player-number :hand] into hand)
        (assoc-in [:drawing-pile] new-pile))))
