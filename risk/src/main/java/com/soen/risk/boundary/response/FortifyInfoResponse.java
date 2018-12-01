package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.entity.Country;
import com.soen.risk.views.DominationView;
import com.soen.risk.views.PhaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class FortifyInfoResponse.
 */
public class FortifyInfoResponse implements Response {

    /** The country names. */
    private List<String> countryNames;

    /** The end game. */
    private boolean endGame;

    /** The phase view. */
    private PhaseView phaseView;

    /** The domination view. */
    private DominationView dominationView;

    /**
     * Instantiates a new fortify info response.
     */
    public FortifyInfoResponse() {
        this.countryNames = new ArrayList<>();
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
     * @param countries the new country names
     */
    public void setCountryNames(List<Country> countries) {
        for (Country country : countries)
            this.countryNames.add(country.getName());
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

    /**
     * Checks if is end game.
     *
     * @return true, if is end game
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     * Sets the end game.
     *
     * @param endGame the new end game
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}
