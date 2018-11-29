/**
 * 
 */
package com.soen.risk.entity.player.benevolent;

import org.junit.Test;

import com.soen.risk.entity.Country;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author fly
 *
 */
public class BenevolentStartupStrategyTest {
	public BenevolentStartupStrategy benStartupStrategy;
	Country country;
	
	@Before
	public void setUp()
	{
		benStartupStrategy=new BenevolentStartupStrategy();
		country=new Country(22, "Africa");
		country.addArmy(20);
	}
	
	@Test
	public void executeTest(){
		benStartupStrategy.execute(country, 20);
		
		Assert.assertEquals(24, country.getArmy());
	}

}
