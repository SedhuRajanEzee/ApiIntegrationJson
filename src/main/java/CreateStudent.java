import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateStudent {
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/studentapi";
		String user="root";
		String password="root";
		
		Connection con=DriverManager.getConnection(url, user, password);
		
		Statement st= con.createStatement();
		int status=st.executeUpdate("Create Table Student ( studentId int(15), studentName varchar(40), studentLocation varchar(40), studentEmail varchar(40), studentPassword varchar(60))");
		System.out.println(status);
		st.close();
		con.close();
	}
}
