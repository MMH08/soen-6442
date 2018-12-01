package com.soen.risk.boundary.response;

import java.util.ArrayList;
import java.util.List;

public class TournamentResponse {
    private List<String> winners;
    private List<String> mapNames;
    private List<String> gameCounts;

    public TournamentResponse() {
        winners = new ArrayList<>();
        mapNames = new ArrayList<>();
        gameCounts = new ArrayList<>();
    }

    public List<String> getWinners() {
        return winners;
    }

    public void addWinner(String winner) {
        this.winners.add(winner);
    }

    public List<String> getMapNames() {
        return mapNames;
    }

    public void addMapName(String mapName) {
        this.mapNames.add(mapName);
    }

    public List<String> getGameCounts() {
        return gameCounts;
    }

    public void addGameCount(String gameCount) {
        this.gameCounts.add(gameCount);
    }
}
