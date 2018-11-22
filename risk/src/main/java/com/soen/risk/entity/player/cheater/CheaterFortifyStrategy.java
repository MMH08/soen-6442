package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.util.List;
import java.util.logging.Level;

public class CheaterFortifyStrategy implements FortifyStrategy {
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
        for (Country c : allowedCountries) {
            for (Country neighborCountry : map.getNeighbouringCountry(c)) {
                // if c shares border with opponent player's country
                if (!allowedCountries.contains(neighborCountry)) {
                    logger.log(Level.INFO, "Double the army in country " + c);
                    c.setArmy(c.getArmy() * 2);
                    break;
                }
            }
        }
        return true;
    }

//    private boolean checkNeighbouringCountry(List<Country> countries, Country c) {
//        for (Country c1 : countries) {
//            if (c1.getName().equals(c.getName())) {
//                return false;
//            }
//        }
//        return true;
//    }
}
