package com.soen.risk.interactor.phase;


import com.soen.risk.interactor.Phase;

public class AttackPhase implements Phase {
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
