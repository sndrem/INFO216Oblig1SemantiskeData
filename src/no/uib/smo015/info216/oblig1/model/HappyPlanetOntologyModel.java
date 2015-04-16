package no.uib.smo015.info216.oblig1.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

public class HappyPlanetOntologyModel {
	
	
	private OntModel owlModel;
	private Dataset dataset;
	
	public HappyPlanetOntologyModel() {
		setOwlModel(ModelFactory.createOntologyModel());
		File datasetFile = new File("hpiDataset");
		if(datasetFile.listFiles().length > 0) {
			System.out.println("Dataset already populated.");
		} else {
			dataset = TDBFactory.createDataset("hpiDataset/");
		}
	}
	
	public HappyPlanetOntologyModel(OntModel owlModel, Dataset dataset) {
		this.setOwlModel(ModelFactory.createOntologyModel());
		File datasetFile = new File("hpiDataset");
		if(datasetFile.listFiles().length > 0) {
			System.out.println("Dataset already populated.");
		} else {
			System.out.println("Creating new dataset...");
			this.dataset = dataset;
		}
		
	}
	
	/**
	 * Method to write the model to TURTLE-notation
	 * @param fileName The name of the file you want to save
	 */
	public void saveFile(String fileName){
		FileOutputStream output = null;
		
		try {
			output = new FileOutputStream(new File(fileName));
			owlModel.write(output, "TURTLE");
			System.out.println(fileName + " was succesfully written to file");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to map the specified long urls to shorter prefixes
	 * @param prefixMap the map of the prefixes
	 */
	public void prefixMapping(Map<String, String> prefixMap){
		for(String prefix : prefixMap.keySet()){
			owlModel.setNsPrefix(prefix, prefixMap.get(prefix));
		}
	}
	
	public void printModel(){
		getOwlModel().write(System.out, "TURTLE");
	}

	/**
	 * @return the owlModel
	 */
	public OntModel getOwlModel() {
		return owlModel;
	}

	/**
	 * @param owlModel the owlModel to set
	 */
	public void setOwlModel(OntModel owlModel) {
		this.owlModel = owlModel;
	}
	
	


}
