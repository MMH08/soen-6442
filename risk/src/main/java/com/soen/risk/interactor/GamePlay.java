package com.soen.risk.interactor;

import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
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

public class GamePlay extends Observable {
    private static Logger logger = Logger.getLogger(GamePlay.class.getName());
    private static GamePlay gamePlayInstance = null;
    private Game game;
    private Player currentPlayer;
    private String currentPhase = "startupPhase";
    private List<String> action = new ArrayList<>();

    public static GamePlay getInstance() {
        if (gamePlayInstance == null)
            gamePlayInstance = new GamePlay();
        return gamePlayInstance;
    }

    private GamePlay() {
    }

    /**
     * Perform all functionality of map after creating map object.
     * Gameplay will store the view object which will get updated as and when any player takes action.
     */
    public void buildGame(String filename, int countOfPlayers) {
        Map map = new Map();
        map.load(filename);
        if (map.isValid()) {
            this.game = new Game(map, countOfPlayers);
        }
    }

    public void initializeGame() {
        this.game.allocateInitialCountries();
        this.game.allocateInitialArmy();
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
                    break;
                } else {
                    this.setCurrentPhase(phases[i + 1]);
                    break;
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

    public void execute(){
        // output received from the phase execution on the current player.
        String outputString = "";
        this.updateAction(outputString);
    }


    private void setCurrentPlayer(Player currentPlayer) {
        logger.log(Level.INFO, "Changing player to " + currentPlayer.getName());
        this.currentPlayer = currentPlayer;
        this.setChanged();
        this.notifyObservers(this);
    }

    private void setCurrentPhase(String currentPhase) {
        logger.log(Level.INFO, "Changing phase to " + currentPhase);
        this.currentPhase = currentPhase;
        this.resetAction();
        this.setChanged();
        this.notifyObservers(this);
    }

    private void updateAction(String actionItem){
        this.action.add(actionItem);
        this.setChanged();
        this.notifyObservers(this);
    }

    private void resetAction(){
        this.action = new ArrayList<>();
        this.setChanged();
        this.notifyObservers(this);
    }

    public void endGame() {
        this.currentPhase = "";
    }

    public List<String> getAction() {
        return action;
    }
    public Game getGame() {
        return game;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String getCurrentPhase() {
        return currentPhase;
    }
}
