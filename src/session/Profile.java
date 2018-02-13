package session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Profile extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html");
		
		try {
			PrintWriter out = res.getWriter();
			
			HttpSession session = req.getSession();
			String userName = (String) session.getAttribute("uname");
			
			req.getRequestDispatcher("header.html").include(req, res);
			
			out.println("<h1>Welcome " + userName + "</h1>");
			
			out.println("Currently your profile is empty and you can do nothing about that");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
