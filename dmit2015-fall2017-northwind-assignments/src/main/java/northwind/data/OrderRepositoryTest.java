package northwind.data;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import northwind.model.Order;
import northwind.util.Resources;


@RunWith(Arquillian.class)
public class OrderRepositoryTest {

	@Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
        		.addPackage("northwind.model")
                .addClasses(OrderRepository.class, AbstractJpaRepository.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }

	@Inject
	private OrderRepository orderRepo;
	
	@Test
	public void testFindOne() {
//		fail("Not yet implemented");
		Order order1 = orderRepo.findOne(10248);
		assertEquals(10248, order1.getOrderID());
		assertEquals(3, order1.getOrderDetails().size());
		
	}

}
