package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Continent Class</h2>
 * <p>This is the Continent class that adds country names ,their control values and
   list of countries to the map.</p>
 * @author Amit Sachdeva
 * @since 2018-10-08
 * @version 1.0.0
 */
public class Continent {

	private String name;
	private int controlValue;
	private List<Country> countries;

	// -------------------------------------------------------------

	/**
	 * Continent name of the map
	 * @param name 
	 */
	public Continent(String name) {
		this.name = name;
		this.countries = new ArrayList<>();
	}

	/**
	 * Adds the specifications of country to the continent 
	 * @param country
	 */
	public void addCountry(Country country) {
		this.countries.add(country);
	}

	// -------------------------------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getControlValue() {
		return controlValue;
	}

	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

}
