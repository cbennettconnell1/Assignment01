package northwind.controller;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import northwind.data.ProductRepository;
import northwind.model.Product;
import northwind.report.ProductSales;
import northwind.service.ProductService;

@SuppressWarnings("serial")
@Model
public class ProductController implements Serializable {
	
	@Inject
	private ProductRepository productRepository;
	
	@Inject
	private ProductService productService;
	
	private List<Product> products;
	
	private Product currentNewProduct = new Product();
	private Integer currentSelectedSupplierId;
	private Integer currentCategoryId;
	
	
	@PostConstruct
	void init()
	{
		products = productRepository.findAll();
	}
	
	public List<Product> getProducts()
	{
		return products;
	}
	
		
	public Product getCurrentNewProduct() {
		return currentNewProduct;
	}

	public void setCurrentNewProduct(Product currentNewProduct) {
		this.currentNewProduct = currentNewProduct;
	}

	public Integer getCurrentSelectedSupplierId() {
		return currentSelectedSupplierId;
	}

	public void setCurrentSelectedSupplierId(Integer currentSelectedSupplierId) {
		this.currentSelectedSupplierId = currentSelectedSupplierId;
	}
	

	public Integer getCurrentCategoryId() {
		return currentCategoryId;
	}

	public void setCurrentCategoryId(Integer currentCategoryId) {
		this.currentCategoryId = currentCategoryId;
	}


	//Product By Category
	//Returns Multiple Records using list in repository
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
			}
			else 
			{
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
	// Returns a Single Result
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
			}
			else
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
	
	//call out-products sales and top ten products
	public List<Product> retrieveTopTenProducts() {
		return productRepository.findTopTenProducts(); 
	}
	
	public List<ProductSales> retrieveProductSales() {
		return productRepository.findProductSales(); 
	}
	
	//create new product details 
	public void createNewProduct() 
	{
		try {
			productService.createProduct(currentNewProduct, currentSelectedSupplierId, currentCategoryId );
			Messages.addGlobalInfo("Create product details was successful.");
			currentNewProduct = new Product();
		} catch(Exception e) 
		{
			Messages.addGlobalInfo("Create product details was not successful.");
		}
	}	
	
}
