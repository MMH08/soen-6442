/**
 * 
 */
package com.app.main;

import java.util.List;

/**
 * @author fly
 *
 */
public class ContinentForm 
{
 private List<Continent> continents;
 private List<Country> countryList;
 

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

	/**
 * 
 */
public ContinentForm() {
	
	// TODO Auto-generated constructor stub
}

	public List<Continent> getContinents() {
		return continents;
	}

	public void setContinents(List<Continent> continents) {
		this.continents = continents;
	}
 
 
 
}
