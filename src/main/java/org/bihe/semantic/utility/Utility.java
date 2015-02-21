package org.bihe.semantic.utility;

import java.util.ArrayList;

import org.bihe.semantic.model.Category;
import org.bihe.semantic.model.Course;
import org.bihe.semantic.model.Instructor;
import org.bihe.semantic.model.Session;
import org.bihe.semantic.model.University;

public class Utility {

	/**
	 * @author Mozhde
	 *
	 */
	public static void printList(ArrayList<Course> courseDetails) {
		if (courseDetails.size() == 0) {
			System.out.println("Nothing has been found!");
			return;
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

}
