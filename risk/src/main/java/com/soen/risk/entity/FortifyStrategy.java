package com.soen.risk.entity;

import java.util.List;
import java.util.logging.Logger;

public interface FortifyStrategy {
    boolean execute(Map map, List<Country> allowedCountries);
    Logger logger = Logger.getLogger(FortifyStrategy.class.getName());
}
