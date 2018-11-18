package com.soen.risk.entity;

import java.util.List;

public interface AttackStrategy {
    void execute(Map map, List<Country> countries);
    int getAttackCounter();
    List<Country> getWon();
    List<Country> getLost();
}
