package northwind.controller;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Messages;

import northwind.data.CustomerRepository;
import northwind.data.OrderRepository;
import northwind.exception.IllegalQuantityException;
import northwind.model.Customer;
import northwind.model.Order;
import northwind.model.OrderDetail;
import northwind.service.OrderService;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class OrderController implements Serializable{
	
	@Inject
	private Logger log;
	
	@Inject
	private OrderRepository orderRepository;
	
	@Inject
	private CustomerRepository customerRepository;
	
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
					Messages.addGlobalInfo("There is no order with Order ID{0}", currentSelectedOrderId);
				}
			}
			else
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
	
	public double findSubTotal()
	{
		double subtotal = 0;
		for(OrderDetail od :currentSelectedOrder.getOrderDetails()) {
			subtotal += od.getUnitPrice().doubleValue() * od.getQuantity();
		}
		return subtotal;
	}
	
	public double Total()
	{
		double freight = currentSelectedOrder.getFreight().doubleValue();
		return findSubTotal() + freight;
	}

	

	
	
	//order by customer
	private List<Order> orderByCustomer;   //getter
	private String currentSelectedCustomerID; //getter/setter

	public void findOrdersbyCustomer() 
	{
		if(!FacesContext.getCurrentInstance().isPostback())
		{
			if(currentSelectedCustomerID != null)
			{
				orderByCustomer = orderRepository.findAllByCustomerId(currentSelectedCustomerID);
				if(orderByCustomer == null)
				{
					Messages.addGlobalInfo("There are no orders for specified customerId {0}",currentSelectedCustomerID);					
				}
			}
			else
			{
				Messages.addGlobalError("Bad request. A valid CustomerID is required");
			}
		}
	}

	public String getCurrentSelectedCustomerID() {
		return currentSelectedCustomerID;
	}

	public void setCurrentSelectedCustomerID(String currentSelectedCustomerID) {
		this.currentSelectedCustomerID = currentSelectedCustomerID;
	}

	public List<Order> getOrderByCustomer() {
		return orderByCustomer;
	}
	
	
	
	//Employee
	private List<Order> orderByEmployee;   //getter
	private int currentSelectedEmployeeID; //getter/setter

	public void findOrdersbyEmployee() 
	{
		if(!FacesContext.getCurrentInstance().isPostback())
		{
			if(currentSelectedEmployeeID > 0)
			{
				orderByEmployee = orderRepository.findAllByEmployeeId(currentSelectedEmployeeID);
				if(orderByEmployee == null)
				{
					Messages.addGlobalInfo("There are no orders for specified EmployeeId {0}",currentSelectedEmployeeID);					
				}
			}
			else
			{
				Messages.addGlobalError("Bad request. A valid EmployeeID is required");
			}
		}
	}

	public int getCurrentSelectedEmployeeID() {
		return currentSelectedEmployeeID;
	}

	public void setCurrentSelectedEmployeeID(int currentSelectedEmployeeID) {
		this.currentSelectedEmployeeID = currentSelectedEmployeeID;
	}

	public List<Order> getOrderByEmployee() {
		return orderByEmployee;
	}
	

	//assignment 4---
	@Inject 
	private OrderService orderService;
	

	@NotNull(message="OrderID field value is required.")
	private Integer searchValue;		// +getter+setter



	public void findOneOrder() {
		try
		{
			currentSelectedOrder = orderService.findOneOrder(
					searchValue);
			if( currentSelectedOrder == null ) 
			{
				Messages.addGlobalInfo("We found 0 results for {0}",
						searchValue);
			} else 
			{
				Messages.addGlobalInfo("1 result for {0}", 
						searchValue);				
			}
		} 
		catch(Exception e) 
		{
			log.info(e.getMessage());
			currentSelectedOrder = null;
			Messages.addGlobalInfo("We found 0 results for {0}",
					searchValue);
		}
	}
	
	public Integer getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(Integer searchValue) {
		this.searchValue = searchValue;
	}


		//Date Range
		private List<Order> orderbyDate; //getter
		private Date startDate; //getter/setter
		private Date endDate; //getter/setter
		
		public void findOrderbyDateRange()
		{
			orderbyDate = orderRepository.findbyDateRange(startDate, endDate);	
			int Count = orderbyDate.size();
			
			if (orderbyDate.size() == 0) {
				Messages.addGlobalError("There are not invoices between{0} and {1}", startDate, endDate);
			} else {
				Messages.addGlobalInfo("There are {0} orders from {1} to {2}", Count,startDate,endDate);
			}
		}

		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public List<Order> getOrderbyDate() {
			return orderbyDate;
		}
		
		
		
		
		/*	public void findOneOrder() {
		currentSelectedOrder = orderRepository.findOne(currentSelectedOrderId);
		if( currentSelectedOrder==0) {
			Messages.addGlobalInfo("There is no order with orderID {0}", currentSelectedOrderId);					
		} else {
			Messages.addGlobalInfo("We found  result with orderID {0}", currentSelectedOrderId);								
		}
	}

	*/
	
	//assignment 5 
	@NotNull(message="OrderID field value is required")
	private Order querySingleResult;    //getter	
	
	public Order getQuerySingleResult() 
	{
		return querySingleResult;
	}

	public void updateOrder() 
	{
		try
		{
			orderService.updateOrder(currentSelectedOrder);
			Messages.addGlobalInfo("Update was successful");
		}
		catch (Exception e)
		{;
			Messages.addGlobalInfo("Update was not successful");
			log.info(e.getMessage());
		}
	}
	
	/*public void SubmitOrder()
	{
		try
		{
			int orderId = currentSelectedOrderId;
			Order order = orderRepository.find(orderId);
			Messages.addGlobalInfo("Successfully updated order #{0}", orderId);

		}
		catch (IllegalQuantityException e)
		{
			Messages.addGlobalError(e.getMessage());	
		}
		catch( Exception e ) 
		{
			Messages.addGlobalError("update order was not successful");
		}
		
	}*/
	

	/*public void SubmitOrder() 
	{
		try
		{
			String customerID = currentSelectedCustomerID;
			Customer orderCustomer = customerRepository.find(customerID);
		}
		catch (IllegalQuantityException e ) 
		{
			Messages.addGlobalError(e.getMessage());	
		}
		catch( Exception e ) 
		{
			Messages.addGlobalError("Create invoice was not successful");
		}

	}*/
	
}


