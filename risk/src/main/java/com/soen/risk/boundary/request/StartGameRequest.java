package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

/**
 * The Class StartGameRequest.
 */
public class StartGameRequest implements Request {
    
    /** The filename. */
    private String filename;
    
    /** The count of players. */
    private int countOfPlayers;

    /**
     * Instantiates a new start game request.
     *
     * @param args the args
     */
    public StartGameRequest(String... args) {
        this.filename = args[0];
        this.countOfPlayers = Integer.valueOf(args[1]);
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
     * Gets the count of players.
     *
     * @return the count of players
     */
    public int getCountOfPlayers() {
        return countOfPlayers;
    }

}
