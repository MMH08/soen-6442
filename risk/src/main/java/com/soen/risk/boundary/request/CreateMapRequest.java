package com.soen.risk.boundary.request;

import java.util.ArrayList;
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
        this.continents = new ArrayList<>();
        this.countries = new ArrayList<>();
        this.controlValues = new ArrayList<>();
        this.countryOwners = new ArrayList<>();
        this.connections = new ArrayList<>();
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

    public void setContinents(String cont) {
        //TODO
    	String s[] = cont.split(",");
    	for(int i=0;i<s.length;i++)
    	{
    		this.continents.add(s[i]);
    	}
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(String cont) {
        // TODO
    	String s[] = cont.split(",");
    	for(int i=0;i<s.length;i++)
    	{
    		this.countries.add(s[i]);
    	}
    }

    public List<Integer> getControlValues() {
        return controlValues;
    }

    public void setControlValues(String cont) {
        //TODO
    	String s[] = cont.split(",");
    	for(int i=0;i<s.length;i++)
    	{
    		this.controlValues.add(Integer.valueOf(s[i]));
    	}
    }

    public List<String> getCountryOwners() {
        return countryOwners;
    }

    public void setCountryOwners(String cont) {
        //TODO
    	String s[] = cont.split(",");
    	for(int i=0;i<s.length;i++)
    	{
    		this.countryOwners.add(s[i]);
    	}
    }

    public List<List<String>> getConnections() {
        return connections;
    }

    // Format: c1,c2,c3|c4,c3|c4,c5
    public void setConnections(String cont) {
        //TODO
    	String s[] = cont.split("|");
    	for(int i=0;i<s.length;i++)
    	{
    		ArrayList<String> ll = new ArrayList();
    		String in[] = s[i].split(",");
    		for(int j=0;j<in.length;j++)
    		{
    			ll.add(in[j]);
    		}
    		connections.add(ll);
    	}
    }
}
