package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

/**
 * The Class CheaterAttackStrategy.
 *
 * @author Hina
 */
public class CheaterAttackStrategy implements AttackStrategy, Serializable {


    /**
     * The won.
     */
    private List<Country> won;

    /**
     * The lost.
     */
    private HashMap<Country, Country> lost;

    /**
     * The is complete.
     */
    private boolean isComplete;

    /**
     * Instantiates a new cheater attack strategy.
     */
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

    /* (non-Javadoc)
     * @see com.soen.risk.entity.AttackStrategy#getWon()
     */
    public List<Country> getWon() {
        return won;
    }


    /* (non-Javadoc)
     * @see com.soen.risk.entity.AttackStrategy#getLost()
     */
    public HashMap<Country, Country> getLost() {
        return lost;
    }

    /* (non-Javadoc)
     * @see com.soen.risk.entity.AttackStrategy#isComplete()
     */
    @Override
    public boolean isComplete() {
        return isComplete;
    }

}
