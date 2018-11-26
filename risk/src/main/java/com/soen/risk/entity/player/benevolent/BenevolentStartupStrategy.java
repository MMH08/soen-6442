package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

public class BenevolentStartupStrategy implements StartupStrategy {
	 
    @Override
    public int execute(Country country, int armyCapacity) {
    	country.setArmy(country.getArmy() + 4);
        return  armyCapacity; 
    }
}
