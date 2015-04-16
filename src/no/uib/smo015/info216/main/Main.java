package no.uib.smo015.info216.main;

import java.util.HashMap;
import java.util.Map;

import no.uib.smo015.info216.HappyOntology.HappyOnt;
import no.uib.smo015.info216.oblig1.csvParser.Parser;
import no.uib.smo015.info216.oblig1.model.HappyPlanetOntologyModel;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

/**
 * The main class used to run the application
 * @author Sindre Moldelev
 * @version 0.0.1
 */
public class Main {
	
	private static Map<String, String> map;

	public static void main(String[] args) {
		Parser parser = new Parser();
		OntModel owlModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);
		HappyPlanetOntologyModel hpiModel = new HappyPlanetOntologyModel(owlModel);
		parser.readFile(hpiModel.getOwlModel(), "data/Riktig_HPI_Index.csv");
		
		map = new HashMap<>();
		map.put("hpi", HappyOnt.NS);
		map.put("xsd", "http://www.w3.org/2001/XMLSchema#");
		map.put("dbp", "http://dbpedia.org/ontology/");
		map.put("rdfs", RDFS.getURI());
		map.put("rdf", RDF.getURI());
		hpiModel.prefixMapping(map);
		hpiModel.printModel();
		hpiModel.saveFile("testFil.ttl");
//		model.prefixMapping(map);
//		model.printModel();
//		System.out.println("------------");
		
//		model.saveFile("/Users/Sindre/Dropbox/info216 oblig/216/framføringsFil.ttl");
	}

}
