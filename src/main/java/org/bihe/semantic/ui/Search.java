package org.bihe.semantic.ui;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import org.bihe.semantic.SPARQLParser.OpenUniversitySPARQLParser;
import org.bihe.semantic.jsonParser.CourseraJSonParser;
import org.bihe.semantic.model.Course;
import org.bihe.semantic.model.Modeling;
import org.bihe.semantic.utility.Utility;
import com.google.common.xml.XmlEscapers;
import com.hp.hpl.jena.rdf.model.Model;

public class Search {
	private String name;
	private String category;
	private String type;

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
		// Pass the course that user wants to find it in Coursera
		try {
			System.out.println(" Results on Coursera : ");
			CourseraJSonParser coursera = new CourseraJSonParser();
			ArrayList<Course> courseraCourseDetails = coursera
					.getCoursesByName(getName());
			Utility.printList(courseraCourseDetails);

			System.out.println(" Results on Open University : ");
			OpenUniversitySPARQLParser ou = new OpenUniversitySPARQLParser();
			ArrayList<Course> ouCourseDetails = ou.getCoursesByName(getName());
			Utility.printList(ouCourseDetails);

			@SuppressWarnings("unchecked")
			ArrayList<Course> courses = (ArrayList<Course>) courseraCourseDetails
					.clone();
			courses.addAll(ouCourseDetails);
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
