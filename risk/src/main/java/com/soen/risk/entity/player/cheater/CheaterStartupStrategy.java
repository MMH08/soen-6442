package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

public class CheaterStartupStrategy implements StartupStrategy {
    @Override
    public int execute(Country country, int armyCapacity) {
        return 0;
    }
}
