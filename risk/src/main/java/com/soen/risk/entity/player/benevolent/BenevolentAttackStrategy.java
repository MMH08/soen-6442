package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.util.List;

public class BenevolentAttackStrategy implements AttackStrategy {
    private List<Country> won;
    private List<Country> lost;

    @Override
    public void execute(Map map, List<Country> countries) {

    }

    @Override
    public int getAttackCounter() {
        return 0;
    }

    public List<Country> getWon() {
        return won;
    }


    public List<Country> getLost() {
        return lost;
    }
}
