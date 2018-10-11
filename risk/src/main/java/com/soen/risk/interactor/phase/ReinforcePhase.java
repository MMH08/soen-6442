package com.soen.risk.interactor.phase;

import com.soen.risk.interactor.Phase;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;
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
        Player p = GamePlay.getInstance().getCurrentPlayer();
        int i=0;
        for(Country c: p.getCountries())
        {
        	c.setArmy(c.getArmy() + this.countryArmyCollection.get(i));
        	i++;
        }
        
    }

    @Override
    public void exit() {
    	GamePlay.getInstance().updateCurrentPhase();
    }

    @Override
    public String getName() {
        return null;
    }
}
