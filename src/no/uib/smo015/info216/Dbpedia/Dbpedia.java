package no.uib.smo015.info216.Dbpedia;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;

public class Dbpedia {
	
	private static final Model model = ModelFactory.createDefaultModel();
	
	public static final Property COUNTRY = model.createProperty("http://dbpedia.org/ontology/country");
	
	public static final Property LIFE_EXPECTANCY = model.createProperty("http://dbpedia.org/ontology/lifeExpectancy");
	
	public static final Property POPULATION = model.createProperty("http://dbpedia.org/ontology/population");
	
	public static final Property REGION = model.createProperty("http://dbpedia.org/ontology/region");

}
