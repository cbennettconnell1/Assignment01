package northwind.data;
import java.util.List;
import northwind.model.Order;
import northwind.report.MonthlySales;

public class OrderRepository extends AbstractJpaRepository<Order>{
	private static final long serialVersionUID = 1L;
	
	public OrderRepository() 
	{
		super(Order.class);
	}
	

	public List<Order> findAllByCustomerId(String customerId)
	{
		return getEntityManager().createQuery("SELECT o FROM Order o WHERE o.customer.customerID = :idValue",Order.class)
				.setParameter("idValue", customerId)
				.getResultList();
	}
	

	public Order findOne(int orderId)
	{
		return getEntityManager().createQuery("SELECT o FROM Order o JOIN FETCH o.orderDetails WHERE o.orderID = :idValue",Order.class)
	    .setParameter("idValue", orderId)
	    .getSingleResult();
	}

	
	
	public List<Order> findAllByEmployeeId(int EmployeeId)
	{
		return getEntityManager().createQuery("SELECT o FROM Order o WHERE o.employee.employeeID = :idValue",Order.class)
				.setParameter("idValue", EmployeeId)
				.getResultList();
	}
	
	public List<MonthlySales> findMonthlySales()
	{
		return getEntityManager().createQuery(
				"SELECT new northwind.report.MonthlySales(SUM(od.quantity * od.unitPrice * (1 - od.discount)))"
				+ " FROM OrderDetail od"
				+ " WHERE YEAR(od.order.shippedDate) = BETWEEN :startDate and :endDate"
				,MonthlySales.class)
				.setParameter("startDate",1996)
				.setParameter("startDate",1998)
				.getResultList();
		
	}
}
	









