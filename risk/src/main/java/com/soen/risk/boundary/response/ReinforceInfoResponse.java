package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

import java.util.List;

public class ReinforceInfoResponse implements Response {
    private int reinforceArmyCapacity;
    private String playerName;
    private List<String> countries;

    public int getReinforceArmyCapacity() {
        return reinforceArmyCapacity;
    }

    public void setReinforceArmyCapacity(int reinforceArmyCapacity) {
        this.reinforceArmyCapacity = reinforceArmyCapacity;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
}
