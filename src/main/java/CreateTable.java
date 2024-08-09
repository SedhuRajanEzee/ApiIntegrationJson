import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/userapi";
		String user="root";
		String password="root";
		
		Connection con=DriverManager.getConnection(url, user, password);		
		Statement st= con.createStatement();
		int status=st.executeUpdate("Create Table User ( userId int(15), userEmail varchar(40), first_name varchar(40), last_name varchar(40), avatar varchar(60)); ");
		System.out.println(status);
		st.close();
		con.close();
	}
	
}

