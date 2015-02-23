package no.uib.smo015.info216.oblig1.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import no.uib.smo015.info216.oblig1.csvParser.Parser;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.tdb.TDBFactory;
/**
 * A class used to represent the data model you want to parse information to.
 * @author Sindre Moldeklev
 * @version 0.0.1
 *
 */
public class DataModel {
	private Model hpiModel;
	private Dataset dataset;
	private Parser fileParser;
	private String prefix;
	private Property id, country, subRegion, lifeExpectancy, wellBeing, happyLifeYears, footPrint, happyIndex, population, gdp, govRank, type, rank;
	
	private Map<String, String> prefixMap;
	private final String ID = "id", COUNTRY = "country", SUB_REGION = "subRegion", LIFE_EXPECTANCY = "lifeExpectancy", HAPPY_LIFE_YEARS = "happyLifeYears", 
			FOOTPRINT = "footPrint", HAPPY_INDEX = "happyIndex", POPULATION = "populationTotal", GDP = "grossDomesticProduct", GOV_RANK = "govRank", WELL_BEING = "wellBeing", RANK = "rank";
	
	public DataModel(){
		hpiModel = ModelFactory.createDefaultModel();
		dataset = TDBFactory.createDataset("hpiDataset/");
		createProperties();
		populateModel();
		dataset.close();
	}
	
	/**
	 * Method to populate the model using the parser
	 */
	public void populateModel(){
		fileParser = new Parser();
		
		fileParser.readFile(this, "data/Riktig_HPI_Index.csv");
	}
	
	/**
	 * Method to print a model to the console in turle notation
	 */
	public void printModel(){
		hpiModel.write(System.out, "TURTLE");
	}
	
	/**
	 * Method to map the specified long urls to shorter prefixes
	 * @param prefixMap the map of the prefixes
	 */
	public void prefixMapping(Map<String, String> prefixMap){
		for(String prefix : prefixMap.keySet()){
			hpiModel.setNsPrefix(prefix, prefixMap.get(prefix));
		}
	}
	
	
	/**
	 * Method to write the model to TURTLE-notation
	 * @param fileName The name of the file you want to save
	 */
	public void saveFile(String fileName){
		FileOutputStream output = null;
		File file = new File(fileName);
		
		try {
			output = new FileOutputStream(file);
			hpiModel.write(output, "TURTLE");
			System.out.println(file.getName() + " was succesfully written to file");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to create the properties for the model
	 */
	private void createProperties(){
		prefix = "http://smo015.uib.no/happyPlanetIndex#";
		String dbpedia = "http://dbpedia.org/ontology/";
		id = hpiModel.createProperty(prefix + this.ID);
		rank = hpiModel.createProperty(prefix + this.RANK);
		country = hpiModel.createProperty(prefix + this.COUNTRY);
		subRegion = hpiModel.createProperty(prefix + this.SUB_REGION);
		lifeExpectancy = hpiModel.createProperty(dbpedia + this.LIFE_EXPECTANCY);
		happyLifeYears = hpiModel.createProperty(prefix + this.HAPPY_LIFE_YEARS);
		footPrint = hpiModel.createProperty(prefix + this.FOOTPRINT);
		happyIndex = hpiModel.createProperty(prefix + this.HAPPY_INDEX);
		population = hpiModel.createProperty(dbpedia + this.POPULATION);
		gdp = hpiModel.createProperty(dbpedia + this.GDP);
		govRank = hpiModel.createProperty(prefix + this.GOV_RANK);
		wellBeing = hpiModel.createProperty(prefix + this.WELL_BEING);
	}

	
	/**
	 * @return the wellBeing
	 */
	public Property getWellBeing() {
		return wellBeing;
	}

	/**
	 * @param wellBeing the wellBeing to set
	 */
	public void setWellBeing(Property wellBeing) {
		this.wellBeing = wellBeing;
	}

	/**
	 * @return the hpiModel
	 */
	public Model getHpiModel() {
		return hpiModel;
	}

	/**
	 * @param hpiModel the hpiModel to set
	 */
	public void setHpiModel(Model hpiModel) {
		this.hpiModel = hpiModel;
	}

	/**
	 * @return the dataset
	 */
	public Dataset getDataset() {
		return dataset;
	}

	/**
	 * @param dataset the dataset to set
	 */
	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	/**
	 * @return the fileParser
	 */
	public Parser getFileParser() {
		return fileParser;
	}

	/**
	 * @param fileParser the fileParser to set
	 */
	public void setFileParser(Parser fileParser) {
		this.fileParser = fileParser;
	}

	

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the id
	 */
	public Property getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Property id) {
		this.id = id;
	}

	/**
	 * @return the country
	 */
	public Property getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(Property country) {
		this.country = country;
	}

	/**
	 * @return the subRegion
	 */
	public Property getSubRegion() {
		return subRegion;
	}

	/**
	 * @param subRegion the subRegion to set
	 */
	public void setSubRegion(Property subRegion) {
		this.subRegion = subRegion;
	}

	/**
	 * @return the lifeExpectancy
	 */
	public Property getLifeExpectancy() {
		return lifeExpectancy;
	}

	/**
	 * @param lifeExpectancy the lifeExpectancy to set
	 */
	public void setLifeExpectancy(Property lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	/**
	 * @return the happyLifeYears
	 */
	public Property getHappyLifeYears() {
		return happyLifeYears;
	}

	/**
	 * @param happyLifeYears the happyLifeYears to set
	 */
	public void setHappyLifeYears(Property happyLifeYears) {
		this.happyLifeYears = happyLifeYears;
	}

	/**
	 * @return the footPrint
	 */
	public Property getFootPrint() {
		return footPrint;
	}

	/**
	 * @param footPrint the footPrint to set
	 */
	public void setFootPrint(Property footPrint) {
		this.footPrint = footPrint;
	}

	/**
	 * @return the happyIndex
	 */
	public Property getHappyIndex() {
		return happyIndex;
	}

	/**
	 * @param happyIndex the happyIndex to set
	 */
	public void setHappyIndex(Property happyIndex) {
		this.happyIndex = happyIndex;
	}

	/**
	 * @return the population
	 */
	public Property getPopulation() {
		return population;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(Property population) {
		this.population = population;
	}

	/**
	 * @return the gdp
	 */
	public Property getGdp() {
		return gdp;
	}

	/**
	 * @param gdp the gdp to set
	 */
	public void setGdp(Property gdp) {
		this.gdp = gdp;
	}

	/**
	 * @return the govRank
	 */
	public Property getGovRank() {
		return govRank;
	}

	/**
	 * @param govRank the govRank to set
	 */
	public void setGovRank(Property govRank) {
		this.govRank = govRank;
	}

	/**
	 * @return the prefixMap
	 */
	public Map<String, String> getPrefixMap() {
		return prefixMap;
	}

	/**
	 * @param prefixMap the prefixMap to set
	 */
	public void setPrefixMap(Map<String, String> prefixMap) {
		this.prefixMap = prefixMap;
	}

	/**
	 * @return the type
	 */
	public Property getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Property type) {
		this.type = type;
	}

	/**
	 * @return the rank
	 */
	public Property getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(Property rank) {
		this.rank = rank;
	}
	
	
	
	
}
