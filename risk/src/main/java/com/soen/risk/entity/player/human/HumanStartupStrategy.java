package com.soen.risk.entity.player.human;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

import java.io.Serializable;

/**
 * The Class HumanStartupStrategy.
 *
 * @author varun
 */
public class HumanStartupStrategy implements StartupStrategy, Serializable {

    /**
     * The army count.
     */
    private int armyCount;

    /**
     * Instantiates a new human startup strategy.
     *
     * @param armyCount the army count
     */
    public HumanStartupStrategy(int armyCount) {
        this.armyCount = armyCount;
    }

    /* (non-Javadoc)
     * @see com.soen.risk.entity.StartupStrategy#execute(com.soen.risk.entity.Country, int)
     */
    @Override
    public int execute(Country country, int armyCapacity) {
        country.setArmy(country.getArmy() + armyCount);
        return armyCount; // consumed army count
    }
}
