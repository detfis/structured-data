(ns structured-data)

(defn do-a-thing [x]
  (let [xx (+ x x )]
  (Math/pow xx xx)))

(defn spiff [v]
  (+ (get v 0) (get v 2)))

(defn cutify [v]
  (conj v "<3"))

(defn spiff-destructuring [v]
  (let [[x y z] v]
    (+ x z)))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (Math/abs (- x1 x2))))

(defn height [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (Math/abs (- y1 y2))))

(defn square? [rectangle]
  (= (height rectangle) (width rectangle)))

(defn area [rectangle]
  (* (height rectangle) (width rectangle)))

(defn contains-point? [rectangle point]
  (let [
        [p1 p2] point
        [[x1 y1] [x2 y2]] rectangle
        ]
    (and (and (<= x1 p1) (>= x2 p1)) (and (<= y1 p2) (>= y2 p2)))))

(defn contains-rectangle? [outer inner]
  (let [
        [i_point_1 i_point_2] inner
        ]
    (and (contains-point? outer i_point_1) (contains-point? outer i_point_2))))

(defn title-length [book]
  (count (:title book)))

(defn author-count [book]
  (count (:authors book)))

(defn multiple-authors? [book]
  (> (author-count book) 1))

(defn add-author [book new-author]
  (let [authors (:authors book)
        new-authors (conj authors new-author)]   
    (assoc book :authors new-authors)))

(defn alive? [author]
  (not (contains? author :death-year)))

(defn element-lengths [collection]
  (map count collection))

(defn second-elements [collection]
  (let [get-second (fn [x] (get x 1))]
    (map get-second collection)))

(defn titles [books]
  (map :title books))

(defn monotonic? [a-seq]
  (or (apply <= a-seq) (apply >= a-seq)))

(defn stars [n]
  (apply str (repeat n "*")))

(defn toggle [a-set elem]
  (if (contains? a-set elem)
    (disj a-set elem)
    (conj a-set elem)))

(defn contains-duplicates? [a-seq]
  (not (= (count a-seq) (count (set a-seq)))))

(defn old-book->new-book [book]
  (let [authors (:authors book)]
    (assoc book :authors (set authors))))

(defn has-author? [book author]
  (contains? (:authors book) author))

(defn authors [books]
  (apply clojure.set/union (map :authors books)))

(defn all-author-names [books]
  (set(map :name (authors books))))

(defn author->string [author]
  (let [name (:name author)
        date_present? (contains? author :birth-year)]
    (if date_present?
      (str name, " (", (:birth-year author), " - ", (:death-year author), ")")
      (str name))))

(defn authors->string [authors]
  (clojure.string/join ", " (map author->string authors)))

(defn book->string [book]
  (str (:title book), ", written by ", (authors->string (:authors book))))

(defn books->string [books]
  (cond 
    (empty? books) "No books."
    (= 1 (count books)) (str "1 book. ", (book->string (first books)), ".")
    :else (str (count books), " books. ", (clojure.string/join ". " (map book->string books)))))
    

(defn books-by-author [author books]
  (filter (fn [x] (has-author? x author)) books))

(defn author-by-name [name authors]
  (first (filter (fn [x] (= (:name x) name)) authors)))

(defn living-authors [authors]
  (filter alive? authors))

(defn has-a-living-author? [book]
  (not (empty? (living-authors (:authors book)))))

(defn books-by-living-authors [books]
  (filter has-a-living-author? books))

; %________%
