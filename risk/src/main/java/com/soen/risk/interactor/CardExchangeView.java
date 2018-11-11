package com.soen.risk.interactor;

import com.soen.risk.entity.Player;

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
 * @since 05/11/2018
 * @version 1.0.2
 */
public class CardExchangeView implements Observer {
    private HashMap<String, List<String>> cards;
    private static Logger logger = Logger.getLogger(CardExchangeView.class.getName());

    CardExchangeView() {
        cards = new HashMap<>();
    }

    /**
     * Add the Player Name and Card names to the observable on card exchange.
     *
     * @param obs GamePlay instance to find the PlayerName and CardNames given
     * @param o
     */
    @Override
    public void update(Observable obs, Object o) {
        logger.log(Level.INFO, "Observing update card view - " + ((Player) obs).getCardNames());
        cards.put(((Player) obs).getName(), ((Player) obs).getCardNames());
    }

    public HashMap<String, List<String>> getCards() {
        return cards;
    }

    public void setCards(HashMap<String, List<String>> cards) {
        this.cards = cards;
    }
}
