package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BenevolentReinforceStrategy implements ReinforceStrategy {
    @Override
    public void execute(Map map, List<Country> countries) {
        //todo : multiple weakest country - divide the reinforce count and add to country
        int reinforceCount = ReinforceStrategy.calculateArmyCount(map, countries);
        Country weakestCountry = Collections.min(countries, Comparator.comparing(Country::getArmy));
        weakestCountry.addArmy(reinforceCount);
    }
}
