package com.soen.risk.entity.player.random;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

public class RandomStartupStrategy implements StartupStrategy {
    @Override
    public int execute(Country country, int armyCapacity) {
        return 0;
    }
}
