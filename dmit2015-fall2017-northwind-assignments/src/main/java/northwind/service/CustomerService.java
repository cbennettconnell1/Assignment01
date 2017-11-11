package northwind.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import northwind.data.CustomerRepository;
import northwind.model.Customer;

@Stateless
public class CustomerService {

	@Inject
	private CustomerRepository customerRepository;
	
	public void createCustomer(String companyName,String customerID)
	{
		Customer currentCustomer = new Customer();
		currentCustomer.setCompanyName(companyName);
		currentCustomer.setCustomerID(customerID);
		createCustomer(currentCustomer);
	}
	
	public void createCustomer(Customer currentCustomer)
	{
		customerRepository.persist(currentCustomer);
	}
}
