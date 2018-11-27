package com.soen.risk.entity.player.aggressive;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AggressiveReinforceStrategy implements ReinforceStrategy {
    @Override

    public void execute(Map map, List<Country> countries) {
    	int armyCount = ReinforceStrategy.calculateArmyCount(map, countries);
    	int max = Integer.MIN_VALUE;
    	Country maxCountry = null;
    	for(Country c: countries)
    	{
    		if(c.getArmy()>max)
    		{
    			max = c.getArmy();
    			maxCountry = c;
    		}
    	}
    	maxCountry.setArmy(maxCountry.getArmy() + armyCount);
    }
}
