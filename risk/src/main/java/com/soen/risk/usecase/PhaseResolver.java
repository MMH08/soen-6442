package com.soen.risk.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.request.PhaseResolverRequest;
import com.soen.risk.response.PhaseResolverResponse;

public class PhaseResolver implements Usecase {
    private PhaseResolverRequest request;
    private PhaseResolverResponse response;

    public PhaseResolver(String... args) {
        request = new PhaseResolverRequest(args);
        response = new PhaseResolverResponse();
    }

    @Override
    public void execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        response.setPhaseName(gamePlay.getCurrentPhase().getName());
    }

    @Override
    public PhaseResolverResponse getResponse() {
        return response;
    }
}
