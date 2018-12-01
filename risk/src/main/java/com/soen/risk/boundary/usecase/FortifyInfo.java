package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.FortifyInfoRequest;
import com.soen.risk.boundary.response.FortifyInfoResponse;
import com.soen.risk.entity.Game;
import com.soen.risk.interactor.GamePlay;

/**
 * The Class FortifyInfo.
 */
public class FortifyInfo implements Usecase {

    /**
     * The request.
     */
    private FortifyInfoRequest request;

    /**
     * The response.
     */
    private FortifyInfoResponse response;

    /**
     * Instantiates a new fortify info.
     *
     * @param args the args
     */
    public FortifyInfo(String... args) {
        this.request = new FortifyInfoRequest(args);
        this.response = new FortifyInfoResponse();
    }

    /**
     * Generate information screen for the fortify phase.
     *
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public FortifyInfoResponse execute() {
        Game game = GamePlay.getInstance().getGame();
        //domain objects
        response.setCountryNames(game.getCurrentPlayer().getCountries());
        //views
        response.setPhaseView(GamePlay.getInstance().getPhaseView());
        response.setDominationView(GamePlay.getInstance().getDominationView());

        return response;
    }
}
