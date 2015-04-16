package no.uib.smo015.info216.oblig1.csvParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;

import no.uib.smo015.info216.HappyOntology.HappyOnt;
import no.uib.smo015.info216.Utils.StringUtilities;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.vocabulary.RDF;
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
	public OntModel readFile(OntClass ontClass, OntModel owlModel, String fileToRead) {
		reader = null;
		String line = "";
		String split = ";";

//		Model tempModel = data.getHpiModel();

		try {
			File fileToParse = new File(fileToRead);
			reader = new BufferedReader(new FileReader(fileToParse));
			System.out.println("Loaded: " + fileToParse.getName());
			System.out.println("-----------------------------");
			int index = 0;

			while ((line = reader.readLine()) != null) {
				String[] props = line.split(split);
				// Valgt 1 fordi det er kolonne 2 i csv-filen som inneholder countryNames
				String countryName = compact(props[1]);
				
				//Opprett datatype properties
				NumberFormat nf = NumberFormat.getInstance();
				Individual countryIndividual = ontClass.createIndividual(HappyOnt.NS + countryName);
					
				DatatypeProperty rankProp = owlModel.createDatatypeProperty(HappyOnt.NS + "rank");
								rankProp.addDomain(ontClass);
								rankProp.addRange(XSD.integer);
				
				DatatypeProperty subRegionProp = owlModel.createDatatypeProperty(HappyOnt.NS + "subRegion");
								subRegionProp.addDomain(ontClass);
								subRegionProp.addRange(XSD.xstring);
								
				DatatypeProperty lifeExpectancyProp = owlModel.createDatatypeProperty(HappyOnt.NS + "lifeExpectancy");
								lifeExpectancyProp.addDomain(ontClass);
								lifeExpectancyProp.addRange(XSD.xfloat);
				
				DatatypeProperty wellBeingProp = owlModel.createDatatypeProperty(HappyOnt.NS + "wellBeing");
								wellBeingProp.addDomain(ontClass);
								wellBeingProp.addRange(XSD.xfloat);
				
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
								
				DatatypeProperty gdpProp = owlModel.createDatatypeProperty(HappyOnt.NS + "gdp");
								gdpProp.addDomain(ontClass);
								gdpProp.addRange(XSD.integer);
								
				DatatypeProperty regionProp = owlModel.createDatatypeProperty(HappyOnt.NS + "region");
								regionProp.addDomain(ontClass);
								regionProp.addRange(XSD.xstring);
				
				DatatypeProperty descriptionProp = owlModel.createDatatypeProperty(HappyOnt.NS + "description");
								descriptionProp.addDomain(ontClass);
								descriptionProp.addRange(XSD.xstring);
								
				
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
								
				
				
				
				
		
				
				
				
				
				
				
				
				
						tempModel.createResource(HappyOnt.NS + countryName);
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
						//Legg til nye egenskaper
						if(props[10].equals("n/a")){
							res.addLiteral(data.getGovRank(), props[10]);
						} else {
							res.addLiteral(data.getGovRank(), new Integer(props[10]));
						}

				//tempModel.add(res, RDF.type, "country");		
						
				index++;

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

		return tempModel;
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
