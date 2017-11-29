package northwind.service;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;


import northwind.data.OrderRepository;
import northwind.exception.IllegalQuantityException;
import northwind.exception.NoProductsException;
import northwind.model.Customer;
import northwind.model.Order;
import northwind.model.Product;

@Stateless
public class OrderService {
	@Inject
	private OrderRepository orderRepository;
	
	public Order findOneOrder(int orderId) {
		return orderRepository.find(orderId);
	}
	
	@Resource
	private EJBContext context;
	
	@Inject
	private EntityManager entityManager;
	
	public int createOrder(
			Customer orderCustomer,
			String shippingAddress,
			String shippingCity,
			String shippingProvince,
			String shippingCountry,
			String shippingPostalCode,
			List<Product> items
			) throws NoProductsException, IllegalQuantityException  {
		int orderId = 0;
		
		if (items == null || items.size() == 0) {
			context.setRollbackOnly();
			throw new NoProductsException("There are no items in the order");			
		}
		
		Order newOrder = new Order();
		newOrder.setCustomer(orderCustomer);
		newOrder.setShipAddress(shippingAddress);
		newOrder.setShipCity(shippingCity);
		newOrder.setShipRegion(shippingProvince);
		newOrder.setShipCountry(shippingCountry);
		newOrder.setShipPostalCode(shippingPostalCode);
		
		Date today = Calendar.getInstance().getTime();
		newOrder.setOrderDate( new Timestamp(today.getTime()) );

		entityManager.persist(newOrder);
		orderId = newOrder.getOrderID();
		
		for (Product singleItem : items) {
			if (singleItem.getQuantityPerUnit().length() < 1) {
				context.setRollbackOnly();
				throw new IllegalQuantityException("Invalid quantity ordered.");
			}
			entityManager.persist(singleItem);
		}		
		
		return orderId;
	}
	

}
