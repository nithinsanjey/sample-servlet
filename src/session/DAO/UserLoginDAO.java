package session.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import session.Util.DBConnection;


public class UserLoginDAO {
	
	public static boolean validateLogin(String username, String password) {
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT * FROM user_login WHERE username=? AND password=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			else 
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return true;
	}
}
