package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GamePlay;

public class EndGame implements Usecase {


    @Override
    public Object execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay = null;
        return null;
    }
}
