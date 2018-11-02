package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *<h2> Player Class</h2>
 *<p>In this class, we can countries of a player, number of armies assigned during reinforcement phase, 
 *Assign armies to country in startup phase</p>
 *@author Amit Sachdeva
 *@since 2018-10-10
 *@version 1.0.0
 */
public class Player extends Observable {
    private String name;
    private int armyCapacity;
    private List<Country> countries;

    /**
     *Player(int nameSuffix): Initiating arraylist of countries for current player and assigning name to player
     *@param nameSuffix name of player
     */
    public Player(int nameSuffix) {
        this.name = "Player_" + String.valueOf(nameSuffix);
        this.countries = new ArrayList<>();
    }

    /**
     * addCountry(Country c): Add new country to countries object
     * @param c
     */
    public void addCountry(Country c) {
        this.countries.add(c);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * When a match will be lost then remove country will be called
     */
    public void removeCountry(Country c){
        // code to remove country
        this.setChanged();
        this.notifyObservers();
    }

    public List<Country> getCountries() {
        return this.countries;
    }

    /**
     * nextCountryToAssignArmy()
     * @return Object of country to which army is assign
     */
    public Country nextCountryToAssignArmy() {
        for (Country country : countries) {
            if (country.isEmpty())
                return country;
        }
        return countries.get(countries.size()-1);
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
    
    /**
     *getReinforceArmyCapacity(Map m): Checking if player has all countries of whole continent then give army according to 
     *control value, otherwise give armies according to countries player own.
     * @return Object of country to which army is assign
     * @param m Reference of map class
     */
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
        return Math.max(3, (int) Math.ceil(number_of_countries / 3.0));

    }
    
    //Return All Countries Name of Player
    public List<String> getCountryNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Country country : countries)
            names.add(country.getName());
        return names;
    }

    public String toString() {
        return this.name;
    }
}
