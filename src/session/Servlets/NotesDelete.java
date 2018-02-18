package session.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import session.POJO.UserNotes;

public class NotesDelete extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
res.setContentType("text/html");
		
		HttpSession session = req.getSession();
		
		String username = (String) session.getAttribute("loggedInUser");
		try {
			if (username == null || username.equals("")) {
				res.sendRedirect("LoginProcess");
			}
			else {
				UserNotes userNotes = new UserNotes();
				userNotes.setUsername(username);
				userNotes.deleteOneUserAllNotes();
				res.sendRedirect("NotesProcess");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		doPost(req, res);
	}
}
