package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.util.List;
import java.util.logging.Level;

public class CheaterFortifyStrategy implements FortifyStrategy {

    /**
     * double the army count if a neighbour country is encountered which is not present in allowedCountries.
     *
     * @param map              Map
     * @param allowedCountries countries on which fortification is possible
     * @return if fortification was success
     */
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
        for (Country c : allowedCountries) {
            List<Country> neighbours = map.getNeighbouringCountry(c);
            // finding if neighbour belongs to opponent
            for (Country neighbour : neighbours) {
                if (!allowedCountries.contains(neighbour)) {
                    logger.log(Level.INFO, "Opponent country " + neighbour + " adjacent to " + c);
                    logger.log(Level.INFO, "Original count " + c.getArmy());
                    c.setArmy(c.getArmy() * 2);
                    logger.log(Level.INFO, "Updated count " + c.getArmy());
                    break;
                }
            }
        }
        return true;
    }

}
