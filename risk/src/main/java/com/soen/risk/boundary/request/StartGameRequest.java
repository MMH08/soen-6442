package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

import java.util.Arrays;
import java.util.List;

public class StartGameRequest implements Request {

    private String filename;
    private List<String> playerNames;
    private List<String> playerBehaviors;

    public StartGameRequest(String... args) {
        this.filename = args[0];
        this.playerNames = Arrays.asList(args[1].split(","));
        this.playerBehaviors = Arrays.asList(args[2].split(","));

    }

    public String getFilename() {
        return filename;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public List<String> getPlayerBehaviors() {
        return playerBehaviors;
    }

}
