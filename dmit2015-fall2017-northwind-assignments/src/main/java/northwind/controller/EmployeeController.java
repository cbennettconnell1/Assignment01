package northwind.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import northwind.data.EmployeeRepository;
import northwind.model.Employee;
import northwind.report.EmployeeSales;

@Model
public class EmployeeController {

	@Inject
	private EmployeeRepository employeeRepository;
	private List<Employee> employees;
	
	@PostConstruct
	void init()
	{
		employees = employeeRepository.findAll();
	}
	public List<Employee> getEmployees()
	{
		return employees;
	}
	
	private int currentSelectedEmployeeId; //getter/setter
	private Employee currentSelectedEmployee;//getter
	
	public void findEmployee()
	{
		if(!FacesContext.getCurrentInstance().isPostback())
		{
			if(currentSelectedEmployeeId > 0)
			{
				currentSelectedEmployee = employeeRepository.find(currentSelectedEmployeeId);
				if(currentSelectedEmployee == null)
				{
					Messages.addGlobalInfo("There is no order with Order ID{0}", currentSelectedEmployeeId);
				}
			}else
			{
				Messages.addGlobalError("Bad Request.Invalid Order ID {0}", currentSelectedEmployeeId);		
			}
		}
	}
	public int getCurrentSelectedEmployeeId() {
		return currentSelectedEmployeeId;
	}
	public void setCurrentSelectedEmployeeId(int currentSelectedEmployeeId) {
		this.currentSelectedEmployeeId = currentSelectedEmployeeId;
	}
	public Employee getCurrentSelectedEmployee() {
		return currentSelectedEmployee;
	}
	
	public List<Employee> getEmployeees() 
	{
		return employees;
	}
	
	public List<EmployeeSales> retrieveEmployeeSales() 
	{
		return employeeRepository.findEmployeeSales();
	}

	
}
