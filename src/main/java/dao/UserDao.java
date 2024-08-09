package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import dbConnection.DBConnection;
import dto.User;


public class UserDao {

	public void saveAllUser(List<User> list) {

		try {

			String url = "jdbc:mysql://localhost:3306/userapi";
			String user = "root";
			String password = "root";

			Connection con = DBConnection.getConnection(url, user, password);
			PreparedStatement pStatement = con.prepareStatement("Insert into User values(?,?,?,?,?)");

			for (User usr : list) {

				pStatement.setInt(1, usr.getUserId());
				pStatement.setString(2, usr.getUserEmail());
				pStatement.setString(3, usr.getFirst_name());
				pStatement.setString(4, usr.getLast_name());
				pStatement.setString(5, usr.getAvatar());

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
