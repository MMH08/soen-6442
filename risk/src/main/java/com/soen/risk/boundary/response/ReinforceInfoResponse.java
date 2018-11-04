package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

import java.util.List;

/**
 * The Class ReinforceInfoResponse.
 */
public class ReinforceInfoResponse implements Response {
    
    /** The reinforce army capacity. */
    private int reinforceArmyCapacity;
    
    /** The player name. */
    private String playerName;
    
    /** The countries. */
    private List<String> countries;
    
    /** The cards. */
    private List<String> cards;

    /**
     * Gets the reinforce army capacity.
     *
     * @return the reinforce army capacity
     */
    public int getReinforceArmyCapacity() {
        return reinforceArmyCapacity;
    }

    /**
     * Sets the reinforce army capacity.
     *
     * @param reinforceArmyCapacity the new reinforce army capacity
     */
    public void setReinforceArmyCapacity(int reinforceArmyCapacity) {
        this.reinforceArmyCapacity = reinforceArmyCapacity;
    }

    /**
     * Gets the player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the player name.
     *
     * @param playerName the new player name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gets the countries.
     *
     * @return the countries
     */
    public List<String> getCountries() {
        return countries;
    }

    /**
     * Sets the countries.
     *
     * @param countries the new countries
     */
    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
    
    /**
     * Gets the list of cards player earned.
     *
     * @return the player's cards
     */
    public List<String> getPlayerCards() {
        return cards;
    }

    /**
     * Sets the list of cards player earned.
     *
     * @param cards the cards earned
     */
    public void setPlayerCards(List<String> cards) {
        this.cards = cards;
    }
}
