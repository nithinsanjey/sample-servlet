package session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Notes extends HttpServlet {
	public void init() {
		ApplicationContext.setServletContext(getServletContext());  //need code review here
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		//a set of database parsing loop to display the previous notes
		//a text area and button to make new entry
		res.setContentType("text/html");
		
		HttpSession session = req.getSession();
		
		String username = (String) session.getAttribute("loggedInUser");
		try {
			PrintWriter out = res.getWriter();
			if (username == null || username.equals("")) {
				res.sendRedirect("LoginProcess");
			} 
			else {
				String notes = req.getParameter("notes");
				if(notes != null) {
					java.util.Date utilDate = new java.util.Date();
				    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				    
					Time time = new Time(utilDate.getHours(), utilDate.getMinutes(), utilDate.getMinutes());
					UserNotes userNotes = new UserNotes();
					userNotes.setUsername(username);
					userNotes.setNotes(notes);
					userNotes.setDate(sqlDate);
					userNotes.setTime(time);
					userNotes.saveNote();
				}
				req.getRequestDispatcher("header.html").include(req, res);
				
				out.println("<h2>Welcome, " + username.substring(0, 1).toUpperCase() + username.substring(1) + "</h2>");
				
				System.out.println("Generating notes for username : " + username);
				
				req.getRequestDispatcher("notes.html").include(req, res);
				//display all the previous notes of that particular logged in user
				//requires db access
				UserNotes userNotes = new UserNotes();
				userNotes.setUsername(username);
				List<UserNotes> allUserNotes = userNotes.getAllNotes();
				allUserNotes.forEach(userNote -> {
					out.println("<p width='100px'>" + userNote.getNotes() + "</p>");
					out.println("<p>" + userNote.getDate() + " " + userNote.getTime() + "</p>");
				});
			}
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		doPost(req, res);
	}
}
