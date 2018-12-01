package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

import java.io.Serializable;

public class BenevolentStartupStrategy implements StartupStrategy, Serializable {

    /**
     * Startup strategy for benevolent player
     *
     * @param country      in which army has to be assigned
     * @param armyCapacity total army count available
     * @return armies consumed
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
