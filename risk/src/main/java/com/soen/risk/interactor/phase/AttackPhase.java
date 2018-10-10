package com.soen.risk.interactor.phase;


import com.soen.risk.request.AttackPhaseRequest;
import com.soen.risk.interactor.PhaseFactory;

public class AttackPhase implements PhaseFactory.Phase<AttackPhaseRequest> {
    private String name;

    public AttackPhase() {
        this.name = "attackPhase";
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void begin() {

    }

    @Override
    public void execute(AttackPhaseRequest request) {

    }

    @Override
    public void exit() {

    }

    @Override
    public String getName() {
        return null;
    }
}
