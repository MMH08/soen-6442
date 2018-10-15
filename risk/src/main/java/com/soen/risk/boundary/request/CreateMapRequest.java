package com.soen.risk.boundary.request;

import java.util.List;

public class CreateMapRequest {
    private String name;
    private List<String> continents;
    private List<String> countries;
    private List<Integer> controlValues;
    private List<String> countryOwners;
    private List<List<String>> connections;

    public CreateMapRequest(String... args) {
        setName(args[0]);
        setContinents(args[1]);
        setControlValues(args[2]);
        setCountries(args[3]);
        setCountryOwners(args[4]);
        setConnections(args[5]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContinents() {
        return continents;
    }

    public void setContinents(String continents) {
        //TODO
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        // TODO
    }

    public List<Integer> getControlValues() {
        return controlValues;
    }

    public void setControlValues(String controlValues) {
        //TODO
    }

    public List<String> getCountryOwners() {
        return countryOwners;
    }

    public void setCountryOwners(String countryOwners) {
        //TODO
    }

    public List<List<String>> getConnections() {
        return connections;
    }

    // Format: c1,c2,c3|c4,c3|c4,c5
    public void setConnections(String connections) {
        //TODO
    }
}
