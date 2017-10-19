package northwind.data;
import northwind.model.Order;

public class OrderRepository extends AbstractJpaRepository<Order>{
	private static final long serialVersionUID = 1L;
	
	public OrderRepository() 
	{
		super(Order.class);
	}
	
	public Order findOne(int orderId)
	{
		return getEntityManager().createQuery("SELECT o FROM Order o JOIN FETCH o.orderDetails WHERE o.orderID = :idValue",Order.class)
	    .setParameter("idValue", orderId)
	    .getSingleResult();
	}
	
}
	







