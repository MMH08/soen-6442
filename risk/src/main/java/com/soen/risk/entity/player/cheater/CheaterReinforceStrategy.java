package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.ReinforceStrategy;

import java.util.List;

public class CheaterReinforceStrategy implements ReinforceStrategy {
    @Override
    public void execute(List<Country> countries) {
    	for(Country c: countries)
    	{
    		c.setArmy(2*c.getArmy());
    	}
    }
}
