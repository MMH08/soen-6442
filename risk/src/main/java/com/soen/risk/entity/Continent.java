package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Continent {

	private String name;
	private int controlValue;
	private List<Country> countries;

	// -------------------------------------------------------------

	/**
	 * @param name
	 */
	public Continent(String name) {
		this.name = name;
		this.countries = new ArrayList<>();
	}

	/**
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
