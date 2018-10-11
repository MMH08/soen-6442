package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.phase.ReinforcePhase;
import com.soen.risk.boundary.request.ReinforcePhaseRequest;
import com.soen.risk.boundary.response.ReinforcePhaseResponse;

public class AddReinforceArmy implements Usecase {
    private ReinforcePhaseRequest request;
    private ReinforcePhaseResponse response;

    public AddReinforceArmy(String... args){
        request = new ReinforcePhaseRequest(args);

    }

    @Override
    public ReinforcePhaseResponse execute() {
        ReinforcePhase phase = new ReinforcePhase(this.request.getMap());
        if (phase.isValid()) {
            phase.begin();
            phase.execute();
            phase.exit();
        }
        return response;
    }
}
