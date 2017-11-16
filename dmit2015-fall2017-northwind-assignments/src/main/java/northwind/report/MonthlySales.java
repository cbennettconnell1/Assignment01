package northwind.report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MonthlySales {

	
	private BigDecimal monthlySales; //getter/setter
	
	
	public MonthlySales(BigDecimal monthlySales) {
		super();
		this.monthlySales = monthlySales;
	}
	
	public MonthlySales(double monthlySales) {
		super();
		this.monthlySales = BigDecimal.valueOf(monthlySales).setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getMonthlySales() {
		return monthlySales;
	}

	public void setMonthlySales(BigDecimal monthlySales) {
		this.monthlySales = monthlySales;
	}
	
	
}
