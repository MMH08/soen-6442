package com.soen.risk.interactor;

import com.soen.risk.entity.*;
import com.soen.risk.views.CardExchangeView;
import com.soen.risk.views.DominationView;
import com.soen.risk.views.PhaseView;

import java.util.List;
import java.util.logging.Logger;

/**
 * <h2>Game Play</h2>
 * <ul>
 * <li>Create Map class object to perform all functionality of map class i.e. allocating initial armies etc.
 * <li>Change Current Phase
 * <li>Change Current player after its turn
 *
 * </ul>
 *
 * @author Varun Singhal
 * @version 1.0.2
 * @since 2018-11-1
 */

public class GamePlay {
    private static Logger logger = Logger.getLogger(GamePlay.class.getName());


    private Game game;

    private PhaseView phaseView;
    private DominationView dominationView;
    private CardExchangeView cardExchangeView;

    private static GamePlay gamePlayInstance = null;

    public static GamePlay getInstance() {
        if (gamePlayInstance == null)
            gamePlayInstance = new GamePlay();
        return gamePlayInstance;
    }

    private GamePlay() {
    }

    public void newGame(Map map, List<Player> players) {
        this.game = new Game(map, players);

        // register views
        phaseView = new PhaseView();
        game.addObserver(phaseView);

        // register the observer - dominationView
        dominationView = new DominationView(game.getMap().getNumberOfCountries());
        cardExchangeView = new CardExchangeView();
        for (Player player : game.getPlayers()) {
            player.addObserver(dominationView);
            player.addObserver(cardExchangeView);
        }

        game.setCurrentPlayer(players.get(0));
        game.allocateInitialCountries();
        game.allocateInitialArmies();

        for (Player player : game.getPlayers()) {
            for (Country country : player.getCountries()) {
                country.addObserver(dominationView);
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public PhaseView getPhaseView() {
        return phaseView;
    }

    public DominationView getDominationView() {
        return dominationView;
    }

    public CardExchangeView getCardExchangeView() {
        return cardExchangeView;
    }
}

