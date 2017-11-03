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
					"SELECT new northwind.report.ProductSales(p.productName,SUM(od.quantity * od.unitPrice * (1 - od.discount)))"
					+ " FROM OrderDetail od,IN(od.Product) p "
					+ " WHERE YEAR(od.order.shippedDate) = :yearValue "
				    + " GROUP BY p.productName "
					+ " ORDER BY p.productName "		
					,ProductSales.class)
			.setParameter("yearValue",1997)
			.getResultList();
		}
}
