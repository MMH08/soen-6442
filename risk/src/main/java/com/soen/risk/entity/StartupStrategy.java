package com.soen.risk.entity;

public interface StartupStrategy {
    int execute(Country country, int armyCapacity);
}
