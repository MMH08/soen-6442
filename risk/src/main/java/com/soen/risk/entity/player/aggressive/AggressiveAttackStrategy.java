package com.soen.risk.entity.player.aggressive;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

/**
 * The Class AggressiveAttackStrategy.
 *
 * @author Amit
 */
public class AggressiveAttackStrategy implements AttackStrategy, Serializable {

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
     * The attack counter.
     */
    private int attackCounter;

    /**
     * Instantiates a new aggressive attack strategy.
     */
    public AggressiveAttackStrategy() {
        this.won = new ArrayList<>();
        this.lost = new HashMap<>();
        this.isComplete = true;
    }

    /**
     * Aggressive player will keep on attacking till the time one thing happens:
     * 1. either he lost the attacking country
     * 2. or it runs out of options to attack, i.e., for loop runs completely.
     *
     * @param map       Map
     * @param countries list of countries
     */
    @Override
    public void execute(Map map, List<Country> countries) {
        Country attackingCountry = this.findStrongestCountry(countries);
        List<Country> defendingCountries = this.findDefendingCountries(map, attackingCountry, countries);

        for (Country defendingCountry : defendingCountries) {
            boolean continueAttack = true;
            while (continueAttack) {
                if(attackCounter > 100) continueAttack = false;
                ArrayList<Boolean> wins = this.playGame(attackingCountry, defendingCountry);
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
                    attackCounter = 0; // reset counter for future fights. ownership will be changed once and for all.
                    won.add(defendingCountry);
                    continueAttack = false;

                } else if (attackingCountry.getArmy() == 0) {
                    logger.log(Level.INFO, "Attacking country lost all the army");
                    AttackStrategy.exchangeArmy(defendingCountry, attackingCountry, attackCounter);
                    lost.put(attackingCountry, defendingCountry);
                    return; // exit the function
                }
            }
        }
    }

    /**
     * Find strongest country.
     *
     * @param countries the countries
     * @return the country
     */
    private Country findStrongestCountry(List<Country> countries) {
        int max = Integer.MIN_VALUE;
        Country maxCountry = null;
        for (Country c : countries) {
            if (c.getArmy() > max) {
                max = c.getArmy();
                maxCountry = c;
            }
        }
        return maxCountry;
    }

    /**
     * Find defending countries.
     *
     * @param map              the map
     * @param attackingCountry the attacking country
     * @param countries        the countries
     * @return the list
     */
    private List<Country> findDefendingCountries(Map map, Country attackingCountry, List<Country> countries) {
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
     * @param attackingCountry the attacking country
     * @return the int
     */
    private int normalizeAttackDiceCount(Country attackingCountry) {
        if (attackingCountry.getArmy() >= 3) return 3;
        if (attackingCountry.getArmy() == 2) return 2;
        if (attackingCountry.getArmy() == 1) return 1;
        return 0;
    }

    /**
     * Normalize defending dice count.
     *
     * @param defendingCountry the defending country
     * @param attackingCountry the attacking country
     * @return the int
     */
    private int normalizeDefendingDiceCount(Country defendingCountry, Country attackingCountry) {
        if (defendingCountry.getArmy() >= 2) return 2;
        if (defendingCountry.getArmy() == 1) return 1;
        if (attackingCountry.getArmy() == 1) return 1;
        return 0;
    }

    /**
     * Play game.
     *
     * @param attackingCountry the attacking country
     * @param defendingCountry the defending country
     * @return the array list
     */
    private ArrayList<Boolean> playGame(Country attackingCountry, Country defendingCountry) {
        logger.log(Level.INFO, "Attack by aggressive : " + attackingCountry + " on " + defendingCountry);
        int attackingDiceCount = normalizeAttackDiceCount(attackingCountry);
        int defendingDiceCount = normalizeDefendingDiceCount(defendingCountry, attackingCountry);
        attackCounter = attackCounter + defendingDiceCount;

        return AttackStrategy.simulateDiceRoll(attackingDiceCount, defendingDiceCount);

    }

    /* (non-Javadoc)
     * @see com.soen.risk.entity.AttackStrategy#getWon()
     */
    public List<Country> getWon() {
        return won;
    }


    /* (non-Javadoc)
     * @see com.soen.risk.entity.AttackStrategy#isComplete()
     */
    @Override
    public boolean isComplete() {
        return isComplete;
    }

    /* (non-Javadoc)
     * @see com.soen.risk.entity.AttackStrategy#getLost()
     */
    public HashMap<Country, Country> getLost() {
        return lost;
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
