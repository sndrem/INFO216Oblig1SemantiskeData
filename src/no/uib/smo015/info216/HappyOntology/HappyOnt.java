package no.uib.smo015.info216.HappyOntology;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class HappyOnt {
	// Modellen som gjør at vi kan opprette ressurser og properties
	private static Model model = ModelFactory.createDefaultModel();
	//Namespace definert som en string
	public final static String NS = "http://happyPlanetOntology/ontology#";
	// Ressursen for et country
	public final static Resource COUNTRY = model.createResource(HappyOnt.NS + "country");
	
	

}
