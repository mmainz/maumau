(ns maumau.game-test
  (:require [maumau.game :as game]
            [cljs.test :as t :refer-macros [deftest testing is]]))

(deftest init-game-state
  (let [game-state (game/init-game-state 3)]
    (testing "drawing pile is generated"
      (is (contains? game-state :drawing-pile))
      (is (= (count (:drawing-pile game-state)) 52)))

    (testing "discarded pile is generated"
      (is (contains? game-state :playing-pile))
      (is (= (:playing-pile game-state) '())))

    (testing "generates player states"
      (let [players (:players game-state)]
        (is (contains? game-state :players))
        (is (vector? players))
        (is (= (count players) 3))
        (is (every? #(= {:hand []} %) (map #(select-keys % [:hand]) players)))
        (is (= (map :player-number players) [0 1 2]))))))

(deftest draw-hand-for-player
  (let [game-state (game/init-game-state 4)]
    (testing "moves the top five drawing pile cards to player hand"
      (let [new-state (game/draw-hand-for-player game-state 0)]
        (is (= (take 5 (:drawing-pile game-state))
               (get-in new-state [:players 0 :hand]))))
      (let [new-state (game/draw-hand-for-player game-state 2)]
        (is (= (take 5 (:drawing-pile game-state))
               (get-in new-state [:players 2 :hand])))))

    (testing "removes the drawn cards from the drawing pile"
      (let [new-state (game/draw-hand-for-player game-state 1)]
        (is (= (drop 5 (:drawing-pile game-state))
               (:drawing-pile new-state)))))))
