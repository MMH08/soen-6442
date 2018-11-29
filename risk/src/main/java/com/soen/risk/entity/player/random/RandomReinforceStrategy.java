package com.soen.risk.entity.player.random;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class RandomReinforceStrategy implements ReinforceStrategy {

    /**
     * Randomly add amount from the reinforce army count.
     *
     * @param map       Map
     * @param countries owned by the player
     */
    @Override
    public void execute(Map map, List<Country> countries) {
        int reinforceArmy = ReinforceStrategy.calculateArmyCount(map, countries);

        Random rand = new Random();
        // till we consume the reinforce army count, keep on adding the count to random countries.
        while (reinforceArmy > 0) {
            Country country = countries.get(rand.nextInt(countries.size()));
            country.addArmy(1);
            reinforceArmy = reinforceArmy - 1;
            logger.log(Level.INFO, "Current reinforce count " + reinforceArmy + ", added army to " + country);
        }
    }
}
