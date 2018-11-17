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

public class SingleMode {
    private static SingleMode singleModeInstance = null;

    private Game game;
    private GameDriver gameDriver;

    private PhaseView phaseView;
    private DominationView dominationView;
    private CardExchangeView cardExchangeView;

    public static SingleMode getInstance() {
        if (singleModeInstance == null)
            singleModeInstance = new SingleMode();
        return singleModeInstance;
    }

    private SingleMode() {
        this.gameDriver = new GameDriver();
    }

    /**
     * Response build for the Game Play object with necessary views
     * //     * @param Response object to build
     * //     * @param fileName for initiating  Game
     *
     * @param countOfPlayers for initiating Game
     * @return response object
     */
    public void startGame(String filename, int countOfPlayers) {
        this.game = new Game(filename, countOfPlayers);

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
    }


    /**
     * Setting necessary phase information accordingly to phase name
     * //* @param Response object with phase info
     *
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
