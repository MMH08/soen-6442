package com.soen.risk.boundary.usecase;

import java.util.ArrayList;
import java.util.List;

import com.soen.risk.boundary.CardObserver;
import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.CardExchangeRequest;
import com.soen.risk.boundary.response.CardExchangeResponse;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;


public class CardExchange implements Usecase{

	/** The request. */
    private CardExchangeRequest request;
    
    /** The response. */
    private CardExchangeResponse response;

    /** The observers. */
    private List<CardObserver> observers = new ArrayList<CardObserver>();
    
    /**
     * Instantiates a new card exchange.
     *
     * @param args the args
     */
    public CardExchange(String... args) {
        request = new CardExchangeRequest(args);
        response = new CardExchangeResponse();
    }
     
    public void attach(CardObserver observer){
        observers.add(observer);		
    }
    
    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public CardExchangeResponse execute() {
    	GamePlay gamePlay = GamePlay.getInstance();
    	boolean bExchanged = gamePlay.executeExchange(request.getCardsExchanged());
         if(bExchanged) {
         response.setObservers(observers);
         response.setState(1);
         }
         
    	return response;   	
    }
    
   
}
