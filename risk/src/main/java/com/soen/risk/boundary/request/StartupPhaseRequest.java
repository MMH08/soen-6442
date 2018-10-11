package com.soen.risk.boundary.request;

public class StartupPhaseRequest {
    private String countryName;
    private int armyCount;

    public StartupPhaseRequest(String... args) {
        this.armyCount = Integer.valueOf(args[0]);
        this.countryName = args[1];
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
