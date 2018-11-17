package com.soen.risk.interactor;

import com.soen.risk.entity.*;
import com.soen.risk.entity.player.human.HumanAttackStrategy;
import com.soen.risk.entity.player.human.HumanFortifyStrategy;
import com.soen.risk.entity.player.human.HumanReinforceStrategy;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h2>Game Play</h2>
 * <ul>
 * <li>Create Map class object to perform all functionality of map class i.e. allocating initial armies etc.
 * <li>Change Current Phase
 * <li>Change Current player after its turn
 *
 * </ul>
 *
 * @author Varun Singhal
 * @version 1.0.2
 * @since 2018-11-1
 */

public class GameDriver {
    private static Logger logger = Logger.getLogger(GameDriver.class.getName());

    private boolean isAttackWon;

    private ReinforceStrategy reinforceStrategy;
    private AttackStrategy attackStrategy;
    private FortifyStrategy fortifyStrategy;

    public GameDriver() {
    }

    // -----------------------------------------------------------------------------------------------------------------


    /**
     * To initiate startup Phase with necessary country and armies
     *
     * @param countryName for the startup
     * @param armyCount   to assign for each Country
     */
    public void executeStartupPhase(Game game, String countryName, int armyCount) {
        Country country = game.getMap().findByCountryName(countryName);
        game.getCurrentPlayer().addArmy(country, armyCount);
        // updates
        game.updateCurrentPlayer();
        if (game.allPlayersHaveZeroArmy()) game.updateCurrentPhase();
    }

    /**
     * To initiate Reinforce Phase with necessary armyCounts
     * //     * @param List of armyCounts of a given player
     */
    public void executeReinforcePhase(Game game, ArrayList<Integer> armyCounts) {
        game.getCurrentPlayer().reinforce(new HumanReinforceStrategy(armyCounts));
        // updates
        game.getCurrentPlayer().resetExchangeArmy();
        game.updateCurrentPhase();
    }


    /**
     * To initiate Attack Phase with necessary data include options for Skip attack and All Out Mode
     * //     * @param Attacking country-countryName
     * //     * @param Defending country-countryName
     *
     * @param attackingDiceCount- Dice Count of the Attacking country
     * @param defendingDiceCount- Dice Count of the Defending country
     * @param skipAttack-option   for skipping atack by user
     * @param allOutMode-         to set value for All out Mode
     */
    public void executeAttackPhase(Game game, String attackingCountry, String defendingCountry, int attackingDiceCount,
                                   int defendingDiceCount, int skipAttack, int allOutMode) {
        //Add players random issue one player get no army
        //Exception handling
        if (skipAttack == 1) {
            logger.log(Level.INFO, "Skipping attack...");
            // add one random card and reset the win counter
            if (isAttackWon) {
                game.getCurrentPlayer().addRandomCard();
                isAttackWon = false;
            }
            //game.getCurrentPlayer().setAttackCounter(0);
            game.updateCurrentPhase();
            return;
        }
        Country attackingCon = game.getMap().findByCountryName(attackingCountry);
        Country defendingCon = game.getMap().findByCountryName(defendingCountry);
        Player defendingPlayer = game.getPlayerFromCountry(defendingCountry);

        AttackStrategy attackStrategy = new HumanAttackStrategy(attackingCon, defendingCon, attackingDiceCount,
                defendingDiceCount, allOutMode);
        game.getCurrentPlayer().attack(attackStrategy);
        int countOfAttack = attackStrategy.getAttackCounter();

        if (attackingCon.getArmy() == 0) {
            logger.log(Level.INFO, "Attacking country lost all the army");
            game.getCurrentPlayer().removeCountry(attackingCon);
            defendingPlayer.addCountry(attackingCon);
            //updates
            //game.getCurrentPlayer().setAttackCounter(0);
            //game.updateCurrentPhase();
        } else if (defendingCon.getArmy() == 0) {
            logger.log(Level.INFO, "Defending country lost all the army");
            if (attackingCon.getArmy() <= countOfAttack) {
                defendingCon.setArmy(attackingCon.getArmy() - 1);
                attackingCon.setArmy(1);
            } else {
                defendingCon.setArmy(countOfAttack);
                attackingCon.setArmy(attackingCon.getArmy() - countOfAttack);
            }
            isAttackWon = true; // we say attacker won only when he conquer the defending country.
            game.getCurrentPlayer().addCountry(defendingCon);
            defendingPlayer.removeCountry(defendingCon);
            //updates
            //game.getCurrentPlayer().setAttackCounter(0);
        }
        // exchange cards if all countries are lost
        if (game.getCurrentPlayer().getCountries().size() == 0) {
            logger.log(Level.INFO, "all countries lost by attacker");
            game.getCurrentPlayer().sendCardsTo(defendingPlayer);
            game.dropPlayer(game.getCurrentPlayer());
            game.updateCurrentPhase();
        } else if (defendingPlayer.getCountries().size() == 0) {
            logger.log(Level.INFO, "all countries lost by defender");
            defendingPlayer.sendCardsTo(game.getCurrentPlayer());
            game.dropPlayer(defendingPlayer);
            game.updateCurrentPhase();
        }
    }

    /**
     * To initiate Fortification Phase with respective county and armies required
     *
     * @param startCountry     for army transfer
     * @param endCountry       for army transfer
     * @param armyCount-number of armies to be trnasfered
     */
    public void executeFortificationPhase(Game game, String startCountry, String endCountry, int armyCount) {
        Country start = game.getMap().findByCountryName(startCountry);
        Country end = game.getMap().findByCountryName(endCountry);
        logger.log(Level.INFO, String.valueOf(start.getArmy()) + " " + String.valueOf(end.getArmy()));
        Country startCountryObj = game.getMap().findByCountryName(startCountry);
        Country endCountryObj = game.getMap().findByCountryName(endCountry);

        if (game.getCurrentPlayer().fortify(new HumanFortifyStrategy(startCountryObj, endCountryObj, armyCount), game.getMap())) {
            logger.log(Level.INFO, String.valueOf(start.getArmy()) + " " + String.valueOf(end.getArmy()));
            // updates
            game.updateCurrentPhase();
            game.updateCurrentPlayer();
        }

    }

    // -----------------------------------------------------------------------------------------------------------------


}

