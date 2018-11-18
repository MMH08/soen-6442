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
 * @since 2018-10-31
 */
public class Player extends Observable {
    private static Logger logger = Logger.getLogger(Player.class.getName());

    private String name;
    private PlayerType type;
    private int armyCapacity;
    private List<Country> countries;
    private List<Card> cards;

    private int exchangeArmy = 0;
    private int exchangeCount = 0;

    private ReinforceStrategy reinforceStrategy;
    private AttackStrategy attackStrategy;
    private FortifyStrategy fortifyStrategy;

    /**
     * Initialise the player with given suffix and empty list of owned countries.
     *
//     * @param number count of player
     */
    public Player(String name, String behavior) {
        this.name = name;
        this.countries = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.armyCapacity = 0;
        this.type = PlayerType.valueOf(behavior);
        //new ArrayList<>(Arrays.asList(Card.ARTILLERY, Card.INFANT, Card.INFANT, Card.CAVALRY, Card.CAVALRY, Card.CAVALRY, Card.CAVALRY));
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

    public void addArmy(Country country, int armyCount) {
        country.setArmy(country.getArmy() + armyCount);
        setArmyCapacity(getArmyCapacity() - armyCount);
    }

    public void reinforce() {
        reinforceStrategy.execute(this.countries);
    }

    public void attack(Map map) {
        attackStrategy.execute(map, this.countries);
    }


    public boolean fortify(Map map) {
        return fortifyStrategy.execute(map, countries);
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

    private Country findByCountryName(String s) {
        for (Country c : countries) if (c.getName().equals(s)) return c;
        return null;
    }

    /**
     * To remove cards once used by Player. Observed by CardExchangeView
     *
     * @param temp Array of Card Names
     */
    public void removeCard(String temp[]) {
        logger.log(Level.INFO, "Removing card " + temp);
        for (String aTemp : temp) {
            int count = 0;
            for (Card c : cards) {
                if (c.toString().equals(aTemp)) {
                    logger.log(Level.INFO, "Remove= " + c.toString());
                    cards.remove(c);
                    break;
                }
                count++;
            }
        }
        setChanged();
        notifyObservers(this);

    }


    /**
     * Adding Card Objects to Player. Notified to CardExchangeView
     *
     * @param card
     */
    private void addCard(Card card) {
        this.cards.add(card);
        logger.log(Level.INFO, "Adding card : " + card.toString());
        setChanged();
        notifyObservers(this);
    }


    /**
     * Adding RandomCard
     */
    public void addRandomCard() {
        Random rand = new Random();
        List<Card> cardTypes = Arrays.asList(Card.values());
        addCard(cardTypes.get(rand.nextInt(3)));
    }

    /**
     * To get all Card Names
     *
     * @return CardNames as List
     */
    public List<String> getCardNames() {
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards)
            cardNames.add(card.toString());
        return cardNames;
    }

    public void assignExchangeArmies() {
        setExchangeArmy(exchangeArmy + (exchangeCount + 1) * 5);
        setExchangeCount(getExchangeCount() + 1);
    }

    public void resetExchangeArmy() {
        setExchangeArmy(0);
    }

    public void sendCardsTo(Player currentPlayer) {
        for (Card card : cards) {
            currentPlayer.addCard(card);
        }
        setCards(new ArrayList<>());
    }

    public boolean isCardExchangeEnabled() {
        HashMap<String, Integer> hp = new HashMap<>();
        if (cards.size() < 5)
            return false;
        for (Card c : cards) {
            if (!hp.isEmpty()) {
                if (hp.containsKey(c.toString())) {
                    hp.put(c.toString(), hp.get(c.toString()) + 1);

                } else {
                    hp.put(c.toString(), 1);
                }
            } else {
                hp.put(c.toString(), 1);
            }
        }
        //One of them have 3
        for (String key : hp.keySet()) {
            if (hp.get(key) >= 3) {
                return true;
            }
        }
        if (hp.size() < 3) {
            return false;
        }
        int flag = 0;
        for (String key : hp.keySet()) {
            if (!(hp.get(key) >= 1)) {
                flag = 1;
            }
        }
        if (flag == 0)
            return true;
        else
            return false;
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
    }

    public int getExchangeCount() {
        return exchangeCount;
    }

    public void setExchangeCount(int exchangeCount) {
        logger.log(Level.INFO, "exchange count updated : " + exchangeCount);
        this.exchangeCount = exchangeCount;
    }

    public int getExchangeArmy() {
        return exchangeArmy;
    }

    public void setExchangeArmy(int exchangeArmy) {
        logger.log(Level.INFO, "Updating exchange army count : " + exchangeArmy);
        this.exchangeArmy = exchangeArmy;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        setChanged();
        notifyObservers(this);
    }

    public ReinforceStrategy getReinforceStrategy() {
        return reinforceStrategy;
    }

    public void setReinforceStrategy(ReinforceStrategy reinforceStrategy) {
        this.reinforceStrategy = reinforceStrategy;
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public FortifyStrategy getFortifyStrategy() {
        return fortifyStrategy;
    }

    public void setFortifyStrategy(FortifyStrategy fortifyStrategy) {
        this.fortifyStrategy = fortifyStrategy;
    }

    public PlayerType getType() {
        return type;
    }
}
