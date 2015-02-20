package org.bihe.semantic.ui;

import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class Main {
	// HTML template in src/main/resources/spark/template/freemarker
	public static final String TEMPLATE = "template.ftl";

	public static void main(String[] args) {
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
			search.getResults();

			return new ModelAndView(attributes, TEMPLATE);
		}, new FreeMarkerEngine());
	}
}
