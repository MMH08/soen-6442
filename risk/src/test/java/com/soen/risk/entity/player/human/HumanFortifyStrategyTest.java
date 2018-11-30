/**
 * 
 */
package com.soen.risk.entity.player.human;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import org.junit.Assert;

/**
 * @author fly
 *
 */
public class HumanFortifyStrategyTest {
	HumanFortifyStrategy humanFortify;
	Map map;
	ArrayList<Country> allowedCountries;
	Path  parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
    String relativePath = FileSystems.getDefault().getSeparator() + "fixture" + FileSystems.getDefault().getSeparator() + "createnew.map";
	
    @Before
	public void setUp(){
		map=new Map();
		map.load(parentPath+relativePath);
		map.findByCountryName("Country2").setArmy(20);
		map.findByCountryName("Country3").setArmy(7);
		allowedCountries=new ArrayList();
		allowedCountries.add(map.findByCountryName("Country2"));
		allowedCountries.add(map.findByCountryName("Country3"));
		
		humanFortify=new HumanFortifyStrategy(map.findByCountryName("Country2"), map.findByCountryName("Country3"), 5);
	}
	
	@Test
	public void executeTest(){
		humanFortify.execute(map, allowedCountries);
		
		Assert.assertEquals(12, map.findByCountryName("Country3").getArmy());
		
	}

}
