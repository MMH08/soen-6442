package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.util.ArrayList;
import java.util.List;

public class CheaterAttackStrategy implements AttackStrategy {
    private List<Country> won;
    private List<Country> lost;

    @Override
    public void execute(Map map, List<Country> countries) {
        won = new ArrayList<>();
        lost = new ArrayList<>();
        for (Country c : countries) {
            List<Country> ll = map.getNeighbouringCountry(c);
            List<Country> coun = new ArrayList<>();
            for (int i = 1; i < ll.size(); i++) {
                if (checkNeighbouringCountry(countries, ll.get(i))) {
                    checkDuplicacy(ll.get(i));
                }
            }
        }
    }

    private boolean checkNeighbouringCountry(List<Country> countries, Country c) {
        for (Country c1 : countries) {
            if (c1.getName().equals(c.getName())) {
                return false;
            }
        }
        return true;
    }

    private void checkDuplicacy(Country c) {
        if (won.size() == 0) {
            won.add(c);
        } else {
            for (Country c1 : won) {
                if (c1.getName().equals(c.getName())) {
                    return;
                }
            }
            won.add(c);
        }

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
