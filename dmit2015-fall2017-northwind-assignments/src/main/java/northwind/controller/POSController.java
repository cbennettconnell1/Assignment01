package northwind.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;


import northwind.data.CustomerRepository;
import northwind.exception.IllegalQuantityException;
import northwind.exception.NoProductsException;
import northwind.model.Customer;
import northwind.model.Product;
import northwind.service.OrderService;
import northwind.service.ProductService;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class POSController implements Serializable{
	
				
	private Set<Product> items = new HashSet<>();					// +getter
	
	@Inject
	private ProductService productService;
	
	
	@NotNull(message="ProductId field value is required")
	private Integer currentProductId;						// +getter +setter
	
	@NotNull(message="Customer field value selection is required")
	private Integer currentSelectedCustomerId;			// +getter +setter
	
	@NotNull(message="Employee field value selection is required")
	private Integer currentSelectedEmployeeId;			// +getter +setter
	
	@NotNull(message="Date field value selection is required")
	private Date requiredDate;			// +getter +setter
	
	@Inject
	private CustomerRepository customerRepository;
	
	
	private String shippingAddress;						// +getter +setter
	private String shippingCity;							// +getter +setter
	private String shippingProvince;						// +getter +setter
	private String shippingCountry;						// +getter +setter
	private String shippingPostalCode;					// +getter +setter
	
	
	public void changeShippingInfo() {
		int customerId = currentSelectedCustomerId;
		Customer invoiceCustomer = customerRepository.find(customerId);
		shippingAddress = invoiceCustomer.getAddress();
		shippingCity = invoiceCustomer.getCity();
		shippingProvince = invoiceCustomer.getRegion();
		shippingCountry = invoiceCustomer.getCountry();
		shippingPostalCode = invoiceCustomer.getPostalCode();
	}
	
	public void addItemWithProductId() {
		Product currentTrack = productService.findOne(currentProductId);
		if (currentTrack != null) {
			addItem(currentTrack);	
		} else {
			Messages.addGlobalError("Invalid ProductId {0}", currentProductId);
		}
	}
	
	public String addItemWithProductIdQueryParameter() {	
		String ProductIdParam = Faces.getRequestParameter("ProductId");
		if( ProductIdParam != null && !ProductIdParam.isEmpty() ) {
			int ProductId = Integer.parseInt(ProductIdParam);
			Product currentProduct = productService.findOne(ProductId);
			if( currentProduct != null ) {
				addItem(currentProduct);
			} else {
				Messages.addGlobalError("Invalid productId {0}", currentProductId);
			}
		}
		return "/public/Assignment05/PointofSales.xhtml?faces-redirect=true";
	}
	
	public String addItem(Product currentProduct) {
		Product item = new Product();
		item.setProductID(currentProduct.getProductID());
		item.setProductName(currentProduct.getProductName());
		item.setUnitPrice(currentProduct.getUnitPrice());
		item.setQuantityPerUnit("1");
		// Add item to shopping cart
		if (!items.add(item)) {
			// Item is already in the shopping cart
			// Get existing item and increment quantity by 1
			Product existingItem = items.stream().filter(singleItem -> singleItem.getProductID() == currentProduct.getProductID() ).findFirst().orElse(null);
			if (existingItem != null) {
				existingItem.setQuantityPerUnit(existingItem.getQuantityPerUnit() + "1");
				Messages.addFlashGlobalInfo("Item quantity was updated");
			}
		} else {
			Messages.addFlashGlobalInfo("Item was added to cart");
		}
		
		// return navigation to the page shoppingBag.xhtml
		return "/public/Assignment05/PointofSales.xhtml?faces-redirect=true";
	}
	
    @Inject
	private OrderService orderService;
	
	public void submitOrder() {
		try {
			int customerId = currentSelectedCustomerId;
			Customer orderCustomer = customerRepository.find(customerId);
		
			 int orderId = orderService.createOrder(
					orderCustomer, 
					shippingAddress,
					shippingCity,
					shippingProvince,
					shippingCountry,
					shippingPostalCode,
					new ArrayList<>(items));
			Messages.addGlobalInfo("Successfully created order #{0}", customerId);

			// clear the form field values
			currentSelectedCustomerId = null;
			shippingAddress = null;
			shippingCity = null;
			shippingProvince = null;
			shippingCountry = null;
			shippingPostalCode = null;
			// empty the shopping cart
			items.clear();			
		} catch( NoProductsException | IllegalQuantityException e ) {
			Messages.addGlobalError(e.getMessage());
		} catch( Exception e ) {
			Messages.addGlobalError("Create order was not successful");
		}
	}
	

	
	
	
	public void removeItem(Product item) {
		items.remove(item);
	}
	
	public void emptyCart() {
		items.clear();
	}

	
	
	
	
	
	
	public Integer getCurrentProductId() {
		return currentProductId;
	}

	public void setCurrentProductId(Integer currentProductId) {
		this.currentProductId = currentProductId;
	}

	public Integer getCurrentSelectedCustomerId() {
		return currentSelectedCustomerId;
	}

	public void setCurrentSelectedCustomerId(Integer currentSelectedCustomerId) {
		this.currentSelectedCustomerId = currentSelectedCustomerId;
	}
	

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingProvince() {
		return shippingProvince;
	}

	public void setShippingProvince(String shippingProvince) {
		this.shippingProvince = shippingProvince;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getShippingPostalCode() {
		return shippingPostalCode;
	}

	public void setShippingPostalCode(String shippingPostalCode) {
		this.shippingPostalCode = shippingPostalCode;
	}

	public Set<Product> getItems() {
		return items;
	}

	public Integer getCurrentSelectedEmployeeId() {
		return currentSelectedEmployeeId;
	}

	public void setCurrentSelectedEmployeeId(Integer currentSelectedEmployeeId) {
		this.currentSelectedEmployeeId = currentSelectedEmployeeId;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	
	
	
}

