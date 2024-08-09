package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {


	private static Connection con;

	//making the constructor as private to achieve singleton
	private DBConnection() {
		
	}
	
	public static Connection getConnection(String url,String user,String password) {
		
		if(con==null) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(url, user, password);

				return con;

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		
		return con;
	}

	
}
