package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.entity.Continent;
import com.soen.risk.entity.Country;

import java.util.LinkedList;
import java.util.List;

public class EditMapResponse implements Response {
    private String name;
    private String numberOfContinent;
    private String numberOfCountry;
    private String continentNames;
    private String controlValues;
    private String countryNames;
    private String continentOfCountries;
    private String connectionString;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfContinent() {
        return numberOfContinent;
    }

    public void setNumberOfContinent(int numberOfContinent) {
        this.numberOfContinent = String.valueOf(numberOfContinent);
    }

    public String getNumberOfCountry() {
        return numberOfCountry;
    }

    public void setNumberOfCountry(int numberOfCountry) {
        this.numberOfCountry = String.valueOf(numberOfCountry);
    }

    public String getContinentNames() {
        return continentNames;
    }

    public void setContinentNames(List<Continent> continents) {
        StringBuilder continentNameBuilder = new StringBuilder();
        for (Continent continent : continents) {
            continentNameBuilder.append(continent.getName());
            continentNameBuilder.append(",");
        }
        this.continentNames = continentNameBuilder.toString();
    }

    public String getControlValues() {
        return controlValues;
    }

    public void setControlValues(List<Continent> continents) {
        StringBuilder controlValueBuilder = new StringBuilder();
        for (Continent continent : continents) {
            controlValueBuilder.append(continent.getControlValue());
            controlValueBuilder.append(",");
        }
        this.controlValues = controlValueBuilder.toString();
    }

    public String getCountryNames() {
        return countryNames;
    }

    public void setCountryNames(List<Country> countries) {
        StringBuilder names = new StringBuilder();
        for (Country country : countries) {
            names.append(country.getName());
            names.append(",");
        }
        this.countryNames = names.toString();
    }

    public String getContinentOfCountries() {
        return continentOfCountries;
    }

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

    public String getConnectionString() {
        return connectionString;
    }

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
