package org.bihe.semantic.model;

public class Course {
	private int id;
	private String shortname;
	private Instructor[] instructors;
	private Cathegory[] cathegories;
	private Session[] session;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public Instructor[] getInstructors() {
		return instructors;
	}

	public void setInstructors(Instructor[] instructors) {
		this.instructors = instructors;
	}

	public Cathegory[] getCathegories() {
		return cathegories;
	}

	public void setCathegories(Cathegory[] cathegories) {
		this.cathegories = cathegories;
	}

	public Session[] getSession() {
		return session;
	}

	public void setSession(Session[] session) {
		this.session = session;
	}

}
