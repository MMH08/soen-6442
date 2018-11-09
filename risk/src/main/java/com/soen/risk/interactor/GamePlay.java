package com.soen.risk.interactor;

import com.soen.risk.boundary.Response;
import com.soen.risk.boundary.response.AttackInfoResponse;
import com.soen.risk.boundary.response.FortifyInfoResponse;
import com.soen.risk.boundary.response.ReinforceInfoResponse;
import com.soen.risk.boundary.response.StartupInfoResponse;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Phase;
import com.soen.risk.entity.Player;

import java.util.ArrayList;
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
 * @version 2.0.0
 * @since 2018-10-10
 */

public class GamePlay {
    private static Logger logger = Logger.getLogger(GamePlay.class.getName());

    private static GamePlay gamePlayInstance = null;

    private Game game;
    private PhaseView phaseView;
    private DominationView dominationView;
    private CardExchangeView cardExchangeView;

    public static GamePlay getInstance() {
        if (gamePlayInstance == null)
            gamePlayInstance = new GamePlay();
        return gamePlayInstance;
    }

    private GamePlay() {
    }

    /**
     * Response build for the Game Play object with necessary views
     * @param Response object to build 
     * @param fileName for initiating  Game
     * @param countOfPlayers for initiating Game 
     * @return response object
     */
    public Response start(Response response, String filename, int countOfPlayers) {
        game = new Game(filename, countOfPlayers);

        phaseView = new PhaseView();
        game.addObserver(phaseView);

        // register the observer - dominationView
        dominationView = new DominationView(game.getMap().getNumberOfCountries());
        cardExchangeView = new CardExchangeView();
        for (Player player : game.getPlayers()) {
            player.addObserver(dominationView);
            player.addObserver(cardExchangeView);
        }

        // record the changes in views
        game.initialize();
        for (Player player : game.getPlayers()) {
            for (Country country : player.getCountries()) {
                country.addObserver(dominationView);
            }
        }
        return response;
    }

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * Setting necessary phase information accordingly to phase name
     * @param Response object with phase info 
     * @return Response object with required info for each phase
     */
    public Response getPhaseInfo(Response response) {
        if (game.getCurrentPhase().equals(Phase.STARTUP)) {
            ((StartupInfoResponse) response).setCountryName(game.getCurrentPlayer().nextCountryToAssignArmy());
            ((StartupInfoResponse) response).setArmyCapacity(game.getCurrentPlayer().getArmyCapacity());
        } else if (game.getCurrentPhase().equals(Phase.REINFORCE)) {
            ((ReinforceInfoResponse) response).setReinforceArmyCapacity(game.getCurrentPlayer().calculateReinforceCount(game.getMap()));
            ((ReinforceInfoResponse) response).setCountries(game.getCurrentPlayer().getCountryNames());
            ((ReinforceInfoResponse) response).setCardExchangeEnabled(game.getCurrentPlayer().isCardExchangeEnabled());
        } else if (game.getCurrentPhase().equals(Phase.ATTACK)) {
            ((AttackInfoResponse) response).setCountryNames(game.getCurrentPlayer().getCountryNames());
            ((AttackInfoResponse) response).setAllCountryNames(game.getPlayerNeighbouringCountries());
        } else if (game.getCurrentPhase().equals(Phase.FORTIFY)) {
            ((FortifyInfoResponse) response).setCountryNames(game.getCurrentPlayer().getCountryNames());
            ((FortifyInfoResponse) response).setEndGame(game.isEndNear());
        }
        return response;
    }

   
    /**
     * To initiate startup Phase with necessary country and armies
     * @param countryName for the startup
     * @param armyCount to assign for each Country
     */
    public void executeStartupPhase(String countryName, int armyCount) {
        Country country = game.getMap().findByCountryName(countryName);
        game.getCurrentPlayer().executeStartupPhase(country, armyCount);
        // updates
        game.updateCurrentPlayer();
        if (allPlayersHaveZeroArmy()) {
            game.updateCurrentPhase();
        }
    }

