package northwind.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import northwind.data.OrderRepository;
import northwind.model.Order;

@Model   //needed this*****
public class OrderController {
	
	@Inject
	private OrderRepository orderRepository;
	
	private List<Order> orders;
	
	@PostConstruct
	void init() 
	{
		orders = orderRepository.findAll();
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	//Order Details
	private int currentSelectedOrderId; //getter/setter
	private Order currentSelectedOrder;//getter
	
	public void findOrder()
	{
		if(!FacesContext.getCurrentInstance().isPostback())
		{
			if(currentSelectedOrderId > 0)
			{
				currentSelectedOrder = orderRepository.findOne(currentSelectedOrderId);
				if(currentSelectedOrder == null)
				{
					Messages.addGlobalInfo("There is no order with Order ID{0}", currentSelectedOrderId);;
				}
			}else
			{
				Messages.addGlobalError("Bad Request.Invalid Order ID {0}", currentSelectedOrderId);		
			}
		}
	}

	public int getCurrentSelectedOrderId() {
		return currentSelectedOrderId;
	}

	public void setCurrentSelectedOrderId(int currentSelectedOrderId) {
		this.currentSelectedOrderId = currentSelectedOrderId;
	}

	public Order getCurrentSelectedOrder() {
		return currentSelectedOrder;
	}
	
	
	
}


