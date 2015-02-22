package org.bihe.semantic.SPARQLParser;

import java.util.ArrayList;
import org.bihe.semantic.model.Category;
import org.bihe.semantic.model.Course;
import org.bihe.semantic.model.CourseInfo;
import org.bihe.semantic.model.Instructor;
import org.bihe.semantic.model.Session;
import org.bihe.semantic.model.University;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

//Import log4j classes.
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class OpenUniversitySPARQLParser {
	// Define a static logger variable so that it references the
	// Logger instance named "MyApp".
	private static final Logger logger = LogManager.getLogger(OpenUniversitySPARQLParser.class);
	
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
	
	public ArrayList<Course> getCoursesByName(String courseName) {
		logger.trace("Entering application.");
		
		ArrayList<Course> courseDetailLists = new ArrayList<Course>();
		
		 String queryStr = 
				 "PREFIX mlo: <http://purl.org/net/mlo/>" +
	    			"SELECT DISTINCT ?url ?title ?label ?university ?id" +
	    			"where { " +
	    			"?thing <http://purl.org/dc/terms/title> ?title; " +
	    			"<http://www.w3.org/2000/01/rdf-schema#label> ?label;" +
	    			"<http://purl.org/dc/elements/1.1/identifier> ?id;" +
	    			"<http://courseware.rkbexplorer.com/ontologies/courseware#taught-at> ?university; " +
	    			"<http://purl.org/net/mlo/url> ?url. " +
	    			"FILTER regex(?title, '" + courseName + "', 'i' ) } \n ";
		 Query query = QueryFactory.create(queryStr);
		 // Remote execution.
		 try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://data.open.ac.uk/query", query) ) {
			 // Set the DBpedia specific timeout.
			 ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
			 // Execute.
			 ResultSet rs = qexec.execSelect() ;		 
			 // The order of results is undefined.
			 for ( ; rs.hasNext() ; )
			 {
				 QuerySolution rb = rs.nextSolution() ;
				 Course crs = new Course();
				 // Get title - variable names do not include the '?' (or '$')
				 RDFNode x = rb.get("title") ;
				 // Check the type of the result value
				 if ( x.isLiteral() )
				 {
					 Literal titleStr = (Literal)x ;
					 System.out.println(" "+titleStr.getValue().toString()) ;
					 crs.setCourseName(titleStr.getValue().toString());
				 }
				 x = rb.get("label") ;
				 // Check the type of the result value
				 if ( x.isLiteral() )
				 {
					 Literal labelStr = (Literal)x ;
					 System.out.println(" "+labelStr.getValue().toString()) ;
					 crs.setShortname(labelStr.getValue().toString());
				 }
				 x = rb.get("url") ;
				 // Check the type of the result value
				 if ( x.isLiteral() )
				 {
					 Literal urlStr = (Literal)x ;
					 System.out.println(" "+urlStr.getValue().toString()) ;
					 ArrayList<Session> sessionsName = new ArrayList<Session>();
					 Session session = new Session();
					 session.setHomepage(urlStr.getValue().toString());
					 sessionsName.add(session);
					 crs.setSessions(sessionsName);
				 }
				 x = rb.get("university") ;
				 // Check the type of the result value
				 if ( x.isLiteral() )
				 {
					 Literal universityStr = (Literal)x ;
					 System.out.println(" "+universityStr.getValue().toString()) ;
					 ArrayList<University> universitiesName = new ArrayList<University>();
					 University university = new University();
					 university.setName(universityStr.getValue().toString());
					 universitiesName.add(university);
					 crs.setUniversities(universitiesName);
				 }
				 crs.setOrigin(2);
				 courseDetailLists.add(crs);
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		logger.trace("Exiting application.");
		return courseDetailLists;
	}
}
