package com.soen.risk.interactor;

import com.soen.risk.entity.Continent;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void build() {
        this.map.setName(this.mapName);
        addContinentObjects();
        addCountryObjects();
        addCountryToContinents();


        //Making map
        int i = 0;
        for (List<String> connection : this.connections) {
            logger.log(Level.INFO, "Building connection :" + this.countries.get(i));
            String s[] = new String[connection.size() + 1];
            s[0] = this.countries.get(i);
            int j = 1;
            for (String connectingCountry : connection) {
                System.out.println("JAI MATA DI" + connectingCountry);
                s[j] = connectingCountry;
                j++;
            }
            map.map_name_creation(s);
            i++;
        }
        map.map_country_object_creation();
    }

    private void addContinentObjects() {
        int i = 0;
        for (String s : this.continents) {
            Continent c = new Continent(s);
            c.setControlValue(controlValues.get(i));
            map.addContinent(c);
            i++;
        }
    }

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

    private void addCountryToContinents() {
        int i = 0;
        for (String continent : countryOwners) {
            map.findByContinentName(continent).addCountry(map.getCountries().get(i));
            i++;
        }
    }

    public String save() {
        map.setFileName("/home/varun/Downloads/test/" + map.getName() + ".map");
        //if (map.isValid())
        map.save();
        return map.getFileName();
    }
}
