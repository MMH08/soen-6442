package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.FortifyPhaseRequest;
import com.soen.risk.boundary.response.FortifyPhaseResponse;
import com.soen.risk.interactor.GamePlay;

/**
 * The Class MoveFortifyArmy.
 */
public class MoveFortifyArmy implements Usecase {
    
    /** The request. */
    private FortifyPhaseRequest request;
    
    /** The response. */
    private FortifyPhaseResponse response;

    /**
     * Instantiates a new move fortify army.
     *
     * @param args the args
     */
    public MoveFortifyArmy(String... args) {
        request = new FortifyPhaseRequest(args);
        response = new FortifyPhaseResponse();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public FortifyPhaseResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.executeFortificationPhase(request.getStartCountry(), request.getEndCountry(), request.getArmyCount());

        return response;
    }
}
