package northwind.service;


import javax.inject.Inject;


import northwind.data.CategoryRepository;
import northwind.data.SupplierRepository;
import northwind.model.Category;
import northwind.model.Product;
import northwind.model.Supplier;


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
		
		//createProduct(newProduct, categoryId, supplierId);
	}
	
}
