package com.soen.risk.entity.player.aggressive;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class AggressiveAttackStrategy implements AttackStrategy {
    private List<Country> won;
    private HashMap<Country, Country> lost;
    private boolean isComplete;
    private int attackCounter;

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

            } else if (attackingCountry.getArmy() == 0) {
                logger.log(Level.INFO, "Attacking country lost all the army");
                AttackStrategy.exchangeArmy(defendingCountry, attackingCountry, attackCounter);
                lost.put(attackingCountry, defendingCountry);
                return; // exit the function
            }
        }
    }

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

    private int normalizeAttackDiceCount(Country attackingCountry) {
        if (attackingCountry.getArmy() >= 3) return 3;
        if (attackingCountry.getArmy() == 2) return 2;
        if (attackingCountry.getArmy() == 1) return 1;
        return 0;
    }

    private int normalizeDefendingDiceCount(Country defendingCountry, Country attackingCountry) {
        if (defendingCountry.getArmy() >= 2) return 2;
        if (defendingCountry.getArmy() == 1) return 1;
        if (attackingCountry.getArmy() == 1) return 1;
        return 0;
    }

    private ArrayList<Boolean> playGame(Country attackingCountry, Country defendingCountry) {
        logger.log(Level.INFO, "Attack by aggressive : " + attackingCountry + " on " + defendingCountry);
        int attackingDiceCount = normalizeAttackDiceCount(attackingCountry);
        int defendingDiceCount = normalizeDefendingDiceCount(defendingCountry, attackingCountry);
        attackCounter = attackCounter + defendingDiceCount;

        return AttackStrategy.simulateDiceRoll(attackingDiceCount, defendingDiceCount);

    }

    public List<Country> getWon() {
        return won;
    }


    @Override
    public boolean isComplete() {
        return isComplete;
    }

    public HashMap<Country, Country> getLost() {
        return lost;
    }
    
    public int getAttackCounter(){
    	return attackCounter;
    }

}
