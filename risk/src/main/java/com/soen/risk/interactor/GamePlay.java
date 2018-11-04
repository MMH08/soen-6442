package com.soen.risk.interactor;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Player;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h2>Game Play</h2>
 * <ul>
 * <li>Create Map class object to perform all functionality of map class i.e. allocating initial armies etc.
 * <li>Change Current Phase
 * <li>Change Current player after its turn
 * </ul>
 *
 * @author Varun Singhal
 * @version 1.0.0
 * @since 2018-10-10
 */

public class GamePlay {
    private static Logger logger = Logger.getLogger(GamePlay.class.getName());

    private static GamePlay gamePlayInstance = null;

    private Game game;
    private PhaseView phaseView;
    private DominationView dominationView;

    public static GamePlay getInstance() {
        if (gamePlayInstance == null)
            gamePlayInstance = new GamePlay();
        return gamePlayInstance;
    }

    private GamePlay() {
    }

    public void start(String filename, int countOfPlayers) {
        game = new Game(filename, countOfPlayers);

        phaseView = new PhaseView();
        game.addObserver(phaseView);

        // register the observer - dominationView
        dominationView = new DominationView();
        for (Player player : game.getPlayers()) {
            player.addObserver(dominationView);
        }

        // record the changes in views
        game.initialize();
    }

    private void end() {
    }

    // -----------------------------------------------------------------------------------------------------------------

    public void executeStartupPhase(String countryName, int armyCount) {
        // implementation
        Player player = game.getCurrentPlayer();
        Country country = game.getMap().findByCountryName(countryName);
        country.setArmy(armyCount); // todo
        player.setArmyCapacity(player.getArmyCapacity() - armyCount);

        // update the player
        game.updateCurrentPlayer();

        if (allPlayersHaveZeroArmy()) {
            game.updateCurrentPhase();
        }
    }

    public void executeReinforcePhase(ArrayList<Integer> armyCounts) {
        //implementation
        Player player = game.getCurrentPlayer();
        int i = 0;
        for (Country c : player.getCountries()) {
            logger.log(Level.INFO, "Adding reinforce army to country " + c.getName() + ", army count " + armyCounts.get(i));
            c.setArmy(c.getArmy() + armyCounts.get(i)); // new army count of the country
            player.setArmyCapacity(player.getArmyCapacity() - armyCounts.get(i)); // new army capacity left with player
            i++;
        }
        // update the phase
        game.updateCurrentPhase();
    }

    public void executeAttackPhase(){

        game.updateCurrentPhase();
        // who ever lost the match - check below condition
//        if (lostPlayer.getCountries().size() == 0) {
//            logger.log(Level.INFO, "Dropping the player " + p.getName());
//            game.dropPlayer(lostPlayer);
//        }
        if(game.getPlayers().size() == 1){
            end();
        }
    }

    public void executeFortificationPhase(String startCountry, String endCountry, int armyCount) {
    	if(game.getMap().pathExists(startCountry, endCountry,(ArrayList) game.getCurrentPlayer().getCountries()))
    	{
    		int flag = 0;
    		Country start=null;
    		Country end=null;
    		for(Country c: game.getCurrentPlayer().getCountries())
    		{
    			if (c.getName().equals(startCountry)) {
    				if(c.getArmy() <= armyCount)
    				{
    					c.setArmy(1);
    					flag = 1;
    					start=c;
    				}
    				else
    				{
    					c.setArmy(c.getArmy() - armyCount);
    				}
                    
                }
                if (c.getName().equals(endCountry)) {
                	end = c;
                }
    		}
    		if(flag == 1)
    		{
    			end.setArmy(end.getArmy() + start.getArmy() - 1);
    		}
    		else 
    		{
    			end.setArmy(end.getArmy() + armyCount);
    		}
    	}
        game.updateCurrentPhase();
        game.updateCurrentPlayer();
    }

    // -----------------------------------------------------------------------------------------------------------------


    private boolean allPlayersHaveZeroArmy() {
        for (Player p : game.getPlayers()) {
            if (p.getArmyCapacity() != 0) return false;
        }
        return true;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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


//    public void execute(){
//        // output received from the phase execution on the current player.
//        String outputString = "";
//        this.updateAction(outputString);
//    }
//
//
//    private void setCurrentPlayer(Player currentPlayer) {
//        logger.log(Level.INFO, "Changing player to " + currentPlayer.getName());
//        this.currentPlayer = currentPlayer;
//        this.setChanged();
//        this.notifyObservers(this);
//    }
//
//    private void setCurrentPhase(String currentPhase) {
//        logger.log(Level.INFO, "Changing phase to " + currentPhase);
//        this.currentPhase = currentPhase;
//        this.resetAction();
//
//    }

//    private void updateAction(String actionItem){
//        this.action.add(actionItem);
//        this.setChanged();
//        this.notifyObservers(this);
//    }
//
//    private void resetAction(){
//        this.action = new ArrayList<>();
//        this.setChanged();
//        this.notifyObservers(this);
//    }

//    public void endGame() {
//        this.currentPhase = "";
//    }
//
//    public List<String> getAction() {
//        return action;
//    }
//    public Game getGame() {
//        return game;
//    }
//
//    public Player getCurrentPlayer() {
//        return currentPlayer;
//    }
//
//    public String getCurrentPhase() {
//        return currentPhase;
//    }
}
