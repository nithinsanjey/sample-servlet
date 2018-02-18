package session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Notes extends HttpServlet {

	public void init() {
		ApplicationContext.setServletContext(getServletContext());  //need code review here
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
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
					Time time = new Time(utilDate.getHours(), utilDate.getMinutes(), utilDate.getSeconds());
					
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
				
				UserNotes userNotes = new UserNotes();
				userNotes.setUsername(username);
				List<UserNotes> allUserNotes = userNotes.getAllNotes();
				out.println("<table><tr><th>Notes</th><th>Date</th><th>Time</th><th>Time diff</th></tr>");
				for (int i=0; i<allUserNotes.size(); i++) {
					UserNotes userNote = allUserNotes.get(i);
					String diff = "Start of day";
					if(i+1 < allUserNotes.size()) {
						UserNotes nextNote = allUserNotes.get(i+1);
						if(userNote.getDate().compareTo(nextNote.getDate()) == 0) {
							Time time1 = userNote.getTime();
							Time time2 = nextNote.getTime();
							int currentSeconds = time1.getHours()*60*60 + time1.getMinutes()*60 + time1.getSeconds();
							int pastSeconds = time2.getHours()*60*60 + time2.getMinutes()*60 + time2.getSeconds();
							int somedif = currentSeconds - pastSeconds;
							
							int hours = (int) Math.floor(somedif / (60 * 60));
							int minutes = (somedif / (60)) % 60;
							int seconds = (somedif) % 60; 
							
							diff = hours + "h:" +minutes + "m:" + seconds + "s";
						}
					}
					
					out.println("<tr><td>" + userNote.getNotes() + "</td>");
					out.println("<td>" + userNote.getDate() + "</td><td>" + userNote.getTime() + "</td><td>" + diff +"</td></tr>");
				}
				out.println("</table>");
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		doPost(req, res);
	}
}
