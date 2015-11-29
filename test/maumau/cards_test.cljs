(ns maumau.cards-test
  (:require [maumau.cards :as cards]
            [cljs.test :as t :refer-macros [deftest testing is]]))

(def valid-card-ranks
  #{:2 :3 :4 :5 :6 :7 :8 :9 :10 :joker :queen :king :ace})

(def valid-card-suits
  #{:clubs :diamonds :hearts :spades})

(deftest generate-shuffled-deck
  (testing "returns a collection of Card records"
    (is (every? #(instance? cards/Card %1)
                (cards/generate-shuffled-deck))))

  (testing "only returns cards with valid ranks"
    (is (every? #(contains? valid-card-ranks (:rank %1))
                (cards/generate-shuffled-deck))))

  (testing "only returns cards with valid suits"
    (is (every? #(contains? valid-card-suits (:suit %1))
                (cards/generate-shuffled-deck))))

  (testing "returns 52 cards"
    (is (= (count (cards/generate-shuffled-deck)) 52)))

  (testing "every card is unique"
    (let [deck (cards/generate-shuffled-deck)]
      (is (= (count deck) (count (set deck))))))

  (testing "card order is random"
    (is (not= (cards/generate-shuffled-deck)
              (cards/generate-shuffled-deck)))))
