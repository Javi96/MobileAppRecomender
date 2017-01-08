package appRecomendations;

import java.util.Iterator;

import jess.Fact;
import jess.JessException;
import jess.QueryResult;
import jess.RU;
import jess.Rete;
import jess.Value;
import jess.ValueVector;
import jess.WorkingMemoryMarker;
import appRecomendations.model.App;
import appRecomendations.model.Launch;
import appRecomendations.model.User;

public class Engine {
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

	public QueryResult run() throws JessException {
		// Remove any previous order data, leaving only catalog data
		// engine.resetToMark(marker);

		// Load data for this order
		// loadOrderData(orderNumber);

		// Fire the rules that apply to this order
		engine.run();
		Iterator listF = engine.listFacts();
		Fact f;
		while (listF.hasNext()) {
			f = (Fact) listF.next();
			System.out.println(f.toStringWithParens());
		}

		System.out
				.println("---------------------------------------------------------");
		// Return the list of offers created by the rules
		QueryResult result =

		engine.runQueryStar("favoritos",
				new ValueVector().add(new Value("Pedro", RU.STRING)));
		while (result.next()) {
			System.out.println(result.getString("app") + " "
					+ result.getString("name"));
		}

		// return engine.runQueryStar("users", new ValueVector().add("Otaku"));
		return null;
	}

	public void act(User u, App app) {

		try {
			ValueVector vv = new ValueVector();
			vv.add(new Value("Pedro", RU.STRING));
			vv.add(new Value("Vocaloid", RU.STRING));
			QueryResult it = engine.runQueryStar("getFav", vv);
			it.next();
			Fact f = new Fact("Like", engine);
			f.setSlotValue("nick", new Value("Pedro", RU.STRING));
			f.setSlotValue("app", new Value("Vocaloid", RU.STRING));
			f.setSlotValue("fav", new Value(it.getInt("fav"), RU.INTEGER));
			engine.modify(f, "fav", new Value(it.getInt("fav") + 1, RU.INTEGER));
		} catch (JessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}