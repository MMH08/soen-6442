package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.StartupStrategy;

import java.io.Serializable;
import java.util.logging.Level;

public class CheaterStartupStrategy implements StartupStrategy, Serializable {
    @Override
    public int execute(Country country, int armyCapacity) {
        logger.log(Level.INFO, "Army count received to cheater " + armyCapacity);
        if(armyCapacity > 0){
            country.addArmy(1);
            return 1;
        }
        return armyCapacity;
    }
}
