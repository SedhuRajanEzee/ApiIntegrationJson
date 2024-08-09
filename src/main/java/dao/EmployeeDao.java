package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import dbConnection.DBConnection;
import dto.Employee;

public class EmployeeDao {

	public void saveAllStudent(List<Employee> list) {

		try {

			String url = "jdbc:mysql://localhost:3306/employeeapi";
			String user = "root";
			String password = "root";

			Connection con = DBConnection.getConnection(url, user, password);
			PreparedStatement pStatement = con.prepareStatement("Insert into Employee values(?,?,?,?,?)");

			for (Employee usr : list) {

				pStatement.setInt(1, usr.getEmployeeId());
				pStatement.setString(2, usr.getEmployeeName());
				pStatement.setString(3, usr.getEmployeeLocation());
				pStatement.setString(4, usr.getEmployeeEmail());
				pStatement.setString(5, usr.getEmployeePassword());

				pStatement.addBatch();
			}
			
			pStatement.executeBatch();
			pStatement.close();
			con.close();
		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}
}
