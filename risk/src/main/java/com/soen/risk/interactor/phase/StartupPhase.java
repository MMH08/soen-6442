package com.soen.risk.interactor.phase;

import com.soen.risk.boundary.StartupPhaseRequest;
import com.soen.risk.entity.Country;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.PhaseFactory;

public class StartupPhase implements PhaseFactory.Phase<StartupPhaseRequest> {
    private String name;
    private GamePlay gamePlay;

    public StartupPhase() {
        this.name = "startupPhase";
        this.gamePlay = GamePlay.getInstance();
    }

    public boolean isValid() {
        // TODO: check validity by iterating over players and find their army capacity.
        // if army capacity is 0 for all -- then exit startup phase.
        // if current player is having army capacity 0 then exit current phase i.e, change player -- call exit method.
        return true;
    }

    public void begin() {
    }

    @Override
    public void execute(StartupPhaseRequest request) {
        for (Country c : this.gamePlay.getGame().getMap().getCountries()) {
            if (c.getName().equals(request.getCountryName())) {
                c.setArmy(request.getArmyCount());
                this.gamePlay.getCurrentPlayer().setArmyCapacity(
                        this.gamePlay.getCurrentPlayer().getArmyCapacity() - request.getArmyCount());
                break;
            }
        }
    }

    public void exit() {
        this.gamePlay.updateCurrentPlayer();
    }

    public String getName() {
        return this.name;
    }
}
