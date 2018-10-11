package com.soen.risk.interactor.phase;

import com.soen.risk.interactor.Phase;

public class FortifyPhase implements Phase {
    private String name;

    public FortifyPhase() {
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
    public void execute() {

    }

    @Override
    public void exit() {

    }

    @Override
    public String getName() {
        return null;
    }
}
