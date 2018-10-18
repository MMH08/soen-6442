package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.boundary.request.StartupInfoRequest;
import com.soen.risk.boundary.response.StartupInfoResponse;

/**
 * The Class StartupInfo.
 */
public class StartupInfo implements Usecase {
    
    /** The request. */
    private StartupInfoRequest request;
    
    /** The response. */
    private StartupInfoResponse response;

    /**
     * Instantiates a new startup info.
     *
     * @param args the args
     */
    public StartupInfo(String... args) {
        request = new StartupInfoRequest(args);
        response = new StartupInfoResponse();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
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
