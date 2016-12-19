(import gov.sandia.jess.example.pricing.model.*)
(deftemplate User       (declare (from-class User)))
(deftemplate Profiles   (declare (from-class Profiles)))
(deftemplate App 		(declare (from-class App)))
(deftemplate Like 		(slot nick) (slot app))
(deftemplate Profile 	(slot nick) (slot profileName))


(defquery likes
    (declare (variables ?nick))
    (Like (nick ?nick) (app ?app))
    )
(defquery users
    (declare (variables ?profile))
    (Profile (nick ?x) (profileName ?profile))
    )

;; ------------------------------------------------------------
;;-----------------REGLAS PERFILES USUARIO---------------------
;;-------------------------------------------------------------

(defrule usuario1 "Creacion usuario tipo 1"
    (User (name ?x) (age ?y) (country ?z))
    
    (User (name "Pedro") (age ?) (country ?))
    =>
    (assert (Profile (nick ?x) (profileName "Otaku")))
    )
(defrule usuario2 "Creacion usuario tipo 2"
    (User (name ?x) (age ?y) (country ?z))
    
    (User (name "Javi") (age ?) (country ?))
    =>
    (assert (Profile (nick ?x) (profileName "RatKid")))
    )
