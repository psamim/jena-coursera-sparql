package org.bihe.semantic.model;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;


public class Modeling {

	private Model model;
	public void addStatement(String s, String p, String o){
		Resource subject = model.createResource(s);
		Property predicate = model.createProperty(p);
		RDFNode object = model.createResource(o);
		Statement stmt = model.createStatement(subject, predicate, object);
		model.add(stmt);
		
	}
	public void addLiteralStatement(String s, String p, String o){
		Resource subject = model.createResource(s);
		Property predicate = model.createProperty(p);
		Object object = model.createResource(o);
		Statement stmt = model.createLiteralStatement(subject, predicate, object);
		
		
		
	}
	public void writeModel(){
		model.write(System.out,"TTL");
	}
	
	
	
	
}
