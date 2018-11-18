package com.soen.risk.entity;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h2>Game Class</h2>
 * <p>In this class, Can add new player to a game, assign random countries to players, armies to countries and drop
 * players after lost of all countries. Also return map and players</p>
 *
 * @author Manmeet Singh
 * @version 1.0.2
 * @since 2018-11-05
 */
public class Game extends Observable {
    private static Logger logger = Logger.getLogger(Game.class.getName());

    private Map map;
    private List<Player> players;

    private Player currentPlayer;
    private Phase currentPhase = Phase.STARTUP;

    /**
     * Assign to map reference, new arraylist for players, and adding all players.
     *
//     * @param filename       Having reference of map class
//     * @param countOfPlayers Number of players playing game
     */
    public Game(Map map, List<Player> players) {
        this.map = map;
        this.players = players;
    }


    /**
     * Change current player after its turn is over.
     */
    public void updateCurrentPlayer() {
        int count = 0;
        for (Player p : getPlayers()) {
            if (p.getName().equals(currentPlayer.getName())) {
                if (count == getPlayers().size() - 1) {
                    this.setCurrentPlayer(getPlayers().get(0));
                } else {
                    this.setCurrentPlayer(getPlayers().get(count + 1));
                }
                break;
            }
            count++;
        }
    }

    // visit this function again

    /**
     * Getting Player object  from Country Name
     *
     * @param countryName
     * @return Player object corresponding to the country
     */
    public Player getPlayerFromCountry(String countryName) {
        for (Player p : players) {
            for (Country c : p.getCountries()) {
                if (c.getName().equals(countryName)) {
                    return p;
                }
            }
        }
        return null;
    }


    private ArrayList<String> checkPlayerSpecificNeigbhouringCountries(LinkedList<Country> lc) {
        ArrayList<String> ar = new ArrayList<>();
        for (Country c1 : lc) {
            int flag = 0;
            for (Country c : currentPlayer.getCountries()) {
                if (c1.getName().equals(c.getName())) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                ar.add(c1.getName());
            }
        }
        return ar;
    }


    /**
     * Return HashMap with countries and its neighboring countries not belong to player
     *
     * @return HashMap of Neighboring countries of a Player Specific Country Name
     */
    public HashMap<String, ArrayList<String>> getNeighbouringCountries() {
        LinkedList<LinkedList<Country>> ll = map.getAdjCountry();
        HashMap<String, ArrayList<String>> neighbouring = new HashMap<String, ArrayList<String>>();
        for (Country c : currentPlayer.getCountries()) {
            for (LinkedList<Country> lc : ll) {
                if (c.getName().equals(lc.get(0).getName())) {
                    neighbouring.put(c.getName(), checkPlayerSpecificNeigbhouringCountries(lc));
                }
            }
        }
        return neighbouring;
    }

    /**
     * Changing current phase between reinforcement, attack, and fortify.
     */
    public void updateCurrentPhase() {
        if (this.getCurrentPhase().equals(Phase.STARTUP)) {
            this.setCurrentPhase(Phase.REINFORCE);
            return;
        }

        Phase[] phases = {Phase.REINFORCE, Phase.ATTACK, Phase.FORTIFY};

        for (int i = 0; i < phases.length; i++) {
            if (currentPhase.equals(phases[i])) {
                if (i == phases.length - 1) {
                    this.setCurrentPhase(phases[0]);
                    break;
                } else {
                    this.setCurrentPhase(phases[i + 1]);
                    break;
                }
            }
        }
    }

    /**
     * Drop player after lose all countries
     *
     * @param p Player needed to remove from game
     */
    public void dropPlayer(Player p) {
        int i = 0;
        for (Player p1 : players) {
            if (p.equals(p1)) {
                players.remove(i);
                break;
            }
            i++;
        }
    }

    /**
     * Randomly allocate initial countries to all players.
     */
    public void allocateInitialCountries() {
        Random rand = new Random();
        int index = 0;
        for (Player p : players) {
            p.addCountry(map.getCountries().get(index));
            index++;
        }
        for (int i = index; i < map.getCountries().size(); i++) {
            int indexOfPlayer = rand.nextInt(players.size());
            players.get(indexOfPlayer).addCountry(map.getCountries().get(i));
            logger.log(Level.INFO, "Adding country " + map.getCountries().get(i).getName() + " to player " + players.get(indexOfPlayer).getName());
        }
    }

    public void allocateInitialArmies() {
        for(Player player: players){
            player.allocateInitialArmy();
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


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /*
     * To change Player and also notifies Observers regarding the change to the view
     *
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.setChanged();
        this.notifyObservers(this);
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    /*
     * To change Phase and also notifies Observers regarding the change to the Phase view
     *
     */
    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
        this.setChanged();
        this.notifyObservers(this);
    }


    /**
     * To check whether Game needs to end
     *
     * @return boolean value returning whether value of Players has reached 1
     */
    public boolean isEndNear() {
        return getPlayers().size() == 1;
    }

    public boolean allPlayersHaveZeroArmy() {
        for (Player p : this.getPlayers()) {
            if (p.getArmyCapacity() != 0) return false;
        }
        return true;
    }

}
