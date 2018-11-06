package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.CardObserver;
import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.ReinforceInfoRequest;
import com.soen.risk.boundary.response.ReinforceInfoResponse;
import com.soen.risk.interactor.GamePlay;

/**
 * The Class ReinforceInfo.
 */
public class ReinforceInfo extends CardObserver implements Usecase  {
    
    /** The request. */
    private ReinforceInfoRequest request;
    
    /** The response. */
    private ReinforceInfoResponse response;

    /**
     * Instantiates a new reinforce info.
     *
     * @param args the args
     */
    public ReinforceInfo(String... args){
        request = new ReinforceInfoRequest(args);
        response = new ReinforceInfoResponse();
    }
    
    /**
     * Instantiates a new reinforce info for acting as Observer.
     *
     * @param subject the subject that notifies observers.
     */
    public ReinforceInfo(CardExchange subject){
        this.cardExchangeObject = subject;
        this.cardExchangeObject.attach(this);
     }
    
    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public ReinforceInfoResponse execute() {
        GamePlay gamePlay = GamePlay.getInstance();
<<<<<<< HEAD
        Player player = gamePlay.getCurrentPlayer();
        response.setReinforceArmyCapacity(player.getReinforceArmyCapacity(gamePlay.getGame().getMap()));
        response.setPlayerName(player.getName());
        response.setCountries(player.getCountryNames());
        response.setPlayerCards(player.getCards());
=======
        response = (ReinforceInfoResponse) gamePlay.getPhaseInfo(response);
        response.setPhaseView(GamePlay.getInstance().getPhaseView());
        response.setDominationView(GamePlay.getInstance().getDominationView());
>>>>>>> build2
        return response;
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		GamePlay gamePlay = GamePlay.getInstance();
        Player player = gamePlay.getCurrentPlayer();
		player.setCardExchangeArmies();
	}
}
