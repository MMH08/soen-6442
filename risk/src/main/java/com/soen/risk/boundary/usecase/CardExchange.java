package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.CardExchangeRequest;
import com.soen.risk.boundary.response.CardExchangeResponse;
import com.soen.risk.entity.Card;
import com.soen.risk.interactor.GamePlay;

import java.util.HashMap;
import java.util.logging.Level;


public class CardExchange implements Usecase {

    /**
     * The request.
     */
    private CardExchangeRequest request;

    /**
     * The response.
     */
    private CardExchangeResponse response;

    /**
     * Instantiates a new card exchange.
     *
     * @param args the args
     */
    public CardExchange(String... args) {
        request = new CardExchangeRequest(args);
        response = new CardExchangeResponse();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    private void removeCard(String[] cards) {
        logger.log(Level.INFO, "Valid scenario - card exchange");
        GamePlay.getInstance().getGame().getCurrentPlayer().removeCard(cards);
        GamePlay.getInstance().getGame().getCurrentPlayer().assignExchangeArmies();
    }

    private void validationCheck(String str) {
        HashMap<String, Integer> hp = new HashMap<>();
        String[] temp = str.split(", ");
        Card[] allCards = Card.values();
        for (int i = 0; i < temp.length; i++) {
            if (!hp.isEmpty()) {
                if (!hp.containsKey(temp[i])) {
                    hp.put(temp[i], 1);
                } else {
                    hp.put(temp[i], hp.get(temp[i]) + 1);
                }
            } else {
                hp.put(temp[i], 1);
            }
        }
        for (String key : hp.keySet()) {
            logger.log(Level.INFO, key + " " + hp.get(key));
        }
        if (hp.size() == 3) {
            removeCard(temp);
        } else {
            int flag = 0;
            for (String key : hp.keySet()) {
                if (hp.get(key) == 3) {
                    flag = 1;
                }
            }
            if (flag == 1)
                removeCard(temp);
        }
    }

    public CardExchangeResponse execute() {
        logger.log(Level.INFO, request.getCards().toString());
        String cardVal = request.getCards().toString().substring(1, request.getCards().toString().length() - 1);
        logger.log(Level.INFO, cardVal);

        if (cardVal.split(", ").length == 3) {
            validationCheck(cardVal);
        }

        return response;
    }


}
