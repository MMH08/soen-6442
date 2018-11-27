package com.soen.risk.entity.player.random;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RandomFortifyStrategy implements FortifyStrategy {
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
    	Country startCountry;
        Country endCountry;
        int armyCount = 0;
        int i = 1;
    	int strongestCountryindex = 0;
    	endCountry = allowedCountries.get(strongestCountryindex);
    	while(i < allowedCountries.size() ) {
    		startCountry = allowedCountries.get(i);
    		if (map.pathExists(startCountry, endCountry, allowedCountries)) {
    			if (startCountry.getArmy()>1 && startCountry.getArmy()%2 == 0) {
    				armyCount = startCountry.getArmy()/2 ;
    				startCountry.setArmy (startCountry.getArmy() - armyCount);
    				endCountry.setArmy (endCountry.getArmy() + armyCount);
    			}
    			else if (startCountry.getArmy()>1 && startCountry.getArmy()%2 == 1) {
    				armyCount = (startCountry.getArmy()-1)/2;
    				startCountry.setArmy (startCountry.getArmy() - armyCount);
    				endCountry.setArmy (endCountry.getArmy() + armyCount);
    			}
    			return true;
    		}
    		else {   	
    			i++;   	
    		}
    	}
    	return true; 
    }
}
