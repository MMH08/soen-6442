package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

/**
 * The Class FortifyPhaseRequest.
 */
public class FortifyPhaseRequest implements Request {
    
    /** The start country. */
    private String startCountry;
    
    /** The end country. */
    private String endCountry;
    
    /** The army count. */
    private int armyCount;

    /**
     * Instantiates a new fortify phase request.
     *
     * @param args the args
     */
    public FortifyPhaseRequest(String... args) {
        setStartCountry(args[0]);
        setEndCountry(args[1]);
        setArmyCount(args[2]);
    }

    /**
     * Gets the start country.
     *
     * @return the start country
     */
    public String getStartCountry() {
        return startCountry;
    }

    /**
     * Sets the start country.
     *
     * @param startCountry the new start country
     */
    public void setStartCountry(String startCountry) {
        this.startCountry = startCountry;
    }

    /**
     * Gets the end country.
     *
     * @return the end country
     */
    public String getEndCountry() {
        return endCountry;
    }

    /**
     * Sets the end country.
     *
     * @param endCountry the new end country
     */
    public void setEndCountry(String endCountry) {
        this.endCountry = endCountry;
    }

    /**
     * Gets the army count.
     *
     * @return the army count
     */
    public int getArmyCount() {
        return armyCount;
    }

    /**
     * Sets the army count.
     *
     * @param armyCount the new army count
     */
    public void setArmyCount(String armyCount) {
        this.armyCount = Integer.parseInt(armyCount);
    }
}
