package controller;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import model.Model;
import model.observer.Observer;
import view.View;



public class Controller implements Observer{

	private Model model;
	
	private View view;
		
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
		this.model.addObserver(this);
	}
	
	public void add(Observer o) {
		model.addObserver(o);
	}
	
	public static void main(String[] args) {
		Controller ctrl = new Controller(new Model(), new View());
		ctrl.start();
	}

	public void start() {
		view.start(this);
		model.start();
	}

	public ArrayList<ImageIcon> getImageIconList(){
		return model.getImageIconList();
	}

	public void opAppEnd() {
		// TODO Auto-generated method stub
		
	}

	public void opAppStart(List<Image> actList) {
		// TODO Auto-generated method stub
		
	}

	public void OnUserLogOut() {
		// TODO Auto-generated method stub
		
	}

	public void OnListAct(List<Image> l) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}