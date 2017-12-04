package northwind.controller;

import java.util.HashSet;
import java.util.Set;

import org.omnifaces.util.Messages;

import northwind.model.Order;

public class CreateOrderController {
	private Set<Order> orders = new HashSet<>();
	
	//cancel order
	public void cancelOrder(Order currentOrder) {
		orders.remove(currentOrder);
		Messages.addGlobalInfo("Order was cancelled.");
	}
}
