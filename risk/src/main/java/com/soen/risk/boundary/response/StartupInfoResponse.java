package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

import java.util.List;

/**
 * The Class StartupInfoResponse.
 */
public class StartupInfoResponse implements Response {
    
    /** The player name. */
    private String playerName;
    
    /** The country name. */
    private String countryName;
    
    /** The army capacity. */
    private int armyCapacity;
    
    /** The countries. */
    private List<String> countries;

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
     * Gets the country name.
     *
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the country name.
     *
     * @param countryName the new country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
}
