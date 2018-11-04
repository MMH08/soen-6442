package com.soen.risk.boundary.request;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.soen.risk.boundary.Request;

/**
 * The Class CardExchangeRequest.
 */
public class CardExchangeRequest implements Request {
	
	    
    /** The card values. */
    private List<String> cardValues;

    /**
     * Instantiates a new card exchange request.
     *
     * @param args the args
     */
    public CardExchangeRequest(String... args) {
    	setCardsExchanged(args[0]);
    }

    /**
     * Gets the card values exchanged.
     *
     * @return the card names
     */
    public List<String> getCardsExchanged() {
        return cardValues;
    }

    /**
     * Sets the card values exchanged.
     *
     * @param cards the new set of cards to be exchanged
     */
    public void setCardsExchanged(String cards) {
    	logger.log(Level.INFO, "card values : " + cards);
        this.cardValues = new ArrayList<>();
        for (String card : cards.split(",")) {
            this.cardValues.add(card);
        }
    }

}
