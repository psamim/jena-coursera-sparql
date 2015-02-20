package org.bihe.semantic.model;

import java.util.ArrayList;

/**
 * @author Mozhde
 *
 */
public class CourseInfo {
	long courseId;
	String shortName;
	String courseName;
	ArrayList<Long> categories = new ArrayList<Long>();
	ArrayList<Long> universities = new ArrayList<Long>();
	ArrayList<Long> sessions = new ArrayList<Long>();
	ArrayList<Long> instructors = new ArrayList<Long>();

	public CourseInfo(long courseId, String shortName, String courseName,
			ArrayList<Long> categories, ArrayList<Long> universities,
			ArrayList<Long> sessions, ArrayList<Long> instructors) {
		super();
		this.courseId = courseId;
		this.shortName = shortName;
		this.courseName = courseName;
		this.categories = categories;
		this.universities = universities;
		this.sessions = sessions;
		this.instructors = instructors;
	}

	public CourseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public ArrayList<Long> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Long> categories) {
		this.categories = categories;
	}

	public ArrayList<Long> getUniversities() {
		return universities;
	}

	public void setUniversities(ArrayList<Long> universities) {
		this.universities = universities;
	}

	public ArrayList<Long> getSessions() {
		return sessions;
	}

	public void setSessions(ArrayList<Long> sessions) {
		this.sessions = sessions;
	}

	public ArrayList<Long> getInstructors() {
		return instructors;
	}

	public void setInstructors(ArrayList<Long> instructors) {
		this.instructors = instructors;
	}
}
