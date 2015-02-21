package org.bihe.semantic.ui;

import static spark.Spark.*;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bihe.semantic.model.Course;
import org.bihe.semantic.model.Modeling;
import com.google.common.xml.XmlEscapers;
import com.hp.hpl.jena.rdf.model.Model;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class Main {
	// HTML template in src/main/resources/spark/template/freemarker
	public static final String TEMPLATE = "template.ftl";
    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 4567;

	public static void main(String[] args) {
            ipAddress(IP_ADDRESS);
	    port(PORT);
		staticFileLocation("/public"); // Static files

		get("/search", (rq, rs) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("page", "search");
			return new ModelAndView(attributes, TEMPLATE);
		}, new FreeMarkerEngine());

		get("/results", (rq, rs) -> {
			Search search = new Search();
			search.setCategory(rq.queryParams("category"));
			search.setName(rq.queryParams("name"));
			search.setType(rq.queryParams("type"));

			Map<String, Object> attributes = new HashMap<>();
			attributes.put("page", "results");
			attributes.put("url", rq.url());
			attributes.put("query", rq.queryMap().toMap());

			ArrayList<Course> courses = search.getResults();

			if (search.getType().equals("table")) { // Show in a table
					attributes.put("results", courses);
				} else { // Or as XML and TTL
					Model model = new Modeling().createModel(courses);
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
					model.write(outputStream, search.getType());
					String results = XmlEscapers.xmlContentEscaper().escape(
							outputStream.toString());
					attributes.put("results", results);
				}

				return new ModelAndView(attributes, TEMPLATE);
			}, new FreeMarkerEngine());

		get("/about", (rq, rs) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("page", "about");
			return new ModelAndView(attributes, TEMPLATE);
		}, new FreeMarkerEngine());
	}
}
