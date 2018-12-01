package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

import java.util.Arrays;
import java.util.List;

/**
 * The Class StartGameRequest.
 */
public class StartGameRequest implements Request {

    /** The filename. */
    private String filename;
    
    /** The player names. */
    private List<String> playerNames;
    
    /** The player behaviors. */
    private List<String> playerBehaviors;

    /**
     * Instantiates a new start game request.
     *
     * @param args the args
     */
    public StartGameRequest(String... args) {
        this.filename = args[0];
        this.playerNames = Arrays.asList(args[1].split(","));
        this.playerBehaviors = Arrays.asList(args[2].split(","));

    }

    /**
     * Gets the filename.
     *
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Gets the player names.
     *
     * @return the player names
     */
    public List<String> getPlayerNames() {
        return playerNames;
    }

    /**
     * Gets the player behaviors.
     *
     * @return the player behaviors
     */
    public List<String> getPlayerBehaviors() {
        return playerBehaviors;
    }

}
