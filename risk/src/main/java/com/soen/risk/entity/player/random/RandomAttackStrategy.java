package com.soen.risk.entity.player.random;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RandomAttackStrategy implements AttackStrategy {
    private List<Country> won;
    private HashMap<Country, Country> lost;
    private boolean isComplete;
    public RandomAttackStrategy() {
        this.won = new ArrayList<>();
        this.lost = new HashMap<>();
        this.isComplete = true;
    }
    private List<Country> attackedCountry(Map map, Country attackingCountry, List<Country> countries)
    {
    	List<Country> ll = map.getNeighbouringCountry(attackingCountry);
    	List<Country> attackedCountry = new ArrayList<Country>();
    	for(Country c: ll) {    		
    			int flag = 0;
		    	for(Country checkCountry: countries)
		    	{
		    		if(c.getName().equals(checkCountry.getName()))
		    		{
		    			flag  = 1;
		    			break;
		    		}
		    	}
		    	if(flag == 0)
		    	{
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
    private ArrayList<Boolean> playGame(Country attackingCountry, Country attackedCountry)
    {
        ArrayList<Boolean> win = new ArrayList<>();

        int attackingDiceCount = normalizeAttackDiceCount(attackingCountry);
        int defendingDiceCount = normalizeDefendingDiceCount(attackedCountry, attackingCountry);

        if (defendingDiceCount <= 0 || attackingDiceCount <= 0) {
            return win;
        }

        int dicI1[] = new int[attackingDiceCount];
        int dicI2[] = new int[defendingDiceCount];


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
    public void execute(Map map, List<Country> countries) {
    	Country attackingCountry = countries.get(countries.size()/2);
    	List<Country> ListAttackedCountry = this.attackedCountry(map, attackingCountry, countries);
    	Country attackedCountry = ListAttackedCountry.get(ListAttackedCountry.size()/2);
    	int flag = 0;
    	int j = 0;
    	Random rand = new Random();
    	int randomNumber = 1+rand.nextInt(3);
    	while(flag !=1 || j<randomNumber)
    	{
    		ArrayList<Boolean> b = new ArrayList<Boolean>();
    		b = this.playGame(attackingCountry,attackedCountry);
    		for(int i=0;i<b.size();i++)
    		{
    			if(b.get(i) == true)
    			{
    				attackingCountry.setArmy(attackingCountry.getArmy()+1);
    				attackedCountry.setArmy(attackedCountry.getArmy()-1);
    			}
    			else
    			{
    				attackingCountry.setArmy(attackingCountry.getArmy()-1);
    				attackedCountry.setArmy(attackedCountry.getArmy()+1);
    			}
    		}
    		if(attackedCountry.getArmy() == 0)
    		{
    			won.add(attackingCountry);
    			lost.put(attackedCountry,attackingCountry);
    			flag=1;
    		}
    		else if(attackingCountry.getArmy() == 0)
    		{
    			won.add(attackedCountry);
    			lost.put(attackingCountry,attackedCountry);
    			flag=1;
    		}
    		j++;
    	}
    }

    public List<Country> getWon() {
        return won;
    }


    public HashMap<Country, Country> getLost() {
        return lost;
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }
}
