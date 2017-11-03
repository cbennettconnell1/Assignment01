package northwind.report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EmployeeSales {
	
	//don't know if i also need lastName???
	private String firstName;    //getter, setter
	private BigDecimal salary;        //getter, setter
	
	
	public EmployeeSales(String firstName, BigDecimal salary) 
	{
		super();
		this.firstName = firstName;
		this.salary = salary;
	}

	public EmployeeSales(String firstName, double salary) {
		super();
		this.firstName = firstName;
		this.salary = BigDecimal.valueOf(salary).setScale(2, RoundingMode.HALF_UP);
	}
	

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public BigDecimal getSalary() {
		return salary;
	}


	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	
	
}
