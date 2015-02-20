package org.bihe.semantic.model;

import com.hp.hpl.jena.rdf.model.*;

public class Modeling {
	private Model model;
	private Bag bag;

	public static void main(String[] args) {

		// Category category = new Category();
		// category.setCategoryName("Cat name");
		// category.setId(1);
		// category.setShortname("Short cat name");

		Course course = new Course();
		course.setCourseName("Test Course");
		course.setId(1);
		course.setShortname("ShortTestName");

		Course[] courses = { course };
		Modeling modeling = new Modeling();
		modeling.createModel(courses);

		modeling.writeModel();

	}

	public void addStatement(String s, String p, String o) {
		Resource subject = model.createResource(s);
		Property predicate = model.createProperty(p);
		RDFNode object = model.createResource(o);
		Statement stmt = model.createStatement(subject, predicate, object);
		model.add(stmt);
	}

	public void addLiteralStatement(String s, String p, Object o) {
		Resource subject = model.createResource(s);
		Property predicate = model.createProperty(p);
		Statement stmt = model.createLiteralStatement(subject, predicate, o);
		model.add(stmt);
	}

	public void createModel(Course[] courses) {
		model = ModelFactory.createDefaultModel();
		bag = model.createBag();
			// String nsRDFS= "http://www.w3.org/2000/01/rdf-schema#";
//			addStatement(Constant.COURSE_URI, Constant.COURSE_URI + "has",
//					Constant.SESSION_URI);
//			addStatement(Constant.COURSE_URI, Constant.COURSE_URI + "taughtby",
//					Constant.INSTRUCTOR_URI);
//			addStatement(Constant.COURSE_URI,
//					Constant.COURSE_URI + "belongsto", Constant.CATHEGORY_URI);
//			addStatement(Constant.COURSE_URI, Constant.COURSE_URI + "taughtin",
//					Constant.CATHEGORY_URI);

		for (Course course : courses) {
			addLiteralStatement(Constant.COURSE_URI + course.getShortname(),
					Constant.COURSE_URI + "name", course.getCourseName());
			addLiteralStatement(Constant.COURSE_URI + course.getShortname(),
					Constant.COURSE_URI + "id", course.getId());

			addToBag(Constant.COURSE_URI + course.getShortname());
		}

	}

	private void addToBag(String courseresource) {
		Resource resource = model.createResource(courseresource);

		bag.add(resource);
	}

	public void writeModel() {
		model.write(System.out, "TTL");
	}
}
