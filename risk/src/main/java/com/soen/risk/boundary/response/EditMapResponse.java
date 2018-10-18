package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.entity.Continent;
import com.soen.risk.entity.Country;

import java.util.LinkedList;
import java.util.List;

/**
 * The Class EditMapResponse.
 */
public class EditMapResponse implements Response {
    
    /** The name. */
    private String name;
    
    /** The number of continent. */
    private String numberOfContinent;
    
    /** The number of country. */
    private String numberOfCountry;
    
    /** The continent names. */
    private String continentNames;
    
    /** The control values. */
    private String controlValues;
    
    /** The country names. */
    private String countryNames;
    
    /** The continent of countries. */
    private String continentOfCountries;
    
    /** The connection string. */
    private String connectionString;

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
     * Gets the number of continent.
     *
     * @return the number of continent
     */
    public String getNumberOfContinent() {
        return numberOfContinent;
    }

    /**
     * Sets the number of continent.
     *
     * @param numberOfContinent the new number of continent
     */
    public void setNumberOfContinent(int numberOfContinent) {
        this.numberOfContinent = String.valueOf(numberOfContinent);
    }

    /**
     * Gets the number of country.
     *
     * @return the number of country
     */
    public String getNumberOfCountry() {
        return numberOfCountry;
    }

    /**
     * Sets the number of country.
     *
     * @param numberOfCountry the new number of country
     */
    public void setNumberOfCountry(int numberOfCountry) {
        this.numberOfCountry = String.valueOf(numberOfCountry);
    }

    /**
     * Gets the continent names.
     *
     * @return the continent names
     */
    public String getContinentNames() {
        return continentNames;
    }

    /**
     * Sets the continent names.
     *
     * @param continents the new continent names
     */
    public void setContinentNames(List<Continent> continents) {
        StringBuilder continentNameBuilder = new StringBuilder();
        for (Continent continent : continents) {
            continentNameBuilder.append(continent.getName());
            continentNameBuilder.append(",");
        }
        this.continentNames = continentNameBuilder.toString();
    }

    /**
     * Gets the control values.
     *
     * @return the control values
     */
    public String getControlValues() {
        return controlValues;
    }

    /**
     * Sets the control values.
     *
     * @param continents the new control values
     */
    public void setControlValues(List<Continent> continents) {
        StringBuilder controlValueBuilder = new StringBuilder();
        for (Continent continent : continents) {
            controlValueBuilder.append(continent.getControlValue());
            controlValueBuilder.append(",");
        }
        this.controlValues = controlValueBuilder.toString();
    }

    /**
     * Gets the country names.
     *
     * @return the country names
     */
    public String getCountryNames() {
        return countryNames;
    }

    /**
     * Sets the country names.
     *
     * @param countries the new country names
     */
    public void setCountryNames(List<Country> countries) {
        StringBuilder names = new StringBuilder();
        for (Country country : countries) {
            names.append(country.getName());
            names.append(",");
        }
        this.countryNames = names.toString();
    }

    /**
     * Gets the continent of countries.
     *
     * @return the continent of countries
     */
    public String getContinentOfCountries() {
        return continentOfCountries;
    }

    /**
     * Sets the continent of countries.
     *
     * @param countries the countries
     * @param continents the continents
     */
    public void setContinentOfCountries(List<Country> countries, List<Continent> continents) {
        StringBuilder continentOfCountriesBuilder = new StringBuilder();
        for (Country country : countries) {
            for (Continent continent : continents) {
                if (continent.getCountries().contains(country)) {
                    continentOfCountriesBuilder.append(continent.getName());
                    continentOfCountriesBuilder.append(",");
                }
            }
        }
        this.continentOfCountries = continentOfCountriesBuilder.toString();
    }

    /**
     * Gets the connection string.
     *
     * @return the connection string
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * Sets the connection string.
     *
     * @param connections the new connection string
     */
    public void setConnectionString(LinkedList<LinkedList<Country>> connections) {
        StringBuilder connectionsBuilder = new StringBuilder();
        for (LinkedList<Country> connection : connections) {
            for (Country country : connection) {
                connectionsBuilder.append(country.getName());
                connectionsBuilder.append(",");
            }
            connectionsBuilder.append("|");
        }
        this.connectionString = connectionsBuilder.toString();
    }
}
