(ns maumau.core-test
  (:require [cljs.test :as t :refer-macros [deftest testing is]]))

(deftest js-math
  (testing "1 + 1 = 2"
    (is (= (+ 1 1) 2))))
