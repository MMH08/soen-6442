package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class CheaterAttackStrategy implements AttackStrategy, Serializable {


    private List<Country> won;
    private HashMap<Country, Country> lost;
    private boolean isComplete;

    public CheaterAttackStrategy() {
        won = new ArrayList<>();
        lost = new HashMap<>();
        isComplete = true;

    }

    /**
     * Automatically conquer the neighbouring countries of all of its countries.
     *
     * @param map       Map
     * @param countries countries owned by the player
     */
    @Override
    public void execute(Map map, List<Country> countries) {
        for (Country c : countries) {
            List<Country> neighbours = map.getNeighbouringCountry(c);
            logger.log(Level.INFO, "Neighbours of " + c + " are :" + neighbours);
            for (Country neighbour : neighbours) {
                if (!countries.contains(neighbour) && !won.contains(neighbour)) {
                    logger.log(Level.INFO, "Adding country to won list " + neighbour);
                    won.add(neighbour);
                }
            }
        }
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
