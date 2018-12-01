package com.soen.risk.entity.player.human;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

import java.io.Serializable;

public class HumanStartupStrategy implements StartupStrategy, Serializable {
    private int armyCount;

    public HumanStartupStrategy(int armyCount) {
        this.armyCount = armyCount;
    }

    @Override
    public int execute(Country country, int armyCapacity) {
        country.setArmy(country.getArmy() + armyCount);
        return armyCount; // consumed army count
    }
}
