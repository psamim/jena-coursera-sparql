package org.bihe.semantic.model;

import com.hp.hpl.jena.graph.NodeFactory;
import com.hp.hpl.jena.rdf.model.*;

public class Modeling {
	private Model model;

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

		Bag bag = model.createBag();

		for (Course course : courses) {
			String co = "http://www.coursera.org/course#";
			String cath = "http://www.coursera.org/cathegory#";
			String se = "http://www.coursera.org/session#";
			String ins = "http://www.coursera.org/instructor#";
			String uni = "http://www.coursera.org/university#";
			// String nsRDFS= "http://www.w3.org/2000/01/rdf-schema#";
			addStatement(co, co + "has", se);
			addStatement(co, co + "taughtby", ins);
			addStatement(co, co + "belongsto", cath);
			addStatement(co, co + "taughtin", uni);
			addStatement(co + course.getShortname(), co + "has", se);

			addLiteralStatement(co + course.getShortname(), co + "name",
					course.getCourseName());
			addLiteralStatement(co + course.getShortname(), co + "id",
					course.getId());
			addLiteralStatement(co + course.getShortname(), co + "has",
					course.getId());
			
//			bag.add()
		}

	}

	public void writeModel() {
		model.write(System.out, "TTL");
	}
}
