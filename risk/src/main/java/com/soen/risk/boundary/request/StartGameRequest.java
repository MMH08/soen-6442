package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

public class StartGameRequest implements Request {
    private String filename;
    private int countOfPlayers;

    public StartGameRequest(String... args) {
        this.filename = args[0];
        this.countOfPlayers = Integer.valueOf(args[1]);
    }

    public String getFilename() {
        return filename;
    }

    public int getCountOfPlayers() {
        return countOfPlayers;
    }

}
