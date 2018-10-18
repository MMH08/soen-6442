package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

import java.util.ArrayList;
import java.util.List;

public class FortifyInfoResponse implements Response {
    private String playerName;
    private List<String> countryNames;
    private List<Integer> armyCounts;

    public FortifyInfoResponse() {
        this.armyCounts = new ArrayList<>();
        this.countryNames = new ArrayList<>();
    }

    public void addArmyCount(int count) {
        armyCounts.add(count);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    public List<String> getCountryNames() {
        return countryNames;
    }

    public void setCountryNames(List<String> countryNames) {
        this.countryNames = countryNames;
    }

    public List<Integer> getArmyCounts() {
        return armyCounts;
    }

    public void setArmyCounts(List<Integer> armyCounts) {
        this.armyCounts = armyCounts;
    }
}
