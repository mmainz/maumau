(ns maumau.cards-test
  (:require [maumau.cards :as cards]
            [cljs.test :as t :refer-macros [deftest testing is]]))

(def valid-card-ranks
  #{:2 :3 :4 :5 :6 :7 :8 :9 :10 :joker :queen :king :ace})

(def valid-card-suits
  #{:clubs :diamonds :hearts :spades})

(deftest generate-shuffled-deck
  (testing "returns a list"
    (is (list? (cards/generate-shuffled-deck))))

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

(deftest draw
  (let [draw-number 5
        pile (cards/generate-shuffled-deck)
        [drawn-hand new-pile :as draw-result] (cards/draw draw-number pile)]

    (testing "returns a two element vector"
      (is (vector? draw-result))
      (is (= (count draw-result) 2)))

    (testing "first element are the drawn cards"
      (is (vector? drawn-hand))
      (is (= (count drawn-hand) draw-number))
      (is (every? #(instance? cards/Card %1) drawn-hand)))

    (testing "second element is the new pile"
      (is (list? new-pile))
      (is (= (count new-pile)
             (- (count pile) draw-number)))
      (is (every? #(instance? cards/Card %1) new-pile)))

    (testing "drawn cards are not in new pile"
      (is (not-any? #(contains? (set new-pile) %1) drawn-hand)))))
