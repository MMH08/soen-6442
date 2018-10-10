package com.soen.risk.request;

public class StartupPhaseRequest {
    private String countryName;
    private int armyCount;

    public StartupPhaseRequest(String countryName, int armyCount){
        this.armyCount = armyCount;
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void setArmyCount(int armyCount) {
        this.armyCount = armyCount;
    }
}
