/**
 * 
 */
package com.soen.risk.entity.player.cheater;

import java.nio.file.FileSystems;
import java.nio.file.Path;
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
public class CheaterAttackStrategyTest {
	CheaterAttackStrategy cheaAttack;
	Map map;
	ArrayList<Country> listofCountries=new ArrayList<Country>();
	Path  parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
    String relativePath = FileSystems.getDefault().getSeparator() + "fixture" + FileSystems.getDefault().getSeparator() + "createnew.map";
	
	@Before
	public void setUp(){
		cheaAttack=new CheaterAttackStrategy();
		map=new Map();
		map.load(parentPath+relativePath);
		
		Country country=map.findByCountryName("Country1");
		country.setArmy(20);
		
		listofCountries.add(country);
		
		
	}
	
	@Test
	public void executeTest()
	{
		cheaAttack.execute(map, listofCountries);
		
		assertEquals(2,cheaAttack.getWon().size());
	}

}
