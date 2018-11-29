/**
 * 
 */
package com.soen.risk.entity.player.benevolent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.soen.risk.entity.Map;

/**
 * @author fly
 *
 */
public class BenevolentReinforceStrategyTest {
	
	 BenevolentReinforceStrategy benReinforceStrategy;
	 Map map;
	 Path  parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
	 String relativePath = FileSystems.getDefault().getSeparator() + "fixture" + FileSystems.getDefault().getSeparator() + "createnew.map";
	 
	 
	 @Before
	 public void setUp(){
		 benReinforceStrategy=new BenevolentReinforceStrategy();
		 map =new Map();
		 map.load(parentPath+relativePath);
		 
		 map.findByCountryName("Country1").setArmy(10);
		 map.findByCountryName("Country2").setArmy(20);
		 map.findByCountryName("Country3").setArmy(30);
		 map.findByCountryName("Country4").setArmy(40);
		}
	 
	 @Test
	 public void executeTest()
	 {
		 benReinforceStrategy.execute(map, map.getCountries());
		 assertThat("army",map.findByCountryName("Country1").getArmy(),greaterThan(10));
	 }
	 

}
