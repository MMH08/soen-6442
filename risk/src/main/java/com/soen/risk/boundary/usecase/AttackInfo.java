package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.AttackInfoRequest;
import com.soen.risk.boundary.response.AttackInfoResponse;
import com.soen.risk.interactor.GamePlay;

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
    }

    @Override
    public AttackInfoResponse execute() {
        GamePlay gameplay = GamePlay.getInstance();

        response.setPhaseView(gameplay.getPhaseView());
        response.setDominationView(gameplay.getDominationView());
        return response;
    }
}
