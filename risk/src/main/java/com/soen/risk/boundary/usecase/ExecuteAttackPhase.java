package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.AttackPhaseRequest;
import com.soen.risk.boundary.response.AttackPhaseResponse;

public class ExecuteAttackPhase implements Usecase {
    private AttackPhaseRequest request;
    private AttackPhaseResponse response;

    public ExecuteAttackPhase(){
        request = new AttackPhaseRequest();
        response = new AttackPhaseResponse();
    }

    @Override
    public AttackPhaseResponse execute() {
        return response;
    }
}
