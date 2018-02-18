package session.POJO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import session.DAO.UserNotesDAO;

public class UserNotes {
	
	private String username;
	private String notes;
	private Date date;
	private Time time;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	public int saveNote() {
		return UserNotesDAO.saveNote(username, notes, date, time);
	}
	
	public List<UserNotes> getAllNotes() {
		return UserNotesDAO.getAllNotes(username);
	}
	
	public int deleteOneUserAllNotes() {
		return UserNotesDAO.deleteOneUserAllNotes(username);
	}
}
