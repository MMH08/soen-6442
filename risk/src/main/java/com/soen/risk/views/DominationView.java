package com.soen.risk.views;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use case to buildGame the game play will be responsible to attach the domination view to all the players that are
 * playing the game. The observer should be attached after the players have been spawned, i.e.,
 * in the buildGame method of GamePlay.
 *
 * @author Varun Singhal
 * @version 1.0.2
 * @since 04-11-2018
 */
public class DominationView implements Observer, Serializable {

    /** The logger. */
    private static Logger logger = Logger.getLogger(DominationView.class.getName());
    
    /** The players info. */
    private HashMap<String, PlayerInfo> playersInfo;
    
    /** The total countries. */
    private int totalCountries;


    /**
     * Constructor for Domination View.
     *
     * @param totalCountries the total countries
     */
    public DominationView(int totalCountries) {
        this.totalCountries = totalCountries;
        playersInfo = new HashMap<>();
    }

    /**
     * Update the Player and his assigned countries with army count.
     *
     * @param obs PLayer instance to find the player information
     * @param o the o
     */
    @Override
    public void update(Observable obs, Object o) {
        try {
            Player player = (Player) obs;
            updatePlayer(player);
        } catch (ClassCastException e) {
            try {
                Country country = (Country) obs;
                updateCountry(country);
            } catch (ClassCastException p) {
                logger.log(Level.SEVERE, "Update not defined");
            }
        }
    }

    // country has been added or dropped -- recalculate the share.

    /**
     * Update the DominationView object with his name and share of armies.
     *
     * @param p the p
     */
    private void updatePlayer(Player p) {
        PlayerInfo playerinfo = playersInfo.getOrDefault(p.getName(), new PlayerInfo());
        playerinfo.setCountries(new HashMap<>()); //reset the countries
        for (Country country : p.getCountries()) {
            playerinfo.addCountry(country.getName(), country.getArmy());
            playerinfo.setTotalArmyCount(calculateTotalCount(playerinfo));
        }
        logger.log(Level.INFO, p + playerinfo.toString());
        playersInfo.put(p.getName(), playerinfo);

        //update share for every player
        for (String playerName : playersInfo.keySet()) {
            PlayerInfo currentPlayerInfo = playersInfo.get(playerName);
            try {
                currentPlayerInfo.setShare((float) currentPlayerInfo.getCountries().size() / totalCountries * 100);
            } catch (ArithmeticException e) {
                currentPlayerInfo.setShare(" - %");
            }
            playersInfo.put(playerName, currentPlayerInfo);
        }
    }

    // army count has been updated for the country. -- update total army count

    /**
     * Update the Country with updated total armyCount needed for view.
     *
     * @param c the c
     */
    private void updateCountry(Country c) {
        for (String playerName : playersInfo.keySet()) {
            PlayerInfo currentPlayerInfo = playersInfo.getOrDefault(playerName, new PlayerInfo());
            for (String countryName : currentPlayerInfo.getCountries().keySet()) {
                if (countryName.equals(c.getName())) {
                    currentPlayerInfo.getCountries().put(countryName, c.getArmy());
                    currentPlayerInfo.setTotalArmyCount(calculateTotalCount(currentPlayerInfo));//calculate total armies again
                    break;
                }
            }
            playersInfo.put(playerName, currentPlayerInfo);
        }
    }

    /**
     * Update the Player with updated total armyCount needed for view.
     *
     * @param playerInfo the player info
     * @return Country count of a Player
     */
    private int calculateTotalCount(PlayerInfo playerInfo) {
        int counter = 0;
        for (String countryName : playerInfo.getCountries().keySet()) {
            counter += playerInfo.getCountries().get(countryName);
        }
        return counter;
    }


    /**
     * Gets the players info.
     *
     * @return the players info
     */
    public HashMap<String, PlayerInfo> getPlayersInfo() {
        return playersInfo;
    }

    /**
     * Sets the players info.
     *
     * @param playersInfo the players info
     */
    public void setPlayersInfo(HashMap<String, PlayerInfo> playersInfo) {
        this.playersInfo = playersInfo;
    }

    /**
     * The Class PlayerInfo.
     */
    class PlayerInfo implements Serializable{
        
        /** The countries. */
        private HashMap<String, Integer> countries;
        
        /** The total army count. */
        private int totalArmyCount;
        
        /** The share. */
        private String share;

        /**
         * Instantiates a new player info.
         */
        PlayerInfo() {
            countries = new HashMap<>();
        }

        /**
         * Adds the country.
         *
         * @param countryName the country name
         * @param armyCount the army count
         */
        public void addCountry(String countryName, int armyCount) {
            countries.put(countryName, armyCount);
        }

        /**
         * Gets the countries.
         *
         * @return the countries
         */
        public HashMap<String, Integer> getCountries() {
            return countries;
        }

        /**
         * Sets the countries.
         *
         * @param countries the countries
         */
        public void setCountries(HashMap<String, Integer> countries) {
            this.countries = countries;
        }

        /**
         * Gets the total army count.
         *
         * @return the total army count
         */
        public int getTotalArmyCount() {
            return totalArmyCount;
        }

        /**
         * Sets the total army count.
         *
         * @param totalArmyCount the new total army count
         */
        public void setTotalArmyCount(int totalArmyCount) {
            this.totalArmyCount = totalArmyCount;
        }

        /**
         * Gets the share.
         *
         * @return the share
         */
        public String getShare() {
            return share;
        }

        /**
         * Sets the share.
         *
         * @param share the new share
         */
        public void setShare(String share) {
            this.share = share;
        }

        /**
         * Sets the share.
         *
         * @param share the new share
         */
        public void setShare(float share) {
            this.share = String.valueOf(share) + " %";
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return String.valueOf(countries);
        }
    }
}
