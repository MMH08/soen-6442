package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BenevolentReinforceStrategy implements ReinforceStrategy {
    @Override
    public void execute(List<Country> countries) {
    	Country con_max =  Collections.max(countries, Comparator.comparing(s1 -> s1.getArmy()));    
    	Country con_min =  Collections.min(countries, Comparator.comparing(s -> s.getArmy()));       
    	con_min.setArmy(con_max.getArmy());
    }
}
