/**
 * 
 */
package com.soen.risk.entity.player.benevolent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

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
	 
	 
	 @Before
	 public void setUp(){
		 benReinforceStrategy=new BenevolentReinforceStrategy();
		 map =new Map();
		 map.load("createnew.map");
		 
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
