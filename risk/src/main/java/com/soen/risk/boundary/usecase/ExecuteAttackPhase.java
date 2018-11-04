package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.AttackPhaseRequest;
import com.soen.risk.boundary.response.AttackPhaseResponse;
import com.soen.risk.interactor.GamePlay;

public class ExecuteAttackPhase implements Usecase {
    private AttackPhaseRequest request;
    private AttackPhaseResponse response;

    public ExecuteAttackPhase(){
        request = new AttackPhaseRequest();
        response = new AttackPhaseResponse();
    }

    @Override
    public AttackPhaseResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.executeAttackPhase();

        return response;
    }
}
