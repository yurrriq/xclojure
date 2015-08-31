(ns allergies)

(def ^:private allergens
  [:eggs :peanuts :shellfish :strawberries :tomatoes :chocolate :pollen :cats])

(defn- flagged?
  [flags index]
  (-> (bit-shift-right flags index)
      (bit-and 1)
      (pos?)))

(defn allergies
  "Given an 8-bit bitmap of flags, return the list of matching allergens."
  [flags]
  (mapv last
    (filter
      (fn [[index allergen]]
        (flagged? flags index))
      (map-indexed vector allergens))))

(defn allergic-to?
  "Given an 8-bit bitmap of flags and an allergen, return a boolean
  indicating whether or not the patient is allergic to the given allergen."
  [flags allergen]
  (some #{allergen} (allergies flags)))
