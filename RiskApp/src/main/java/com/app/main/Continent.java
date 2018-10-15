/**
 * 
 */
package com.app.main;

import java.util.List;

/**
 * @author fly
 *
 */
public class Continent 
{
	private String continentName;
	private int controlValue;
	private List<Country> countries;
	
	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	
	
	/**
	 * @return the continentName
	 */
	public String getContinentName() {
		return continentName;
	}
	public Continent(int controlValue,String continentName) {
		
		this.continentName = continentName;
		this.controlValue = controlValue;
	}
	
	public Continent()
	{
		
	}
	/**
	 * @param continentName the continentName to set
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}
	/**
	 * @return the controlValue
	 */
	public int getControlValue() {
		return controlValue;
	}
	/**
	 * @param controlValue the controlValue to set
	 */
	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}
	

	

}
