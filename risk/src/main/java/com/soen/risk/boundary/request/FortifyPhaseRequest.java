package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

public class FortifyPhaseRequest implements Request {
    private String startCountry;
    private String endCountry;
    private int armyCount;

    public FortifyPhaseRequest(String... args) {
        setStartCountry(args[0]);
        setEndCountry(args[1]);
        setArmyCount(args[2]);
    }

    public String getStartCountry() {
        return startCountry;
    }

    public void setStartCountry(String startCountry) {
        this.startCountry = startCountry;
    }

    public String getEndCountry() {
        return endCountry;
    }

    public void setEndCountry(String endCountry) {
        this.endCountry = endCountry;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void setArmyCount(String armyCount) {
        this.armyCount = Integer.parseInt(armyCount);
    }
}
