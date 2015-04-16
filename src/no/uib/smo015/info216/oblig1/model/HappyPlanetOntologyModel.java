package no.uib.smo015.info216.oblig1.model;

import no.uib.smo015.info216.HappyOntology.HappyOnt;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;

public class HappyPlanetOntologyModel {
	
	private Property id, country, subRegion, region, lifeExpectancy, wellBeing, 
	happyLifeYears, footPrint, description,  
	happyIndex, population, gdp, govRank, type, rank;
	
	private OntModel owlModel;
	private OntClass ontCountryClass;
	
	public HappyPlanetOntologyModel() {
		owlModel = ModelFactory.createOntologyModel();
		ontCountryClass = owlModel.createClass(HappyOnt.NS + "Country");
		
		
		
	}

}
