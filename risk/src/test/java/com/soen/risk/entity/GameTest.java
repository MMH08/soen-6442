package com.soen.risk.entity;

import com.soen.risk.interactor.GamePlay;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The Class GameTest.
 */
public class GameTest {

    

   

   

    

    private Game game;
    private GamePlay p;
    static String path = "/home/varun/Downloads/";
    static String Filename = path + "playerTesting.map";
    @Before
	public void setUp()
	{
//		Game game= new Game(Filename,2);
//		p = GamePlay.getInstance();
//		p.setGame(game);
	}
    /**
     * Check Phase.
     *
     */
    @Test
    public void checkPhasePlayers() 
    {    	
    	p.getGame().setCurrentPhase(Phase.REINFORCE);
    	//p.getGame().updateCurrentPhase();
    	assertEquals("attackPhase",p.getGame().getCurrentPhase().toString());
    }
    @Test
    public void intialCountries()
    {
    	p.getGame().allocateInitialCountries();
    	for(Player p1: p.getGame().getPlayers())
    	{
    		assertNotEquals(p1.getCountries().size(),0);
    	}
    }
    
}