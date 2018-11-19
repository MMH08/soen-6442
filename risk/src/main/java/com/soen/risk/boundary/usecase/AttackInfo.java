package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.AttackInfoRequest;
import com.soen.risk.boundary.response.AttackInfoResponse;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;

/**
 * The Class AttackInfo.
 */
public class AttackInfo implements Usecase {

    private AttackInfoRequest request;
    private AttackInfoResponse response;

    public AttackInfo(String... args) {
        request = new AttackInfoRequest();
        response = new AttackInfoResponse();
    }

    @Override
    public AttackInfoResponse execute() {
        // domain objects
        GamePlay gameplay = GamePlay.getInstance();
        Game game = gameplay.getGame();
        Player player = game.getCurrentPlayer();
        //
        response.setCountryNames(player.getCountries());
        response.setAllCountryNames(game.getMap().getAdjCountries(player.getCountries()));
        // views
        response.setPhaseView(gameplay.getPhaseView());
        response.setDominationView(gameplay.getDominationView());

        return response;
    }
}
