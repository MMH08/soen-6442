package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GamePlay;

/**
 * The Class EndGame.
 */
public class EndGame implements Usecase {


    /**
     * Use case to manual interrupt the game.
     *
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public Object execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay = null;
        return null;
    }
}
