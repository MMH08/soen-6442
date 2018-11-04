package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.FortifyInfoRequest;
import com.soen.risk.boundary.response.FortifyInfoResponse;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Player;
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

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public FortifyInfoResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        Player player = gamePlay.getGame().getCurrentPlayer();

        response.setCountryNames(player.getCountryNames());

        for (Country country : player.getCountries()) {
            response.addArmyCount(country.getArmy());
        }

        response.setPhaseView(GamePlay.getInstance().getPhaseView());
        response.setDominationView(GamePlay.getInstance().getDominationView());

        return response;
    }
}
