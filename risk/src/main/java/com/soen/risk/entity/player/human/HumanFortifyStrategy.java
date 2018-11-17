package com.soen.risk.entity.player.human;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.util.List;

public class HumanFortifyStrategy implements FortifyStrategy {
    private Country startCountry;
    private Country endCountry;
    private int armyCount;

    public HumanFortifyStrategy(Country startCountry, Country endCountry, int armyCount) {
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.armyCount = armyCount;
    }

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
