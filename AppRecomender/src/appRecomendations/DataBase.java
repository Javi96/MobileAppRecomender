package appRecomendations;

import java.util.ArrayList;
import java.util.Collection;

import appRecomendations.model.App;
import appRecomendations.model.Profiles;
import appRecomendations.model.User;

/**
 * A toy implementation of the Database interface with some hard-coded order
 * data.
 */

public class DataBase {
	// ------------------------------------------
	private ArrayList<User> users;
	private ArrayList<App> apps;
	private Profiles profiles;

	public DataBase() {
		createUsers();
		createApps();
		// createProfiles();


	}

	/*
	 * private void createApps() { apps = new ArrayList(); apps.add(new
	 * App("transistor", (float)17.00, new String[] {"Romantic"})); apps.add(new
	 * App("Minecraft", (float)5.00, new String[] {"Game", "OST", "Sand box"}));
	 * apps.add(new App("Clash royale", (float)0.00, new String[] {"Game",
	 * "OST", "Pay to win++"})); apps.add(new App("Clash of clans", (float)0.00,
	 * new String[] {"Game", "OST", "Pay to win"})); apps.add(new
	 * App("Mario run", (float)10.00, new String[] {"Game", "OST",
	 * "Lateral Scroll"})); apps.add(new App("Nisekoi", (float)1.00, new
	 * String[] {"Manga", "Romantic", "Comedy"})); apps.add(new
	 * App("Boom beach", (float)0.00, new String[] {"Game", "OST",
	 * "pay to win"})); apps.add(new App("Pokemon Go", (float)1.00, new String[]
	 * {"Game", "OST", "Walk"})); apps.add(new App("GPS", (float)1.00, new
	 * String[] {"Functionality", "gadget"}));
	 * 
	 * 
	 * }
	 */

	

	private void createApps() {
		apps = new ArrayList<App>();
		apps.add(new App("Clash Royale", (float) 10.00, "Manga"));
		//apps.add(new App("Clash Royale", (float) 0.00, "Action"));
		apps.add(new App("Clash of Clans", (float) 0.00, "Manga"));
		apps.add(new App("Hay Day", (float) 0.00, "Manga"));
		apps.add(new App("Linkedln", (float) 0.00, "Manga"));
		apps.add(new App("Twitter", (float) 0.00, "Manga"));
		apps.add(new App("Whatsapp", (float) 0.00, "Manga"));
	}

	private void createUsers() {
		users = new ArrayList<User>();
		users.add(new User("Pedro", 1995, 'm', "Spain"));
	}

	public Collection<User> getUsers() {
		return users;
	}

	public Collection<App> getApps() {
		return apps;
	}

	public Profiles getProfiles() {
		return profiles;
	}
}
