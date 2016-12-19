package appRecomendations;

import java.util.Iterator;

import jess.Fact;
import jess.JessException;
import jess.QueryResult;
import jess.Rete;
import jess.ValueVector;
import jess.WorkingMemoryMarker;

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
		Fact f ;
		while (listF.hasNext()) {
			f = (Fact)listF.next();
			System.out.println(f.toStringWithParens());
		}
		
		// Return the list of offers created by the rules
		/*QueryResult result =
	            engine.runQueryStar("users", new ValueVector().add("Otaku"));
	        while (result.next()) {
	        	System.out.println("Hola");
	            System.out.println(result.getString("nick") + " " + result.getString("profileName"));
	        }*/
	        
	        
		//return engine.runQueryStar("users", new ValueVector().add("Otaku"));
	        return null;
	}
}