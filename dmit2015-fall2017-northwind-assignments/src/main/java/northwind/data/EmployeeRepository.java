package northwind.data;

import java.util.List;

import northwind.model.Employee;
import northwind.report.EmployeeSales;

public class EmployeeRepository extends AbstractJpaRepository<Employee> {
	private static final long serialVersionUID = 1L;

	public EmployeeRepository() {
		super(Employee.class);
	}
	
	public List<EmployeeSales>findEmployeeSales()
	{
		return getEntityManager().createQuery(		
		"SELECT new northwind.report.EmployeeSales(e.firstName, SUM(e.salary))"
		+ "FROM Employee e"

		, EmployeeSales.class)
		.setMaxResults(10)
		.getResultList();
											
	}

}

