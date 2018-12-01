package com.soen.risk.entity.player.random;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

/**
 * The Class RandomAttackStrategy.
 *
 * @author Manmeet, varun
 */
public class RandomAttackStrategy implements AttackStrategy, Serializable {

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
     * The attacking country.
     */
    private Country attackingCountry;

    /**
     * The defending country.
     */
    private Country defendingCountry;

    /**
     * Instantiates a new random attack strategy.
     */
    public RandomAttackStrategy() {
        this.won = new ArrayList<>();
        this.lost = new HashMap<>();
        this.isComplete = true;
        this.attackCounter = 0;
    }

    /**
     * Attacked country.
     *
     * @param map       the map
     * @param countries the countries
     * @return the list
     */
    private List<Country> attackedCountry(Map map, List<Country> countries) {
        List<Country> ll = map.getNeighbouringCountry(attackingCountry);
        List<Country> attackedCountry = new ArrayList<Country>();
        for (Country c : ll) {
            int flag = 0;
            for (Country checkCountry : countries) {
                if (c.getName().equals(checkCountry.getName())) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                attackedCountry.add(c);
            }
        }
        return attackedCountry;
    }

    /**
     * Normalize attack dice count.
     *
     * @return the int
     */
    private int normalizeAttackDiceCount() {
        if (attackingCountry.getArmy() >= 3) return 3;
        if (attackingCountry.getArmy() == 2) return 2;
        if (attackingCountry.getArmy() == 1) return 1;
        return 0;
    }

    /**
     * Normalize defending dice count.
     *
     * @return the int
     */
    private int normalizeDefendingDiceCount() {
        if (defendingCountry.getArmy() >= 2) return 2;
        if (defendingCountry.getArmy() == 1) return 1;
        if (attackingCountry.getArmy() == 1) return 1;
        return 0;
    }

    /**
     * Play game.
     *
     * @return the array list
     */
    private ArrayList<Boolean> playGame() {

        int attackingDiceCount = normalizeAttackDiceCount();
        int defendingDiceCount = normalizeDefendingDiceCount();
        attackCounter = attackCounter + defendingDiceCount; //revisit this code
        return AttackStrategy.simulateDiceRoll(attackingDiceCount, defendingDiceCount);

    }

    /**
     * Random player - attack strategy
     * Will exit the attack if it wins over one country or loses its own country.
     * Changes: added attackCounter, corrected the lost and won assignment,
     *
     * @param map       Map
     * @param countries list of owned countries
     */
    @Override
    public void execute(Map map, List<Country> countries) {
        if (countries.isEmpty()) return;
        attackingCountry = countries.get(countries.size() / 2);
        List<Country> ListAttackedCountry = this.attackedCountry(map, countries);
        defendingCountry = ListAttackedCountry.get(ListAttackedCountry.size() / 2);
        logger.log(Level.INFO, "Attacking country : " + attackingCountry + ", defending : " + defendingCountry);

        int flag = 0;
        int j = 0;
        Random rand = new Random();
        int randomNumber = 1 + rand.nextInt(3);

        while (flag != 1 || j < randomNumber) {
            ArrayList<Boolean> wins = this.playGame();
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
            if (defendingCountry.getArmy() == 0) {
                logger.log(Level.INFO, "Defending country lost all the army");
                AttackStrategy.exchangeArmy(attackingCountry, defendingCountry, attackCounter);
                won.add(defendingCountry);
                flag = 1;
            } else if (attackingCountry.getArmy() == 0) {
                logger.log(Level.INFO, "Attacking country lost all the army");
                AttackStrategy.exchangeArmy(defendingCountry, attackingCountry, attackCounter);
                lost.put(attackingCountry, defendingCountry);
                flag = 1;
            }
            j++;
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

    /**
     * Gets the attack counter.
     *
     * @return the attack counter
     */
    public int getAttackCounter() {
        return attackCounter;
    }
}
