package com.soen.risk.interactor;

import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GamePlay {
    private static Logger logger = Logger.getLogger(GamePlay.class.getName());

    private static GamePlay gamePlayInstance = null;
    private Game game;

    // status
    private Player currentPlayer;
    private String currentPhase;

    public static GamePlay getInstance() {
        if (gamePlayInstance == null)
            gamePlayInstance = new GamePlay();
        return gamePlayInstance;
    }

    private GamePlay() {
    }

    public void build(String filename, int countOfPlayers) {
        // Builder pattern - startup phase 1
        Map map = new Map();
        map.load(filename);
        this.game = new Game(map, countOfPlayers);
        this.game.allocateInitialCountries();
        this.game.allocateInitialArmy();
        this.setCurrentPhase("startupPhase");
        this.setCurrentPlayer(this.game.getPlayers().get(0));
    }

    public void updateCurrentPhase() {
        String[] phases = {"reinforcePhase", "attackPhase", "fortifyPhase"};
        for (int i = 0; i < phases.length; i++) {
            if (currentPhase.equals(phases[i])) {
                if (i == phases.length - 1) {
                    this.setCurrentPhase(phases[0]);
                } else {
                    this.setCurrentPhase(phases[i + 1]);
                }
            }
        }
        // change phase in circular fashion.
    }

    public void updateCurrentPlayer() {

        int count = 0;
        for (Player p : game.getPlayers()) {
            if (p.getName().equals(currentPlayer.getName())) {
                if (count == game.getPlayers().size() - 1) {
                    this.setCurrentPlayer(game.getPlayers().get(0));
                } else {
                    this.setCurrentPlayer(game.getPlayers().get(count + 1));
                }
                break;
            }
            count++;
        }

    }

    public Game getGame() {
        return game;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void setCurrentPlayer(Player currentPlayer) {
        logger.log(Level.INFO, "Changing player to " + currentPlayer.getName());
        this.currentPlayer = currentPlayer;
    }

    public String getCurrentPhase() {
        return currentPhase;
    }

    private void setCurrentPhase(String currentPhase) {
        logger.log(Level.INFO, "Changing phase to " + currentPhase);
        this.currentPhase = currentPhase;
    }


}
