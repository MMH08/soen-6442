package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.entity.Country;
import com.soen.risk.interactor.DominationView;
import com.soen.risk.interactor.PhaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class FortifyInfoResponse.
 */
public class FortifyInfoResponse implements Response {

    private List<String> countryNames;

    private boolean endGame;

    private PhaseView phaseView;

    private DominationView dominationView;

    public FortifyInfoResponse() {
        this.countryNames = new ArrayList<>();
    }

    public List<String> getCountryNames() {
        return countryNames;
    }

    public void setCountryNames(List<Country> countries) {
        for (Country country : countries)
            this.countryNames.add(country.getName());
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

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}
