package com.soen.risk.views;

import com.soen.risk.entity.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * PhaseView will be attached to observe Gameplay only when the game play is built.
 * Make phaseView Singleton to re-use the view instance and get the updates. The phaseView will be attached
 *
 * @author varun
 * @version 1.0.2
 * @since 02/11/2018
 */
public class PhaseView implements Observer, Serializable {

    /** The phase name. */
    private String phaseName = "";
    
    /** The player name. */
    private String playerName = "";
    
    /** The actions. */
    private List<String> actions = new ArrayList<>();
    
    /** The logger. */
    private static Logger logger = Logger.getLogger(PhaseView.class.getName());

    /**
     * Update the phaseName and playerName on each set statement of GamePlay.
     *
     * @param obs GamePlay instance to find the current player and current phase.
     * @param o the o
     */
    @Override
    public void update(Observable obs, Object o) {
        Game game = (Game) obs;
        playerName = game.getCurrentPlayer().toString();
        phaseName = game.getCurrentPhase().toString();


    }
    //------------------------------------------------------
   
    
    
    /**
     * Gets the phase name.
     *
     * @return the phase name
     */
    public String getPhaseName() {
        return phaseName;
    }

    /**
     * Gets the player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the actions.
     *
     * @return the actions
     */
    public List<String> getActions() {
        return actions;
    }
}
