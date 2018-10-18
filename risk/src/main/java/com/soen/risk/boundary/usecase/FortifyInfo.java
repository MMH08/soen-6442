package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.FortifyInfoRequest;
import com.soen.risk.boundary.response.FortifyInfoResponse;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;

public class FortifyInfo implements Usecase {
    private FortifyInfoRequest request;
    private FortifyInfoResponse response;

    public FortifyInfo(String... args) {
        this.request = new FortifyInfoRequest(args);
        this.response = new FortifyInfoResponse();
    }

    @Override
    public FortifyInfoResponse execute() {
        Player player = GamePlay.getInstance().getCurrentPlayer();
        response.setPlayerName(player.getName());
        response.setCountryNames(player.getCountryNames());

        for(Country country: player.getCountries()){
            response.addArmyCount(country.getArmy());
        }

        return response;
    }
}
