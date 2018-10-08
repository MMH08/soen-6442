package com.soen.risk.interactor.phase;

import com.soen.risk.boundary.ReinforcePhaseRequest;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.PhaseFactory;

public class ReinforcePhase implements PhaseFactory.Phase<ReinforcePhaseRequest> {

    private String name;

    public ReinforcePhase() {
        this.name = "reinforcePhase";
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void begin() {

    }

    @Override
    public void execute(ReinforcePhaseRequest request) {
        // TODO: work in loop (hashmap)
        Map m = GamePlay.getInstance().getGame().getMap();
        for (Country c : m.getCountries()) {
            if (c.getName().equals(request.getCountryName())) {
                c.setArmy(c.getArmy() + request.getArmyCount());
                break;
            }
        }
    }

    @Override
    public void exit() {

    }

    @Override
    public String getName() {
        return null;
    }
}
