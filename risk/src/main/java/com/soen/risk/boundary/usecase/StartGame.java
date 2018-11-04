package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.DominationView;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.boundary.request.StartGameRequest;
import com.soen.risk.boundary.response.StartGameResponse;
import com.soen.risk.interactor.PhaseView;

import java.util.Observer;

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
        GamePlay gamePlay = GamePlay.getInstance();
        // phase view
        PhaseView phaseView = PhaseView.getInstance();
        gamePlay.addObserver(phaseView);

        gamePlay.buildGame(request.getFilename(), request.getCountOfPlayers());

        // register the observer - dominationView
        DominationView dominationView = DominationView.getInstance();
        for(Player player : gamePlay.getGame().getPlayers()){
            player.addObserver(dominationView);
        }

        gamePlay.initializeGame();
        return response;
    }
}
