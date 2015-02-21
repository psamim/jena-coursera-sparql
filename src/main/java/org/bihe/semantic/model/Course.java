package org.bihe.semantic.model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Mozhde
 *
 */
public class Course implements Serializable {
	private long id;
	private String shortname;
	private String courseName;
	private int origin; // 1:Coursera  --- 2:Open University
	private ArrayList<Instructor> instructors= new ArrayList<Instructor>();
	private ArrayList<Category> categories = new ArrayList<Category>();
	private ArrayList<Session> sessions = new ArrayList<Session>();
	private ArrayList<University> universities= new ArrayList<University>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public ArrayList<Instructor> getInstructors() {
		return instructors;
	}
	public void setInstructors(ArrayList<Instructor> instructors) {
		this.instructors = instructors;
	}
	public ArrayList<Category> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}
	public ArrayList<Session> getSessions() {
		return sessions;
	}
	public void setSessions(ArrayList<Session> sessions) {
		this.sessions = sessions;
	}
	public ArrayList<University> getUniversities() {
		return universities;
	}
	public void setUniversities(ArrayList<University> universities) {
		this.universities = universities;
	}
}
