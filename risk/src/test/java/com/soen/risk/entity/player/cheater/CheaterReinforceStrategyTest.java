/**
 * 
 */
package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Map;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author fly
 *
 */
public class CheaterReinforceStrategyTest {
	
	CheaterReinforceStrategy cheaReinforce;
	Map map;
	
	@Before
	public void setUp()
	{
		cheaReinforce=new CheaterReinforceStrategy();
		map =new Map();
		map.load("createnew.map");
		map.findByCountryName("Country1").setArmy(40);
		map.findByCountryName("Country2").setArmy(20);
		
		
	}

	@Test
	public void executeTest(){
		cheaReinforce.execute(map, map.getCountries());
		
		assertEquals(80, map.findByCountryName("Country1").getArmy());
		assertEquals(40, map.findByCountryName("Country2").getArmy());
		
	}
}
