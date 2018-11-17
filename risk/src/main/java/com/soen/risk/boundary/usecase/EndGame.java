package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GameDriver;

public class EndGame implements Usecase {


    @Override
    public Object execute() {
        GameDriver gameDriver = GameDriver.getInstance();
        gameDriver = null;
        return null;
    }
}
