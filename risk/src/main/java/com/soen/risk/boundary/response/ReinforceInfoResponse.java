package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.interactor.CardExchangeView;
import com.soen.risk.interactor.DominationView;
import com.soen.risk.interactor.PhaseView;

import java.util.List;

/**
 * The Class ReinforceInfoResponse.
 */
public class ReinforceInfoResponse implements Response {

    private List<String> countries;
    private List<String> cards;
    private int reinforceArmyCapacity;
    private boolean cardExchangeEnabled;
    private PhaseView phaseView;
    private DominationView dominationView;
    private CardExchangeView cardExchangeView;

    public int getReinforceArmyCapacity() {
        return reinforceArmyCapacity;
    }

    public void setReinforceArmyCapacity(int reinforceArmyCapacity) {
        this.reinforceArmyCapacity = reinforceArmyCapacity;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }

    public PhaseView getPhaseView() {
        return phaseView;
    }

    public void setPhaseView(PhaseView phaseView) {
        this.phaseView = phaseView;
    }

    public DominationView getDominationView() {
        return dominationView;
    }

    public void setDominationView(DominationView dominationView) {
        this.dominationView = dominationView;
    }

    public boolean isCardExchangeEnabled() {
        return cardExchangeEnabled;
    }

    public void setCardExchangeEnabled(boolean cardExchangeEnabled) {
        this.cardExchangeEnabled = cardExchangeEnabled;
    }

    public CardExchangeView getCardExchangeView() {
        return cardExchangeView;
    }

    public void setCardExchangeView(CardExchangeView cardExchangeView) {
        this.cardExchangeView = cardExchangeView;
    }
}
