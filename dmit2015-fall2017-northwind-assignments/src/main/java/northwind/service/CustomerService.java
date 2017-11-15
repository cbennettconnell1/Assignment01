package northwind.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import northwind.data.CustomerRepository;
import northwind.model.Customer;

@Stateless
public class CustomerService {

	@Inject
	private CustomerRepository customerRepository;

	public void createCustomer(Customer newCustomer)
	{
		customerRepository.persist(newCustomer);
	}
	
	public void updateCustomer(Customer newCustomer)
	{
		customerRepository.edit(newCustomer);
	}
	
	public void removeCustomer(Customer newCustomer)
	{
		customerRepository.remove(newCustomer);
	}
	
	
}
