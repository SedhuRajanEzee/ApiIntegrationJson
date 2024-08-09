package controller;

import java.util.List;
import factory.ControllerFactory;
import service.StudentService;

public class StudentController implements ControllerFactory {

	private StudentService service=new StudentService();
	
	public List<Object> fetchAll(){
		return service.saveAllStudent();
	}

}
