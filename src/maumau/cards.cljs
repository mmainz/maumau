(ns maumau.cards)

(def ranks
  #{:2 :3 :4 :5 :6 :7 :8 :9 :10 :joker :queen :king :ace})

(def suits
  #{:clubs :diamonds :hearts :spades})

(defrecord Card [rank suit])

(defn generate-shuffled-deck []
  (apply list
        (shuffle
         (for [rank ranks
               suit suits]
           (Card. rank suit)))))

(defn draw [n pile]
  (let [drawn-hand (vec (take n pile))
        new-pile (apply list (drop n pile))]
    [drawn-hand new-pile]))
