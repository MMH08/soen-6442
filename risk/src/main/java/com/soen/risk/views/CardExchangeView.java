package com.soen.risk.views;

import com.soen.risk.entity.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h2>CardExchangeView</h2>
 * CardExchangeView Class will be attached to the GamePlay during the reinforcement Phase.
 * Exits after a card exchange happens.
 *
 * @author Nivedha
 * @version 1.0.2
 * @since 05/11/2018
 */
public class CardExchangeView implements Observer, Serializable {
    
    /** The cards. */
    private HashMap<String, List<String>> cards;
    
    /** The logger. */
    private static Logger logger = Logger.getLogger(CardExchangeView.class.getName());

    /**
     * Instantiates a new card exchange view.
     */
    public CardExchangeView() {
        cards = new HashMap<>();
    }

    /**
     * Add the Player Name and Card names to the observable on card exchange.
     *
     * @param obs GamePlay instance to find the PlayerName and CardNames given
     * @param o the o
     */
    @Override
    public void update(Observable obs, Object o) {
        logger.log(Level.INFO, "Observing update card view - " + ((Player) obs).getCardNames());
        cards.put(((Player) obs).getName(), ((Player) obs).getCardNames());
    }

    /**
     * Gets the cards.
     *
     * @return the cards
     */
    public HashMap<String, List<String>> getCards() {
        return cards;
    }

    /**
     * Sets the cards.
     *
     * @param cards the cards
     */
    public void setCards(HashMap<String, List<String>> cards) {
        this.cards = cards;
    }
}
