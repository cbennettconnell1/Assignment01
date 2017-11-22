package northwind.service;


import javax.ejb.Stateless;
import javax.inject.Inject;


import northwind.data.OrderRepository;

import northwind.model.Order;

@Stateless
public class OrderService {


	
	@Inject
	private OrderRepository orderRepository;
	
	public Order findOneOrder(int orderId) {
		return orderRepository.find(orderId);
	}

}
