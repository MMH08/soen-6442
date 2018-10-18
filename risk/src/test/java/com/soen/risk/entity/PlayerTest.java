package com.soen.risk.entity;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class PlayerTest.
 */
public class PlayerTest {

	/** The map. */
	private static Map map; 
	
	/** The game. */
	private static Game game;
    
    /**
     * Sets the up.
     */
    @BeforeClass
    public static void setUp()
    {
    	map = new Map();
    	game = new Game(map,2);
    }
	
	/**
	 * Adds the country.
	 */
	@Test
    public void addCountry() {
    }

    /**
     * Next country to assign army.
     */
    @Test
    public void nextCountryToAssignArmy() {
    }

    /**
     * Gets the country names.
     *
     */
    @Test
    public void getCountryNames() {
    }
    
    /**
     * Test reinforcement army.
     */
    @Test
    public void testReinforcementArmy()
    {
    	Country c1 = new Country(0,"A");
    	Country c2 = new Country(1,"B");
    	Country c3 = new Country(2,"C");
    	Country c4 = new Country(3,"D");
    	Country c5 = new Country(4,"E");
    	map.addCountry(c1);
    	map.addCountry(c2);
    	map.addCountry(c3);
    	map.addCountry(c4);
    	map.addCountry(c5);
    	String s[] = {c1.getName(),c2.getName(),c3.getName(),c4.getName()};
    	map.map_name_creation(s);
    	String s1[] = {c2.getName(),c3.getName(),c4.getName(),c1.getName()};
    	String s2[] = {c3.getName(),c4.getName(),c1.getName(),c2.getName()};
    	String s3[] = {c4.getName(),c3.getName(),c2.getName(),c1.getName()};
    	String s4[] = {c5.getName()};
    	map.map_name_creation(s1);
    	map.map_name_creation(s2);
    	map.map_name_creation(s3);
    	map.map_name_creation(s4);
    	map.map_country_object_creation();
    	
    	Continent co1= new Continent("AA");
    	co1.setControlValue(11);
    	Continent co2= new Continent("BB");
    	co2.setControlValue(13);
    	map.addContinent(co1);
    	map.addContinent(co2);
    	co1.addCountry(c1);
    	co1.addCountry(c2);
    	co1.addCountry(c3);
    	co2.addCountry(c4);
    	co2.addCountry(c5);
    	
    	//Now player work
    	Player p1 = new Player(0);
    	Player p2 = new Player(1);
    	List<Player> x = Arrays.asList(p1,p2);
    	game.setPlayers(x);
    	p1.addCountry(c1);
    	p2.addCountry(c2);
    	p2.addCountry(c3);
    	p2.addCountry(c4);
    	p2.addCountry(c5);
    	
    	assertEquals(13, p2.getReinforceArmyCapacity(map));
    	assertEquals(3,p1.getReinforceArmyCapacity(map));
    	
    }

}