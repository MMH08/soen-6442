/**
 * 
 */
package com.soen.risk.entity.player.random;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.soen.risk.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * @author fly
 *
 */
public class RandomStartupStrategyTest {
	RandomStartupStrategy randomStartup;
	Country country;
	
	@Before
	public void setUp(){
		randomStartup=new RandomStartupStrategy();
		country=new Country(1,"UK");
		country.setArmy(40);
	}
	
	@Test
	public void executeTest(){
		randomStartup.execute(country, 1);
		Assert.assertEquals(41, country.getArmy());
	}

}
