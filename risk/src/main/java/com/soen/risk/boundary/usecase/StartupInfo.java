package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.StartupInfoRequest;
import com.soen.risk.boundary.response.StartupInfoResponse;
import com.soen.risk.interactor.GameDriver;
import com.soen.risk.interactor.SingleMode;

/**
 * The Class StartupInfo.
 */
public class StartupInfo implements Usecase {

    /**
     * The request.
     */
    private StartupInfoRequest request;

    /**
     * The response.
     */
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

    @Override
    public StartupInfoResponse execute() {
        SingleMode singleMode = SingleMode.getInstance();
        response = (StartupInfoResponse) singleMode.getPhaseInfo(response);

        response.setPhaseView(singleMode.getPhaseView());
        response.setDominationView(singleMode.getDominationView());

        return response;
    }
}
