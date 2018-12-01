package com.soen.risk.entity.player.aggressive;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

public class AggressiveReinforceStrategy implements ReinforceStrategy, Serializable {
    /**
     * Aggressive strategy locates the strongest country, to add all of its reinforce army. In case,
     * there is a draw, it will assign all of its reinforce army to the one which occurs first in
     * the list of owned countries.
     *
     * @param map       Map
     * @param countries owned by the player
     */
    @Override

    public void execute(Map map, List<Country> countries) {
        int armyCount = ReinforceStrategy.calculateArmyCount(map, countries);
        int max = Integer.MIN_VALUE;
        Country maxCountry = null;
        for (Country c : countries) {
            if (c.getArmy() > max) {
                max = c.getArmy();
                maxCountry = c;
            }
        }
        if (maxCountry != null) {
            maxCountry.addArmy(armyCount);
            logger.log(Level.INFO, "Aggressive player added " + armyCount + " armies to " + maxCountry);
        }
    }
}
