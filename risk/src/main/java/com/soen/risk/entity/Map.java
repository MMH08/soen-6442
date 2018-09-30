package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;

public class Map {
	private final int MAX_COUNTRIES = 26;
	private String name;
	
	private List<Continent> continents;
	
	private List<Country> countries;
	private int adjCountry[][];
	
	// -------------------------------------------------------------
	
	public Map(String name) {
		this.setName(name);
		this.continents = new ArrayList<>();
		this.countries = new ArrayList<>();
		this.adjCountry = new int[MAX_COUNTRIES][MAX_COUNTRIES];
	}
	
	public void addContinent(Continent continent) {
		continents.add(continent);
	}
	
	public void connect(Country country1, Country country2) {
		adjCountry[country1.getId()][country2.getId()] = 1;
		adjCountry[country2.getId()][country1.getId()] = 1;
	}
	
	public void addCountry(Country country) {
		countries.add(country);
	}
	
	// -------------------------------------------------------------
	
	
	
	// -------------------------------------------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public int[][] getAdjCountry() {
		return adjCountry;
	}

	public void setAdjCountry(int[][] adjCountry) {
		this.adjCountry = adjCountry;
	}

	public int getMAX_COUNTRIES() {
		return MAX_COUNTRIES;
	}

	// -------------------------------------------------------------

}
