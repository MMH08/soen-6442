package com.soen.risk.entity;

import java.util.List;
import java.util.logging.Logger;

public interface ReinforceStrategy  {
    void execute(Map map, List<Country> countries);

    static int calculateArmyCount(Map map, List<Country> countries){
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
    Logger logger = Logger.getLogger(ReinforceStrategy.class.getName());
}