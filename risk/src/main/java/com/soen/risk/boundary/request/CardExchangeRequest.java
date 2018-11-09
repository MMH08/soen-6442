package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * The Class CardExchangeRequest.
 */
public class CardExchangeRequest implements Request {


    /**
     * The card values.
     */
    private List<String> cards;

    /**
     * Instantiates a new card exchange request.
     *
     * @param args the args
     */
    public CardExchangeRequest(String... args) {
        this.cards = new ArrayList<>();
        setCards(args[0]);
    }


    /**
     * Sets the card values exchanged.
     *
     * @param cards the new set of cards to be exchanged
     */
    private void setCards(String cards) {
        logger.log(Level.INFO, "card values : " + cards);
        for (String card : cards.split(",")) {
            this.cards.add(card);
        }
    }

    public List<String> getCards() {
        return cards;
    }

}
