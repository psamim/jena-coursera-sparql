package org.bihe.semantic.ui;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bihe.semantic.jsonParser.CourseraJSonParser;
import org.bihe.semantic.model.Course;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class Main {
	// HTML template in src/main/resources/spark/template/freemarker
	public static final String TEMPLATE = "template.ftl";

	public static void main(String[] args) {

		// Added by Mozhde
		try {
			CourseraJSonParser coursera = new CourseraJSonParser();
			// pass the course that user wants to find it in Coursera
			String searchedCourse="big";
			ArrayList<Course> courseDetails = coursera
					.getCourseDetail(searchedCourse);
			coursera.printList(courseDetails);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// END Mozhde

		staticFileLocation("/public"); // Static files

		get("/search", (rq, rs) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("page", "search");
			return new ModelAndView(attributes, TEMPLATE);
		}, new FreeMarkerEngine());

		get("/results", (rq, rs) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("page", "results");

			Search search = new Search();
			search.setCategory(rq.queryParams("category"));
			search.setName(rq.queryParams("name"));

			return new ModelAndView(attributes, TEMPLATE);
		}, new FreeMarkerEngine());
	}
}
