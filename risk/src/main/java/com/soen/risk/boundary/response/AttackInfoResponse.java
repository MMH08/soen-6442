package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.interactor.DominationView;
import com.soen.risk.interactor.PhaseView;

import java.util.List;

public class AttackInfoResponse implements Response {

    private List<String> countryNames;
    private List<String> allCountryNames;

    private PhaseView phaseView;
    private DominationView dominationView;

    public AttackInfoResponse() {
    }

    public List<String> getCountryNames() {
        return countryNames;
    }

    public void setCountryNames(List<String> countryNames) {
        this.countryNames = countryNames;
    }

    public List<String> getAllCountryNames() {
        return allCountryNames;
    }

    public void setAllCountryNames(List<String> allCountryNames) {
        this.allCountryNames = allCountryNames;
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
