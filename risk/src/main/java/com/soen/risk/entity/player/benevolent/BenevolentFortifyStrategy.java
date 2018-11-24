package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BenevolentFortifyStrategy implements FortifyStrategy {
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
        //todo : first validate if strong - weak country are connected, if not go for
        // second stronger country.
        // sort the allowed countries
    	Country con_max =  Collections.max(allowedCountries, Comparator.comparing(s1 -> s1.getArmy()));
    	Country con_min =  Collections.min(allowedCountries, Comparator.comparing(s -> s.getArmy()));
    	con_max.setArmy(con_max.getArmy() - 1);
    	con_min.setArmy(con_min.getArmy() + 1);
        return true;
    }
}
