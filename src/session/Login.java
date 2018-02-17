package session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

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
					out.println("<h2>Welcome, " + username + "</h2>");
					req.getRequestDispatcher("welcome.html").include(req, res);
					res.sendRedirect("NotesProcess");
					//req.getRequestDispatcher("NotesProcess").forward(req, res);
				}
				else {
					out.println("Login failed");
					req.getRequestDispatcher("index.html").include(req, res);
				}
			}
			else {
				String username = (String) session.getAttribute("loggedInUser");
				out.println("User already logged in.");
				out.println("Welcome, " + username);
				//req.getRequestDispatcher("NotesProcess").forward(req, res);
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
