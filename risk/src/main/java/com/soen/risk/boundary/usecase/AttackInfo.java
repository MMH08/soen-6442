package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.AttackInfoRequest;
import com.soen.risk.boundary.response.AttackInfoResponse;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;

/**
 * The Class AttackInfo.
 *
 * @author Nivetha
 */
public class AttackInfo implements Usecase {

    /**
     * The request.
     */
    private AttackInfoRequest request;

    /**
     * The response.
     */
    private AttackInfoResponse response;

    /**
     * Instantiates a new attack info.
     *
     * @param args the args
     */
    public AttackInfo(String... args) {
        request = new AttackInfoRequest();
        response = new AttackInfoResponse();
    }

    /**
     * Fetch the current scenario of game to display in the UI
     *
     * @see com.soen.risk.boundary.Usecase#execute()
     */
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
