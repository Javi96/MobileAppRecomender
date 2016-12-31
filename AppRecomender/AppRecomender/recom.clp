(import appRecomendations.model.*)
(deftemplate User       	(declare (from-class User)))
(deftemplate Aplications	(declare (from-class App)))
(deftemplate Like 			(slot nick) (slot app)(slot fav))
;;(deftemplate Profiles   (declare (from-class Profiles)))
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
			    (modify ?u (language fra�ais))
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


			;; ------------------------------------------------------------
			;;-------------------REGLAS ECLVL USUARIO----------------------
			;;-------------------------------------------------------------
			
				;; ------------------------------------------------------------
				;;----------------ESPA�A FRANCIA ITALIA------------------------
				;;-------------------------------------------------------------
				

			(defrule ecLvlSFILow
	        	?u <- (User {country == "Spain" && country == "Italy" && country == "France"}
        		{age < 12})
    			=>
	        	(modify ?u (ecLvl low))
        	)
			
			(defrule ecLvlSFIMedium
	        	?u <- (User {country == "Spain" && country == "Italy" && country == "France"}
        		{age > 12 && age < 25})
    			=>
	        	(modify ?u (ecLvl medium))
        	)

			(defrule ecLvlSFIHigh
	        	?u <- (User {country == "Spain" && country == "Italy" && country == "France"}
        		{age > 25})
    			=>
	        	(modify ?u (ecLvl high))
        	)

				;; ------------------------------------------------------------
				;;----------------ESPA�A FRANCIA ITALIA------------------------
				;;-------------------------------------------------------------

			(defrule ecLvlGermanyMedium
    			?u <- (User {country == "Germany"}
        		{age < 25})
    			=>
	        	(modify ?u (ecLvl medium))
        	)
			
			(defrule ecLvlGermanyHigh
    			?u <- (User {country == "Germany"}
        		{age > 25})
    			=>
	        	(modify ?u (ecLvl high))
        	)
			
			;; ------------------------------------------------------------
			;;----------------REGLAS GUSTOS USUARIO PERFIL-----------------
			;;-------------------------------------------------------------

			(defrule otaku
    			(User {age > 15 && age < 26} {country == "Spain"} (name ?nick))
    			=>
    			(assert (Like (nick ?nick) (app "Vocaloid") (fav 1)))
    			(assert (Like (nick ?nick) (app "Manga") (fav 1)))
    			(assert (Like (nick ?nick) (app "Animacion") (fav 1)))
    		)
			
			(defrule ratKid
    			(User {age < 14} 
        		{country == "Spain" && country == "France"}
        		(name ?nick))
    			=>
    			(assert (Like (nick ?nick) (app "Aventura") (fav 1)))
    			(assert (Like (nick ?nick) (app "Deporte") (fav 1)))
    			(assert (Like (nick ?nick) (app "Comedia") (fav 1)))
    			(assert (Like (nick ?nick) (app "Rap") (fav 1)))
    		)

