package com.soen.risk.entity.player.human;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HumanAttackStrategy implements AttackStrategy {
    private static Logger logger = Logger.getLogger(HumanAttackStrategy.class.getName());

    private Country attackingCountry;
    private Country defendingCountry;
    private int attackingDiceCount;
    private int defendingDiceCount;
    private int allOutMode;
    private int counter;

    public HumanAttackStrategy(Country attackingCountry, Country defendingCountry, int attackingDiceCount,
                               int defendingDiceCount, int allOutMode) {
        this.attackingCountry = attackingCountry;
        this.defendingCountry = defendingCountry;
        this.attackingDiceCount = attackingDiceCount;
        this.defendingDiceCount = defendingDiceCount;
        this.allOutMode = allOutMode;
        this.counter = 0;
    }

    @Override
    public void execute() {
        if (allOutMode == 1) {
            logger.log(Level.INFO, "Entered all out mode ... ");
            while (!(attackingCountry.getArmy() == 0 || defendingCountry.getArmy() == 0)) {
                executeOneAttackPhase();
            }
        } else {
            executeOneAttackPhase();
        }
    }

    @Override
    public int getAttackCounter() {
        return this.counter;
    }

    private int normalizeAttackDiceCount() {
        if (attackingCountry.getArmy() >= 3) return 3;
        if (attackingCountry.getArmy() == 2) return 2;
        if (attackingCountry.getArmy() == 1) return 1;
        return attackingDiceCount;
    }

    private int normalizeDefendingDiceCount() {
        if (defendingCountry.getArmy() >= 2) return 2;
        if (defendingCountry.getArmy() == 1) return 1;
        if (attackingCountry.getArmy() == 1) return 1;
        return defendingDiceCount;
    }

    private void executeOneAttackPhase() {
        logger.log(Level.INFO, "Entered one attack phase ...");
        ArrayList<Boolean> wins = attackPlay();
        //logger.log(Level.INFO, "set attack counter : " + this.getAttackCounter());
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

    private ArrayList<Boolean> attackPlay() {
        ArrayList<Boolean> win = new ArrayList<>();

        attackingDiceCount = normalizeAttackDiceCount();
        defendingDiceCount = normalizeDefendingDiceCount();

        if (defendingDiceCount <= 0 || attackingDiceCount <= 0) {
            return win;
        }

        int dicI1[] = new int[attackingDiceCount];
        int dicI2[] = new int[defendingDiceCount];
        counter = counter + defendingDiceCount; //revisit this code


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
