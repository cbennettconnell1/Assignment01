package northwind.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import northwind.data.CustomerRepository;
import northwind.model.Customer;

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
	
}
