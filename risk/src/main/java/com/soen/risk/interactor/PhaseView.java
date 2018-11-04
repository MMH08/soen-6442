package com.soen.risk.interactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PhaseView will be attached to observe Gameplay only when the game play is built.
 * Make phaseView Singleton to re-use the view instance and get the updates. The phaseView will be attached
 *
 * @author Varun Singhal
 * @since 02/11/2018
 */
public class PhaseView implements Observer {

    private String phaseName = "";
    private String playerName = "";
    private List<String> action = new ArrayList<>();
    private static Logger logger = Logger.getLogger(PhaseView.class.getName());
    private static PhaseView phaseViewInstance = null;

    /**
     * Singleton implementation of phase view to re-use the attached observer instance.
     *
     * @return Instantiated phase view object.
     */
    public static PhaseView getInstance() {
        if (phaseViewInstance == null)
            phaseViewInstance = new PhaseView();
        return phaseViewInstance;
    }

    /**
     * Update the phaseName and playerName on each set statement of GamePlay.
     *
     * @param obs GamePlay instance to find the current player and current phase.
     * @param o
     */
    @Override
    public void update(Observable obs, Object o) {
        playerName = ((GamePlay) obs).getCurrentPlayer().toString();
        phaseName = ((GamePlay) obs).getCurrentPhase();
        action = ((GamePlay) obs).getAction();
        logger.log(Level.INFO, "player name" + playerName);
        logger.log(Level.INFO, "current phase" + phaseName);
    }

    public String getPhaseName() {
        return phaseName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getAction() {
        return action;
    }
}
