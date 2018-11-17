package com.soen.risk.entity.player.random;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;

import java.util.List;

public class RandomFortifyStrategy implements FortifyStrategy {
    @Override
    public boolean execute(Map map, List<Country> allowedCountries) {
        return false;
    }
}
