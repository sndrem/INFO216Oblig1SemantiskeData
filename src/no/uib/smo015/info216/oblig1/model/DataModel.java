package no.uib.smo015.info216.oblig1.model;

<<<<<<< HEAD
import src.no.uib.smo015.info216.HappyOntology.HappyOnt;
import src.no.uib.smo015.info216.oblig1.csvParser.Parser;
=======
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import no.uib.smo015.info216.HappyOntology.HappyOnt;
import no.uib.smo015.info216.oblig1.csvParser.Parser;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.update.UpdateAction;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
>>>>>>> parent of c8c9a40... Lagt til domain og range
/**
 * A class used to represent the data model you want to parse information to.
 * @author Sindre Moldeklev
 * @version 0.0.1
 *
 */
public class DataModel {
	private Model hpiModel;
	private InfModel rdfsModel;
	private Dataset dataset;
	private Parser fileParser;
	private String prefix;
	private Property id, country, subRegion, region, lifeExpectancy, wellBeing, happyLifeYears, 
					footPrint, happyIndex, population, gdp, govRank, type, rank, countryLabel;
	
	private Map<String, String> prefixMap;
	private final String ID = "id", COUNTRY = "country", SUB_REGION = "subRegion", LIFE_EXPECTANCY = "lifeExpectancy", HAPPY_LIFE_YEARS = "happyLifeYears", 
			FOOTPRINT = "footPrint", HAPPY_INDEX = "happyIndex", POPULATION = "populationTotal", GDP = "grossDomesticProduct", GOV_RANK = "govRank", WELL_BEING = "wellBeing", RANK = "rank",
			REGION = "region";
	
	public DataModel(){
		hpiModel = ModelFactory.createDefaultModel();
		rdfsModel = ModelFactory.createRDFSModel(hpiModel);
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
		rdfsModel.write(System.out, "TURTLE");
		ValidityReport report = rdfsModel.validate();
		if(report.isValid()){
			System.out.println("Modellen er valid");
		} else System.out.println("Modellen er ikke valid");
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
		
		try {
			output = new FileOutputStream(new File(fileName));
			hpiModel.write(output, "TURTLE");
			System.out.println(fileName + " was succesfully written to file");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to create the properties for the model
	 */
	private void createProperties(){
		// TODO Legg til properties for egen klasse
//		prefix = "http://smo015.uib.no/happyPlanetIndex#";
<<<<<<< HEAD
		id = (Property) hpiModel.createProperty(HappyOnt.NS + this.ID)
			.addProperty(RDFS.domain, HappyOnt.COUNTRY)
			.addProperty(RDFS.range, XSD.xint);
		
		rank = (Property) hpiModel.createProperty(HappyOnt.NS + this.RANK)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xint);
		
		country = (Property) hpiModel.createProperty(HappyOnt.NS + this.COUNTRY)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xstring);
		
		subRegion = (Property) hpiModel.createProperty(HappyOnt.NS + this.SUB_REGION)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xstring);
		
		lifeExpectancy = (Property) hpiModel.createProperty(HappyOnt.NS + this.LIFE_EXPECTANCY)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xfloat);
		
		happyLifeYears = (Property) hpiModel.createProperty(HappyOnt.NS + this.HAPPY_LIFE_YEARS)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xfloat);
		
		footPrint = (Property) hpiModel.createProperty(HappyOnt.NS + this.FOOTPRINT)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xfloat);
		
		happyIndex = (Property) hpiModel.createProperty(HappyOnt.NS + this.HAPPY_INDEX)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xfloat);
		
		population = (Property) hpiModel.createProperty(HappyOnt.NS + this.POPULATION)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xlong);
		
		gdp = (Property) hpiModel.createProperty(HappyOnt.NS + this.GDP)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xint);
		
		govRank = (Property) hpiModel.createProperty(HappyOnt.NS + this.GOV_RANK)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xint);
		
		wellBeing = (Property) hpiModel.createProperty(HappyOnt.NS + this.WELL_BEING)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xfloat);
		
		region = (Property) hpiModel.createProperty(HappyOnt.NS + this.REGION)
				.addProperty(RDFS.domain, HappyOnt.COUNTRY)
				.addProperty(RDFS.range, XSD.xstring);
		
				
=======
		id = (Property) hpiModel.createProperty(HappyOnt.NS + this.ID).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		rank = (Property) hpiModel.createProperty(HappyOnt.NS + this.RANK).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		country = (Property) hpiModel.createProperty(HappyOnt.NS + this.COUNTRY).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		subRegion = (Property) hpiModel.createProperty(HappyOnt.NS + this.SUB_REGION).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		lifeExpectancy = (Property) hpiModel.createProperty(HappyOnt.NS + this.LIFE_EXPECTANCY).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		happyLifeYears = (Property) hpiModel.createProperty(HappyOnt.NS + this.HAPPY_LIFE_YEARS).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		footPrint = (Property) hpiModel.createProperty(HappyOnt.NS + this.FOOTPRINT).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		happyIndex = (Property) hpiModel.createProperty(HappyOnt.NS + this.HAPPY_INDEX).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		population = (Property) hpiModel.createProperty(HappyOnt.NS + this.POPULATION).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		gdp = (Property) hpiModel.createProperty(HappyOnt.NS + this.GDP).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		govRank = (Property) hpiModel.createProperty(HappyOnt.NS + this.GOV_RANK).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		wellBeing = (Property) hpiModel.createProperty(HappyOnt.NS + this.WELL_BEING).addProperty(RDFS.domain, HappyOnt.COUNTRY);
		region = (Property) hpiModel.createProperty(HappyOnt.NS + this.REGION).addProperty(RDFS.domain, HappyOnt.COUNTRY);
>>>>>>> parent of c8c9a40... Lagt til domain og range
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
	 * @return the region
	 */
	public Property getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Property region) {
		this.region = region;
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

	public Property getCountryLabel() {
		return countryLabel;
	}

	public void setCountryLabel(Property countryLabel) {
		this.countryLabel = countryLabel;
	}
	
	
	
	
}
