package com.soen.risk.interactor;

import com.soen.risk.entity.Player;

import java.util.*;
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
    private HashMap<String, List<String>> playerInfo;
    private final String DEFAULT_PERCENTAGE = " - %";

    /**
     * Singleton implementation of domination view to re-use the attached observer instance.
     *
     * @return Instantiated domination view object.
     */
    public DominationView() {
        playerInfo = new HashMap<>();
    }


    @Override
    public void update(Observable obs, Object o) {
        String name = ((Player) obs).getName();
        String totalArmyCount = String.valueOf(((Player) obs).getTotalArmyCount()); // calculated based
        List<String> countries = ((Player) obs).getCountryNames();
        String countOfCountries = String.valueOf(countries.size());
        playerInfo.put(name, Arrays.asList(totalArmyCount, convertToString(countries), countOfCountries,  DEFAULT_PERCENTAGE));
        recalculateShare();
        logger.log(Level.INFO, "Countries updated by " + name + " : " + countries);
        logger.log(Level.INFO, "Army count re-calculated for " + name + " : " + totalArmyCount);
    }

    private String convertToString(List<String> countries) {
        StringBuilder countryHtml = new StringBuilder();
        for (String country : countries) {
            countryHtml.append(country).append("<br>");
        }
        return countryHtml.toString();
    }


    private void recalculateShare() {
        int totalCountries = 0;
        for (String currentPlayerName : playerInfo.keySet()) {
            totalCountries += Integer.valueOf(playerInfo.get(currentPlayerName).get(2));
        }
        for (String currentPlayerName : playerInfo.keySet()) {
            List<String> currentPlayerInfo = playerInfo.get(currentPlayerName);
            try {
                currentPlayerInfo.set(3, String.valueOf(Float.valueOf(currentPlayerInfo.get(2)) / totalCountries * 100) + " %");
            } catch (ArithmeticException e) {
                currentPlayerInfo.set(3, DEFAULT_PERCENTAGE);
            }
            playerInfo.put(currentPlayerName, currentPlayerInfo);
        }
    }

    public HashMap<String, List<String>> getPlayerInfo() {
        return playerInfo;
    }
}
