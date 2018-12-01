package com.soen.risk.entity.player.human;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.io.Serializable;
import java.util.List;

/**
 * The Class HumanFortifyStrategy.
 *
 * @author varun
 */
public class HumanFortifyStrategy implements FortifyStrategy, Serializable {

    /**
     * The start country.
     */
    private Country startCountry;

    /**
     * The end country.
     */
    private Country endCountry;

    /**
     * The army count.
     */
    private int armyCount;

    /**
     * Instantiates a new human fortify strategy.
     *
     * @param startCountry the start country
     * @param endCountry   the end country
     * @param armyCount    the army count
     */
    public HumanFortifyStrategy(Country startCountry, Country endCountry, int armyCount) {
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.armyCount = armyCount;
    }

    /* (non-Javadoc)
     * @see com.soen.risk.entity.FortifyStrategy#execute(com.soen.risk.entity.Map, java.util.List)
     */
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
        if (map.pathExists(startCountry, endCountry, allowedCountries)) {
            // normalise the army count using the initial army in start country
            if (startCountry.getArmy() <= armyCount) armyCount = startCountry.getArmy() - 1;
            startCountry.setArmy(startCountry.getArmy() - armyCount);
            endCountry.setArmy(endCountry.getArmy() + armyCount);
            return true;
        }
        return false;
    }
}
