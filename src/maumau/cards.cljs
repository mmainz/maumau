(ns maumau.cards
  (:require [schema.core :as s :include-macros true]))

(def ranks
  #{:2 :3 :4 :5 :6 :7 :8 :9 :10 :joker :queen :king :ace})

(def Rank (apply s/enum ranks))

(def suits
  #{:clubs :diamonds :hearts :spades})

(def Suit (apply s/enum suits))

(s/defrecord Card
    [rank :- Rank
     suit :- Suit])

(s/defschema Deck [Card])

(s/defn generate-shuffled-deck :- [Card]
  []
  (apply list
         (shuffle
          (for [rank ranks
                suit suits]
            (Card. rank suit)))))

(s/defn draw :- [(s/one [Card] "drawn-cards")
                 (s/one Deck "new-deck")]
  [n :- s/Num
   deck :- Deck]
  (let [drawn-cards (vec (take n deck))
        new-deck (apply list (drop n deck))]
    [drawn-cards new-deck]))
