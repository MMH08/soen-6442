package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

import java.util.List;

public class FortifyInfoResponse implements Response {
    private String playerName;
    private List<String> countryNames;

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
}
