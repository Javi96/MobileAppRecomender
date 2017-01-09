package appRecomendations;

import java.util.ArrayList;
import java.util.Observable;

import jess.Fact;
import jess.JessException;
import jess.QueryResult;
import jess.RU;
import jess.Rete;
import jess.Value;
import jess.ValueVector;
import jess.WorkingMemoryMarker;
import appRecomendations.model.App;
import appRecomendations.model.User;

public class Engine extends Observable {
	private Rete engine;
	private WorkingMemoryMarker marker;
	private DataBase database;

	public Engine(DataBase aDatabase) throws JessException {
		// Create a Jess rule engine
		engine = new Rete();
		engine.reset();

		// Load the pricing rules
		engine.batch("recom.clp");

		// Load the catalog data into working memory
		database = aDatabase;
		engine.addAll(database.getUsers());
		engine.addAll(database.getApps());

		// Mark end of catalog data for later
		// marker = engine.mark();
	}

	/*
	 * private void loadOrderData(int orderNumber) throws JessException { //
	 * Retrive the order from the database Order order =
	 * database.getOrder(orderNumber);
	 * 
	 * if (order != null) { // Add the order and its contents to working memory
	 * engine.add(order); engine.add(order.getCustomer());
	 * engine.addAll(order.getItems()); } }
	 */

	public void run() throws JessException {
		engine.run();
	}

	public void act(User u, App app) {

		try {
			System.out.println("Actualizado1");
			ValueVector vv = new ValueVector();
			vv.add(new Value(u.getName(), RU.STRING));
			vv.add(new Value(app.getCategoryList(), RU.STRING));
			engine.eval("(facts)");
			QueryResult it = engine.runQueryStar("getFav", vv);
			it.next();
			System.out.println("salta2");
			Fact f = new Fact("Like", engine);
			System.out.println("salta3");
			f.setSlotValue("nick", new Value(u.getName(), RU.STRING));
			f.setSlotValue("app", new Value(app.getCategoryList(), RU.STRING));
			f.setSlotValue("fav", new Value(it.getInt("fav"), RU.INTEGER));
			engine.modify(f, "fav", new Value(it.getInt("fav") + 1, RU.INTEGER));
			System.out.println("salta4");
			System.out.println("Actualizado2");
		} catch (JessException e) {

			try {
				System.out.println("Actualizado3");
				Fact nf = new Fact("Like", engine);
				nf.setSlotValue("nick", new Value(u.getName(), RU.STRING));
				nf.setSlotValue("app", new Value(app.getCategoryList(),
						RU.STRING));
				nf.setSlotValue("fav", new Value(1, RU.INTEGER));
				engine.assertFact(nf);
				System.out.println("Actualizado4");
			} catch (JessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public ArrayList<App> getRecom(User user) {
		ArrayList<App> apps = new ArrayList<App>();
		try {
			QueryResult result = engine
					.runQueryStar("favoritos", new ValueVector().add(new Value(
							user.getName(), RU.STRING)));
			/*
			 * while (result.next()) {
			 * System.out.println(result.getString("nick") + " " +
			 * result.getString("name")); }
			 */
			while (result.next()) {

				QueryResult qr = engine.runQueryStar("appFinder",
						new ValueVector().add(new Value(result
								.getString("name"), RU.STRING)));
				qr.next();
				if (Math.random() < Math.log(result.getFloat("fav") + 1)) {
					apps.add(new App(qr.getString("name"),
							qr.getFloat("prize"), qr.getString("categoryList")));
				}
				
			}
			notifyAll();
			return apps;

		} catch (JessException e) {
			e.printStackTrace();
		}
		return null;

	}

	public User newUser(User user) {
		try {
			if (!engine
					.runQueryStar(
							"findUser",
							new ValueVector().add(new Value(user.getName(),
									RU.STRING))).next()) {
				engine.add(user);
				return user;
			}
		} catch (JessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User changeUser(User user) {
		try {
			engine.eval("(facts)");

			QueryResult qr = engine.runQueryStar("findUser",
					new ValueVector().add(new Value(user.getName(), RU.STRING)));
			System.out.println(user.getName());
			if (qr.next()) {
				return new User(qr.getString("name"), 0,
						'm', "");
			}
		} catch (JessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
