package northwind.controller;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import northwind.data.CustomerRepository;
import northwind.model.Customer;
import northwind.model.Product;
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
	
	@Inject
	private CustomerRepository customerRepository;
	
	
	private String billingAddress;						// +getter +setter
	private String billingCity;							// +getter +setter
	private String billingProvince;						// +getter +setter
	private String billingCountry;						// +getter +setter
	private String billingPostalCode;					// +getter +setter
	
	
	public void changeBillingInfo() {
		int customerId = currentSelectedCustomerId;
		Customer invoiceCustomer = customerRepository.find(customerId);
		billingAddress = invoiceCustomer.getAddress();
		billingCity = invoiceCustomer.getCity();
		billingProvince = invoiceCustomer.getRegion();
		billingCountry = invoiceCustomer.getCountry();
		billingPostalCode = invoiceCustomer.getPostalCode();
	}
	
	public void addItemWithProductId() {
		Product currentTrack = productService.findOne(currentProductId);
		if (currentTrack != null) {
			addItem(currentTrack);	
		} else {
			Messages.addGlobalError("Invalid trackId {0}", currentProductId);
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
		return "/public/transaction/shoppingCart.xhtml?faces-redirect=true";
	}
	
	public String addItem(Product currentProduct) {
		Product item = new Product();
		item.setProductID(currentProduct.getProductID());
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
		return "/public/transaction/shoppingCart.xhtml?faces-redirect=true";
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

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingProvince() {
		return billingProvince;
	}

	public void setBillingProvince(String billingProvince) {
		this.billingProvince = billingProvince;
	}

	public String getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}

	public String getBillingPostalCode() {
		return billingPostalCode;
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}

	public Set<Product> getItems() {
		return items;
	}
	
	
	
}

