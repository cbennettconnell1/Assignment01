package northwind.controller;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import northwind.model.Product;
import northwind.service.ProductService;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class POSController implements Serializable{
	
	/*					
	private Set<Product> products = new HashSet<>();					// +getter
	
	@Inject
	private ProductService productService;
	
	public String addItem() {	
		String productIdParam = Faces.getRequestParameter("productId");
		if( productIdParam != null && !productIdParam.isEmpty() ) {
			int productId = Integer.parseInt(productIdParam);
			Product currentProduct = productService.findOne(productId);
			if( currentProduct != null ) {
				addItem(currentProduct);
			}
		}
		return "/public/transaction/shoppingCart.xhtml?faces-redirect=true";
	}

	public String addItem(Product currentProduct) {
		Product item = new Product();
		item.setProductID(currentProduct.getProductID());
		item.setProductName(currentProduct.getProductName());
		
		// Add item to shopping cart
		if (!products.add(item)) {
			// Item is already in the shopping cart
			// Get existing item and increment quantity by 1
			Product existingItem = products.stream().filter( */
		/*			singleItem -> singleItem.getTrack().getTrackId() == currentTrack.getTrackId() )
					.findFirst().orElse(null);
			if (existingItem != null) {
				existingItem.setQuantity( existingItem.getQuantity() + 1);
				Messages.addFlashGlobalInfo("Item quantity was updated");
			}
		} else 
			Messages.addFlashGlobalInfo("Item was added to cart");
		}
		
		// return navigation to the page shoppingBag.xhtml
		return "/public/transaction/shoppingCart.xhtml?faces-redirect=true";
	}
	
	
	
	public Set<Product> getProducts() {
		return products;
	}
	
	
}
*/
}