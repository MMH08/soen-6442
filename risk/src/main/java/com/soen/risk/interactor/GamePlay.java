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
    private PhaseView phaseView;
    private DominationView dominationView;

    public static GamePlay getInstance() {
        if (gamePlayInstance == null)
            gamePlayInstance = new GamePlay();
        return gamePlayInstance;
    }

    private GamePlay() {
    }

    public Response start(Response response, String filename, int countOfPlayers) {
        game = new Game(filename, countOfPlayers);

        phaseView = new PhaseView();
        game.addObserver(phaseView);

        // register the observer - dominationView
        dominationView = new DominationView();
        for (Player player : game.getPlayers()) {
            player.addObserver(dominationView);
        }

        // record the changes in views
        game.initialize();
        return response;
    }

    private void end() {
    }

    // -----------------------------------------------------------------------------------------------------------------

    public Response getPhaseInfo(Response response) {
        if (game.getCurrentPhase().equals(Phase.STARTUP)) {
            ((StartupInfoResponse) response).setCountryName(game.getCurrentPlayer().nextCountryToAssignArmy());
            ((StartupInfoResponse) response).setArmyCapacity(game.getCurrentPlayer().getArmyCapacity());
        } else if (game.getCurrentPhase().equals(Phase.REINFORCE)) {
            game.getCurrentPlayer().calculateReinforceCount(game.getMap());
            ((ReinforceInfoResponse) response).setReinforceArmyCapacity(game.getCurrentPlayer().getArmyCapacity());
            ((ReinforceInfoResponse) response).setCountries(game.getCurrentPlayer().getCountryNames());
        } else if (game.getCurrentPhase().equals(Phase.ATTACK)) {
            ((AttackInfoResponse) response).setCountryNames(game.getCurrentPlayer().getCountryNames());
            ((AttackInfoResponse) response).setAllCountryNames(game.getMap().getCountryNames());
            for(Country country: game.getMap().getCountries()){
                ((AttackInfoResponse) response).addArmyCount(country.getArmy());
            }
        } else if (game.getCurrentPhase().equals(Phase.FORTIFY)) {
            ((FortifyInfoResponse) response).setCountryNames(game.getCurrentPlayer().getCountryNames());
            for (Country country : game.getCurrentPlayer().getCountries()) {
                ((FortifyInfoResponse) response).addArmyCount(country.getArmy());
            }
        }
        return response;
    }

    public void executeStartupPhase(String countryName, int armyCount) {
        Country country = game.getMap().findByCountryName(countryName);
        game.getCurrentPlayer().executeStartupPhase(country, armyCount);
        // updates
        game.updateCurrentPlayer();
        if (allPlayersHaveZeroArmy()) {
            game.updateCurrentPhase();
        }
    }

    public void executeReinforcePhase(ArrayList<Integer> armyCounts) {
        game.getCurrentPlayer().executeReinforcePhase(armyCounts);
        // updates
        game.updateCurrentPhase();
    }

    public void executeAttackPhase(String attackingCountry, String defendingCountry, int attackingDiceCount,
                                   int defendingDiceCount, int skipAttack, int allOutMode) {
        //Add players random issue one player get no army
        //Exception handling
        if (skipAttack == 1) {
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
            game.getCurrentPlayer().removeCountry(attackingCon);
            defendingPlayer.addCountry(attackingCon);
            //updates
            game.getCurrentPlayer().setAttackCounter(0);
            game.updateCurrentPhase();
        } else if (defendingCon.getArmy() == 0) {
            game.getCurrentPlayer().addCountry(defendingCon);
            defendingPlayer.removeCountry(defendingCon);
            defendingCon.setArmy(game.getCurrentPlayer().getAttackCounter());
            attackingCon.setArmy(attackingCon.getArmy() - game.getCurrentPlayer().getAttackCounter());
            //updates
            game.getCurrentPlayer().setAttackCounter(0);
            game.updateCurrentPhase();
        }

        if (game.getPlayers().size() == 1) {
            end();
        }

    }

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

}
