/**
 * 
 */
package com.soen.risk.entity.player.human;

import com.soen.risk.entity.Country;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author fly
 *
 */
public class HumanStartupStrategyTest {
	HumanStartupStrategy humanStartup;
	Country country;
	
	
	@Before
	public void setUp(){
		humanStartup=new HumanStartupStrategy(10);
		country=new Country(1, "Canada");
		country.setArmy(20);
	}
	
	@Test
	public void executeTest(){
		humanStartup.execute(country, 10);
		Assert.assertEquals(30, country.getArmy());
	}
	
	

}
