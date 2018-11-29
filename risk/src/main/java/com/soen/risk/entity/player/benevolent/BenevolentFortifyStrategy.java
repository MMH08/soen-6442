package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

public class BenevolentFortifyStrategy implements FortifyStrategy {

    /**
     * Start from the weakest country and try to move the army from the strongest country. If the path
     * does not exist then go for second best strongest country. This goes on till all the countries have
     * been traversed. Once done,
     * benevolent player will try to fortify the second weakest country by using the same strategy.
     * <p>
     * A special check to avoid swap between same countries in the end of first inner loop.
     * <p>
     * Reverse order sort - strongest country --> weakest country
     *
     * @param map              Map
     * @param allowedCountries owned countries by the player
     * @return if fortification was success.
     */
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
        allowedCountries.sort(Collections.reverseOrder(Comparator.comparing(Country::getArmy)));

        for (int weakestCountryindex = allowedCountries.size() - 1; weakestCountryindex > 0; weakestCountryindex--) {
            Country endCountry = allowedCountries.get(weakestCountryindex);
            for (Country startCountry : allowedCountries) {
                // endCountry is owned by the same player
                // and there is a path between them then fortify and return true
                if (isValidMove(map, allowedCountries, endCountry, startCountry)) {
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

    private boolean isValidMove(Map map, List<Country> allowedCountries, Country endCountry, Country startCountry) {
        return !startCountry.getName().equals(endCountry.getName()) && startCountry.getArmy() > endCountry.getArmy() && map.pathExists(startCountry, endCountry, allowedCountries);
    }
}
