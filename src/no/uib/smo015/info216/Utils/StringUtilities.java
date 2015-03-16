package no.uib.smo015.info216.Utils;

public class StringUtilities {

	public static final String underscoreRemoval(String name){
		return name.replace("_", " ");
	}
	
	public static final String congoConvert(String countryName){
		if(countryName.equals("Congo. Dem. Rep. of the")){
			return "Democratic Republic of the Congo";
		}
		else{
			return countryName;
		}
		
	}
}
