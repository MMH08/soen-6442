package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.FortifyInfoRequest;
import com.soen.risk.boundary.response.FortifyInfoResponse;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.DominationView;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.PhaseView;

/**
 * The Class FortifyInfo.
 */
public class FortifyInfo implements Usecase {
    
    /** The request. */
    private FortifyInfoRequest request;
    
    /** The response. */
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
        Player player = GamePlay.getInstance().getCurrentPlayer();
        response.setCountryNames(player.getCountryNames());

        for(Country country: player.getCountries()){
            response.addArmyCount(country.getArmy());
        }

        response.setPhaseView(PhaseView.getInstance());
        response.setDominationView(DominationView.getInstance());

        return response;
    }
}
