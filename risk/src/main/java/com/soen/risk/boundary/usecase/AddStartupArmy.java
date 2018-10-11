package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.phase.StartupPhase;
import com.soen.risk.boundary.request.StartupPhaseRequest;
import com.soen.risk.boundary.response.StartupPhaseResponse;

public class AddStartupArmy implements Usecase {
    private StartupPhaseRequest request;
    private StartupPhaseResponse response;

    public AddStartupArmy(String... args) {
        request = new StartupPhaseRequest(args);
        response = new StartupPhaseResponse();
    }

    @Override
    public StartupPhaseResponse execute() {
        StartupPhase phase = new StartupPhase(request.getCountryName(), request.getArmyCount());
        if (phase.isValid()) {
            phase.begin();
            phase.execute();
            phase.exit();
        }
        return response;
    }
}
