package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h2>Game Class</h2>
 * <p>In this class, Can add new player to a game, assign random countries to players, armies to countries and drop
 * players after lost of all countries. Also return map and players</p>
 *
 * @author Manmeet Singh
 * @version 1.0.0
 * @since 2018-10-11
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
     * @param filename       Having reference of map class
     * @param countOfPlayers Number of players playing game
     */
    public Game(String filename, int countOfPlayers) {
        this.map = new Map();
        this.map.load(filename);

        if (map.isValid()) {
            this.players = new ArrayList<>();
            this.addPlayers(countOfPlayers);
        }
    }

    public void initialize() {
        this.setCurrentPlayer(this.getPlayers().get(0));
        allocateInitialCountries();
        for (Player player : this.players)
            player.allocateInitialArmy();
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
     * @param countOfPlayers number of players playing game
     */
    private void addPlayers(int countOfPlayers) {
        System.out.println("Adding players " + String.valueOf(countOfPlayers));
        for (int i = 0; i < countOfPlayers; i++)
            this.players.add(new Player(i));
    }

    /**
     * Drop player after lose all countries
     *
     * @param p Player needed to remove from game
     */
    private void dropPlayer(Player p) {
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
    private void allocateInitialCountries() {
        Random rand = new Random();
        for (Country country : map.getCountries()) {
            int indexOfPlayer = rand.nextInt(players.size());
            players.get(indexOfPlayer).addCountry(country);
            logger.log(Level.INFO, "Adding country " + country.getName() + " to player " + players.get(indexOfPlayer).getName());
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

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.setChanged();
        this.notifyObservers(this);
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
        this.setChanged();
        this.notifyObservers(this);
    }
}
