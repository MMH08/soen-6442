package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.AttackInfoRequest;
import com.soen.risk.boundary.response.AttackInfoResponse;
import com.soen.risk.entity.Player;
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
    }

    @Override
    public AttackInfoResponse execute() {
        GamePlay gameplay = GamePlay.getInstance();
        response = (AttackInfoResponse) gameplay.getPhaseInfo(response);
        response.setPhaseView(gameplay.getPhaseView());
        response.setDominationView(gameplay.getDominationView());
        return response;
    }
}
