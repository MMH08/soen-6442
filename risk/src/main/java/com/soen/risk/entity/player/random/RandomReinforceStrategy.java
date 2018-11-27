package com.soen.risk.entity.player.random;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;

import java.util.List;
import java.util.Random;

public class RandomReinforceStrategy implements ReinforceStrategy {
    @Override
    public void execute(Map map, List<Country> countries) {
    	Random rand = new Random();
    	for(Country c: countries)
    	{    		
    		c.setArmy(c.getArmy() + rand.nextInt(11));
    	}
    }
}
