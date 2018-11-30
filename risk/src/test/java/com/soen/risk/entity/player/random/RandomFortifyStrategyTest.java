/**
 * 
 */
package com.soen.risk.entity.player.random;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author fly
 *
 */
public class RandomFortifyStrategyTest {
	RandomFortifyStrategy randomFortify;
	Map map;
	ArrayList<Country> allowedCountries;
	Path  parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
    String relativePath = FileSystems.getDefault().getSeparator() + "fixture" + FileSystems.getDefault().getSeparator() + "createnew.map";
	
    @Before
	public void setUp(){
		map=new Map();
		map.load(parentPath+relativePath);
		
		map.findByCountryName("Country2").setArmy(5);
		map.findByCountryName("Country4").setArmy(5);
		map.findByCountryName("Country1").setArmy(10);
		map.findByCountryName("Country3").setArmy(10);
		allowedCountries=new ArrayList();
		allowedCountries.add(map.findByCountryName("Country2"));
		allowedCountries.add(map.findByCountryName("Country3"));
		//allowedCountries.add(map.findByCountryName("Country4"));
		//allowedCountries.add(map.findByCountryName("Country1"));
		
		
		
		randomFortify=new RandomFortifyStrategy();
		
	}
	
	@Test
	public void executeTest(){
		randomFortify.execute(map, allowedCountries);
		
		//Assert.assertEquals(, map.findByCountryName("Country3").getArmy());
		
	}
}
