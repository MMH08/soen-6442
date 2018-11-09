package com.soen.risk.entity;

import org.junit.Before;
import org.junit.Test;

import com.soen.risk.interactor.GamePlay;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * The Class GameTest.
 */
public class GameTest {

    

   

   

    

    private Game game;
    private GamePlay p;
    static String path = "\\W:\\Java\\";
    static String Filename = path + "playerTesting.map";
    @Before
	public void setUp()
	{
		Game game= new Game(Filename,2);
		p = GamePlay.getInstance();
		p.setGame(game);
	}
    /**
     * Check Phase.
     *
     */
    @Test
    public void checkPhasePlayers() 
    {    	
    	p.getGame().setCurrentPhase(Phase.REINFORCE);
    	p.getGame().updateCurrentPhase();
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