package org.bihe.semantic.model;

import java.util.ArrayList;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.VCARD;

/**
 * @author Shiva
 *
 */
public class Modeling {
	private Model model;
	private Bag bag;

	public static void main(String[] args) {

		// Category category = new Category();
		// category.setCategoryName("Cat name");
		// category.setId(1);
		// category.setShortname("Short cat name");

		Course course1 = new Course();
		course1.setCourseName("Test Course");
		course1.setId(1);
		course1.setShortname("ShortTestName");
		Instructor instructor1 = new Instructor();
		instructor1.setFirstname("hamid");
		instructor1.setLastname("hoveydaye");
		Instructor instructor2 = new Instructor();
		instructor2.setFirstname("jalil");
		instructor2.setLastname("sadeghi");
		ArrayList<Instructor> instrcutors = new ArrayList<Instructor>();
		instrcutors.add(instructor2);
		instrcutors.add(instructor2);
		course1.setInstructors(instrcutors);

		Course course2 = new Course();
		course2.setCourseName("Final Course");
		course2.setId(2);
		course2.setShortname("FinalTestName");
		Session session = new Session();
		session.setId(234);
		session.setHomepage("http:www.google.com");

		ArrayList<Session> sessions = new ArrayList<Session>();
		sessions.add(session);

		course2.setSessions(sessions);
		course1.setSessions(sessions);

		Course[] courses = { course1, course2 };
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

	public void addToModel(Resource s, String p, Object o) {
		// Resource subject = model.createResource(s);
		Property predicate = createProperty(p);
		Statement stmt = model.createLiteralStatement(s, predicate, o);
		model.add(stmt);
	}

	public void createModel(Course[] courses) {
		model = ModelFactory.createDefaultModel();
		bag = model.createBag();
		// addStatement(Constant.COURSE_URI, Constant.COURSE_URI + "has",
		// Constant.SESSION_URI);
		// addStatement(Constant.COURSE_URI, Constant.COURSE_URI + "taughtby",
		// Constant.INSTRUCTOR_URI);
		// addStatement(Constant.COURSE_URI,
		// Constant.COURSE_URI + "belongsto", Constant.CATHEGORY_URI);
		// addStatement(Constant.COURSE_URI, Constant.COURSE_URI + "taughtin",
		// Constant.CATHEGORY_URI);

		for (Course course : courses) {
			Resource courseresouce = model.createResource(Constant.COURSE_URI
					+ course.getShortname());

			courseresouce = addLiteralProperties(courseresouce, course);

			addToBag(courseresouce);
		}

	}

	private Resource addLiteralProperties(Resource courseresouce, Course course) {

		courseresouce = model.createResource(
				Constant.COURSE_URI + course.getShortname()).addProperty(
				createProperty(Constant.COURSE_URI + "name"),
				course.getCourseName());
		courseresouce = addInstrcutorsProperty(course.getInstructors(),
				courseresouce);
		courseresouce = addSessionProperty(course.getSessions(), courseresouce);
		courseresouce = addUniversityProperty(course.getUniversities(),
				courseresouce);
		addToModel(courseresouce, Constant.COURSE_URI + "id", course.getId());
		return courseresouce;
	}

	private Resource addUniversityProperty(ArrayList<University> universities,
			Resource courseresouce) {
		for (University university : universities) {
			courseresouce.addProperty(
					VCARD.N,
					model.createResource()
							.addProperty(createProperty(Constant.RDF + "type"),
									Constant.UNIVERSITY_URI)
							.addProperty(
									createProperty(Constant.UNIVERSITY_URI
											+ "id"),
									Long.toString(university.getId())))
					.addProperty(
							createProperty(Constant.UNIVERSITY_URI + "name"),
							university.getName());
		}

		return courseresouce;
	}

	private Resource addSessionProperty(ArrayList<Session> sessions,
			Resource courseresouce) {
		for (Session session : sessions) {
			courseresouce
					.addProperty(
							VCARD.N,
							model.createResource()
									.addProperty(
											createProperty(Constant.RDF
													+ "type"),
											Constant.SESSION_URI)
									.addProperty(
											createProperty(Constant.SESSION_URI
													+ "id"),
											Long.toString(session.getId())))
					.addProperty(
							createProperty(Constant.SESSION_URI + "homepage"),
							session.getHomepage());

		}
		return courseresouce;
	}

	private Resource addInstrcutorsProperty(ArrayList<Instructor> instructors,
			Resource courseresouce) {
		for (Instructor instructor : instructors) {
			courseresouce.addProperty(
					VCARD.N,
					model.createResource()
							.addProperty(createProperty(Constant.RDF + "type"),
									Constant.INSTRUCTOR_URI)
							.addProperty(
									createProperty(Constant.INSTRUCTOR_URI
											+ "name"),
									instructor.getFirstname())
							.addProperty(
									createProperty(Constant.COURSE_URI
											+ "lastname"),
									instructor.getLastname()));

		}
		return courseresouce;
	}

	private Property createProperty(String s) {
		Property property = model.createProperty(s);
		return property;
	}

	private void addToBag(Resource courseresource) {
		// Resource resource = model.createResource(courseresource);

		bag.add(courseresource);
	}

	public void writeModel() {
		model.write(System.out, "TTL");
	}
}
