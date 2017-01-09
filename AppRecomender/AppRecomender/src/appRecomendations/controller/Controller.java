package appRecomendations.controller;

import java.util.ArrayList;
import java.util.Observer;

import jess.JessException;
import appRecomendations.DataBase;
import appRecomendations.Engine;
import appRecomendations.model.App;
import appRecomendations.model.User;
import appRecomendations.view.View;

public class Controller {

	private Engine model;

	private View view;

	private User user;

	public Controller(Engine model, View view) {
		user = new User("Pedro", 1995, 'm', "Spain");
		this.model = model;
		try {
			model.run();
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.view = view;
		this.view.start(this);

		model.addObserver(view);
		// this.user = (User) model.getUsers();
	}

	public void add(Observer o) {
		model.addObserver(o);
	}

	public static void main(String[] args) {
		try {
			new Controller(new Engine(new DataBase()), new View());
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void act(App app) {
		model.act(user, app);

	}

	public ArrayList<App> getRecom() {
		System.out.println("GetRecomCtrl");
		return model.getRecom(user);
	}

	public User getUser() {
		return user;
	}

	public void newUser(User user) {
		User tempUser = model.newUser(user);
		if (tempUser != null) {
			this.user = tempUser;
		}

	}

	public void changeUser(User user) {
		System.err.println(this.user.getName());
		User tempUser = model.changeUser(user);
		if (tempUser != null) {
			this.user = tempUser;
			getRecom();
		}
		
		System.err.println(user.getName());

	}
}