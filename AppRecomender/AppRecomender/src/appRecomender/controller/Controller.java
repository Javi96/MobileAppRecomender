package appRecomender.controller;

import java.util.Observer;

import jess.JessException;
import appRecomender.Engine;
import appRecomender.model.App;
import appRecomender.model.User;
import appRecomender.view.View;

/**
 * 
 * Clase controlador del MVC que gestiona la comunicación entre la vista y el modelo
 * 
 * @author Javi
 *
 */
public class Controller {

	/**
	 * Atributos privados
	 */
	
	private Engine model;

	private View view;

	private User user;

	/**
	 * Contructor
	 * @param model modelo de la aplicación
	 * @param view vista de la aplicación
	 */
	public Controller(Engine model, View view) {
		this.user = new User("Pedro", 1995, 'm', "Spain");
		this.model = model;
		try {
			model.run();
		} catch (JessException e) {
			e.printStackTrace();
		}
		this.view = view;
		model.addObserver(view);
		this.view.start(this);
	}

	/**
	 * Añade el modelo a la lista de observers
	 * @param o Observer del API de Java
	 */
	public void add(Observer o) {
		model.addObserver(o);
	}
	
	/**
	 * Actualiza los gustos del usuario en base a 
	 * @param app Aplicación con la que el usuario ha interactuado
	 */
	public void act(App app) {
		model.act(user, app);

	}

	/**
	 * Manda al modelo a actualizar la lista de gustos del usuario
	 */
	public void getRecom() {
		model.getRecom(user);
	}

	/**
	 * Devuelve el usuario conectado
	 * @return Usuario conectado
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Añade un nuevo usuario a la meoria de trabajo y lo situa como el usuario conectado
	 * @param user Nuevo usuario
	 */
	public void newUser(User user) {
		User tempUser = model.newUser(user);
		if (tempUser != null) {
			this.user = tempUser;
		}
	}

	
	public void changeUser(User user) {
		User tempUser = model.changeUser(user);
		if (tempUser != null) {
			this.user = tempUser;
			getRecom();
		}
	}

	public void addUser(User user) {
		model.newUser(user);
	}
}