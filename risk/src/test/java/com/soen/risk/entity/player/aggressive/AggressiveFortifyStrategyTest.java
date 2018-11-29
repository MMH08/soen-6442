/**
 * 
 */
package com.soen.risk.entity.player.aggressive;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.*;
import org.junit.Assert.*;


import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.player.human.HumanFortifyStrategy;

/**
 * @author fly
 *
 */
public class AggressiveFortifyStrategyTest {
	Map map;
	public AggressiveFortifyStrategy aggfortifyStrategy;
	
	@Before
	public void setUp(){
			map=new Map();
			aggfortifyStrategy=new AggressiveFortifyStrategy();
			map.load("createnew.map"); 
	}
	
	@Test
	public void executeTest(){
		map.findByCountryName("Country1").setArmy(10);
		map.findByCountryName("Country2").setArmy(20);
		map.findByCountryName("Country3").setArmy(7);
		map.findByCountryName("Country4").setArmy(13);
		
		
		
		ArrayList<Country> allowedCountries=new ArrayList();
		allowedCountries.add(map.findByCountryName("Country2"));
		allowedCountries.add(map.findByCountryName("Country3"));
		allowedCountries.add(map.findByCountryName("Country4"));


		
		aggfortifyStrategy.execute(map, allowedCountries);
		
		Assert.assertEquals(25, map.findByCountryName("Country2").getArmy());

		
		
	}
	
	
}
