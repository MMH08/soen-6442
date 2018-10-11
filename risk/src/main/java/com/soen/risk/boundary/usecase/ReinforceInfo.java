package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.boundary.request.ReinforceInfoRequest;
import com.soen.risk.boundary.response.ReinforceInfoResponse;

public class ReinforceInfo implements Usecase {
    private ReinforceInfoRequest request;
    private ReinforceInfoResponse response;

    public ReinforceInfo(String... args){
        request = new ReinforceInfoRequest(args);
        response = new ReinforceInfoResponse();
    }
    @Override
    public ReinforceInfoResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        Player player = gamePlay.getCurrentPlayer();
        response.setReinforceArmyCapacity(player.getReinforceArmyCapacity(gamePlay.getGame().getMap()));
        response.setPlayerName(player.getName());
        response.setCountries(player.getCountryNames());
        return response;
    }
}
