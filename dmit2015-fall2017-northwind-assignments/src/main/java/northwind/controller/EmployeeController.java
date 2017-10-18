package northwind.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import northwind.data.EmployeeRepository;
import northwind.model.Employee;

@Model
public class EmployeeController {
	
	private int currentSelectedEmployeeID;
	private Employee currentSelectedEmployee;
	
	public void findEmployee() {
		if( !FacesContext.getCurrentInstance().isPostback() ) {
			if( currentSelectedEmployeeID > 0 ) {
				currentSelectedEmployee = employeeRepository.find(currentSelectedEmployeeID);
				if( currentSelectedEmployee == null ) {
					Messages.addGlobalInfo("There is no employee with employeeID {0}", 
							currentSelectedEmployeeID);
				} else {
					Messages.addGlobalInfo("Successfully retrieved employee info.");
				}
			} else {
				Messages.addGlobalError("Invalid request. A valid employeeID is required.");
			}
		}		
	}

	@Inject
	private EmployeeRepository employeeRepository;
	
	private List<Employee> employees;
	
	@PostConstruct
	void init() {
		employees = employeeRepository.findAll();
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public int getCurrentSelectedEmployeeID() {
		return currentSelectedEmployeeID;
	}

	public void setCurrentSelectedEmployeeID(int currentSelectedEmployeeID) {
		this.currentSelectedEmployeeID = currentSelectedEmployeeID;
	}

	public Employee getCurrentSelectedEmployee() {
		return currentSelectedEmployee;
	}
		
}
