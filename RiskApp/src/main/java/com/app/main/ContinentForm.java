/**
 * 
 */
package com.app.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author fly
 *
 */
public class ContinentForm 
{
 private List<Continent> continents;
 private List<Country> countryList;
 private List<String> countryNames;
 private LinkedHashMap<String,String> listOfCountries;
 private LinkedHashMap<String,String> adjCountries;


/**
 * @return the listOfCountries
 */
public LinkedHashMap<String, String> getListOfCountries() {
	return listOfCountries;
}


/**
 * @param listOfCountries the listOfCountries to set
 */
public void setListOfCountries(LinkedHashMap<String, String> listOfCountries) {
	this.listOfCountries = listOfCountries;
}


/**
 * @return the countryNames
 */
public List<String> getCountryNames() {
	return countryNames;
}


/**
 * @param countryNames the countryNames to set
 */
public void setCountryNames(List<String> countryNames) {
	this.countryNames = countryNames;
}


/**
 * @param countries the countries to set
 */



 

	/**
 * @return the countryList
 */
public List<Country> getCountryList() {
	return countryList;
}


/**
 * @param countryList the countryList to set
 */
public void setCountryList(List<Country> countryList) {
	this.countryList = countryList;
}





public ContinentForm() {
	
	// TODO Auto-generated constructor stub
}

	public List<Continent> getContinents() {
		return continents;
	}

	public void setContinents(List<Continent> continents) {
		this.continents = continents;
	}


	/**
	 * @return the adjCountries
	 */
	public LinkedHashMap<String, String> getAdjCountries() {
		return adjCountries;
	}


	/**
	 * @param adjCountries the adjCountries to set
	 */
	public void setAdjCountries(LinkedHashMap<String, String> adjCountries) {
		this.adjCountries = adjCountries;
	}


	
 
 
 
}
