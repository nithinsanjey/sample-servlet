package session;

import java.sql.*;

import javax.servlet.ServletContext;

public class UserDAO {
//Class.forName("com.mysql.jdbc.Driver")
	
	private static ServletContext application;
	
	public void setApplication(ServletContext application) {
		this.application = application;
	}
	
	public static Connection getConnection(){  
        Connection con=null;  
        try{  
            Class.forName(application.getInitParameter("MysqlDriver"));  
            System.out.println("the application context :: Driver name : " + application.getInitParameter("MysqlDriver"));
            con=DriverManager.getConnection(application.getInitParameter("MysqlUrl") , "root" , "mysql");  
        }catch(Exception e){System.out.println(e);}  
        return con;  
    }  
	
	public static int save(User u) {
		
		Connection con = UserDAO.getConnection();
		int status = 0;
    try {
 	PreparedStatement ps=con.prepareStatement(  
                "insert into user_tbl(name) values (?)");
		ps.setString(1,u.getName());
		status = ps.executeUpdate();
		//maybe some commit problem
		con.close(); 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}    
		return status;
	}
	
}
