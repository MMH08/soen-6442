package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.AttackPhaseRequest;
import com.soen.risk.boundary.response.AttackPhaseResponse;
import com.soen.risk.interactor.GameDriver;

public class ExecuteAttackPhase implements Usecase {
    private AttackPhaseRequest request;
    private AttackPhaseResponse response;

    public ExecuteAttackPhase(String... args){
        request = new AttackPhaseRequest(args);
        response = new AttackPhaseResponse();
    }

    @Override
    public AttackPhaseResponse execute() {
        GameDriver gameDriver = GameDriver.getInstance();
        gameDriver.executeAttackPhase(request.getAttackingCountry(), request.getDefendingCountry(),
                request.getAttackingDiceCount(), request.getDefendingDiceCount(), request.getSkipAttack(),
                request.getAllOutMode());
        return response;
    }
}
