import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateEmployee {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/employeeapi";
		String user="root";
		String password="root";
		
		Connection con=DriverManager.getConnection(url, user, password);
		
		Statement st= con.createStatement();
		int status=st.executeUpdate("Create Table Employee ( employeeId int(15), employeeName varchar(40), employeeLocation varchar(40), employeeEmail varchar(40), employeePassword varchar(60))");
		System.out.println(status);
		st.close();
		con.close();
	}
}
