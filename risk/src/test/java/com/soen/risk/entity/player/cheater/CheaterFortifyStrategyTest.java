/**
 * 
 */
package com.soen.risk.entity.player.cheater;

import java.util.ArrayList;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author fly
 *
 */
public class CheaterFortifyStrategyTest {
	CheaterFortifyStrategy cheaFortify;
	Map map;
	ArrayList<Country> listofCountries=new ArrayList<Country>();
	Country country;
	
	@Before
	public void setUp(){
		cheaFortify=new CheaterFortifyStrategy();
		map=new Map();
		map.load("createnew.map");
		
		country=map.findByCountryName("Country1");
		
		country.setArmy(20);
		listofCountries.add(country);
	}
	
	@Test
	public void executeTest(){
		cheaFortify.execute(map, listofCountries);
		assertEquals(80, country.getArmy());
		
		
	}

}
