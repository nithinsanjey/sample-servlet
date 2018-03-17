package session.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Logout extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html");
		
		HttpSession session = req.getSession();
		session.setAttribute("uname", "");
		session.invalidate();
		PrintWriter out;
		try {
			out = res.getWriter();
			out.println("User logged out successfully.");
			req.getRequestDispatcher("index.html").include(req, res);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}
}
