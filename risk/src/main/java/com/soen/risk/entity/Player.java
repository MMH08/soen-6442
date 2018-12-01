package com.soen.risk.entity;

import java.io.Serializable;
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
public class Player extends Observable implements Serializable {
    
    /** The logger. */
    private static Logger logger = Logger.getLogger(Player.class.getName());

    /** The name. */
    private String name;
    
    /** The type. */
    private PlayerType type;
    
    /** The army capacity. */
    private int armyCapacity;
    
    /** The countries. */
    private List<Country> countries;
    
    /** The cards. */
    private List<Card> cards;

    /** The exchange army. */
    private int exchangeArmy = 0;
    
    /** The exchange count. */
    private int exchangeCount = 0;
    
    /** The is attack won. */
    private boolean isAttackWon;

    /** The startup strategy. */
    private StartupStrategy startupStrategy;
    
    /** The reinforce strategy. */
    private ReinforceStrategy reinforceStrategy;
    
    /** The attack strategy. */
    private AttackStrategy attackStrategy;
    
    /** The fortify strategy. */
    private FortifyStrategy fortifyStrategy;

    /**
     * Initialise the player with given suffix and empty list of owned countries.
     * <p>
     * //     * @param number count of player
     *
     * @param name the name
     * @param behavior the behavior
     */
    public Player(String name, String behavior) {
        this.name = name;
        this.countries = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.armyCapacity = 0;
        this.type = PlayerType.valueOf(behavior);
        this.isAttackWon = false;
        //new ArrayList<>(Arrays.asList(Card.ARTILLERY, Card.INFANT, Card.INFANT, Card.CAVALRY, Card.CAVALRY, Card.CAVALRY, Card.CAVALRY));
    }

    /**
     * Add new country to countries object.
     *
     * @param c country
     */
    public void addCountry(Country c) {
        this.countries.add(c);
        this.setChanged();
        this.notifyObservers(this);
    }

    /**
     * When a match will be lost then remove country will be called.
     *
     * @param c the c
     */
    public void removeCountry(Country c) {
        this.countries.remove(c);
        this.setChanged();
        this.notifyObservers(this);
    }

    /**
     * nextCountryToAssignArmy().
     *
     * @return Object of country to which army is assign
     */
    public Country nextCountryToAssignArmy() {
        for (Country country : countries) {
            if (country.isEmpty())
                return country;
        }
        return countries.get(countries.size() - 1);
    }

    // -----------------------------------------------------------------------------------------------------------------

//    public void addArmy(Country country, int armyCount) {
//        country.setArmy(country.getArmy() + armyCount);
//        setArmyCapacity(getArmyCapacity() - armyCount);
//    }

    /**
     * Startup.
     */
    public void startup() {
        int consumedArmy = startupStrategy.execute(this.nextCountryToAssignArmy(), this.armyCapacity);
        setArmyCapacity(getArmyCapacity() - consumedArmy);
    }

    /**
     * Reinforce.
     *
     * @param map the map
     */
    public void reinforce(Map map) {
        reinforceStrategy.execute(map, this.countries);
    }

    /**
     * Attack.
     *
     * @param map the map
     */
    public void attack(Map map) {
        attackStrategy.execute(map, this.countries);
    }


    /**
     * Fortify.
     *
     * @param map the map
     * @return true, if successful
     */
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

    /**
     * Find by country name.
     *
     * @param s the s
     * @return the country
     */
    private Country findByCountryName(String s) {
        for (Country c : countries) if (c.getName().equals(s)) return c;
        return null;
    }

    /**
     * To remove cards once used by Player. Observed by CardExchangeView
     *
     * @param temp Array of Card Names
     */
    public void removeCard(String[] temp) {
        for (String aTemp : temp) {
            logger.log(Level.INFO, "Removing card " + aTemp);
            for (Card c : cards) {
                if (c.toString().equals(aTemp)) {
                    logger.log(Level.INFO, "Remove= " + c.toString());
                    cards.remove(c);
                    break;
                }
            }
        }
        setChanged();
        notifyObservers(this);

    }


    /**
     * Adding Card Objects to Player. Notified to CardExchangeView
     *
     * @param card the card
     */
    private void addCard(Card card) {
        this.cards.add(card);
        logger.log(Level.INFO, "Adding card : " + card.toString());
        setChanged();
        notifyObservers(this);
    }


    /**
     * Adding RandomCard.
     */
    public void addRandomCard() {
        Random rand = new Random();
        List<Card> cardTypes = Arrays.asList(Card.values());
        addCard(cardTypes.get(rand.nextInt(3)));
    }

