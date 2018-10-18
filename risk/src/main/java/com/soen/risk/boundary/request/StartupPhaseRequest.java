package com.soen.risk.boundary.request;

/**
 * The Class StartupPhaseRequest.
 */
public class StartupPhaseRequest {
    
    /** The country name. */
    private String countryName;
    
    /** The army count. */
    private int armyCount;

    /**
     * Instantiates a new startup phase request.
     *
     * @param args the args
     */
    public StartupPhaseRequest(String... args) {
        this.armyCount = Integer.valueOf(args[0]);
        this.countryName = args[1];
    }

    /**
     * Gets the country name.
     *
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the country name.
     *
     * @param countryName the new country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
    public void setArmyCount(int armyCount) {
        this.armyCount = armyCount;
    }
}
