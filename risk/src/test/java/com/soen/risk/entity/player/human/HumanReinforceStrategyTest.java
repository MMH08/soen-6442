/**
 * 
 */
package com.soen.risk.entity.player.human;

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
public class HumanReinforceStrategyTest {
	
	HumanReinforceStrategy humanReinforce;
	Map map;
	ArrayList<Country> allowedCountries;
	ArrayList armyCounts=new ArrayList<>();
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
		
		armyCounts.add(1);
		armyCounts.add(2);
		
		humanReinforce=new HumanReinforceStrategy(armyCounts);
	}
	
	@Test
	public void executeTest(){
		humanReinforce.execute(map, allowedCountries);
		
		Assert.assertEquals(21, map.findByCountryName("Country2").getArmy());
		Assert.assertEquals(2, map.findByCountryName("Country4").getArmy());
	}

}
