package session.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import session.POJO.UserNotes;
import session.Util.DBConnection;

public class UserNotesDAO {
	
	public static int saveNote(String username, String notes, Date date, Time time) {
		Connection con = DBConnection.getConnection();
		String sql = "INSERT INTO USER_NOTES VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, notes);
			ps.setDate(3, date);
			ps.setTime(4, time);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static List<UserNotes> getAllNotes(String username) {
		Connection con = DBConnection.getConnection();
		String sql = "SELECT * FROM USER_NOTES WHERE USERNAME=? ORDER BY DATE DESC, TIME DESC";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			List<UserNotes> allUserNotes = new ArrayList<>();
			while (rs.next()) {
				UserNotes userNotes = new UserNotes();
				userNotes.setUsername(rs.getString(1));
				userNotes.setNotes(rs.getString(2));
				userNotes.setDate(rs.getDate(3));
				userNotes.setTime(rs.getTime(4));
				allUserNotes.add(userNotes);
			}
			return allUserNotes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int deleteOneUserAllNotes(String username) {
		Connection con = DBConnection.getConnection();
		String sql = "DELETE FROM USER_NOTES WHERE USERNAME=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
