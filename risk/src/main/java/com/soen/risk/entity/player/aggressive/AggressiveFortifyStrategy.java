package com.soen.risk.entity.player.aggressive;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

public class AggressiveFortifyStrategy implements FortifyStrategy, Serializable {
    /**
     * Maximise the army count in one country, which is possible if endCountry is the strongest country and
     * we move the armies from the second best country.
     *
     * @param map              Map
     * @param allowedCountries owned countries by the player
     * @return if fortification was possible
     */
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
        allowedCountries.sort(Collections.reverseOrder(Comparator.comparing(Country::getArmy)));

        for (int endCountryIndex = 0; endCountryIndex < allowedCountries.size(); endCountryIndex++) {
            Country endCountry = allowedCountries.get(endCountryIndex);
            for (Country startCountry : allowedCountries) {
                // endCountry is owned by the same player
                // and there is a path between them then fortify and return true
                if (isValidMove(map, allowedCountries, endCountry, startCountry)) {
                    int armyCount = startCountry.getArmy() - 1;
                    startCountry.setArmy(startCountry.getArmy() - armyCount);
                    endCountry.setArmy(endCountry.getArmy() + armyCount);
                    logger.log(Level.INFO, "Fortification between " + startCountry + " and " + endCountry + " count : " + armyCount);
                    return true;
                }
            }

        }
        return true;
    }

    private boolean isValidMove(Map map, List<Country> allowedCountries, Country endCountry, Country startCountry) {
        return !startCountry.getName().equals(endCountry.getName()) && map.pathExists(startCountry, endCountry, allowedCountries);
    }
}
