/**
 * 
 */
package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author fly
 *
 */
//Need to rewrite-Uses GamePlay
public class BenevolentFortifyStrategyTest {
	BenevolentFortifyStrategy benFortifyStrategy;
	Map map;
	ArrayList countries=new ArrayList<>();
	Path  parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
    String relativePath = FileSystems.getDefault().getSeparator() + "fixture" + FileSystems.getDefault().getSeparator() + "createnew.map";
	
	@Before
	public void setUp(){
		benFortifyStrategy=new BenevolentFortifyStrategy();
		map=new Map();
		
		map.load(parentPath+relativePath);
		map.findByCountryName("Country1").setArmy(10);
		map.findByCountryName("Country2").setArmy(20);
		 map.findByCountryName("Country3").setArmy(30);
		 
		 countries.add(map.findByCountryName("Country2"));
		 countries.add(map.findByCountryName("Country3"));
		
	}
	
	@Test
	public void executeTest(){
		
		benFortifyStrategy.execute(map, countries);
		
		assertThat("army",map.findByCountryName("Country2").getArmy(),greaterThan(20));
		
		
		
	}

}
