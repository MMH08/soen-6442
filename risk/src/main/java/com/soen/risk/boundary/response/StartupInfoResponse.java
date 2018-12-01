package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.views.DominationView;
import com.soen.risk.views.PhaseView;

/**
 * The Class StartupInfoResponse.
 */
public class StartupInfoResponse implements Response {
    
    /** The country name. */
    private String countryName;
    
    /** The army capacity. */
    private int armyCapacity;

    /** The phase view. */
    private PhaseView phaseView;

    /** The domination view. */
    private DominationView dominationView;

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
     * Gets the army capacity.
     *
     * @return the army capacity
     */
    public int getArmyCapacity() {
        return armyCapacity;
    }

    /**
     * Sets the army capacity.
     *
     * @param armyCapacity the new army capacity
     */
    public void setArmyCapacity(int armyCapacity) {
        this.armyCapacity = armyCapacity;
    }

    /**
     * Gets the phase view.
     *
     * @return the phase view
     */
    public PhaseView getPhaseView() {
        return phaseView;
    }

    /**
     * Sets the phase view.
     *
     * @param phaseView the new phase view
     */
    public void setPhaseView(PhaseView phaseView) {
        this.phaseView = phaseView;
    }

    /**
     * Gets the domination view.
     *
     * @return the domination view
     */
    public DominationView getDominationView() {
        return dominationView;
    }

    /**
     * Sets the domination view.
     *
     * @param dominationView the new domination view
     */
    public void setDominationView(DominationView dominationView) {
        this.dominationView = dominationView;
    }
}
