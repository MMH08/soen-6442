/**
 * 
 */
package com.app.main;

/**
 * @author fly
 *
 */
public class Continent 
{
	private String continentName;
	private int controlValue;
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
