package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import dbConnection.DBConnection;
import dto.Student;

public class StudentDao {

	public void saveAllStudent(List<Student> list) {

		try {

			String url = "jdbc:mysql://localhost:3306/studentapi";
			String user = "root";
			String password = "root";

			Connection con = DBConnection.getConnection(url, user, password);
			PreparedStatement pStatement = con.prepareStatement("Insert into Student values(?,?,?,?,?)");

			for (Student usr : list) {

				pStatement.setInt(1, usr.getStudentId());
				pStatement.setString(2, usr.getStudentName());
				pStatement.setString(3, usr.getStudentEmail());
				pStatement.setString(4, usr.getStudentPassword());
				pStatement.setString(5, usr.getStudentLocation());

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
