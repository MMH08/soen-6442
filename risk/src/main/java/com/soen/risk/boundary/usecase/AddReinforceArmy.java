package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.phase.ReinforcePhase;
import com.soen.risk.boundary.request.ReinforcePhaseRequest;
import com.soen.risk.boundary.response.ReinforcePhaseResponse;

/**
 * The Class AddReinforceArmy.
 */
public class AddReinforceArmy implements Usecase {
    
    /** The request. */
    private ReinforcePhaseRequest request;
    
    /** The response. */
    private ReinforcePhaseResponse response;

    /**
     * Instantiates a new adds the reinforce army.
     *
     * @param args the args
     */
    public AddReinforceArmy(String... args){
        request = new ReinforcePhaseRequest(args);

    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public ReinforcePhaseResponse execute() {
        ReinforcePhase phase = new ReinforcePhase(this.request.getArmyCounts());
        if (phase.isValid()) {
            phase.begin();
            phase.execute();
            phase.exit();
        }
        return response;
    }
}
