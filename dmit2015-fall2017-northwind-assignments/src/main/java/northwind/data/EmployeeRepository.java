package northwind.data;

import java.util.List;

import northwind.model.Employee;
import northwind.report.EmployeeSales;

public class EmployeeRepository extends AbstractJpaRepository<Employee> {
	private static final long serialVersionUID = 1L;

	public EmployeeRepository() {
		super(Employee.class);
	}
	
	public List<EmployeeSales> findEmployeeSales()
	{
		return getEntityManager().createQuery(		
		"SELECT new northwind.report.EmployeeSales(CONCAT (e.firstName, e.lastName),SUM(od.quantity * od.unitPrice * (1 - od.discount))AS sales ) "
		+ " FROM OrderDetail od, IN (od.order) o, IN (o.employee) e "
		+ " WHERE YEAR(o.shippedDate) = :yearValue "
		+ " GROUP BY e.employeeID"
		+ " ORDER BY sales DESC"
		, EmployeeSales.class)
		.setParameter("yearValue",1997)
		.getResultList();
											
	}

}

