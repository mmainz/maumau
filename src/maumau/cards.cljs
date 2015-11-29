(ns maumau.cards)

(def ranks
  #{:2 :3 :4 :5 :6 :7 :8 :9 :10 :joker :queen :king :ace})

(def suits
  #{:clubs :diamonds :hearts :spades})

(defrecord Card [rank suit])

(defn generate-shuffled-deck []
  (shuffle
   (for [rank ranks
         suit suits]
     (Card. rank suit))))

(defn draw [n pile]
  (split-at n pile))
