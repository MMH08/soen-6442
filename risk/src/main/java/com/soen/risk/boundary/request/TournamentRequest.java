package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

import java.util.Arrays;
import java.util.List;

/**
 * The Class TournamentRequest.
 */
public class TournamentRequest implements Request {
    
    /** The filenames. */
    private List<String> filenames;
    
    /** The player names. */
    private String playerNames;
    
    /** The behaviors. */
    private String behaviors;
    
    /** The count of games. */
    private int countOfGames;
    
    /** The turns. */
    private int turns;

    /**
     * Instantiates a new tournament request.
     *
     * @param args the args
     */
    public TournamentRequest(String... args) {
        setFilenames(args[0]);
        setPlayerNames(args[1]);
        setBehaviors(args[2]);
        setTurns(args[3]);
        setCountOfGames(args[4]);


    }

    /**
     * Gets the filenames.
     *
     * @return the filenames
     */
    public List<String> getFilenames() {
        return filenames;
    }

    /**
     * Sets the filenames.
     *
     * @param filenames the new filenames
     */
    private void setFilenames(String filenames) {
        this.filenames = Arrays.asList(filenames.split(","));
    }

    /**
     * Gets the player names.
     *
     * @return the player names
     */
    public String getPlayerNames() {
        return playerNames;
    }

    /**
     * Sets the player names.
     *
     * @param playerNames the new player names
     */
    public void setPlayerNames(String playerNames) {
        this.playerNames = playerNames;
    }

    /**
     * Gets the behaviors.
     *
     * @return the behaviors
     */
    public String getBehaviors() {
        return behaviors;
    }

    /**
     * Sets the behaviors.
     *
     * @param behaviors the new behaviors
     */
    public void setBehaviors(String behaviors) {
        this.behaviors = behaviors;
    }

    /**
     * Gets the count of games.
     *
     * @return the count of games
     */
    public int getCountOfGames() {
        return countOfGames;
    }

    /**
     * Sets the count of games.
     *
     * @param countOfGames the new count of games
     */
    private void setCountOfGames(String countOfGames) {
        this.countOfGames = Integer.parseInt(countOfGames);
    }

    /**
     * Gets the turns.
     *
     * @return the turns
     */
    public int getTurns() {
        return turns;
    }

    /**
     * Sets the turns.
     *
     * @param turns the new turns
     */
    private void setTurns(String turns) {
        this.turns = Integer.parseInt(turns);
    }
}
