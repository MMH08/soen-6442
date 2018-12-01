/**
 * 
 */
package com.soen.risk.entity.player.human;

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
public class HumanAttackStrategyTest {
	HumanAttackStrategy humanAttack;
	Map map;
	ArrayList<Country> allowedCountries;
	Path  parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
    String relativePath = FileSystems.getDefault().getSeparator() + "fixture" + FileSystems.getDefault().getSeparator() + "createnew.map";
	
    @Before
	public void setUp(){
    	
		map=new Map();
		map.load(parentPath+relativePath);
		
		map.findByCountryName("Country1").setArmy(10);
		map.findByCountryName("Country2").setArmy(20);
		map.findByCountryName("Country3").setArmy(7);
		map.findByCountryName("Country4").setArmy(0);
		allowedCountries=new ArrayList();
		allowedCountries.add(map.findByCountryName("Country2"));
		allowedCountries.add(map.findByCountryName("Country4"));
		
		humanAttack=new HumanAttackStrategy(map.findByCountryName("Country2"), map.findByCountryName("Country4"), 2,1,0 , 1);
		
	}
	
	@Test
	public void executeTest(){
		humanAttack.execute(map, allowedCountries);
		Assert.assertNotEquals(0, humanAttack.getWon().size());
	}
	
	
	

}
