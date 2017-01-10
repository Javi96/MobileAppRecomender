package appRecomender.model;

/**
 * @author Javi
 * 
 */
public class User {

	private String name, language, country;
	private char sex;
	private int age, birthYear;
	private float ecLvl;

	public User(String name, int birthYear, char sex, String country) {
		this.name = name;
		this.birthYear = birthYear;
		this.sex = sex;
		this.country = country;
		this.language = "english";
		this.ecLvl=5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getbirthYear() {
		return birthYear;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getEcLvl() {
		return ecLvl;
	}

	public void setEcLvl(float ecLvl) {
		this.ecLvl = ecLvl;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", language=" + language + ", country="
				+ country + ", sex=" + sex + ", age=" + age + ", birthYear="
				+ birthYear + ", ecLvl=" + ecLvl + "]";
	}

}
