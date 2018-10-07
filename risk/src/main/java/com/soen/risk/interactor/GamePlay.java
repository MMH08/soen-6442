package com.soen.risk.interactor;

import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;


public class GamePlay {
    private static GamePlay gamePlayInstance = null;
    private Game game;

    // status
    private Player currentPlayer;
    private Phase currentPhase;

    public static GamePlay getInstance(){
        if(gamePlayInstance == null)
            gamePlayInstance = new GamePlay();
        return gamePlayInstance;
    }

    private GamePlay() {
    }

    public void build(String filename, int countOfPlayers){
        // Builder pattern - startup phase 1
        Map map = new Map();
        map.load(filename);
        this.game = new Game(map, countOfPlayers);
        this.game.allocateInitialCountries();
        this.game.allocateInitialArmy();
        this.setCurrentPhase(PhaseFactory.build("startupPhase"));
    }

    public void updateCurrentPhase(){
        String[] phases = {"reinforcePhase", "attackPhase", "fortifyPhase"};
        // change phase in circular fashion.
    }

    public void updateCurrentPlayer(){
        // change player in circular fashion.

    }

    public Game getGame() {
        return game;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }
    //    public void executePhases() {
//        while (game.isNotOver()) {
//            for (Player player : this.game.getPlayers()) {
//                for (String phaseName : this.game.getPhases()) {
//                    PhaseFactory.Phase phase = PhaseFactory.build(phaseName);
//                    phase.execute();
//                }
//            }
//        }
//    }

}
