package northwind.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import northwind.data.OrderRepository;
import northwind.model.Order;

@Stateless
public class SalesService {

	@Inject
	private OrderRepository orderRepository;
		
	public void createOrder(Order currentOrder) {
		orderRepository.persist(currentOrder);
	}
	
	public Order updateOrder(Order currentOrder) {
		return orderRepository.edit(currentOrder);
	}
	
	public void removeOrder(Order currentOrder) {
		orderRepository.remove(currentOrder);
	}
	
	public Order findOneOrder(Date orderDate) {
		return orderRepository.find(orderDate);
	}
}
