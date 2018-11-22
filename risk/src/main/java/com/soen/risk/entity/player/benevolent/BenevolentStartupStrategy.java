package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

public class BenevolentStartupStrategy implements StartupStrategy {
    @Override
    public int execute(Country country, int armyCapacity) {
        return 0;
    }
}
