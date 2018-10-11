package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.boundary.request.StartupInfoRequest;
import com.soen.risk.boundary.response.StartupInfoResponse;

public class StartupInfo implements Usecase {
    private StartupInfoRequest request;
    private StartupInfoResponse response;

    public StartupInfo(String... args) {
        request = new StartupInfoRequest(args);
        response = new StartupInfoResponse();
    }

    @Override
    public StartupInfoResponse execute() {
        Player player = GamePlay.getInstance().getCurrentPlayer();

        response.setCountryName(player.nextCountryToAssignArmy().getName());
        response.setArmyCapacity(player.getArmyCapacity());
        response.setPlayerName(player.getName());
        response.setCountries(player.getCountryNames());
        return response;
    }
}
