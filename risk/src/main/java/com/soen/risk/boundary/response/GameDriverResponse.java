package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

/**
 * The Class GameDriverResponse.
 */
public class GameDriverResponse implements Response {
    
    /** The phase name. */
    private String phaseName;
    
    /** The is game end. */
    private Boolean isGameEnd;

    /**
     * Gets the phase name.
     *
     * @return the phase name
     */
    public String getPhaseName() {
        return phaseName;
    }

    /**
     * Sets the phase name.
     *
     * @param phaseName the new phase name
     */
    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    /**
     * Gets the game end.
     *
     * @return the game end
     */
    public Boolean getGameEnd() {
        return isGameEnd;
    }

    /**
     * Sets the game end.
     *
     * @param gameEnd the new game end
     */
    public void setGameEnd(Boolean gameEnd) {
        isGameEnd = gameEnd;
    }
}
