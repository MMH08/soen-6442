package com.soen.risk.entity;

import java.util.List;
import java.util.logging.Logger;

public interface FortifyStrategy {
    /**
     * Move army from one country to another, provided both the countries belong in the allowedCountries.
     * Also, map is required because, it is required to validate if there exists a path between the countries.
     *
     * @param map              Map
     * @param allowedCountries owned countries by the player
     * @return if fortification was possible
     */
    boolean execute(Map map, List<Country> allowedCountries);

    Logger logger = Logger.getLogger(FortifyStrategy.class.getName());
}
