package northwind.report;


public class EmployeeSales {
	
	//don't know if i also need lastName???
	private String firstName;    //getter, setter
	private float salary;        //getter, setter
	
	
	public EmployeeSales(String firstName, float salary) 
	{
		super();
		this.firstName = firstName;
		this.salary = salary;
	}

	
	

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public float getSalary() {
		return salary;
	}


	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	
	
}
