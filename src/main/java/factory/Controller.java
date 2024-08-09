package factory;

import controller.EmployeeController;
import controller.StudentController;
import controller.UserController;

public class Controller {

	public static ControllerFactory getInstanceOf(String s) {

		switch(s) {

		case "User": return new UserController();
		case "Student": return new StudentController();
		case "Employee": return new EmployeeController();

		}

		return null;
	}
}
