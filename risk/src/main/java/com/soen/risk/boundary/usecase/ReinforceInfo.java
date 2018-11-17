package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.ReinforceInfoRequest;
import com.soen.risk.boundary.response.ReinforceInfoResponse;
import com.soen.risk.interactor.GameDriver;

public class ReinforceInfo implements Usecase  {

    private ReinforceInfoRequest request;
    private ReinforceInfoResponse response;

    public ReinforceInfo(String... args){
        request = new ReinforceInfoRequest(args);
        response = new ReinforceInfoResponse();
    }
    
    /**
     * Instantiates a new reinforce info for acting as Observer.
     *
     // @param subject the subject that notifies observers.
     */
//    public ReinforceInfo(CardExchange subject){
//        this.cardExchangeObject = subject;
//        this.cardExchangeObject.attach(this);
//     }
//
    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public ReinforceInfoResponse execute() {
       GameDriver gameDriver = GameDriver.getInstance();
        response = (ReinforceInfoResponse) gameDriver.getPhaseInfo(response);
        response.setCardExchangeView(gameDriver.getCardExchangeView());
        response.setPhaseView(GameDriver.getInstance().getPhaseView());
        response.setDominationView(GameDriver.getInstance().getDominationView());
        return response;
    }

//	@Override
//	public void update() {
//		GameDriver gamePlay = GameDriver.getInstance();
//		gamePlay.addNewArmies();
//	}
}
