package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.phase.AttackPhase;

import java.util.logging.Level;

public class AttackInfo implements Usecase {

    public AttackInfo(String... args){
        logger.log(Level.INFO, "Exit by default");
        GamePlay.getInstance().updateCurrentPhase();
    }

    @Override
    public Object execute() {
        return null;
    }
}
