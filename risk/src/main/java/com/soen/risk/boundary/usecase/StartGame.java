package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.StartGameRequest;
import com.soen.risk.boundary.response.StartGameResponse;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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

        //initialise map
        Map map = new Map();
        map.load(request.getFilename());
        if(!map.isValid()) return response;

        //initialise players
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < request.getPlayerNames().size(); i++)
            players.add(new Player(request.getPlayerNames().get(i), request.getPlayerBehaviors().get(i)));

        // initialise game
        gamePlay.newGame(map, players);

        return response;
    }
}
