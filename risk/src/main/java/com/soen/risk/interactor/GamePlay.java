package com.soen.risk.interactor;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;
import com.soen.risk.views.CardExchangeView;
import com.soen.risk.views.DominationView;
import com.soen.risk.views.PhaseView;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
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

public class GamePlay implements Serializable {
    
    /** The logger. */
    private static Logger logger = Logger.getLogger(GamePlay.class.getName());


    /** The game. */
    private Game game;

    /** The phase view. */
    private PhaseView phaseView;
    
    /** The domination view. */
    private DominationView dominationView;
    
    /** The card exchange view. */
    private CardExchangeView cardExchangeView;

    /** The game play instance. */
    private static GamePlay gamePlayInstance = null;

    /**
     * Gets the single instance of GamePlay.
     *
     * @return single instance of GamePlay
     */
    public static GamePlay getInstance() {
        if (gamePlayInstance == null)
            gamePlayInstance = new GamePlay();
        return gamePlayInstance;
    }

    /**
     * Sets the instance.
     *
     * @param gamePlay the new instance
     */
    public static void setInstance(GamePlay gamePlay) {
        logger.log(Level.INFO, "Setting instance");
        gamePlayInstance = gamePlay;
    }

    /**
     * Instantiates a new game play.
     */
    private GamePlay() {
    }

    /**
     * New game.
     *
     * @param map the map
     * @param players the players
     */
    public void newGame(Map map, List<Player> players) {
        this.game = new Game(map, players);

        // initialize views
        phaseView = new PhaseView();
        dominationView = new DominationView(game.getMap().getNumberOfCountries());
        cardExchangeView = new CardExchangeView();

        // register views
        registerGame();

        // register the observer - dominationView
        registerPlayers();

        game.setCurrentPlayer(players.get(0));
        game.allocateInitialCountries();
        game.allocateInitialArmies();

        registerCountries();
    }

    /**
     * Register countries.
     */
    public void registerCountries() {
        for (Player player : game.getPlayers()) {
            for (Country country : player.getCountries()) {
                logger.log(Level.INFO, "Registering country " + country.toString());
                country.addObserver(dominationView);
            }
        }
    }

    /**
     * Register players.
     */
    public void registerPlayers() {
        for (Player player : game.getPlayers()) {
            logger.log(Level.INFO, "Registering player " + player.getName());
            player.addObserver(dominationView);
            player.addObserver(cardExchangeView);
        }
    }

    /**
     * Register game.
     */
    public void registerGame() {
        logger.log(Level.INFO, "Registering phaseView");
        game.addObserver(phaseView);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Gets the game.
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the game.
     *
     * @param game the new game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Gets the phase view.
     *
     * @return the phase view
     */
    public PhaseView getPhaseView() {
        return phaseView;
    }

    /**
     * Gets the domination view.
     *
     * @return the domination view
     */
    public DominationView getDominationView() {
        return dominationView;
    }

    /**
     * Gets the card exchange view.
     *
     * @return the card exchange view
     */
    public CardExchangeView getCardExchangeView() {
        return cardExchangeView;
    }
}

