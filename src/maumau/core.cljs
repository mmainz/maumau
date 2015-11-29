(ns maumau.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello world!"}))

(om/root
 (fn [data owner]
   (reify om/IRender
     (render [_]
       (dom/h1 nil (:text data)))))
 app-state
 {:target (. js/document (getElementById "app"))})
