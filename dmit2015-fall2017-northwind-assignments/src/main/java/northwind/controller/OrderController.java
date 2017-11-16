package northwind.controller;

import java.io.Serializable;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Date;
=======
>>>>>>> refs/remotes/origin/master
=======
import java.util.Date;
>>>>>>> refs/remotes/origin/master
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
<<<<<<< HEAD

=======
import javax.validation.constraints.NotNull;
>>>>>>> refs/remotes/origin/master

import org.omnifaces.util.Messages;

import northwind.data.OrderRepository;
import northwind.model.Order;
import northwind.model.OrderDetail;


@SuppressWarnings("serial")
@Named
@ViewScoped
@Model   //needed this*****
public class OrderController implements Serializable{
	
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
	
<<<<<<< HEAD
	//assignment 4---
	private Integer currentSelectedCustomerId;
	private List<Order> ordersByCustomer;
	
	/*@NotNull(message="OrderId field value is required")*/
	//private Integer currentSelectedOrderId;
	//private Order currentSelectedOrder;
	
	//public void findOrder() {
		//if( !FacesContext.getCurrentInstance().isPostback() ) {
			//if( currentSelectedOrderId > 0 ) {
				//currentSelectedOrder = orderRepository.findOne(currentSelectedOrderId);
				///if( currentSelectedOrder == null ) {
					//Messages.addGlobalInfo("There is no order with orderID {0}", 
							//currentSelectedOrderId);					
				//}
			//} else {
				//Messages.addGlobalError("Bad request. Invalid orderID {0}", currentSelectedOrderId);
			//}
		//}
	//}
	
	public void findOneOrder() {
		currentSelectedOrder = orderRepository.findOne(currentSelectedOrderId);
		if( currentSelectedOrder== null ) {
			Messages.addGlobalInfo("There is no order with orderID {0}", currentSelectedOrderId);					
		} else {
			Messages.addGlobalInfo("We found 1 result with orderID {0}", currentSelectedOrderId);								
		}
	}
	
	public void findOneOrder(int orderId) {
		currentSelectedOrderId = orderId;
		findOneOrder();
	}

	public Integer getCurrentSelectedCustomerId() {
		return currentSelectedCustomerId;
	}

	public void setCurrentSelectedCustomerId(Integer currentSelectedCustomerId) {
		this.currentSelectedCustomerId = currentSelectedCustomerId;
	}

	public List<Order> getOrdersByCustomer() {
		return ordersByCustomer;
	}

	public void setCurrentSelectedOrderId(Integer currentSelectedOrderId) {
		this.currentSelectedOrderId = currentSelectedOrderId;
	}
<<<<<<< HEAD
=======
	//assignment 4
	private Integer currentSelectedCustomerId;
	private List<Order> ordersByCustomer;
>>>>>>> refs/remotes/origin/master
	
	@NotNull(message="OrderId field value is required")
	//private Integer currentSelectedOrderId;
	//private Order currentSelectedOrder;
	
<<<<<<< HEAD
	//Date Range
	private List<Order> orderbyDate; //getter
	private Date startDate; //getter/setter
	private Date endDate; //getter/setter
=======
	//public void findOrder() {
		//if( !FacesContext.getCurrentInstance().isPostback() ) {
			//if( currentSelectedOrderId > 0 ) {
				//currentSelectedOrder = orderRepository.findOne(currentSelectedOrderId);
				///if( currentSelectedOrder == null ) {
					//Messages.addGlobalInfo("There is no order with orderID {0}", 
							//currentSelectedOrderId);					
				//}
			//} else {
				//Messages.addGlobalError("Bad request. Invalid orderID {0}", currentSelectedOrderId);
			//}
		//}
	//}
>>>>>>> refs/remotes/origin/master
	
<<<<<<< HEAD
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
=======
	public void findOneOrder() {
		currentSelectedOrder = orderRepository.findOne(currentSelectedOrderId);
		if( currentSelectedOrder== null ) {
			Messages.addGlobalInfo("There is no order with orderID {0}", currentSelectedOrderId);					
		} else {
			Messages.addGlobalInfo("We found 1 result with orderID {0}", currentSelectedOrderId);								
		}
	}
	
	public void findOneOrder(int orderId) {
		currentSelectedOrderId = orderId;
		findOneOrder();
	}

	public Integer getCurrentSelectedCustomerId() {
		return currentSelectedCustomerId;
	}

	public void setCurrentSelectedCustomerId(Integer currentSelectedCustomerId) {
		this.currentSelectedCustomerId = currentSelectedCustomerId;
	}

	public List<Order> getOrdersByCustomer() {
		return ordersByCustomer;
	}

	public void setCurrentSelectedOrderId(Integer currentSelectedOrderId) {
		this.currentSelectedOrderId = currentSelectedOrderId;
>>>>>>> refs/remotes/origin/master
	}
=======
	
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
	
	
>>>>>>> refs/remotes/origin/master
}


