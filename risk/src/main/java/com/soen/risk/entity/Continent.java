package com.soen.risk.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h2>Continent Class</h2>
 * <p>This is the Continent class that adds country names ,their control values and
 *    list of countries to the map.</p>
 *
 * @author Amit Sachdeva
 * @version 1.0.2
 * @since 2018-11-01
 */
public class Continent implements Serializable {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(Continent.class.getName());
	
	/** The name. */
	private String name;
	
	/** The control value. */
	private int controlValue;
	
	/** The countries. */
	private List<Country> countries;

	// -------------------------------------------------------------

	/**
	 * Continent name of the map.
	 *
	 * @param name the name
	 */
	public Continent(String name) {
		this.name = name;
		this.countries = new ArrayList<>();
	}

	/**
	 * Adds the specifications of country to the continent .
	 *
	 * @param country the country
	 */
	public void addCountry(Country country) {
		this.countries.add(country);
	}

	// -------------------------------------------------------------

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the control value.
	 *
	 * @return the control value
	 */
	public int getControlValue() {
		return controlValue;
	}

	/**
	 * Sets the control value.
	 *
	 * @param controlValue the new control value
	 */
	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}

	/**
	 * Gets the countries.
	 *
	 * @return the countries
	 */
	public List<Country> getCountries() {
		return countries;
	}

	/**
	 * Sets the countries.
	 *
	 * @param countries the new countries
	 */
	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

}
