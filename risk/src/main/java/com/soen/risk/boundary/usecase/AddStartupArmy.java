package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.StartupPhaseRequest;
import com.soen.risk.boundary.response.StartupPhaseResponse;
import com.soen.risk.interactor.GamePlay;

/**
 * The Class AddStartupArmy.
 */
public class AddStartupArmy implements Usecase {

    /**
     * The request.
     */
    private StartupPhaseRequest request;

    /**
     * The response.
     */
    private StartupPhaseResponse response;

    /**
     * Instantiates a new adds the startup army.
     *
     * @param args the args
     */
    public AddStartupArmy(String... args) {
        request = new StartupPhaseRequest(args);
        response = new StartupPhaseResponse();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public StartupPhaseResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.executeStartupPhase(request.getCountryName(), request.getArmyCount());
        return response;
    }
}
