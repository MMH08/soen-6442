package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.interactor.DominationView;
import com.soen.risk.interactor.PhaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class FortifyInfoResponse.
 */
public class FortifyInfoResponse implements Response {
    
    /** The player name. */
    private String playerName;
    
    /** The country names. */
    private List<String> countryNames;
    
    /** The army counts. */
    //private List<Integer> armyCounts;

    private PhaseView phaseView;

    private DominationView dominationView;

    /**
     * Instantiates a new fortify info response.
     */
    public FortifyInfoResponse() {
        //this.armyCounts = new ArrayList<>();
        this.countryNames = new ArrayList<>();
    }


//    public void addArmyCount(int count) {
//        armyCounts.add(count);
//    }

    /**
     * Gets the player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the player name.
     *
     * @param playerName the new player name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    /**
     * Gets the country names.
     *
     * @return the country names
     */
    public List<String> getCountryNames() {
        return countryNames;
    }

    /**
     * Sets the country names.
     *
     * @param countryNames the new country names
     */
    public void setCountryNames(List<String> countryNames) {
        this.countryNames = countryNames;
    }

    /**
     * Gets the army counts.
     *
     * @return the army counts
     */
//    public List<Integer> getArmyCounts() {
//        return armyCounts;
//    }

//    public void setArmyCounts(List<Integer> armyCounts) {
//        this.armyCounts = armyCounts;
//    }

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
