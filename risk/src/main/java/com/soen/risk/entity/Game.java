package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 */
public class Game {
    private Map map;
    private List<Player> players;
    private String[] phases = {"reinforce", "attack", "fortify"};

    /**
     *
     *
     * @param map
     * @param countOfPlayers
     */
    public Game(Map map, int countOfPlayers) {
        this.map = map;
        this.players = new ArrayList<>();
        this.addPlayers(countOfPlayers);
    }


    /**
     *
     *
     * @param countOfPlayers
     */
    private void addPlayers(int countOfPlayers) {
        for (int i = 0; i < countOfPlayers; i++)
            this.players.add(new Player(i));
    }

    // -------------------------------------------------------------

    /**
     *
     * @return
     */
    public boolean isNotOver() {
        //return (map.fetchOwners().size() == 1) ? false: true;
        return false;
    }

    public void allocateInitialCountries(){
        // random assignment
        // for each country in this.map.getCountries()
        // shuffle player from the this.players
        // player.addCountry(Country object)
    }

    public void allocateInitialArmy(){
        // for each player this.players
        // armyCapacity has to be filled in
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
