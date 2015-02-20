package org.bihe.semantic.ui;

import java.util.ArrayList;

import org.bihe.semantic.SPARQLParser.OpenUniversitySPARQLParser;
import org.bihe.semantic.jsonParser.CourseraJSonParser;
import org.bihe.semantic.model.Course;
import org.bihe.semantic.utility.Utility;

public class Search {
	private String name;
	private String category;

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

	public void getResults() {
		// Pass the course that user wants to find it in Coursera

		System.out.println(" Results on Coursera : ");
		try {
			CourseraJSonParser coursera = new CourseraJSonParser();
			ArrayList<Course> courseraCourseDetails = coursera
					.getCoursesByName(getName());
			Utility.printList(courseraCourseDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" Results on Open University : ");
		try {
			System.out.println(getName());
			OpenUniversitySPARQLParser ou = new OpenUniversitySPARQLParser();
			ArrayList<Course> courseraCourseDetails = ou
					.getCoursesByName(getName());
			Utility.printList(courseraCourseDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
