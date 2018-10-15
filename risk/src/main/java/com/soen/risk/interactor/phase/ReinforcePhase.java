package com.soen.risk.interactor.phase;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.Phase;

import java.util.ArrayList;
import java.util.logging.Level;

public class ReinforcePhase implements Phase {

    private String name;
    private ArrayList<Integer> armyCounts;

    public ReinforcePhase(ArrayList<Integer> armyCounts) {
        this.armyCounts = armyCounts;
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
        int i = 0;
        for (Country c : p.getCountries()) {
            logger.log(Level.INFO, "Adding reinforce army to country " + c.getName() + " " + this.armyCounts.get(i));
            c.setArmy(c.getArmy() + this.armyCounts.get(i));
            i++;
        }

    }

    @Override
    public void exit() {
        logger.log(Level.INFO, "in exit phase... ");
        GamePlay.getInstance().updateCurrentPhase();
    }

    @Override
    public String getName() {
        return this.name;
    }
}
