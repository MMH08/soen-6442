package com.soen.risk.interactor;

import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h2>Game Play</h2>
 * <ul>
 * <li>Create Map class object to perform all functionality of map class i.e. allocating initial armies etc.
 * <li>Change Current Phase
 * <li>Change Current player after its turn
 * </ul>
 *
 * @author Varun Singhal
 * @version 1.0.0
 * @since 2018-10-10
 */

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

    /**
     * Perform all functionality of map after creating map object.
     *
     * @param filename       Path of file along with its file name
     * @param countOfPlayers Number of players playing game
     */
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

    /**
     * Changing current phase between reinforcement, attack, and fortify.
     */
    public void updateCurrentPhase() {
        if (this.getCurrentPhase().equals("startupPhase")) {
            this.setCurrentPhase("reinforcePhase");
            return;
        }

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
    }

    /**
     * Change current player after its turn is over.
     */
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

    /**
     * Update Current Player to next player after completing player's turn.
     *
     * @param currentPlayer
     */
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
