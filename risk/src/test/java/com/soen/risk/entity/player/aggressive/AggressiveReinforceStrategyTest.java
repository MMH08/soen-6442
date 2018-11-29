/**
 * 
 */
package com.soen.risk.entity.player.aggressive;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.*;
import org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;


/**
 * @author fly
 *
 */
public class AggressiveReinforceStrategyTest {
	
	
	Map map;
	public AggressiveReinforceStrategy aggReinforceStrategy;
	Path  parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
    String relativePath = FileSystems.getDefault().getSeparator() + "fixture" + FileSystems.getDefault().getSeparator() + "createnew.map";
	
	@Before
	public void setUp(){
			map=new Map();
			aggReinforceStrategy=new AggressiveReinforceStrategy();
			map.load(parentPath+relativePath);
			
			
			
		}
	
	
	@Test
	public void executeTest()
	{
		
		ReinforceStrategy.calculateArmyCount(map, map.getCountries());
		ArrayList<Country> countryList=map.getCountries();
		ArrayList<Integer> armiesCount=new ArrayList<>();
		for (Country c:countryList)
		{
			armiesCount.add(c.getArmy());
		}
		Collections.sort(armiesCount, Collections.reverseOrder());
		
		
		aggReinforceStrategy.execute(map, map.getCountries());
		countryList=map.getCountries();
		ArrayList<Integer> armiesCount1=new ArrayList<>();
		for (Country c:countryList)
		{
			armiesCount1.add(c.getArmy());
		}
		Collections.sort(armiesCount1, Collections.reverseOrder());
		assertThat(armiesCount1.get(0) , greaterThan(armiesCount.get(0)));
	
		
		
	}

}
