package northwind.service;


import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;


import northwind.data.CategoryRepository;

import northwind.data.ProductRepository;
import northwind.data.SupplierRepository;
import northwind.model.Category;

import northwind.model.Product;
import northwind.model.Supplier;

@Stateless
public class ProductService {

	
	@Inject
	private SupplierRepository supplierRepository;
	
	@Inject
	private CategoryRepository categoryRepository;
	
	
	public void createProduct(Product newProduct, Integer categoryId, Integer supplierId)
	{
		if (categoryId != null) 
		{
			Category currentCategory = categoryRepository.find(categoryId);
			newProduct.setCategory(currentCategory);
		}
		
		if (supplierId != null) 
		{
			Supplier currentSupplier = supplierRepository.find(supplierId);
			newProduct.setSupplier(currentSupplier);
		}
		
		
	}
	
	@Inject
	private Logger log;
	
	@Inject
	private ProductRepository productRepository;
	
	
	public Product findOneProduct(int productId) {
		return productRepository.findOne(productId);
	}
	
	
	
	public void createProduct(Product newProduct) {
		try {
			productRepository.persist(newProduct);
		} catch(Exception e) {
			log.info(e.getMessage());
		}
	}
	
}
