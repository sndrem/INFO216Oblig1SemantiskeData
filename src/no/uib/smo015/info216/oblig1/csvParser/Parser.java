package no.uib.smo015.info216.oblig1.csvParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import no.uib.smo015.info216.oblig1.model.DataModel;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

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
	public Model readFile(DataModel data, String fileToRead) {
		reader = null;
		String line = "";
		String split = ";";

		Model tempModel = data.getHpiModel();

		try {
			File fileToParse = new File(fileToRead);
			String dbpedia = "http://dbpedia.org/ontology/";
			reader = new BufferedReader(new FileReader(fileToParse));
			System.out.println("Loaded: " + fileToParse.getName());
			System.out.println("-----------------------------");
			int index = 0;

			while ((line = reader.readLine()) != null) {
				String[] props = line.split(split);
				String countryName = compact(props[0]);
				
				// System.out.println( "Index " + index + ": [Country] " +
				// props[0] + " [Sub region] " + props[1] +
				// " [Life Expectancy] " + props[2] + " [Well Being] " +
				// props[3] +
				// " [Happy Life Years] " + props[4] + " [Footprint] " +
				// props[5] + " [HPI] " + props[6] + " [Populations] " +
				// props[7] +
				// " [GDP/Capital] " + props[8] + " [Governance rank] " +
				// props[9]);
				//
				Resource res = tempModel.createResource(data.getPrefix() + countryName);
				
						res.addProperty(data.getId(),tempModel.createTypedLiteral(index))
						.addLiteral(data.getRank(), new Integer(props[0]))
						.addLiteral(data.getSubRegion(), props[2])
						.addLiteral(data.getLifeExpectancy(), new Float(props[3]))
						.addLiteral(data.getWellBeing(), new Float(props[4]))
						.addLiteral(data.getHappyLifeYears(), new Float(props[5]))
						.addLiteral(data.getFootPrint(), new Float(props[6]))
						.addLiteral(data.getHappyIndex(), new Float(props[7]))
						.addLiteral(data.getPopulation(), new Integer(props[8]))
						.addLiteral(data.getGdp(), new Integer(props[9]));
						if(props[10].equals("n/a")){
							res.addLiteral(data.getGovRank(), props[10]);
						} else {
							res.addLiteral(data.getGovRank(), new Integer(props[10]));
						}

				tempModel.add(res, RDF.type, dbpedia + "country")
						.addLiteral(data.getSubRegion(), props[1])
						.addLiteral(data.getLifeExpectancy(), props[2])
						.addLiteral(data.getWellBeing(), props[3])
						.addLiteral(data.getHappyLifeYears(), props[4])
						.addLiteral(data.getFootPrint(), props[5])
						.addLiteral(data.getHappyIndex(), props[6])
						.addLiteral(data.getPopulation(), props[7])
						.addLiteral(data.getGdp(), props[8])
						.addLiteral(data.getGovRank(), props[9]);

				tempModel.add(res, RDF.type, "country");		
						
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
