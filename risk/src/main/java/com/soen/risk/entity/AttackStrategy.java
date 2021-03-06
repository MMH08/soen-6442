package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Interface AttackStrategy.
 * @author varun
 */
public interface AttackStrategy {

    /**
     * Execute.
     *
     * @param map the map
     * @param countries the countries
     */
    void execute(Map map, List<Country> countries);

    /**
     * Gets the won.
     *
     * @return won country against other country
     */
    List<Country> getWon();

    /**
     * Gets the lost.
     *
     * @return lost country against other country
     */
    HashMap<Country, Country> getLost();

    /**
     * Checks if is complete.
     *
     * @return true, if is complete
     */
    boolean isComplete();

    /** The logger. */
    Logger logger = Logger.getLogger(AttackStrategy.class.getName());

    /**
     * Exchange army.
     *
     * @param from the from
     * @param to the to
     * @param attackCounter the attack counter
     */
    static void exchangeArmy(Country from, Country to, int attackCounter) {
        if (from.getArmy() <= attackCounter) {
            logger.log(Level.INFO, "Exchange : From country has less army than attackCounter " + attackCounter + ", from" + from + " to " + to);
            logger.log(Level.INFO, "Exchange :  Moving " + (from.getArmy() -1) + " army from " + from + " to " + to);
            to.setArmy(from.getArmy() - 1);
            from.setArmy(1);
        } else {
            logger.log(Level.INFO, "Exchange : Moving " + attackCounter + " army from " + from + " to " + to);
            to.setArmy(attackCounter);
            from.setArmy(from.getArmy() - attackCounter);
        }
    }

    /**
     * Simulate dice roll.
     *
     * @param attackingDiceCount the attacking dice count
     * @param defendingDiceCount the defending dice count
     * @return the array list of wins
     */
    static ArrayList<Boolean> simulateDiceRoll(int attackingDiceCount, int defendingDiceCount) {
        logger.log(Level.INFO, "Attacking dice count " + attackingDiceCount + ", defending dice count " + defendingDiceCount);
        ArrayList<Boolean> win = new ArrayList<>();
        int[] dicI1 = new int[attackingDiceCount];
        int[] dicI2 = new int[defendingDiceCount];

        if (defendingDiceCount <= 0 || attackingDiceCount <= 0) {
            return win;
        }


        for (int i = 0; i < dicI1.length; i++) {
            dicI1[i] = 1 + (int) (9.0 * Math.random());
        }

        for (int i = 0; i < dicI2.length; i++) {
            dicI2[i] = 1 + (int) (9.0 * Math.random());
        }
        Arrays.sort(dicI1);
        Arrays.sort(dicI2);

        if (dicI1.length == 1) {
            if (dicI1[0] >= dicI2[0]) {
                win.add(true);
            } else {
                win.add(false);
            }
        } else if (dicI1.length == 2) {
            if (dicI2.length == 1) {
                if (dicI1[1] >= dicI2[0]) {
                    win.add(true);
                } else {
                    win.add(false);
                }
            } else if (dicI2.length == 2) {
                if (dicI1[1] >= dicI2[1]) {
                    win.add(true);
                } else {
                    win.add(false);
                }
                if (dicI1[0] >= dicI2[0]) {
                    win.add(true);
                } else {
                    win.add(false);
                }
            }
        } else if (dicI1.length == 3) {
            if (dicI2.length == 1) {
                if (dicI1[2] >= dicI2[0]) {
                    win.add(true);
                } else {
                    win.add(false);
                }
            } else if (dicI2.length == 2) {
                if (dicI1[2] >= dicI2[1]) {
                    win.add(true);
                } else {
                    win.add(false);
                }
                if (dicI1[1] >= dicI2[0]) {
                    win.add(true);
                } else {
                    win.add(false);
                }
            }
        }
        return win;
    }
}
