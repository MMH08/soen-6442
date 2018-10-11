package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

public class FortifyInfoResponse implements Response {
    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
