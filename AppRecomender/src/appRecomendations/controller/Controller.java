package appRecomendations.controller;


import java.util.ArrayList;
import java.util.Observer;

import javax.swing.ImageIcon;

import jess.JessException;
import appRecomendations.DataBase;
import appRecomendations.Engine;
import appRecomendations.model.App;
import appRecomendations.model.User;
import appRecomendations.view.View;



public class Controller{

	private Engine model;
	
	private View view;
	
	private User user;
		
	public Controller(Engine model, View view){
		user = new User("Pedro", 1995,'m', "Spain");
		this.model = model;
		try {
			model.run();
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.view = view;
		this.view.start2(this);
		
		model.addObserver(view);
		//this.user = (User) model.getUsers();
	}
	
	public void add(Observer o) {
		model.addObserver(o);
	}
	
	public static void main(String[] args)  {
		Controller ctrl;
		try {
			ctrl = new Controller(new Engine(new DataBase()), new View());
			ctrl.start();
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void start() {
		//view.start(this);
		model.start();
	}

	public ArrayList<ImageIcon> getImageIconList(){
		return model.getImageIconList();
	}

	public String getImageId(int i) {
		return model.getImageId(i);
	}

	public void act(App app) {
		model.act(user, app);
		
	}

	public  ArrayList<App> getRecom() {
		System.out.println("GetRecomCtrl");
		return model.getRecom(user);
	}	
	
}