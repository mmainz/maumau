(ns utils.runner
  (:require [doo.runner :refer-macros [doo-all-tests]]
            [maumau.cards-test]))

(doo-all-tests)
