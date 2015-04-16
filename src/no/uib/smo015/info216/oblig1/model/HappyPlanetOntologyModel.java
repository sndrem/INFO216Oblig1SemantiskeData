package no.uib.smo015.info216.oblig1.model;

import no.uib.smo015.info216.HappyOntology.HappyOnt;
import no.uib.smo015.info216.Utils.StringUtilities;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class HappyPlanetOntologyModel {
	
	private Property id, country, subRegion, region, lifeExpectancy, wellBeing, 
	happyLifeYears, footPrint, description,  
	happyIndex, population, gdp, govRank, type, rank;
	
	private OntModel owlModel;
	private OntClass ontCountryClass;
	private Dataset dataset;
	
	public HappyPlanetOntologyModel() {
		owlModel = ModelFactory.createOntologyModel();
		ontCountryClass = owlModel.createClass(HappyOnt.NS + "Country");
		
		/**
		 * TODO 1. 
		 * for hvert land --> opprett individer med ulike egenskaper (range og domain + type osv)
		 */
		
		/**
		 * TODO 2. 
		 * Legg til hvert individ i countryklassen med base og navn
		 */
		
		/**
		 * TODO 3.
		 * Legg til properties for domain, range, type osv.
		 */
		
		Resource res = tempModel.createResource(HappyOnt.NS + countryName);
		res.addProperty(data.getId(),tempModel.createTypedLiteral(index))
		.addLiteral(data.getRank(), new Integer(props[0]))
		.addLiteral(data.getSubRegion(), props[2])
		.addLiteral(data.getLifeExpectancy(), new Float(props[3]))
		.addLiteral(data.getWellBeing(), new Float(props[4]))
		.addLiteral(data.getHappyLifeYears(), new Float(props[5]))
		.addLiteral(data.getFootPrint(), new Float(props[6]))
		.addLiteral(data.getHappyIndex(), new Float(props[7]))
		.addLiteral(data.getPopulation(), new Integer(props[8]))
		.addLiteral(data.getGdp(), nf.format(new Integer(props[9])))
		.addLiteral(data.getRegion(), computeRegion(props[2]))
		.addProperty(RDF.type, HappyOnt.COUNTRY)
		.addProperty(RDFS.label, StringUtilities.congoConvert(StringUtilities.underscoreRemoval(countryName)))
		.addProperty(data.getDescription(), StringUtilities.congoConvert(StringUtilities.underscoreRemoval(countryName)) + " is a country in the region of the " + 
											StringUtilities.underscoreRemoval(computeRegion(props[2])) + "." + "\nThe population is ca. " + nf.format(new Integer(props[8])) + 
		                                    " and the Happy Index was measured to " + props[7] + " in 2012.\n" +
		                                    "The well being is " + props[4] + " and they have a measured\n" +
		                                    "happy years of " + props[5] + ". They are ranked as number " + 
		                                    props[0] + " of all the countries.\nThis is based " +
		                                    "on their geologial footprint which is " + props[6] + "\nand their " +
		                                    "well being of " + props[4]);
		
	}

}
