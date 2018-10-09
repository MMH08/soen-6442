package com.soen.risk.entity;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author :
 */
public class Map {
    private String name;

    private ArrayList<Continent> continents;
    private ArrayList<Country> countries;
    private LinkedList<LinkedList> adjCountry;
    private LinkedList list_country;

    /**
     *
     */
    public Map() {
        this.continents = new ArrayList<>();
        this.countries = new ArrayList<>();
        this.adjCountry = new LinkedList<>();
        this.list_country = new LinkedList<>();
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
     * @param filename
     * @author Amit Sachdev
     * @since 2018-10-06
     */
    public void load(String filename) {
        System.out.println("Reading filename " + filename);
        try {
            Scanner reading_file = new Scanner(new FileReader(filename));
            int flag_continent = 0, flag_country = 0;

            while (reading_file.hasNext()) {
                String temp = reading_file.nextLine();
                System.out.println(temp);

                if (!temp.equals("") && flag_continent == 1) this.addNewContinent(temp);
                else if (temp.equals("") && flag_continent == 1) flag_continent = 0;

                if (flag_country == 1) this.addNewCountry(temp);

                if (temp.equals("[Continents]")) flag_continent = 1;
                else if (temp.equals("[Territories]")) flag_country = 1;
            }

            reading_file.close();
            this.create_map_object_function();
        } catch (Exception e) {
            System.out.println("System Error");
        }
    }

    public void addNewContinent(String temp) {
        System.out.println("Adding continent " + temp);
        String split_continent[] = temp.split("=");
        Continent cont = new Continent(split_continent[0]);
        cont.setControlValue(Integer.valueOf(split_continent[1]));
        continents.add(cont);
    }

    public void addNewCountry(String temp) {
        System.out.println("Adding new country " + temp);
        String split_country[] = temp.split(",");
        Country coun = new Country(split_country[0]);
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

    public void map_name_creation(String s[]) {
        LinkedList temp_list = new LinkedList<String>();

        int i = 0;
        while (i < s.length) {
            temp_list.add(s[i]);
            i++;
        }
        this.list_country.add(temp_list);
    }

    //Function to intialize map country object creation
    public void create_map_object_function() {
        //this.adjCountry = new LinkedList<LinkedList>();
        this.map_country_object_creation();
    }

    //Function to create 2D linked list on basis of country object
    private Country retrieve_country_object(String s) {
        Iterator il = countries.iterator();
        while (il.hasNext()) {
            Country c = (Country) il.next();
            if (c.getName().equals(s)) {
                return c;
            }
        }
        return null;
    }

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

    public int getNumberOfCountries() {
        return countries.size();
    }

    public int getNumberOfContinents() {
        return continents.size();
    }

    /**
     * @author Manmeet Singh
     * @since 2018-10-06
     */
    public void save() {
    }

    /**
     * @return
     * @author Nivetha
     * @since 2018-10-06
     */
    public boolean isValid() {
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

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList countries) {
        this.countries = countries;
    }

    public LinkedList getAdjCountry() {
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
