package com.soen.risk.interactor.phase;

import com.soen.risk.request.FortifyPhaseRequest;
import com.soen.risk.interactor.PhaseFactory;

public class FortifyPhase implements PhaseFactory.Phase<FortifyPhaseRequest> {
    private String name;

    public FortifyPhase(){
        this.name = "fortifyPhase";
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void begin() {

    }

    @Override
    public void execute(FortifyPhaseRequest request) {

    }

    @Override
    public void exit() {

    }

    @Override
    public String getName() {
        return null;
    }
}
