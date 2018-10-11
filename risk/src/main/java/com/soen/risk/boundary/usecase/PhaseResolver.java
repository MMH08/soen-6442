package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.boundary.request.PhaseResolverRequest;
import com.soen.risk.boundary.response.PhaseResolverResponse;

public class PhaseResolver implements Usecase {
    private PhaseResolverRequest request;
    private PhaseResolverResponse response;

    public PhaseResolver(String... args) {
        request = new PhaseResolverRequest(args);
        response = new PhaseResolverResponse();
    }

    @Override
    public PhaseResolverResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        response.setPhaseName(gamePlay.getCurrentPhase());
        return response;
    }
}
