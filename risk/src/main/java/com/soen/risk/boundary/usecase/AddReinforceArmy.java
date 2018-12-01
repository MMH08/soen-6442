package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.ReinforcePhaseRequest;
import com.soen.risk.boundary.response.ReinforcePhaseResponse;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.player.human.HumanReinforceStrategy;
import com.soen.risk.interactor.GamePlay;

/**
 * The Class AddReinforceArmy.
 *
 * @author varun
 */
public class AddReinforceArmy implements Usecase {

    /**
     * The request.
     */
    private ReinforcePhaseRequest request;

    /**
     * The response.
     */
    private ReinforcePhaseResponse response;

    /**
     * Instantiates a new adds the reinforce army.
     *
     * @param args the args
     */
    public AddReinforceArmy(String... args) {
        request = new ReinforcePhaseRequest(args);

    }

    /**
     * Execute reinforce phase with user input provided by the UI layer.
     *
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public ReinforcePhaseResponse execute() {
        Game game = GamePlay.getInstance().getGame();
        game.getCurrentPlayer().setReinforceStrategy(new HumanReinforceStrategy(request.getArmyCounts()));
        game.executeReinforcePhase();
        return response;
    }
}
