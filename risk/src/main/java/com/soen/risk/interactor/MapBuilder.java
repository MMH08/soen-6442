package com.soen.risk.interactor;

import com.soen.risk.entity.Continent;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h3>MapBuilder</h3>
 * Map Builder to create the Map with given name, continents, countries and their control values. <br/>
 * Adjacency between the maps is also built<br/>
 * Map is saved as text file to a external folder<br/>
 *
 * @author Amit
 * @since 03/11/2018
 * @version 1.0.2
 */


public class MapBuilder {
    private static Logger logger = Logger.getLogger(MapBuilder.class.getName());

    private String mapName;
    private List<String> continents;
    private List<String> countries;
    private List<Integer> controlValues;
    private List<String> countryOwners;
    private List<List<String>> connections;
    private Map map;

    public MapBuilder(String name, List<String> continents, List<Integer> controlValues, List<String> countries,
                      List<String> countryOwners, List<List<String>> connections) {
        map = new Map();
        this.mapName = name;
        this.continents = continents;
        this.countries = countries;
        this.controlValues = controlValues;
        this.countryOwners = countryOwners;
        this.connections = connections;
        
    }
    
    
    /**
     * Building the Map object by calling the respective methods.
     */
    public void build() {
        this.map.setName(this.mapName);
        addContinentObjects();
        addCountryObjects();
        addCountryToContinents();
        logger.log(Level.INFO,"CONNECTIONS"+this.connections.size());
               

        //Making map
        int i = 0;
        for (List<String> connection : this.connections) {
            logger.log(Level.INFO, "Building connection :" + this.countries.get(i)+" "+connection.size()+1);
            String s[] = new String[connection.size() + 1];
            s[0] = this.countries.get(i);
            int j = 1;
            for (String connectingCountry : connection) {
                System.out.println(connectingCountry);
                s[j] = connectingCountry;
                j++;
            }
            map.map_name_creation(s);
            i++;
        }
        map.map_country_object_creation();
    }
    
    /**
     * Getting continent objects and adding to Map
     */
    private void addContinentObjects() {
        int i = 0;
        for (String s : this.continents) {
            Continent c = new Continent(s);
            c.setControlValue(controlValues.get(i));
            map.addContinent(c);
            i++;
        }
    }
    
    /**
     * Getting country objects and adding to Map
     */
    private void addCountryObjects() {
        //Countries
//        ArrayList<Country> temp = new ArrayList<>();
        int i = 0;
        for (String s : this.countries) {
            Country c = new Country(i, s);
            c.setCoordinateX("0");
            c.setCoordinateY("0");
            map.addCountry(c);
//            for (Continent con : map.getContinents()) {
//                if (con.getName().equals(countryOwners.get(i))) {
//                    con.addCountry(c);
//                    break;
//                }
//            }
//            temp.add(c);
            i++;
        }
    }
    
    
    /**
     * Mapping country to Continents
     */
    private void addCountryToContinents() {
        int i = 0;
        for (String continent : countryOwners) {
            map.findByContinentName(continent).addCountry(map.getCountries().get(i));
            i++;
        }
    }

    /**
     * Saving the map to an external folder
    // * @param User given folder location
     */
    public String save(String downloadFolder) {
        //map.setFileName();
        if (map.isValid()){
            logger.log(Level.INFO, "Map is valid ");
            map.save(downloadFolder + map.getName() + ".map");
        }
        return map.getFileName();
    }
}
