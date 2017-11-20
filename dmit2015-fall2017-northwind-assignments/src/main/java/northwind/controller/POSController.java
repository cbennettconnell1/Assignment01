package northwind.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Messages;


import northwind.model.Product;

import northwind.service.ProductService;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class POSController implements Serializable{
	
	@NotNull(message="TrackId field value is required")
	private Integer currentSelectedProductId;						// +getter +setter
	private Set<Product> products = new HashSet<>();					// +getter
	
	@Inject
	private ProductService productService;
	
	
	public void addProductToOrder() {
		// Find the Track entity with the currentSelectedTrackid
		Product currentProduct = productService.findOneProduct(currentSelectedProductId);
		if (currentProduct == null) {
			Messages.addGlobalWarn("{0} is not a valid ProductId value.", currentSelectedProductId);
		} else {
			products.add(currentProduct);
			Messages.addGlobalInfo("Add Product was successful.");
		}
	}


	public Integer getCurrentSelectedProductId() {
		return currentSelectedProductId;
	}


	public void setCurrentSelectedProductId(Integer currentSelectedProductId) {
		this.currentSelectedProductId = currentSelectedProductId;
	}


	public Set<Product> getProducts() {
		return products;
	}
	

}
