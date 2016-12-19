package appRecomendations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import appRecomendations.model.App;
import appRecomendations.model.Profiles;
import appRecomendations.model.User;

/**
 * A toy implementation of the Database interface with some
 * hard-coded order data.
 */

public class DataBase{

	private ArrayList users;
	private ArrayList apps;
	private Profiles profiles;

	public DataBase() {
		createUsers();
		createApps();
		createProfiles();
	}
	
	private void createApps() {
		apps = new ArrayList();
		apps.add(new App("transistor", (float)17.00, new String[] {"Game", "OST", "Romantic"}));	
		apps.add(new App("Minecraft", (float)5.00, new String[] {"Game", "OST", "Sand box"}));		
		apps.add(new App("Clash royale", (float)0.00, new String[] {"Game", "OST", "Pay to win++"}));		
		apps.add(new App("Clash of clans", (float)0.00, new String[] {"Game", "OST", "Pay to win"}));		
		apps.add(new App("Mario run", (float)10.00, new String[] {"Game", "OST", "Lateral Scroll"}));		
		apps.add(new App("Nisekoi", (float)1.00, new String[] {"Manga", "Romantic", "Comedy"}));	
		apps.add(new App("Boom beach", (float)0.00, new String[] {"Game", "OST", "pay to win"}));		
		apps.add(new App("Pokemon Go", (float)1.00, new String[] {"Game", "OST", "Walk"}));	
		apps.add(new App("GPS", (float)1.00, new String[] {"Functionality", "gadget"}));
		

	}

	private void createUsers() {
		users = new ArrayList();
		users.add(new User("Pedro", "21", "España"));
		users.add(new User("Javi", "20", "España"));
		users.add(new User("ChingYon", "22", "China"));
		users.add(new User("Lorem", "21", "Francia"));
		users.add(new User("Rowlet", "15", "Alola"));
	}

	private void createProfiles(){
		profiles = new Profiles();
		
		ArrayList likeR = new ArrayList();
		likeR.add("Game");
		likeR.add("shooter");
		likeR.add("sandbox");
		profiles.put((String)"RatKid", likeR);
		
		ArrayList likeO = new ArrayList();
		likeO.add("Game");
		likeO.add("Vocaloid");
		likeO.add("Manga");
		profiles.put((String)"Otaku", likeO);
		
		
	}
	
	public Collection getUsers() {
		return users;
	}

	public Collection getApps() {
		return apps;
	}
	public Profiles getProfiles(){
		return profiles;
	}
}
