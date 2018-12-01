package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Class BenevolentAttackStrategy.
 *
 * @author Nivetha
 */
public class BenevolentAttackStrategy implements AttackStrategy, Serializable {

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
     * Instantiates a new benevolent attack strategy.
     */
    public BenevolentAttackStrategy() {
        this.won = new ArrayList<>();
        this.lost = new HashMap<>();
        this.isComplete = true;
    }

    /* (non-Javadoc)
     * @see com.soen.risk.entity.AttackStrategy#execute(com.soen.risk.entity.Map, java.util.List)
     */
    @Override
    public void execute(Map map, List<Country> countries) {
        //pass
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