    /**
     * To get all Card Names.
     *
     * @return CardNames as List
     */
    public List<String> getCardNames() {
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards)
            cardNames.add(card.toString());
        return cardNames;
    }

    /**
     * Assign exchange armies.
     */
    public void assignExchangeArmies() {
        setExchangeArmy(exchangeArmy + (exchangeCount + 1) * 5);
        setExchangeCount(getExchangeCount() + 1);
    }

    /**
     * Reset exchange army.
     */
    public void resetExchangeArmy() {
        setExchangeArmy(0);
    }

    /**
     * Send cards to.
     *
     * @param otherPlayer the other player
     */
    public void sendCardsTo(Player otherPlayer) {
        for (Card card : cards) {
            otherPlayer.addCard(card);
        }
        setCards(new ArrayList<>());
    }

    /**
     * Checks if is card exchange enabled.
     *
     * @return true, if is card exchange enabled
     */
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
        return flag == 0;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return this.name;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the countries.
     *
     * @return the countries
     */
    public List<Country> getCountries() {
        return this.countries;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the army capacity.
     *
     * @return the army capacity
     */
    public int getArmyCapacity() {
        return armyCapacity;
    }

    /**
     * Sets the army capacity.
     *
     * @param armyCapacity the new army capacity
     */
    public void setArmyCapacity(int armyCapacity) {
        this.armyCapacity = armyCapacity;
    }

    /**
     * Gets the exchange count.
     *
     * @return the exchange count
     */
    public int getExchangeCount() {
        return exchangeCount;
    }

    /**
     * Sets the exchange count.
     *
     * @param exchangeCount the new exchange count
     */
    public void setExchangeCount(int exchangeCount) {
        logger.log(Level.INFO, "exchange count updated : " + exchangeCount);
        this.exchangeCount = exchangeCount;
    }

    /**
     * Gets the exchange army.
     *
     * @return the exchange army
     */
    public int getExchangeArmy() {
        return exchangeArmy;
    }

    /**
     * Sets the exchange army.
     *
     * @param exchangeArmy the new exchange army
     */
    public void setExchangeArmy(int exchangeArmy) {
        logger.log(Level.INFO, "Updating exchange army count : " + exchangeArmy);
        this.exchangeArmy = exchangeArmy;
    }

    /**
     * Gets the cards.
     *
     * @return the cards
     */
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Sets the cards.
     *
     * @param cards the new cards
     */
    public void setCards(List<Card> cards) {
        this.cards = cards;
        setChanged();
        notifyObservers(this);
    }

    /**
     * Gets the reinforce strategy.
     *
     * @return the reinforce strategy
     */
    public ReinforceStrategy getReinforceStrategy() {
        return reinforceStrategy;
    }

    /**
     * Sets the reinforce strategy.
     *
     * @param reinforceStrategy the new reinforce strategy
     */
    public void setReinforceStrategy(ReinforceStrategy reinforceStrategy) {
        this.reinforceStrategy = reinforceStrategy;
    }

    /**
     * Gets the attack strategy.
     *
     * @return the attack strategy
     */
    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    /**
     * Sets the attack strategy.
     *
     * @param attackStrategy the new attack strategy
     */
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    /**
     * Gets the fortify strategy.
     *
     * @return the fortify strategy
     */
    public FortifyStrategy getFortifyStrategy() {
        return fortifyStrategy;
    }

    /**
     * Sets the fortify strategy.
     *
     * @param fortifyStrategy the new fortify strategy
     */
    public void setFortifyStrategy(FortifyStrategy fortifyStrategy) {
        this.fortifyStrategy = fortifyStrategy;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public PlayerType getType() {
        return type;
    }

    /**
     * Checks if is attack won.
     *
     * @return true, if is attack won
     */
    public boolean isAttackWon() {
        return isAttackWon;
    }

    /**
     * Sets the attack won.
     *
     * @param attackWon the new attack won
     */
    public void setAttackWon(boolean attackWon) {
        logger.log(Level.INFO, "Setting attackWon flag " + attackWon);
        isAttackWon = attackWon;
    }

    /**
     * Gets the startup strategy.
     *
     * @return the startup strategy
     */
    public StartupStrategy getStartupStrategy() {
        return startupStrategy;
    }

    /**
     * Sets the startup strategy.
     *
     * @param startupStrategy the new startup strategy
     */
    public void setStartupStrategy(StartupStrategy startupStrategy) {
        this.startupStrategy = startupStrategy;
    }
}
