package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BenevolentAttackStrategy implements AttackStrategy {
    private List<Country> won;
    private HashMap<Country, Country> lost;
    private boolean isComplete;

    public BenevolentAttackStrategy(){
        this.won = new ArrayList<>();
        this.lost = new HashMap<>();
        this.isComplete = true;
    }

    @Override
    public void execute(Map map, List<Country> countries) {

    }

    public List<Country> getWon() {
        return won;
    }


    public HashMap<Country, Country> getLost() {
        return lost;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }
}
