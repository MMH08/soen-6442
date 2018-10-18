package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.FortifyPhaseRequest;
import com.soen.risk.boundary.response.FortifyPhaseResponse;
import com.soen.risk.interactor.phase.FortifyPhase;

public class MoveFortifyArmy implements Usecase {
    private FortifyPhaseRequest request;
    private FortifyPhaseResponse response;

    public MoveFortifyArmy(String... args) {
        request = new FortifyPhaseRequest(args);
        response = new FortifyPhaseResponse();
    }

    @Override
    public FortifyPhaseResponse execute() {
        FortifyPhase phase = new FortifyPhase(request.getStartCountry(), request.getEndCountry(), request.getArmyCount());
        if (phase.isValid()) {
            phase.begin();
            phase.execute();
            phase.exit();
        }
        return response;
    }
}
