package northwind.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.hibernate.validator.constraints.NotBlank;
import org.omnifaces.util.Messages;

import northwind.data.CustomerRepository;
import northwind.model.Customer;
import northwind.service.CustomerService;

@Model
public class CustomerController {

	@Inject
	private CustomerRepository customerRepository;
	
	private List<Customer> customers;
	
	@PostConstruct
	void init() {
		customers = customerRepository.findAll();
	}

	public List<Customer> getCustomers() {
		return customers;
	}	
	
	private String currentSelectedCustomerId;   //getter/setter
	private Customer currentSelectedCustomer;  //getter

	public void findCustomer() 
	{
		if(!FacesContext.getCurrentInstance().isPostback())
		{
			if(currentSelectedCustomerId != null)
			{
				currentSelectedCustomer = customerRepository.find(currentSelectedCustomerId);
				if(currentSelectedCustomer == null)
				{
					Messages.addGlobalInfo("There is no customer with customerID {0}", currentSelectedCustomerId);
				}
			}
				else
				{
					Messages.addGlobalError("Bad request. A valid customerID is required.");
				}			
		}
	}
		
	public String getCurrentSelectedCustomerId() {
		return currentSelectedCustomerId;
	}

	public void setCurrentSelectedCustomerId(String currentSelectedCustomerId) {
		this.currentSelectedCustomerId = currentSelectedCustomerId;
	}

	public Customer getCurrentSelectedCustomer() {
		return currentSelectedCustomer;
	}
	
	//New Customer
	private Customer newCustomer = new Customer();
	@Inject
	private CustomerService customerService;
	
	@NotBlank(message="Company Name value is required")
	private String companyName; //getter/setter
	private String customerID;
	
	public void createNewCustomer()
	{
		try
		{
			newCustomer.setCompanyName(companyName);
			newCustomer.setCustomerID(customerID);
			customerService.createCustomer(newCustomer);
			Messages.addGlobalInfo("Create Customer was SuccessFul");
			companyName="";
			
		}
		catch(Exception e)
		{
			Messages.addGlobalError("Error creating customer with exception: {0}", 
					e.getMessage());
		}
	}

	public String getCustomerName() {
		return companyName;
	}

	public void setCustomerName(String companyName) {
		this.companyName = companyName;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
}
