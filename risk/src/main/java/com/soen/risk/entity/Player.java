package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Player {
    private String name;
    private int armyCapacity;
    private List<Country> countries;

    /**
     *
     */
    // -------------------------------------------------------------
    public Player(int nameSuffix) {
        this.name = "Player_" + String.valueOf(nameSuffix);
        this.countries = new ArrayList<>();
    }

    /**
     * @param c
     */
    public void addCountry(Country c) {
        this.countries.add(c);
    }

    public List<Country> getCountries() {
        return this.countries;
    }

    /**
     * @return
     */
    public Country nextCountryToAssignArmy() {
        for (Country country : countries) {
            if (country.isEmpty())
                return country;
        }
        return null;
    }

    // -------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmyCapacity() {
        return armyCapacity;
    }

    public void setArmyCapacity(int armyCapacity) {
        this.armyCapacity = armyCapacity;
    }

    public int getReinforceArmyCapacity(Map m) {
        //Check if player has all country of a continent
        for (Continent ctt : m.getContinents()) {
            int size = ctt.getCountries().size();
            int count = 0;
            for (Country player_countries : this.getCountries()) {
                for (Country continent_countries : ctt.getCountries()) {
                    if (continent_countries.getName().equals(player_countries.getName())) {
                        count++;
                    }
                }
            }
            if (size == count) {
                return ctt.getControlValue();
            }

        }
        //If Player do not have all country of a continent
        int number_of_countries = this.getCountries().size();
        return (int) Math.ceil(number_of_countries / 3.0);

    }

    public List<String> getCountryNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Country country : countries)
            names.add(country.getName());
        return names;
    }

}
