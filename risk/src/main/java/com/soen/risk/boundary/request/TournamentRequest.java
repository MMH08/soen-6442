package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

import java.util.Arrays;
import java.util.List;

public class TournamentRequest implements Request {
    private List<String> filenames;
    private String playerNames;
    private String behaviors;
    private int countOfGames;
    private int turns;

    public TournamentRequest(String... args) {
        setFilenames(args[0]);
        setPlayerNames(args[1]);
        setBehaviors(args[2]);
        setTurns(args[3]);
        setCountOfGames(args[4]);


    }

    public List<String> getFilenames() {
        return filenames;
    }

    private void setFilenames(String filenames) {
        this.filenames = Arrays.asList(filenames.split(","));
    }

    public String getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String playerNames) {
        this.playerNames = playerNames;
    }

    public String getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(String behaviors) {
        this.behaviors = behaviors;
    }

    public int getCountOfGames() {
        return countOfGames;
    }

    private void setCountOfGames(String countOfGames) {
        this.countOfGames = Integer.parseInt(countOfGames);
    }

    public int getTurns() {
        return turns;
    }

    private void setTurns(String turns) {
        this.turns = Integer.parseInt(turns);
    }
}
