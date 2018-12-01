package com.soen.risk.entity.player.human;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class HumanAttackStrategy.
 *
 * @author Amit, varun
 */
public class HumanAttackStrategy implements AttackStrategy, Serializable {

    /**
     * The logger.
     */
    private static Logger logger = Logger.getLogger(HumanAttackStrategy.class.getName());

    /**
     * The attacking country.
     */
    private Country attackingCountry;

    /**
     * The defending country.
     */
    private Country defendingCountry;

    /**
     * The attacking dice count.
     */
    private int attackingDiceCount;

    /**
     * The defending dice count.
     */
    private int defendingDiceCount;

    /**
     * The all out mode.
     */
    private int allOutMode;

    /**
     * The attack counter.
     */
    private int attackCounter;

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
     * The skip attack.
     */
    private int skipAttack;

    /**
     * Instantiates a new human attack strategy.
     *
     * @param attackingCountry   the attacking country
     * @param defendingCountry   the defending country
     * @param attackingDiceCount the attacking dice count
     * @param defendingDiceCount the defending dice count
     * @param skipAttack         the skip attack
     * @param allOutMode         the all out mode
     */
    public HumanAttackStrategy(Country attackingCountry, Country defendingCountry, int attackingDiceCount,
                               int defendingDiceCount, int skipAttack, int allOutMode) {
        this.attackingCountry = attackingCountry;
        this.defendingCountry = defendingCountry;
        this.attackingDiceCount = attackingDiceCount;
        this.defendingDiceCount = defendingDiceCount;
        this.allOutMode = allOutMode;
        this.attackCounter = 0;
        this.skipAttack = skipAttack;
        this.won = new ArrayList<>();
        this.lost = new HashMap<>();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.entity.AttackStrategy#execute(com.soen.risk.entity.Map, java.util.List)
     */
    @Override
    public void execute(Map map, List<Country> countries) {
        if (this.skipAttack == 1) {
            isComplete = true;
            return;
        }
        if (allOutMode == 1) {
            logger.log(Level.INFO, "Entered all out mode ... ");
            while (!(attackingCountry.getArmy() == 0 || defendingCountry.getArmy() == 0)) {
                executeOneAttackPhase();
            }
        } else {
            executeOneAttackPhase();
        }

        if (attackingCountry.getArmy() == 0) {
            logger.log(Level.INFO, "Attacking country lost all the army");
            AttackStrategy.exchangeArmy(defendingCountry, attackingCountry, attackCounter);
            lost.put(attackingCountry, defendingCountry);
        } else if (defendingCountry.getArmy() == 0) {
            logger.log(Level.INFO, "Defending country lost all the army");
            AttackStrategy.exchangeArmy(attackingCountry, defendingCountry, attackCounter);
            won.add(defendingCountry);
        }
    }

    /**
     * Execute one attack phase.
     */
    private void executeOneAttackPhase() {
        logger.log(Level.INFO, "Entered one attack phase ...");
        ArrayList<Boolean> wins = attackPlay();
        //logger.log(Level.INFO, "set attack attackCounter : " + this.getAttackCounter());
        for (boolean win : wins) {
            if (win) {
                logger.log(Level.INFO, "Attacker won 1 army.");
                attackingCountry.setArmy(attackingCountry.getArmy() + 1);
                defendingCountry.setArmy(defendingCountry.getArmy() - 1);
            } else {
                logger.log(Level.INFO, "Defender won 1 army.");
                attackingCountry.setArmy(attackingCountry.getArmy() - 1);
                defendingCountry.setArmy(defendingCountry.getArmy() + 1);
            }
        }
    }

    /**
     * Normalize attack dice count.
     *
     * @return the int
     */
    private int normalizeAttackDiceCount() {
        if (attackingCountry.getArmy() >= 3) {
            if (attackingDiceCount == 0) return 3;
            if (attackingDiceCount <= 3) return attackingDiceCount; // can be 1,2,3
            return 3; // normalized to 3
        }
        if (attackingCountry.getArmy() == 2) {
            if (attackingDiceCount == 0) return 2;
            if (attackingDiceCount <= 2) return attackingDiceCount; // can be 1,2
            return 2; // normalized to 2
        }
        if (attackingCountry.getArmy() == 1) return 1; // allowed only 1
        return attackingDiceCount;
    }

    /**
     * Normalize defending dice count.
     *
     * @return the int
     */
    private int normalizeDefendingDiceCount() {
        if (defendingCountry.getArmy() >= 2) {
            if (defendingDiceCount == 0) return 2;
            if (defendingDiceCount <= 2) return defendingDiceCount; // can be 1, 2
            return 2; // normalised to 2
        }
        if (defendingCountry.getArmy() == 1) return 1; // allowed value only 1
        if (attackingCountry.getArmy() == 1) return 1; // allowed value only 1
        return defendingDiceCount;
    }

    /**
     * Attack play.
     *
     * @return the array list
     */
    private ArrayList<Boolean> attackPlay() {
        attackingDiceCount = normalizeAttackDiceCount();
        defendingDiceCount = normalizeDefendingDiceCount();
        attackCounter = attackCounter + defendingDiceCount; //revisit this code

        return AttackStrategy.simulateDiceRoll(attackingDiceCount, defendingDiceCount);

    }

    /* (non-Javadoc)
     * @see com.soen.risk.entity.AttackStrategy#getWon()
     */
    @Override
    public List<Country> getWon() {
        return won;
    }

    /* (non-Javadoc)
     * @see com.soen.risk.entity.AttackStrategy#getLost()
     */
    @Override
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

    /**
     * Gets the attack counter.
     *
     * @return the attack counter
     */
    public int getAttackCounter() {
        return attackCounter;
    }

}
