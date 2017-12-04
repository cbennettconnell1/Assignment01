package northwind.service;

import javax.ejb.Stateless;
import javax.enterprise.context.spi.Context;
import javax.inject.Inject;

import northwind.data.OrderRepository;
import northwind.model.Order;
import northwind.model.OrderDetail;

@Stateless
public class OrderService {

	@Inject
	private OrderRepository orderRepository;
	
	public Order findOneOrder(int orderId) {
		return orderRepository.find(orderId);
	}
	
	//assignment5
	public void removeOrder(Order currentOrder) {
		orderRepository.remove(currentOrder);
	}
}
