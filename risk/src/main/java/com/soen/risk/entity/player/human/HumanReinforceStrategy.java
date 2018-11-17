package com.soen.risk.entity.player.human;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.ReinforceStrategy;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HumanReinforceStrategy implements ReinforceStrategy {
    private List<Integer> armyCounts;
    private static Logger logger = Logger.getLogger(HumanReinforceStrategy.class.getName());


    public HumanReinforceStrategy(List<Integer> armyCounts) {
        this.armyCounts = armyCounts;
    }

    @Override
    public void execute(List<Country> countries) {
        int i = 0;
        for (Country c : countries) {
            logger.log(Level.INFO, "Adding reinforce army to country " + c.getName() + ", army count " + armyCounts.get(i));
            c.addArmy(armyCounts.get(i)); // new army count of the country
            i++;
        }
    }
}
