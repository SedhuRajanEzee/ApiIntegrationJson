package controller;

import java.util.List;

import factory.ControllerFactory;
import service.UserService;


public class UserController implements ControllerFactory{

	private UserService service=new UserService();

	public List<Object> fetchAll(){
		return service.saveAllUser();
	}
}
