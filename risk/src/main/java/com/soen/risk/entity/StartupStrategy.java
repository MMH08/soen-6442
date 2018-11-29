package com.soen.risk.entity;

import java.util.logging.Logger;

public interface StartupStrategy {

    /**
     * @param country in which army has to be assigned
     * @param armyCapacity total army count available
     * @return is the consumed army count
     */
    int execute(Country country, int armyCapacity);
    Logger logger = Logger.getLogger(StartupStrategy.class.getName());
}