    /**
     * To initiate Reinforce Phase with necessary armyCounts
     * @param List of armyCounts of a given player
     */
    public void executeReinforcePhase(ArrayList<Integer> armyCounts) {
        game.getCurrentPlayer().executeReinforcePhase(armyCounts);
        // updates
        game.getCurrentPlayer().resetExchangeArmy();
        game.updateCurrentPhase();
    }

    
    /**
     * To initiate Attack Phase with necessary data include options for Skip attack and All Out Mode
     * @param Attacking country-countryName
     * @param Defending country-countryName
     * @param attackingDiceCount- Dice Count of the Attacking country
     * @param defendingDiceCount- Dice Count of the Defending country
     * @param skipAttack-option for skipping atack by user
     * @param allOutMode- to set value for All out Mode 
     */
    public void executeAttackPhase(String attackingCountry, String defendingCountry, int attackingDiceCount,
                                   int defendingDiceCount, int skipAttack, int allOutMode) {
        //Add players random issue one player get no army
        //Exception handling
        if (skipAttack == 1) {
            logger.log(Level.INFO, "Skipping attack...");
            // add one random card and reset the win counter
            if(game.getCurrentPlayer().getWinCounter() > 0) {
                game.getCurrentPlayer().addRandomCard();
                game.getCurrentPlayer().setWinCounter(0);
            }
            game.getCurrentPlayer().setAttackCounter(0);
            game.updateCurrentPhase();
            return;
        }
        Country attackingCon = game.getMap().findByCountryName(attackingCountry);
        Country defendingCon = game.getMap().findByCountryName(defendingCountry);
        Player defendingPlayer = game.getPlayerFromCountry(defendingCountry);

        game.getCurrentPlayer().executeAttackPhase(defendingPlayer, attackingCon, defendingCon, attackingDiceCount,
                defendingDiceCount, allOutMode);

        if (attackingCon.getArmy() == 0) {
            logger.log(Level.INFO, "Attacking country lost all the army");
            game.getCurrentPlayer().removeCountry(attackingCon);
            defendingPlayer.addCountry(attackingCon);
            //updates
            game.getCurrentPlayer().setAttackCounter(0);
            //game.updateCurrentPhase();
        } else if (defendingCon.getArmy() == 0) {
            logger.log(Level.INFO, "Defending country lost all the army");
            if (attackingCon.getArmy() <= game.getCurrentPlayer().getAttackCounter()) {
                defendingCon.setArmy(attackingCon.getArmy() - 1);
                attackingCon.setArmy(1);
            } else {
                defendingCon.setArmy(game.getCurrentPlayer().getAttackCounter());
                attackingCon.setArmy(attackingCon.getArmy() - game.getCurrentPlayer().getAttackCounter());
            }
            game.getCurrentPlayer().setWinCounter(1);
            game.getCurrentPlayer().addCountry(defendingCon);
            defendingPlayer.removeCountry(defendingCon);
            //updates
            game.getCurrentPlayer().setAttackCounter(0);
        }
        // exchange cards if all countries are lost
        if(game.getCurrentPlayer().getCountries().size() == 0) {
            logger.log(Level.INFO, "all countries lost by attacker");
            game.getCurrentPlayer().sendCardsTo(defendingPlayer);
            game.dropPlayer(game.getCurrentPlayer());
            game.updateCurrentPhase();
        }
        else if (defendingPlayer.getCountries().size() == 0) {
            logger.log(Level.INFO, "all countries lost by defender");
            defendingPlayer.sendCardsTo(game.getCurrentPlayer());
            game.dropPlayer(defendingPlayer);
            game.updateCurrentPhase();
        }
    }

    /**
     * To initiate Fortification Phase with respective county and armies required
     * @param startcountry for army transfer
     * @param endCountry for army transfer
     * @param armyCount-number of armies to be trnasfered
     */
    public void executeFortificationPhase(String startCountry, String endCountry, int armyCount) {
        if (game.getMap().pathExists(startCountry, endCountry, game.getCurrentPlayer().getCountries())) {
            game.getCurrentPlayer().executeFortifyPhase(startCountry, endCountry, armyCount);
            // updates
            game.updateCurrentPhase();
            game.updateCurrentPlayer();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------


    private boolean allPlayersHaveZeroArmy() {
        for (Player p : game.getPlayers()) {
            if (p.getArmyCapacity() != 0) return false;
        }
        return true;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public PhaseView getPhaseView() {
        return phaseView;
    }

    public void setPhaseView(PhaseView phaseView) {
        this.phaseView = phaseView;
    }

    public DominationView getDominationView() {
        return dominationView;
    }

    public void setDominationView(DominationView dominationView) {
        this.dominationView = dominationView;
    }

    public CardExchangeView getCardExchangeView() {
        return cardExchangeView;
    }

    public void setCardExchangeView(CardExchangeView cardExchangeView) {
        this.cardExchangeView = cardExchangeView;
    }
}
