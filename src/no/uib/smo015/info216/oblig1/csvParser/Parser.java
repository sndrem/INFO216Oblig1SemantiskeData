package no.uib.smo015.info216.oblig1.csvParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;

import no.uib.smo015.info216.Dbpedia.Dbpedia;
import no.uib.smo015.info216.HappyOntology.HappyOnt;
import no.uib.smo015.info216.Utils.StringUtilities;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.XSD;

/**
 * 
 *  A class used to parse a csv file and create triples
 * @author Sindre Moldeklev
 * @version 0.0.1
 */

public class Parser {
	private BufferedReader reader;

	public Parser() {

	}

	/**
	 * Method to read a file and parse the csv
	 * 
	 * @param fileToRead
	 *            The csv.file you would read
	 * @param data The data model you want to work with
	 * @return a Model object for Jena to work with
	 * 
	 */
	public OntModel readFile(OntModel owlModel, String fileToRead) {
		OntClass ontClass = owlModel.createClass(HappyOnt.NS + "Country");
		reader = null;
		String line = "";
		String split = ";";

//		Model tempModel = data.getHpiModel();

		try {
			File fileToParse = new File(fileToRead);
			reader = new BufferedReader(new FileReader(fileToParse));
			System.out.println("Loaded: " + fileToParse.getName());
			System.out.println("-----------------------------");

			while ((line = reader.readLine()) != null) {
				String[] props = line.split(split);
				// Valgt 1 fordi det er kolonne 2 i csv-filen som inneholder countryNames
				String countryName = compact(props[1]);
				
				//Opprett datatype properties
				NumberFormat nf = NumberFormat.getInstance();
				Individual countryIndividual = ontClass.createIndividual(HappyOnt.NS + countryName);
							countryIndividual.addSameAs(Dbpedia.COUNTRY);
					
				DatatypeProperty rankProp = owlModel.createDatatypeProperty(HappyOnt.NS + "rank");
								rankProp.addDomain(ontClass);
								rankProp.addRange(XSD.integer);
				
				DatatypeProperty subRegionProp = owlModel.createDatatypeProperty(HappyOnt.NS + "subRegion");
								subRegionProp.addDomain(ontClass);
								subRegionProp.addRange(XSD.xstring);
								subRegionProp.addSameAs(Dbpedia.SUB_REGION);
								
				DatatypeProperty lifeExpectancyProp = owlModel.createDatatypeProperty(HappyOnt.NS + "lifeExpectancy");
								lifeExpectancyProp.addDomain(ontClass);
								lifeExpectancyProp.addRange(XSD.xfloat);
								lifeExpectancyProp.addSameAs(Dbpedia.LIFE_EXPECTANCY);
				
				DatatypeProperty wellBeingProp = owlModel.createDatatypeProperty(HappyOnt.NS + "wellBeing");
								wellBeingProp.addDomain(ontClass);
								wellBeingProp.addRange(XSD.xfloat);
								wellBeingProp.addSameAs(Dbpedia.WELL_BEING);
				
				DatatypeProperty happyLifeYearsProp = owlModel.createDatatypeProperty(HappyOnt.NS + "happyLifeYears");
								happyLifeYearsProp.addDomain(ontClass);
								happyLifeYearsProp.addRange(XSD.xfloat);
								
				DatatypeProperty footPrintProp = owlModel.createDatatypeProperty(HappyOnt.NS + "footPrint");
								footPrintProp.addDomain(ontClass);
								footPrintProp.addRange(XSD.xfloat);
				
				DatatypeProperty happyIndexProp = owlModel.createDatatypeProperty(HappyOnt.NS + "happyIndex");
								happyIndexProp.addDomain(ontClass);
								happyIndexProp.addRange(XSD.xfloat);
								
				DatatypeProperty populationProp = owlModel.createDatatypeProperty(HappyOnt.NS + "population");
								populationProp.addDomain(ontClass);
								populationProp.addRange(XSD.integer);
								populationProp.addSameAs(Dbpedia.POPULATION);
								
				DatatypeProperty gdpProp = owlModel.createDatatypeProperty(HappyOnt.NS + "gdp");
								gdpProp.addDomain(ontClass);
								gdpProp.addRange(XSD.integer);
								gdpProp.addSameAs(Dbpedia.GDP);
								
				DatatypeProperty regionProp = owlModel.createDatatypeProperty(HappyOnt.NS + "region");
								regionProp.addDomain(ontClass);
								regionProp.addRange(XSD.xstring);
								regionProp.addSameAs(Dbpedia.REGION);
				
				DatatypeProperty descriptionProp = owlModel.createDatatypeProperty(HappyOnt.NS + "description");
								descriptionProp.addDomain(ontClass);
								descriptionProp.addRange(XSD.xstring);
								descriptionProp.addSameAs(Dbpedia.ABSTRACT);
								
				DatatypeProperty govRankProp = owlModel.createDatatypeProperty(HappyOnt.NS + "govRank");
								govRankProp.addDomain(ontClass);
								govRankProp.addRange(XSD.integer);
								
				DatatypeProperty labelprop = owlModel.createDatatypeProperty(RDFS.label.getURI());
								
								
				// Opprett literaler
				Literal rankLit = owlModel.createTypedLiteral(props[0]);
				Literal subRegionLit = owlModel.createTypedLiteral(props[2]);
				Literal lifeExpectLit = owlModel.createTypedLiteral(props[3]);
				Literal wellBeingLit = owlModel.createTypedLiteral(props[4]);
				Literal happyLifeYearLit = owlModel.createTypedLiteral(props[5]);
				Literal footPrintLit = owlModel.createTypedLiteral(props[6]);
				Literal happyIndexLit = owlModel.createTypedLiteral(props[7]);
				Literal populationLit = owlModel.createTypedLiteral(props[8]);
				Literal gdpLit = owlModel.createTypedLiteral(props[9]);
				Literal regionLit = owlModel.createTypedLiteral(computeRegion(props[2]));
				Literal descriptionLit = owlModel.createTypedLiteral(StringUtilities.congoConvert(StringUtilities.underscoreRemoval(countryName)) + " is a country in the region of the " + 
															StringUtilities.underscoreRemoval(computeRegion(props[2])) + "." + "\nThe population is ca. " + nf.format(new Integer(props[8])) + 
						                                    " and the Happy Index was measured to " + props[7] + " in 2012.\n" +
						                                    "The well being is " + props[4] + " and they have a measured\n" +
						                                    "happy years of " + props[5] + ". They are ranked as number " + 
						                                    props[0] + " of all the countries.\nThis is based " +
						                                    "on their geologial footprint which is " + props[6] + "\nand their " +
						                                    "well being of " + props[4]);
				
				Literal labelLit = owlModel.createTypedLiteral(StringUtilities.congoConvert(StringUtilities.underscoreRemoval(countryName)));
				Literal govRankLit = null;
				
				//Noen av dataene har ikke en verdi for governance rank. Dersom de har N/A setter vi verdien til -1.
				if(props[10].equals("n/a")){
					govRankLit = owlModel.createTypedLiteral(-1);
				} else {
					govRankLit = owlModel.createTypedLiteral(props[10]);
				}
				
				// Legg til literalene til individene og modellen.
				
				countryIndividual.addProperty(rankProp, rankLit);
				countryIndividual.addProperty(govRankProp, govRankLit);
				countryIndividual.addProperty(descriptionProp, descriptionLit);
				countryIndividual.addProperty(regionProp, regionLit);
				countryIndividual.addProperty(gdpProp, gdpLit);
				countryIndividual.addProperty(populationProp, populationLit);
				countryIndividual.addProperty(happyIndexProp, happyIndexLit);
				countryIndividual.addProperty(footPrintProp, footPrintLit);
				countryIndividual.addProperty(happyLifeYearsProp, happyLifeYearLit);
				countryIndividual.addProperty(wellBeingProp, wellBeingLit);
				countryIndividual.addProperty(lifeExpectancyProp, lifeExpectLit);
				countryIndividual.addProperty(subRegionProp, subRegionLit);
				countryIndividual.addProperty(labelprop, labelLit);
				
				
			}

			System.out.println("Bleep bloop bling blong, I am finished parsing.\n");

		} catch (FileNotFoundException e) {
			System.out.println("Could not find the specified file");
		} catch (IOException e) {
			System.out.println("There was a problem with the input/output");
		}

		finally {
			try {
				reader.close();
			} catch (Exception e) {
				System.out
						.println("There was problems when closing the reader. Your data could be lost.");
			}
		}

		return owlModel;
	}

	private String computeRegion(String subRegion) {
		if(subRegion.substring(0, 1).equalsIgnoreCase("1")){
			return "Latin_America";
		} else if (subRegion.substring(0, 1).equalsIgnoreCase("2")){
			return "Western_World";
		} else if (subRegion.substring(0, 1).equalsIgnoreCase("3")){
			return "Middle_East&North_Africa";
		} else if (subRegion.substring(0, 1).equalsIgnoreCase("4")){
			return "Sub_Saharan_Africa";
		} else if (subRegion.substring(0, 1).equalsIgnoreCase("5")){
			return "South_Asia";
		} else if (subRegion.substring(0, 1).equalsIgnoreCase("6")){
			return "East_Asia";
		} else if (subRegion.substring(0, 1).equalsIgnoreCase("7")){
			return "Transition_States";
		}
		return null;
	}

	/**
	 * Method to "overwrite" the original name for a few countries with spaces, apostrophies between words and commas such as United Kingdom, Cote D'Ivore and 
	 * Congo, Dem. Rep. Of.
	 * @param countryName
	 * @return the new compact country name
	 */
	private String compact(String countryName) {
		return countryName.replace(" ", "_").replace("'", "_").replace(",", "_");
	}

}
