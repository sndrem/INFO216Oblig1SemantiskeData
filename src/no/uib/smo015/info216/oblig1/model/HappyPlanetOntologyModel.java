package no.uib.smo015.info216.oblig1.model;

import java.io.File;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

public class HappyPlanetOntologyModel {
	
	
	private OntModel owlModel;
	private Dataset dataset;
	
	public HappyPlanetOntologyModel() {
		owlModel = ModelFactory.createOntologyModel();
		File datasetFile = new File("hpiDataset");
		if(datasetFile.listFiles().length > 0) {
			System.out.println("Dataset already populated.");
		} else {
			dataset = TDBFactory.createDataset("hpiDataset/");
		}
		
	}


}
