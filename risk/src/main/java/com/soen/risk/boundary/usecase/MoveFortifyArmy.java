package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.FortifyPhaseRequest;
import com.soen.risk.boundary.response.FortifyPhaseResponse;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.player.human.HumanFortifyStrategy;
import com.soen.risk.interactor.GamePlay;

/**
 * The Class MoveFortifyArmy.
 */
public class MoveFortifyArmy implements Usecase {

    /**
     * The request.
     */
    private FortifyPhaseRequest request;

    /**
     * The response.
     */
    private FortifyPhaseResponse response;

    /**
     * Instantiates a new move fortify army.
     *
     * @param args the args
     */
    public MoveFortifyArmy(String... args) {
        request = new FortifyPhaseRequest(args);
        response = new FortifyPhaseResponse();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public FortifyPhaseResponse execute() {
        Game game = GamePlay.getInstance().getGame();
        //domain
        Map map = game.getMap();
        Country startCountry = map.findByCountryName(request.getStartCountry());
        Country endCountry = map.findByCountryName(request.getEndCountry());
        //strategy
        game.getCurrentPlayer().setFortifyStrategy(new HumanFortifyStrategy(startCountry, endCountry, request.getArmyCount()));
        game.executeFortificationPhase();

        return response;
    }
}
