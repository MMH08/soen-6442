package com.soen.risk.entity;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static Logger logger = Logger.getLogger(Map.class.getName());
    private String name;

    private ArrayList<Continent> continents;
    private ArrayList<Country> countries;
    private LinkedList<LinkedList<Country>> adjCountry;
    private LinkedList<Object> list_country;
    private String fileName;

    /**
     * Initiating continents, countries, 2D linkedlist of country
     */
    public Map() {
        this.continents = new ArrayList<>();
        this.countries = new ArrayList<>();
        this.adjCountry = new LinkedList<>();
        this.list_country = new LinkedList<>();
    }

    /**
     * Add new continent to continent object     *
     *
     * @param continent new continent
     */
    public void addContinent(Continent continent) {
        logger.log(Level.INFO, "Adding continent " + continent.getName());
        continents.add(continent);
    }

    /**
     * Add country to country object
     *
     * @param country
     */
    public void addCountry(Country country) {
        logger.log(Level.INFO, "Adding country " + country.getName());
        countries.add(country);
    }

    /**
     * @param country1
     * @param country2
     */
    public void connect(Country country1, Country country2) {
    }

    public Country findByCountryName(String s) {
        logger.log(Level.INFO, "Searching for country by name :" + s);
        for (Country c : countries) if (c.getName().equals(s)) return c;
        return null;
    }

    public Continent findByContinentName(String s) {
        logger.log(Level.INFO, "Searching for continent by name :" + s);
        for (Continent c : continents) if (c.getName().equals(s)) return c;
        return null;
    }


    // -------------------------------------------------------------

    /**
     * This method receive map from file and add to adjCountry object.
     *
     * @param filename Path of File with name of file
     * @author Amit Sachdeva
     * @since 2018-10-06
     */
    public void load(String filename) {
        this.fileName = filename;
        logger.log(Level.INFO, "Reading filename " + this.fileName);
        try {
            Scanner readingFile = new Scanner(new FileReader(this.fileName));
            int flagContinent = 0, flagCountry = 0;
            //TODO: machine independent path separator to load name of the map.
            while (readingFile.hasNext()) {
                String line = readingFile.nextLine();

                if (!line.equals("") && flagContinent == 1) this.addNewContinent(line);
                else if (line.equals("") && flagContinent == 1) flagContinent = 0;

                if (flagCountry == 1) this.addNewCountry(line);

                if (line.equals("[Continents]")) flagContinent = 1;
                else if (line.equals("[Territories]")) flagCountry = 1;
            }

            readingFile.close();
            this.map_country_object_creation();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * This method save finally updated map in a file.
     *
     * @author Manmeet Singh
     * @since 2018-10-07
     */
    public void save() {
        try {
            logger.log(Level.INFO, "Saving map to " + fileName);
            PrintWriter pw = new PrintWriter(new File(this.fileName));
//            String temp[] = this.fileName.split("//");
            pw.println("Map=" + this.name);
            pw.println();
            pw.println("[Continents]");
            for (Continent continent : continents) {
                pw.println(continent.getName() + "=" + continent.getControlValue());
            }
            pw.println();
            pw.println("[Territories]");
            for (Country country : countries) {
                String tem = country.getName() + "," + country.getCoordinateX() + "," + country.getCoordinateY() + "," +
                        this.getCountryFromContinents(country) + "," + this.getNeighbouringCountries(country.getName());
                logger.log(Level.FINE, tem);
                pw.println(tem);
            }
            pw.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
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
                    logger.log(Level.FINE, "Continents" + u.getName());
                return false;
            }
        } else {
            logger.log(Level.INFO, "Continents are empty");
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
            logger.log(Level.INFO, "countries are empty");
            return false;
        }
        return true;

    }
    // -------------------------------------------------------------

    /**
     * This method get continent for specific country.
     *
     * @param c Pass country object
     * @return Return Continent name.
     * @author Manmeet Singh
     * @since 2018-10-07
     */
    private String getCountryFromContinents(Country c) {
        for (Continent con : continents) {
            for (Country coun : con.getCountries()) {
                if (coun.getName().equals(c.getName())) {
                    return con.getName();
                }
            }
        }
        return "";
    }

    /**
     * This method get neighbouring countries for specific country.
     *
     * @param country current country string
     * @return Return neighbouring countries of current country.
     * @author Manmeet Singh
     * @since 2018-10-07
     */
    private String getNeighbouringCountries(String country) {
        for (LinkedList<Country> ll : this.adjCountry) {
            if (ll.get(0).getName().equals(country)) {
                int i = 1;
                StringBuilder temp = new StringBuilder();
                while (i < ll.size()) {
                    temp.append(ll.get(i).getName());
                    if (i != ll.size() - 1) {
                        temp.append(",");
                    }
                    i++;
                }
                return temp.toString();
            }
        }
        return "";
    }

    // --------------------------------------------------------------------------------------

    /**
     * This method to add new continent
     *
     * @param temp Receive continent name with control value
     * @author Amit Sachdeva
     * @since 2018-10-06
     */
    private void addNewContinent(String temp) {
        logger.log(Level.FINE, "Adding continent " + temp);
        String split_continent[] = temp.split("=");
        Continent cont = new Continent(split_continent[0]);
        cont.setControlValue(Integer.valueOf(split_continent[1]));
        continents.add(cont);
    }

    /**
     * This method to add new country and add new Country in their respective continent
     *
     * @param temp Receive country with location, continent in which belong and neighbouring countries
     * @author Amit Sachdeva
     * @since 2018-10-06
     */
    private void addNewCountry(String temp) {
        logger.log(Level.FINE, "Adding new country " + temp);
        String split_country[] = temp.split(",");
        Country coun = new Country(countries.size(), split_country[0]);
        coun.setCoordinateX(split_country[1]);
        coun.setCoordinateY(split_country[2]);
        Iterator<Continent> il = continents.iterator();

        String temp_continent_name = split_country[3];
        while (il.hasNext()) {

            Continent temp_cont = il.next();
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
    public void map_name_creation(String s[]) {
        LinkedList<String> temp_list = new LinkedList<>();

        int i = 0;
        while (i < s.length) {
            temp_list.add(s[i]);
            i++;
        }
        this.list_country.add(temp_list);
    }

    //Function to intialize map country object creation
//    public void create_map_object_function() {
//        this.adjCountry = new LinkedList<LinkedList>();
//        this.map_country_object_creation();
//    }


    /**
     * This method to create graph of whole map.
     *
     * @author Amit Sachdeva
     * @since 2018-10-07
     */
    public void map_country_object_creation() {
        for(Object a: list_country)
        {
            LinkedList ee = (LinkedList) a;
            for(Object s: ee)
            {
                System.out.print((String)s);
            }
            System.out.println();
        }
        for (Object aList_country : list_country) {
            LinkedList l1 = (LinkedList) aList_country;
            LinkedList<Country> links = new LinkedList<>();
            for (Object aL1 : l1) {
                String s = (String) aL1;
                links.add(findByCountryName(s));
            }
            adjCountry.add(links);
        }

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

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }

    public LinkedList<LinkedList<Country>> getAdjCountry() {
        return adjCountry;
    }

    public void setAdjCountry(LinkedList<LinkedList<Country>> adjCountry) {
        this.adjCountry = adjCountry;
    }

    public ArrayList<Continent> getContinents() {
        return continents;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        logger.log(Level.INFO, "Setting filename " + fileName);
        this.fileName = fileName;
    }

    //Get map on basis of name
    public LinkedList<Object> getMap() {
        return list_country;
    }

    //Get map on basis of country object
    public LinkedList<LinkedList<Country>> getMapCountryObject() {
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
    // -------------------------------------------------------------

}
