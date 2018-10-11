package com.soen.risk.interactor.phase;

import com.soen.risk.interactor.Phase;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.interactor.GamePlay;

import java.util.ArrayList;
import java.util.HashMap;

public class ReinforcePhase implements Phase {

    private String name;
    private HashMap<String, Integer> countryArmyCollection;

    public ReinforcePhase(ArrayList<Integer> armyCounts) {
        this.countryArmyCollection = countryArmyCollection;
        this.name = "reinforcePhase";
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void begin() {

    }

    @Override
    public void execute() {
        Map m = GamePlay.getInstance().getGame().getMap();
        countryArmyCollection.forEach((key, value) -> {
                    for (Country c : m.getCountries()) {
                        if (c.getName().equals(key)) {
                            c.setArmy(c.getArmy() + value);
                            break;
                        }
                    }
                }
        );
    }

    @Override
    public void exit() {

    }

    @Override
    public String getName() {
        return null;
    }
}
