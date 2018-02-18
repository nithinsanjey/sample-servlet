package session.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;

public class DBConnection {
	private static Connection con;
	
	private DBConnection() {
		
	}
	
	public static Connection getConnection() {
		if (con == null) {
			try {
				Class.forName(ApplicationContext.getServletContext().getInitParameter("MysqlDriver"));
				con = DriverManager.getConnection(ApplicationContext.getServletContext().getInitParameter("MysqlUrl"), ApplicationContext.getServletContext().getInitParameter("MysqlUsername"), ApplicationContext.getServletContext().getInitParameter("MysqlPassword"));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
