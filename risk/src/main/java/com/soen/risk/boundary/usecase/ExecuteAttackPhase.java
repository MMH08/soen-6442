package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.AttackPhaseRequest;
import com.soen.risk.boundary.response.AttackPhaseResponse;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;
import com.soen.risk.entity.player.human.HumanAttackStrategy;
import com.soen.risk.interactor.GamePlay;

/**
 * The Class ExecuteAttackPhase.
 */
public class ExecuteAttackPhase implements Usecase {

    /**
     * The request.
     */
    private AttackPhaseRequest request;

    /**
     * The response.
     */
    private AttackPhaseResponse response;

    /**
     * Instantiates a new execute attack phase.
     *
     * @param args the args
     */
    public ExecuteAttackPhase(String... args) {
        request = new AttackPhaseRequest(args);
        response = new AttackPhaseResponse();
    }

    /**
     * Human strategy to receive the input arguments for attack phase using web UI.
     *
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public AttackPhaseResponse execute() {
        Game game = GamePlay.getInstance().getGame();
        Player player = game.getCurrentPlayer();
        Map map = game.getMap();

        player.setAttackStrategy(new HumanAttackStrategy(
                map.findByCountryName(request.getAttackingCountry()),
                map.findByCountryName(request.getDefendingCountry()),
                request.getAttackingDiceCount(),
                request.getDefendingDiceCount(),
                request.getSkipAttack(),
                request.getAllOutMode()
        ));

        game.executeAttackPhase();
        return response;
    }
}
