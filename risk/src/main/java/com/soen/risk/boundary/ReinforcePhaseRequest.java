package com.soen.risk.boundary;

public class ReinforcePhaseRequest {
    private String countryName;
    private int armyCount;

    public ReinforcePhaseRequest(String countryNames, String armyCounts){
        //prepare hashmap - <countryName> <armycount>
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
