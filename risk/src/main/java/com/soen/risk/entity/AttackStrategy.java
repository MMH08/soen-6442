package com.soen.risk.entity;

import java.util.List;

public interface AttackStrategy {
    void execute(List<Country> countries, Map map);
    int getAttackCounter();
    List<Country> getWon();
    List<Country> getLost();
}
