package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.boundary.request.StartGameRequest;
import com.soen.risk.boundary.response.StartGameResponse;

public class StartGame implements Usecase {

    private StartGameRequest request;
    private StartGameResponse response;

    public StartGame(String... args) {
        request = new StartGameRequest(args);
        response = new StartGameResponse();
    }

    @Override
    public StartGameResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.build(request.getFilename(), request.getCountOfPlayers());
        return response;
    }
}
