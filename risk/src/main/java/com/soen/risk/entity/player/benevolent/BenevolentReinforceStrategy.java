package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BenevolentReinforceStrategy implements ReinforceStrategy {
    @Override
    public void execute(Map map, List<Country> countries) {
        int count = 1;
    	ArrayList<Country> weakCountries = new ArrayList<>(); 
        int reinforceCount = ReinforceStrategy.calculateArmyCount(map, countries);
        Country weakestCountry = Collections.min(countries, Comparator.comparing(Country::getArmy));
        int weakestArmycount = weakestCountry.getArmy();
        for (Country c: countries)
        {
        	if(weakestArmycount == c.getArmy())
        	{
        		weakCountries.add(c);
        	}
        }
        while(count<=reinforceCount) {
        	for (Country weakestCountries : weakCountries) {
        		if(count <= reinforceCount) {
        			weakestCountries.addArmy(1);
        			count++;
        		}
        	}
        }
    }
}


