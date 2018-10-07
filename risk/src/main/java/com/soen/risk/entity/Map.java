package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author :
 *
 */
public class Map {
	private String name;
	
	private List<Continent> continents;
	
	private List<Country> countries;
	private  LinkedList<LinkedList> adjCountry;

    /**
     *
     */
	public Map() {
		this.continents = new ArrayList<>();
		this.countries = new ArrayList<>();
		this.adjCountry = new LinkedList<>();
	}

    /**
     * @param continent
     */
	public void addContinent(Continent continent) {
		continents.add(continent);
	}

    /**
     * @param country1
     * @param country2
     */
	public void connect(Country country1, Country country2) {
	}

	// -------------------------------------------------------------

    /**
     *
     * @param filename
     *
     * @since 2018-10-06
     * @author Amit Sachdev
     */
	public void load(String filename) {

	}

	/**
     *
     *
     * @since 2018-10-06
     * @author Manmeet Singh
     */
	public void save() {
	}

    /**
     * @return
     *
     * @author Nivetha
     * @since 2018-10-06
     */
	public boolean isValid(){
	    return false;
    }
	
	
	// -------------------------------------------------------------

    /**
     * @param country
     */
    private void addCountry(Country country) {
        countries.add(country);
    }

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

	public LinkedList getAdjCountry() {
		return adjCountry;
	}

	public void setAdjCountry(LinkedList adjCountry) {
		this.adjCountry = adjCountry;
	}


	// -------------------------------------------------------------

}
