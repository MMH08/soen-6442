/**
 * 
 */
package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author fly
 *
 */
public class BenevolentFortifyStrategyTest {
	BenevolentFortifyStrategy benFortifyStrategy;
	Map map;
	
	
	@Before
	public void setUp(){
		benFortifyStrategy=new BenevolentFortifyStrategy();
		map=new Map();
		
		map.load("createnew.map");
		map.findByCountryName("Country1").setArmy(10);
		 map.findByCountryName("Country2").setArmy(20);
		 map.findByCountryName("Country3").setArmy(30);
		
		
	}
	
	@Test
	public void executeTest(){
		
		benFortifyStrategy.execute(map, map.getCountries());
		
		map.findByCountryName("Country3").getArmy();
		
		assertThat("army",map.findByCountryName("Country1").getArmy(),greaterThan(10));
		
		//Assert.assertNotEquals(map.findByCountryName("Country3").getArmy(), 30);
		
	}

}
