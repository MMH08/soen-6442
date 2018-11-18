package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.ReinforceInfoRequest;
import com.soen.risk.boundary.response.ReinforceInfoResponse;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Player;
import com.soen.risk.entity.ReinforceStrategy;
import com.soen.risk.interactor.GamePlay;

public class ReinforceInfo implements Usecase {

    private ReinforceInfoRequest request;
    private ReinforceInfoResponse response;

    public ReinforceInfo(String... args) {
        request = new ReinforceInfoRequest(args);
        response = new ReinforceInfoResponse();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public ReinforceInfoResponse execute() {
        Game game = GamePlay.getInstance().getGame();
        Player player = game.getCurrentPlayer();

        int reinforceCapacity = ReinforceStrategy.calculateArmyCount(game.getMap(), player.getCountries()) + player.getExchangeArmy();

        response.setCountries(player.getCountries());
        response.setReinforceArmyCapacity(reinforceCapacity);
        response.setCardExchangeEnabled(player.isCardExchangeEnabled());

        response.setCardExchangeView(GamePlay.getInstance().getCardExchangeView());
        response.setPhaseView(GamePlay.getInstance().getPhaseView());
        response.setDominationView(GamePlay.getInstance().getDominationView());

        return response;
    }

}
