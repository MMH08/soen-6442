package com.soen.risk.entity;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

/**
 * <h2>Map Class</h2>
 * <p> Map class contains loading of map using load method, save map, add new Country and continents.
 * Validation of map.</p>
 *
 * @author : Amit Sachdeva
 * @author : Manmeet Singh
 * @author : Nivetha
 * @version : 1.0.0
 */
public class Map {
    static Logger logger = Logger.getLogger(Map.class.getName());
    private String name;

    private ArrayList<Continent> continents;
    private ArrayList<Country> countries;
    private LinkedList<LinkedList> adjCountry;
    private LinkedList list_country;
    private String fileName;

    /**
     * Initiating continents, countries, 2D linkedlist of country
     * @author Amit Sachdeva
     */
    public Map() {
        this.continents = new ArrayList<>();
        this.countries = new ArrayList<>();
        this.adjCountry = new LinkedList<>();
        this.list_country = new LinkedList<>();
    }

    /**
     * Add new continent to continent object     * 
     * @param continent new continent
     * @author Amit Sachdeva
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
     * This method receive map from file and add to adjCountry object.
     * @param filename Path of File with name of file
     * @author Amit Sachdeva
     * @since 2018-10-06
     */
    public void load(String filename) {
    	this.fileName = filename;
        logger.log(Level.INFO, "Reading filename " + this.fileName);
        
        try {
            Scanner reading_file = new Scanner(new FileReader(this.fileName));
            int flag_continent = 0, flag_country = 0;

            while (reading_file.hasNext()) {
                String temp = reading_file.nextLine();
                if (!temp.equals("") && flag_continent == 1) this.addNewContinent(temp);
                else if (temp.equals("") && flag_continent == 1) flag_continent = 0;

                if (flag_country == 1) this.addNewCountry(temp);

                if (temp.equals("[Continents]")) flag_continent = 1;
                else if (temp.equals("[Territories]")) flag_country = 1;
            }

            reading_file.close();
            this.create_map_object_function();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
    /**
     * This method to add new continent
     * @param temp Receive continent name with control value
     * @author Amit Sachdeva
     * @since 2018-10-06
     */
    public void addNewContinent(String temp) {
        logger.log(Level.FINE,"Adding continent " + temp);
        String split_continent[] = temp.split("=");
        Continent cont = new Continent(split_continent[0]);
        cont.setControlValue(Integer.valueOf(split_continent[1]));
        continents.add(cont);
    }
    
    /**
     * This method to add new country and add new Country in their respective continent
     * @param temp Receive country with location, continent in which belong and neighbouring countries
     * @author Amit Sachdeva
     * @since 2018-10-06
     */
    public void addNewCountry(String temp) {
        logger.log(Level.FINE, "Adding new country " + temp);
        String split_country[] = temp.split(",");
        Country coun = new Country(countries.size(), split_country[0]);
        coun.setCoordinateX(split_country[1]);
        coun.setCoordinateY(split_country[2]);
        Iterator il = continents.iterator();

        String temp_continent_name = split_country[3];
        while (il.hasNext()) {

            Continent temp_cont = (Continent) il.next();
            if (temp_cont.getName().equals(temp_continent_name)) {
                temp_cont.addCountry(coun);
                break;
            }
        }
        this.countries.add(coun);
        String arr[] = new String[split_country.length - 3];
        arr[0] = split_country[0];
        for (int i = 4; i < split_country.length; i++) {
            arr[i - 3] = split_country[i];
        }
        this.map_name_creation(arr);
    }
    
    //Creating map on basis of Country Name
    private void map_name_creation(String s[]) {
        LinkedList temp_list = new LinkedList<String>();

        int i = 0;
        while (i < s.length) {
            temp_list.add(s[i]);
            i++;
        }
        this.list_country.add(temp_list);
    }

    //Function to intialize map country object creation
    private void create_map_object_function() {
        //this.adjCountry = new LinkedList<LinkedList>();
        this.map_country_object_creation();
    }

    //Function to create 2D linked list on basis of country object
    private Country retrieve_country_object(String s) {
        for (Country c : countries) {
            if (c.getName().equals(s)) return c;
        }
        return null;
    }
    
    /**
     * This method to create graph of whole map.
     * @author Amit Sachdeva
     * @since 2018-10-07
     */
    void map_country_object_creation() {

        Iterator il = list_country.iterator();
        while (il.hasNext()) {
            LinkedList l1 = (LinkedList) il.next();
            LinkedList temp = new LinkedList<Country>();
            Iterator il2 = l1.iterator();
            while (il2.hasNext()) {
                String s = (String) il2.next();
                temp.add(retrieve_country_object(s));
            }
            adjCountry.add(temp);
        }

    }

    //Get map on basis of name
    public LinkedList getMap() {
        return list_country;
    }

    //Get map on basis of country object
    public LinkedList getMapCountryObject() {
        return adjCountry;
    }
    
    //Get number of countries
    public int getNumberOfCountries() {
        return countries.size();
    }
    
    //Get number of continents
    public int getNumberOfContinents() {
        return continents.size();
    }
    
    /**
     * This method get continent for specific country.
     * @return Return Continent name.
     * @param c Pass country object
     * @author Manmeet Singh
     * @since 2018-10-07
     */
    public String getCountryFromContinents(Country c)
    {
    	for(Continent con: continents)
    	{
    		for(Country coun: con.getCountries())
    		{
    			if(coun.getName().equals(c.getName()))
    			{
    				return con.getName();
    			}
    		}
    	}
    	return "";
    }
    /**
     * This method get continent for specific country.
     * @return Return Neighbouring countries of current country.
     * @param country current country string
     * @author Manmeet Singh
     * @since 2018-10-07
     */
    public String getNeighbouringCountries(String country)
    {
    	for(LinkedList<Country> ll: this.adjCountry)
    	{
    		if(ll.get(0).getName().equals(country))
    		{
    			int i=1;
    			String temp = "";
    			while(i<ll.size())
    			{
    				temp = temp + ll.get(i).getName();
    				if(i!=ll.size() - 1)
    				{
    					temp = temp + ",";
    				}
    				i++;
    			}
    			return temp;
    		}
    	}
    	return "";
    }
    /**
     * This method save finally updated map in a file.
     * @author Manmeet Singh
     * @since 2018-10-07
     */
    public void save(){
    	try {
    		
    		PrintWriter pw = new PrintWriter(new File(this.fileName));
    		String temp[] = this.fileName.split("\\");
    		pw.println("Map" + temp[temp.length - 1]);
    		pw.println();
    		pw.println("[Continents]");
    		for(Continent cont: continents)
    		{
    			pw.println(cont.getName()+"="+cont.getControlValue());
    		}
    		pw.println();
    		pw.println("[Territories]");
    		for(Country coun: countries)
    		{
    			String tem = coun.getName() + "," + coun.getCoordinateX() + "," + coun.getCoordinateY() +"," + this.getCountryFromContinents(coun) +"," + this.getNeighbouringCountries(coun.getName());
    			pw.println(tem);
    		}
    		pw.close();
    	}
    	catch(Exception e)
    	{
    		
    	}
    	
    	
    }

    /**
     * Validates the continents and country objects for duplicate values.
     *
     * @return true is no duplicates found
     * false for duplicate occurrence
     * @author Nivetha
     * @since 2018-10-06
     */
    public boolean isValid() {

        if (!continents.isEmpty()) {
            HashSet<String> hs = new HashSet<String>();
            for (Continent u : continents) {

                if (hs.add(u.getName()) == false)
                    logger.log(Level.FINE,"Continents" + u.getName());
                return false;
            }
        } else {
            logger.log(Level.INFO,"Continents are empty");
            return false;
        }
        if (!countries.isEmpty()) {
            HashSet<String> hs1 = new HashSet<String>();
            for (Country u : countries) {

                if (hs1.add(u.getName()) == false)
                    logger.log(Level.FINE, "Countries" + u.getName());
                return false;
            }
        } else {
            logger.log(Level.INFO,"countries are empty");
            return false;
        }
        return true;

    }


    /**
     * Add country to country object
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

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList countries) {
        this.countries = countries;
    }

    public LinkedList<LinkedList> getAdjCountry() {
        return adjCountry;
    }

    public void setAdjCountry(LinkedList adjCountry) {
        this.adjCountry = adjCountry;
    }

    public ArrayList<Continent> getContinents() {
        return continents;
    }

    // -------------------------------------------------------------

}
