package no.uib.smo015.info216.main;

import java.util.HashMap;
import java.util.Map;

import no.uib.smo015.info216.oblig1.model.DataModel;

/**
 * @author Sindre
 * @version 0.0.1
 * The main class used to run the application
 */
public class Main {
	
	private static Map<String, String> map;

	public static void main(String[] args) {

		DataModel model = new DataModel();
		
		map = new HashMap<>();
		
		map.put("hpi", "http://smo015.uib.no/happyPlanetIndex#");
		map.put("xsd", "http://www.w3.org/2001/XMLSchema#");
		map.put("dbp", "http://dbpedia.org/ontology/");

		model.prefixMapping(map);
		
//		model.saveFile("/Users/Sindre/Dropbox/info216 oblig/216/framføringsFil.ttl");
		
		model.printModel();
	}

}
