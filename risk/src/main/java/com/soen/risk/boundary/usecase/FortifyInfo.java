package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.FortifyInfoRequest;
import com.soen.risk.boundary.response.FortifyInfoResponse;
import com.soen.risk.interactor.GameDriver;

/**
 * The Class FortifyInfo.
 */
public class FortifyInfo implements Usecase {

    /**
     * The request.
     */
    private FortifyInfoRequest request;

    /**
     * The response.
     */
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
        GameDriver gameDriver = GameDriver.getInstance();
        response = (FortifyInfoResponse) gameDriver.getPhaseInfo(response);
        response.setPhaseView(GameDriver.getInstance().getPhaseView());
        response.setDominationView(GameDriver.getInstance().getDominationView());
        return response;
    }
}
