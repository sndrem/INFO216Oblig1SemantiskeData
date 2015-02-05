package no.uib.smo015.info216.main;

import java.util.HashMap;
import java.util.Map;

import no.uib.smo015.info216.oblig1.model.DataModel;

public class Main {
	
	private static Map<String, String> map;

	public static void main(String[] args) {

		DataModel model = new DataModel();
		
		map = new HashMap<>();
		
		map.put("hpi", "http://smo015.uib.no/happyPlanetIndex#");
		map.put("xml", "http://www.w3.org/2001/XMLSchema#");

		model.prefixMapping(map);
		
//		model.saveFile("testHappyIndex.ttl");
		
		model.printModel();
		
		
		
		
		
	}

}
