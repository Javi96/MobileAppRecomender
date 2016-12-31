package pricing;


import java.util.Collection;

import pricing.model.Order;

public interface Database {
	public Collection getCatalogItems();
	public Order getOrder(int orderNumber);
}