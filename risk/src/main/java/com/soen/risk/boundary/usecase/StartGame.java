package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.boundary.request.StartGameRequest;
import com.soen.risk.boundary.response.StartGameResponse;

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

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public StartGameResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.build(request.getFilename(), request.getCountOfPlayers());
        // Register the observer here
        // Game play with the phaseView as it has all the current scenario statements.
        // Domination view with the List<Players> derived from game.
        return response;
    }
}
