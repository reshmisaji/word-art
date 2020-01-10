(ns word-art.prod
  (:require
    [word-art.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
