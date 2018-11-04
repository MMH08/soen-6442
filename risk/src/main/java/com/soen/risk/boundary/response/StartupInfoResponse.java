package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.interactor.DominationView;
import com.soen.risk.interactor.PhaseView;

import java.util.List;

/**
 * The Class StartupInfoResponse.
 */
public class StartupInfoResponse implements Response {
    
    /** The country name. */
    private String countryName;
    
    /** The army capacity. */
    private int armyCapacity;

    private PhaseView phaseView;

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

    public PhaseView getPhaseView() {
        return phaseView;
    }

    public void setPhaseView(PhaseView phaseView) {
        this.phaseView = phaseView;
    }

    public DominationView getDominationView() {
        return dominationView;
    }

    public void setDominationView(DominationView dominationView) {
        this.dominationView = dominationView;
    }
}
