package northwind.service;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import northwind.data.OrderRepository;
import northwind.model.Order;

@Stateless
public class OrderService {
	
	@Resource
	private EJBContext context;
	

	@Inject
	private OrderRepository orderRepository;
	
	public Order findOneOrder(int orderId) {
		return orderRepository.find(orderId);
	}
	
	public Order updateOrder(Order currentOrder)
	{
		return orderRepository.edit(currentOrder);
	}
		
}
