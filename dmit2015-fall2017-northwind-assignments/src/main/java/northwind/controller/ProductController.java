package northwind.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import northwind.data.ProductRepository;
import northwind.model.Product;

@Model
public class ProductController {
	
	@Inject
	private ProductRepository productRepository;
	
	private List<Product> products;
	
	@PostConstruct
	void init()
	{
		products = productRepository.findAll();
	}
	
	public List<Product> getProducts()
	{
		return products;
	}
	
	
	//Product By Category
	private List<Product> productbyCategory; //getter
	private int currentSelectedCategoryId;  //getter/setter
	
	public void findProductsbyCategory()
	{
		if(!FacesContext.getCurrentInstance().isPostback())
		{
			if(currentSelectedCategoryId > 0 )
			{
				productbyCategory = productRepository.findAllByCategoryId(currentSelectedCategoryId);
						if(productbyCategory.size() == 0)
						{
							Messages.addGlobalInfo("There are no products for categoryID {0}", currentSelectedCategoryId);
						}
			}else {
				Messages.addGlobalError("Bad Request. A valid categoryID is required");
			}
		}
	}

	public int getCurrentSelectedCategoryId() {
		return currentSelectedCategoryId;
	}

	public void setCurrentSelectedCategoryId(int currentSelectedCategoryId) {
		this.currentSelectedCategoryId = currentSelectedCategoryId;
	}

	public List<Product> getProductbyCategory() {
		return productbyCategory;
	}
	
	
	
	
	
	//Product Details
	private int currentSelectedProductId; //getter/setter
	private Product currentSelectedProduct; //getter
	
	public void findProduct() {
		if(!FacesContext.getCurrentInstance().isPostback())
		{
			if(currentSelectedProductId > 0)
			{
				currentSelectedProduct = productRepository.findOne(currentSelectedProductId);
				if(currentSelectedProduct == null)
				{
					Messages.addGlobalInfo("There is no product with Product ID {0}",currentSelectedProductId);
					
				}
			}else
				{
					Messages.addGlobalError("Bad Request. Invalid Product ID {0}", currentSelectedProductId);
				}
		}
	}

	public int getCurrentSelectedProductId() {
		return currentSelectedProductId;
	}

	public void setCurrentSelectedProductId(int currentSelectedProductId) {
		this.currentSelectedProductId = currentSelectedProductId;
	}

	public Product getCurrentSelectedProduct() {
		return currentSelectedProduct;
	}
	
	
	
	
	
	
}
