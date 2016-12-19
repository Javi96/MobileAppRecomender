package appRecomendations.model;

import java.util.ArrayList;

public class App {
	private String name;
	private float prize;
	private String superCat;
	private ArrayList categoryList;
	
	public App(String name, float prize, String[] cat){
		this.name = name;
		this.prize = prize;
		this.superCat = cat[0];
		categoryList = new ArrayList();
		for(int i = 1; i < cat.length; i++){
			this.categoryList.add(cat[i]);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrize() {
		return prize;
	}

	public void setPrize(float prize) {
		this.prize = prize;
	}

	public String getSuperCat() {
		return superCat;
	}

	public void setSuperCat(String superCat) {
		this.superCat = superCat;
	}

	public ArrayList getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList categoryList) {
		this.categoryList = categoryList;
	}
}
