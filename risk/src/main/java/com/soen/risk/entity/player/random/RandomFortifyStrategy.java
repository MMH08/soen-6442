package com.soen.risk.entity.player.random;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/**
 * The Class RandomFortifyStrategy.
 *
 * @author Amit
 */
public class RandomFortifyStrategy implements FortifyStrategy, Serializable {
    /**
     * Randomly start fortification from one of the allowedCountries, if loop completes without fortification
     * then that means fortification was not possible, hence return true to exit the phase.
     *
     * @param map              Map
     * @param allowedCountries owned countries by the player
     * @return if fortification was success
     */
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
        Collections.shuffle(allowedCountries);
        for (Country startCountry : allowedCountries) {
            List<Country> neighbours = map.getNeighbouringCountry(startCountry);
            // treat all neighbours as end country
            for (Country endCountry : neighbours) {
                // endCountry is owned by the same player
                // and there is a path between them then fortify and return true
                if (!startCountry.getName().equals(endCountry.getName()) && allowedCountries.contains(endCountry) && map.pathExists(startCountry, endCountry, allowedCountries)) {
                    int armyCount = startCountry.getArmy() / 2;
                    startCountry.setArmy(startCountry.getArmy() - armyCount);
                    endCountry.setArmy(endCountry.getArmy() + armyCount);
                    logger.log(Level.INFO, "Fortification between " + startCountry + " and " + endCountry + " count : " + armyCount);
                    return true;
                }
            }
        }
        return true;
    }
}
