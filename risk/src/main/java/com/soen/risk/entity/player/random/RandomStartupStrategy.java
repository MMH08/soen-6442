package com.soen.risk.entity.player.random;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

public class RandomStartupStrategy implements StartupStrategy {
    /**
     * Adding 1 army count out of startup armies
     *
     * @param country      in which army has to be assigned
     * @param armyCapacity total army count available
     * @return army count consumed
     */
    @Override
    public int execute(Country country, int armyCapacity) {
        if (armyCapacity > 0) {
            country.addArmy(1);
        }
        return 1;
    }
}
