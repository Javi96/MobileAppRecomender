package appRecomender.model;

public class App {
	private String name;
	private float prize;
	private String categoryList;	
	
	public App(String name, float prize, String categoryList){
		this.name = name;
		this.prize = prize;
		this.categoryList = categoryList;
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

	public String getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(String categoryList) {
		this.categoryList = categoryList;
	}
}
