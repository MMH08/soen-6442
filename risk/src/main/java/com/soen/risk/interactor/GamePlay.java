package com.soen.risk.interactor;

import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;

import java.util.Observable;
import java.util.Observer;

public class GamePlay implements Observer {
    private static GamePlay gamePlayInstance = null;
    private Game game;
    private Player currentPlayer;

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
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
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
