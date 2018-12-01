package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

/**
 * The Class CheaterReinforceStrategy.
 *
 * @author Hina
 */
public class CheaterReinforceStrategy implements ReinforceStrategy, Serializable {

    /* (non-Javadoc)
     * @see com.soen.risk.entity.ReinforceStrategy#execute(com.soen.risk.entity.Map, java.util.List)
     */
    @Override
    public void execute(Map map, List<Country> countries) {
        for (Country c : countries) {
            logger.log(Level.INFO, "Cheater doubles the army in " + c + " from " + c.getArmy() + " to " + 2 * c.getArmy());
            c.setArmy(2 * c.getArmy());
        }
    }
}
