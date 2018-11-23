package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.util.ArrayList;
import java.util.List;

public class CheaterFortifyStrategy implements FortifyStrategy {
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
    	for(Country c: allowedCountries)
    	{
    		List<Country> ll = map.getNeighbouringCountry(c);
    		for(int i=1;i<ll.size();i++)
    		{
    			if(checkNeighbouringCountry(allowedCountries,ll.get(i)))
    			{
    				c.setArmy(c.getArmy()*2);
    				break;
    			}
    		}    		
    	}
    	return true;
    }
    private boolean checkNeighbouringCountry(List<Country> countries, Country c)
    {
    	for(Country c1: countries)
    	{
    		if(c1.getName().equals(c.getName()))
    		{
    			return false;
    		}
    	}
    	return true;
    }
}
