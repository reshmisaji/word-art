(ns word-art.core
    (:require
      [reagent.core :as r] [clojure.string :as str]))
;; -------------------------
;; Views

(defonce color (r/atom ""))
(defonce word (r/atom ""))
(defonce show (r/atom false))
(defonce words (r/atom []))
(defonce colors (r/atom []))


(defn word-input []
  [:div.word-input
   "Words: "
   [:input {:type "text"
            :placeholder "Enter the words"
            :value @word
            :on-change #(reset! word (-> % .-target .-value))
            :on-blur #(reset! words (str/split @word #","))
            }]])

(defn color-input []
  [:div.color-input
   "Color: "
   [:input {:type "text"
            :placeholder "Enter the Color"
            :value @color
            :on-change #(reset! color (-> % .-target .-value))
            :on-blur #(reset! colors (str/split @color #","))
           }]])

(defn should-show [] (and (pos? (count @words)) (pos? (count @colors))))

(defn get-color []  (nth @colors (int (rand (count @colors)))))

(defn get-word []  (nth @words (int (rand (count @words)))))

(defn get-font []  (nth (range 12 23) (int (rand (count (range 12 23))))))

(defn submit []
      [:div
      [:button
       {:on-click #(reset! show true)}
       "Submit"
       ]])

(defn shape []
 (when (and (should-show) @show)
  [:result-shape
  [:div
   (for [i (range 20)]
        [:div
         (for [i (range 20)]
              [:span
               {:style {:color (get-color) :font-size (get-font)}}
               (get-word)
               ])
         ]
        )
   ]
  ]))

(defn circle [] [:div   {:style {
                                 :width 300
                                 :height 300
                                 :border-radius "50%"
                                 :overflow "hidden"
                                 }}
                 (shape)
                 ])
(defn square [] [:div   {:style {
                                 :width 300
                                 :height 300
                                 :border-radius "5%"
                                 :overflow "hidden"
                                 }}
                 (shape)
                 ])


(defn home-page []
  [:div
   [word-input]
   [color-input]
   [submit]
   [circle]
   [square]])
 (defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
