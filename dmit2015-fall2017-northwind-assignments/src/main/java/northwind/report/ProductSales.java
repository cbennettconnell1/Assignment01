package northwind.report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductSales {
	
	private String categoryName; //getter/setter
	private String productName; //getter/setter
	private BigDecimal productSales; //getter/setter
	
	
	public ProductSales(String categoryName, String productName, BigDecimal productSales) {
		super();
		this.categoryName = categoryName;
		this.productName = productName;
		this.productSales = productSales;
	}
	
	public ProductSales(String categoryName, String productName, double productSales) {
		super();
		this.categoryName = categoryName;
		this.productName = productName;
		this.productSales = BigDecimal.valueOf(productSales).setScale(2, RoundingMode.HALF_UP);
	}

	public String getCategoryName() {
		return categoryName;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductSales() {
		return productSales;
	}

	public void setProductSales(BigDecimal productSales) {
		this.productSales = productSales;
	}
}
