package no.uib.smo015.info216.queryPackage;

import no.uib.smo015.info216.oblig1.model.DataModel;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

/**
 * Class for executing sparql queries
 * 
 * @author sindremoldeklev
 * @version 0.0.1
 */

public class QueryBuilder {
	private DataModel data;
	private Model myModel;
	
	private final String QUERY_STRING = "PREFIX hpi: <http://smo015.uib.no/happyPlanetIndex#>" +
										"PREFIX xml: <http://www.w3.org/2001/XMLSchema#>" +
										"select ?country ?wellBeing" +
										"where {?country hpi:wellBeing ?wellBeing . } " +
										"order by desc (?wellBeing)";
													
	/**
	 * Constructor for the class QueryBuilder
	 * The constructor initializes the datamodel and retrieves the model which it will execute queries on
	 */
	public QueryBuilder() {
		data = new DataModel();
		myModel = data.getHpiModel();
	}
	
	/**
	 * Method to exequte a query
	 * @deprecated
	 */
	@Deprecated
	public void executeQuery(String queryString){
		Query query = QueryFactory.create(queryString);
		try (QueryExecution exe = QueryExecutionFactory.create(query, myModel)) {
			ResultSet results = exe.execSelect();
			for (; results.hasNext() ; ){
				{
					
				}
			}
		}
				
		
				
	}
	
	
	
	
	
	
	

}
