package controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import factory.Controller;
import factory.ControllerFactory;

public class Main {

	public static void main(String[] args) {

		ControllerFactory cf= Controller.getInstanceOf("User");
		
		List<Object> list= cf.fetchAll();

		for(Object u:list) {
			
			// just using this for print data in proper json form
			ObjectMapper mapper = new ObjectMapper();

			try {
				String jString = mapper.writeValueAsString(u);
				String jvalue = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(jString));
				System.out.println(jvalue);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
