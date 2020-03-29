;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at http://mozilla.org/MPL/2.0/.
;;
;; This Source Code Form is "Incompatible With Secondary Licenses", as
;; defined by the Mozilla Public License, v. 2.0.
;;
;; Copyright (c) 2015-2020 Andrey Antukh <niwi@niwi.nz>

(ns uxbox.util.time
  (:require
   ["date-fns/format" :as df-format]
   ["date-fns/formatDistanceToNow" :as df-format-distance]
   ["date-fns/locale/fr" :as df-fr-locale]
   ["date-fns/locale/en-US" :as df-en-locale]
   [goog.object :as gobj]))

(def ^:private locales
  #js {:default df-en-locale
       :en df-en-locale
       :en_US df-en-locale
       :fr df-fr-locale
       :fr_FR df-fr-locale})

(defn now
  "Return the current Instant."
  []
  (js/Date.))

(defn format
  ([v fmt] (format v fmt nil))
  ([v fmt {:keys [locale]
           :or {locale "default"}}]
   (df-format v fmt #js {:locale (gobj/get locales locale)})))

(defn timeago
  ([v] (timeago v nil))
  ([v {:keys [seconds? locale]
       :or {seconds? true
            locale "default"}}]
   (df-format-distance v
                       #js {:includeSeconds seconds?
                            :addSuffix true
                            :locale (gobj/get locales locale)})))
