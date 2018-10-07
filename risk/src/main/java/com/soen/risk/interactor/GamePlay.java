package com.soen.risk.interactor;

import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;

public class GamePlay {
    private static GamePlay gamePlayInstance = null;
    private Game game;

    public static GamePlay getInstance(){
        if(gamePlayInstance == null)
            gamePlayInstance = new GamePlay();
        return gamePlayInstance;
    }

    private GamePlay() {
    }

    public void build(String filename, int countOfPlayers){
        // Builder pattern
        Map map = new Map();
        map.load(filename);
        this.game = new Game(map, countOfPlayers);
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
