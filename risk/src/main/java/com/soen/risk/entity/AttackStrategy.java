package com.soen.risk.entity;

import java.util.HashMap;
import java.util.List;

public interface AttackStrategy {
    void execute(Map map, List<Country> countries);

    /**
     * @return won country against other country
     */
    List<Country> getWon();

    /**
     * @return lost country against other country
     */
    HashMap<Country, Country> getLost();

    boolean isComplete();
}
