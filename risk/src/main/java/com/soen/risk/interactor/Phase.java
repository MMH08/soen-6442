package com.soen.risk.interactor;

import com.soen.risk.entity.Game;

public abstract class Phase {
    private GamePlay gamePlay = GamePlay.getInstance();

    public abstract void execute();

    public Game getGame(){
        return this.gamePlay.getGame();
    }


}
