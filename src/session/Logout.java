package session;

import java.io.IOException;
import java.io.PrintWriter;

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
			out.println("User logged out successfully. For login go to <a href='index.html'>Login page</a>");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
