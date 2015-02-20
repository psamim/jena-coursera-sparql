package org.bihe.semantic.jsonParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import org.bihe.semantic.model.Category;
import org.bihe.semantic.model.Course;
import org.bihe.semantic.model.CourseInfo;
import org.bihe.semantic.model.Instructor;
import org.bihe.semantic.model.Session;
import org.bihe.semantic.model.University;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Mozhde
 *
 */
public class CourseraJSonParser {
	static ArrayList<CourseInfo> courseLists = new ArrayList<CourseInfo>();
	static ArrayList<Category> categoryLists = new ArrayList<Category>();
	static ArrayList<Instructor> instructorLists = new ArrayList<Instructor>();
	static ArrayList<University> universityLists = new ArrayList<University>();
	static ArrayList<Session> sessionLists = new ArrayList<Session>();

	public CourseraJSonParser() {
		try {
			courseLists = readCourseInfo();

			categoryLists = readCategories();

			instructorLists = readInstructors();

			universityLists = readUniversities();

			sessionLists = readSessions();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) throws Exception {
	 * 
	 * ArrayList<Course> courseDetails = getCourseDetail("research");
	 * printList(courseDetails);
	 * 
	 * }
	 */

	public void printList(ArrayList<Course> courseDetails) {
		if (courseDetails.size() == 0) {
			System.out.println("Nothing has been found in Coursera !");
		}
		for (int i = 0; i < courseDetails.size(); i++) {
			System.out.println("CourseId: " + courseDetails.get(i).getId());
			System.out.println("CourseName: "
					+ courseDetails.get(i).getCourseName());
			System.out.println("ShortName: "
					+ courseDetails.get(i).getShortname());

			System.out.print("Categories: ");
			ArrayList<Category> c = courseDetails.get(i).getCategories();
			for (int j = 0; j < c.size(); j++) {
				System.out.print(c.get(j).getCategoryName());
				if (j != c.size() - 1)
					System.out.print(" , ");
			}
			System.out.println();
			System.out.print("Universities: ");
			ArrayList<University> u = courseDetails.get(i).getUniversities();
			for (int j = 0; j < u.size(); j++) {
				System.out.print(u.get(j).getName() == null ? u.get(j)
						.getShortName() : u.get(j).getName());
				if (j != u.size() - 1)
					System.out.print(" , ");
			}
			System.out.println();
			System.out.print("Instructors: ");
			ArrayList<Instructor> in = courseDetails.get(i).getInstructors();
			for (int j = 0; j < in.size(); j++) {
				System.out.print(in.get(j).getFirstname() + " "
						+ in.get(j).getLastname());
				if (j != in.size() - 1)
					System.out.print(" , ");

			}
			System.out.println();
			System.out.print("Sessions: ");
			ArrayList<Session> s = courseDetails.get(i).getSessions();
			for (int j = 0; j < s.size(); j++) {
				System.out.print(s.get(j).getHomepage());
				if (j != s.size() - 1)
					System.out.print(" , ");
			}

			System.out.println();
			System.out.println("-----------------------------");

		}

	}

	public ArrayList<Course> getCoursesByName(String courseName)
			throws Exception {

		ArrayList<Course> courseDetailLists = new ArrayList<Course>();

		for (int i = 0; i < courseLists.size(); i++) {
			if (courseLists.get(i).getCourseName().toLowerCase()
					.contains(courseName.toLowerCase())
					|| courseLists.get(i).getShortName().toLowerCase()
							.contains(courseName.toLowerCase())) {
				Course courseDetail = new Course();
				courseDetail.setId(courseLists.get(i).getCourseId());
				courseDetail.setCourseName(courseLists.get(i).getCourseName());
				courseDetail.setShortname(courseLists.get(i).getShortName());

				// ///////// Categories/////////////////////////////////
				ArrayList<Long> curr_categories = courseLists.get(i)
						.getCategories();
				ArrayList<Category> categoriesName = new ArrayList<Category>();

				if (curr_categories != null)
					for (int cat = 0; cat < (int) curr_categories.size(); cat++) {
						for (int catlist = 0; catlist < categoryLists.size(); catlist++) {
							if (categoryLists.get(catlist).getId() == curr_categories
									.get(cat)) {
								categoriesName.add(categoryLists.get(catlist));
							}
						}
					}
				courseDetail.setCategories(categoriesName);

				// //////////Universities/////////////////////////////
				ArrayList<Long> curr_universities = courseLists.get(i)
						.getUniversities();
				ArrayList<University> universitiesName = new ArrayList<University>();

				if (curr_universities != null)
					for (int uni = 0; uni < (int) curr_universities.size(); uni++) {
						for (int unilist = 0; unilist < universityLists.size(); unilist++) {
							if (universityLists.get(unilist).getId() == curr_universities
									.get(uni)) {
								universitiesName.add(universityLists
										.get(unilist));
							}
						}

					}
				courseDetail.setUniversities(universitiesName);

				// //////////instructors/////////////////////////////
				ArrayList<Long> curr_instructors = courseLists.get(i)
						.getInstructors();
				ArrayList<Instructor> instructorsName = new ArrayList<Instructor>();

				if (curr_instructors != null)
					for (int ins = 0; ins < (int) curr_instructors.size(); ins++) {
						for (int inslist = 0; inslist < instructorLists.size(); inslist++) {
							if (instructorLists.get(inslist).getId() == curr_instructors
									.get(ins)) {
								instructorsName.add(instructorLists
										.get(inslist));
							}
						}

					}
				courseDetail.setInstructors(instructorsName);

				// //////////sessions/////////////////////////////
				ArrayList<Long> curr_sessions = courseLists.get(i)
						.getSessions();
				ArrayList<Session> sessionsName = new ArrayList<Session>();

				if (curr_sessions != null)
					for (int se = 0; se < (int) curr_sessions.size(); se++) {
						for (int selist = 0; selist < sessionLists.size(); selist++) {
							if (sessionLists.get(selist).getId() == curr_sessions
									.get(se)) {
								sessionsName.add(sessionLists.get(selist));
							}
						}

					}
				courseDetail.setSessions(sessionsName);

				// /////////////////////////////////
				courseDetailLists.add(courseDetail);

			}

		}

		return courseDetailLists;
	}

	public ArrayList<CourseInfo> readCourseInfo() throws Exception {

		JSONParser parser = new JSONParser();
		JSONArray jsonObjectArr = (JSONArray) parser.parse(new FileReader(
				"courseraJsonFiles" + File.separator + "courseInfo.json"));

		ArrayList<CourseInfo> courseLists = new ArrayList<CourseInfo>();
		for (int i = 0; i < jsonObjectArr.size(); i++) {

			CourseInfo course = new CourseInfo();
			JSONObject elements = (JSONObject) jsonObjectArr.get(i);
			String name = (String) elements.get("name");
			course.setCourseName(name);
			String shortName = (String) elements.get("shortName");
			course.setShortName(shortName);
			long courseID = (long) elements.get("id");
			course.setCourseId(courseID);
			// loop array
			JSONObject jsonObjectDetail = (JSONObject) parser.parse(elements
					.get("links").toString());

			JSONArray sessions = (JSONArray) jsonObjectDetail.get("sessions");
			course.setSessions(sessions);

			JSONArray categories = (JSONArray) jsonObjectDetail
					.get("categories");
			course.setCategories(categories);

			JSONArray universities = (JSONArray) jsonObjectDetail
					.get("universities");
			course.setUniversities(universities);

			JSONArray instructors = (JSONArray) jsonObjectDetail
					.get("instructors");
			course.setInstructors(instructors);

			courseLists.add(course);
		}

		return courseLists;

	}

	public ArrayList<Category> readCategories() throws Exception {
		JSONParser parser = new JSONParser();

		JSONArray jsonObjectArr = (JSONArray) parser.parse(new FileReader(
				"courseraJsonFiles" + File.separator + "categories.json"));

		ArrayList<Category> categoryLists = new ArrayList<Category>();
		for (int i = 0; i < jsonObjectArr.size(); i++) {

			Category category = new Category();
			JSONObject elements = (JSONObject) jsonObjectArr.get(i);
			String categoryName = (String) elements.get("name");
			category.setCategoryName(categoryName);
			;
			String shortName = (String) elements.get("shortName");
			category.setShortname(shortName);
			long categoryID = (long) elements.get("id");
			category.setId(categoryID);

			categoryLists.add(category);
		}

		return categoryLists;

	}

	public ArrayList<Instructor> readInstructors() throws Exception {
		JSONParser parser = new JSONParser();

		JSONArray jsonObjectArr = (JSONArray) parser.parse(new FileReader(
				"courseraJsonFiles" + File.separator + "instructors.json"));

		ArrayList<Instructor> instructorLists = new ArrayList<Instructor>();
		for (int i = 0; i < jsonObjectArr.size(); i++) {

			Instructor instructor = new Instructor();
			JSONObject elements = (JSONObject) jsonObjectArr.get(i);
			String firstName = (String) elements.get("firstName");
			instructor.setFirstname(firstName);
			String lastName = (String) elements.get("lastName");
			instructor.setLastname(lastName);
			long instructorID = (long) elements.get("id");
			instructor.setId(instructorID);

			instructorLists.add(instructor);
		}

		return instructorLists;

	}

	public ArrayList<University> readUniversities() throws Exception {
		JSONParser parser = new JSONParser();

		JSONArray jsonObjectArr = (JSONArray) parser.parse(new FileReader(
				"courseraJsonFiles" + File.separator + "universities.json"));

		ArrayList<University> universityLists = new ArrayList<University>();
		for (int i = 0; i < jsonObjectArr.size(); i++) {

			University university = new University();
			JSONObject elements = (JSONObject) jsonObjectArr.get(i);
			String universityName = (String) elements.get("name");
			university.setName(universityName);
			;
			String shortName = (String) elements.get("shortName");
			university.setShortName(shortName);
			long universityID = (long) elements.get("id");
			university.setId(universityID);

			universityLists.add(university);
		}

		return universityLists;

	}

	public ArrayList<Session> readSessions() throws Exception {
		JSONParser parser = new JSONParser();

		JSONArray jsonObjectArr = (JSONArray) parser.parse(new FileReader(
				"courseraJsonFiles" + File.separator + "sessions.json"));

		ArrayList<Session> sessionLists = new ArrayList<Session>();
		for (int i = 0; i < jsonObjectArr.size(); i++) {

			Session session = new Session();
			JSONObject elements = (JSONObject) jsonObjectArr.get(i);
			String homeLink = (String) elements.get("homeLink");
			session.setHomepage(homeLink);
			long sessionID = (long) elements.get("id");
			session.setId(sessionID);

			sessionLists.add(session);
		}

		return sessionLists;

	}
}
