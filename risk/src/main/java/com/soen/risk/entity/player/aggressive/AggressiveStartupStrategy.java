package com.soen.risk.entity.player.aggressive;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

import java.io.Serializable;

/**
 * The Class AggressiveStartupStrategy.
 *
 * @author varun
 */
public class AggressiveStartupStrategy implements StartupStrategy, Serializable {

    /**
     * Execute.
     *
     * @param country      in which army has to be assigned
     * @param armyCapacity total army count available
     * @return consumed army count
     */
    @Override
    public int execute(Country country, int armyCapacity) {
        if (armyCapacity > 0) {
            country.addArmy(1);
            return 1;
        }
        return armyCapacity;
    }
}
