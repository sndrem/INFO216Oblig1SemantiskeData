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
	
	public static final Property SUB_REGION = model.createProperty("http://dbpedia.org/ontology/subregion");
	
	public static final Property GDP = model.createProperty("http://dbpedia.org/ontology/grossDomesticProduct");

	public static final Property ABSTRACT = model.createProperty("http://dbpedia.org/ontology/abstract");
	
	public static final Property WELL_BEING = model.createProperty("http://dbpedia.org/page/Well-being");
	
}
