package northwind.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.NotBlank;
import org.omnifaces.util.Messages;

import northwind.data.CustomerRepository;
import northwind.model.Customer;
import northwind.service.CustomerService;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class CustomerController implements Serializable{

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
	@NotBlank(message="Company Name is required")
	private Customer newCustomer = new Customer();

	@Inject
	private CustomerService customerService;

	public void createNewCustomer()
	{
		try
		{
			customerService.createCustomer(newCustomer);
			Messages.addGlobalInfo("Create Customer was SuccessFul");
		}
		catch(Exception e)
		{
			Messages.addGlobalError("Error creating customer with exception: {0}", 
					e.getMessage());
		}
	}

	public Customer getNewCustomer() {
		return newCustomer;
	}

	public void setNewCustomer(Customer newCustomer) {
		this.newCustomer = newCustomer;
	}






	
}
