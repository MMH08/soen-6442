package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.entity.Country;
import com.soen.risk.views.CardExchangeView;
import com.soen.risk.views.DominationView;
import com.soen.risk.views.PhaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ReinforceInfoResponse.
 */
public class ReinforceInfoResponse implements Response {

    /** The countries. */
    private List<String> countries;
    
    /** The cards. */
    private List<String> cards;
    
    /** The reinforce army capacity. */
    private int reinforceArmyCapacity;
    
    /** The card exchange enabled. */
    private boolean cardExchangeEnabled;
    
    /** The end game. */
    private boolean endGame;
    
    /** The phase view. */
    private PhaseView phaseView;
    
    /** The domination view. */
    private DominationView dominationView;
    
    /** The card exchange view. */
    private CardExchangeView cardExchangeView;

    /**
     * Instantiates a new reinforce info response.
     */
    public ReinforceInfoResponse() {
        countries = new ArrayList<>();
    }

    /**
     * Gets the reinforce army capacity.
     *
     * @return the reinforce army capacity
     */
    public int getReinforceArmyCapacity() {
        return reinforceArmyCapacity;
    }

    /**
     * Sets the reinforce army capacity.
     *
     * @param reinforceArmyCapacity the new reinforce army capacity
     */
    public void setReinforceArmyCapacity(int reinforceArmyCapacity) {
        this.reinforceArmyCapacity = reinforceArmyCapacity;
    }

    /**
     * Gets the countries.
     *
     * @return the countries
     */
    public List<String> getCountries() {
        return countries;
    }

    /**
     * Sets the countries.
     *
     * @param countries the new countries
     */
    public void setCountries(List<Country> countries) {
        for (Country country : countries)
            this.countries.add(country.getName());
    }

    /**
     * Gets the cards.
     *
     * @return the cards
     */
    public List<String> getCards() {
        return cards;
    }

    /**
     * Sets the cards.
     *
     * @param cards the new cards
     */
    public void setCards(List<String> cards) {
        this.cards = cards;
    }

    /**
     * Gets the phase view.
     *
     * @return the phase view
     */
    public PhaseView getPhaseView() {
        return phaseView;
    }

    /**
     * Sets the phase view.
     *
     * @param phaseView the new phase view
     */
    public void setPhaseView(PhaseView phaseView) {
        this.phaseView = phaseView;
    }

    /**
     * Gets the domination view.
     *
     * @return the domination view
     */
    public DominationView getDominationView() {
        return dominationView;
    }

    /**
     * Sets the domination view.
     *
     * @param dominationView the new domination view
     */
    public void setDominationView(DominationView dominationView) {
        this.dominationView = dominationView;
    }

    /**
     * Checks if is card exchange enabled.
     *
     * @return true, if is card exchange enabled
     */
    public boolean isCardExchangeEnabled() {
        return cardExchangeEnabled;
    }

    /**
     * Sets the card exchange enabled.
     *
     * @param cardExchangeEnabled the new card exchange enabled
     */
    public void setCardExchangeEnabled(boolean cardExchangeEnabled) {
        this.cardExchangeEnabled = cardExchangeEnabled;
    }

    /**
     * Gets the card exchange view.
     *
     * @return the card exchange view
     */
    public CardExchangeView getCardExchangeView() {
        return cardExchangeView;
    }

    /**
     * Sets the card exchange view.
     *
     * @param cardExchangeView the new card exchange view
     */
    public void setCardExchangeView(CardExchangeView cardExchangeView) {
        this.cardExchangeView = cardExchangeView;
    }

    /**
     * Checks if is end game.
     *
     * @return true, if is end game
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     * Sets the end game.
     *
     * @param endGame the new end game
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}
