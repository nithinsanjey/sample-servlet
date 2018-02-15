package session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

public class Login extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html");
		try {
			PrintWriter out = res.getWriter();
			
			HttpSession session = req.getSession();
			
			String userName = null;
			if (session.getAttribute("uname") != null) {
				userName = (String) session.getAttribute("uname");
				out.println("User : " + userName + " has already logged in. Please logout if you are a different user.<br>");
				out.println("<a href='ProfileProcess'>View Profile</a>");
			}
			//set the database driver in web.xml
			//user user_login database for session
			//if(!userName.equals("") || userName == null) {   mysql --host=localhost --port=3306 --user=root -p
			else {
				userName = req.getParameter("uname");
				out.println("Welcome " + userName);
				UserDAO userDAO = new UserDAO();
				
				ServletContext application = getServletContext();
				userDAO.setApplication(application);
				//System.out.println("the application context :: Driver name : " + application.getInitParameter("MysqlDriver"));
				User u = new User();
				u.setName(userName);
				userDAO.save(u);
				
				session.setAttribute("uname", userName);
				out.println("<a href='ProfileProcess'>View Profile</a>");
			}
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		doPost(req, res);
	}
}
