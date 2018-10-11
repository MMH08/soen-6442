package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

public class StartupInfoResponse implements Response {
    private String playerName;
    private String countryName;
    private int armyCapacity;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getArmyCapacity() {
        return armyCapacity;
    }

    public void setArmyCapacity(int armyCapacity) {
        this.armyCapacity = armyCapacity;
    }
}
