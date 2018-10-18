package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.phase.AttackPhase;

import java.util.logging.Level;

/**
 * The Class AttackInfo.
 */
public class AttackInfo implements Usecase {

    /**
     * Instantiates a new attack info.
     *
     * @param args the args
     */
    public AttackInfo(String... args){
        logger.log(Level.INFO, "Exit by default");
        GamePlay.getInstance().updateCurrentPhase();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public Object execute() {
        return null;
    }
}
