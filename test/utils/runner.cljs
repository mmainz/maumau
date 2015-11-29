(ns utils.runner
  (:require [doo.runner :refer-macros [doo-all-tests]]
            [maumau.core-test]))

(doo-all-tests)
