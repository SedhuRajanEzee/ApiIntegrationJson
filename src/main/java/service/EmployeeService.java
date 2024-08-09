package service;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dao.EmployeeDao;
import dto.Employee;


public class EmployeeService {

	private EmployeeDao dao=new EmployeeDao();	
	private Employee u;

	public List<Object> saveAllEmployee() {

		try {

			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://mocki.io/v1/76da7597-7d74-4b12-b904-3a3bf4fcfab3"))
					.GET()
					.build();

			// sending...request and getting response in HttpResponsestring format
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			Gson gson = new Gson();
			JsonObject jResponse = gson.fromJson(response.body(), JsonObject.class);
			JsonArray ja = jResponse.getAsJsonArray("data");

			List<Employee> list=new ArrayList<Employee>();

			for (JsonElement je : ja) {

				u= new Employee();
				JsonObject jo = je.getAsJsonObject();
				u.setEmployeeId((jo.get("employeeId").getAsInt()));
				u.setEmployeeName(jo.get("employeeName").getAsString());
				u.setEmployeeLocation(jo.get("employeeLocation").getAsString());
				u.setEmployeeEmail(jo.get("employeeEmail").getAsString());
				u.setEmployeePassword(jo.get("employeePassword").getAsString());

				list.add(u);
			}
			dao.saveAllStudent(list);
			
			List<Object> obj=new ArrayList<Object>();
			obj.addAll(list);
			
			return obj;
		}

		catch (ConnectException e) {

			System.err.println("Something Went Wrong with your Internet Connection.....");
			System.err.println("Check Your Internet.......");

		} 
		catch (Exception e) {

			System.err.println("Something Went Wrong with your URL.......");
			System.err.println("Check Your URL.......");
		} 

		return null;
	}


}


