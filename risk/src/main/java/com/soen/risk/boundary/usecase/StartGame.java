package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.StartGameRequest;
import com.soen.risk.boundary.response.StartGameResponse;
import com.soen.risk.interactor.GameDriver;
import com.soen.risk.interactor.SingleMode;

/**
 * The Class StartGame.
 */
public class StartGame implements Usecase {

    /** The request. */
    private StartGameRequest request;
    
    /** The response. */
    private StartGameResponse response;

    /**
     * Instantiates a new start game.
     *
     * @param args the args
     */
    public StartGame(String... args) {
        request = new StartGameRequest(args);
        response = new StartGameResponse();
    }

    /**
     * Game play with the phaseView as it has both current phase and current player info.
     * Domination view with the List<Players> derived from game.
     * @return response object
     */
    @Override
    public StartGameResponse execute() {
        SingleMode singleMode = SingleMode.getInstance();
        singleMode.startGame(request.getFilename(), request.getCountOfPlayers());
        return response;
    }
}
