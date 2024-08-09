package controller;

import java.util.List;
import factory.ControllerFactory;
import service.EmployeeService;

public class EmployeeController implements ControllerFactory  {

	private EmployeeService service=new EmployeeService();
	
	public List<Object> fetchAll(){
		return service.saveAllEmployee();
	}
}
