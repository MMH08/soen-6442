package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.util.List;

public class CheaterFortifyStrategy implements FortifyStrategy {
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
        return false;
    }
}
