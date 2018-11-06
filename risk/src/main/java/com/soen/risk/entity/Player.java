package com.soen.risk.entity;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is responsible to hold the name, army capacity remaining and list of countries owned by the
 * player instance. It is being observed by the domination view to calculate the share of each player.
 *
 * @author Amit Sachdeva, Varun Singhal
 * @version 2.0.0
 * @since 2018-10-10
 */

public class Player extends Observable {
    private String name;
    private int armyCapacity;
    private int extraArmies;
    private int exchangeCount = 0;
    private List<Country> countries;
    private List<String> cards;
    private int finalarmies;
    private static Logger logger = Logger.getLogger(Player.class.getName());
    private int Attackercounter = 0;

    /**
     * Initialise the player with given suffix and empty list of owned countries.
     *
     * @param nameSuffix name of player
     */
    public Player(int nameSuffix) {
        this.name = "Player_" + String.valueOf(nameSuffix);
        this.countries = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    /**
     * Add new country to countries object
     *
     * @param c country
     */
    public void addCountry(Country c) {
        this.countries.add(c);
        this.setChanged();
        this.notifyObservers(this);
    }

    /**
     * When a match will be lost then remove country will be called
     */
    public void removeCountry(Country c) {
        this.countries.remove(c);
        this.setChanged();
        this.notifyObservers(this);
    }

    /**
     * nextCountryToAssignArmy()
     *
     * @return Object of country to which army is assign
     */
    public String nextCountryToAssignArmy() {
        for (Country country : countries) {
            if (country.isEmpty())
                return country.getName();
        }
        return countries.get(countries.size() - 1).getName();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public void executeStartupPhase(Country country, int armyCount) {
        country.setArmy(country.getArmy() + armyCount);
        setArmyCapacity(getArmyCapacity() - armyCount);
    }

    public void executeReinforcePhase(ArrayList<Integer> armyCounts) {
        int i = 0;
        for (Country c : countries) {
            logger.log(Level.INFO, "Adding reinforce army to country " + c.getName() + ", army count " + armyCounts.get(i));
            c.setArmy(c.getArmy() + armyCounts.get(i)); // new army count of the country
            setArmyCapacity(armyCapacity - armyCounts.get(i)); // new army capacity left with player
            i++;
        }
    }

    private ArrayList<Boolean> attackPlay(int attackingDice, int attackedDice) {
        int dicI1[] = new int[attackingDice];
        int dicI2[] = new int[attackedDice];
        ArrayList<Boolean> win = new ArrayList<>();

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

    public void executeAttackPhase(Player defendingPlayer, Country attackingCountry, Country defendingCountry, int attackingDiceCount, int defendingDiceCount, int allOutMode) {
        if (allOutMode == 1) {
            logger.log(Level.INFO, "Entered all out mode ... ");
            while (!(attackingCountry.getArmy() == 0 || defendingCountry.getArmy() == 0)) {
                if (attackingCountry.getArmy() >= 3) {
                    attackingDiceCount = 3;
                    if (defendingCountry.getArmy() >= 2) {
                        defendingDiceCount = 2;
                    } else if (defendingCountry.getArmy() == 1) {
                        defendingDiceCount = 1;
                    }
                } else if (attackingCountry.getArmy() == 2) {
                    attackingDiceCount = 2;
                    if (defendingCountry.getArmy() >= 2) {
                        defendingDiceCount = 2;
                    } else if (defendingCountry.getArmy() == 1) {
                        defendingDiceCount = 1;
                    }
                } else if (attackingCountry.getArmy() == 1) {
                    attackingDiceCount = 1;
                    defendingDiceCount = 1;
                }

                executeOneAttackPhase(defendingPlayer, attackingCountry, defendingCountry, attackingDiceCount, defendingDiceCount);
            }
        } else {
            executeOneAttackPhase(defendingPlayer, attackingCountry, defendingCountry, attackingDiceCount, defendingDiceCount);
        }
    }

    private void executeOneAttackPhase(Player defendingPlayer, Country attackingCountry, Country defendingCountry, int attackingDiceCount, int attackedDiceCount) {
        logger.log(Level.INFO, "Entered one attack phase ...");
        ArrayList<Boolean> wins = this.attackPlay(attackingDiceCount, attackedDiceCount);
        this.setAttackCounter(this.getAttackCounter() + attackedDiceCount);
        logger.log(Level.INFO, "set attack counter : " + this.getAttackCounter());
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
        // refresh view
        logger.log(Level.INFO, "Refreshing the views ... ");
        this.setArmyCapacity(this.getArmyCapacity());
        defendingPlayer.setArmyCapacity(defendingPlayer.getArmyCapacity());
    }

    public void executeFortifyPhase(String startCountry, String endCountry, int armyCount) {
        Country country1 = findByCountryName(startCountry);
        Country country2 = findByCountryName(endCountry);
        if (country1.getArmy() <= armyCount) armyCount = country1.getArmy() - 1;
        country1.setArmy(country1.getArmy() - armyCount);
        country2.setArmy(country2.getArmy() + armyCount);
    }


    // -----------------------------------------------------------------------------------------------------------------

    /**
     * To allocate initial army we should know about the countries owned.
     */
    void allocateInitialArmy() {
        Random rand = new Random();
        this.setArmyCapacity(this.getCountries().size() * (2 + rand.nextInt(2)));
        logger.log(Level.INFO, "Adding army capacity to " + this.getName() + " " + this.getArmyCapacity());
    }

    // -------------------------------------------------------------

    /**
     * calculateReinforceCount(Map m): Checking if player has all countries of whole continent then give army according to
     * control value, otherwise give armies according to countries player own.
     *
     * @param m Reference of map class
     */

    public void calculateReinforceCount(Map m) {
        //Check if player has all country of a continent
        for (Continent ctt : m.getContinents()) {
            int size = ctt.getCountries().size();
            int count = 0;
            for (Country player_countries : this.getCountries()) {
                for (Country continent_countries : ctt.getCountries()) {
                    if (continent_countries.getName().equals(player_countries.getName())) {
                        count++;
                    }
                }
            }
            if (size == count) {
                armyCapacity = ctt.getControlValue();
            }

        }
        //If Player do not have all country of a continent
        int number_of_countries = this.getCountries().size();
        armyCapacity = Math.max(3, (int) Math.ceil(number_of_countries / 3.0));

    }

    private Country findByCountryName(String s) {
        for (Country c : countries) if (c.getName().equals(s)) return c;
        return null;
    }


    //Return All Countries Name of Player
    public List<String> getCountryNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Country country : countries)
            names.add(country.getName());
        return names;
    }


    /**
     * (addCard card): Adds risk cards to players
     *
     * @param q
     */
    public void addCard(String card) {
        this.cards.add(card);
    }

    public List<String> getCards() {
        return this.cards;
    }

    public int getTotalArmyCount() {
        int armyCount = 0;
        for (Country country : this.countries) {
            armyCount += country.getArmy();
        }
        return armyCount;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public List<Country> getCountries() {
        return this.countries;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArmyCapacity() {
        return armyCapacity;
    }

    public void setArmyCapacity(int armyCapacity) {
        this.armyCapacity = armyCapacity;
        this.setChanged();
        this.notifyObservers();
    }

    public void setAttackCounter(int count) {
        this.Attackercounter = count;
    }

    public int getAttackCounter() {
        return this.Attackercounter;
    }

    public int getExchangecount() {
        return exchangeCount;
    }

    public void setExchangecount() {
        this.exchangeCount++;
    }

    public int getExtraArmies() {
        return extraArmies;
    }

    public void setExtraArmies(int extraArmies) {
        this.extraArmies = extraArmies;
    }

    public int getCardExchangeArmies() {
        return finalarmies;
    }

    public void setCardExchangeArmies() {
        this.finalarmies = getExtraArmies() * getExchangecount();
    }


}
