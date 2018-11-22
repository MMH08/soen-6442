package com.soen.risk.entity.player.aggressive;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

public class AggressiveStartupStrategy implements StartupStrategy {
    @Override
    public int execute(Country country, int armyCapacity) {
        return 0;
    }
}
