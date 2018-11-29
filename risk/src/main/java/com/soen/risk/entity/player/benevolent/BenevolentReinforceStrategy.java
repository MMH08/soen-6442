package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;

public class BenevolentReinforceStrategy implements ReinforceStrategy {
    /**
     * @param map       Map
     * @param countries owned by the player
     */
    @Override
    public void execute(Map map, List<Country> countries) {
        ArrayList<Country> weakCountries = new ArrayList<>();
        int reinforceCount = ReinforceStrategy.calculateArmyCount(map, countries);

        Country weakestCountry = Collections.min(countries, Comparator.comparing(Country::getArmy));
        int weakestArmycount = weakestCountry.getArmy();
        for (Country c : countries) {
            if (weakestArmycount == c.getArmy()) {
                weakCountries.add(c);
            }
        }
        logger.log(Level.INFO, "Weak countries with army " + weakestArmycount + " : " + weakCountries);
        // give reinforce count to weak countries
        while (reinforceCount > 0) {
            for (Country weakestCountries : weakCountries) {
                if (reinforceCount > 0) {
                    weakestCountries.addArmy(1);
                    reinforceCount = reinforceCount - 1;
                    logger.log(Level.INFO, "Current reinforce count " + reinforceCount + ", added army to " + weakestCountries);
                }
            }
        } // while ends
    }

}


