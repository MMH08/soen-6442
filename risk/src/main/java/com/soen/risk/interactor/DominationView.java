package com.soen.risk.interactor;

import com.soen.risk.entity.Player;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * Use case to build the game play will be responsible to attach the domination view to all the players that are
 * playing the game. The observer should be attached after the players have been spawned, i.e.,
 * in the build method of GamePlay.
 */
public class DominationView implements Observer {

    private static Logger logger = Logger.getLogger(PhaseView.class.getName());
    private static DominationView dominationViewInstance = null;

    /**
     * Singleton implementation of domination view to re-use the attached observer instance.
     *
     * @return Instantiated domination view object.
     */
    public static DominationView getInstance() {
        if (dominationViewInstance == null)
            dominationViewInstance = new DominationView();
        return dominationViewInstance;
    }


    @Override
    public void update(Observable obs, Object o) {
        String name = ((Player) obs).getName();
        List<String> countries = ((Player) obs).getCountryNames();
    }
}
