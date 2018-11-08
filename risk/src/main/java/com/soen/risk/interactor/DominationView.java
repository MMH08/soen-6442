package com.soen.risk.interactor;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Player;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use case to buildGame the game play will be responsible to attach the domination view to all the players that are
 * playing the game. The observer should be attached after the players have been spawned, i.e.,
 * in the buildGame method of GamePlay.
 * <p>
 * playerInfo = {"name" : [armyCount, Countries, countOfCountries, percentage]}
 */
public class DominationView implements Observer {

    private static Logger logger = Logger.getLogger(DominationView.class.getName());
    private HashMap<String, PlayerInfo> playersInfo;
    private final String DEFAULT_SHARE = " - %";
    private int totalCountries;

    /**
     * Singleton implementation of domination view to re-use the attached observer instance.
     *
     * @return Instantiated domination view object.
     */
    DominationView(int totalCountries) {
        this.totalCountries = totalCountries;
        playersInfo = new HashMap<>();
    }


    @Override
    public void update(Observable obs, Object o) {
        try {
            Player player = (Player) obs;
            updatePlayer(player);

//            Method update = getClass().getMethod("update1", obs.getClass());
//            logger.log(Level.INFO, update.getName());
//            update.invoke(this, obs);
        } catch (ClassCastException e) {
            try {
                Country country = (Country) obs;
                updateCountry(country);
            }
            catch (ClassCastException p){
                logger.log(Level.SEVERE, "Update not defined");
            }
        }

//    String name = ((Player) obs).getName();
//    String totalArmyCount = String.valueOf(((Player) obs).getTotalArmyCount()); // calculated based
//    List<String> countries = ((Player) obs).getCountryNames();
//    String countOfCountries = String.valueOf(countries.size());
//    playerInfo.put(name, Arrays.asList(totalArmyCount, convertToString(countries), countOfCountries,  DEFAULT_SHARE));
//    recalculateShare();
//    logger.log(Level.INFO, "Countries updated by " + name + " : " + countries);
//    logger.log(Level.INFO, "Army count re-calculated for " + name + " : " + totalArmyCount);

    }

    // country has been added or dropped -- recalculate the share.
    private void updatePlayer(Player p) {
        PlayerInfo playerinfo = playersInfo.getOrDefault(p.getName(), new PlayerInfo());
        playerinfo.setCountries(new HashMap<>()); //reset the countries
        for (Country country : p.getCountries()) {
            logger.log(Level.INFO, "Adding country to player : " + p.getName() + " country : " + country.getName());
            playerinfo.addCountry(country.getName(), country.getArmy());
        }
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

    // army count has been updated for the country. -- update total countries
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

    private int calculateTotalCount(PlayerInfo playerInfo){
        int counter = 0;
        for(String countryName: playerInfo.getCountries().keySet()){
            counter += playerInfo.getCountries().get(countryName);
        }
        return counter;
    }

//    private String convertToString(List<String> countries) {
//        StringBuilder countryHtml = new StringBuilder();
//        for (String country : countries) {
//            countryHtml.append(country).append("<br>");
//        }
//        return countryHtml.toString();
//    }


//    private void recalculateShare() {
//        int totalCountries = 0;
//        for (String currentPlayerName : playerInfo.keySet()) {
//            totalCountries += Integer.valueOf(playerInfo.get(currentPlayerName).get(2));
//        }
//        for (String currentPlayerName : playerInfo.keySet()) {
//            List<String> currentPlayerInfo = playerInfo.get(currentPlayerName);
//            try {
//                currentPlayerInfo.set(3, String.valueOf(Float.valueOf(currentPlayerInfo.get(2)) / totalCountries * 100) + " %");
//            } catch (ArithmeticException e) {
//                currentPlayerInfo.set(3, DEFAULT_SHARE);
//            }
//            playerInfo.put(currentPlayerName, currentPlayerInfo);
//        }
//    }
//
//    public HashMap<String, List<String>> getPlayerInfo() {
//        return playerInfo;
//    }


    public HashMap<String, PlayerInfo> getPlayersInfo() {
        return playersInfo;
    }

    public void setPlayersInfo(HashMap<String, PlayerInfo> playersInfo) {
        this.playersInfo = playersInfo;
    }

    class PlayerInfo {
        private HashMap<String, Integer> countries;
        private int totalArmyCount;
        private String share;

        PlayerInfo() {
            countries = new HashMap<>();
        }

        public void addCountry(String countryName, int armyCount) {
            countries.put(countryName, armyCount);
        }

        public HashMap<String, Integer> getCountries() {
            return countries;
        }

        public void setCountries(HashMap<String, Integer> countries) {
            this.countries = countries;
        }

        public int getTotalArmyCount() {
            return totalArmyCount;
        }

        public void setTotalArmyCount(int totalArmyCount) {
            logger.log(Level.INFO, "Updating total army count" + totalArmyCount);
            this.totalArmyCount = totalArmyCount;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public void setShare(float share) {
            logger.log(Level.INFO, "Updating share - " + share);
            this.share = String.valueOf(share) + " %";
        }
    }
}
