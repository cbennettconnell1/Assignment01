package northwind.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the `Order Details` database table.
 * 
 */
@Entity
@Table(name="`Order Details`")
@NamedQuery(name="Order_Detail.findAll", query="SELECT o FROM Order_Detail o")
public class Order_Detail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Order_DetailPK id;

	@Column(name="Discount")
	private double discount;

	@Column(name="Quantity")
	private short quantity;

	@Column(name="UnitPrice")
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="OrderID")
	private Order order;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="ProductID")
	private Product product;

	public Order_Detail() {
	}

	public Order_DetailPK getId() {
		return this.id;
	}

	public void setId(Order_DetailPK id) {
		this.id = id;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public short getQuantity() {
		return this.quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}