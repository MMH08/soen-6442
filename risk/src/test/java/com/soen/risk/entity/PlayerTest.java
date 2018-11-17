package com.soen.risk.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import com.soen.risk.interactor.GamePlay;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class PlayerTest.
 */
public class PlayerTest {

    /**
     * The map.
     */
    private static Map map;

    /**
     * The game.
     */
    private static Game game;
    private static GamePlay p;
    static String path = "/home/varun/Downloads/";
    static String Filename = path + "playerTesting.map";
    private static Player p1;
    private static Player p2;
    /**
     * Sets the up.
     */
    @BeforeClass
    public static void setUp() {
    	p = GamePlay.getInstance();
        map = new Map();
       game = new Game(Filename, 2);
       map.load(Filename);
       p1 = new Player(0);
       p2 = new Player(1);
 
       game.setCurrentPlayer(p1);
       
       List<Player> x = Arrays.asList(p1, p2);
       game.setPlayers(x);
       int i=0;
       for(Country c: map.getCountries())
       {
       	if(i==0)
       	{
       		p1.addCountry(c);
       	}
       	else
       	{
       		p2.addCountry(c);
       	}
       	i++;
       }
       for(Country c: p1.getCountries())
       {
    	   c.setArmy(4);
       }
       for(Country c: p2.getCountries())
       {
    	   c.setArmy(4);
       }
       p.setGame(game);
       
    }
    
    /*
     * 
     */
    @Test
    public void TestStartupPhase()
    {
    	p1.allocateInitialArmy();
    	p2.allocateInitialArmy();
    	
    	assertNotEquals(p1.getArmyCapacity(),0);
    	assertNotEquals(p2.getArmyCapacity(),0);
    }
    /**
     * Test reinforcement army.
     */
    @Test
    public void testReinforcementArmy() {
        

        assertEquals(13, p2.calculateReinforceCount(map));
        assertEquals(3, p1.calculateReinforceCount(map));

    }
    @Test 
    public void TestAttackPhase1()
    {   	
    	game.setCurrentPhase(Phase.ATTACK);
    	Country A = game.getMap().findByCountryName("A");
    	Country C = game.getMap().findByCountryName("C");
    	A.setArmy(4);
    	C.setArmy(4);
    	p.executeAttackPhase("A", "C", 2, 2, 0, 0);  
    	//assertEquals(2,game.getCurrentPlayer().getAttackCounter());
    }
    @Test 
    public void TestAttackPhase2()
    {
    	Country A = game.getMap().findByCountryName("A");
    	Country C = game.getMap().findByCountryName("C");
    	
    	int countryA = game.getCurrentPlayer().getCountries().size();
    	int countryC = p2.getCountries().size();
    	p.executeAttackPhase("A", "C", 2, 2, 0, 1);
    	assertNotEquals(game.getCurrentPlayer().getCountries().size(), countryA);
    	
    }
    
    @Test 
    public void TestAttackPhase3()
    {  
    	p.executeAttackPhase("A", "C", 2, 2, 1, 0); 
    	assertEquals("fortifyPhase",game.getCurrentPhase().toString());
    }
    
    @Test
    public void TestFortifyPhase()
    {
    	game.setCurrentPlayer(p2);
    	Country D = game.getMap().findByCountryName("D");
    	Country E = game.getMap().findByCountryName("E");
    	p.executeFortificationPhase("D", "E", 2);   	
    	assertNotEquals(2, D.getArmy());
    	assertNotEquals(6, E.getArmy());
    }
    

}