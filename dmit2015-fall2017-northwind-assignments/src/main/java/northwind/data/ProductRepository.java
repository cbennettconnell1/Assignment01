package northwind.data;

import java.util.List;

import northwind.model.Product;
import northwind.report.ProductSales;

public class ProductRepository extends AbstractJpaRepository<Product>{
		private static final long serialVersionUID = 1L;
		
		public ProductRepository() {
			super(Product.class);
			}
		
		
		public List<Product> findAllByCategoryId(int categoryId)
		{
			return getEntityManager().createQuery(
					"SELECT p FROM Product p WHERE p.category.categoryID = :idValue",Product.class)
					.setParameter("idValue", categoryId)
					.getResultList();
		}
		
		public Product findOne(int productId)
		{
			return getEntityManager().createQuery("SELECT p FROM Product p WHERE p.productID = :idValue",Product.class)
				.setParameter("idValue", productId)
				.getSingleResult();
		}
		 
		
		//assignment3
		//ten most expensive products
		public List<Product> findTopTenProducts() {return 
			getEntityManager().createQuery("SELECT p FROM Product p order by p.unitPrice DESC",Product.class)
				.setMaxResults(10)
				.getResultList();}
		
		//product sales
		public List<ProductSales> findProductSales()
		{
			return getEntityManager().createQuery(
					"SELECT new northwind.report.ProductSales(c.categoryName,p.productName,SUM(od.quantity * od.unitPrice * (1 - od.discount))AS sale) "
					+ " FROM Product p,IN(p.category) c,IN(p.orderDetails) od "
					+ " WHERE YEAR(od.order.shippedDate) = :yearValue "
				    + " GROUP BY c.categoryName, p.productName "
					+ " ORDER BY sale DESC"		
					,ProductSales.class)
			.setParameter("yearValue",1997)
			.getResultList();
		}
}
