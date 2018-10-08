package com.soen.risk.interactor.phase;

import com.soen.risk.boundary.StartupPhaseRequest;
import com.soen.risk.entity.*;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.PhaseFactory;

public class StartupPhase implements PhaseFactory.Phase<StartupPhaseRequest> {
    private String name;

    public StartupPhase() {
        this.name = "startupPhase";
    }

    public boolean isValid(){
        return false;
    }

    public void begin(){

    }

    @Override
    public void execute(StartupPhaseRequest request) {
        Map m = GamePlay.getInstance().getGame().getMap();
        for (Country c : m.getCountries()) {
            if (c.getName().equals(request.getCountryName())) {
                c.setArmy(request.getArmyCount());
                break;
            }
        }
    }

    public void exit(){

    }

    public String getName(){
        return this.name;
    }
}
