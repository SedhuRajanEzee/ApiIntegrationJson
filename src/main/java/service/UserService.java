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
import dao.UserDao;
import dto.User;


public class UserService {

	private UserDao dao = new UserDao();
	private User u ;

	public List<Object> saveAllUser() {

		List<User> list=new ArrayList<User>();

		try {
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://reqres.in/api/users?page=2"))
					.GET()
					.build();
			
			// sending...request and getting response in HttpResponsestring format
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			// System.out.println("Response Code: " + response.statusCode());
			Gson gson = new Gson();
			JsonObject jResponse = gson.fromJson(response.body(), JsonObject.class);
			
			// System.out.println("Response: " + jResponse.toString());
			JsonArray ja = jResponse.getAsJsonArray("data");

			for (JsonElement je : ja) {

				u= new User();
				JsonObject jo = je.getAsJsonObject();
				u.setUserId(jo.get("id").getAsInt());
				u.setUserEmail(jo.get("email").getAsString());
				u.setFirst_name(jo.get("first_name").getAsString());
				u.setLast_name(jo.get("last_name").getAsString());
				u.setAvatar(jo.get("avatar").getAsString());

				list.add(u);
			}
			
			dao.saveAllUser(list);
			
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
