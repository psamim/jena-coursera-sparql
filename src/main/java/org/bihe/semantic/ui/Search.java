package org.bihe.semantic.ui;

import java.util.ArrayList;
import org.bihe.semantic.SPARQLParser.OpenUniversitySPARQLParser;
import org.bihe.semantic.jsonParser.CourseraJSonParser;
import org.bihe.semantic.model.Course;
import org.bihe.semantic.utility.Utility;

public class Search {
	private String name;
	private String category;
	private String type;
	private String instructor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<Course> getResults() {
		CourseraJSonParser coursera = new CourseraJSonParser();
		OpenUniversitySPARQLParser ou = new OpenUniversitySPARQLParser();
		ArrayList<Course> courseraCourses = new ArrayList<>();
		ArrayList<Course> ouCourses = new ArrayList<>();

		if (getInstructor() != null) { // By Instructor
			courseraCourses = coursera.getCoursesByInstructor(getInstructor());
		} else if (getName() != null) { // By name
			courseraCourses = coursera.getCoursesByName(getName());
			ouCourses = ou.getCoursesByName(getName());
		}

		System.out.println("Results on Coursera:");
		Utility.printList(courseraCourses);

		System.out.println("Results on Open University:");
		Utility.printList(ouCourses);

		courseraCourses.addAll(ouCourses);
		return courseraCourses;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
}
