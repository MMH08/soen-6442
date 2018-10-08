package com.soen.risk.interactor.phase;

import com.soen.risk.boundary.StartupPhaseRequest;
import com.soen.risk.entity.*;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.PhaseFactory;

public class StartupPhase implements PhaseFactory.Phase<StartupPhaseRequest> {
    private String name;
    private GamePlay gamePlay;

    public StartupPhase() {
        this.name = "startupPhase";
        this.gamePlay = GamePlay.getInstance();
    }

    public boolean isValid(){
        // TODO: Add method to skip the player in case all countries have been filled.
        // gamePlay.getCurrentPlayer()
        // in case of false, call exit()
        return true;
    }

    public void begin(){
    }

    @Override
    public void execute(StartupPhaseRequest request) {
        for (Country c : this.gamePlay.getGame().getMap().getCountries()) {
            if (c.getName().equals(request.getCountryName())) {
                c.setArmy(request.getArmyCount());
                break;
            }
        }
    }

    public void exit(){
        this.gamePlay.updateCurrentPlayer();
    }

    public String getName(){
        return this.name;
    }
}
