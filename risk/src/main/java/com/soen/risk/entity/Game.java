package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 *
 */
public class Game {
    static Logger logger = Logger.getLogger(Game.class.getName());
    private Map map;
    private List<Player> players;

    /**
     * @param map
     * @param countOfPlayers
     */
    public Game(Map map, int countOfPlayers) {
        this.map = map;
        this.players = new ArrayList<>();
        this.addPlayers(countOfPlayers);
    }


    /**
     * @param countOfPlayers
     */
    private void addPlayers(int countOfPlayers) {
        System.out.println("Adding players " + String.valueOf(countOfPlayers));
        for (int i = 0; i < countOfPlayers; i++)
            this.players.add(new Player(i));
    }

    // -------------------------------------------------------------

    /**
     * @return
     */
    public boolean isNotOver() {
        //return (map.fetchOwners().size() == 1) ? false: true;
        return false;
    }

    public void allocateInitialCountries() {
        Random rand = new Random();
        for (Country country : map.getCountries()) {
            int indexOfPlayer = rand.nextInt(players.size());
            players.get(indexOfPlayer).addCountry(country);
            logger.log(Level.INFO,"Adding country " + country.getName() + " to player " + players.get(indexOfPlayer).getName());
        }
    }

    //Army size = number of countries with each player * (2-4) times)
    public void allocateInitialArmy() {
        Random rand = new Random();
        for (Player player : players) {
            player.setArmyCapacity(player.getCountries().size() * (2 + rand.nextInt(2)));
            logger.log(Level.INFO, "Adding army capacity to " + player.getName() + " " + String.valueOf(player.getArmyCapacity()));
        }
    }

    // -------------------------------------------------------------

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
