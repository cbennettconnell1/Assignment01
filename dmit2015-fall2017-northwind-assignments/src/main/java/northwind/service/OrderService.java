package northwind.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;


import northwind.data.OrderRepository;
import northwind.model.Order;

@Stateless
public class OrderService {

	@Inject
	private Logger log;
	
	@Inject
	private OrderRepository orderRepository;
	
	public Order findOneOrder(int orderId) {
		return orderRepository.find(orderId);
	}
	
	public void createOrder(Order newOrder) {
		try {
			orderRepository.persist(newOrder);
		} catch(Exception e) {
			log.info(e.getMessage());
		}
	}
}
