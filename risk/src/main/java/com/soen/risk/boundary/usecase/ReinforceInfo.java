package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.boundary.request.ReinforceInfoRequest;
import com.soen.risk.boundary.response.ReinforceInfoResponse;

/**
 * The Class ReinforceInfo.
 */
public class ReinforceInfo implements Usecase {
    
    /** The request. */
    private ReinforceInfoRequest request;
    
    /** The response. */
    private ReinforceInfoResponse response;

    /**
     * Instantiates a new reinforce info.
     *
     * @param args the args
     */
    public ReinforceInfo(String... args){
        request = new ReinforceInfoRequest(args);
        response = new ReinforceInfoResponse();
    }
    
    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public ReinforceInfoResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        response = (ReinforceInfoResponse) gamePlay.getPhaseInfo(response);
        response.setPhaseView(GamePlay.getInstance().getPhaseView());
        response.setDominationView(GamePlay.getInstance().getDominationView());
        return response;
    }
}
