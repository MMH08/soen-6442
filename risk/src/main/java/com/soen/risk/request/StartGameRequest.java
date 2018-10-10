package com.soen.risk.request;

import com.soen.risk.boundary.Request;

import java.util.Arrays;
import java.util.logging.Level;

public class StartGameRequest implements Request {
    private String filename;
    private int countOfPlayers;

    public StartGameRequest(String... args) {
        logger.log(Level.INFO, Arrays.toString(args));
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
