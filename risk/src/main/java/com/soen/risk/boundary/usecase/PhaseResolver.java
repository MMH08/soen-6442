package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.boundary.request.PhaseResolverRequest;
import com.soen.risk.boundary.response.PhaseResolverResponse;

/**
 * The Class PhaseResolver.
 */
public class PhaseResolver implements Usecase {
    
    /** The request. */
    private PhaseResolverRequest request;
    
    /** The response. */
    private PhaseResolverResponse response;

    /**
     * Instantiates a new phase resolver.
     *
     * @param args the args
     */
    public PhaseResolver(String... args) {
        request = new PhaseResolverRequest(args);
        response = new PhaseResolverResponse();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public PhaseResolverResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        response.setPhaseName(gamePlay.getCurrentPhase());
        return response;
    }
}
