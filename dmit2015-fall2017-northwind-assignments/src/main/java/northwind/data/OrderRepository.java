package northwind.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import northwind.model.Order;

@Stateless
@LocalBean
public class OrderRepository {
	
	@Inject
	private EntityManager em;
	
	public List<Order> findAll(){
		return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
	
	
	}
}
