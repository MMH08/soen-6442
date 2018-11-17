package com.soen.risk.interactor;

import com.soen.risk.entity.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * PhaseView will be attached to observe Gameplay only when the game play is built.
 * Make phaseView Singleton to re-use the view instance and get the updates. The phaseView will be attached
 *
 * @author Varun Singhal
 * @since 02/11/2018
 * @version 1.0.2
 */
public class PhaseView implements Observer {

    private String phaseName = "";
    private String playerName = "";
    private List<String> actions = new ArrayList<>();
    private static Logger logger = Logger.getLogger(PhaseView.class.getName());

    /**
     * Update the phaseName and playerName on each set statement of GameDriver.
     *
     * @param obs GameDriver instance to find the current player and current phase.
     * @param o
     */
    @Override
    public void update(Observable obs, Object o) {
        Game game = (Game) obs;
        playerName = game.getCurrentPlayer().toString();
        phaseName = game.getCurrentPhase().toString();


    }
    //------------------------------------------------------
   
    
    
    public String getPhaseName() {
        return phaseName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getActions() {
        return actions;
    }
}
