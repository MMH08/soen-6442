package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.boundary.request.PhaseResolverRequest;
import com.soen.risk.boundary.response.GameDriverResponse;

/**
 * The Class GameDriver.
 */
public class GameDriver implements Usecase {
    
    /** The request. */
    private PhaseResolverRequest request;
    
    /** The response. */
    private GameDriverResponse response;

    /**
     * Instantiates a new phase resolver.
     *
     * @param args the args
     */
    public GameDriver(String... args) {
        request = new PhaseResolverRequest(args);
        response = new GameDriverResponse();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public GameDriverResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        response.setPhaseName(gamePlay.getGame().getCurrentPhase().toString());
        return response;
    }
}
