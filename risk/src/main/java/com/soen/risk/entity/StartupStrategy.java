package com.soen.risk.entity;

public interface StartupStrategy {

    /**
     * @param country in which army has to be assigned
     * @param armyCapacity total army count available
     * @return is the consumed army count
     */
    int execute(Country country, int armyCapacity);
}
