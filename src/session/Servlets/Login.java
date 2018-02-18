package session.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import session.POJO.UserLogin;
import session.Util.ApplicationContext;

public class Login extends HttpServlet{
	
	public void init() {
		ApplicationContext.setServletContext(getServletContext());
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html");
		try {			
			PrintWriter out = res.getWriter();
			HttpSession session = req.getSession();
			
			req.getRequestDispatcher("header.html").include(req, res);
			
			if (session.getAttribute("loggedInUser") == null || session.getAttribute("loggedInUser").equals("")) {
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				
				UserLogin user = new UserLogin();
				user.setUsername(username);
				user.setPassword(password);

				if (user.validateLogin()) {
					System.out.println("Logged in user : " + username);
					session.setAttribute("loggedInUser", username);
					res.sendRedirect("NotesProcess");
				}
				else {
					out.println("Login failed");
					req.getRequestDispatcher("index.html").include(req, res);
				}
			}
			else {
				res.sendRedirect("NotesProcess");
			}
			out.close();
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		doPost(req, res);
	}
}
