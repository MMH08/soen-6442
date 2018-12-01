/**
 * 
 */
package com.soen.risk.entity.player.random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

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
public class RandomReinforceStrategyTest {
	 RandomReinforceStrategy randomReinforce;
	 Map map;
	 ArrayList<Country> allowedCountries;
	 Path  parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
	 String relativePath = FileSystems.getDefault().getSeparator() + "fixture" + FileSystems.getDefault().getSeparator() + "createnew.map";
	 
	 @Before
	 public void setUp(){
		 randomReinforce=new RandomReinforceStrategy();
		 map=new Map();
		 map.load(parentPath+relativePath);
		 map.findByCountryName("Country2").setArmy(10);
		map.findByCountryName("Country3").setArmy(9);
		allowedCountries=new ArrayList();
		allowedCountries.add(map.findByCountryName("Country2"));
		allowedCountries.add(map.findByCountryName("Country3"));
		 
	 }
	 
	 @Test
	 public void executeTest(){
		 
		 randomReinforce.execute(map, allowedCountries);
		 assertThat(map.findByCountryName("Country3").getArmy(),greaterThan((9)) );
		 
		 
		 
	 }

	

}
