package appRecomendations.Demo;

import java.util.Iterator;

import jess.JessException;
import pricing.PricingEngine;
import pricing.demo.DemoDatabase;
import appRecomendations.DataBase;
import appRecomendations.Engine;

public class Demo {

	public static void main(String[] args) {
		try {
			DataBase database = new DataBase();
			
			Engine engine = new Engine(database);
	

		} catch (JessException e) {
			e.printStackTrace();
		}

	}

	private static void processOrder(DemoDatabase database, PricingEngine engine, int orderNumber) throws JessException {
		Iterator items;
		Iterator offers;
		System.out.println("Items for order " + orderNumber + ":");
		items = database.getOrder(orderNumber).getItems();
		while (items.hasNext()) {
			System.out.println("   " + items.next());
		}			
		
		offers = engine.run(orderNumber);
		System.out.println("Offers for order " + orderNumber + ":");
		while (offers.hasNext()) {
			System.out.println("   " + offers.next());
		}
		System.out.println();
	}

}

