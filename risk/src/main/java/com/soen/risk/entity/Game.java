package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *<h2>Game Class</h2>
 *<p>In this class, Can add new player to a game, assign random countries to players, armies to countries and drop
 *players after lost of all countries. Also return map and players</p>
 *@author Manmeet Singh
 *@since 2018-10-11
 *@version 1.0.0 
 */
public class Game {
    static Logger logger = Logger.getLogger(Game.class.getName());
    private Map map;
    private List<Player> players;

    /**
     * Assign to map reference, new arraylist for players, and adding all players.
     * @param map Having reference of map class
     * @param countOfPlayers Number of players playing game
     */
    public Game(Map map, int countOfPlayers) {
        this.map = map;
        this.players = new ArrayList<>();
        this.addPlayers(countOfPlayers);
    }


    /**
     * @param countOfPlayers number of players playing game
     */
    private void addPlayers(int countOfPlayers) {
        System.out.println("Adding players " + String.valueOf(countOfPlayers));
        for (int i = 0; i < countOfPlayers; i++)
            this.players.add(new Player(i));
    }
    
    /**
     * Drop player after lose all countries
     * @param p Player needed to remove from game
     */
    public void dropPlayer(Player p)
    {
    	int i=0;
		for(Player p1: players)
		{
			if(p.equals(p1))
			{
				players.remove(i);
				break;
			}
			i++;
		}
    }
    // -------------------------------------------------------------

    /**
     * @return boolean
     */
    public boolean isNotOver() {
        //return (map.fetchOwners().size() == 1) ? false: true;
        return false;
    }
    
    /**
     *Randomly allocate initial countries to all players.
     */
    public void allocateInitialCountries() {
        Random rand = new Random();
        for (Country country : map.getCountries()) {
            int indexOfPlayer = rand.nextInt(players.size());
            players.get(indexOfPlayer).addCountry(country);
            logger.log(Level.INFO,"Adding country " + country.getName() + " to player " + players.get(indexOfPlayer).getName());
        }
    }
    
    /**
     *Randomly allocate initial armies to all countries.
     */
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
