package org.bihe.semantic.SPARQLParser;

import java.util.ArrayList;
import java.util.List;

import org.bihe.semantic.model.Category;
import org.bihe.semantic.model.Course;
import org.bihe.semantic.model.CourseInfo;
import org.bihe.semantic.model.Instructor;
import org.bihe.semantic.model.Session;
import org.bihe.semantic.model.University;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

public class OpenUniversitySPARQLParser {
	static ArrayList<CourseInfo> courseLists = new ArrayList<CourseInfo>();
	static ArrayList<Category> categoryLists = new ArrayList<Category>();
	static ArrayList<Instructor> instructorLists = new ArrayList<Instructor>();
	static ArrayList<University> universityLists = new ArrayList<University>();
	static ArrayList<Session> sessionLists = new ArrayList<Session>();

	public OpenUniversitySPARQLParser() {
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Course> getCoursesByName(String courseName)
			throws Exception {
		ArrayList<Course> courseDetailLists = new ArrayList<Course>();
		
		 String queryStr = 
				 "PREFIX mlo: <http://purl.org/net/mlo/>" +
	    			"SELECT DISTINCT ?title ?url " +
	    			"where { " +
	    			"?thing <http://purl.org/dc/terms/title> ?title; " +
	    			"<http://dbpedia.org/property/url> ?url. " +
	    			"FILTER regex(?title, '" + courseName + "', 'i' ) } \n ";
		 Query query = QueryFactory.create(queryStr);
		 // Remote execution.
		 try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://data.open.ac.uk/query", query) ) {
			 // Set the DBpedia specific timeout.
			 ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
			 // Execute.
			 ResultSet rs = qexec.execSelect();
			 List<String> resultVars =  rs.getResultVars();
			 for (int i = 0; i < resultVars.size(); i++) {
				System.out.print(resultVars.get(i));
			 }
			 ResultSetFormatter.out(System.out, rs, query);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	    
		return courseDetailLists;
	}
}
