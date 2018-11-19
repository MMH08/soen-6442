package com.soen.risk.entity.player.human;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HumanAttackStrategy implements AttackStrategy {
    private static Logger logger = Logger.getLogger(HumanAttackStrategy.class.getName());

    private Country attackingCountry;
    private Country defendingCountry;
    private int attackingDiceCount;
    private int defendingDiceCount;
    private int allOutMode;
    private int attackCounter;
    private List<Country> won;
    private HashMap<Country, Country> lost;
    private boolean isComplete;
    private int skipAttack;

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
            exchangeArmy(defendingCountry, attackingCountry);
            lost.put(attackingCountry, defendingCountry);
        } else if (defendingCountry.getArmy() == 0) {
            logger.log(Level.INFO, "Defending country lost all the army");
            exchangeArmy(attackingCountry, defendingCountry);
            won.add(defendingCountry);
        }
    }

    private void exchangeArmy(Country from, Country to) {
        if (from.getArmy() <= attackCounter) {
            to.setArmy(from.getArmy() - 1);
            from.setArmy(1);
        } else {
            to.setArmy(attackCounter);
            from.setArmy(from.getArmy() - attackCounter);
        }
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

    private ArrayList<Boolean> attackPlay() {
        ArrayList<Boolean> win = new ArrayList<>();

        attackingDiceCount = normalizeAttackDiceCount();
        defendingDiceCount = normalizeDefendingDiceCount();

        if (defendingDiceCount <= 0 || attackingDiceCount <= 0) {
            return win;
        }

        int dicI1[] = new int[attackingDiceCount];
        int dicI2[] = new int[defendingDiceCount];
        attackCounter = attackCounter + defendingDiceCount; //revisit this code


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

    @Override
    public List<Country> getWon() {
        return won;
    }

    @Override
    public HashMap<Country, Country> getLost() {
        return lost;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

}
