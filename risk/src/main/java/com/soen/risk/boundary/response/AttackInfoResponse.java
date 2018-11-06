package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.interactor.DominationView;
import com.soen.risk.interactor.PhaseView;

import java.util.ArrayList;
import java.util.List;

public class AttackInfoResponse implements Response {

    private List<String> countryNames;
    private List<String> allCountryNames;

    private PhaseView phaseView;
    private DominationView dominationView;
    private List<Integer> armyCounts;

    public AttackInfoResponse() {
        this.armyCounts = new ArrayList<>();
    }

    public void addArmyCount(int count) {
        armyCounts.add(count);
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

    public List<Integer> getArmyCounts() {
        return armyCounts;
    }

    public void setArmyCounts(List<Integer> armyCounts) {
        this.armyCounts = armyCounts;
    }
}
