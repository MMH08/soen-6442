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
    //private boolean isAttackWon;
    private Phase currentPhase = Phase.STARTUP;

    public Game(Map map, List<Player> players) {
        this.map = map;
        this.players = players;
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
        for (Player player : players) {
            player.allocateInitialArmy();
        }
    }

    /**
     * Getting Player object  from Country Name
     *
     * @param country obj
     * @return Player object corresponding to the country
     */
    private Player getOwner(Country country) {
        for (Player p : players) {
            for (Country c : p.getCountries()) {
                if (c.getName().equals(country.getName())) {
                    return p;
                }
            }
        }
        return null;
    }


    /**
     * To check whether Game needs to end
     *
     * @return boolean value returning whether value of Players has reached 1
     */
    public boolean isEndNear() {
        return getPlayers().size() == 1;
    }

    /**
     * To initiate startup Phase with necessary country and armies
     *
     * @param countryName for the startup
     * @param armyCount   to assign for each Country
     */
    public void executeStartupPhase(String countryName, int armyCount) {
        Country country = getMap().findByCountryName(countryName);
        getCurrentPlayer().addArmy(country, armyCount);
        // updates
        updateCurrentPlayer();
        if (allPlayersHaveZeroArmy()) updateCurrentPhase();
    }


    public void executeReinforcePhase() {
        getCurrentPlayer().reinforce();
        // updates
        getCurrentPlayer().resetExchangeArmy();
        updateCurrentPhase();
    }

    public void executeAttackPhase() {
        currentPlayer.attack(map);
        boolean isComplete = currentPlayer.getAttackStrategy().isComplete();
        List<Country> wins = currentPlayer.getAttackStrategy().getWon();
        HashMap<Country, Country> losts = currentPlayer.getAttackStrategy().getLost();
        if (!wins.isEmpty()) currentPlayer.setAttackWon(true);

        // change the ownership
        for (Country win : wins) {
            getOwner(win).removeCountry(win);
            currentPlayer.addCountry(win);
        }
        for (Country lost : losts.keySet()) {
            currentPlayer.removeCountry(lost);
            getOwner(losts.get(lost)).addCountry(lost);
        }

        // update the phase
        if (isComplete) {
            logger.log(Level.INFO, "Exiting attack...");
            if (currentPlayer.isAttackWon()) {
                currentPlayer.addRandomCard();
                currentPlayer.setAttackWon(false); //reset flag for future attacks in next turn
            }
            updateCurrentPhase();
        }

        // if current player lost all the countries
        if (currentPlayer.getCountries().size() == 0) {
            logger.log(Level.INFO, "all countries lost by attacker");
            currentPlayer.sendCardsTo((Player) losts.values().toArray()[losts.size() - 1]);
            players.remove(currentPlayer);
            updateCurrentPlayer(); // jump the player
            updateCurrentPhase();
            updateCurrentPhase(); // jump to reinforce
        }
        dropEmptyPlayers();

        if (isEndNear()) updateCurrentPhase();
    }

    /**
     * To initiate Fortification Phase with respective county and armies required
     */
    public void executeFortificationPhase() {
        if (currentPlayer.fortify(map)) {
            // updates
            updateCurrentPhase();
            updateCurrentPlayer();
        }
    }


    /**
     * Change current player after its turn is over.
     */
    private void updateCurrentPlayer() {
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
    private void updateCurrentPhase() {
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

    private boolean allPlayersHaveZeroArmy() {
        for (Player p : this.getPlayers()) {
            if (p.getArmyCapacity() != 0) return false;
        }
        return true;
    }

    private void dropEmptyPlayers() {
        // drop players who lost all the countries
        ArrayList<Player> toRemove = new ArrayList<>();
        for(Player player : players){
            if (player.getCountries().size() == 0) {
                logger.log(Level.INFO, "all countries lost by " + player.getName());
                player.sendCardsTo(currentPlayer);
                toRemove.add(player);
            }
        }
        players.removeAll(toRemove);
    }

    // --------------------------------------------------------------------------------------------------------

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
        logger.log(Level.INFO, "Changing player to " + currentPlayer);
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
    void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
        logger.log(Level.INFO, "Changing phase to " + currentPhase);
        this.setChanged();
        this.notifyObservers(this);
    }

}
