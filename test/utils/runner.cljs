(ns utils.runner
  (:require [doo.runner :refer-macros [doo-all-tests]]
            [maumau.cards-test]
            [maumau.game-test]))

(doo-all-tests)
