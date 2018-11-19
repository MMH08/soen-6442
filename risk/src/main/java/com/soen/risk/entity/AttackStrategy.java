package com.soen.risk.entity;

import com.soen.risk.boundary.Response;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

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

    static Logger logger = Logger.getLogger(AttackStrategy.class.getName());
}
