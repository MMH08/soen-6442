package com.soen.risk.entity;

import java.util.List;
import java.util.logging.Logger;

/**
 * The Interface ReinforceStrategy.
 * @author varun
 */
public interface ReinforceStrategy {
    
    /**
     * Additional armies provided to the player to reinforce.
     *
     * @param map       Map
     * @param countries owned by the player
     */
    void execute(Map map, List<Country> countries);

    /**
     * Calculate the army count with the help of map and countries available to the player.
     *
     * @param map       Map
     * @param countries countries owned by the player
     * @return the int
     */
    static int calculateArmyCount(Map map, List<Country> countries) {
        //Check if player has all country of a continent
        for (Continent ctt : map.getContinents()) {
            int size = ctt.getCountries().size();
            int count = 0;
            for (Country country : countries) {
                for (Country continent_countries : ctt.getCountries()) {
                    if (continent_countries.getName().equals(country.getName())) {
                        count++;
                    }
                }
            }
            if (size == count) {
                return ctt.getControlValue();
            }

        }
        //If Player do not have all country of a continent
        int number_of_countries = countries.size();
        return Math.max(3, (int) Math.ceil(number_of_countries / 3.0));
    }

    /** The logger. */
    Logger logger = Logger.getLogger(ReinforceStrategy.class.getName());
}