package northwind.data;

import northwind.model.EmployeeTerritory;

public class EmployeeTerritoryRepository extends AbstractJpaRepository<EmployeeTerritory> {
	private static final long serialVersionUID = 1L;

	public EmployeeTerritoryRepository() {
		super(EmployeeTerritory.class);
	}

}
