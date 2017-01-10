package appRecomender;

import java.util.ArrayList;
import java.util.Collection;

import appRecomender.model.App;
import appRecomender.model.User;

/**
 * A toy implementation of the Database interface with some hard-coded order
 * data.
 */

public class DataBase {
	// ------------------------------------------
	private ArrayList<User> users;
	private ArrayList<App> apps;

	public DataBase() {
		createUsers();
		createApps();

	}

	private void createApps() {
		apps = new ArrayList<App>();

		apps.add(new App("Clash Royale", (float) 10.00, "Action"));
		apps.add(new App("Clash of Clans", (float) 5.00, "Action"));
		apps.add(new App("Hay Day", (float) 2.00, "Action"));
		apps.add(new App("Super Mario Run", (float) 10.00, "Action"));

		apps.add(new App("MARVEL Batalla de Superheroes", (float) 0.00,
				"Adventure"));
		apps.add(new App("N.O.V.A. 3", (float) 15.00, "Adventure"));
		apps.add(new App("Plants vs. Zombies 2", (float) 10.00, "Adventure"));

		apps.add(new App("EA SPORTS UFC", (float) 5.00, "Sports"));
		apps.add(new App("FIFA 16 Ultimate Team", (float) 5.00, "Sports"));
		apps.add(new App("Trial Xtreme 3", (float) 5.00, "Sports"));

		apps.add(new App("Boom Beach", (float) 0.00, "Strategy"));
		apps.add(new App("Mobile Strike", (float) 6.00, "Strategy"));
		apps.add(new App("Tekken Card Tournament", (float) 3.50, "Strategy"));

		apps.add(new App("Linkedln", (float) 0.00, "RRSS"));
		apps.add(new App("Twitter", (float) 0.00, "RRSS"));
		apps.add(new App("Whatsapp", (float) 3.00, "RRSS"));
		apps.add(new App("Facebook", (float) 0.00, "RRSS"));
		apps.add(new App("LINE", (float) 5.00, "RRSS"));
		apps.add(new App("Messenger", (float) 0.00, "RRSS"));
		apps.add(new App("Skype", (float) 0.00, "RRSS"));
		apps.add(new App("Telegram", (float) 0.00, "RRSS"));

		apps.add(new App("BBC News", (float) 0.00, "News"));
		apps.add(new App("El Mundo", (float) 0.00, "News"));
		apps.add(new App("El PAIS", (float) 0.00, "News"));
		apps.add(new App("NYTimes", (float) 0.00, "News"));

		apps.add(new App("EMT Madrid", (float) 0.00, "Navigation"));
		apps.add(new App("Google Maps", (float) 0.00, "Navigation"));
		apps.add(new App("Tom Tom GO Mobile", (float) 15.00, "Navigation"));

		apps.add(new App("Casa del Libro", (float) 0.00, "Books"));
		apps.add(new App("DC Comics", (float) 2.00, "Books"));
		apps.add(new App("Ebook Reader", (float) 0.00, "Books"));
		apps.add(new App("Google Play Books", (float) 0.00, "Books"));
		apps.add(new App("Guinness World Records 2014", (float) 0.00, "Books"));
		apps.add(new App("iCreate", (float) 0.00, "Books"));
		apps.add(new App("Marvel Comics", (float) 0.00, "Books"));
		apps.add(new App("Scribd", (float) 0.00, "Books"));

		apps.add(new App("2048", (float) 3.00, "Puzzle"));
		apps.add(new App("Angry Birds 2", (float) 2.00, "Puzzle"));
		apps.add(new App("Candy Crush Saga", (float) 0.00, "Puzzle"));
	}

	private void createUsers() {
		users = new ArrayList<User>();
		users.add(new User("Pedro", 1995, 'm', "Spain"));
		users.add(new User("Javi", 2004, 'm', "Spain"));
	}

	public Collection<User> getUsers() {
		return users;
	}

	public Collection<App> getApps() {
		return apps;
	}
}
