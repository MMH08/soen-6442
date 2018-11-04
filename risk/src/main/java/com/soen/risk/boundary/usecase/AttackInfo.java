package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.AttackInfoRequest;
import com.soen.risk.boundary.response.AttackInfoResponse;
import com.soen.risk.interactor.DominationView;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.PhaseView;
import com.soen.risk.interactor.phase.AttackPhase;

import java.util.logging.Level;

/**
 * The Class AttackInfo.
 */
public class AttackInfo implements Usecase {

    private AttackInfoRequest request;
    private AttackInfoResponse response;

    public AttackInfo(String... args){
        request = new AttackInfoRequest();
        response = new AttackInfoResponse();
        logger.log(Level.INFO, "Exit by default");
        GamePlay.getInstance().updateCurrentPhase(); // TODO: change
    }

    @Override
    public Object execute() {
        response.setPhaseView(PhaseView.getInstance());
        response.setDominationView(DominationView.getInstance());
        return null;
    }
}
