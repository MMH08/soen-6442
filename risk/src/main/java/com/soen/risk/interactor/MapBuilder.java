package com.soen.risk.interactor;

import java.util.*;

import com.soen.risk.entity.*;
import com.soen.risk.entity.Map;
public class MapBuilder {

	private List<String> continents;
    private List<String> countries;
    private List<Integer> controlValues;
    private List<String> countryOwners;
    private List<List<String>> connections;
    Map map;
	public MapBuilder(List<String> continent, List<Integer> list, List<String> country, List<String> countryOwners, List<List<String>> connections) {
		map = new Map();
        this.continents = new ArrayList<>();
        this.countries = new ArrayList<>();
        this.controlValues = new ArrayList<>();
        this.countryOwners = new ArrayList<>();
        this.connections = new ArrayList<>();
		
    }

    public void build() {
    	int i = 0;
    	
    	//Continents
    	for(String s: this.continents)
    	{
    		Continent c= new Continent(s);
    		c.setControlValue(controlValues.get(i));    		
    		map.addContinent(c);
    		i++;
    	}
    	
    	//Countries
    	ArrayList<Country> temp = new ArrayList<>();
    	i=0;
    	for(String s: this.countries)
    	{
    		Country c = new Country(i,s);
    		c.setCoordinateX("0");
    		c.setCoordinateY("0");
    		for(Continent con: map.getContinents())
    		{
    			if(con.getName().equals(countryOwners.get(i)))
    			{
    				con.addCountry(c);
    				break;
    			}
    		}
    		temp.add(c);
    		i++;
    	}
    	map.setCountries(temp);
    	
    	//Making map
    	i=0;
    	for(List<String> ap: this.connections)
    	{
    		
    		String s[] = new String[ap.size()+1];
    		s[0] = this.countries.get(i);
    		int j=1;
    		for(String k: ap)
    		{
    			s[j] = k;
    			j++;
    		}
    		map.map_name_creation(s);
    		i++;
    	}
    	map.create_map_object_function();
    	
    	
    	
    }

    public void save() {

    }
}
