package com.soen.risk.entity;

import java.util.List;

public interface FortifyStrategy {
    boolean execute(Map map, List<Country> allowedCountries);
}
