package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.interactor.DominationView;
import com.soen.risk.interactor.PhaseView;

import java.util.List;

/**
 * The Class ReinforceInfoResponse.
 */
public class ReinforceInfoResponse implements Response {
    
    /** The reinforce army capacity. */
    private int reinforceArmyCapacity;
    
    /** The countries. */
    private List<String> countries;

    private PhaseView phaseView;
    private DominationView dominationView;

    /**
     * Gets the reinforce army capacity.
     *
     * @return the reinforce army capacity
     */
    public int getReinforceArmyCapacity() {
        return reinforceArmyCapacity;
    }

    /**
     * Sets the reinforce army capacity.
     *
     * @param reinforceArmyCapacity the new reinforce army capacity
     */
    public void setReinforceArmyCapacity(int reinforceArmyCapacity) {
        this.reinforceArmyCapacity = reinforceArmyCapacity;
    }

    /**
     * Gets the countries.
     *
     * @return the countries
     */
    public List<String> getCountries() {
        return countries;
    }

    /**
     * Sets the countries.
     *
     * @param countries the new countries
     */
    public void setCountries(List<String> countries) {
        this.countries = countries;
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
