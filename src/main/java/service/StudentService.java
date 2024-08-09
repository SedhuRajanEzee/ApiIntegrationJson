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

import dao.StudentDao;
import dto.Student;

public class StudentService {

	private StudentDao dao=new StudentDao();
	private Student u;

	public List<Object> saveAllStudent() {

		try {

			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://mocki.io/v1/50390753-556e-4a9d-a2b9-5006123a103e"))
					.GET()
					.build();

			// sending...request and getting response in HttpResponsestring format
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			Gson gson = new Gson();
			JsonObject jResponse = gson.fromJson(response.body(), JsonObject.class);
			JsonArray ja = jResponse.getAsJsonArray("data");
			
			List<Student> list=new ArrayList<Student>();

			for (JsonElement je : ja) {

				u= new Student();
				JsonObject jo = je.getAsJsonObject();
				u.setStudentId((jo.get("studentId").getAsInt()));
				u.setStudentName(jo.get("studentName").getAsString());
				u.setStudentLocation(jo.get("studentLocation").getAsString());
				u.setStudentEmail(jo.get("studentEmail").getAsString());
				u.setStudentPassword(jo.get("studentPassword").getAsString());

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


