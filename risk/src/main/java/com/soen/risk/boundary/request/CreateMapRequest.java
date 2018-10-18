package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;

public class CreateMapRequest implements Request {
    private String name;
    private List<String> continents;
    private List<String> countries;
    private List<Integer> controlValues;
    private List<String> countryOwners;
    private List<List<String>> connections;
    private String downloadFolder;

    public CreateMapRequest(String... args) {
        setName(args[0]);
        setContinents(args[1]);
        setControlValues(args[2]);
        setCountries(args[3]);
        setCountryOwners(args[4]);
        setConnections(args[5]);
        setDownloadFolder(args[6]);
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

    private void setContinents(String cont) {
        logger.log(Level.INFO, "continents : " + cont);
        this.continents = new ArrayList<>();
        Collections.addAll(this.continents, cont.split(","));
    }

    public List<String> getCountries() {
        return countries;
    }

    private void setCountries(String cont) {
        logger.log(Level.INFO, "countries : " + cont);
        this.countries = new ArrayList<>();
        Collections.addAll(this.countries, cont.split(","));
    }

    public List<Integer> getControlValues() {
        return controlValues;
    }

    private void setControlValues(String cont) {
        logger.log(Level.INFO, "control values : " + cont);
        this.controlValues = new ArrayList<>();
        for (String controlValue : cont.split(",")) {
            this.controlValues.add(Integer.valueOf(controlValue));
        }
    }

    public List<String> getCountryOwners() {
        return countryOwners;
    }

    private void setCountryOwners(String cont) {
        logger.log(Level.INFO, "country owners : " + cont);
        this.countryOwners = new ArrayList<>();
        this.countryOwners.addAll(Arrays.asList(cont.split(",")));
    }

    public List<List<String>> getConnections() {
        return connections;
    }

    // Format: c1,c2,c3|c4,c3|c4,c5
    private void setConnections(String cont) {
        logger.log(Level.INFO, "connections : " + cont);
        this.connections = new ArrayList<>();

        ArrayList<String> connection;
        for (String linkedCountryNames : cont.split(Pattern.quote("|"))) {
            logger.log(Level.INFO, "LinkedCountryNames:: " + linkedCountryNames);
            connection = new ArrayList<>();
            for(String neighbour: linkedCountryNames.split(",")){
                connection.add(neighbour);
            }
            connections.add(connection);
        }
    }


    public String getDownloadFolder() {
        return downloadFolder;
    }

    public void setDownloadFolder(String downloadFolder) {
        this.downloadFolder = downloadFolder;
    }
}
