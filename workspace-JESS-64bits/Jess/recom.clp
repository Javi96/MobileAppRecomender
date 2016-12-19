(import appRecomendations.model.*)
(deftemplate User       (declare (from-class User)))
;;(deftemplate Profiles   (declare (from-class Profiles)))
;;(deftemplate App 		(declare (from-class App)))
;;(deftemplate Like 		(slot nick) (slot app))
;;(deftemplate Profile 	(slot nick) (slot profileName))

;;(defquery likes
;;    (declare (variables ?nick))
;;    (Like (nick ?nick) (app ?app))
;;    )
;;(defquery users "Encuentra usuarios con un perfil"
;;    (declare (variables ?profileName))
;;    (Profile (nick ?nick) (profileName ?profileName))
;;    )

;;(defrule usuario1 "Creacion usuario tipo 1"
;;    ?o <- (User {name  == "Pedro"})
;;    =>
;;    (assert (Profile (nick ?o.name) (profileName Otaku)))
;;    )
;;(defrule usuario2 "Creacion usuario tipo 2"
;;    ?o <- (User {name  == "Javi"})
;;    =>
;;    (assert (Profile (nick ?o.name) (profileName RatKid)))
;;    )

;; ------------------------------------------------------------
;;-----------------REGLAS PERFILES USUARIO---------------------
;;-------------------------------------------------------------

			;; ------------------------------------------------------------
			;;-----------------REGLAS LENGUAJE USUARIO---------------------
			;;-------------------------------------------------------------


			(defrule languageSpain
			    ?u <- (User {country == "Spain"})
			    =>
			    (modify ?u (language spanish))
			    )
			
			(defrule languageFrace
			    ?u <- (User {country == "Frace"})
			    =>
			    (modify ?u (language fraçais))
			    )
			
			(defrule languageItaly
			    ?u <- (User {country == "Italy"})
			    =>
			    (modify ?u (language italian))
			    )
			
			(defrule languageGermany
			    ?u <- (User {country == "Germany"})
			    =>
			    (modify ?u (language german))
			    )

			;; ------------------------------------------------------------
			;;-------------------REGLAS EDAD USUARIO-----------------------
			;;-------------------------------------------------------------

(deffunction getAge (?date)
	return (- 2016 ?date)
)

(defrule age
    ?u <- (User )
    =>
	(modify ?u (age (getAge ?u.birthYear)))
    )



