/**
 * 
 */
package com.soen.risk.entity.player.aggressive;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

/**
 * @author fly
 *
 */
public class AggressiveAttackStrategyTest {
	
	Map map;
	public AggressiveAttackStrategy aggAttackStrategy;
	 Path  parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
     String relativePath = FileSystems.getDefault().getSeparator() + "fixture" + FileSystems.getDefault().getSeparator() + "createnew.map";
	
	@Before
	public void setUp(){
			map=new Map();
			aggAttackStrategy=new AggressiveAttackStrategy();
			 map.load(parentPath+relativePath);
			 map.findByCountryName("Country1").setArmy(10);
				map.findByCountryName("Country2").setArmy(20);
				map.findByCountryName("Country3").setArmy(7);
				map.findByCountryName("Country4").setArmy(13);
			
			
		}
	@Test
	public void executeTest(){
		ArrayList<Country> allowedCountries=new ArrayList();
		
		allowedCountries.add(map.findByCountryName("Country2"));
		
		
		
		aggAttackStrategy.execute(map, allowedCountries);
		aggAttackStrategy.getWon().contains(map.findByCountryName("Country1"));
		
		//Assert.assertNotEquals(20, map.findByCountryName("Country2").getArmy());
		//Assert.assertTrue(aggAttackStrategy.getWon().contains(map.findByCountryName("Country2")));
		Assert.assertNotEquals(0, aggAttackStrategy.getAttackCounter());
		
		
	}
	
	
}


