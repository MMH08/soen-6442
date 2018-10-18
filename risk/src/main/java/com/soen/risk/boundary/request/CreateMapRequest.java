package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * The Class CreateMapRequest.
 */
public class CreateMapRequest implements Request {
    
    /** The name. */
    private String name;
    
    /** The continents. */
    private List<String> continents;
    
    /** The countries. */
    private List<String> countries;
    
    /** The control values. */
    private List<Integer> controlValues;
    
    /** The country owners. */
    private List<String> countryOwners;
    
    /** The connections. */
    private List<List<String>> connections;
    
    /** The download folder. */
    private String downloadFolder;

    /**
     * Instantiates a new creates the map request.
     *
     * @param args the args
     */
    public CreateMapRequest(String... args) {
        setName(args[0]);
        setContinents(args[1]);
        setControlValues(args[2]);
        setCountries(args[3]);
        setCountryOwners(args[4]);
        setConnections(args[5]);
        setDownloadFolder(args[6]);
    }

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
     * Gets the continents.
     *
     * @return the continents
     */
    public List<String> getContinents() {
        return continents;
    }

    /**
     * Sets the continents.
     *
     * @param cont the new continents
     */
    private void setContinents(String cont) {
        logger.log(Level.INFO, "continents : " + cont);
        this.continents = new ArrayList<>();
        Collections.addAll(this.continents, cont.split(","));
    }

    /**
     * Gets the countries.
     *
     * @return the countries
     */
    public List<String> getCountries() {
        return countries;
    }

    /**
     * Sets the countries.
     *
     * @param cont the new countries
     */
    private void setCountries(String cont) {
        logger.log(Level.INFO, "countries : " + cont);
        this.countries = new ArrayList<>();
        Collections.addAll(this.countries, cont.split(","));
    }

    /**
     * Gets the control values.
     *
     * @return the control values
     */
    public List<Integer> getControlValues() {
        return controlValues;
    }

    /**
     * Sets the control values.
     *
     * @param cont the new control values
     */
    private void setControlValues(String cont) {
        logger.log(Level.INFO, "control values : " + cont);
        this.controlValues = new ArrayList<>();
        for (String controlValue : cont.split(",")) {
            this.controlValues.add(Integer.valueOf(controlValue));
        }
    }

    /**
     * Gets the country owners.
     *
     * @return the country owners
     */
    public List<String> getCountryOwners() {
        return countryOwners;
    }

    /**
     * Sets the country owners.
     *
     * @param cont the new country owners
     */
    private void setCountryOwners(String cont) {
        logger.log(Level.INFO, "country owners : " + cont);
        this.countryOwners = new ArrayList<>();
        this.countryOwners.addAll(Arrays.asList(cont.split(",")));
    }

    /**
     * Gets the connections.
     *
     * @return the connections
     */
    public List<List<String>> getConnections() {
        return connections;
    }

    /**
     * Sets the connections.
     *
     * @param cont the new connections
     */
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


    /**
     * Gets the download folder.
     *
     * @return the download folder
     */
    public String getDownloadFolder() {
        return downloadFolder;
    }

    /**
     * Sets the download folder.
     *
     * @param downloadFolder the new download folder
     */
    public void setDownloadFolder(String downloadFolder) {
        this.downloadFolder = downloadFolder;
    }
}
